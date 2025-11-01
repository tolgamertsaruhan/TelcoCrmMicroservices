package com.etiya.searchservice.repository;

import com.etiya.searchservice.domain.CustomerSearch;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import java.util.List;

@Repository
public class CustomCustomerSearchRepositoryImpl implements CustomCustomerSearchRepository {
    private final ElasticsearchOperations elasticsearchOperations;

    public CustomCustomerSearchRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<CustomerSearch> searchDynamic(String id, String accountNumber, String nationalId, String firstName, String middleName, String  lastName, String value,int page, int size) {
        BoolQuery.Builder bool = QueryBuilders.bool();

        if (StringUtils.hasText(id)) {
            bool.must(m -> m.term(t -> t.field("id.keyword").value(id)));
        }
        /*if (StringUtils.hasText(customerNumber)) {
            bool.must(m -> m.term(t -> t.field("customerNumber.keyword").value(customerNumber)));
        }*/

        if (StringUtils.hasText(accountNumber)) {
            bool.must(m -> m.nested(n -> n
                    .path("billingAccountList")
                    .query(q -> q.term(t -> t.field("billingAccountList.accountNumber.keyword").value(accountNumber)))
            ));
        }
        if (StringUtils.hasText(nationalId)) {
            bool.must(m -> m.term(t -> t.field("nationalId.keyword").value(nationalId)));
        }
        if (StringUtils.hasText(firstName)) {
            bool.must(m -> m.matchPhrase(mp -> mp.field("firstName").query(firstName)));
        }
        if (StringUtils.hasText(middleName)) {
            bool.must(m -> m.matchPhrase(mp -> mp.field("middleName").query(middleName)));
        }
        if (StringUtils.hasText(lastName)) {
            bool.must(m -> m.matchPhrase(mp -> mp.field("lastName").query(lastName)));
        }
        if (StringUtils.hasText(value)) {
            bool.must(m -> m.nested(n -> n
                    .path("contactMediumSearchList")
                    .query(q -> q.bool(nb -> nb
                            .must(mt -> mt.term(t -> t.field("contactMediumSearchList.type.keyword").value("MOBILE_PHONE"))) // sadece PHONE
                            .must(mt -> mt.term(t -> t.field("contactMediumSearchList.value.keyword").value(value)))
                    ))
            ));
        }

        Query query = bool.build()._toQuery();
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(query)
                .withPageable(PageRequest.of(page, size))
                .build();

        SearchHits<CustomerSearch> hits =
                elasticsearchOperations.search(nativeQuery, CustomerSearch.class);

        return hits.stream().map(SearchHit::getContent).toList();
    }

}