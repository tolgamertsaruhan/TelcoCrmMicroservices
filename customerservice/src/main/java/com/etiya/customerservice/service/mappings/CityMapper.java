package com.etiya.customerservice.service.mappings;

import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.service.requests.city.CreateCityRequest;
import com.etiya.customerservice.service.requests.city.UpdateCityRequest;
import com.etiya.customerservice.service.responses.city.CreatedCityResponse;
import com.etiya.customerservice.service.responses.city.GetCityResponse;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;
import com.etiya.customerservice.service.responses.city.UpdatedCityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    City cityFromCreateCityRequest(CreateCityRequest request);

    CreatedCityResponse createdCityResponseFromCity(City city);

    @Mapping(target = "districts", source = "districts")
    List<GetListCityResponse> getListCityResponseFromCity(List<City> cities);

    GetCityResponse getCityResponseFromCity(City city);

    City cityFromUpdateCityRequest(UpdateCityRequest request, @MappingTarget City city);

    UpdatedCityResponse updatedCityResponseFromCity(City city);

    default List<UUID> mapAddressListToUuidList(List<Address> addresses) {
        // Eğer gelen liste null ise, boş bir liste döndürerek NullPointerException'ı önle
        if (addresses == null) {
            return java.util.Collections.emptyList();
        }
        // Gelen Address listesini stream'e çevir,
        // her bir Address nesnesinin ID'sini al (Address::getId),
        // ve bunları yeni bir UUID listesi olarak topla.
        return addresses.stream()
                .map(Address::getId) // Buradaki getId() metodunun sizde olduğunu varsayıyorum
                .collect(Collectors.toList());
    }

    //List<GetCityWithDistrictsResponse> getCityWithDistrictsResponseFromCity(List<City> cities);

}