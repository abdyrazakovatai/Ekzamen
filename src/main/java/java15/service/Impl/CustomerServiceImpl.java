package java15.service.Impl;

import java15.dao.CustomerDao;
import java15.dao.Impl.CustomerDaoImpl;
import java15.entity.Customer;
import java15.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void createTable() {
        customerDao.createTable();
    }

    @Override
    public String add(Customer customer) {
        return customerDao.add(customer);
    }

    @Override
    public String getCostumerById(Long id) {
        return customerDao.getCostumerById(id);
    }

    @Override
    public String updatePhone(Long id, int phoneNumber) {
        return customerDao.updatePhone(id, phoneNumber);
    }

    @Override
    public String delete(Long id) {
        return customerDao.delete(id);
    }

    @Override
    public String sortCustomerByName() {
        return customerDao.sortCustomerByName();
    }

    @Override
    public String searchCostumerByName(String name) {
        return customerDao.searchCostumerByName(name);
    }
}
