package example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import example.project.domain.Scenario;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestADS {

    @Test
    public void testPrintPath() {
        fail();
        // delete the above line and implement this properly to test if ads.printPath() prints the ADSPath.
        // hint: see testHelloWorld() in our previous lab session.
    }

    @Test
    public void testPredict() {
        ADS ads = new ADS("dummy");
        Scenario scenario = new Scenario("special scenario leading to the prediction of [0, 0]");
        List<Object> prediction = ads.predict(scenario); // the prediction will be null since the ADS is dummy.
        assertEquals(Arrays.asList(0, 0), prediction);
    }

}