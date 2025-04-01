package org.rentifycloud.rentservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rentifycloud.rentservice.entity.CompanyAccount;
import org.rentifycloud.rentservice.entity.enums.CountryEnum;
import org.rentifycloud.rentservice.entity.enums.CurrencyEnum;

@Getter
@Setter
public class CompanyAccountRegistrationRequestDto {
    @NotBlank(message = "Short name is required!")
    private String shortName;

    private String fullName;

    @NotNull(message = "Default tax is required!")
    private Double defaultTax;

    @NotBlank(message = "Country is required!")
    private String country;

    @NotBlank(message = "Base currency is required!")
    private String baseCurrency;

    @NotNull(message = "Zone offset is required!")
    private int zoneOffset;

    @NotNull(message = "Default shift length is required!")
    private int defaultShiftLength;
}
