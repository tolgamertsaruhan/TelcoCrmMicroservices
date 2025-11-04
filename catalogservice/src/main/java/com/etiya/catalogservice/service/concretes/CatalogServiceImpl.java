package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Catalog;
import com.etiya.catalogservice.repository.CatalogRepository;
import com.etiya.catalogservice.service.abstracts.CatalogService;
import com.etiya.catalogservice.service.mappings.CatalogMapper;
import com.etiya.catalogservice.service.requests.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.requests.catalog.UpdateCatalogRequest;
import com.etiya.catalogservice.service.responses.catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.GetCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.GetListCatalogResponse;
import com.etiya.catalogservice.service.responses.catalog.UpdatedCatalogResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public CreatedCatalogResponse add(CreateCatalogRequest request) {
        Catalog catalog = CatalogMapper.INSTANCE.createCatalogRequestToCatalog(request);

        // 🔹 Eğer parentId varsa, parent'ı gerçekten DB'den çek
        if (catalog.getParent() != null && catalog.getParent().getId() != null) {
            Catalog parent = catalogRepository.findById(catalog.getParent().getId())
                    .orElseThrow(() -> new RuntimeException("Parent catalog not found"));
            catalog.setParent(parent);
        }
        Catalog created = catalogRepository.save(catalog);
        return CatalogMapper.INSTANCE.catalogToCreatedCatalogResponse(created);
    }

    @Override
    public UpdatedCatalogResponse update(UpdateCatalogRequest request) {
        Catalog existing = catalogRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Catalog not found"));

        // Mapper’dan yeni nesne oluştur ama mevcut entity’nin bazı alanlarını koruyabilirsin (isteğe bağlı)
        Catalog updatedCatalog = CatalogMapper.INSTANCE.updateCatalogRequestToCatalog(request);
        updatedCatalog.setCreatedDate(existing.getCreatedDate());
        updatedCatalog.setUpdatedDate(LocalDateTime.now());

        // 🔹 Parent güncellemesi varsa yine kontrol et
        if (updatedCatalog.getParent() != null && updatedCatalog.getParent().getId() != null) {
            Catalog parent = catalogRepository.findById(updatedCatalog.getParent().getId())
                    .orElseThrow(() -> new RuntimeException("Parent catalog not found"));
            updatedCatalog.setParent(parent);
        } else {
            updatedCatalog.setParent(null);
        }

        Catalog updated = catalogRepository.save(updatedCatalog);
        return CatalogMapper.INSTANCE.catalogToUpdatedCatalogResponse(updated);
    }

    @Override
    public List<GetListCatalogResponse> getAll() {
        List<Catalog> catalogs = catalogRepository.findAll();
        return CatalogMapper.INSTANCE.catalogListToGetListCatalogResponseList(catalogs);
    }

    @Override
    public GetCatalogResponse getById(UUID id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catalog not found"));
        return CatalogMapper.INSTANCE.catalogToGetCatalogResponse(catalog);
    }

    @Override
    public void delete(UUID id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catalog not found"));
        catalogRepository.delete(catalog);
    }

    @Override
    public void softDelete(UUID id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catalog not found"));
        catalog.setDeletedDate(LocalDateTime.now());
        catalogRepository.save(catalog);
    }
}