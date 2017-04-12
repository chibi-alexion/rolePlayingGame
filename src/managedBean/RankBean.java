/**
 * 
 */
package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;

import org.apache.log4j.Logger;

import connexion.EMF;
import entities.Category;
import entities.Character;
import entities.HallOfFame;
import services.RankService;

/**
 * @author S
 *
 */
@Named
@RequestScoped
public class RankBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(RankBean.class);
	 
	List<Character> listingRank;
	List<Character> listCharacter;
	EntityManager em;

	public RankBean() {
        System.out.println("Constructor is called...");
	}	
	
	@PostConstruct
	public void init() {

		em=EMF.getEM();
		RankService rankLvl = new RankService(em);
		listingRank = rankLvl.rankByLvl();
		log.info(listingRank);
		em.close();
	}

	/**
	 * @return the listingRank
	 */
	public List<Character> getListingRank() {
		return listingRank;
	}

	/**
	 * @param listingRank the listingRank to set
	 */
	public void setListingRank(List<Character> listingRank) {
		this.listingRank = listingRank;
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
	


	
	
}
