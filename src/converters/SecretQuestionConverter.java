
package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;
import org.apache.log4j.Logger;

import entities.SecretQuestion;
import services.SecretQuestionService;

@Named
public class SecretQuestionConverter implements Converter {
	
	
	private static final Logger	log	= Logger.getLogger(SecretQuestionConverter.class);
	
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if(submittedValue == null || submittedValue.isEmpty()) {
			log.info("submitted value =null");

			return null;
        }
		try {
			
            log.info(submittedValue);
            //log.info("secret question converter");

            //SecretQuestion squser = new SecretQuestion();
            SecretQuestionService sqService = new SecretQuestionService();
			//log.info("converter post init service");
            //log.info(sqService.findSecretQuestionByID(Integer.parseInt(submittedValue)));
            return sqService.findSecretQuestionByID(Integer.parseInt(submittedValue));
			
	    } catch (NumberFormatException e) {
	        throw new ConverterException(new FacesMessage(String.format("%s is not a valid ID", submittedValue)), e);
	    }
	}	

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof SecretQuestion) {
        	
            return String.valueOf(((SecretQuestion) modelValue).getIdSecretQuestion());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid secret question", modelValue)));
        }
    }



}
