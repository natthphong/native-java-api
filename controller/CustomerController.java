package controller;

import Httpenum.HttpMethod;
import Httpenum.HttpStatus;
import exception.ProjectException;
import model.CustomerModel;
import service.CustomerService;
import utils.HttpResponse;
import utils.JsonConverter;
import utils.StringUtils;
import utils.SystemOutUtil;

import java.util.Map;
import java.util.Objects;

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    public void run(String body, Map<String, String> param, String path, String method) {
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
                }
            default:
                throw new ProjectException("Invalid Path", HttpStatus.FORBIDDEN);
        }

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
