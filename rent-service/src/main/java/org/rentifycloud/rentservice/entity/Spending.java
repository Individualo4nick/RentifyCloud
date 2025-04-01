package org.rentifycloud.rentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "spending")
@Getter
@Setter
public class Spending extends AbstractVersionedEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private Project project;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "external_price", nullable = false)
    private long externalPrice;

    @Column(name = "internal_price", nullable = false)
    private long internalPrice;

    @Column(name = "state", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private SpendingStateEnum state;

    public enum SpendingStateEnum {
        CANCELED,
        ACTIVE
    }
}
