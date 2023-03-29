package example.project;

import example.project.domain.Scenario;
import example.project.domain.SimulationResult;

public class Simulator {
    String simulatorBinPath;

    public Simulator(String simulatorBinPath) {
        this.simulatorBinPath = simulatorBinPath;
    }

    /**
     * Execute the simulator binary for a given scenario and get the simulation result.
     *
     * @param scenario the driving scenario to run
     * @return simulation result (i.e., series of the snapshots of the simulated world)
     */
    SimulationResult run(Scenario scenario) {
        System.out.println("WARNING: Simulator.run() is not yet implemented.");
        return null;
    }

    SimulationResult run(Scenario scenario, ADS ads) {
        System.out.println("WARNING: Simulator.run() is not yet implemented.");
        return null;
    }
}
