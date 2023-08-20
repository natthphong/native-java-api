package repository;

import exception.ProjectException;
import model.CustomerModel;

import java.util.List;

public interface CustomerRepository {
    public List<CustomerModel> findAll();

    public  void saveCustomer(CustomerModel body) throws ProjectException;

    void deleteCustomer(Long id);

    CustomerModel findById(Long id);
}
