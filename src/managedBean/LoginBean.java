/**
 * 
 */
package managedBean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.User;

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
	
	public User login(){
		
		EntityManager em =EMF.getEM();
		
	    try {
	    	log.info("name" +name);
	    	log.info("password" +password);
	         
			userSession = (User) em.createNamedQuery("User.findLogin").setParameter("login", name).setParameter("password", password)
	            .getSingleResult();
			return userSession;
	      } catch (NoResultException e) {
	    	  System.out.println("erreur");
	        return null;
	      }
		
		
	}
	
	public void test(){
		
		log.info(name);
		System.out.println(name);
    	log.info(password);
		System.out.println(password);

    	return;
	}

	
	
	

}
