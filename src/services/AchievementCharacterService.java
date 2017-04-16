/**
 * 
 */
package services;


import java.io.Serializable;
import java.util.List;

import entities.AchievementOfCharacter;
import entities.Character;
import entities.Race;
import entities.User;

import javax.persistence.*;

import org.apache.log4j.Logger;


/**
 * @author S
 *
 */
public class AchievementCharacterService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final Logger	log	= Logger.getLogger(AchievementCharacterService.class);

	
	private EntityManager em;
	
	public AchievementCharacterService(EntityManager em)
	{
		this.em = em;
	}
	
	public Character AchievCharCreate(Character c) {
		
	    em.getTransaction().begin();  		

		em.persist(c);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return c;
	}
	/*
	public Character AchievCharUpdate(Character c) {
		
		Character ch =  findidCharacterByID(c.getIdCharacter());
		ch.setGold(c.getGold());
		ch.setHitPointCharacter(c.getHitPointCharacter());
		ch.setExperience(c.getExperience());
		em.getTransaction().begin();  		

		
		em.merge(ch);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return ch;
		}*/
	
public List<AchievementOfCharacter> findAchievementByCharacter(Character character){
	
	log.info("findCharacterByName " +character);
	log.info(em);
	
	try{
		TypedQuery<AchievementOfCharacter> query = em.createNamedQuery("AchievementOfCharacter.findAchievementByCharacter", AchievementOfCharacter.class);
		query.setParameter("id", character.getIdCharacter());		
		log.info(query.getResultList());
		List<AchievementOfCharacter> listAchievChar = query.getResultList();
		if(listAchievChar.isEmpty()){
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
