package com.etiya.searchservice.repository;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.repository.CustomerCustomSearchRepository;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerCustomSearchRepositoryImpl implements CustomerCustomSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<CustomerSearch> searchDynamic(CustomerSearch request) {

        List<Query> mustQueries = new ArrayList<>();

        appendIfNotBlank(mustQueries, "id", request.getId(), true);
        appendIfNotBlank(mustQueries, "customerNumber", request.getCustomerNumber(), true);
        appendIfNotBlank(mustQueries, "firstName", request.getFirstName(), false);
        appendIfNotBlank(mustQueries, "lastName", request.getLastName(), false);
        appendIfNotBlank(mustQueries, "nationalId", request.getNationalId(), true);

        if (request.getContactMediumSearchList() != null) {
            for (ContactMedium c : request.getContactMediumSearchList()) {
                if ("PHONE".equals(c.getType()) && isNotBlank(c.getValue())) {

                    // İç bool query (nested)
                    BoolQuery innerBool = BoolQuery.of(b -> b
                            .must(QueryBuilders.term(t -> t.field("contactMediumSearchList.type.keyword").value("PHONE")))
                            .must(QueryBuilders.wildcard(w -> w.field("contactMediumSearchList.value.keyword")
                                    .wildcard("*" + c.getValue() + "*")))
                    );

                    Query innerQuery = new Query(innerBool);

                    Query nestedQuery = QueryBuilders.nested(n -> n
                            .path("contactMediumSearchList")
                            .query(innerQuery)
                            .scoreMode(co.elastic.clients.elasticsearch._types.query_dsl.ChildScoreMode.None)
                    );

                    mustQueries.add(nestedQuery);
                }
            }
        }

        Query mainQuery = QueryBuilders.bool(b -> b.must(mustQueries));

        NativeQuery query = NativeQuery.builder()
                .withQuery(mainQuery)
                .build();

        SearchHits<CustomerSearch> hits = elasticsearchOperations.search(query, CustomerSearch.class);

        for (SearchHit<CustomerSearch> hit : hits) {
            System.out.println("Raw content: " + hit.getContent());

        }
        return hits.stream().map(SearchHit::getContent).toList();
    }

    // Yardımcı metotlar
    private void appendIfNotBlank(List<Query> mustQueries, String field, String value, boolean exact) {
        if (isNotBlank(value)) {
            Query q = exact
                    ? QueryBuilders.term(t -> t.field(field).value(value))
                    : QueryBuilders.wildcard(w -> w.field(field).wildcard("*" + value + "*"));
            mustQueries.add(q);
        }
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

}