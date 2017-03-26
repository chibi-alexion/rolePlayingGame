package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import connexion.EMF;
import services.SecretQuestionService;
import services.UserService;

import org.apache.log4j.Logger;

import entities.Role;
import entities.SecretQuestion;
import entities.User;

/**
 * @author 
 *
 */
@Named
@RequestScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Log4j
	private static final Logger	log	= Logger.getLogger(UserBean.class);
		
	private User user;
	private List <SecretQuestion> listSecretQuestion;
	private SecretQuestion secret;
	/*
	private String answer;
	private String e_mail;
	private String login;
	private String password;
	private Role role;
	
	 */
	
	/**
	 * Default constructor
	 */	
	public UserBean() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
		
		user = new User();
	}
	
	public String submitNewUser(){

		
		EntityManager em = EMF.getEM();
	    UserService service = new UserService(em);

	    em.getTransaction().begin();  		
	    try{
	    
	    	user.setRole(em.find(Role.class, 1));
	    	user.setSecretquestion(em.find(SecretQuestion.class, 1));
	    	service.userCreate(user);
	    	em.getTransaction().commit();

	    	System.out.println("User created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("User not created !"); 	
	    }
	    
	    return "";
	}

	// Getters and Setters

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	 * @return the secret
	 */
	public SecretQuestion getSecret() {
		return secret;
	}

	/**
	 * @param secret the secret to set
	 */
	public void setSecret(SecretQuestion secret) {
		this.secret = secret;
	}
}