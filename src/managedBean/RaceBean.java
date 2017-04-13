/**
 * 
 */
package managedBean;

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
@ViewScoped
public class RaceBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(RaceBean.class);

	private List <Race> listRace;
	

	

	private EntityManager em;

	public RaceBean(){

	}
	
	@PostConstruct
	public void init(){
		
		em = EMF.getEM();

		RaceService rService = new RaceService(em);
		listRace = rService.findAllRace();
		log.info(listRace);
		log.info("Récuperation des races depuis la db ok");
		for(Race c : listRace)
			log.debug("Race: " + c.getNameRace());
		em.close();

	}
	
public String submitNewSecretQuestion(){


	    SecretQuestionService service = new SecretQuestionService(em);
	    try{
	    
	    	//service.secretQuestionCreate();
	    	em.close();
	    	System.out.println("User created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("User not created !"); 	
	    }
	    
	    return "";
	}

public String secretQuestionUpdate(){


    SecretQuestionService service = new SecretQuestionService(em);

    try{
    
    	//service.secretQuestionUpdate(secretQuestion);
    	em.close();
    	System.out.println("Secret Question created");
    }
    catch(Exception e){
    	log.error(e,e);
		log.info("User not created !"); 	
    }
    
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
	
}
