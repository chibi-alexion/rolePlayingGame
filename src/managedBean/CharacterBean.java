/**
 * 
 */
package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Character;
import entities.Role;
import entities.User;
import services.CharacterService;
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
	private Character character;
	private User userSession;
	EntityManager em;
	
	@PostConstruct
	public void init(){
		
		character= new Character();
		/*
		EntityManager em = EMF.getEM();
		//log.info(SessionUser.getUser());
		int idUser =SessionUser.getUserId();
		//log.info("User char bean "+idUser);
		
		//UserService uservice= new UserService(em);
		//User u = uservice.findUserById(idUser);
		//log.info("User from userServicev " +u.getLogin());
		//OK user
		log.info("Id de l'utilisateur session "+idUser);
		CharacterService cService = new CharacterService(em);
		character= cService.characterTest(idUser);
		log.info(character);
		character=cService.findCharacterAlive(idUser);
		log.info(character);
		listCharacter = cService.findAllCharacterDeadByUser(idUser); 

		log.info(listCharacter);
		log.info("Récuperation des entites depuis la db ok");

		for(Character c : listCharacter)
		log.debug("Personnage: " + c.getNameCharacter());
		em.close();*/
	}
	
	public String submitNewCharacter(){

		em=EMF.getEM();
	    CharacterService service = new CharacterService(em);
	    log.info(em);

	    try{
	    	
	    	service.characterCreate(character);

	    	System.out.println("Character created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Character not created !"); 	
	    }
	    return "home";
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

	/**
	 * @return the charList
	 */
	public List<Character> getListCharacter() {
		return listCharacter;
	}

	/**
	 * @param charList the charList to set
	 */
	public void setListCharacter(List<Character> listCharacter) {
		this.listCharacter = listCharacter;
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

	
	

}
