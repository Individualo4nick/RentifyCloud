package org.rentifycloud.rentservice.marshaller;

import org.rentifycloud.rentservice.dto.CompanyAccountRegistrationRequestDto;
import org.rentifycloud.rentservice.dto.CompanyAccountRegistrationResponseDto;
import org.rentifycloud.rentservice.entity.CompanyAccount;
import org.rentifycloud.rentservice.entity.enums.CountryEnum;
import org.rentifycloud.rentservice.entity.enums.CurrencyEnum;
import org.springframework.stereotype.Component;

@Component
public class CompanyAccountMarshaller {
    public CompanyAccount marshalDtoAsEntity(CompanyAccountRegistrationRequestDto requestDto) {
        var company = new CompanyAccount();

        company.setShortName(requestDto.getShortName());
        company.setFullName(requestDto.getFullName());
        company.setType(CompanyAccount.CompanyAccountTypeEnum.TRIAL);
        company.setState(CompanyAccount.CompanyAccountStateEnum.ACTIVE);
        company.setCountry(CountryEnum.valueOf(requestDto.getCountry()));
        company.setBaseCurrency(CurrencyEnum.valueOf(requestDto.getBaseCurrency()));
        company.setDefaultTax(requestDto.getDefaultTax());
        company.setDefaultShiftLength(requestDto.getDefaultShiftLength());
        company.setZoneOffset(requestDto.getZoneOffset());

        return company;
    }

    public CompanyAccountRegistrationResponseDto marshalEntityAsDto(CompanyAccount company) {
        var responseDto = new CompanyAccountRegistrationResponseDto();

        responseDto.setId(company.getId());

        return responseDto;
    }
}
