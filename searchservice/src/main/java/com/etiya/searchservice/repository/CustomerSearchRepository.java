package com.etiya.searchservice.repository;

import com.etiya.searchservice.domain.CustomerSearch;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerSearchRepository extends ElasticsearchRepository<CustomerSearch, String> , CustomCustomerSearchRepository {

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
                      "match": {
                        "firstName": "?0"
                      }
                    },
                    {
                      "nested": {
                        "path": "addressSearchList",
                        "query": {
                          "match": {
                            "addressSearchList.cityName": "?1"
                          }
                        }
                      }
                    }
                  ]
              }
            }
            """)
    List<CustomerSearch> filterSurnameWithCitiesBool(String firstName, String cityName);

    @Query("""
{
  "range": {
    "dateOfBirth": {
      "gte": "?0",
      "lte": "?1"
    }
  }
}
""")
    List<CustomerSearch> findByBirthYearRange(String startYear, String endYear);


}
