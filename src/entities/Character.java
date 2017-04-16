package entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * The persistent class for the character database table.
 * 
 */
@Entity(name="Characters")
@NamedQueries({
@NamedQuery(name="Character.findAll", query="SELECT c FROM Characters c"),
@NamedQuery(name="Character.findAllCharacterDeadByUser", query="SELECT c FROM Characters c where c.user.idUser = :id AND c.hitPointCharacter = 0"),
@NamedQuery(name="Character.findRankingByLevel", query="SELECT c from Characters c ORDER BY c.lvl DESC"),
@NamedQuery(name="Character.findAllAliveByUser", query="SELECT c FROM Characters c where c.user.idUser = :id AND c.hitPointCharacter > 0"),
@NamedQuery(name="Character.findAllAlive", query="SELECT c FROM Characters c where c.hitPointCharacter > 0"),
@NamedQuery(name="Character.findCharacterByID", query="SELECT c FROM Characters c WHERE c.idCharacter = :id"),


})

public class Character implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCharacter;

	private int experience;

	private int gold;

	private int hitPointCharacter;

	private int lvl;
	@Size(min=2, max=45)
	private String nameCharacter;

	private Boolean sexe;

	private Boolean statusCharacter;

	//bi-directional many-to-one association to AchievementOfCharacter
	@OneToMany(mappedBy="character")
	private List<AchievementOfCharacter> achievementofcharacters;

	//bi-directional many-to-one association to Classe
	@ManyToOne
	private Classe classe;

	//bi-directional many-to-one association to Race
	@ManyToOne
	private Race race;

	//bi-directional many-to-one association to UserBean
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to HallOfFame
	@OneToMany(mappedBy="character")
	private List<HallOfFame> halloffames;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="character")
	private List<Inventory> inventories;

	public Character() {
	}

	public int getIdCharacter() {
		return this.idCharacter;
	}

	public void setIdCharacter(int idCharacter) {
		this.idCharacter = idCharacter;
	}

	public int getExperience() {
		return this.experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getGold() {
		return this.gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getHitPointCharacter() {
		return this.hitPointCharacter;
	}

	public void setHitPointCharacter(int hitPointCharacter) {
		this.hitPointCharacter = hitPointCharacter;
	}

	public int getLvl() {
		return this.lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getNameCharacter() {
		return this.nameCharacter;
	}

	public void setNameCharacter(String nameCharacter) {
		this.nameCharacter = nameCharacter;
	}

	public Boolean getSexe() {
		return this.sexe;
	}

	public void setSexe(Boolean sexe) {
		this.sexe = sexe;
	}

	public Boolean getStatusCharacter() {
		return this.statusCharacter;
	}

	public void setStatusCharacter(Boolean statusCharacter) {
		this.statusCharacter = statusCharacter;
	}

	public List<AchievementOfCharacter> getAchievementofcharacters() {
		return this.achievementofcharacters;
	}

	public void setAchievementofcharacters(List<AchievementOfCharacter> achievementofcharacters) {
		this.achievementofcharacters = achievementofcharacters;
	}

	public AchievementOfCharacter addAchievementofcharacter(AchievementOfCharacter achievementofcharacter) {
		getAchievementofcharacters().add(achievementofcharacter);
		achievementofcharacter.setCharacter(this);

		return achievementofcharacter;
	}

	public AchievementOfCharacter removeAchievementofcharacter(AchievementOfCharacter achievementofcharacter) {
		getAchievementofcharacters().remove(achievementofcharacter);
		achievementofcharacter.setCharacter(null);

		return achievementofcharacter;
	}

	public Classe getClasse() {
		return this.classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Race getRace() {
		return this.race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<HallOfFame> getHalloffames() {
		return this.halloffames;
	}

	public void setHalloffames(List<HallOfFame> halloffames) {
		this.halloffames = halloffames;
	}

	public HallOfFame addHalloffame(HallOfFame halloffame) {
		getHalloffames().add(halloffame);
		halloffame.setCharacter(this);

		return halloffame;
	}

	public HallOfFame removeHalloffame(HallOfFame halloffame) {
		getHalloffames().remove(halloffame);
		halloffame.setCharacter(null);

		return halloffame;
	}

	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setCharacter(this);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		inventory.setCharacter(null);

		return inventory;
	}

}