package com.bridgelabz.employeepayrollapp.dto;

import java.util.List;

public class ResponseDTO {
    private String message;
    private Object details;

    public ResponseDTO(String message, Object details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public Object getDetails() {
        return details;
    }
}

