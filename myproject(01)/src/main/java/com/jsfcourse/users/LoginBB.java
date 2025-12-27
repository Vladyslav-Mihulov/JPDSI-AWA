package com.jsfcourse.users;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import java.util.ResourceBundle;
import java.util.Locale;

import com.jsfcourse.dao.UsersDAO;
import com.jsfcourse.dao.UserRoleDAO;
import com.jsfcourse.entities.Users;

@Named
@RequestScoped
public class LoginBB {

    private static final String PAGE_MAIN = "/pages/usersList?faces-redirect=true";
    private static final String PAGE_LOGIN = "/pages/public/login";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String login;
    private String password;

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

    @Inject
    UsersDAO usersDAO;
    @Inject
    UserRoleDAO userRoleDAO;

    public String doLogin() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        Locale locale = ctx.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("resources.textMain", locale);

        Users user = usersDAO.getUserByLogin(login);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            String failedLoginMsg = bundle.getString("failedLogin");
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    failedLoginMsg, null));
            return PAGE_STAY_AT_THE_SAME;
        }

        RemoteClient<Users> client = new RemoteClient<Users>();
        client.setDetails(user);

        List<String> roles = userRoleDAO.getRolesByUserId(user.getIdUser());

        if (roles != null) {
            for (String role : roles) {
                client.getRoles().add(role);
            }
        }

        HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
        client.store(request);

        return PAGE_MAIN;
    }

    public String doLogout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);

        session.invalidate();
        return PAGE_LOGIN;
    }


}
