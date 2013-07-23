package org.kiyuko.playground;

public class Item {

	public static final long INVALID_ID = -1;

	private long id;
	private String name;
	private String description;

	public Item() {

		this.id = INVALID_ID;
		this.name = "";
		this.description = "";
	}

	public Item(long id, String name, String description) {

		this.id = id;
		this.name = name;
		this.description = description;
	}

	public long getId() {

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
