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
	private Classe classe =new Classe();
	private int idClasse =0;
	private EntityManager em;

	public ClasseBean(){
	}
	
	@PostConstruct
	public void init(){
		
		em = EMF.getEM();

		ClasseService cService = new ClasseService(em);
		listClasse = cService.findAllClasse();
		log.info(listClasse);
		log.info("Récuperation des Classes depuis la db");
		for(Classe c : listClasse)
			log.debug("Classe: " + c.getNameClasse());
		em.close();

	}
	
public String submitNewClasse(){

		em = EMF.getEM();
		log.info(classe.getIntelligence());
		log.info(classe.getNameClasse());
		log.info(classe.getStrength());

	    ClasseService service = new ClasseService(em);
	    try{
	    
	    	service.classeCreate(classe);
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
	classe = listClasse.get(idClasse-1);
	log.info(classe);
	return classe;
}

public String classeUpdate(){

	log.info("Force mise a jour "+classe.getStrength());

	em = EMF.getEM();

	log.info("Force mise a jour "+classe.getStrength());
    ClasseService service = new ClasseService(em);

    try{
    
    	service.classeUpdate(classe);
    	em.close();
    	System.out.println("Classe upated");
    }
    catch(Exception e){
    	log.error(e,e);
		log.info("Classe not upated !"); 	
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
/**
 * @return the classe
 */
public Classe getClasse() {
	return classe;
}

/**
 * @param classe the classe to set
 */
public void setClasse(Classe classe) {
	this.classe = classe;
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
}
