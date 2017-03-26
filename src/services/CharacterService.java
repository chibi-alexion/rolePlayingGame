
package services;

import java.util.List;
import java.io.Serializable;
import javax.persistence.*;

import entities.Character;

import connexion.EMF;

/**
 * @author S
 *
 */
public class CharacterService implements Serializable {
	
	private Character character;
		
	private static final long serialVersionUID = 1L;
	protected EntityManager em;
		
	/**
	 * 
	 * @param em (EntityManager)
	 */
	public CharacterService() {
		 this.em = EMF.getEM();
}
	
	public Character getCharacter() {
		return character;
	}

	/**
	 * @param user the character to set
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}
	
	public void characterCreate(Character character) {
		

		
	    em.getTransaction().begin();  		

		em.persist(character);
		
    	em.getTransaction().commit();
        em.close();


		System.out.println("persist ok");

	}
	
public void characterUpdate(Character character) {
		

		
	    em.getTransaction().begin();  		

		em.persist(character);
		
    	em.getTransaction().commit();
        em.close();


		System.out.println("persist ok");

	}
	
	public List<Character> pool(){
		
		
	    try {
	        TypedQuery<Character> query = em.createNamedQuery("Local.findAllCharacterById", Character.class);
	        return query.getResultList();
	      } catch (NoResultException e) {
	        return null;
	      }
}

}
