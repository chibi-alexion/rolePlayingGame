package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the achievementofcharacter database table.
 * 
 */
@Entity
@NamedQuery(name="AchievementOfCharacter.findAll", query="SELECT a FROM AchievementOfCharacter a")
public class AchievementOfCharacter implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AchievementOfCharacterPK id;

	private int place;

	//bi-directional many-to-one association to Achievement
	@ManyToOne
	private Achievement achievement;

	//bi-directional many-to-one association to Character
	@ManyToOne
	private Character character;

	public AchievementOfCharacter() {
	}

	public AchievementOfCharacterPK getId() {
		return this.id;
	}

	public void setId(AchievementOfCharacterPK id) {
		this.id = id;
	}

	public int getPlace() {
		return this.place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

}