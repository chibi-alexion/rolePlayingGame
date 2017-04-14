
package converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
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
import managedBean.ClasseBean;
import services.ClasseService;

@Named
@ViewScoped
@FacesConverter("classeConverter")
public class ClasseConverter implements Converter {
	
	
	private static final Logger	log	= Logger.getLogger(ClasseConverter.class);
	
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		
		log.info("get classe as object "+submittedValue);
		
		if(submittedValue == null || submittedValue.isEmpty()) {
			log.info("submitted value =null");

			return null;
        }
		try {
			EntityManager em = EMF.getEM();
            log.info("id submitted Classe: " + submittedValue);
            
            ClasseService cService = new ClasseService(em);
			
            Classe cl = cService.findClasseById(Integer.parseInt(submittedValue));
            log.debug("Classe after retrieving by id: "+ cl.getNameClasse());
            //************** CLOSE EM ******************
            em.close();
            log.info("Classe envoyé par le converter "+cl.getNameClasse());
            return cl;
			
	    } catch (NumberFormatException e) {
	        throw new ConverterException(new FacesMessage(String.format("%s is not a valid ID", submittedValue)), e);
	    }
	}	

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
       log.info("converter get Race as string");
    	if (modelValue == null) {
            return "";
        }
        if (modelValue instanceof Classe) {
            return String.valueOf(((Classe) modelValue).getIdClasse());
        } else {
        	throw new ConverterException(new FacesMessage(String.format("%s is not a valid Classe", modelValue)));
        }
    }
}