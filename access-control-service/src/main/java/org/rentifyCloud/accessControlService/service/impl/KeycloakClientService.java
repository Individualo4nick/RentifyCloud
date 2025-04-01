package org.rentifyCloud.accessControlService.service.impl;

import jakarta.annotation.PostConstruct;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.rentifyCloud.accessControlService.dto.SSOUserDetails;
import org.rentifyCloud.accessControlService.service.SSOClientService;
import org.rentifyCloud.accessControlService.utils.SecurityUtilsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class KeycloakClientService implements SSOClientService {
    private Keycloak userRealmKeycloak;

    @Value("${keycloak.admin-api.uri}")
    private String serverUrl;
    @Value("${keycloak.admin-api.realm}")
    private String realm;
    @Value("${keycloak.admin-api.username}")
    private String username;
    @Value("${keycloak.admin-api.password}")
    private String password;
    @Value("${keycloak.admin-api.clientId}")
    private String clientId;

    @PostConstruct
    public void init() {
        userRealmKeycloak = KeycloakBuilder
                .builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .username(username)
                .password(password)
                .clientId(clientId)
                .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10).build()).build();
    }

    @Override
    @Cacheable(key = "#root.args[0]", cacheNames = "userSSO")
    public SSOUserDetails getUserDetails(String ssoSystemUserId) {
        var user = userRealmKeycloak.realm(realm).users().get(ssoSystemUserId);
        var kcud = marshalAsUserDetails(user.toRepresentation());
        kcud.setRoles(user.roles().realmLevel().listEffective().stream().map(RoleRepresentation::getName)
                .filter(SecurityUtilsService::isRentifyRole).collect(Collectors.toList()));
        return kcud;
    }

    private SSOUserDetails marshalAsUserDetails(UserRepresentation ur) {
        var kcud = new SSOUserDetails();
        kcud.setSsoSystemId(ur.getId());
        kcud.setUsername(ur.getUsername());
        kcud.setFirstName(ur.getFirstName());
        kcud.setLastName(ur.getLastName());
        kcud.setEmail(ur.getEmail());
        Map<String, List<String>> atts = ur.getAttributes();
        if (atts != null){
            //todo подумать
            //kcud.setPhoneNumber(atts.get("phoneNumber") == null ? null : atts.get("phoneNumber").get(0));
        }
        return kcud;
    }

    @Override
    public void createRole(String ssoSystemRoleName) {
        var roleRepresentation = new RoleRepresentation();
        roleRepresentation.setName(ssoSystemRoleName);
        userRealmKeycloak.realm(realm).roles().create(roleRepresentation);
    }

    @Override
    @CacheEvict(key = "#root.args[0]", cacheNames = "userSSO")
    public void addRolesToUser(String ssoSystemUserId, List<String> ssoSystemRoleNames) {
        var rsr = userRealmKeycloak.realm(realm).users().get(ssoSystemUserId).roles().realmLevel();
        var roles = ssoSystemRoleNames.stream().map(rrName -> userRealmKeycloak.realm(realm).roles().get(rrName).toRepresentation()).collect(Collectors.toList());
        rsr.add(roles);
    }

}
