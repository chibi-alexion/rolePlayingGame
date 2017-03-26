package services;

import javax.persistence.*;

import entities.Category;

/**
 * @author S
 *
 */
public class CategoryService {
	private EntityManager em;
	private Category category;

	public CategoryService(EntityManager em) {
		this.em = em;
	}
	
public Category categoryCreate(Category c) {
		
	    em.getTransaction().begin();  		

		em.persist(c);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return c;
	}
	public Category categoryUpdate(Category c) {
			
		em.getTransaction().begin();  		

		em.persist(c);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return c;
		}

	/**
	 * @return the race
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param race the race to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}


}
