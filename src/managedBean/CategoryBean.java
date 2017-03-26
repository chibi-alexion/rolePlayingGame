package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Category;
import services.CategoryService;

/**
 * @author S
 *
 */
public class CategoryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(RaceBean.class);

	private List <Category> listCategory;
	private Category category;

	public CategoryBean(){
	}
	
	@PostConstruct
	public void init(){
		category = new Category();
}
	
public String submitNewCategory(){

		
		EMF.getEMF();
		EntityManager em = EMF.getEM();
		CategoryService service = new CategoryService(em);

	    em.getTransaction().begin();  		
	    try{
	    
	    	service.categoryCreate(category);
	    	em.getTransaction().commit();

	    	System.out.println("Category created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Category not created !"); 	
	    }
	    
	    return "";
	}

public String updateCategory(){

	
	EMF.getEMF();
	EntityManager em = EMF.getEM();
	CategoryService service = new CategoryService(em);

    em.getTransaction().begin(); 		
    try{
    
    	service.categoryUpdate(category);
    	em.getTransaction().commit();

    	System.out.println("Category updated");
    }
    catch(Exception e){
    	log.error(e,e);
		log.info("Category not updated !"); 	
    }
    
    return "";
}

/**
 * @return the listClasse
 */
public List <Category> getListCategory() {
	return listCategory;
}

/**
 * @param listClasse the listClasse to set
 */
public void setListCategory(List <Category> listCategory) {
	this.listCategory = listCategory;
}

}
