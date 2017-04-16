
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
import entities.Role;
import services.RoleService;

@Named
@ViewScoped
@FacesConverter("roleConverter")
public class RoleConverter implements Converter {
	
	
	private static final Logger	log	= Logger.getLogger(RoleConverter.class);
	
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		log.info("Get Role as object "+submittedValue);
		if(submittedValue == null || submittedValue.isEmpty()) {
			log.info("submitted value =null");

			return null;
        }
		try {
			EntityManager em = EMF.getEM();
            log.info("id submitted Role: " + submittedValue);
            
            RoleService rService = new RoleService(em);
			
            Role ra = rService.findRoleByID(Integer.parseInt(submittedValue));
            log.debug("Role after retrieving by id: "+ ra);
            
            //************** CLOSE EM ******************
            em.close();
            log.info("Role envoyé par le converter "+ra.getNameRole());
            return ra;
			
	    } catch (NumberFormatException e) {
	        throw new ConverterException(new FacesMessage(String.format("%s is not a valid ID", submittedValue)), e);
	    }
	}	

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
       log.info("converter get Role as string");
    	if (modelValue == null) {
            return "";
        }
        if (modelValue instanceof Role) {

            return String.valueOf(((Role) modelValue).getIdRole());
        } else {
        	
        	throw new ConverterException(new FacesMessage(String.format("%s is not a valid Role", modelValue)));
        }
    }
}
