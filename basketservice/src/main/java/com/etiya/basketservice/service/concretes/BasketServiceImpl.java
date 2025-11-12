package com.etiya.basketservice.service.concretes;

import com.etiya.basketservice.client.CatalogServiceClient;
import com.etiya.basketservice.client.CustomerServiceClient;
import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.domain.BasketItem;
import com.etiya.basketservice.repository.BasketRepository;
import com.etiya.basketservice.service.abstracts.BasketService;
import com.etiya.common.responses.CampaignProductOfferResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final CustomerServiceClient customerServiceClient;
    private final CatalogServiceClient catalogServiceClient;

    public BasketServiceImpl(BasketRepository basketRepository, CustomerServiceClient customerServiceClient, CatalogServiceClient catalogServiceClient) {
        this.basketRepository = basketRepository;
        this.customerServiceClient = customerServiceClient;
        this.catalogServiceClient = catalogServiceClient;
    }

//    @Override
//    public void add(UUID billingAccountId, UUID productId, UUID productOfferId) {
//
//        var billingAccount = customerServiceClient.getBillingAccountById(billingAccountId);
//        var product = catalogServiceClient.getProductId(productId);
//        var basket = basketRepository.getBasketByBillingAccountId(billingAccount.getId());
//        var productCampaign = catalogServiceClient.getCampaignProductId(product.getId());
//        var productOffer = catalogServiceClient.getProductOffer(productOfferId,product.getId());
//
//        if (basket == null) {
//            basket = new Basket();
//            basket.setBillingAccountId(billingAccount.getId());
//            basket.setTotalPrice(BigDecimal.ZERO);
//        }
//
//        // Ürün sepette var mı kontrol et
//        BasketItem existingItem = basket.getBasketItems().stream()
//                .filter(item -> item.getProductId().equals(product.getId()))
//                .findFirst()
//                .orElse(null);
//
//        if (existingItem != null) {
//            // Ürün zaten sepetteyse quantity artır
//            existingItem.setQuantity(existingItem.getQuantity() + 1);
//            BigDecimal discountRate = existingItem.getDiscountRate() != null ? existingItem.getDiscountRate() : BigDecimal.ZERO;
//            BigDecimal discountedPrice = existingItem.getPrice()
//                    .multiply(BigDecimal.ONE.subtract(discountRate))
//                    .multiply(BigDecimal.valueOf(existingItem.getQuantity()));
//            existingItem.setDiscountedPrice(discountedPrice);
//        } else {
//            // Yeni ürün ekleniyorsa
//            BasketItem basketItem = new BasketItem();
//            basketItem.setProductId(product.getId());
//            basketItem.setProductName(product.getName());
//            basketItem.setPrice(product.getPrice());
//            basketItem.setProductOfferId(productOffer.getId());
//            basketItem.setCampaignProductId(productCampaign.getId());
//            basketItem.setDiscountRate(productOffer.getDiscountRate() != null ? productOffer.getDiscountRate() : BigDecimal.ZERO);
//            basketItem.setQuantity(1);
//            basketItem.setDiscountedPrice(
//                    product.getPrice().multiply(BigDecimal.ONE.subtract(basketItem.getDiscountRate()))
//            );
//            /*if(basket.getTotalPrice()==null){
//                basket.setTotalPrice(basketItem.getPrice());
//            }
//            else {
//                basket.setTotalPrice(basket.getTotalPrice().add(basketItem.getPrice()));
//            }*/
//
//            basket.getBasketItems().add(basketItem);
//        }
//
//        BigDecimal total = basket.getBasketItems().stream()
//                .map(item -> item.getDiscountedPrice() != null ? item.getDiscountedPrice() : item.getPrice())
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        basket.setTotalPrice(total);
//
//        basketRepository.add(basket);
//
//        /*
//        if(basket==null){
//            basket = new Basket();
//            basket.setBillingAccountId(billingAccount.getId());
//        }
//
//        BasketItem basketItem = new BasketItem();
//        basketItem.setProductId(product.getId());
//        basketItem.setProductName(product.getName());
//        basketItem.setPrice(product.getPrice());
//        basket.setBillingAccountId(billingAccount.getId());
//        if(basket.getTotalPrice()==null){
//            basket.setTotalPrice(basketItem.getPrice());
//        }
//        else {
//            basket.setTotalPrice(basket.getTotalPrice().add(basketItem.getPrice()));
//        }
//        if(basket.getBasketItems()==null){}
//        basket.getBasketItems().add(basketItem);*/
//
//        basketRepository.add(basket);
//    }

    public void add(UUID billingAccountId, UUID productOfferId, UUID campaignId) {
        // 1️⃣ BillingAccount bilgisi al
        var billingAccount = customerServiceClient.getBillingAccountById(billingAccountId);



        // 3️⃣ Kampanya varsa kampanya bilgisi al
        BigDecimal discountRate = BigDecimal.ZERO;

        if (campaignId != null) {
            var campaign = catalogServiceClient.getCampaignId(campaignId);
            discountRate = campaign.getDiscountRate();

        }


        // 4️⃣ Sepeti al veya oluştur
        var basket = basketRepository.getBasketByBillingAccountId(billingAccount.getId());
        if (basket == null) {
            basket = new Basket();
            basket.setBillingAccountId(billingAccount.getId());
            basket.setTotalPrice(BigDecimal.ZERO);
        }

        if(campaignId!=null){
            List<CampaignProductOfferResponse> campaignOffers =
                    catalogServiceClient.getAllCampaignProductOffersByCampaignId(campaignId);

            for (var campaignOffer : campaignOffers) {
                var productOfferItem = catalogServiceClient.getProductOffer(campaignOffer.getProductOfferId());

                // 5️⃣ Sepette aynı ürün varsa miktarı artır
                BasketItem existingItem = basket.getBasketItems().stream()
                        .filter(item -> item.getProductOfferId().equals(campaignOffer.getProductOfferId())
                                && Objects.equals(item.getCampaignProductOfferId(), campaignOffer.getId()))
                        .findFirst()
                        .orElse(null);

                if (existingItem != null) {
                    existingItem.setQuantity(existingItem.getQuantity() + 1);
                } else {
                    BasketItem basketItem = new BasketItem();
                    basketItem.setProductOfferId(productOfferItem.getId());
                    basketItem.setProductName(productOfferItem.getName());
                    basketItem.setPrice(productOfferItem.getPrice());
                    basketItem.setDiscountRate(discountRate);
                    basketItem.setQuantity(1);
                    basketItem.setCampaignProductOfferId(campaignOffer.getId());
                    basketItem.setCampaignId(campaignId);


                    BigDecimal discountedPrice = productOfferItem.getPrice()
                            .multiply(BigDecimal.ONE.subtract(discountRate));
                    basketItem.setDiscountedPrice(discountedPrice);

                    basket.getBasketItems().add(basketItem);
                }
            }
        } else {
            // 5️⃣ Sepette aynı ürün varsa miktarı artır
            BasketItem existingItem = basket.getBasketItems().stream()
                    .filter(item -> item.getProductOfferId().equals(productOfferId)
                            && Objects.equals(item.getCampaignProductOfferId(), null))
                    .findFirst()
                    .orElse(null);

            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + 1);
            } else {

                var productOffer = catalogServiceClient.getProductOffer(productOfferId);
                BasketItem basketItem = new BasketItem();
                basketItem.setProductOfferId(productOffer.getId());
                basketItem.setProductName(productOffer.getName());
                basketItem.setPrice(productOffer.getPrice());
                basketItem.setDiscountRate(discountRate);
                basketItem.setQuantity(1);
                basketItem.setCampaignProductOfferId(null);
                basketItem.setCampaignId(null);
                basketItem.setProductOfferId(productOffer.getId());

                BigDecimal discountedPrice = productOffer.getPrice()
                        .multiply(BigDecimal.ONE.subtract(discountRate));
                basketItem.setDiscountedPrice(discountedPrice);

                basket.getBasketItems().add(basketItem);
            }
        }


        // 6️⃣ Toplam fiyatı güncelle
        BigDecimal total = basket.getBasketItems().stream()
                .map(item -> item.getDiscountedPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        basket.setTotalPrice(total);

        // 7️⃣ Sepeti kaydet
        basketRepository.add(basket);
    }


    @Override
    public Map<String, Basket> getAll() {
        return basketRepository.getBaskets();
    }

    @Override
    public void clearBasket(UUID billingAccountId) {
        basketRepository.clearBasket(billingAccountId);
    }

    @Override
    public void clearBasketItems(UUID billingAccountId) {
        basketRepository.clearBasketItems(billingAccountId);
    }

    @Override
    public Basket getByBillingAccountId(UUID billingAccountId) {
        return basketRepository.getBasketByBillingAccountId(billingAccountId);
    }

    public void removeItem(UUID billingAccountId, UUID productOfferId, UUID campaignId) {
        // 1️⃣ Sepeti al
        var basket = basketRepository.getBasketByBillingAccountId(billingAccountId);
        if (basket == null || basket.getBasketItems().isEmpty()) {
            return; // Sepet yoksa veya boşsa çık
        }

        if (campaignId != null) {
            List<CampaignProductOfferResponse> campaignOffers =
                    catalogServiceClient.getAllCampaignProductOffersByCampaignId(campaignId);

            for (var campaignOffer : campaignOffers) {
                var productOfferItem = catalogServiceClient.getProductOffer(campaignOffer.getProductOfferId());

                // 2️⃣ Sepette ilgili item'i bul
                BasketItem existingItem = basket.getBasketItems().stream()
                        .filter(item -> item.getProductOfferId().equals(productOfferItem.getId())
                                && Objects.equals(item.getCampaignId(), campaignId))
                        .findFirst()
                        .orElse(null);

                if (existingItem == null) {
                    return; // Sepette böyle bir ürün yok
                }

                // 3️⃣ Quantity kontrolü
                if (existingItem.getQuantity() > 1) {
                    existingItem.setQuantity(existingItem.getQuantity() - 1);
                } else {
                    // Quantity 1 ise item'i sepetten çıkar
                    basket.getBasketItems().remove(existingItem);
                }
            }

        } else {
            // 2️⃣ Sepette ilgili item'i bul
            BasketItem existingItem = basket.getBasketItems().stream()
                    .filter(item -> item.getProductOfferId().equals(productOfferId)
                            && Objects.equals(item.getCampaignId(), campaignId))
                    .findFirst()
                    .orElse(null);

            if (existingItem == null) {
                return; // Sepette böyle bir ürün yok
            }

            // 3️⃣ Quantity kontrolü
            if (existingItem.getQuantity() > 1) {
                existingItem.setQuantity(existingItem.getQuantity() - 1);
            } else {
                // Quantity 1 ise item'i sepetten çıkar
                basket.getBasketItems().remove(existingItem);
            }
        }

            // 4️⃣ Sepet toplam fiyatını güncelle
            BigDecimal total = basket.getBasketItems().stream()
                    .map(item -> item.getDiscountedPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            basket.setTotalPrice(total);

            // 5️⃣ Sepeti kaydet
            basketRepository.add(basket);

    }
}