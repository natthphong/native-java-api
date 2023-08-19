package model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerModel {
    private int id;
    private int age;
    private String email;
    private String password;
    private String username;
    private LocalDate date  = LocalDate.now();
    private LocalDateTime timeStamp = LocalDateTime.now();
    public List<String> test = List.of("test" , "test2");


    public CustomerModel(int id, int age, String email, String password, String username) {
        this.id = id;
        this.age = age;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public CustomerModel() {
        // Default constructor
    }

    // Getters and setters for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
