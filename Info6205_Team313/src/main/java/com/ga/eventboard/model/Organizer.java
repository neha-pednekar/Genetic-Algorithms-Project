package com.ga.eventboard.model;
/**
*
* @author Raghavi,Neha
*/
public class Organizer {
	private String id;
	private String name;

	public Organizer(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return ((this.id.equalsIgnoreCase(((Organizer) obj).id))
				&& (this.name.equalsIgnoreCase(((Organizer) obj).name)));
	}

}
