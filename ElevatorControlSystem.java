import java.util.List;

public class ElevatorControlSystem {
    private final List<Elevator> elevators;

    public ElevatorControlSystem(List<Elevator> elevators){
        this.elevators = elevators;
    }

    public Elevator requestElevator(Passenger passenger) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - passenger.getCurrentFloor());
            if (distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }

        return bestElevator;
    }

    public void addPassenger(Passenger passenger) {
        Elevator bestElevator = findBestElevatorForPassenger(passenger);
        if (bestElevator != null) {
            bestElevator.boardPassenger(passenger);
        }
    }

    public void step() {
        for (Elevator elevator : elevators) {
            elevator.update(); // Assuming there's an update method in Elevator class
        }
    } 

    private Elevator findBestElevatorForPassenger(Passenger passenger) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.getPassengers().size() < elevator.getCapacity()) { 
                int distance = Math.abs(elevator.getCurrentFloor() - passenger.getCurrentFloor());

                boolean isElevatorMovingTowardsPassenger = (elevator.isMovingUp() && passenger.getCurrentFloor() > elevator.getCurrentFloor()) ||
                        (!elevator.isMovingUp() && passenger.getCurrentFloor() < elevator.getCurrentFloor());

                if (distance < minDistance && isElevatorMovingTowardsPassenger) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }

        if (bestElevator == null) {
            for (Elevator elevator : elevators) {
                if (elevator.getPassengers().size() < elevator.getCapacity()) { 
                    int distance = Math.abs(elevator.getCurrentFloor() - passenger.getCurrentFloor());
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = elevator;
                    }
                }
            }
        }

        return bestElevator;
    }

}
