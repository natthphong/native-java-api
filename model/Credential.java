package model;

public class Credential {
    private String email;
    private String password;

    public Credential(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public Credential() {
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
}
