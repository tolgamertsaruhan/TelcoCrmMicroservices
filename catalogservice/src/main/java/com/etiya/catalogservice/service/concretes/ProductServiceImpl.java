package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.repository.ProductRepository;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.requests.product.CreateProductRequestHoca;
import com.etiya.catalogservice.service.responses.product.CreatedProductResponseHoca;
import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.responses.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public CreatedProductResponseHoca add(CreateProductRequestHoca request){
        Product mappingProduct = new Product(request.getName(),
                request.getDescription(),
                request.getPrice()
                );
        Product createdProduct = productRepository.save(mappingProduct);
        CreatedProductResponseHoca response = new CreatedProductResponseHoca();
        response.setId(createdProduct.getId());
        return response;
    }

    @Override
    public ProductResponse getById(UUID id) {
        return productRepository.findById(id).stream().map(this::mapToResponse).findFirst().orElseThrow(()->new BusinessException("Product not found"));
    }

    private ProductResponse mapToResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        return response;
    }
}
