package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import connexion.EMF;
import managedBean.UserBean;
import services.UserService;


@ManagedBean
@SessionScoped
@FacesValidator("ValidationLogin")


public class ValidationLogin implements Validator  {


	private static final Logger	log	= Logger.getLogger(ValidationLogin.class);

	private String name;
	private Pattern pattern;
	private Matcher matcher;
	private EntityManager em;

	private static final String ROLE_PATTERN ="[A-Za-z]";

	public ValidationLogin(){
		setPattern(Pattern.compile(ROLE_PATTERN));

	}

	public void validate(FacesContext context, UIComponent component, Object submittedValue) 
			
		throws ValidatorException {

			matcher = pattern.matcher(submittedValue.toString());

			String name = (String)submittedValue;
			em=EMF.getEM();
			
			UserService us=new UserService(em);
			log.info(us.findUserByLogin(name));
			if (us.findUserByLogin(name) == null) 
			{
				log.info("Block if us.findUserByLogin(name) == null ");
				if (!matcher.matches()){
	
					FacesMessage msg =
							new FacesMessage("Login validation failed.",
									"Invalid Login format.");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						
						throw new ValidatorException(msg);	
				}
				return;
	
			}
	
			else {
				log.info("Login found ");
				
				FacesMessage msg =
						new FacesMessage("Username already taken",
								"Invalid E-mail format.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					
					throw new ValidatorException(msg);}
			}

	

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

	public static String getRolePattern() {
		return ROLE_PATTERN;
	}


}