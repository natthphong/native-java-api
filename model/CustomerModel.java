package model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerModel {
    private Long id;
    private Integer age;
    private String email;
    private String password;
    private String username;

    public CustomerModel(Long id, int age, String email, String password, String username) {
        this.id = id;
        this.age = age;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerModel() {
    }



    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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
