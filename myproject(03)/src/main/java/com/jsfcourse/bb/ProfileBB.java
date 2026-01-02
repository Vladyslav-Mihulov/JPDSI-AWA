package com.jsfcourse.bb;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import com.jsfcourse.entities.Users;
import com.jsfcourse.dao.UserRoleDAO;
import jakarta.inject.Inject;

import java.util.List;
/**
 *
 * @author vladm
 */
@Named
@RequestScoped
public class ProfileBB {

    @Inject
    private UserRoleDAO userRoleDAO;

    public Users getCurrentUser() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
        if (session == null) return null;

        RemoteClient<?> client = RemoteClient.load(session);
        if (client != null) {
            return (Users) client.getDetails();
        }
        return null;
    }

    public List<String> getRoles() {
        Users user = getCurrentUser();
        if (user == null) return List.of();
        return userRoleDAO.getRolesByUserId(user.getIdUser());
    }

}
    