package org.kiyuko.playground;

public class Item {

	private String name;
	private String description;

	public Item() {
		this.name = "";
	}

	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return this.name + " (" + this.description + ")";
	}
}
