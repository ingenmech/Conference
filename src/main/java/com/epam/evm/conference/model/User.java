package com.epam.evm.conference.model;

public class User extends DatabaseEntity {

    private final String role;
    private final String login;
    private final String password;


    public User(Long id, String role, String login, String password) {
        super(id);
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;

        if (role != null ? !role.equals(user.role) : user.role != null) {
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }
}
