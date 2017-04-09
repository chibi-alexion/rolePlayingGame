/**
 * 
 */
package services;

import entities.SecretQuestion;
import entities.User;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.log4j.Logger;


/**
 * @author S
 *
 */
public class UserService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7804660371718734982L;
	private static final Logger	log	= Logger.getLogger(UserService.class);

	private User user;
	private EntityManager em; 
	
	public UserService(EntityManager em)
	{
		this.em = em;

	}
	
public User userCreate(User u) {
		
	    em.getTransaction().begin(); 

		em.persist(u);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return u;
	}
	public User userUpdate(User u) {
			
		em.getTransaction().begin();  		

		em.persist(u);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return u;
		}
	public User findUserSession(String name,String password){
		
		
		 try {
		    	log.info(name);
		    	log.info(password);
		    	
		         
				User user = (User) em.createNamedQuery("User.findUserSession").setParameter("login", name).setParameter("password", password)
		            .getSingleResult();
		         return user;
		      } catch (NoResultException e) {
		    	  System.out.println("erreur");
		        return null;
		      }		
	}
	public User findUserById(int userId){
		log.info("findUserById");
		User user = (User) em.createNamedQuery("User.findUserById").setParameter("id", userId).getSingleResult();
	         return user;
	}
	
	
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	

}