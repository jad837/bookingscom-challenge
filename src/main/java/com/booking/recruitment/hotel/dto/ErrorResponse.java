package com.booking.recruitment.hotel.dto;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String msg;

    public ErrorResponse(String msg) {
        this.msg = msg;
    }
}
