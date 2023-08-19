package model;

public class ResponseModel <T>{

    private String status;
    private String message;


    public ResponseModel(){}
    public ResponseModel(String status, String message, T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    private T body;


    public static ResponseModel<Void> responseModelOk(){
        ResponseModel<Void> response = new ResponseModel<>();
        response.message = "ok";
        response.status = "ok";
        return response;
    }
    public static <T> ResponseModel<T> responseModelOk(T body){
        ResponseModel<T> response = new ResponseModel<>();
        response.setMessage("ok");
        response.setStatus("ok");
        response.setBody(body);
        return response;
    }
}
