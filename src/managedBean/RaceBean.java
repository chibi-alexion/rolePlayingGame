package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Race;
import services.RaceService;

/**
 * @author S
 *
 */
public class RaceBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(RaceBean.class);

	private List <Race> listRace;
	private Race race;

	public RaceBean(){
	}
	
	@PostConstruct
	public void init(){
		race = new Race();
}
	
public String submitNewRace(){

		
		EMF.getEMF();
		EntityManager em = EMF.getEM();
		RaceService service = new RaceService(em);

	    em.getTransaction().begin();  		
	    try{
	    
	    	service.raceCreate(race);
	    	em.getTransaction().commit();

	    	System.out.println("Race created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Race not created !"); 	
	    }
	    
	    return "";
	}

public String updateRace(){

	
	EMF.getEMF();
	EntityManager em = EMF.getEM();
	RaceService service = new RaceService(em);

    em.getTransaction().begin();  		
    try{
    
    	service.raceUpdate(race);
    	em.getTransaction().commit();

    	System.out.println("Race updated");
    }
    catch(Exception e){
    	log.error(e,e);
		log.info("Classe not updated !"); 	
    }
    
    return "";
}

/**
 * @return the listClasse
 */
public List <Race> getListRace() {
	return listRace;
}

/**
 * @param listClasse the listClasse to set
 */
public void setListClasse(List <Race> listRace) {
	this.listRace = listRace;
}

}
