package com.ga.eventboard.model;
/**
*
* @author Raghavi,Neha
*/
public class EventTiming {
	private String id;
	private String time;

	public EventTiming(String id, String time) {
		this.id = id;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

}
