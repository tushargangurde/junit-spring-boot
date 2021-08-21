package com.tushar.junit.exception;

import lombok.Data;

@Data
public class ApiError {

    private Integer errorCode;
    private String errorMessage;

}
