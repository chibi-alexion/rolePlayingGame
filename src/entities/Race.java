package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the race database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Race.findAll", query="SELECT r FROM Race r ORDER BY r.idRace"),
@NamedQuery(name="Race.findRaceById", query="SELECT r FROM Race r WHERE r.idRace = :id"),
@NamedQuery(name="Race.findRaceByName", query="SELECT r FROM Race r WHERE r.nameRace = :name"),

})
public class Race implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idRace;

	private String nameRace;

	//bi-directional many-to-one association to Character
	@OneToMany(mappedBy="race")
	private List<Character> characters;

	public Race() {
	}

	public int getIdRace() {
		return this.idRace;
	}

	public void setIdRace(int idRace) {
		this.idRace = idRace;
	}

	public String getNameRace() {
		return this.nameRace;
	}

	public void setNameRace(String nameRace) {
		this.nameRace = nameRace;
	}

	public List<Character> getCharacters() {
		return this.characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public Character addCharacter(Character character) {
		getCharacters().add(character);
		character.setRace(this);

		return character;
	}

	public Character removeCharacter(Character character) {
		getCharacters().remove(character);
		character.setRace(null);

		return character;
	}
	@Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idRace != null)
            ? idRace.equals(((Race) other).idRace)
            : (other == this);
    }

}