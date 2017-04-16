/**
 * 
 */
package services;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.apache.log4j.Logger;

import entities.Race;
import entities.Role;

/**
 * @author S
 *
 */
public class RoleService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger	log	= Logger.getLogger(RoleService.class);

	private EntityManager em;
	private Role role;
	private Role roleUser;

	public RoleService(EntityManager em) {
		this.em = em;
	}
	
public Role roleCreate(Role r) {
		
	    em.getTransaction().begin();  		

		em.persist(r);
	    em.getTransaction().commit();  		

		System.out.println("persist ok");

		return r;
	}
	public Role roleUpdate(Role r) {
			
		em.getTransaction().begin();  		

		em.persist(r);
	    em.getTransaction().commit(); 
	    System.out.println("persist ok");
	
			return r;
		}
	
	public List<Role> findAllRole(){
		TypedQuery<Role> query = em.createNamedQuery("Role.findAll", Role.class);		
		log.info(query);
        return query.getResultList();
        }		
	
	
	public Role findRoleByID(int idRole) {
		
		
	    try {
	         Role role = (Role) em.createNamedQuery("Role.findRoleById").setParameter("id", idRole)
	            .getSingleResult();
	         return role;
	      } catch (NoResultException e) {
	    	  System.out.println("erreur");
	        return null;
	      }
}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}



}
