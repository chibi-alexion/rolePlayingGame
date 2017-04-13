/**
 * 
 */
package services;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.apache.log4j.Logger;

import entities.Classe;
import entities.SecretQuestion;


/**
 * @author S
 *
 */
public class ClasseService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(ClasseService.class);

	private Classe classe;
	private EntityManager em; 
	
	public ClasseService(EntityManager em)
	{
		this.em = em;

	}
	
public Classe classeCreate(Classe cl) {
		
	    em.getTransaction().begin(); 

		em.persist(cl);
	    em.getTransaction().commit();  		

		System.out.println("persist user ok");

		return cl;
	}
	public Classe userUpdate(Classe cl) {
			
		log.info("User service "+cl); 
		em.getTransaction().begin();  		

		em.persist(cl);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return cl;
		}

	public List<Classe> findAllClasse(){
		TypedQuery<Classe> query = em.createNamedQuery("Classe.findAll", Classe.class);		
		log.info(query);
        return query.getResultList();		
	}
	
	public Classe findClasseById(int classeId){
		/*
		log.info("findClasseById " +classeId);
		TypedQuery<Classe> query = em.createNamedQuery("Classe.findClasseById", Classe.class);
		query.setParameter("id", classeId);		
		log.info("findClasseById result"+query.getSingleResult());
        return query.getSingleResult();	
        */
        try {
	    	log.debug(classeId);    
	    	Classe classe = (Classe) em.createNamedQuery("Classe.findClasseById").setParameter("id", classeId)
	            .getSingleResult();
	         return classe;
	      } catch (NoResultException e) {
	    	  System.out.println("erreur");
	        return null;
	      }
	}
}