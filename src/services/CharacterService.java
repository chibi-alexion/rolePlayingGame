/**
 * 
 */
package services;

import entities.SecretQuestion;

import java.io.Serializable;
import java.util.List;

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

	
	private  EntityManager em;
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
	public List<Character> findAllCharacterAlive (EntityManager em){
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
		
		EntityManager em =EMF.getEM();
		
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

	
	
	
}
