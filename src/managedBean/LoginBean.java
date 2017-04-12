/**
 * 
 */
package managedBean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import javax.servlet.http.HttpSession;

import managedBean.SessionUser;
import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Role;
import entities.User;
import services.UserService;

/**
 * @author S
 *
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	static Logger log = Logger.getLogger(LoginBean.class);

	
	private String name;
	private String password;
	private User userSession=null;
	
	public LoginBean() {
		// TODO Auto-generated constructor stub
	}
	
public String login(){
		
		
	    try {
	    	log.info("name " +name);
	    	log.info("password " +password);
			EntityManager em = EMF.getEM();

	    	UserService us = new UserService(em);
	    	userSession = us.findUserSession(name,password);
	    	
	    	if(userSession != null){
	    		HttpSession session = SessionUser.getSession();
				session.setAttribute("idUser", userSession.getIdUser());
				session.setAttribute("login", userSession.getLogin());
				session.setAttribute("mail", userSession.getE_mail());
				session.setAttribute("password", userSession.getPassword());
				Role roleUser=userSession.getRole();
				log.info("Role user session " +roleUser.getNameRole());
	    		
				return roleUser.getNameRole();}
	    	else {
				FacesContext.getCurrentInstance().addMessage(
						"submitLogin",
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Incorrect Username and Passowrd",
								"Please enter correct username and Password"));}
	    		return "login";
	    	
	      } catch (NoResultException e) {
	    	  log.info("erreur");
	        return "login";
	      }
	}
public String logout() {
	HttpSession session = SessionUser.getSession();
	log.info("logout");
	session.invalidate();
	return "login";
}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return userSession;
	}

	public void setUser(User user) {
		this.userSession = user;
	}
}
