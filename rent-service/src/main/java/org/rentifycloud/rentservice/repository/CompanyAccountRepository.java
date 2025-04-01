package org.rentifycloud.rentservice.repository;

import org.rentifycloud.rentservice.entity.CompanyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAccountRepository extends JpaRepository<CompanyAccount, Long> {

}
