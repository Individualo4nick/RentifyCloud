package org.rentifyCloud.authorization.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SSOSystemUserDetails {
    private String ssoSystemId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    //private String phoneNumber;
    private List<String> roles;
    public void addRole(String roleName) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(roleName);
    }

}
