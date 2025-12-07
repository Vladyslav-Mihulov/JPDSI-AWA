package com.jsfcourse.users;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsfcourse.dao.UsersDAO;
import com.jsfcourse.entities.Users;

@Named
@ViewScoped
public class UsersEditBB implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_USERS_LIST = "usersList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private Users user = new Users();
    private Users loaded = null;

    @EJB
    UsersDAO usersDAO;

    @Inject
    FacesContext context;

    @Inject
    Flash flash;

    public Users getUser() {
        return user;
    }

    public void onLoad() throws IOException {
        loaded = (Users) flash.get("user");

        if (loaded != null) {
            user = loaded;
        } else {
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Błędne użycie systemu – brak użytkownika", null));
        }
    }

    public String saveData() {

        if (loaded == null) {
            return PAGE_STAY_AT_THE_SAME;
        }

        try {
            if (user.getIdUser() == null) {
                usersDAO.create(user);
            } else {
                usersDAO.merge(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Wystąpił błąd podczas zapisu", null));
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_USERS_LIST;
    }
}
