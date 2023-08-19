package controller;

import model.CustomerModel;
import service.CustomerService;
import utils.JsonConverter;
import utils.SystemOutUtil;

import java.util.Map;

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    public void run(String body, Map<String, String> param, String path) {
        switch (path) {
            case "/":
                break;
            case "/all":
                getCustomerAll();
                break;
            case "/save":
                insertCustomer(JsonConverter.fromJsonString(body,CustomerModel.class));
                break;
        }

    }


    public void getCustomerAll() {
        customerService.getCustomerAll();
    }
    public void insertCustomer(CustomerModel body){
        customerService.insertCustomer(body);
    }


}
