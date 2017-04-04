package entities;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the secretquestion database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="SecretQuestion.findAll", query="SELECT s FROM SecretQuestion s"),
@NamedQuery(name="SecretQuestion.findAllById", query="SELECT s FROM SecretQuestion s where s.idSecretQuestion = :id"),
})
public class SecretQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idSecretQuestion;

	private String question;

	//bi-directional many-to-one association to UserBean
	@OneToMany(mappedBy="secretquestion")
	private List<User> users;

	public SecretQuestion() {
	}

	public int getIdSecretQuestion() {
		return this.idSecretQuestion;
	}

	public void setIdSecretQuestion(int idSecretQuestion) {
		this.idSecretQuestion = idSecretQuestion;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setSecretquestion(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setSecretquestion(null);

		return user;
	}
	
	@Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idSecretQuestion != null)
            ? idSecretQuestion.equals(((SecretQuestion) other).idSecretQuestion)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (idSecretQuestion != null) 
            ? (getClass().hashCode() + idSecretQuestion.hashCode())
            : super.hashCode();
    }

}