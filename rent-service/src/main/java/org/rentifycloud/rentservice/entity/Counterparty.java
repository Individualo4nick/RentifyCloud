package org.rentifycloud.rentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "counterparty")
@Getter
@Setter
public class Counterparty extends AbstractVersionedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_account_id", referencedColumnName = "id", nullable = false)
    private CompanyAccount companyAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    private Contact contact;

    @Column(name = "state", nullable = false, length = 30)
    private CounterpartyStateEnum state;

    public enum CounterpartyStateEnum {
        BLOCKED,
        ACTIVE
    }

    @Column(name = "discount", nullable = false)
    private Double discount;
}
