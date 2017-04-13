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

	public List<Race> findAllRace(){
		TypedQuery<Race> query = em.createNamedQuery("Race.findAll", Race.class);		
		//log.info(query);
        return query.getResultList();		
	}
	
	public Race findRaceById(int raceId){
		/*
		log.info("findRaceById " +raceId);
		TypedQuery<Race> query = em.createNamedQuery("Race.findRaceById", Race.class);
		query.setParameter("id", raceId);		
		//log.info("findRaceById "+query.getSingleResult());
        return query.getSingleResult();	
        */
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
}