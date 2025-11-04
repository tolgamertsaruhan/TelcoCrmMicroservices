package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.Catalog;
import com.etiya.catalogservice.service.requests.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.requests.catalog.UpdateCatalogRequest;
import com.etiya.catalogservice.service.responses.catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.GetCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.GetListCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.UpdatedCatalogResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper()
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    // 🔹 Request → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "catalogProductOffers", ignore = true)
    @Mapping(target = "parent", expression = "java(mapParent(request.getParentId()))")
    Catalog createCatalogRequestToCatalog(CreateCatalogRequest request);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "catalogProductOffers", ignore = true)
    @Mapping(target = "parent", expression = "java(mapParent(request.getParentId()))")
    Catalog updateCatalogRequestToCatalog(UpdateCatalogRequest request);

    // 🔹 Entity → Response
    @Mapping(target = "parentId", source = "parent.id")
    CreatedCatalogResponse catalogToCreatedCatalogResponse(Catalog catalog);

    @Mapping(target = "parentId", source = "parent.id")
    UpdatedCatalogResponse catalogToUpdatedCatalogResponse(Catalog catalog);

    @Mapping(target = "parentId", source = "parent.id")
    GetCatalogResponse catalogToGetCatalogResponse(Catalog catalog);

    @Mapping(target = "parentId", source = "parent.id")
    GetListCatalogResponse catalogToGetListCatalogResponse(Catalog catalog);

    List<GetListCatalogResponse> catalogListToGetListCatalogResponseList(List<Catalog> catalogs);

    // 🔹 Yardımcı method (parentId varsa set et)
    default Catalog mapParent(UUID parentId) {
        if (parentId == null) {
            return null;
        }
        Catalog parent = new Catalog();
        parent.setId(parentId);
        return parent;
    }
}