package com.swe.comin.controllers.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private Boolean success;
    private String message;
}