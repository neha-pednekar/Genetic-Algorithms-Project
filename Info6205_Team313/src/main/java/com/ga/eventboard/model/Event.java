package com.ga.eventboard.model;
/**
*
* @author Raghavi,Neha
*/
public class Event {
	private int id;
	private Organization org;
	private Title title;
	private Organizer organizer;
	private EventTiming eventTime;
	private Rooms room;

	public Event(int id, Organization org, Title title) {
		this.id = id;
		this.org = org;
		this.title = title;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	public EventTiming getEventTime() {
		return eventTime;
	}

	public void setEventTime(EventTiming eventTime) {
		this.eventTime = eventTime;
	}

	public Rooms getRoom() {
		return room;
	}

	public void setRoom(Rooms room) {
		this.room = room;
	}
	
	@Override
    public String toString() {
        return "[" + org.getName() + "|" + title.getNumber() + "|" + 
        		     organizer.getId() + "|" + eventTime.getId() + "|" + room.getNumber() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return ((this.id == ((Event) obj).id) && (this.org.equals(((Event) obj).getOrg())) && (this.title.equals(((Event) obj).getTitle())));
    }

}
