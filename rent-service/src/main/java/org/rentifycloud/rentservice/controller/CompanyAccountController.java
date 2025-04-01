package org.rentifycloud.rentservice.controller;

import jakarta.validation.Valid;
import org.rentifycloud.rentservice.dto.CompanyAccountRegistrationRequestDto;
import org.rentifycloud.rentservice.dto.CompanyAccountRegistrationResponseDto;
import org.rentifycloud.rentservice.service.CompanyAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentify/company-account")
public class CompanyAccountController {
    private final CompanyAccountService companyAccountService;

    public CompanyAccountController(CompanyAccountService companyAccountService) {
        this.companyAccountService = companyAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<CompanyAccountRegistrationResponseDto> registerCompanyAccount(@Valid @RequestBody CompanyAccountRegistrationRequestDto companyAccountRegistrationRequestDto) {
        return ResponseEntity.ok(companyAccountService.registerCompanyAccount(companyAccountRegistrationRequestDto));
    }
}
