package example.project;

import example.project.domain.Scenario;
import example.project.domain.SimulationResult;
import example.project.domain.Snapshot;

/**
 * An object to check if any safety violations happened during the execution of a scenario
 * for an ADS under test using a simulator.
 */
public class Checker {

    Simulator simulator;
    ADS ads;


    public Checker(Simulator simulator) {
        this.simulator = simulator;
        this.ads = null;
    }

    /**
     * Check if a collision happened during a given scenario.
     *
     * @param scenario a driving scenario.
     * @return `true` if at lease one collision happened; `false` otherwise
     */
    public boolean checkCollisionViolations(Scenario scenario) {

        // run the simulator using the ads for the given scenario
        SimulationResult result = simulator.run(scenario);

        // analyse the result to see if any collision happened
        for (Snapshot snapshot : result.snapshots) {
            if (isCollision(snapshot)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if there is a collision happened in a given snapshot.
     *
     * @param snapshot a snapshot of the simulated world.
     * @return `true` if at least one collision happened; `false` otherwise
     */
    boolean isCollision(Snapshot snapshot) {
        return snapshot.carInFrontPos.get(0) - snapshot.egoCarPos.get(0) <= 0
                && snapshot.carInFrontPos.get(1) - snapshot.egoCarPos.get(1) <= 0;
    }

}
