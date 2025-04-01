package org.rentifyCloud.accessControlService.service;

import org.rentifyCloud.accessControlService.dto.SSOUserDetails;

import java.util.List;

public interface SSOClientService {
    SSOUserDetails getUserDetails(String ssoSystemUserId);

    void createRole(String ssoSystemRoleName);

    void addRolesToUser(String ssoSystemUserId, List<String> ssoSystemRoleNames);
}
