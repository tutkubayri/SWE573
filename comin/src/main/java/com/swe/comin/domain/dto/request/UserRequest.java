package com.swe.comin.domain.dto.request;

import com.swe.comin.domain.model.Role;
import lombok.Getter;

@Getter
public class UserRequest {
    private String username;
    private String email;
    private Role.RoleName role;
}
