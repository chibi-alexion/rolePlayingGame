/**
 * 
 */
package services;

import javax.persistence.*;
import entities.Race;

/**
 * @author S
 *
 */
public class RaceService {
	private EntityManager em;
	private Race race;

	public RaceService(EntityManager em) {
		this.em = em;
	}
	
public Race raceCreate(Race r) {
		
	    em.getTransaction().begin();  		

		em.persist(r);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return r;
	}
	public Race raceUpdate(Race r) {
			
		em.getTransaction().begin();  		

		em.persist(r);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return r;
		}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	/**
	 * @param race the race to set
	 */
	public void setRace(Race race) {
		this.race = race;
	}


}
