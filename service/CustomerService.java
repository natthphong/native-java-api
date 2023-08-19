package service;

import repository.CustomerRepository;

import java.net.Socket;

public class CustomerService {

    private final CustomerRepository customerRepository;

    private final Socket socket;

    public CustomerService(CustomerRepository customerRepository1, Socket socket) {
        this.customerRepository = customerRepository1;
        this.socket = socket;
    }
}
