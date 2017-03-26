package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.HallOfFame;
import services.HallOfFameService;

/**
 * @author S
 *
 */
public class HallOfFameBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(RaceBean.class);

	private List <HallOfFame> listHallOfFame;
	private HallOfFame hallOfFame;

	public HallOfFameBean(){
	}
	
	@PostConstruct
	public void init(){
		hallOfFame = new HallOfFame();
}
	
public String submitNewCategory(){

		
		EMF.getEMF();
		EntityManager em = EMF.getEM();
		HallOfFameService service = new HallOfFameService(em);

	    em.getTransaction().begin();  		
	    try{
	    
	    	service.hallOfFameCreate(hallOfFame);
	    	em.getTransaction().commit();

	    	System.out.println("HallOfFame created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("HallOfFame not created !"); 	
	    }
	    
	    return "";
	}

public String updateHallOfFame(){

	
	EMF.getEMF();
	EntityManager em = EMF.getEM();
	HallOfFameService service = new HallOfFameService(em);

    em.getTransaction().begin(); 		
    try{
    
    	service.hallOfFameUpdate(hallOfFame);
    	em.getTransaction().commit();

    	System.out.println("HallOfFame updated");
    }
    catch(Exception e){
    	log.error(e,e);
		log.info("HallOfFame not updated !"); 	
    }
    
    return "";
}

/**
 * @return the listClasse
 */
public List <HallOfFame> getListHallOfFame() {
	return listHallOfFame;
}

/**
 * @param listClasse the listClasse to set
 */
public void setListHallOfFame(List <HallOfFame> listHallOfFame) {
	this.listHallOfFame = listHallOfFame;
}

}

