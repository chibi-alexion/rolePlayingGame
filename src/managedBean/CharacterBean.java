package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
/*import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.inject.Inject;*/
import javax.inject.Named;
import javax.persistence.EntityManager;
import connexion.EMF;

import org.apache.log4j.Logger;



import entities.Character;
import entities.Classe;
import services.CharacterService;

/**
 * @author 
 *
 */
@Named
@RequestScoped
public class CharacterBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Log4j
	private static final Logger	log	= Logger.getLogger(UserBean.class);
	
	
	private Character character;
	private Classe classe;
	private List <Classe> listClasse;
	
	
	/**
	 * Default constructor
	 */	
	public CharacterBean() {
		// TODO Auto-generated constructor stub
	}
	

public void submitNewCharacter(){

	Character newCharacter = new Character();
    EntityManager em = EMF.getEM();
    CharacterService service = new CharacterService();

    em.getTransaction().begin(); 
    try{
    
    	service.characterCreate(newCharacter);
    	em.getTransaction().commit();

    	System.out.println("Character created");
    }
    catch(Exception e){
    	
    	log.error(e,e);
		log.info("Charater not created !"); 	
    }
}

public  void update(){
	
	Character CharacterUpdate = new Character();

	EntityManager em = EMF.getEM();
    CharacterService service = new CharacterService();

    em.getTransaction().begin();

    try{
    
    	service.characterUpdate(CharacterUpdate);
        em.getTransaction().commit();


    	System.out.println("Character updated");
    }
    catch(Exception e){
    	
    	log.error(e,e);
		log.info("Charater not updated !"); 	
    }
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
 * @return the classe
 */
public Classe getClasse() {
	return classe;
}


/**
 * @param classe the classe to set
 */
public void setClasse(Classe classe) {
	this.classe = classe;
}


/**
 * @return the listClasse
 */
public List <Classe> getListClasse() {
	return listClasse;
}


/**
 * @param listClasse the listClasse to set
 */
public void setListClasse(List <Classe> listClasse) {
	this.listClasse = listClasse;
}
}


