import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {

    private Configuration config;
    private ElevatorControlSystem controlSystem;
    private int ticks; // duration of the simulation
    private Random random;
    private List<Passenger> allPassengers; // list to keep track of all passengers
//Initializes the simulation with configuration settings and sets up the elevator control system.
    public Simulation(String configFile) {
        config = new Configuration(configFile);
        allPassengers = new ArrayList<>();
        initializeSimulation();
    }
//Initializes the elevator control system and other simulation parameters.
    private void initializeSimulation() {
        ArrayList<Elevator> elevators = new ArrayList<>();
        for (int i = 0; i < config.getElevators(); i++) {
            elevators.add(new Elevator(config.getElevatorCapacity()));
        }
        controlSystem = new ElevatorControlSystem(elevators);
        ticks = config.getDuration();
        random = new Random();
    }
//Runs the simulation for the specified duration, generating passengers at each tick and updating the control system.

    public void runSimulation() {
        for (int tick = 0; tick < ticks; tick++) {
            generatePassengers();
            controlSystem.step();
            updatePassengerWaitTimes();
        }
        reportResults();
    }
// Generates passengers randomly based on the configured probability for each floor.
    private void generatePassengers() {
        int floors = config.getFloors();
        double probability = config.getPassengerProbability();
        for (int floor = 1; floor <= floors; floor++) {
            if (random.nextDouble() < probability) {
                int destinationFloor;
                do {
                    destinationFloor = random.nextInt(floors) + 1;
                } while (destinationFloor == floor);
                Passenger passenger = new Passenger(floor, destinationFloor);
                controlSystem.addPassenger(passenger);
                allPassengers.add(passenger); // add passenger to the list for tracking
            }
        }
    }

    private void updatePassengerWaitTimes() {
        for (Passenger passenger : allPassengers) {
            if (!passenger.isInElevator()) {
                passenger.incrementWaitTime();
            }
        }
    }

    private void reportResults() {
        int totalWaitTime = 0;
        int longestWaitTime = 0;
        int shortestWaitTime = Integer.MAX_VALUE;
        for (Passenger passenger : allPassengers) {
            int waitTime = passenger.getWaitTime();
            totalWaitTime += waitTime;
            longestWaitTime = Math.max(longestWaitTime, waitTime);
            shortestWaitTime = Math.min(shortestWaitTime, waitTime);
        }
        double averageWaitTime;

        if (allPassengers.isEmpty()) {
            averageWaitTime = 0;
        } else {
            averageWaitTime = (double) totalWaitTime / allPassengers.size();
        }

        System.out.println("Average Travel Time: " + averageWaitTime);
        System.out.println("Longest Travel Time: " + longestWaitTime);
        System.out.print("Shortest Travel Time: ");

        if (shortestWaitTime == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(shortestWaitTime);
        }

    }
}
