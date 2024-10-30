package java15.dao;

import java15.entity.Customer;

import java.util.List;

public interface CustomerDao {
    void createTable();

    String add(Customer customer);

    String getCostumerById(Long id);

    String updatePhone(Long id, int phoneNumber);

    String delete(Long id);

    String sortCustomerByName();

    String searchCostumerByName(String name);
}
