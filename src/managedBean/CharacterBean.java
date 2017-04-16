/**
 * 
 */
package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Achievement;
import entities.Character;
import entities.Classe;
import entities.Race;
import entities.Role;
import entities.User;
import services.CharacterService;
import services.RaceService;
import services.RankService;
import services.UserService;

/**
 * @author S
 *
 */
@Named
@RequestScoped
public class CharacterBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(CharacterBean.class);

	private List<Character> listCharacter;
	private List<Character> listCharacterUpdate;
	private Character characterNew;
	private Character character;
	private Character charToUpdate;
	private Achievement achievementCharacter;
	private User userSession;
	EntityManager em;
	
	@PostConstruct
	public void init(){
		
		character= new Character();
		characterNew= new Character();
		charToUpdate = new Character();
		
		EntityManager em = EMF.getEM();
		//log.info(SessionUser.getUser());
		//int idUser =;
		//log.info("User char bean "+idUser);
		//CharacterService cService =new CharacterService(em);
		//UserService uservice= new UserService(em);
		//User u = uservice.findUserById(idUser);
		//log.info("User from userServicev " +u.getLogin());
		//OK user
		//log.info("Id de l'utilisateur session "+idUser);
		CharacterService cService = new CharacterService(em);
		character=cService.findCharacterAlive(SessionUser.getUserId());
		log.info("///////////////////////////////Character active////");
		log.info(character.getIdCharacter());
		listCharacterUpdate=cService.findCharacterAllAlive();
		for(Character c : listCharacterUpdate)
			log.debug("Personnage à mettre  a jour: " + c.getNameCharacter());
		charToUpdate=listCharacterUpdate.get(1);
		//log.info("Perso actif "+character);
		listCharacter = cService.findAllCharacterDeadByUser(SessionUser.getUserId()); 
		//log.info(listCharacter);
		//log.info(listCharacter);
		log.info("Récuperation des entites depuis la db ok");

		//for(Character c : listCharacter)
		//log.debug("Personnage: " + c.getNameCharacter());
		em.close();
	}
	
	public String submitNewCharacter(){


		log.info("Name character "+character.getNameCharacter());

		em=EMF.getEM();
	    CharacterService service = new CharacterService(em);
	    UserService uservice = new UserService(em);
	    User user = uservice.findUserById(SessionUser.getUserId());
	    characterNew.setUser(user);
	    characterNew.setHitPointCharacter(characterNew.getClasse().getHitPointClasse());
	    Character oldCharacter =service.findCharacterAlive(user.getIdUser());
	    try{
	    	if(oldCharacter != null){
	    		
	    		oldCharacter.setHitPointCharacter(0);
	    		service.characterUpdate(oldCharacter);
	    	}
	    	
	    	service.characterCreate(characterNew);
	    	FacesContext.getCurrentInstance().addMessage(
					"SubmitCharacter",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Character Created",
							"Please enter correct username and Password"));
	    	log.info("Character created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Character not created !"); 	
	    }
	    return "home";
	}

	public String updateCharacter(){

		log.info("Name of perso to update "+charToUpdate.getNameCharacter());
		//log.info(achievementCharacter.getNameAchievement());
		em = EMF.getEM();
	    CharacterService rservice = new CharacterService(em);

	    try{
	    	rservice.characterUpdate(charToUpdate);
	    	em.close();
	    	System.out.println("Character upated");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Character not upated !"); 	
	    }
	    init();
	    return "";
	}

	/**
	 * @return the listCharacter
	 */
	public List<Character> getListCharacter() {
		return listCharacter;
	}

	/**
	 * @param listCharacter the listCharacter to set
	 */
	public void setListCharacter(List<Character> listCharacter) {
		this.listCharacter = listCharacter;
	}

	/**
	 * @return the listCharacterUpdate
	 */
	public List<Character> getListCharacterUpdate() {
		return listCharacterUpdate;
	}

	/**
	 * @param listCharacterUpdate the listCharacterUpdate to set
	 */
	public void setListCharacterUpdate(List<Character> listCharacterUpdate) {
		this.listCharacterUpdate = listCharacterUpdate;
	}

	/**
	 * @return the character
	 */
	public Character getCharacter() {
		return character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}
	
	/**
	 * @return the characterNew
	 */
	public Character getCharacterNew() {
		return characterNew;
	}

	/**
	 * @param characterNew the characterNew to set
	 */
	public void setCharacterNew(Character characterNew) {
		this.characterNew = characterNew;
	}

	/**
	 * @return the charToUpdate
	 */
	public Character getCharToUpdate() {
		return charToUpdate;
	}

	/**
	 * @param charToUpdate the charToUpdate to set
	 */
	public void setCharToUpdate(Character charToUpdate) {
		this.charToUpdate = charToUpdate;
	}

	/**
	 * @return the achievementCharacter
	 */
	public Achievement getAchievementCharacter() {
		return achievementCharacter;
	}

	/**
	 * @param achievementCharacter the achievementCharacter to set
	 */
	public void setAchievementCharacter(Achievement achievementCharacter) {
		this.achievementCharacter = achievementCharacter;
	}

	/**
	 * @return the userSession
	 */
	public User getUserSession() {
		return userSession;
	}

	/**
	 * @param userSession the userSession to set
	 */
	public void setUserSession(User userSession) {
		this.userSession = userSession;
	}


}
