package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import connexion.EMF;
import services.UserService;


@ManagedBean
@RequestScoped
@FacesValidator("ValidationLogin")


public class ValidationLogin implements Validator  {


	private static final Logger	log	= Logger.getLogger(ValidationLogin.class);

	private String name;
	private Pattern pattern;
	private Matcher matcher;
	private EntityManager em;

	private static final String LOGIN_PATTERN ="^[_A-Za-z0-9-]+";

	public ValidationLogin(){
		  pattern = Pattern.compile(LOGIN_PATTERN);
	}
	@Override

	public void validate(FacesContext context, UIComponent component, Object submittedValue){ 
			

			matcher = pattern.matcher(submittedValue.toString());
			log.info("Patern "+pattern);
			log.info("Matcher "+matcher);
			String name = submittedValue.toString();
			log.info("submitted value "+name);
			em=EMF.getEM();
			
			UserService us=new UserService(em);
			log.info(us.findUserByLogin(name));
			if (us.findUserByLogin(name) == null) 
			{
				log.info("Login not used ");
				log.info(!matcher.matches());
				if (!matcher.matches()){
					
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Invalid login format.",
							"The login must contain only letters and numbers");

					FacesContext.getCurrentInstance().addMessage("SubmitUser:userLogin", msg);

					throw new ValidatorException(msg);
					
				}
				else{return;}
			}
			else {
				log.info("Login found ");
				
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Username already taken",
													"Chose another username ");
				//Le SubmitUser:userLogin c'est d'une par l id du form 'SubmitUser' login et d'autre part l'id du champ 'userLogin' 
				FacesContext.getCurrentInstance().addMessage("SubmitUser:userLogin", msg);
					
					throw new ValidatorException(msg);}
			}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
/*
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public Matcher getMatcher() {
		return matcher;
	}

	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	public static String getLoginPattern() {
		return LOGIN_PATTERN;
	}
*/

}