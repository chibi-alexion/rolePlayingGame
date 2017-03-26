/**
 * 
 */
package services;

import entities.SecretQuestion;

import java.util.List;

import javax.persistence.*;


/**
 * @author S
 *
 */
public class SecretQuestionService {
	
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
	public List<SecretQuestion> findAllSecretQuestion (EntityManager em){
	    try {
			//System.out.print("secretquestion service préquery");
			
			
	        TypedQuery<SecretQuestion> query = em.createNamedQuery("SecretQuestion.findAll", SecretQuestion.class);
			//System.out.print("secretquestion service postquery");

	        //System.out.println(query.getResultList());
	        return query.getResultList();

	      } catch (NoResultException e) {
	    	  
	    	  System.out.print("secretquestion service no shit found");

	        return null;
	      }
}

	public SecretQuestion findSecretQuestionByID(int idSecretQuestion) {
		
		
		    try {
		         SecretQuestion secret = (SecretQuestion) em.createNamedQuery("SecretQuestion.findSecretQuestionByID").setParameter("id", secretQuestionUser.getIdSecretQuestion())
		            .getSingleResult();
		         return secret;
		      } catch (NoResultException e) {
		    	  System.out.println("erreur");
		        return null;
		      }
	}
	
	
}
