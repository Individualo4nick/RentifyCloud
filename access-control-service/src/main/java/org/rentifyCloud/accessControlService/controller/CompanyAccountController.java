package org.rentifyCloud.accessControlService.controller;

import org.rentifyCloud.accessControlService.dto.CompanyAccountRegistrationRequestDto;
import org.rentifyCloud.accessControlService.dto.CompanyAccountRegistrationResponseDto;
import org.rentifyCloud.accessControlService.enums.CompanyAccountRoleEnum;
import org.rentifyCloud.accessControlService.service.SSOClientService;
import org.rentifyCloud.accessControlService.utils.SecurityUtilsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth/company-account")
public class CompanyAccountController {
    private final SecurityUtilsService securityUtilsService;
    private final SSOClientService ssoClientService;
    private final WebClient rentifyBusinessWebCLient = WebClient.create("http://localhost:8082");

    public CompanyAccountController(SecurityUtilsService securityUtilsService, SSOClientService ssoClientService) {
        this.securityUtilsService = securityUtilsService;
        this.ssoClientService = ssoClientService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCompanyAccount(@RequestBody CompanyAccountRegistrationRequestDto companyAccountRegistrationRequestDto, @RequestHeader("oidc-user-subject") String oidcUserSubject) {
        ResponseEntity<CompanyAccountRegistrationResponseDto> response = rentifyBusinessWebCLient.post().uri("/api/rentify/company-account/register")
                .body(Mono.just(companyAccountRegistrationRequestDto), CompanyAccountRegistrationRequestDto.class)
                .exchangeToMono(r -> r.toEntity(CompanyAccountRegistrationResponseDto.class))
                .block();
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            var companyAccountId = Objects.requireNonNull(response.getBody()).getId();
            securityUtilsService.createCARoles(companyAccountId);
            var adminRole = List.of(securityUtilsService.formRoleNameForAccount(CompanyAccountRoleEnum.RENTIFY_ROLE_CA__ADMIN, companyAccountId));
            ssoClientService.addRolesToUser(oidcUserSubject, adminRole);
        }
        return response;
    }
}
