package model;

import java.util.List;

public class TestHardModel {
   public List<CustomerModel> customerModelList;
    public String name;

    public TestHardModel() {
    }

    public TestHardModel(List<CustomerModel> customerModelList, String name) {
        this.customerModelList = customerModelList;
        this.name = name;
    }

    public List<CustomerModel> getCustomerModelList() {
        return customerModelList;
    }

    public void setCustomerModelList(List<CustomerModel> customerModelList) {
        this.customerModelList = customerModelList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
