package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCategory;

	private String nameCategory;

	//bi-directional many-to-one association to HallOfFame
	@OneToMany(mappedBy="category")
	private List<HallOfFame> halloffames;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="category")
	private List<Item> items;

	public Category() {
	}

	public int getIdCategory() {
		return this.idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getNameCategory() {
		return this.nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public List<HallOfFame> getHalloffames() {
		return this.halloffames;
	}

	public void setHalloffames(List<HallOfFame> halloffames) {
		this.halloffames = halloffames;
	}

	public HallOfFame addHalloffame(HallOfFame halloffame) {
		getHalloffames().add(halloffame);
		halloffame.setCategory(this);

		return halloffame;
	}

	public HallOfFame removeHalloffame(HallOfFame halloffame) {
		getHalloffames().remove(halloffame);
		halloffame.setCategory(null);

		return halloffame;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setCategory(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setCategory(null);

		return item;
	}

}