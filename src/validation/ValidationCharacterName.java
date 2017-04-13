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
import services.CharacterService;


@ManagedBean
@RequestScoped
@FacesValidator("ValidationCharacterName")


public class ValidationCharacterName implements Validator  {


	private static final Logger	log	= Logger.getLogger(ValidationCharacterName.class);

	private String name;
	private Pattern pattern;
	private Matcher matcher;
	private EntityManager em;

	private static final String CNAME_PATTERN ="^[_A-Za-z0-9-]+";

	public ValidationCharacterName(){
		  pattern = Pattern.compile(CNAME_PATTERN);
	}
	@Override

	public void validate(FacesContext context, UIComponent component, Object submittedValue){ 
			

			matcher = pattern.matcher(submittedValue.toString());
			log.info("Patern "+pattern);
			log.info("Matcher "+matcher);
			String name = submittedValue.toString();
			log.info("submitted value "+name);
			em=EMF.getEM();
			
			CharacterService us=new CharacterService(em);
			log.info(us.findCharacterByName(name));
			if (us.findCharacterByName(name) == null) 
			{
				log.info("Character name not used ");
				if (!matcher.matches()){
					
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Invalid login format.",
							"The character name must contain only letters and numbers");

					FacesContext.getCurrentInstance().addMessage("SubmitCharacter:characterName", msg);

					throw new ValidatorException(msg);
					
				}
				else{return;}
			}
			else {
				log.info("Character found ");
				
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"character name already taken",
													"Chose another username for your character ");
				//Le SubmitUser:userLogin c'est d'une par l id du form 'SubmitUser' login et d'autre part l'id du champ 'userLogin' 
				FacesContext.getCurrentInstance().addMessage("SubmitCharacter:characterName", msg);
					
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
	
}