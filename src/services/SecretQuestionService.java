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
public class SecretQuestionService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final Logger	log	= Logger.getLogger(SecretQuestionService.class);

	
	private  EntityManager em;
	private SecretQuestion secretQuestionUser;
	
	public SecretQuestionService(EntityManager em)
	{
		this.em = em;
	}
	
	public SecretQuestionService() {
		// TODO Auto-generated constructor stub
	}

	public SecretQuestion secretQuestionCreate(SecretQuestion s) {
		
	    em.getTransaction().begin();  		

		em.persist(s);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return s;
	}
	public SecretQuestion secretQuestionUpdate(SecretQuestion s) {
			
		em.getTransaction().begin();  		

		em.persist(s);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return s;
		}
	public List<SecretQuestion> findAllSecretQuestion (){
	    try {
			log.info(em);
			log.info("service find all secret question");
	        TypedQuery<SecretQuestion> query = em.createNamedQuery("SecretQuestion.findAll", SecretQuestion.class);
	        return query.getResultList();

	      } catch (NoResultException e) {
	    	  
	    	  System.out.print("secretquestion service no shit found");

	        return null;
	      }
	}
	
public SecretQuestion findSecretQuestionByID(int idSecretQuestion) {
		
		
	    try {
	    	log.debug(idSecretQuestion);
	         
			SecretQuestion secret = (SecretQuestion) em.createNamedQuery("SecretQuestion.findAllById").setParameter("id", idSecretQuestion)
	            .getSingleResult();
	         return secret;
	      } catch (NoResultException e) {
	    	  System.out.println("erreur");
	        return null;
	      }
}

	
	
	
}
