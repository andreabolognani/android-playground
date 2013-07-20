package org.kiyuko.playground;

public class Item {

	private int id;
	private String name;
	private String description;

	public Item() {

		this.id = -1;
		this.name = "";
		this.description = "";
	}

	public Item(int id, String name, String description) {

		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {

		return id;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getDescription() {

		return description;
	}

	@Override
	public String toString() {

		return "(" + id + ", " + name + ", " + description + ")";
	}
}
