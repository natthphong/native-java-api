package service;

import Httpenum.HttpContentType;
import Httpenum.HttpStatus;
import exception.ProjectException;
import model.CustomerModel;
import model.ResponseModel;
import repository.CustomerRepository;
import utils.*;

import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class CustomerService {

    private final CustomerRepository customerRepositoryImpl;

    private final Socket socket;
    private final PrintStream ps;
    private final BcryptUtil bcryptUtil;

    public CustomerService(CustomerRepository customerRepositoryImpl1, Socket socket, PrintStream printStream, BcryptUtil bcryptUtil) {
        this.customerRepositoryImpl = customerRepositoryImpl1;
        this.socket = socket;
        this.ps = printStream;
        this.bcryptUtil = bcryptUtil;
    }


    public void getCustomerAll() {
        List<CustomerModel> list = customerRepositoryImpl.findAll();
        HttpResponse.response(ps, JsonConverter.toJsonString(list), HttpStatus.OK, HttpContentType.APPLICATION_JSON);
    }


    public void insertCustomer(CustomerModel body) {
            customerRepositoryImpl.saveCustomer(body);
            HttpResponse.response(ps, JsonConverter.toJsonString(ResponseModel.responseModelOk()), HttpStatus.CREATED, HttpContentType.APPLICATION_JSON);

    }

    public void helloWorld() {
        HttpResponse.response(ps,"HELLO WORLD", HttpStatus.OK, HttpContentType.TEXT_PLAIN);
    }

    public void deleteCustomer(Long id) {
        customerRepositoryImpl.deleteCustomer(id);
        HttpResponse.response(ps, JsonConverter.toJsonString(ResponseModel.responseModelOk()), HttpStatus.OK, HttpContentType.APPLICATION_JSON);

    }

    public void getCustomer(Long id) {

        CustomerModel customerModel = customerRepositoryImpl.findById(id);
        String json = JsonConverter.toJsonString(customerModel);
        if (json.equalsIgnoreCase("{}"))
            throw new ProjectException("Id Not Found",HttpStatus.NOT_FOUND);
        HttpResponse.response(ps, json, HttpStatus.OK, HttpContentType.APPLICATION_JSON);

    }
}
