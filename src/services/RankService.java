/**
 * 
 */
package services;

import entities.Category;
import entities.Character;
import entities.HallOfFame;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.apache.log4j.Logger;


/**
 * @author S
 *
 */
public class RankService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7804660371718734982L;
	private static final Logger	log	= Logger.getLogger(RankService.class);

	private Character character;
	private List<Category> listCategory;
	private EntityManager em; 
	
	public RankService(EntityManager em)
	{
		this.em = em;

	}
	
public List<Character> rankByLvl(){
		

		log.info("rankByLvl methode service");
		//TypedQuery<Category> queryCategory = em.createNamedQuery("Category.findAllByName", Category.class);
		//queryCategory.setParameter("name", "level");
		//log.info(queryCategory.getSingleResult());
		//Category lvl=queryCategory.getSingleResult();
		//log.info(lvl);
		TypedQuery<Character> queryHof = em.createNamedQuery("Character.findRankingByLevel", Character.class);
		//TypedQuery<Character> queryHof = em.createNamedQuery("Character.findRankingByLevel", Character.class);
		//queryHof.setParameter("category", lvl);
		log.info(queryHof.getResultList());
		List<Character> listingRank = queryHof.getResultList();
    	log.info(listingRank);
		return listingRank;
	
	}
	
/*public User userCreate(User u) {
		
	    em.getTransaction().begin(); 

		em.persist(u);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return u;
	}
	public User userUpdate(User u) {
			
		log.info("User service "+u); 
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
				log.info(user);
		         return user;
		      } catch (NoResultException e) {
		    	  log.info("user non trouvé");
		        return null;
		      }		
	}
	public User findUserById(int userId){
		log.info("findUserById");
		User user = (User) em.createNamedQuery("User.findUserById").setParameter("id", userId).getSingleResult();
	         return user;
	}
	*/
	
	public Character getCharacter() {
		return character;
	}

	/**
	 * @param user the user to set
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}
	
	

}