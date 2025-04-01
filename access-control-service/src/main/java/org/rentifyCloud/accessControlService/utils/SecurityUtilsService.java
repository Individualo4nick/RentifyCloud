package org.rentifyCloud.accessControlService.utils;

import org.rentifyCloud.accessControlService.enums.CompanyAccountRoleEnum;
import org.rentifyCloud.accessControlService.service.SSOClientService;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtilsService {
    private final SSOClientService ssoClientService;

    public SecurityUtilsService(SSOClientService ssoClientService) {
        this.ssoClientService = ssoClientService;
    }

    public static boolean isRentifyRole(String roleName) {
        return roleName.startsWith(Constants.RENTIFY_ROLE_PREFIX);
    }

    public void createCARoles(long companyAccountId) {
        var adminRoleName = formRoleNameForAccount(CompanyAccountRoleEnum.RENTIFY_ROLE_CA__ADMIN, companyAccountId);
        var storekeeperRoleName = formRoleNameForAccount(CompanyAccountRoleEnum.RENTIFY_ROLE_CA__STOREKEEPER, companyAccountId);

        ssoClientService.createRole(adminRoleName);
        ssoClientService.createRole(storekeeperRoleName);
    }

    public String formRoleNameForAccount(CompanyAccountRoleEnum companyAccountRoleEnum, long companyAccountId){
        return Constants.RENTIFY_CA_ROLE_PREFIX + companyAccountId + "__" + companyAccountRoleEnum.name().substring(Constants.RENTIFY_CA_ROLE_PREFIX.length());
    }



}
