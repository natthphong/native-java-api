package repository;

import exception.BadRequestException;
import model.CustomerModel;

import java.util.List;

public interface CustomerRepository {
    public List<CustomerModel> findAll();

    public  void saveCustomer(CustomerModel body) throws BadRequestException;
}
