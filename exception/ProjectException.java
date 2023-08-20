package exception;

import Httpenum.HttpStatus;
import model.ErrorResponseModel;
import utils.JsonConverter;

import java.time.LocalDateTime;

public class ProjectException extends RuntimeException {
    private final String describe;

    private final String message;
    private final HttpStatus httpStatus;
    public ProjectException(String message) {
        this(message, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public ProjectException(String message,HttpStatus httpStatus) {
        this(message, message, httpStatus);
    }

    public ProjectException(String message, String describe, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.describe = describe;
        this.httpStatus = httpStatus;
    }

    public ProjectException(String message, String describe) {
        this(message, describe, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String getBodyJson(){
        ErrorResponseModel response = new ErrorResponseModel();
        response.setCode(String.valueOf(this.httpStatus.getCode()));
        response.setMessage(this.message);
        response.setDescribe(this.describe);
        response.setTimeStamp(LocalDateTime.now());
        return JsonConverter.toJsonString(response);
    }

    public String getDescribe() {
        return describe;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
