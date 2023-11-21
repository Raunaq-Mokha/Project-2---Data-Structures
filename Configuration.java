import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The Configuration class is responsible for loading configuration settings from a properties file.
 * If the properties file cannot be found or read, default values are used.
 */
public class Configuration {
    // the properties object is used to store configuration settings
    private final Properties properties = new Properties();

    // default values for each configuration setting, used if the properties file is not found
    private static final String DEFAULT_STRUCTURE = "linked";
    private static final int DEFAULT_FLOORS = 32;
    private static final double DEFAULT_PASSENGERS = 0.03;
    private static final int DEFAULT_ELEVATORS = 1;
    private static final int DEFAULT_ELEVATOR_CAPACITY = 10;
    private static final int DEFAULT_DURATION = 500;

    public Configuration(String filename) {
        try {
            // Attempt to load the properties file from the provided filename
            properties.load(new FileInputStream(filename));
        } catch (IOException e) {
            // If an error occurs while reading the file, log an error message and use default values
            //System.err.println("Warning: Configuration file " + filename + " not found. Using default values.");
            setDefaultProperties();
        }
    }
    private void setDefaultProperties() {
        properties.setProperty("structures", DEFAULT_STRUCTURE);
        properties.setProperty("floors", Integer.toString(DEFAULT_FLOORS));
        properties.setProperty("passengers", Double.toString(DEFAULT_PASSENGERS));
        properties.setProperty("elevators", Integer.toString(DEFAULT_ELEVATORS));
        properties.setProperty("elevatorCapacity", Integer.toString(DEFAULT_ELEVATOR_CAPACITY));
        properties.setProperty("duration", Integer.toString(DEFAULT_DURATION));
    }

    // Getter methods should be added here to retrieve each configuration value
    // Example getter for the 'structures' property:

    // Retrieves number of floors in the building
    public int  getFloors() {
        return Integer.parseInt(properties.getProperty("floors", String.valueOf(DEFAULT_FLOORS)));
    }

    // Retrieves probability of passenger appearance
    public double getPassengerProbability() {
        return Double.parseDouble(properties.getProperty("passengers", String.valueOf(DEFAULT_PASSENGERS)));
    }

    // Retrieves number of elevators in the simulation
    public int getElevators() {
        return Integer.parseInt(properties.getProperty("elevators", String.valueOf(DEFAULT_ELEVATORS)));
    }

    // Retrieves maximum capacity of each elevator
    public int getElevatorCapacity() {
        return Integer.parseInt(properties.getProperty("elevatorCapacity", String.valueOf(DEFAULT_ELEVATOR_CAPACITY)));
    }

    // Retrieves duration of the simulation in ticks
    public int getDuration() {
        return Integer.parseInt(properties.getProperty("duration", String.valueOf(DEFAULT_DURATION)));
    }

}

