package com.ga.eventboard.data;

import java.util.ArrayList;
import java.util.Arrays;

import com.ga.eventboard.model.EventTiming;
import com.ga.eventboard.model.Organization;
import com.ga.eventboard.model.Organizer;
import com.ga.eventboard.model.Rooms;
import com.ga.eventboard.model.Title;
/**
*
* @author Raghavi,Neha
*/
public class Data {
	private ArrayList<Rooms> rooms;
	private ArrayList<Organizer> organizer;
	private ArrayList<Title> eventTitle;
	private ArrayList<Organization> organizations;
	private ArrayList<EventTiming> eventTimings;
	private int numberOfClasses = 0;

	public Data() {
		initialize();
	}
	/*
	 * Populate DATA
	 */

	private Data initialize() {
		Rooms room1 = new Rooms("Room1", 25);
		Rooms room2 = new Rooms("Room2", 45);
		Rooms room3 = new Rooms("Room3", 35);
		Rooms room4 = new Rooms("Room3", 10);
		rooms = new ArrayList<Rooms>(Arrays.asList(room1, room2, room3, room4));

		EventTiming eventTiming1 = new EventTiming("ET1", "MWF 09:00 - 10:00");
		EventTiming eventTiming2 = new EventTiming("ET2", "MTF 11:00 - 12:00");
		EventTiming eventTiming3 = new EventTiming("ET3", "TTH 03:00 - 04:30");
		EventTiming eventTiming4 = new EventTiming("ET4", "TTH 10:30 - 1:00");
		eventTimings = new ArrayList<EventTiming>(
				Arrays.asList(eventTiming1, eventTiming2, eventTiming3, eventTiming4));

		Organizer organizer1 = new Organizer("O1", "Mr. Kevin ");
		Organizer organizer2 = new Organizer("O2", "Mr. Robert Pattinson");
		Organizer organizer3 = new Organizer("O3", "Mr. Keanu Reeves ");
		Organizer organizer4 = new Organizer("O4", "Mr. Stefan Salvatore  ");
		organizer = new ArrayList<Organizer>(Arrays.asList(organizer1, organizer2, organizer3, organizer4));

		Title eventTitle1 = new Title("Title1", "32E", new ArrayList<Organizer>(Arrays.asList(organizer1, organizer2)),
				25);
		Title eventTitle2 = new Title("Title2", "49E",
				new ArrayList<Organizer>(Arrays.asList(organizer1, organizer2, organizer3)), 35);
		Title eventTitle3 = new Title("Title3", "26E", new ArrayList<Organizer>(Arrays.asList(organizer1, organizer2)),
				25);
		Title eventTitle4 = new Title("Title4", "64E", new ArrayList<Organizer>(Arrays.asList(organizer3, organizer4)),
				30);
		Title eventTitle5 = new Title("Title5", "60E", new ArrayList<Organizer>(Arrays.asList(organizer4)), 35);
		Title eventTitle6 = new Title("Title6", "33E", new ArrayList<Organizer>(Arrays.asList(organizer1, organizer3)),
				45);
		Title eventTitle7 = new Title("Title7", "83E", new ArrayList<Organizer>(Arrays.asList(organizer2, organizer4)),
				45);
		eventTitle = new ArrayList<Title>(Arrays.asList(eventTitle1, eventTitle2, eventTitle3, eventTitle4, eventTitle5,
				eventTitle6, eventTitle7));

		Organization dept1 = new Organization("ORG1", new ArrayList<Title>(Arrays.asList(eventTitle1, eventTitle3)));
		Organization dept2 = new Organization("ORG2",
				new ArrayList<Title>(Arrays.asList(eventTitle2, eventTitle4, eventTitle5)));
		Organization dept3 = new Organization("ORG3", new ArrayList<Title>(Arrays.asList(eventTitle6, eventTitle7)));
		organizations = new ArrayList<Organization>(Arrays.asList(dept1, dept2, dept3));
		organizations.forEach(x -> numberOfClasses += x.getTitle().size());
		return this;
	}

	public ArrayList<Rooms> getRooms() {
		return rooms;
	}

	public ArrayList<Organizer> getOrganizers() {
		return organizer;
	}

	public ArrayList<Title> getEventTitles() {
		return eventTitle;
	}

	public ArrayList<Organization> getOrganizations() {
		return organizations;
	}

	public ArrayList<EventTiming> getEventTimings() {
		return eventTimings;
	}

	public int getNumberOfClasses() {
		return this.numberOfClasses;
	}
}
