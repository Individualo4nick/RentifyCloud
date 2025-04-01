package org.rentifyCloud.accessControlService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyAccountRegistrationRequestDto {
    private String shortName;
    private String fullName;
    private String type;
}
