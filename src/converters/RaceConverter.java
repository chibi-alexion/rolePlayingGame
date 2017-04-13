
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
import entities.Race;
import services.RaceService;

@Named
@RequestScoped
@FacesConverter("raceConverter")
public class RaceConverter implements Converter {
	
	
	private static final Logger	log	= Logger.getLogger(RaceConverter.class);
	
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if(submittedValue == null || submittedValue.isEmpty()) {
			log.info("submitted value =null");

			return null;
        }
		try {
			EntityManager em = EMF.getEM();
            log.info("id submitted Race: " + submittedValue);
            
            RaceService rService = new RaceService(em);
			
            Race race = rService.findRaceById(Integer.parseInt(submittedValue));
            log.debug("Classe after retrieving by id: "+ race.getNameRace());
            
            //************** CLOSE EM ******************
            em.close();
            
            return race;
			
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
        if (modelValue instanceof Race) {
        	log.info("block if model value classe");

            return String.valueOf(((Race) modelValue).getIdRace());
        } else {
        	
        	throw new ConverterException(new FacesMessage(String.format("%s is not a valid Race", modelValue)));
        }
    }
}
