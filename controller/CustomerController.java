package controller;

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


    public void run(String body, Map<String, String> param, String path) {
        switch (path) {
            case "/":
                getHelloWord();
                break;
            case "/all":
                getCustomerAll();
                break;
            case "/update":
            case "/save":
                if (StringUtils.isBlank(body))throw new RuntimeException("require body");
                var req = JsonConverter.fromJsonString(body,CustomerModel.class);
                insertCustomer(req);
                break;



        }

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


}
