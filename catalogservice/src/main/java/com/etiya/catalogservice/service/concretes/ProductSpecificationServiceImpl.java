package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.ProductSpecification;
import com.etiya.catalogservice.repository.ProductSpecificationRepository;
import com.etiya.catalogservice.service.abstracts.ProductSpecificationService;
import com.etiya.catalogservice.service.mappings.ProductSpecificationMapper;
import com.etiya.catalogservice.service.requests.productSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.requests.productSpecification.UpdateProductSpecificationRequest;
import com.etiya.catalogservice.service.responses.productSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.GetListProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.GetProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.UpdatedProductSpecificationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

    private final ProductSpecificationRepository productSpecificationRepository;

    public ProductSpecificationServiceImpl(ProductSpecificationRepository productSpecificationRepository) {
        this.productSpecificationRepository = productSpecificationRepository;
    }

    @Override
    public CreatedProductSpecificationResponse add(CreateProductSpecificationRequest request) {
        ProductSpecification productSpecification = ProductSpecificationMapper.INSTANCE
                .productSpecificationFromCreateProductSpecificationRequest(request);
        productSpecification = productSpecificationRepository.save(productSpecification);
        return ProductSpecificationMapper.INSTANCE
                .createdProductSpecificationResponseFromProductSpecification(productSpecification);
    }

    @Override
    public UpdatedProductSpecificationResponse update(UpdateProductSpecificationRequest request) {
        ProductSpecification existing = productSpecificationRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("ProductSpecification not found."));

        if (existing.getDeletedDate() != null) {
            throw new RuntimeException("Cannot update a deleted ProductSpecification.");
        }

        ProductSpecificationMapper.INSTANCE.updateProductSpecificationFromRequest(request, existing);
        existing.setUpdatedDate(LocalDateTime.now());

        productSpecificationRepository.save(existing);

        return ProductSpecificationMapper.INSTANCE
                .UpdatedProductSpecificationResponseFromProductSpecification(existing);
    }

    @Override
    public void delete(UUID id) {
        productSpecificationRepository.deleteById(id);
    }

    @Override
    public void softDelete(UUID id) {
        ProductSpecification productSpecification = productSpecificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductSpecification not found"));
        productSpecification.setDeletedDate(LocalDateTime.now());
        productSpecificationRepository.save(productSpecification);
    }

    @Override
    public GetProductSpecificationResponse getById(UUID id) {
        ProductSpecification productSpecification = productSpecificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductSpecification not found."));
        if (productSpecification.getDeletedDate() != null) {
            throw new RuntimeException("ProductSpecification not found.");
        }
        return ProductSpecificationMapper.INSTANCE
                .GetProductSpecificationResponseProductSpecification(productSpecification);
    }

    @Override
    public List<GetListProductSpecificationResponse> getAll() {
        List<ProductSpecification> specs = productSpecificationRepository.findAll()
                .stream()
                .filter(ps -> ps.getDeletedDate() == null)
                .toList();
        return ProductSpecificationMapper.INSTANCE
                .GetListProductSpecificationResponseFromProductSpecificationList(specs);
    }
}
