package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the halloffame database table.
 * 
 */
@Entity
@NamedQuery(name="HallOfFame.findAll", query="SELECT h FROM HallOfFame h")
public class HallOfFame implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HallOfFamePK id;

	private int place;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to Character
	@ManyToOne
	private Character character;

	public HallOfFame() {
	}

	public HallOfFamePK getId() {
		return this.id;
	}

	public void setId(HallOfFamePK id) {
		this.id = id;
	}

	public int getPlace() {
		return this.place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

}