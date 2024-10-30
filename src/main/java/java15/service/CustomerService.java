package java15.service;

import java15.entity.Customer;

public interface CustomerService {
    void createTable();

    String add(Customer customer);

    String getCostumerById(Long id);

    String updatePhone(Long id, int phoneNumber);

    String delete(Long id);

    String sortCustomerByName();

    String searchCostumerByName(String name);
}
