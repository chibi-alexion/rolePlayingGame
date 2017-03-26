package services;

import javax.persistence.*;

import entities.Category;
import entities.HallOfFame;

/**
 * @author S
 *
 */
public class HallOfFameService {
	private EntityManager em;
	private HallOfFame hallOfFame;

	public HallOfFameService(EntityManager em) {
		this.em = em;
	}
	
public HallOfFame hallOfFameCreate(HallOfFame hof) {
		
	    em.getTransaction().begin();  		

		em.persist(hof);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return hof;
	}
	public HallOfFame hallOfFameUpdate(HallOfFame hof) {
			
		em.getTransaction().begin();  		

		em.find(
		em.persist(hof);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return hof;
		}

	/**
	 * @return the race
	 */
	public HallOfFame getHallOfFame() {
		return hallOfFame;
	}

	/**
	 * @param race the race to set
	 */
	public void setHallOfFame(HallOfFame hallOfFame) {
		this.hallOfFame = hallOfFame;
	}


}

