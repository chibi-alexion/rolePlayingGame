/**
 * 
 */
package services;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.apache.log4j.Logger;

import entities.Classe;
import entities.Race;
import entities.SecretQuestion;


/**
 * @author S
 *
 */
public class RaceService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(RaceService.class);

	private Race race;
	private EntityManager em; 
	
	public RaceService(EntityManager em)
	{
		this.em = em;

	}
	
public Race raceCreate(Race r) {
		
	    em.getTransaction().begin(); 

		em.persist(r);
	    em.getTransaction().commit();  		

		System.out.println("Classe persist ok");

		return r;
	}
	public Race raceUpdate(Race r) {
			
		log.info("User service "+r); 
		em.getTransaction().begin();  		

		em.merge(r);
	    em.getTransaction().commit(); 
	    System.out.println("Classe persist ok");
	
			return r;
		}

	public List<Race> findAllRace(){
		TypedQuery<Race> query = em.createNamedQuery("Race.findAll", Race.class);		
		log.info(query);
        return query.getResultList();		
	}
	
	public Race findRaceById(int raceId){
		
        try {
	    	log.debug(raceId);    
	    	Race race = (Race) em.createNamedQuery("Race.findRaceById").setParameter("id", raceId)
	            .getSingleResult();
	         return race;
	      } catch (NoResultException e) {
	    	  System.out.println("erreur");
	        return null;
	      }
	}
	public Race findRaceByName(String raceName){
		
        try {
	    	log.debug("Nom de la race dans service find by name "+raceName);    
	    	Race race = (Race) em.createNamedQuery("Race.findRaceByName").setParameter("name", raceName)
	            .getSingleResult();
	         return race;
	      } catch (NoResultException e) {
	    	  System.out.println("race non trouvé");
	        return null;
	      }
	}
}