package model;

import java.util.Objects;

public class User {
    private long idUsers;
    private String firstName;
    private String lastName;
    private String login;
    private String pass;
    private String adress;
    private String email;
    private String roleId;

    public User(long id, String firstName, String secondName,
                String login, String password, String adress, String email, String roleId) {
        this.idUsers = id;
        this.firstName = firstName;
        this.lastName = secondName;
        this.login = login;
        this.pass = password;
        this.adress = adress;
        this.email = email;
        this.roleId = roleId;
    }

    public User(String firstName, String secondName,
                String login, String password, String adress, String email, String roleId) {
        this.firstName = firstName;
        this.lastName = secondName;
        this.login = login;
        this.pass = password;
        this.adress = adress;
        this.email = email;
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return idUsers == user.idUsers &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                login.equals(user.login) &&
                pass.equals(user.pass) &&
                adress.equals(user.adress) &&
                email.equals(user.email) &&
                roleId.equals(user.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, login, pass, adress, email, roleId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + idUsers +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + pass + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
