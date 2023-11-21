public class Passenger {
    private int currentFloor;
    private int destinationFloor;
    private boolean inElevator;
    private int waitTime;
    private Elevator requestedElevator;

    // Constructor to initialize a Passenger with specified current and destination floors.
    public Passenger(int currentFloor, int destinationFloor) {
        if (currentFloor < 1 || destinationFloor < 1 || currentFloor == destinationFloor) {
            throw new IllegalArgumentException("Invalid floor numbers");
        }
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.inElevator = false;
        this.waitTime = 0;
    }
    //Method to indicate that the Passenger is exiting the elevator.
    //Checks if the Passenger is in the elevator and if the elevator is at the destination floor.
    //If conditions are met, updates the Passenger's status and releases the Passenger from the elevator.
    public void exitElevator() {
        if (inElevator && requestedElevator.getCurrentFloor() == destinationFloor) {
            inElevator = false;
            requestedElevator.releasePassenger(this);
            requestedElevator = null;
        }
    }

    // Getters:
    public int getCurrentFloor() { 
        return currentFloor; }

    public int getDestinationFloor() 
    { return destinationFloor; 
    }
    public boolean isInElevator() { 
        return inElevator; 
    }
    public int getWaitTime() { 
        return waitTime; 
    }
    public void incrementWaitTime() {
        this.waitTime++;
    }

}
