/**
 * 
 */
package services;

import entities.User;
import javax.persistence.*;


/**
 * @author S
 *
 */
public class UserService {
	
	private User user;
	private EntityManager em; 
	
	public UserService(EntityManager em)
	{
		this.em = em;

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
	
	
}
