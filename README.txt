===========================================   EVENT SCHEDULING IMPLEMENTATION   ======================================================

Team Number: 313

Team Members: Raghavi Kirouchenadradjou, Neha Pednekar

======================================================================================================================================

Problem Statement:

Generation of Event Schedules can be a time consuming and a challenging task. We have used Genetic Algorithm to find a solution to the Event Scheduling problem. Considering a set of data, where we have a list of different organizations, events taking place which are conducted by these organizations, different event themes or titles under every event(For example, dancing and singing under Arts and Culture Event, Scientific Seminars under Scientific Events, etc.), organizers conducting these events, number of attendees showing presence for a specific event and available rooms within a venue for these events, an event schedule can be produced with minimal or no conflicts. In Event Scheduling problem, we begin with an initial population and select parents for mating. We will then be applying mutation and crossover operations on the parents to generate new off-springs which in turn will replace the existing individuals. This is a repetitive process until we find an optimal set of solutions within a search space. In simple words, this process will help us find an optimal event schedule.

Uploaded Files are as following

==> Info6205_Team313.zip
==> Info6205_Team313 Entire folder
==> ReadMe.txt
==> INFO6205_Team313_Observations.xlsx
==> Report_INFO6205_Team313.docx

Files inside the Info6205_Team313 folder

==> Source Package Folder 
    ==> main/java/com/ga/eventboard
        ==> controller - Controller folder has EventSchedule.java, Genetic Algorithm.java, Population.java
        ==> data - data folder has Data.java which contains the sample data used as an input.
        ==> main - main folder contains Main.java which is the starting point of the algorithm application.
        ==> model - model folder consists of all the POJO classes which form the basis of Event Scheduling probem. They are               Event.java, EventTiming.java, Organization.java, Organizer.java, Rooms.java and Title.java
        ==> utilies - Under utilites folder, we have a Stopwatch.java class which is used for time frame calculations.

==> Test Package folder
    ==> test/java/com/ga/eventboard/test
        ==> TestJava.java - JUnit test cases are contained within this file.
        
==> .project file
==> test dependencies
==> java dependencies
==> pom.xml
==> .classpath - contains all dependencies like log4j

======================================================================================================================================