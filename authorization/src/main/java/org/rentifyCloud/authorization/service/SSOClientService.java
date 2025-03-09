package org.rentifyCloud.authorization.service;

import org.rentifyCloud.authorization.dto.SSOSystemUserDetails;

import java.util.List;

public interface SSOClientService {
    SSOSystemUserDetails getUserDetails(String ssoSystemUserId);

    void createRole(String ssoSystemRoleName);

    void addRolesToUser(String ssoSystemUserId, List<String> ssoSystemRoleNames);
}
