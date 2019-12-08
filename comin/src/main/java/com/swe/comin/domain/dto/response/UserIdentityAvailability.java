package com.swe.comin.domain.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentityAvailability {
    private Boolean available;
}
