package example.project.domain;

import java.util.Arrays;
import java.util.List;

/**
 * A class defining the data type of snapshot.
 * The definition of a snapshot (i.e., the entities in the simulated world) depends on the domain/application.
 * This should be modified and updated with class Scenario.
 */
public class Snapshot {
    public String roadType;
    public String weatherCondition;
    public List<Float> egoCarPos;  // (x, y)
    public List<Float> carInFrontPos;  // (x, y)

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        Snapshot snapshot = (Snapshot) obj;
        return roadType.equals(snapshot.roadType)
                && weatherCondition.equals(snapshot.weatherCondition)
                && egoCarPos.equals(snapshot.egoCarPos)
                && carInFrontPos.equals(snapshot.carInFrontPos);
    }
}
