package com.etiya.catalogservice.domain.entities;


import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "campaigns")
public class Campaign extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    // db de campaign tablosunda constraints olarak unique olması tanımlı.
    @Column(name = "campaign_code", length = 50, nullable = false,unique = true)
    private String campaignCode;

    // Data type : numeric (2,3) olduğu için
    @Column(name = "discount_rate", precision = 3, scale = 2, nullable = false)
    private BigDecimal discountRate;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "campaign")
    private List<CampaignProductOffer> campaignProductOffers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<CampaignProductOffer> getCampaignProducts() {
        return campaignProductOffers;
    }

    public void setCampaignProducts(List<CampaignProductOffer> campaignProductOffers) {
        this.campaignProductOffers = campaignProductOffers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Campaign() {
    }

    public Campaign(UUID id, String name, String campaignCode, BigDecimal discountRate, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.name = name;
        this.campaignCode = campaignCode;
        this.discountRate = discountRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Campaign(UUID id, String name, String campaignCode, BigDecimal discountRate, LocalDateTime startDate, LocalDateTime endDate, List<CampaignProductOffer> campaignProductOffers) {
        this.id = id;
        this.name = name;
        this.campaignCode = campaignCode;
        this.discountRate = discountRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campaignProductOffers = campaignProductOffers;
    }
}
