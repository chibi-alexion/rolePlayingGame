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
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import managedBean.SessionUser;
import org.apache.log4j.Logger;

import connexion.EMF;
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
	private User userSession;
	
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
	    	
	    	log.info("user found " + userSession.getLogin());
	    	if(userSession != null){
	    		HttpSession session = SessionUser.getSession();
				session.setAttribute("idUser", userSession.getIdUser());
				session.setAttribute("login", userSession.getLogin());
				log.info("idUser "+userSession.getIdUser());
				log.info("idUser "+userSession.getLogin());
				
				log.info("Session crée "+session);
	    		
				return "success";}
	    	else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Incorrect Username and Passowrd",
								"Please enter correct username and Password"));}
	    		return "fail";
	    	
	      } catch (NoResultException e) {
	    	  System.out.println("erreur");
	        return "fail";
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
