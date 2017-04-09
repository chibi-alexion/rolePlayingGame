package managedBean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import entities.User;

public class SessionUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(SessionUser.class);


	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute("login").toString();
	}

	public static Integer getUserId() {
		HttpSession session = getSession();
		log.info("getUserId");
		if (session != null){
			log.info("session!=null");
			log.info((Integer)session.getAttribute("idUser"));
			return (Integer) session.getAttribute("idUser");
		}else
			return null;
	}
	public static User getUser(){
		HttpSession session = getSession();

		if (session != null){
			log.info("session!=null");
			log.info("get user "+(User)session.getAttribute("user"));
			return (User) session.getAttribute("user");
		}else
			return null;
	}		
	
}