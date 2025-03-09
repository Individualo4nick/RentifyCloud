package org.rentifyCloud.authorization.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessAccountRegistrationRequestDto {
    private String shortName;
    private String fullName;
    private String type;
}
