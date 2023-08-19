package repository;

import java.sql.Connection;

public class CustomerRepository {

    public final Connection connection;
    public CustomerRepository(Connection connection){
        this.connection = connection;
    }
}
