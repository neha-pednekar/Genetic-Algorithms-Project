package com.ga.eventboard.model;

import java.util.ArrayList;
/**
*
* @author Raghavi,Neha
*/
public class Organization {

	private String name;
	private ArrayList<Title> title;

	public Organization(String name, ArrayList<Title> title) {
		this.name = name;
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Title> getTitle() {
		return title;
	}

	@Override
	public boolean equals(Object obj) {
		return ((this.name == null ? ((Organization) obj).name == null : this.name.equals(((Organization) obj).name))
				&& (this.title.equals(((Organization) obj).getTitle())));
	}
}
