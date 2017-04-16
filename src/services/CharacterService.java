/**
 * 
 */
package services;


import java.io.Serializable;
import java.util.List;
import entities.Character;
import entities.Race;
import entities.User;

import javax.persistence.*;

import org.apache.log4j.Logger;


/**
 * @author S
 *
 */
public class CharacterService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final Logger	log	= Logger.getLogger(CharacterService.class);

	
	private EntityManager em;
	
	public CharacterService(EntityManager em)
	{
		this.em = em;
	}
	
	public Character characterCreate(Character c) {
		
	    em.getTransaction().begin();  		

		em.persist(c);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return c;
	}
	public Character characterUpdate(Character c) {
		
		Character ch =  findidCharacterByID(c.getIdCharacter());
		ch.setGold(c.getGold());
		ch.setHitPointCharacter(c.getHitPointCharacter());
		ch.setExperience(c.getExperience());
		em.getTransaction().begin();  		

		
		em.merge(ch);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return ch;
		}
	
	public List<Character> findCharacterAllAlive (){
	    try {
			
	        TypedQuery<Character> query = em.createNamedQuery("Character.findAllAlive", Character.class);
	        return query.getResultList();

	      } catch (NoResultException e) {
	    	  
	    	  System.out.print(" no character alive found");

	        return null;
	      }
	}
	public Character findCharacterAlive (int idUser){
	    try {
			log.info(idUser);
			
			Character user = (Character) em.createNamedQuery("Character.findAllAliveByUser").setParameter("id", idUser)
		            .getSingleResult();
	        //TypedQuery<Character> query = em.createNamedQuery("Character.findAllAliveByUser", Character.class);
	        //query.setParameter("id", idUser);
	        log.info(user);
	        return user;

	      } catch (NoResultException e) {
	    	  
	    	  log.info("no character alive found");
	        return null;
	      }
	}
	
public Character findidCharacterByID(int idCharacter) {
		
		
	    try {
	    	log.info(idCharacter);
	         
	    	Character character = (Character) em.createNamedQuery("Character.findCharacterByID").setParameter("id", idCharacter)
	            .getSingleResult();
	         return character;
	      } catch (NoResultException e) {
	    	  log.info("no character found");
	        return null;
	      }
}
public List<Character> findAllCharacterDeadByUser(int idUser) {
	
	
    try {
    	log.info("findAllCharacterByUser");
    	log.info("Id utilisateur "+idUser);
    	
    	TypedQuery<Character> query = em.createNamedQuery("Character.findAllCharacterDeadByUser", Character.class);
    	query.setParameter("id", idUser);
    	log.info(query);
    	log.info(query.getResultList());
    	
    	return query.getResultList(); 
    	
      } catch (NoResultException e) {
    	  System.out.println("erreur");
        return null;
      }
}

public Character characterTest(int idUser) {
	
	log.info("characterTest find all");
	
	Character userChar = (Character) em.createNamedQuery("Character.findAllAliveByUser").setParameter("id", idUser)
            .getSingleResult();
		log.info(userChar);
		
         return userChar;
	
}

public List<Character> findCharacterByName(String characterName){
	
	log.info("findCharacterByName " +characterName);
	log.info(em);
	
	try{
		TypedQuery<Character> query = em.createNamedQuery("Character.findCharacterByName", Character.class);
		query.setParameter("login", characterName);		
		log.info("query "+query);
		log.info(query.getResultList());
		List<Character> list=query.getResultList();
		if(list.isEmpty()){
			return null;
	    }
		else{
			return query.getResultList();
		}
    	}
	catch (NoResultException e) {
    	  log.info("character error service");
        return null;
      }
}
}
