package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.AdminLogic;

import java.io.IOException;
import java.util.Objects;

public class Admin {
    public static final String EMTPTY_STRING_EXCEPTION = "Login and password can't be null";

    private String login;
    private String password;

    public Admin(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(login, admin.login) &&
                Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
