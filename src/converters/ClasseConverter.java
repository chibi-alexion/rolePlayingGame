
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
import entities.Classe;
import services.ClasseService;

@Named
@RequestScoped
@FacesConverter("classeConverter")
public class ClasseConverter implements Converter {
	
	
	private static final Logger	log	= Logger.getLogger(ClasseConverter.class);
	
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if(submittedValue == null || submittedValue.isEmpty()) {
			log.info("submitted value =null");

			return null;
        }
		try {
			EntityManager em = EMF.getEM();
            log.info("id submitted Classe: " + submittedValue);
            
            ClasseService cService = new ClasseService(em);
			
            Classe classe = cService.findClasseById(Integer.parseInt(submittedValue));
            log.debug("Classe after retrieving by id: "+ classe.getNameClasse());
            
            //************** CLOSE EM ******************
            em.close();
            
            return classe;
			
	    } catch (NumberFormatException e) {
	        throw new ConverterException(new FacesMessage(String.format("%s is not a valid ID", submittedValue)), e);
	    }
	}	

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
       log.info("converter get as string");
    	if (modelValue == null) {
        	log.info("model value "+modelValue);
            return "";
        }
        log.info("Model value "+modelValue);
        if (modelValue instanceof Classe) {
        	log.info("block if model value classe");

            return String.valueOf(((Classe) modelValue).getIdClasse());
        } else {
        	
        	throw new ConverterException(new FacesMessage(String.format("%s is not a valid Classe", modelValue)));
        }
    }
}
