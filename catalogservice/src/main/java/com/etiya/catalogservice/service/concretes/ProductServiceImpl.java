package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.repository.ProductRepository;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.requests.product.CreateProductRequestHoca;
import com.etiya.catalogservice.service.responses.product.CreatedProductResponseHoca;
import org.springframework.stereotype.Service;

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
}
