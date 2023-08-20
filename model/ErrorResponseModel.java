package model;

import java.time.LocalDateTime;

public class ErrorResponseModel {
    private String code;
    private String message;
    private String describe;

    private LocalDateTime timeStamp;

    public ErrorResponseModel(String code, String message, String describe, LocalDateTime timeStamp) {
        this.code = code;
        this.message = message;
        this.describe = describe;
        this.timeStamp = timeStamp;
    }
    public ErrorResponseModel(){}

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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
