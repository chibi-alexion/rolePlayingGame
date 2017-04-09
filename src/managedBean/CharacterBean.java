/**
 * 
 */
package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Character;
import entities.User;
import services.CharacterService;
import services.UserService;

/**
 * @author S
 *
 */
@Named
@SessionScoped
public class CharacterBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(CharacterBean.class);

	private List<Character> listCharacter;
	private Character Character;
	private User userSession;
	
	@PostConstruct
	public void init(){
		EntityManager em = EMF.getEM();
		//log.info(SessionUser.getUser());
		int idUser =SessionUser.getUserId();
		//log.info("User char bean "+idUser);
		
		UserService uservice= new UserService(em);
		User u = uservice.findUserById(idUser);
		//log.info("User from userServicev " +u.getLogin());
		//OK user
		
		CharacterService cService = new CharacterService(em);
		listCharacter = cService.findAllCharacterByUser(u); 

		log.info(listCharacter);
		log.info("Récuperation des entites depuis la db ok");

		for(Character c : listCharacter)
		log.debug("Question: " + c.getNameCharacter());
		em.close();
	}

	/**
	 * @return the character
	 */
	public Character getCharacter() {
		return Character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter(Character character) {
		Character = character;
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

	
	

}
