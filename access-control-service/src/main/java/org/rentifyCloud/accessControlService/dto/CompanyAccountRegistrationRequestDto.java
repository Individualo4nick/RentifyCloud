package org.rentifyCloud.accessControlService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyAccountRegistrationRequestDto {
    private String shortName;

    private String fullName;

    private Double defaultTax;

    private String country;

    private String baseCurrency;

    private int zoneOffset;

    private int defaultShiftLength;
}

