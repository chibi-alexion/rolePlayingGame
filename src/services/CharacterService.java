/**
 * 
 */
package services;

import entities.SecretQuestion;
import entities.User;

import java.io.Serializable;
import java.util.List;
import entities.Character;

import javax.persistence.*;

import org.apache.log4j.Logger;

import connexion.EMF;


/**
 * @author S
 *
 */
public class CharacterService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final Logger	log	= Logger.getLogger(CharacterService.class);

	
	private EntityManager em;
	private SecretQuestion secretQuestionUser;
	
	public CharacterService(EntityManager em)
	{
		this.em = em;
	}
	
	public CharacterService() {
		// TODO Auto-generated constructor stub
	}

	public Character characterCreate(Character c) {
		
	    em.getTransaction().begin();  		

		em.persist(c);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return c;
	}
	public Character characterUpdate(Character c) {
			
		em.getTransaction().begin();  		

		em.persist(c);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return c;
		}
	public List<Character> findAllCharacterAlive (){
	    try {
			
			
	        TypedQuery<Character> query = em.createNamedQuery("SecretQuestion.findAll", Character.class);
	        //log.info(query.getResultList());
	        return query.getResultList();

	      } catch (NoResultException e) {
	    	  
	    	  System.out.print("secretquestion service no shit found");

	        return null;
	      }
	}
	
public Character findidCharacterByID(int idCharacter) {
		
		
	    try {
	    	log.info(idCharacter);
	         
	    	Character secret = (Character) em.createNamedQuery("Character.findSecretQuestionByID").setParameter("id", idCharacter)
	            .getSingleResult();
	         return secret;
	      } catch (NoResultException e) {
	    	  System.out.println("erreur");
	        return null;
	      }
}
public List<Character> findAllCharacterByUser(User user) {
	
	
    try {
    	log.info("findAllCharacterByUser");
    	log.info(user.getIdUser());
    	
    	TypedQuery<Character> query = em.createNamedQuery("Character.findAllCharacterByUser", Character.class);
    	log.info(query);
    	query.setParameter("user", user);
    	log.info(query);
    	log.info(query.getResultList());
    	
    	return query.getResultList(); 
    	
      } catch (NoResultException e) {
    	  System.out.println("erreur");
        return null;
      }
}

	
	
	
}
