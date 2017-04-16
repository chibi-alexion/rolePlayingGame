/**
 * 
 */
package services;

import entities.Race;
import entities.SecretQuestion;
import entities.User;

import java.io.Serializable;
import java.util.List;

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

		System.out.println("persist user ok");

		return u;
	}
	public User userUpdate(User u) {
			
		log.info("User service "+u); 
		User us = findUserById(u.getIdUser());
		em.getTransaction().begin();  		
		
		em.merge(us);
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
				log.info(user);
		         return user;
		      } catch (NoResultException e) {
		    	  log.info("user non trouvé");
		        return null;
		      }		
	}
	
	
	public List<User> findAllUser(){
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);		
		log.info(query);
        return query.getResultList();		
	}
	public User findUserById(int userId){
		log.info("findUserById " +userId);
		log.info(em);
		TypedQuery<User> query = em.createNamedQuery("User.findUserById", User.class);
		query.setParameter("id", userId);		
		log.info(query);
		log.info(query.getSingleResult());
        return query.getSingleResult();		
	}
	
	public List<User> findUserByLogin(String login){
		log.info("findUserByLogin " +login);
		log.info(em);
		try{
		TypedQuery<User> query = em.createNamedQuery("User.findUserByLogin", User.class);
		query.setParameter("login", login);		
		log.info("query "+query);
		log.info(query.getResultList());
		List<User> list=query.getResultList();
		if(list.isEmpty()){
			return null;
        }
		else{
			return query.getResultList();
		}
			
        }
		catch (NoResultException e) {
	    	  log.info("user bug");
	        return null;
	      }
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