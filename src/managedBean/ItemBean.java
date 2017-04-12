/**
 * 
 */
package managedBean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Item;
import services.ItemService;


/**
 * @author S
 *
 */
@Named
@SessionScoped
public class ItemBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(ItemBean.class);

	private List <Item> listItem;
	private Item item;

	public ItemBean(){
	}
	
	@PostConstruct
	public void init(){
		log.info("passage 1");
		//System.out.print("race bean load race");
		EntityManager em = EMF.getEM();
		log.info("passage 2");
		ItemService tlService = new ItemService(em);
		log.info("passage 3");
		//System.out.print("race bean init service");
		listItem = tlService.findAllItem();
		//System.out.print("race bean retour service");
		log.info(listItem);
		log.info("Récuperation des entites depuis la db ok");
		for(Item sq : listItem)
			log.debug("Item : " + sq.getNameItem());
	}
	
	public List<Item> loadItem(){
		
		//System.out.print("secretquestion bean load secretquestion");
		EntityManager em = EMF.getEM();
		ItemService tlService = new ItemService(em);
		//System.out.print("secretquestion bean init service");
		listItem = tlService.findAllItem();
		//System.out.print("secretquestion bean retour service");
		log.info(listItem);
		log.info("Récuperation des entites depuis la db ok");

		return listItem;
		
	}
	

	
	
	
public String submitNewItem(){

		
		EMF.getEMF();
		EntityManager em = EMF.getEM();
		ItemService service = new ItemService(em);

	    em.getTransaction().begin();  		
	    try{
	    
	    	service.itemCreate(item);
	    	em.getTransaction().commit();

	    	System.out.println("Item created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Item not created !"); 	
	    }
	    
	    return "";
	}

public String ItemUpdate(){

	
	EMF.getEMF();
	EntityManager em = EMF.getEM();
	ItemService service = new ItemService(em);

    em.getTransaction().begin();  		
    try{
    
    	service.itemUpdate(item);
    	em.getTransaction().commit();

    	System.out.println("Item created");
    }
    catch(Exception e){
    	log.error(e,e);
		log.info("Item not created !"); 	
    }
    
    return "";
}
	/**
	 * @return the listItem
	 */
	public List <Item> getListItem() {
		return listItem;
	}

	/**
	 * @param listItem the listItem to set
	 */
	public void setListRace(List <Item> listItem) {
		this.listItem = listItem;
	}

	/**
	 * @return the secretQuestion
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param Item the Item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

}
