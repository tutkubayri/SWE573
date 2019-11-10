package com.swe573.comin.model;

import java.io.Serializable;

public class ApiResponse implements Serializable {
    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
