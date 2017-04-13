/**
 * 
 */
package managedBean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.*;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Classe;
import entities.SecretQuestion;
import services.ClasseService;
import services.SecretQuestionService;


/**
 * @author S
 *
 */
@Named
@RequestScoped
public class ClasseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(ClasseBean.class);

	private List <Classe> listClasse;
	private SecretQuestion secretQuestion;
	

	private EntityManager em;

	public ClasseBean(){

	}
	
	@PostConstruct
	public void init(){
		
		log.info("jose");
		em = EMF.getEM();

		ClasseService cService = new ClasseService(em);
		log.info("init post init service");
		listClasse = cService.findAllClasse();
		log.info(listClasse);
		log.info("Récuperation des entites depuis la db ok");
		for(Classe c : listClasse)
			log.debug("Question: " + c.getStrength());
		em.close();

	}
	
public String submitNewSecretQuestion(){


	    SecretQuestionService service = new SecretQuestionService(em);
	    try{
	    
	    	service.secretQuestionCreate(secretQuestion);
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
    
    	service.secretQuestionUpdate(secretQuestion);
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
 * @return the listClasse
 */
public List<Classe> getListClasse() {
	return listClasse;
}

/**
 * @param listClasse the listClasse to set
 */
public void setListClasse(List<Classe> listClasse) {
	this.listClasse = listClasse;
}
}
