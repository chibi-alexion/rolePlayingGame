/**
 * 
 */
package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.persistence.*;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Classe;
import entities.Race;
import entities.SecretQuestion;
import services.ClasseService;
import services.RaceService;
import services.SecretQuestionService;


/**
 * @author S
 *
 */
@Named
@RequestScoped
public class RaceBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(RaceBean.class);

	private List <Race> listRace;
	private Race raceUpdate;
	private Race raceCreate;
	private EntityManager em;

	public RaceBean(){
	}
	
	@PostConstruct
	public void init(){
		
		em = EMF.getEM();
		raceCreate=new Race();
		raceUpdate=new Race();
		RaceService rService = new RaceService(em);
		listRace = rService.findAllRace();
		log.info(listRace);
		log.info("Récuperation des Race depuis la db");
		for(Race r : listRace)
			log.debug("Race: " + r.getNameRace());
		em.close();
		raceUpdate=listRace.get(1);

	}
	
public String submitNewRace(){

		em = EMF.getEM();

		//log.info(classeCreate.getIntelligence());
		

	    RaceService service = new RaceService(em);
	    try{
	    
	    	service.raceCreate(raceCreate);
	    	em.close();
	    	System.out.println("Race created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Race not created !"); 	
	    }
	    
	    return "";
	}

public Race raceToUpdate(int idRace)throws IOException{
	
	log.info(idRace);
	raceUpdate = listRace.get(idRace-1);
	log.info(raceUpdate);
	return raceUpdate;
}

public String raceUpdate(){

	
	em = EMF.getEM();
    RaceService rservice = new RaceService(em);

    try{
    	rservice.raceUpdate(raceUpdate);
    	em.close();
    	System.out.println("Race upated");
    }
    catch(Exception e){
    	log.error(e,e);
		log.info("Race not upated !"); 	
    }
    init();
    return "";
}

/**
 * @return the listRace
 */
public List<Race> getListRace() {
	return listRace;
}

/**
 * @param listRace the listRace to set
 */
public void setListRace(List<Race> listRace) {
	this.listRace = listRace;
}

/**
 * @return the raceUpdate
 */
public Race getRaceUpdate() {
	return raceUpdate;
}

/**
 * @param raceUpdate the raceUpdate to set
 */
public void setRaceUpdate(Race raceUpdate) {
	this.raceUpdate = raceUpdate;
}

/**
 * @return the raceCreate
 */
public Race getRaceCreate() {
	return raceCreate;
}

/**
 * @param raceCreate the raceCreate to set
 */
public void setRaceCreate(Race raceCreate) {
	this.raceCreate = raceCreate;
}


}
