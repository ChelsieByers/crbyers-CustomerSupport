package com.example.crbyerscustomersupport.site;

import com.example.crbyerscustomersupport.entities.UserPrincipal;

public interface AuthenticationService {
    UserPrincipal authenticate(String username, String password);
    void saveUser(UserPrincipal principal, String newPassword);
}
