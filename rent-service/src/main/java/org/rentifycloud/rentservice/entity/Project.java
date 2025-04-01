package org.rentifycloud.rentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "project")
@Getter
@Setter
public class Project extends AbstractVersionedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_account_id", referencedColumnName = "id", nullable = false)
    private CompanyAccount companyAccount;

    @Column(name = "short_name", nullable = false, length = 60)
    private String shortName;

    @Column(name = "full_name", length = 120)
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counterparty_id", referencedColumnName = "id", nullable = false)
    private Counterparty counterparty;

    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @Column(name = "total_price_before_discount", nullable = false)
    private long totalPriceBeforeDiscount;

    @Column(name = "total_price_after_discount", nullable = false)
    private long totalPriceAfterDiscount;

    @Column(name = "total_price_after_tax", nullable = false)
    private long totalPriceAfterTax;

    @Column(name = "discount", nullable = false)
    private double discount;

    @Column(name = "tax", nullable = false)
    private double tax;

    @Column(name = "state", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private ProjectStateEnum state;

    public enum ProjectStateEnum {
        CANCELED,
        ACTIVE,
        FINISHED
    }
}
