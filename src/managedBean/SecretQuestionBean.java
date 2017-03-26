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
import entities.SecretQuestion;
import services.SecretQuestionService;


/**
 * @author S
 *
 */
@Named
@SessionScoped
public class SecretQuestionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(SecretQuestionService.class);

	private List <SecretQuestion> listSecretQuestion;
	private SecretQuestion secretQuestion;

	public SecretQuestionBean(){
	}
	
	@PostConstruct
	public void init(){
	}
	
	public List<SecretQuestion> loadSecretQuestion(){
		
		//System.out.print("secretquestion bean load secretquestion");
		EntityManager em = EMF.getEM();
		SecretQuestionService tlService = new SecretQuestionService(em);
		//System.out.print("secretquestion bean init service");
		listSecretQuestion = tlService.findAllSecretQuestion(em);
		//System.out.print("secretquestion bean retour service");

		return listSecretQuestion;
		
	}
	
	
	
public String submitNewSecretQuestion(){

		
		EMF.getEMF();
		EntityManager em = EMF.getEM();
	    SecretQuestionService service = new SecretQuestionService(em);

	    em.getTransaction().begin();  		
	    try{
	    
	    	service.secretQuestionCreate(secretQuestion);
	    	em.getTransaction().commit();

	    	System.out.println("User created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("User not created !"); 	
	    }
	    
	    return "";
	}

public String secretQuestionUpdate(){

	
	EMF.getEMF();
	EntityManager em = EMF.getEM();
    SecretQuestionService service = new SecretQuestionService(em);

    em.getTransaction().begin();  		
    try{
    
    	service.secretQuestionUpdate(secretQuestion);
    	em.getTransaction().commit();

    	System.out.println("Secret Question created");
    }
    catch(Exception e){
    	log.error(e,e);
		log.info("User not created !"); 	
    }
    
    return "";
}
	/**
	 * @return the listSecretQuestion
	 */
	public List <SecretQuestion> getListSecretQuestion() {
		return listSecretQuestion;
	}

	/**
	 * @param listSecretQuestion the listSecretQuestion to set
	 */
	public void setListSecretQuestion(List <SecretQuestion> listSecretQuestion) {
		this.listSecretQuestion = listSecretQuestion;
	}

	/**
	 * @return the secretQuestion
	 */
	public SecretQuestion getSecretQuestion() {
		return secretQuestion;
	}

	/**
	 * @param secretQuestion the secretQuestion to set
	 */
	public void setSecretQuestion(SecretQuestion secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

}
