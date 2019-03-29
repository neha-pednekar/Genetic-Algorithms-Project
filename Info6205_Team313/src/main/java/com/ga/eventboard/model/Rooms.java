package com.ga.eventboard.model;
/**
*
* @author Raghavi,Neha
*/
public class Rooms {

	private String number;
	private int seatCapacity;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getSeatCapacity() {
		return seatCapacity;
	}

	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	public Rooms(String number, int seatCapacity) {
		this.number = number;
		this.seatCapacity = seatCapacity;
	}

}
