package entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * The persistent class for the classe database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Classe.findAll", query="SELECT c FROM Classe c ORDER BY c.idClasse"),
@NamedQuery(name="Classe.findClasseById", query="SELECT c FROM Classe c WHERE c.idClasse = :id"),
@NamedQuery(name="Classe.findClasseByName", query="SELECT c FROM Classe c WHERE c.nameClasse = :name"),
})
public class Classe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//convertir en Integer car equals() ne peut ce faire sur un int ... 
	private Integer idClasse;

	private int armor;

	private int hitPointClasse;

	private int intelligence;

	private int magic;

	@Size(min=1, max=45)
	private String nameClasse;

	private int strength;

	//bi-directional many-to-one association to Character
	@OneToMany(mappedBy="classe")
	private List<Character> characters;

	public Classe() {
	}

	public int getIdClasse() {
		return this.idClasse;
	}

	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}

	public int getArmor() {
		return this.armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getHitPointClasse() {
		return this.hitPointClasse;
	}

	public void setHitPointClasse(int hitPointClasse) {
		this.hitPointClasse = hitPointClasse;
	}

	public int getIntelligence() {
		return this.intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getMagic() {
		return this.magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public String getNameClasse() {
		return this.nameClasse;
	}

	public void setNameClasse(String nameClasse) {
		this.nameClasse = nameClasse;
	}

	public int getStrength() {
		return this.strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public List<Character> getCharacters() {
		return this.characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public Character addCharacter(Character character) {
		getCharacters().add(character);
		character.setClasse(this);

		return character;
	}

	public Character removeCharacter(Character character) {
		getCharacters().remove(character);
		character.setClasse(null);

		return character;
	}
	//Implementer cette methode qui va permettre de faire la comparaison
	//entre l'objet selectionné et le contenu de la liste que tu affiche (en tout cas je le suppose)
	@Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && idClasse != null)
            ? idClasse.equals(((Classe) other).idClasse)
            : (other == this);
    }
	

}