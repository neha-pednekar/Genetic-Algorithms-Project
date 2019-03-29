package com.ga.eventboard.main;

import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.ga.eventboard.controller.EventSchedule;
import com.ga.eventboard.controller.GeneticAlgorithm;
import com.ga.eventboard.controller.Population;
import com.ga.eventboard.data.Data;
import com.ga.eventboard.model.Event;
import com.ga.eventboard.utilities.*;
/**
*
* @author Raghavi,Neha
*/
public class Main {

	public static final int POPULATION_SIZE = 20;
	public static final double MUTATION_RATE = 0.4;
	public static final double CROSSOVER_RATE = 0.1;
	public static final int TOURNAMENT_SELECTION_SIZE = 3;
	public static final int NUMB_OF_ELITE_SCHEDULES = 1;
	public static final int CUT_OFF = 10;

	private int scheduleNum = 0;
	private int eventNum = 1;
	private Data data;

	static Population population = null;

	final static Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		BasicConfigurator.configure();
		StopWatch stopWatch = new StopWatch();
		logger.debug("Entered Main Class");
		Main driver = new Main();
		driver.data = new Data();
		int generationNumber = 0;
		driver.printAvailableData();

		logger.debug("Calling the Genetic Algorithm -- for different Schedule");
		System.out.println("Generation No: " + generationNumber);
		System.out.print("Event Schedule No: |                                           ");
		System.out.print("Events Data [ org | event title | room | organizer | event timing ]       ");
		System.out.println("                                                                     | Fitness | Conflicts");
		System.out.print("---------------------------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------------------------------------------");

		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
		Population population = new Population(Main.POPULATION_SIZE, driver.data).sortByFitness();
		population.getSchedules()
				.forEach(schedule -> System.out.println("       " + driver.scheduleNum++ + "     | " + schedule + " | "
						+ String.format("%.5f", schedule.getFitness()) + " | " + schedule.getNumbOfConflicts()));
		driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
		driver.eventNum = 1;

		logger.debug("Unless the fitness turns to 1 - Call the GA");
		while (population.getSchedules().get(0).getFitness() != 1.0) {
			System.out.println();
			System.out.print("---------------------------------------------------------------------------------------------------------------");
			System.out.println("----------------------------------------------------------------------------------------------------------------");
			System.out.println("==> Generation No. " + ++generationNumber);
			System.out.print("==> Event Schedule No. |                                           ");
			System.out.print("Events [org|event title|organizer id|event timing id|room]       ");
			System.out.println("                                                                     | Fitness | Conflicts");
			System.out.print("---------------------------------------------------------------------------------------------------------------");
			System.out.println("----------------------------------------------------------------------------------------------------------------");

			population = geneticAlgorithm.evolve(population).sortByFitness();
			driver.scheduleNum = 0;
			population.getSchedules()
					.forEach(schedule -> System.out.println("       " + driver.scheduleNum++ + "     | " + schedule
							+ " | " + String.format("%.5f", schedule.getFitness()) + " | "
							+ schedule.getNumbOfConflicts()));
			driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
			driver.eventNum = 1;
		}
		logger.debug("Obtained the Generation Counts");
		double time = stopWatch.elapsedTime();
		System.out.println("Time taken to run " + time);
		logger.debug("Time is noted down");

	}

	private void printScheduleAsTable(EventSchedule schedule, int generation) {
		ArrayList<Event> classes = schedule.getClasses();
		System.out.print("\n                       ");
		System.out.println(
				"Event No. | Org | Event(Title|Max No. of Attendees) | Room(Capacity) |   Organizer(ID)   |  Event Time (ID)");
		System.out.print("                       ");
		System.out.print("------------------------------------------------------");
		System.out.println("---------------------------------------------------------------");

		classes.forEach(x -> {

			int majorIndex = data.getOrganizations().indexOf(x.getOrg());
			int eventTitlesIndex = data.getEventTitles().indexOf(x.getTitle());
			int roomsIndex = data.getRooms().indexOf(x.getRoom());
			int organizersIndex = data.getOrganizers().indexOf(x.getOrganizer());
			int eventTimingIndex = data.getEventTimings().indexOf(x.getEventTime());
			System.out.print("                       ");
			System.out.print(String.format("  %1$02d  ", eventNum) + "  | ");
			System.out.print(String.format("%1$4s", data.getOrganizations().get(majorIndex).getName()) + " | ");
			System.out.print(String.format("%1$21s",
					data.getEventTitles().get(eventTitlesIndex).getName() + "("
							+ data.getEventTitles().get(eventTitlesIndex).getNumber() + " | "
							+ x.getTitle().getMaxNumberOfAttendees())
					+ ")             | ");
			System.out.print(String.format("%1$10s",
					data.getRooms().get(roomsIndex).getNumber() + "(" + x.getRoom().getSeatCapacity()) + ")     | ");
			System.out.print(String.format("%1$15s", data.getOrganizers().get(organizersIndex).getName() + "("
					+ data.getOrganizers().get(organizersIndex).getId() + ")") + "  | ");
			System.out.println(data.getEventTimings().get(eventTimingIndex).getTime() + "("
					+ data.getEventTimings().get(eventTimingIndex).getId() + ")");
			eventNum++;
		});

		if (schedule.getFitness() == 1)
			System.out.println("> Solution Found in " + (generation + 1) + " generations");
		System.out.print("-----------------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------------------");
		logger.info("Generations obtained in - > " + (generation + 1));
	}

	private void printAvailableData() {
		logger.debug("Print the Initial DATA ");
		System.out.println("\nAvailable Organizations:");
		System.out.println("--------------------------------------------------------------------------------------");
		
		data.getOrganizations()
				.forEach(x -> System.out.println("Name: " + x.getName() + ", Event Titles: " + x.getTitle()));
		System.out.println("\nAvailable Event Titles:");
		System.out.println("--------------------------------------------------------------------------------------");
		data.getEventTitles().forEach(x -> System.out.println("Event Title No.: " + x.getNumber() + ", Name: " + x.getName()
				+ ", Max no. of attendees: " + x.getMaxNumberOfAttendees() + ", Organizors: " + x.getOrganizers()));
		System.out.println("\nAvailable Event Rooms:");
		System.out.println("--------------------------------------------------------------------------------------");
		data.getRooms().forEach(
				x -> System.out.println("Room No.: " + x.getNumber() + ", max seating capacity: " + x.getSeatCapacity()));
		System.out.println("\nAvailable Organizers:");
		System.out.println("--------------------------------------------------------------------------------------");
		data.getOrganizers().forEach(x -> System.out.println("ID: " + x.getId() + ", name: " + x.getName()));
		System.out.println("\nAvailable Event Times:");
		System.out.println("--------------------------------------------------------------------------------------");
		data.getEventTimings().forEach(x -> System.out.println("ID: " + x.getId() + ", Event Time: " + x.getTime()));
		System.out.print("-----------------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------------------");
	}

}
