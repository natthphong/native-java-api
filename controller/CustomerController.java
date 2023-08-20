package controller;

import Httpenum.HttpMethod;
import Httpenum.HttpStatus;
import exception.ProjectException;
import model.Credential;
import model.CustomerModel;
import service.CustomerService;
import utils.*;

import java.util.Map;

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    public void run(String body, Map<String, String> param, String path, String method,String token) {
        switch (path) {
            case "/":
                getHelloWord();
                break;
            case "/all":
                if (method.equalsIgnoreCase(HttpMethod.GET.getValue())){
                    getCustomerAll();
                    break;
                }
                throw new ProjectException("Method Not Allow", HttpStatus.FORBIDDEN);
            case "/update":
            case "/save":
                if (method.equalsIgnoreCase(HttpMethod.POST.getValue())) {
                    if (StringUtils.isBlank(body)) throw new ProjectException("require body");
                    var req = JsonConverter.fromJsonString(body, CustomerModel.class);
                    insertCustomer(req);
                    break;
                }
                throw new ProjectException("Method Not Allow", HttpStatus.FORBIDDEN);
            case "/delete":
                if (method.equalsIgnoreCase(HttpMethod.POST.getValue())){
                    if (!param.containsKey("id")) throw new ProjectException("require param id");
                    deleteCustomer(Long.valueOf(param.get("id")));
                    break;
                }
                throw new ProjectException("Method Not Allow", HttpStatus.FORBIDDEN);
            case "/profile":
                if (method.equalsIgnoreCase(HttpMethod.GET.getValue())){
                    if (!param.containsKey("id")) throw new ProjectException("require param id");
                    getCustomer(Long.valueOf(param.get("id")));
                    break;
                }
                throw new ProjectException("Method Not Allow", HttpStatus.FORBIDDEN);
            case "/login":
                if (method.equalsIgnoreCase(HttpMethod.POST.getValue())){
                    if (StringUtils.isBlank(body)) throw new ProjectException("require body");
                    var reqLogin = JsonConverter.fromJsonString(body, Credential.class);
                    if (!Validate.validateNotNullNotBlank(reqLogin.getEmail(),reqLogin.getPassword()))
                        throw new ProjectException("Bad Request",HttpStatus.BAD_REQUEST);
                    login(reqLogin);
                    break;
                }
                throw new ProjectException("Method Not Allow", HttpStatus.FORBIDDEN);
            case "/token":
                if (method.equalsIgnoreCase(HttpMethod.GET.getValue())){
                    if (StringUtils.isBlank(token)) throw new ProjectException("require token",HttpStatus.FORBIDDEN);
                    testToken(token);
                    break;
                }
                throw new ProjectException("Method Not Allow", HttpStatus.FORBIDDEN);
            default:
                throw new ProjectException("Invalid Path", HttpStatus.FORBIDDEN);
        }

    }

    private void testToken(String token) {
        customerService.testToken(token);
    }

    private void login(Credential req) {
        customerService.login(req);
    }

    private void getCustomer(Long id) {
        customerService.getCustomer(id);
    }

    public void getHelloWord(){
        customerService.helloWorld();
    }


    public void getCustomerAll() {
        customerService.getCustomerAll();
    }
    public void insertCustomer(CustomerModel body){
        customerService.insertCustomer(body);
    }

    public void deleteCustomer(Long id){
        customerService.deleteCustomer(id);
    }

}
