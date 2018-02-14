package com.zmh.projectoa.dto;

/**
 * Created by ChengShanyunduo
 * 2017/12/29
 */
public class ReturnDto {
    private String code;
    private String message;
    private Object value;
    private String status;
    private String errorMessage;

    public ReturnDto() {

    }

    public ReturnDto(String code, String message, Object value) {
        this.code = code;
        this.message = message;
        this.value = value;
    }

    public ReturnDto(String status) {
        this.status = status;
    }

    public ReturnDto(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static ReturnDto buildSuccessReturnDto() {
        return new ReturnDto("OK");
    }

    public static ReturnDto buildFailReturnDto(String errorMessage) {
        return new ReturnDto("ERROR", errorMessage);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static ReturnDto buildSuccessReturnDto(Object value) {

        return new ReturnDto("000", "Success", value);
    }

    public static ReturnDto buildFailedReturnDto(String failMessage) {
        return new ReturnDto("101", failMessage, null);
    }

    public static ReturnDto buildSystemErrorReturnDto() {
        return new ReturnDto("599", "System Error", null);
    }

    @Override
    public String toString() {
        return "ReturnDto{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
