/**
 * 
 */
package services;

import entities.Item;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.apache.log4j.Logger;

import connexion.EMF;


/**
 * @author S
 *
 */
public class ItemService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final Logger	log	= Logger.getLogger(ItemService.class);

	
	private  EntityManager em;
	private Item item;
	
	public ItemService(EntityManager em)
	{
		this.em = em;
	}
	
	public ItemService() {
		// TODO Auto-generated constructor stub
	}

	public Item itemCreate(Item i) {
		
	    em.getTransaction().begin();  		

		em.persist(i);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return i;
	}
	public Item itemUpdate(Item i) {
			
		em.getTransaction().begin();  		

		em.persist(i);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return i;
		}
	public List<Item> findAllItem (){
	    try {
			
			
	        TypedQuery<Item> query = em.createNamedQuery("Item.findAll", Item.class);
	        //log.info(query.getResultList());
	        return query.getResultList();

	      } catch (NoResultException e) {
	    	  
	    	  System.out.print("race service no shit found");

	        return null;
	      }
	}
	
public Item findItemByID(int idItem) {
		
		//EntityManager em =EMF.getEM();
		
	    try {
	    	log.info(idItem);
	         
	    	Item item = (Item) em.createNamedQuery("Item.findAllById").setParameter("id", idItem)
	            .getSingleResult();
	         return item;
	      } catch (NoResultException e) {
	    	  System.out.println("erreur");
	        return null;
	      }
}

}
