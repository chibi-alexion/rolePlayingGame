/**
 * 
 */
package managedBean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class SecretQuestionBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(SecretQuestionBean.class);

	private List <SecretQuestion> listSecretQuestion;
	private SecretQuestion secretQuestion;
	private EntityManager em;

	public SecretQuestionBean(){

	}
	
	@PostConstruct
	public void init(){
		
		em = EMF.getEM();
		SecretQuestionService sqService = new SecretQuestionService(em);
		listSecretQuestion = sqService.findAllSecretQuestion();
		log.info(listSecretQuestion);
		log.info("Récuperation des question secretes depuis la db ok");
		for(SecretQuestion sq : listSecretQuestion)
			log.debug("Question: "+ sq + sq.getQuestion());
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
