package example.project;

import example.project.domain.Scenario;

import java.util.List;

public class ADS {
    String ADSPath;

    public ADS(String ADSBinPath) {
        this.ADSPath = ADSBinPath;
    }

    /**
     * Predict the driving commands (e.g., steering angles) for a given scenario.
     *
     * @param scenario a given scenario
     * @return
     */
    public List<Object> predict(Scenario scenario) {
        System.out.println("WARNING: ADS.predict() is not yet implemented.");
        return null;
    }

    public void printPath() {
        System.out.println(ADSPath);
    }

}
