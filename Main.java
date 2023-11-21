public class Main {
    public static void main(String[] args) {
        // Decide which configuration file to use
        String configFile;

        if (args.length > 0) {
            configFile = args[0];
        } else {
            configFile = "config.properties";
        }

        // initialize and run the simulation with the specified config file
        Simulation simulation = new Simulation(configFile);
        simulation.runSimulation();
    }
}
