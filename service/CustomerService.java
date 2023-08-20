package service;

import Httpenum.HttpContentType;
import Httpenum.HttpStatus;
import exception.ProjectException;
import model.Credential;
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
        HttpResponse.response(ps, "HELLO WORLD", HttpStatus.OK, HttpContentType.TEXT_PLAIN);
    }

    public void deleteCustomer(Long id) {
        customerRepositoryImpl.deleteCustomer(id);
        HttpResponse.response(ps, JsonConverter.toJsonString(ResponseModel.responseModelOk()), HttpStatus.OK, HttpContentType.APPLICATION_JSON);

    }

    public void getCustomer(Long id) {

        CustomerModel customerModel = customerRepositoryImpl.findById(id);
        String json = JsonConverter.toJsonString(customerModel);
        if (json.equalsIgnoreCase("{}"))
            throw new ProjectException("Not Found", HttpStatus.NOT_FOUND);
        HttpResponse.response(ps, json, HttpStatus.OK, HttpContentType.APPLICATION_JSON);

    }

    public void login(Credential req) {
        CustomerModel customerModel = customerRepositoryImpl.findByEmail(req.getEmail());
        if (!Validate.validateNotNullNotBlank(customerModel.getUsername(), customerModel.getPassword()))
            throw new ProjectException("Not Found", HttpStatus.NOT_FOUND);
        if (!bcryptUtil.verifyPassword(req.getPassword(), customerModel.getPassword()))
            throw new ProjectException("Password is not correct", HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseModel response = ResponseModel.responseModelOk(JwtUtil.generateToken(JsonConverter.toJsonString(customerModel), 100000L));
        HttpResponse.response(ps, JsonConverter.toJsonString(response), HttpStatus.OK, HttpContentType.APPLICATION_JSON);
    }

    public void testToken(String token) {
        try {
            String body = JwtUtil.parseToken(token);
            CustomerModel credential = JsonConverter.fromJsonString(body,CustomerModel.class);
            SystemOutUtil.printObjects(credential);
            HttpResponse.response(ps, body, HttpStatus.OK, HttpContentType.APPLICATION_JSON);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ProjectException("Token Invalid", HttpStatus.FORBIDDEN);
        }
    }
}
