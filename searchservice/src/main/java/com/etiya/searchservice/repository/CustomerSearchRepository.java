package com.etiya.searchservice.repository;

import com.etiya.searchservice.domain.CustomerSearch;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerSearchRepository extends ElasticsearchRepository<CustomerSearch, String> {

    @Query("""     
    {
        "query_string":
        {
            "query":"*?0*",
            "fields":["firstName", "lastName", "nationalId", "customerNumber"]
        }
    }
    """)
    List<CustomerSearch> searchAllFields(String keyword);

    @Query("""
        {
          "match": {
            "firstName": "?0"
          }
        }
        """)
    List<CustomerSearch> firstNameMatch(String firstName);

    @Query("""
        {
            "term":
            {
                "nationalId": "?0"
            }
        }
    """)
    List<CustomerSearch> nationalIdTerm(String keyword);

    @Query("""
    {
      "fuzzy": {
        "firstName": {
          "value": "?0",
          "fuzziness": "AUTO"
        }
      }
    }
    """)
    List<CustomerSearch> fuzzyTerm(String keyword);

    @Query("""
    {
      "bool": {
        "must": [
          {
            "term": {
              "districtId": "?0"
            }
          },
          {
            "match": {
              "lastName": "?1"
            }
          }
        ]
      }
    }
    """)
    List<CustomerSearch> filterSurnameWithCitiesBool(String districtId, String lastName);
}
