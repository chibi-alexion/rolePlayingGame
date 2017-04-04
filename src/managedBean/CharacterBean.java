/**
 * 
 */
package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Character;
import entities.SecretQuestion;
import services.SecretQuestionService;

/**
 * @author S
 *
 */
@Named
@SessionScoped
public class CharacterBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(CharacterBean.class);

	private List <Character> listCharacter;
	private Character Character;
	
	@PostConstruct
	public void init(){
	}
	
	public List<Character> loadUserCharacter(){
		
		//System.out.print("secretquestion bean load secretquestion");
		EntityManager em = EMF.getEM();
		CharacterService cService = new CharacterService(em);
		//System.out.print("secretquestion bean init service");
		listCharacter = cService.findAllCharacterByUser(em);
		//System.out.print("secretquestion bean retour service");
		log.info(listCharacter);
		log.info("Récuperation des entites depuis la db ok");

		return listCharacter;
		
	}

}
