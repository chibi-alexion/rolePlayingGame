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
import services.ClasseService;


@ManagedBean
@RequestScoped
@FacesValidator("ValidationClasse")


public class ValidationClasse implements Validator  {


	private static final Logger	log	= Logger.getLogger(ValidationClasse.class);

	private String name;
	private Pattern pattern;
	private Matcher matcher;
	private EntityManager em;

	private static final String CLASSE_PATTERN ="^[_A-Za-z0-9-]+";

	public ValidationClasse(){
		  pattern = Pattern.compile(CLASSE_PATTERN);
	}
	@Override
	public void validate(FacesContext context, UIComponent component, Object submittedValue){ 
			
			matcher = pattern.matcher(submittedValue.toString());
			log.info("Patern "+pattern);
			log.info("Matcher "+matcher);
			String name = submittedValue.toString();
			log.info("submitted value "+name);
			em=EMF.getEM();
			
			ClasseService us=new ClasseService(em);
			if (us.findClasseByName(name) == null) 
			{
				em.close();
				log.info("Classe name not used ");
				log.info(!matcher.matches());
				if (!matcher.matches() || name.length()>45){
					
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Invalid format.",
							"The classeName must contain only letters and numbers and be shorter than 45 letters");

					FacesContext.getCurrentInstance().addMessage("SubmitClasse:classeName", msg);

					throw new ValidatorException(msg);
					
				}
				else{return;}
			}
			else {
				log.info("Classe found found ");
				em.close();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Classe already taken",
													"Chose another classeName ");
				FacesContext.getCurrentInstance().addMessage("SubmitClasse:nameClasse", msg);
					
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