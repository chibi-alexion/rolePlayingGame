
package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.SecretQuestion;
import services.SecretQuestionService;

@Named
public class SecretQuestionConverter implements Converter {

	@PersistenceContext(unitName = "rolePlayingGame")
    // I include this because you will need to 
    // lookup  your entities based on submitted values
    private transient EntityManager em;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        /*if(submittedValue == null || submittedValue.isEmpty()) {
            System.out.println("converter null");
        	return null;
        }*/
		try {
            System.out.println("converter pre init service");

			//SecretQuestionService sqService = new SecretQuestionService();
            System.out.println("converter post init service");
            return em.find(SecretQuestion.class, Integer.parseInt(submittedValue)); 
			//	return sqService.findSecretQuestionByID(Integer.parseInt(submittedValue));
			
	    } catch (NumberFormatException e) {
	        throw new ConverterException(new FacesMessage(String.format("%s is not a valid User ID", submittedValue)), e);
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
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid TypeLocal", modelValue)));
        }
    }



}
