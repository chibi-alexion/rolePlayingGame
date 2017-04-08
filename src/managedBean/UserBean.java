package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import connexion.EMF;
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
	private List <Role> listRole;

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
	    	
	    	System.out.println(user.getSecretquestion());
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
	 * @return the listRole
	 */
	public List <Role> getListRole() {
		return listRole;
	}

	/**
	 * @param listRole the listRole to set
	 */
	public void setListRole(List <Role> listRole) {
		this.listRole = listRole;
	}
}