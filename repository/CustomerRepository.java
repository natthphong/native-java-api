package repository;

import model.CustomerModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerRepository {

    public final Connection connection;
    public CustomerRepository(Connection connection){
        this.connection = connection;
    }

    public  List<CustomerModel> findAll() {
        List<CustomerModel> customers = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = " SELECT * FROM customer_info ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CustomerModel customer = new CustomerModel();
                customer.setId(resultSet.getLong("id"));
                customer.setAge(resultSet.getInt("age"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customer.setUsername(resultSet.getString("username"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
            return customers;
    }

    public  void insertCustomer(CustomerModel body) {

        String query = """             
                INSERT INTO db.customer_info
                (age, email, password, username)
                VALUES( ? , ? , ? , ? ) 
                      """;
        if (!Objects.isNull(body.getId()) && body.getId()> 0){
            query = """
                    UPDATE db.customer_info
                    SET age=?, email=?, password=?, username=?
                    WHERE id=?;       
                    """;
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int col = 1 ;
            preparedStatement.setInt(col++, body.getAge());
            preparedStatement.setString(col++, body.getEmail());
            preparedStatement.setString(col++, body.getPassword());
            preparedStatement.setString(col++, body.getUsername());
            if (!Objects.isNull(body.getId()) && body.getId()> 0){
                preparedStatement.setString(col, body.getUsername());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }
}
