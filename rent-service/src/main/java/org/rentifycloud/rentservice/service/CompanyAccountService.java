package org.rentifycloud.rentservice.service;

import org.rentifycloud.rentservice.dto.CompanyAccountRegistrationRequestDto;
import org.rentifycloud.rentservice.dto.CompanyAccountRegistrationResponseDto;
import org.rentifycloud.rentservice.marshaller.CompanyAccountMarshaller;
import org.rentifycloud.rentservice.repository.CompanyAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyAccountService {
    private final CompanyAccountRepository companyAccountRepository;
    private final CompanyAccountMarshaller companyAccountMarshaller;

    public CompanyAccountService(CompanyAccountRepository companyAccountRepository, CompanyAccountMarshaller companyAccountMarshaller) {
        this.companyAccountRepository = companyAccountRepository;
        this.companyAccountMarshaller = companyAccountMarshaller;
    }

    public CompanyAccountRegistrationResponseDto registerCompanyAccount(CompanyAccountRegistrationRequestDto requestDto) {
        var company = companyAccountMarshaller.marshalDtoAsEntity(requestDto);

        companyAccountRepository.save(company);

        return companyAccountMarshaller.marshalEntityAsDto(company);
    }
}
