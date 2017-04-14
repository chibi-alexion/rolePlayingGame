
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
import entities.Race;
import services.RaceService;

@Named
@ViewScoped
@FacesConverter("raceConverter")
public class RaceConverter implements Converter {
	
	
	private static final Logger	log	= Logger.getLogger(RaceConverter.class);
	
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		log.info("Get Race as object "+submittedValue);
		if(submittedValue == null || submittedValue.isEmpty()) {
			log.info("submitted value =null");

			return null;
        }
		try {
			EntityManager em = EMF.getEM();
            log.info("id submitted Race: " + submittedValue);
            
            RaceService rService = new RaceService(em);
			
            Race ra = rService.findRaceById(Integer.parseInt(submittedValue));
            log.debug("Race after retrieving by id: "+ ra);
            
            //************** CLOSE EM ******************
            em.close();
            log.info("Race envoyéparle converter "+ra.getNameRace());
            return ra;
			
	    } catch (NumberFormatException e) {
	        throw new ConverterException(new FacesMessage(String.format("%s is not a valid ID", submittedValue)), e);
	    }
	}	

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
       log.info("converter get as string");
    	if (modelValue == null) {
            return "";
        }
        if (modelValue instanceof Race) {

            return String.valueOf(((Race) modelValue).getIdRace());
        } else {
        	
        	throw new ConverterException(new FacesMessage(String.format("%s is not a valid Race", modelValue)));
        }
    }
}
