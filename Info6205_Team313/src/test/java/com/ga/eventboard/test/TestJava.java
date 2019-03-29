package com.ga.eventboard.test;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ga.eventboard.controller.EventSchedule;
import com.ga.eventboard.controller.GeneticAlgorithm;
import com.ga.eventboard.controller.Population;
import com.ga.eventboard.data.Data;
import com.ga.eventboard.main.Main;

/**
 *
 * @author Neha ,Raghavi
 */
public class TestJava {
    Data data;
    Main main;
     GeneticAlgorithm geneticAlgo;

    @Before
    public void setUp() {
         data = new Data();
         main = new Main();
         geneticAlgo = new GeneticAlgorithm(data);
    }


    @Test
    public void testTournamentPopulation() {
        
        Population test = new Population(main.POPULATION_SIZE, data);
        Population expected = new Population(main.TOURNAMENT_SELECTION_SIZE, data);
        
        assertEquals(expected,geneticAlgo.selectTournamentPopulation(test));
        assertEquals(expected.getSchedules().size(),geneticAlgo.selectTournamentPopulation(test).getSchedules().size());
    }
    @Test
    public void testCrossoverPopulation() {
        Population test = new Population(main.POPULATION_SIZE, data);        
        Population crossPopu = new Population(main.POPULATION_SIZE, data);
        Population expected =  geneticAlgo.crossoverPopulation(crossPopu);
        assertEquals(expected, geneticAlgo.crossoverPopulation(test));
        assertEquals(expected.getSchedules().size(), geneticAlgo.crossoverPopulation(test).getSchedules().size());
        
    }
    
    @Test
    public void testMutatePopulation() {
    	
        Population test = new Population(main.POPULATION_SIZE, data);
        Population mutatePopu = new Population(main.POPULATION_SIZE, data);
        Population expected =  geneticAlgo.mutatePopulation(mutatePopu);
        assertEquals(expected, geneticAlgo.mutatePopulation(test));
        assertEquals(expected.getSchedules().size(), geneticAlgo.mutatePopulation(test).getSchedules().size());
    }
    
    @Test
    public void testMutateSchedule() {
        Population mutatePopulation = new Population(main.POPULATION_SIZE, data);
	EventSchedule test =  mutatePopulation.getSchedules().get(0);
	EventSchedule expected= new EventSchedule(data).initialize();
        assertEquals(expected, geneticAlgo.mutateSchedule(test));
    }
    @Test
    public void testCrossSchedule() {
        Population population = new Population(main.POPULATION_SIZE, data);
        EventSchedule expected = new EventSchedule(data).initialize();
        EventSchedule schedule1 = geneticAlgo.selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
        EventSchedule schedule2 = geneticAlgo.selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
        assertEquals(expected, geneticAlgo.crossoverSchedule(schedule1,schedule2));
    }
    

}
