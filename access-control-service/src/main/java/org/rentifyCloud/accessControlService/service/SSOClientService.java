package org.rentifyCloud.accessControlService.service;

import org.rentifyCloud.accessControlService.dto.SSOSystemUserDetails;

import java.util.List;

public interface SSOClientService {
    SSOSystemUserDetails getUserDetails(String ssoSystemUserId);

    void createRole(String ssoSystemRoleName);

    void addRolesToUser(String ssoSystemUserId, List<String> ssoSystemRoleNames);
}
