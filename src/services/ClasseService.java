/**
 * 
 */
package services;

import javax.persistence.*;
import entities.Classe;

/**
 * @author S
 *
 */
public class ClasseService {
	private EntityManager em;
	private Classe classe;

	public ClasseService(EntityManager em) {
		this.em = em;
	}
	
public Classe classeCreate(Classe c) {
		
	    em.getTransaction().begin();  		

		em.persist(c);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return c;
	}
	public Classe classeUpdate(Classe c) {
			
		em.getTransaction().begin();  		

		em.persist(c);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return c;
		}

	/**
	 * @return the classe
	 */
	public Classe getClasse() {
		return classe;
	}

	/**
	 * @param classe the classe to set
	 */
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
}
