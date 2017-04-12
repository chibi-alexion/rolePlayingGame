
package converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.SecretQuestion;
import services.SecretQuestionService;

@Named
@RequestScoped
@FacesConverter("secretQuestionConverter")
public class SecretQuestionConverter implements Converter {
	
	
	private static final Logger	log	= Logger.getLogger(SecretQuestionConverter.class);
	
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if(submittedValue == null || submittedValue.isEmpty()) {
			log.info("submitted value =null");

			return null;
        }
		try {
			EntityManager em = EMF.getEM();
            log.info("idSecretQuestion: " + submittedValue);
            
            SecretQuestionService sqService = new SecretQuestionService(em);
			
            SecretQuestion sq = sqService.findSecretQuestionByID(Integer.parseInt(submittedValue));
            log.debug("Question after retrieving by id: "+ sq.getQuestion());
            
            //************** CLOSE EM ******************
            em.close();
            
            return sq;
			
	    } catch (NumberFormatException e) {
	        throw new ConverterException(new FacesMessage(String.format("%s is not a valid ID", submittedValue)), e);
	    }
	}	

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
        	log.info("model value null");
            return "";
        }
        log.info(modelValue);
        if (modelValue instanceof SecretQuestion) {
        	log.info("block if model value secret question");

            return String.valueOf(((SecretQuestion) modelValue).getIdSecretQuestion());
        } else {
        	
        	throw new ConverterException(new FacesMessage(String.format("%s is not a valid secret question", modelValue)));
        }
    }



}
