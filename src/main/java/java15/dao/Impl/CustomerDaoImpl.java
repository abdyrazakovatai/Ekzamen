package java15.dao.Impl;

import java15.config.JDBSConnection;
import java15.dao.CustomerDao;
import java15.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private Connection conn = JDBSConnection.getConnection();


    @Override
    public void createTable() {
        String sql = """
                    create table customers(
                    id serial primary key not null,
                    first_name varchar,
                    last_name varchar,
                    phone_number int 
                );
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String add(Customer customer) {
        String sql = "insert into customers(first_name,last_name,phone_number) values(?,?,?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setInt(3, customer.getPhoneNumber());
            preparedStatement.executeUpdate();
            return "Success";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Not added";
    }

    @Override
    public String getCostumerById(Long id) {
        String sql = "select * from customers where id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            Customer customer = new Customer();
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            customer.setFirstName(resultSet.getString("first_name"));
            customer.setLastName(resultSet.getString("last_name"));
            customer.setPhoneNumber(resultSet.getInt("phone_number"));
            return customer.getFirstName() + " " + customer.getLastName();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "not found";
    }

    @Override
    public String updatePhone(Long id, int phoneNumber) {
        String sql = "update customers set phone_number=? where id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, phoneNumber);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return "Success";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "not updated";
    }

    @Override
    public String delete(Long id) {
        String sql = "delete from customers where id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return "Success";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "not deleted";
    }

    @Override
    public String sortCustomerByName() {
        String sql = "select * from customers order by first_name asc";
        try (Statement stmt = conn.createStatement()) {
            boolean execute = stmt.execute(sql);
            return execute ? "success" : "failed";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Not sorted by name";
    }

    @Override
    public String searchCostumerByName(String name) {
        String sql = "select * from customers where first_name like ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("last_name");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Not found";
    }
}
