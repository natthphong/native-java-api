package repository.Impl;

import exception.BadRequestException;
import model.CustomerModel;
import repository.CustomerRepository;
import utils.*;

import java.sql.*;
import java.util.*;

public class CustomerRepositoryImpl implements CustomerRepository {

    public final Connection connection;
    private final BcryptUtil bcrypt;

    public CustomerRepositoryImpl(Connection connection, BcryptUtil bcryptUtil) {
        this.connection = connection;
        this.bcrypt = bcryptUtil;
    }

    @Override
    public List<CustomerModel> findAll() {
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
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public void saveCustomer(CustomerModel body) throws BadRequestException {

        List<Object> param = new ArrayList<>();
        String query = """             
                INSERT INTO db.customer_info
                (age, email, password, username)
                VALUES( ? , ? , ? , ? ) 
                      """;

        if (!Objects.isNull(body.getId()) && body.getId() > 0) {
            StringJoiner update = new StringJoiner(",");
            query = " UPDATE db.customer_info SET ";
            if (!NumberUtils.isEmpty(body.getAge())) {
                update.add(" age = ? ");
                param.add(body.getAge());
            }
            if (!StringUtils.isEmpty(body.getEmail())) {
                update.add(" email = ? ");
                param.add(body.getEmail());
            }
            if (!StringUtils.isEmpty(body.getPassword())) {
                update.add(" password = ? ");
                param.add(bcrypt.hashPassword(body.getPassword()));
            }
            if (!StringUtils.isEmpty(body.getUsername())) {
                update.add(" username = ? ");
                param.add(body.getUsername());
            }
            query += update;
            query += " WHERE id =? ";
            if (param.isEmpty()) {
                throw new RuntimeException("at least one field needs to be updated");
            }
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int col = 1;
            if (!Objects.isNull(body.getId()) && body.getId() > 0) {
                for (var e : param) preparedStatement.setObject(col++, e);
                preparedStatement.setLong(col, body.getId());
            } else {
                if (!Validate.validateNotNullNotBlank(body.getAge(),body.getEmail(),body.getUsername(),body.getPassword())){
                    throw new BadRequestException("");
                }
                preparedStatement.setInt(col++, body.getAge());
                preparedStatement.setString(col++, body.getEmail());
                preparedStatement.setString(col++, bcrypt.hashPassword(body.getPassword()));
                preparedStatement.setString(col, body.getUsername());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (BadRequestException e) {
            throw e;
        }
    }
}
