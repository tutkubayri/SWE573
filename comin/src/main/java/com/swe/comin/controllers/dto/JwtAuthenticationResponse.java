package com.swe.comin.controllers.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private final static String tokenType = "Bearer";
}