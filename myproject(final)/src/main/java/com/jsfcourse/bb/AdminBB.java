package com.jsfcourse.bb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;


import com.jsfcourse.dao.UsersDAO;
import com.jsfcourse.entities.Users;


/**
 *
 * @author vladm
 */
@Named
@RequestScoped
public class AdminBB {

    private String lastName;
    private String firstName;
    private String login;
    private String email;
    private Long idUser;

    @EJB
    private UsersDAO usersDAO;

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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public List<Users> getUsers() {
        return usersDAO.findAllWithRoles();
    }

    public List<Users> getList() {
        Map<String, Object> searchParams = new HashMap<>();

        if (lastName != null && !lastName.isEmpty()) {
            searchParams.put("lastName", lastName);
        }
        if (firstName != null && !firstName.isEmpty()) {
            searchParams.put("firstName", firstName);
        }
        if (login != null && !login.isEmpty()) {
            searchParams.put("login", login);
        }
        if (email != null && !email.isEmpty()) {
            searchParams.put("email", email);
        }
        if (idUser != null) {
            searchParams.put("idUser", idUser);
        }

        return usersDAO.getList(searchParams);
    }

}
