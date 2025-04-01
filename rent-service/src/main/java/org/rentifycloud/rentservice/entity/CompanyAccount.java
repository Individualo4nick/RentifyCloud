package org.rentifycloud.rentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.rentifycloud.rentservice.entity.enums.CountryEnum;
import org.rentifycloud.rentservice.entity.enums.CurrencyEnum;

@Entity
@Table(name = "company_account")
@Getter
@Setter
public class CompanyAccount extends AbstractVersionedEntity {
    @Column(name = "short_name", nullable = false, length = 60)
    private String shortName;

    @Column(name = "full_name", length = 120)
    private String fullName;

    @Column(name = "type", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private CompanyAccountTypeEnum type;

    public enum CompanyAccountTypeEnum {
        SANDBOX,
        TRIAL,
        PRODUCTION
    }

    @Column(name = "state", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private CompanyAccountStateEnum state;

    public enum CompanyAccountStateEnum {
        BLOCKED,
        ACTIVE
    }

    @Column(name = "default_tax")
    private Double defaultTax;

    @Column(name = "country", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private CountryEnum country;

    @Column(name = "base_currency", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private CurrencyEnum baseCurrency;

    @Column(name = "zone_offset", nullable = false)
    private int zoneOffset; // in minutes

    @Column(name = "default_shift_length", nullable = false)
    private int defaultShiftLength; // in minutes
}
