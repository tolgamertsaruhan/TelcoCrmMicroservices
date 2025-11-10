package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.domain.entities.ProductOffer;
import com.etiya.catalogservice.repository.ProductOfferRepository;
import com.etiya.catalogservice.service.abstracts.ProductOfferService;
import com.etiya.catalogservice.service.mappings.ProductOfferMapper;
import com.etiya.catalogservice.service.requests.productOffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.requests.productOffer.UpdateProductOfferRequest;
import com.etiya.catalogservice.service.responses.productOffer.CreatedProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.GetProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.GetListProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.UpdatedProductOfferResponse;
import com.etiya.catalogservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductOfferServiceImpl implements ProductOfferService {

    private final ProductOfferRepository productOfferRepository;
    private final ProductRepository productRepository;


    public ProductOfferServiceImpl(ProductOfferRepository productOfferRepository, ProductRepository productRepository) {
        this.productOfferRepository = productOfferRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CreatedProductOfferResponse addProductOffer(CreateProductOfferRequest request) {
        ProductOffer productOffer = ProductOfferMapper.INSTANCE.productOfferFromCreateProductOfferRequest(request);
        productOfferRepository.save(productOffer);
        return ProductOfferMapper.INSTANCE.createdProductOfferResponseFromProductOffer(productOffer);
    }

    @Override
    public UpdatedProductOfferResponse update(UpdateProductOfferRequest request) {
        ProductOffer existingProductOffer = productOfferRepository.findByIdAndDeletedDateIsNull(request.getId());
        ProductOfferMapper.INSTANCE.updateProductOfferFromRequest(request, existingProductOffer);
        existingProductOffer.setUpdatedDate(LocalDateTime.now());
        productOfferRepository.save(existingProductOffer);
        return ProductOfferMapper.INSTANCE.updatedProductOfferResponseFromProductOffer(existingProductOffer);
    }

    @Override
    public GetProductOfferResponse getProductOffer(UUID id) {
        ProductOffer productOffer = productOfferRepository.findByIdAndDeletedDateIsNull(id);
        if (productOffer != null) {
            return ProductOfferMapper.INSTANCE.getProductOfferResponseFromProductOffer(productOffer);
        }
        return null;
    }

    @Override
    public GetProductOfferResponse getProductOfferWithProductIdControl(UUID id) {
        ProductOffer productOffer = productOfferRepository.findByIdAndDeletedDateIsNull(id);
        return ProductOfferMapper.INSTANCE.getProductOfferResponseFromProductOffer(productOffer);
    }

    @Override
    public List<GetListProductOfferResponse> getAllProductOffers() {
        List<ProductOffer> productOffers = productOfferRepository.findByDeletedDateIsNull();
        return ProductOfferMapper.INSTANCE.getListProductOfferResponseFromProductOfferList(productOffers);
    }

    @Override
    public void softDelete(UUID id) {
        ProductOffer productOffer = productOfferRepository.findByIdAndDeletedDateIsNull(id);
        if (productOffer != null) {
            productOffer.setDeletedDate(LocalDateTime.now());  // Soft delete işlemi
            productOfferRepository.save(productOffer);
        }
    }

    @Override
    public void delete(UUID id) {
        productOfferRepository.deleteById(id);  // Hard delete işlemi
    }
}
