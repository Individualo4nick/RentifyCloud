package org.rentifyCloud.accessControlService.controller;

import org.rentifyCloud.accessControlService.dto.BusinessAccountRegistrationRequestDto;
import org.rentifyCloud.accessControlService.dto.BusinessAccountRegistrationResponseDto;
import org.rentifyCloud.accessControlService.enums.BusinessAccountRoleEnum;
import org.rentifyCloud.accessControlService.service.SSOClientService;
import org.rentifyCloud.accessControlService.utils.SecurityUtilsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth/business-account")
public class BusinessAccountController {
    private final SecurityUtilsService securityUtilsService;
    private final SSOClientService ssoClientService;
    private final WebClient rentifyBusinessWebCLient = WebClient.create("http://localhost:8082");

    public BusinessAccountController(SecurityUtilsService securityUtilsService, SSOClientService ssoClientService) {
        this.securityUtilsService = securityUtilsService;
        this.ssoClientService = ssoClientService;
    }

//    @PostMapping("/register")
//    public void registerBusinessAccount(@RequestBody BusinessAccountRegistrationRequestDto businessAccountRegistrationRequestDto, @RequestHeader("oidc-user-subject") String oidcUserSubject) {
//        ResponseEntity<BusinessAccountRegistrationResponseDto> response = rentifyBusinessWebCLient.post().uri("/api/rentify/business-account/register")
//                .body(Mono.just(businessAccountRegistrationRequestDto), BusinessAccountRegistrationRequestDto.class)
//                .exchangeToMono(r -> r.toEntity(BusinessAccountRegistrationResponseDto.class))
//                .block();
//        if (response != null && response.getStatusCode().is2xxSuccessful()) {
//            var businessAccountId = Objects.requireNonNull(response.getBody()).getId();
//            securityUtilsService.createBARoles(businessAccountId);
//            var adminRole = List.of(securityUtilsService.formRoleNameForAccount(BusinessAccountRoleEnum.RENTIFY_ROLE_BA__ADMIN, businessAccountId));
//            ssoClientService.addRolesToUser(oidcUserSubject, adminRole);
//        }
//    }

    //todo delete after testing
    @PostMapping("/register")
    public void registerBusinessAccount(@RequestBody BusinessAccountRegistrationRequestDto businessAccountRegistrationRequestDto, @RequestHeader("oidc-user-subject") String oidcUserSubject) {
        var businessAccountId = 50132;
        securityUtilsService.createBARoles(businessAccountId);
        var adminRole = List.of(securityUtilsService.formRoleNameForAccount(BusinessAccountRoleEnum.RENTIFY_ROLE_BA__ADMIN, businessAccountId));
        ssoClientService.addRolesToUser(oidcUserSubject, adminRole);
    }
}
