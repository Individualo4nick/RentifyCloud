package org.rentifycloud.rentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product extends AbstractVersionedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_account_id", referencedColumnName = "id", nullable = false)
    private CompanyAccount companyAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @Column(name = "short_name", nullable = false, length = 60)
    private String shortName;

    @Column(name = "full_name", length = 120)
    private String fullName;

    @Column(name = "external_code", nullable = false, length = 30)
    private String externalCode;

    @Column(name = "barcode", length = 30)
    private String barcode;

    @Column(name = "state", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private ProductStateEnum state;

    public enum ProductStateEnum {
        NOT_AVAILABLE,
        AVAILABLE
    }

    @Column(name = "instance_tracking_type", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private InstanceTrackingTypeEnum instanceTrackingType;

    public enum InstanceTrackingTypeEnum {
        BULK,
        INSTANCE_TRACKED
    }

    @Column(name = "price_per_shift", nullable = false)
    private long pricePerShirt;

    @Column(name = "instance_count", nullable = false)
    private int instanceCount;
}
