package service;

import Httpenum.HttpContentType;
import Httpenum.HttpMethod;
import Httpenum.HttpStatus;
import exception.BadRequestException;
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
        SystemOutUtil.printObjects(list);
        HttpResponse.response(ps, JsonConverter.toJsonString(list), HttpStatus.OK, HttpContentType.APPLICATION_JSON);
    }


    public void insertCustomer(CustomerModel body) {
        try {
            customerRepositoryImpl.saveCustomer(body);
            HttpResponse.response(ps, JsonConverter.toJsonString(ResponseModel.responseModelOk()), HttpStatus.CREATED, HttpContentType.APPLICATION_JSON);
        }catch (BadRequestException ex){
            HttpResponse.response(ps,HttpUtil.errorJsonMessage("Bad Request"),HttpStatus.BAD_REQUEST,HttpContentType.APPLICATION_JSON);
        }

    }

    public void helloWorld() {
        HttpResponse.response(ps,"HELLO WORLD", HttpStatus.OK, HttpContentType.TEXT_PLAIN);
    }
}
