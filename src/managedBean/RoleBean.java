package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import connexion.EMF;


import org.apache.log4j.Logger;

import entities.Race;
import entities.Role;
import services.RaceService;
import services.RoleService;


/**
 * @author 
 *
 */
@Named
@RequestScoped
public class RoleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Log4j
	private static final Logger	log	= Logger.getLogger(UserBean.class);
	
	private List <Role> listRole;
	private Role role;
	EntityManager em;

	public RoleBean(){
	}
	
	@PostConstruct
	public void init(){
		role = new Role();
		em = EMF.getEM();
		
		RoleService rService = new RoleService(em);
		listRole = rService.findAllRole();
		log.info(listRole);
		log.info("Récuperation des Race depuis la db");
		for(Role r : listRole)
			log.debug("Role: " + r.getNameRole());
		em.close();
		//raceUpdate=listRace.get(1);
	}
	/*
	public String submitNewSecretQuestion(){

		
		EMF.getEMF();
		EntityManager em = EMF.getEM();
	    RoleService service = new RoleService(em);

	    em.getTransaction().begin();  		
	    try{
	    
	    	service.roleCreate(role);
	    	em.getTransaction().commit();

	    	System.out.println("Role created");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Role not created !"); 	
	    }
	    
	    return "";
	}
	
	public String secretQuestionUpdate(){

		
		EMF.getEMF();
		EntityManager em = EMF.getEM();
	    RoleService service = new RoleService(em);

	    em.getTransaction().begin();  		
	    try{
	    
	    	service.roleUpdate(role);
	    	em.getTransaction().commit();

	    	System.out.println("Role updated");
	    }
	    catch(Exception e){
	    	log.error(e,e);
			log.info("Role not updated !"); 	
	    }
	    
	    return "";
	}*/

	/**
	 * @return the listRole
	 */
	public List <Role> getListRole() {
		return listRole;
	}

	/**
	 * @param listRole the listRole to set
	 */
	public void setListRole(List <Role> listRole) {
		this.listRole = listRole;
	}
	
}
