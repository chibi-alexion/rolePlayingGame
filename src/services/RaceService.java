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
		log.info(em);
		TypedQuery<Race> query = em.createNamedQuery("Race.findAll", Race.class);		
		log.info(query);
        return query.getResultList();		
	}
	
	public Race findRaceById(int raceId){
		log.info("findUserById " +raceId);
		log.info(em);
		TypedQuery<Race> query = em.createNamedQuery("Race.findRaceById", Race.class);
		query.setParameter("id", raceId);		
		log.info(query);
		log.info(query.getSingleResult());
        return query.getSingleResult();		
	}
}