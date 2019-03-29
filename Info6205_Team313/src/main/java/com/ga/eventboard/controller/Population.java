package com.ga.eventboard.controller;

import java.util.ArrayList;
import java.util.stream.IntStream;

import com.ga.eventboard.data.Data;
/**
*
* @author Raghavi,Neha
*/
public class Population {
	private ArrayList<EventSchedule> schedules;

	public Population(int size, Data data) {
		schedules = new ArrayList<EventSchedule>(size);
		IntStream.range(0, size).forEach(x -> schedules.add(new EventSchedule(data).initialize()));
	}

	public Population(ArrayList<EventSchedule> schedules1, ArrayList<EventSchedule> schedules2) {

		schedules = new ArrayList<EventSchedule>(schedules1.size() + schedules2.size());
		schedules.addAll(schedules1);
		schedules.addAll(schedules2);

	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public ArrayList<EventSchedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(ArrayList<EventSchedule> schedules) {
		this.schedules = schedules;
	}

	/*
	 *  sort function--to order the organisms by their fitness function
	 */
	public Population sortByFitness() {
		schedules.sort((schedule1, schedule2) -> {
			int returnValue = 0;
			if (schedule1.getFitness() > schedule2.getFitness()) {
				returnValue = -1;
			} else if (schedule1.getFitness() < schedule2.getFitness()) {
				returnValue = 1;
			}
			return returnValue;
		});
		return this;
	}

	public boolean equals(Object obj) {
		return (this.schedules.equals(((Population) obj).getSchedules()));
	}
}
