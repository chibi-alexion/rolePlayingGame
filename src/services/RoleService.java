/**
 * 
 */
package services;


import javax.persistence.*;
import entities.Role;

/**
 * @author S
 *
 */
public class RoleService {
	private EntityManager em;
	private Role role;

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
