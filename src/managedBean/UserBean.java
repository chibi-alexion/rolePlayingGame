package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import connexion.EMF;

import org.apache.log4j.Logger;

import entities.Role;
import entities.SecretQuestion;
import entities.User;
import managedBean.SessionUser;
import services.UserService;


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
	private EntityManager em;
	private String mail;
	private String password;
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

		em=EMF.getEM();
		log.info(user.getSecretquestion());
	    UserService service = new UserService(em);

	    try{
	    	user.setRole(em.find(Role.class, 1));
	    	service.userCreate(user);
	    	System.out.println("User created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("User not created !");	
	    }
	    
	    return "";
	}
	public String updateUserInfo(){

		em=EMF.getEM();

		UserService uservice = new UserService(em);

	    try{
	    	log.info(mail);
	    	log.info(password);
	    	log.info(em);
			User u = uservice.findUserById(SessionUser.getUserId());
	    	
	    	u.setE_mail(mail);
	    	u.setPassword(password);
	    	
	    	log.info("new password set "+u.getPassword());
	    	log.info("new mail set "+u.getE_mail());
	    	//user.setSecretquestion(secretquestion);
	    
	    	
	    	//System.out.println(user.getSecretquestion());
	    	User user= uservice.userUpdate(u);
	    	log.info(u);

	    	log.info("User updated");
	    	HttpSession session = SessionUser.getSession();
			
			session.setAttribute("mail", u.getE_mail());
			session.setAttribute("password", u.getPassword());
			log.info("Session updated");
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

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}