package com.ga.eventboard.model;

import java.util.ArrayList;
/**
*
* @author Raghavi,Neha
*/
public class Title {

	private int maxNumberOfAttendees;
	private ArrayList<Organizer> organizers;
	private String number = null;
	private String name = null;

	public Title(String number, String name, ArrayList<Organizer> organizers, int maxNumberOfAttendees) {
		this.number = number;
		this.name = name;
		this.organizers = organizers;
		this.maxNumberOfAttendees = maxNumberOfAttendees;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public int getMaxNumberOfAttendees() {
		return maxNumberOfAttendees;
	}

	public ArrayList<Organizer> getOrganizers() {
		return organizers;
	}

	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return ((this.name.equalsIgnoreCase(((Title) obj).name)) && (this.number == ((Title) obj).number)
				&& (this.organizers.equals(((Title) obj).getOrganizers())));
	}

}
