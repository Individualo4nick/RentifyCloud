package org.rentifycloud.rentservice.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "image")
public class Image extends AbstractVersionedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_account_id", referencedColumnName = "id", nullable = false)
    private CompanyAccount companyAccount;

    @Column(name = "file_name", nullable = false, length = 1000)
    private String fileName;

    @Column(name = "external_id", length = 32, nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID uuid;

}
