package com.jsfcourse.bb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import com.jsfcourse.dao.UsersDAO;
import com.jsfcourse.entities.Users;

@Named
@RequestScoped
public class UsersListBB {

    private static final String PAGE_USERS_EDIT = "usersEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String lastName;
    private String firstName;
    private String login;
    private String email;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    UsersDAO usersDAO;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Users> getFullList() {
        return usersDAO.getFullList();
    }

    public List<Users> getList() {
        Map<String, Object> searchParams = new HashMap<>();

        if (lastName != null && !lastName.isEmpty()) {
            searchParams.put("lastName", lastName);
        }
        if (firstName != null && !firstName.isEmpty()) {
            searchParams.put("firstName", firstName);
        }

        return usersDAO.getList(searchParams);
    }

    public String newUser() {
        Users user = new Users();

        flash.put("user", user);

        return PAGE_USERS_EDIT;
    }

    public String editUser(Users user) {

        flash.put("user", user);

        return PAGE_USERS_EDIT;
    }

    public String deleteUser(Users user) {
        usersDAO.remove(user);
        return PAGE_STAY_AT_THE_SAME;
    }
}
