/**
 * 
 */
package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.*;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.AchievementOfCharacter;
import entities.Character;
import entities.Classe;
import entities.SecretQuestion;
import services.AchievementCharacterService;
import services.CharacterService;
import services.ClasseService;
import services.SecretQuestionService;
import services.UserService;


/**
 * @author S
 *
 */
@Named
@RequestScoped
public class AchievementCharacterBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(ClasseBean.class);

	private List <AchievementOfCharacter> listAchievementCharacter; 
	private Character characterActive;
	private AchievementOfCharacter achievement;
	private EntityManager em;

	public AchievementCharacterBean(){
	}
	
	@PostConstruct
	public void init(){
		
		em = EMF.getEM();
		CharacterService cService = new CharacterService(em);
		characterActive=cService.findCharacterAlive(SessionUser.getUserId());

		if(characterActive == null){
			return;
		}
		AchievementCharacterService acService = new AchievementCharacterService(em);
		listAchievementCharacter=acService.findAchievementByCharacter(characterActive);
		log.info("Récuperation des Achievement du personnage depuis la db");
		for(AchievementOfCharacter c : listAchievementCharacter)
			log.debug("Classe: " + c.getAchievement().getNameAchievement());
		em.close();
		//classeUpdate=listClasse.get(1);
	}
	/*
public String submitNewClasse(){

		em = EMF.getEM();

		

	    ClasseService service = new ClasseService(em);
	    try{
	    
	    	service.classeCreate(classeCreate);
	    	FacesContext.getCurrentInstance().addMessage(
					"SubmitCharacter",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Classe Created",
							"Please enter correct username and Password"));
	    	em.close();
	    	System.out.println("Classe created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Classe not created !"); 	
	    }
	    
	    return "";
	}

public String classUpdate(){

	
	em = EMF.getEM();
    ClasseService service = new ClasseService(em);

    try{
    	service.classeUpdate(classeUpdate);
    	em.close();
    	System.out.println("Classe upated");
    }
    catch(Exception e){
    	log.error(e,e);
		log.info("Classe not upated !"); 	
    }
    init();
    return "";
}*/

	/**
	 * @return the listAchievementCharacter
	 */
	public List<AchievementOfCharacter> getListAchievementCharacter() {
		return listAchievementCharacter;
	}

	/**
	 * @param listAchievementCharacter the listAchievementCharacter to set
	 */
	public void setListAchievementCharacter(List<AchievementOfCharacter> listAchievementCharacter) {
		this.listAchievementCharacter = listAchievementCharacter;
	}

	/**
	 * @return the characterActive
	 */
	public Character getCharacterActive() {
		return characterActive;
	}

	/**
	 * @param characterActive the characterActive to set
	 */
	public void setCharacterActive(Character characterActive) {
		this.characterActive = characterActive;
	}

	/**
	 * @return the achievement
	 */
	public AchievementOfCharacter getAchievement() {
		return achievement;
	}

	/**
	 * @param achievement the achievement to set
	 */
	public void setAchievement(AchievementOfCharacter achievement) {
		this.achievement = achievement;
	}
	
	
	





}
