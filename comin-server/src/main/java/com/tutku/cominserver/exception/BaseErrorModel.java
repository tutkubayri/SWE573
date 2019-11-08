package com.tutku.cominserver.exception;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BaseErrorModel implements Serializable {
    private String message;
}
