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
	private Classe classeUpdate;
	private Classe classeCreate;
	private int idClasse =0;
	private EntityManager em;

	public ClasseBean(){
	}
	
	@PostConstruct
	public void init(){
		
		em = EMF.getEM();
		classeCreate=new Classe();
		classeUpdate=new Classe();
		ClasseService cService = new ClasseService(em);
		listClasse = cService.findAllClasse();
		log.info(listClasse);
		log.info("Récuperation des Classes depuis la db");
		for(Classe c : listClasse)
			log.debug("Classe: " + c.getStrength());
		em.close();
		classeUpdate=listClasse.get(1);
	}
	
public String submitNewClasse(){

		em = EMF.getEM();

		log.info(classeCreate.getIntelligence());
		log.info(classeCreate.getNameClasse());
		log.info(classeCreate.getStrength());

	    ClasseService service = new ClasseService(em);
	    try{
	    
	    	service.classeCreate(classeCreate);
	    	em.close();
	    	System.out.println("Classe created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Classe not created !"); 	
	    }
	    
	    return "";
	}

public Classe classeToUpdate(int idClasse)throws IOException{
	
	log.info(idClasse);
	classeUpdate = listClasse.get(idClasse-1);
	log.info(classeUpdate);
	return classeUpdate;
}

public String classeUpdate(){

	
	em = EMF.getEM();
    ClasseService service = new ClasseService(em);

    try{
    	service.classeUpdate(classeUpdate);
    	em.close();
    	System.out.println("Classe upated");
    }
    catch(Exception e){
    	log.error(e,e);
		log.info("Classe not upated !"); 	
    }
    init();
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


/**
 * @return the idClasse
 */
public int getIdClasse() {
	return idClasse;
}

/**
 * @param idClasse the idClasse to set
 */
public void setIdClasse(int idClasse) {
	this.idClasse = idClasse;
}

/**
 * @return the classeUpdate
 */
public Classe getClasseUpdate() {
	return classeUpdate;
}

/**
 * @param classeUpdate the classeUpdate to set
 */
public void setClasseUpdate(Classe classeUpdate) {
	this.classeUpdate = classeUpdate;
}

/**
 * @return the classeCreate
 */
public Classe getClasseCreate() {
	return classeCreate;
}

/**
 * @param classeCreate the classeCreate to set
 */
public void setClasseCreate(Classe classeCreate) {
	this.classeCreate = classeCreate;
}

}
