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
import javax.inject.Named;
import javax.persistence.*;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Race;
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
	private SecretQuestion sqCreate;
	private SecretQuestion sqUpdate;
	private EntityManager em;

	public SecretQuestionBean(){

	}
	
	@PostConstruct
	public void init(){
		
		em = EMF.getEM();
		sqCreate= new SecretQuestion();
		sqUpdate= new SecretQuestion();
		SecretQuestionService sqService = new SecretQuestionService(em);
		listSecretQuestion = sqService.findAllSecretQuestion();
		log.info(listSecretQuestion);
		log.info("Récuperation des question secretes depuis la db ok");
		for(SecretQuestion sq : listSecretQuestion)
			log.debug("Question: "+ sq + sq.getQuestion());
		em.close();
		sqUpdate=listSecretQuestion.get(1);

	}
	
public String submitNewSecretQuestion(){

	em = EMF.getEM();

	    SecretQuestionService service = new SecretQuestionService(em);
	    try{
	    
	    	service.secretQuestionCreate(sqCreate);
	    	em.close();
	    	System.out.println("Secret Question created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Secret Question not created !"); 	
	    }
	    em.close();
	    return "";
	}
public SecretQuestion secretQuestionToUpdate(int idSecretQuestion)throws IOException{

	log.info(idSecretQuestion);
	sqUpdate = listSecretQuestion.get(idSecretQuestion-1);
	log.info(sqUpdate);
	return sqUpdate;
}

public String secretQuestionUpdate(){

	em = EMF.getEM();
	log.info(sqUpdate.getQuestion());
	log.info(sqUpdate.getIdSecretQuestion());
    SecretQuestionService service = new SecretQuestionService(em);

    try{
    
    	service.secretQuestionUpdate(sqUpdate);
    	em.close();
    	System.out.println("Secret Question updated");
    }
    catch(Exception e){
    	log.error(e,e);
		log.info("Secret Question not updated !");
		
    }
    init();
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
	 * @return the sqCreate
	 */
	public SecretQuestion getSqCreate() {
		return sqCreate;
	}

	/**
	 * @param sqCreate the sqCreate to set
	 */
	public void setSqCreate(SecretQuestion sqCreate) {
		this.sqCreate = sqCreate;
	}

	/**
	 * @return the sqUpdate
	 */
	public SecretQuestion getSqUpdate() {
		return sqUpdate;
	}

	/**
	 * @param sqUpdate the sqUpdate to set
	 */
	public void setSqUpdate(SecretQuestion sqUpdate) {
		this.sqUpdate = sqUpdate;
	}


}
