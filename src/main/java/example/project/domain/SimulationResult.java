package example.project.domain;

import java.util.ArrayList;
import java.util.List;

public class SimulationResult {

    public List<Snapshot> snapshots;

    public SimulationResult() {
        // constructor
        snapshots = new ArrayList<>();
    }

    /**
     * Load the simulation result saved in a file.
     *
     * @param filePath the file to load.
     */
    public void load(String filePath) {
        System.out.printf("(dummy) load a simulation result from %s", filePath);
    }

    /**
     * Save the simulation result into a file.
     *
     * @param filePath the file to save.
     */
    public void save(String filePath) {
        System.out.printf("(dummy) save a simulation result to %s", filePath);
    }
}
