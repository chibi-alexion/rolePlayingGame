/**
 * 
 */
package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

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
public class ClasseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(SecretQuestionService.class);

	private List <SecretQuestion> listClasse;
	private Classe classe;

	public ClasseBean(){
	}
	
	@PostConstruct
	public void init(){
		classe = new Classe();
}
	
public String submitNewClasse(){

		
		EMF.getEMF();
		EntityManager em = EMF.getEM();
	    ClasseService service = new ClasseService(em);

	    em.getTransaction().begin();  		
	    try{
	    
	    	service.classeCreate(classe);
	    	em.getTransaction().commit();

	    	System.out.println("Classe created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Classe not created !"); 	
	    }
	    
	    return "";
	}

public String secretQuestionUpdate(){

	
	EMF.getEMF();
	EntityManager em = EMF.getEM();
	ClasseService service = new ClasseService(em);

    em.getTransaction().begin();  		
    try{
    
    	service.classeUpdate(classe);
    	em.getTransaction().commit();

    	System.out.println("Classe updated");
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
public List <SecretQuestion> getListClasse() {
	return listClasse;
}

/**
 * @param listClasse the listClasse to set
 */
public void setListClasse(List <SecretQuestion> listClasse) {
	this.listClasse = listClasse;
}

}
