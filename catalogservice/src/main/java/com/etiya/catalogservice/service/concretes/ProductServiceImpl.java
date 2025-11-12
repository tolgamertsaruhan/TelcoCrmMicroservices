package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Catalog;
import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.domain.entities.ProductSpecification;
import com.etiya.catalogservice.repository.ProductRepository;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.mappings.ProductMapper;
import com.etiya.catalogservice.service.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.requests.product.CreateProductRequestHoca;
import com.etiya.catalogservice.service.requests.product.UpdateProductRequest;
import com.etiya.catalogservice.service.responses.product.*;
import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.responses.ProductResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public CreatedProductResponse add(CreateProductRequest request) {
        Product product = ProductMapper.INSTANCE.productFromCreateProductRequest(request);
        Catalog catalog = new Catalog();
        catalog.setId(request.getCatalogId());
        product.setCatalog(catalog);

//        ProductSpecification spec = new ProductSpecification();
//        spec.setId(request.getSpecificationId());
//        product.setProductSpecification(spec);

        product = productRepository.save(product);
        return ProductMapper.INSTANCE.createdProductResponseFromProduct(product);
    }

    @Override
    public UpdatedProductResponse update(UpdateProductRequest request) {
        Product existing = productRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Product not found."));

        if (existing.getDeletedDate() != null) {
            throw new RuntimeException("Cannot update a deleted product.");
        }

        ProductMapper.INSTANCE.updateProductFromRequest(request, existing);

        existing.setUpdatedDate(LocalDateTime.now());

        productRepository.save(existing);

        return ProductMapper.INSTANCE.UpdatedProductResponseFromProduct(existing);
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public GetProductResponse getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found."));
        if (product.getDeletedDate() != null) {
            throw new RuntimeException("Product not found.");
        }
        return ProductMapper.INSTANCE.GetProductResponseProduct(product);
    }

    @Override
    public List<GetListProductResponse> getAll() {
        List<Product> products = productRepository.findAll()
                .stream()
                .filter(p -> p.getDeletedDate() == null)
                .toList();
        return ProductMapper.INSTANCE.GetListProductResponseFromProductList(products);
    }

    @Override
    public void softDelete(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDeletedDate(LocalDateTime.now());
        productRepository.save(product);
    }

//    @Override
//    public List<GetListProductResponse> getByCatalogId(UUID catalogId) {
//        List<Product> products = productRepository.findAllByCatalog_IdAndDeletedDateIsNull(catalogId);
//        return ProductMapper.INSTANCE.GetListProductResponseFromProductList(products);
//    }

//    @Override
//    public List<GetListProductResponse> getBySpecificationId(UUID specificationId) {
//        List<Product> products = productRepository.findAllByProductSpecification_IdAndDeletedDateIsNull(specificationId);
//        return ProductMapper.INSTANCE.GetListProductResponseFromProductList(products);
//    }

    private ProductResponse mapToResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        return response;
    }
}
