package com.jsfcourse.bb;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import com.jsfcourse.entities.Users;
/**
 *
 * @author vladm
 */
@Named
@SessionScoped
public class UserSession implements Serializable {

    private Users currentUser;

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }
}
