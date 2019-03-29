package com.ga.eventboard.controller;

import java.util.ArrayList;
import java.util.stream.IntStream;

import com.ga.eventboard.data.Data;
import com.ga.eventboard.main.Main;
/**
*
* @author Raghavi,Neha
*/
public class GeneticAlgorithm {
	private Data data;

	public GeneticAlgorithm(Data data) {
		this.data = data;
	}

	// returns the population value
	public Population evolve(Population population) {
		return mutatePopulation(crossoverPopulation(population));
	}

	// returns a population from the cross over of schedules which is greater than
	// cross over rate
	public Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(population.getSchedules().size(), data);
		IntStream.range(0, Main.NUMB_OF_ELITE_SCHEDULES)
				.forEach(x -> crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x)));
		IntStream.range(Main.NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(x -> {
			if (Main.CROSSOVER_RATE > Math.random()) {
				EventSchedule schedule1 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
				EventSchedule schedule2 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
				crossoverPopulation.getSchedules().set(x, crossoverSchedule(schedule1, schedule2));
			} else
				crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x));
		});
		return crossoverPopulation;
	}

	// return the schedule based on Random Function
	public EventSchedule crossoverSchedule(EventSchedule schedule1, EventSchedule schedule2) {
		EventSchedule crossoverSchedule = new EventSchedule(data).initialize();
		IntStream.range(0, crossoverSchedule.getClasses().size()).forEach(x -> {
			if (Math.random() > 0.5)
				crossoverSchedule.getClasses().set(x, schedule1.getClasses().get(x));
			else
				crossoverSchedule.getClasses().set(x, schedule2.getClasses().get(x));
		});
		return crossoverSchedule;
	}

	// returns the Mutated Populated based on ELITE SCHEDULE
	public Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(population.getSchedules().size(), data);
		ArrayList<EventSchedule> schedules = mutatePopulation.getSchedules();
		IntStream.range(0, Main.NUMB_OF_ELITE_SCHEDULES)
				.forEach(x -> schedules.set(x, population.getSchedules().get(x)));
		IntStream.range(Main.NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(x -> {
			schedules.set(x, mutateSchedule(population.getSchedules().get(x)));
		});
		return mutatePopulation;
	}

	// Returns the Event Schedule Class Schedule > Mutate Rate
	public EventSchedule mutateSchedule(EventSchedule mutateSchedule) {
		EventSchedule schedule = new EventSchedule(data).initialize();
		IntStream.range(0, mutateSchedule.getClasses().size()).forEach(x -> {
			if (Main.MUTATION_RATE > Math.random())
				mutateSchedule.getClasses().set(x, schedule.getClasses().get(x));
		});
		return mutateSchedule;
	}

	// returns the best population
	public Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(Main.TOURNAMENT_SELECTION_SIZE, data);
		IntStream.range(0, Main.TOURNAMENT_SELECTION_SIZE).forEach(x -> {
			tournamentPopulation.getSchedules().set(x,
					population.getSchedules().get((int) (Math.random() * population.getSchedules().size())));
		});
		return tournamentPopulation;
	}
}
