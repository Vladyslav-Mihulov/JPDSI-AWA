package com.jsfcourse.bb;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Date;
import java.math.BigInteger;
import java.util.ResourceBundle;
import java.util.Locale;

import com.jsfcourse.dao.UsersDAO;
import com.jsfcourse.entities.Users;
import com.jsfcourse.dao.UserRoleDAO;
import com.jsfcourse.entities.UserRole;
import com.jsfcourse.entities.UserRolePK;
/**
 *
 * @author vladm
 */
@Named
@RequestScoped
public class RegistrationBB {
    private static final String PAGE_LOGIN = "/pages/public/login?faces-redirect=true";
    
    private String login;
    private String email;
    private String password;
    private String passwordConfirm;
    private String firstName;
    private String lastName;

    @Inject
    private UsersDAO usersDAO;
    @Inject
    private UserRoleDAO userRoleDAO; 

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    public String doRegistration() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale locale = ctx.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("resources.textMain", locale);
        

        if (!password.equals(passwordConfirm)) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    bundle.getString("passwordMismatch"), null));
            return null;
        }


        if (usersDAO.getUserByLogin(login) != null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    bundle.getString("loginExists"), null));
            return null;
        }

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

        Users user = new Users();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(hashed);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        usersDAO.create(user);


        user.setWhoCreated(BigInteger.valueOf(user.getIdUser()));
        user.setWhoModify(BigInteger.valueOf(user.getIdUser()));

        usersDAO.merge(user);
        
        UserRole userRole = new UserRole();
        long roleId = 2L; 
        userRole.setUserRolePK(new UserRolePK(user.getIdUser(), roleId));
        userRole.setUsers(user);
        userRole.setDateStart1(new Date());
        userRoleDAO.create(userRole);
        
        ctx.getExternalContext().getFlash().setKeepMessages(true);
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                bundle.getString("registrationSuccess"), null));

        return PAGE_LOGIN;
    }
}
