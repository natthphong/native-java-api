package service;

import Httpenum.HttpContentType;
import Httpenum.HttpStatus;
import model.CustomerModel;
import model.ResponseModel;
import repository.CustomerRepository;
import utils.HttpResponse;
import utils.JsonConverter;
import utils.SystemOutUtil;

import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class CustomerService {

    private final CustomerRepository customerRepository;

    private final Socket socket;
    private final PrintStream ps;

    public CustomerService(CustomerRepository customerRepository1, Socket socket, PrintStream printStream) {
        this.customerRepository = customerRepository1;
        this.socket = socket;
        this.ps = printStream;
    }


    public void getCustomerAll(){
            List<CustomerModel> list = customerRepository.findAll();
                SystemOutUtil.printObjects(list);
            HttpResponse.response(ps, JsonConverter.toJsonString(list), HttpStatus.OK, HttpContentType.APPLICATION_JSON);
    }


    public void insertCustomer(CustomerModel body) {
        customerRepository.insertCustomer(body);
        HttpResponse.response(ps, JsonConverter.toJsonString(ResponseModel.responseModelOk()), HttpStatus.CREATED, HttpContentType.APPLICATION_JSON);
    }
}
