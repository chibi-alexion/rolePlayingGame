package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Target;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import connexion.EMF;

import org.apache.log4j.Logger;

import entities.Race;
import entities.Role;
import entities.SecretQuestion;
import entities.User;
import managedBean.SessionUser;
import services.RaceService;
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
	
	private List <User> listUser;
	private List <SecretQuestion> listSecretQuestion;
	private User userCreate;
	private EntityManager em;
	private int idUser;
	private String mail;
	private String password;
	
	
	/**
	 * Default constructor
	 */	
	public UserBean() {
	}
	
	@PostConstruct
	public void init() {
		
		em = EMF.getEM();
		userCreate = new User();

		UserService uService = new UserService(em);
		listUser = uService.findAllUser();
		log.info(listUser);
		log.info("Récuperation des Race depuis la db");
		for(User u : listUser)
			log.debug("Utilisateur: " + u.getIdUser());
		em.close();
		//userUpdate=;
		user = listUser.get(1);
		log.info("Role du user "+user.getLogin());

		log.info("Role du user "+user.getRole().getNameRole());



	}
	
	public String submitNewUser(){

		em=EMF.getEM();
		log.info(user.getSecretquestion());
	    UserService service = new UserService(em);

	    try{
	    	userCreate.setRole(em.find(Role.class, 1));
	    	service.userCreate(userCreate);
	    	FacesContext.getCurrentInstance().addMessage(
					"SubmitUser:userLogin",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Character Created",
							"User Created"));
	    	LoginBean u = new LoginBean();
	    	u.logout();
	    	System.out.println("User created");
		    return "login";

	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("User not created !");	
	    }
	    em.close();
	    return "login";
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
	    	User user= uservice.userUpdate(u);
	    	log.info(user.getPassword());

	    	log.info("User updated");
	    	HttpSession session = SessionUser.getSession();
			
			session.setAttribute("mail", u.getE_mail());
			session.setAttribute("password", u.getPassword());
			
			FacesContext.getCurrentInstance().addMessage(
					"submitInformation",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Informations updated",
							"Please enter correct username and Password"));
			
			log.info("Session updated");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("User not created !"); 	
	    }
	    em.close();
	    return "";
	}
	
	public String updateUserRole(){
		
		log.info("updateUserRole");
		log.info("User "+user.getRole().getNameRole());
		log.info(user.getLogin());
		em=EMF.getEM();

		UserService uservice = new UserService(em);

	    try{
	    	//user.setRole(roleUser);
	    	log.info("new password set "+user.getPassword());
	    	log.info("new mail set "+user.getE_mail());
	    	//user.setSecretquestion(secretquestion);
	    
	    	
	    	//System.out.println(user.getSecretquestion());
	    	User us= uservice.userUpdate(user);
	    	log.info(us.getLogin());

	    	log.info("User updated");
	    	//HttpSession session = SessionUser.getSession();
			
			//session.setAttribute("mail", user.getRole());
			//log.info("Session updated");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("User not created !"); 	
	    }
	    init();
		return "";
		
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userCreate
	 */
	public User getUserCreate() {
		return userCreate;
	}

	/**
	 * @param userCreate the userCreate to set
	 */
	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}

	/**
	 * @return the listUser
	 */
	public List<User> getListUser() {
		return listUser;
	}

	/**
	 * @param listUser the listUser to set
	 */
	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	/**
	 * @return the listSecretQuestion
	 */
	public List<SecretQuestion> getListSecretQuestion() {
		return listSecretQuestion;
	}

	/**
	 * @param listSecretQuestion the listSecretQuestion to set
	 */
	public void setListSecretQuestion(List<SecretQuestion> listSecretQuestion) {
		this.listSecretQuestion = listSecretQuestion;
	}

	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
}