package com.swe.comin.domain.dto.request;

import lombok.Getter;

@Getter
public class PasswordChangeRequest {
    private String email;
    private String password;
}
