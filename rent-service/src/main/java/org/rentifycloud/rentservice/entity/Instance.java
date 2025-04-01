package org.rentifycloud.rentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "instance")
@Getter
@Setter
public class Instance extends AbstractVersionedEntity {
    @Column(name = "short_name", nullable = false, length = 60)
    private String shortName;

    @Column(name = "full_name", length = 120)
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Column(name = "inventory_number", nullable = false, length = 30)
    private String inventoryNumber;

    @Column(name = "barcode", length = 30)
    private String barcode;

    @Column(name = "serial_number", length = 30)
    private String serialNumber;

    @Column(name = "state", length = 30)
    private InstanceStateEnum state;

    public enum InstanceStateEnum {
        NOT_AVAILABLE,
        AVAILABLE
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
}
