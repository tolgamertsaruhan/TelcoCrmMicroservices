package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.ProductSpecCharacteristic;
import com.etiya.catalogservice.repository.ProductSpecCharacteristicRepository;
import com.etiya.catalogservice.service.abstracts.ProductSpecCharacteristicService;
import com.etiya.catalogservice.service.mappings.ProductSpecCharacteristicMapper;
import com.etiya.catalogservice.service.requests.productSpecCharacteristic.CreateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.requests.productSpecCharacteristic.UpdateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.responses.productSpecCharacteristic.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductSpecCharacteristicServiceImpl implements ProductSpecCharacteristicService {

    private final ProductSpecCharacteristicRepository repository;

    public ProductSpecCharacteristicServiceImpl(ProductSpecCharacteristicRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatedProductSpecCharacteristicResponse add(CreateProductSpecCharacteristicRequest request) {
        // Soft delete olmuş aynı spec_id, characteristic_id kombinasyonunun olup olmadığını kontrol et
        ProductSpecCharacteristic existing = repository.findAll()
                .stream()
                .filter(e -> e.getProductSpecification().getId().equals(request.getProductSpecificationId()) &&
                        e.getCharacteristic().getId().equals(request.getCharacteristicId()) &&
                        e.getDeletedDate() != null) // soft delete yapılmış olanı al
                .findFirst()
                .orElse(null);

        if (existing != null) {
            // Eğer soft delete yapılmışsa, mevcut kaydı aktif hale getir
            existing.setDeletedDate(null);  // Soft delete'i temizle
            existing.setRequired(request.isRequiredIs());  // requiredIs'i güncelle
            repository.save(existing);
            return ProductSpecCharacteristicMapper.INSTANCE
                    .createdProductSpecCharacteristicResponseFromProductSpecCharacteristic(existing);
        }

        ProductSpecCharacteristic entity = ProductSpecCharacteristicMapper.INSTANCE
                .productSpecCharacteristicFromCreateProductSpecCharacteristicRequest(request);
        entity.setCreatedDate(LocalDateTime.now());
        repository.save(entity);

        return ProductSpecCharacteristicMapper.INSTANCE
                .createdResponseFromEntity(entity);
    }

    @Override
    public UpdatedProductSpecCharacteristicResponse update(UpdateProductSpecCharacteristicRequest request) {
        ProductSpecCharacteristic existing = repository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("ProductSpecCharacteristic not found"));

        // MapStruct kullanarak alanları güncelle
        ProductSpecCharacteristicMapper.INSTANCE
                .updateProductSpecCharacteristicFromRequest(request, existing);

        existing.setUpdatedDate(LocalDateTime.now());
        repository.save(existing);

        return ProductSpecCharacteristicMapper.INSTANCE
                .updatedResponseFromEntity(existing);
    }

    @Override
    public void softDelete(UUID id) {
        ProductSpecCharacteristic existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductSpecCharacteristic not found"));
        existing.setDeletedDate(LocalDateTime.now());
        repository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<GetListProductSpecCharacteristicResponse> getAll() {
        List<ProductSpecCharacteristic> list = repository.findAll()
                .stream()
                .filter(x -> x.getDeletedDate() == null)
                .toList();

        return ProductSpecCharacteristicMapper.INSTANCE
                .getListResponseFromEntityList(list);
    }

    @Override
    public GetProductSpecCharacteristicResponse getById(UUID id) {
        ProductSpecCharacteristic entity = repository.findById(id)
                .filter(x -> x.getDeletedDate() == null)
                .orElseThrow(() -> new RuntimeException("ProductSpecCharacteristic not found or deleted"));

        return ProductSpecCharacteristicMapper.INSTANCE
                .getResponseFromEntity(entity);
    }

    @Override
    public List<GetListProductSpecCharacteristicResponse> getByProductSpecificationId(UUID specId) {
        var list = repository.findByProductSpecification_Id(specId)
                .stream()
                .filter(x -> x.getDeletedDate() == null)
                .toList();

        return ProductSpecCharacteristicMapper.INSTANCE.getListResponseFromEntityList(list);
    }

    @Override
    public List<GetListProductSpecCharacteristicResponse> getByCharacteristicId(UUID charId) {
        var list = repository.findByCharacteristic_Id(charId)
                .stream()
                .filter(x -> x.getDeletedDate() == null)
                .toList();

        return ProductSpecCharacteristicMapper.INSTANCE.getListResponseFromEntityList(list);
    }

}
