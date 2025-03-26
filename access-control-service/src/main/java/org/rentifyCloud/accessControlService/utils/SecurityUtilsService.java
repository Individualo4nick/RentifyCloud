package org.rentifyCloud.accessControlService.utils;

import org.rentifyCloud.accessControlService.enums.BusinessAccountRoleEnum;
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

    public void createBARoles(long businessAccountId) {
        var adminRoleName = formRoleNameForAccount(BusinessAccountRoleEnum.RENTIFY_ROLE_BA__ADMIN, businessAccountId);
        var storekeeperRoleName = formRoleNameForAccount(BusinessAccountRoleEnum.RENTIFY_ROLE_BA__STOREKEEPER, businessAccountId);

        ssoClientService.createRole(adminRoleName);
        ssoClientService.createRole(storekeeperRoleName);
    }

    public String formRoleNameForAccount(BusinessAccountRoleEnum businessAccountRoleEnum, long businessAccountId){
        return Constants.RENTIFY_BA_ROLE_PREFIX + businessAccountId + "__" + businessAccountRoleEnum.name().substring(Constants.RENTIFY_BA_ROLE_PREFIX.length());
    }



}
