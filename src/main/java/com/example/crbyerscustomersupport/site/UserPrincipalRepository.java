package com.example.crbyerscustomersupport.site;

import com.example.crbyerscustomersupport.entities.UserPrincipal;

public interface UserPrincipalRepository extends GenericRespository<Long, UserPrincipal> {

    UserPrincipal getByUsername(String username);
}
