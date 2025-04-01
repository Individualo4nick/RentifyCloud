package org.rentifycloud.rentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contact")
@Getter
@Setter
public class Contact extends AbstractVersionedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_account_id", referencedColumnName = "id", nullable = false)
    private CompanyAccount companyAccount;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "second_name", length = 30)
    private String secondName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "main_social_network_link", length = 15)
    private String mainSocialNetworkLink;
}
