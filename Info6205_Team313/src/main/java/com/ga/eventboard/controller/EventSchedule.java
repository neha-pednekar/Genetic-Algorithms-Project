package com.ga.eventboard.controller;

import java.util.ArrayList;

import com.ga.eventboard.data.Data;
import com.ga.eventboard.model.Event;
import com.ga.eventboard.model.Organization;
/**
*
* @author Raghavi,Neha
*/
public class EventSchedule {
	private boolean isFitnessChanged = true;
	private double fitness = -1;
	private int eventNum = 0;
	private Data data;
	private ArrayList<Event> events;
	private int numOfConflicts = 0;

	public Data getData() {
		return data;
	}

	public EventSchedule(Data data) {
		this.data = data;
		events = new ArrayList<Event>(data.getNumberOfClasses());
	}

	public EventSchedule initialize() {
		new ArrayList<Organization>(data.getOrganizations()).forEach(org -> {
			org.getTitle().forEach(evtTitle -> {
				Event newEvent = new Event(eventNum++, org, evtTitle);
				newEvent.setEventTime(
						data.getEventTimings().get((int) (data.getEventTimings().size() * Math.random())));
				newEvent.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
				newEvent.setOrganizer(
						evtTitle.getOrganizers().get((int) (evtTitle.getOrganizers().size() * Math.random())));
				events.add(newEvent);
			});
		});
		return this;
	}

	public int getNumbOfConflicts() {
		return numOfConflicts;
	}

	public ArrayList<Event> getClasses() {
		isFitnessChanged = true;
		return events;
	}

	public double getFitness() {
		if (isFitnessChanged == true) {
			fitness = calculateFitness();
			isFitnessChanged = false;
		}
		return fitness;
	}

	/*
	 * a fitness function--this is essentially a measure of how good a candidate
	 * (organism) solution is for the problem you have chosen to solve
	 */

	private double calculateFitness() {
		numOfConflicts = 0;
		events.forEach(x -> {
			if (x.getRoom().getSeatCapacity() < x.getTitle().getMaxNumberOfAttendees()) {
				numOfConflicts++;
			}
			events.stream().filter(y -> events.indexOf(y) >= events.indexOf(x)).forEach(y -> {
				if (x.getEventTime() == y.getEventTime() && x.getId() != y.getId()) {
					if (x.getRoom() == y.getRoom()) {
						numOfConflicts++;
					}
					if (x.getOrganizer() == y.getOrganizer()) {
						numOfConflicts++;
					}
				}
			});
		});
		return 1 / (double) (numOfConflicts + 1);
	}

	@Override
	public boolean equals(Object obj) {
		return ((this.events.equals(((EventSchedule) obj).getClasses())));
	}

	public String toString() {
		String returnVal = new String();
		for (int x = 0; x < events.size() - 1; x++) {
			returnVal += events.get(x) + ",";
		}
		returnVal += events.get(events.size() - 1);
		return returnVal;
	}

}
