# Testing for Reengineering

This repository includes lab materials to support the Spring Semester of the COM3523/6523 module "Software Reengineering" at the University of Sheffield.

In this week, we explore testing for reengineering.


## Important Notes

This repository contains skeleton classes for checking safety violations of an Automated Driving System (ADS) by running a driving scenario using a simulator. 
The ADS under analysis and the simulator are external (i.e., not included in this code base).
In other words, this code base is supposed to work with external ADSs and simulators.

Since our aim is to learn "testing for reengineering", you can only modify test cases (i.e., files under `src/test`).
You must *not* modify the source code under `src/main` to solve the problems below.
The only exception is [Scenario.java](src/main/example/project/domain/Scenario.java) which should be modified in Part 3.

By default, a CI/CD pipeline is set up to automatically check the following commands (i.e., the commands that must pass when you complete all the tasks below) when your commits are pushed to the main branch:
- `mvn test -Dtest=TestSimulator#testRun2`
- `mvn test -Dtest=TestADS#testPrintPath`
- `mvn test -Dtest=TestChecker#testCheckCollisionViolations`
- `mvn test -Dtest=TestScenario#testEquality`


## Part 0: Setup

This part is to set up your own repository and understand the code in the repository.

### Step 1: Clone your repository
First, clone this repository to your local machine (e.g., the user drive in the University computer; `u:/Teaching/COM3523/2022-23` in my case). Open `Git Bash` and enter the following commands:

```bash
cd u:/Teaching/COM3523/2022-23
git clone [YOUR_REPOSITORY_ADDRESS]
```

### Step 2: Take a look at the code
Now, take a look at the code you just cloned.
If you want, open the project in IntelliJ and browse the files in it.
For those who wants to install IntelliJ 2022 on your shared drive (/U:), please refer to [this guideline](/resources/install-intellij-user-drive.md).


### Step 3: Run maven command on your local machine
Try the following command in a terminal (Git Bash) to see the status of the testing results:
```bash
mvn test
```
It will return several errors because there are *broken* test cases you must update during this lab session.


## Part 1: Test automation

This part is about test automation. You already learned CI/CD basics in Week 6, so this will be easy.

### Step 1: Understand `TestSimulator.java`

Open [TestSimulator.java](src/test/java/example/project/TestSimulator.java).

You can see that an example test case is written for you:
```Java
@Test
package example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import example.project.domain.Scenario;
import org.junit.jupiter.api.Test;

public class TestSimulator {

    @Test
    public void testRun() {
        Simulator simulator = new Simulator("dummy");
        Scenario scenario = new Scenario();
        assertEquals(null, simulator.run(scenario));
    }

}
```

### (Task) Step 2: Fix `testRun2()`

In [TestSimulator.java](src/test/java/example/project/TestSimulator.java), please fix `testRun2()` following the instructions in the code. 


### (Task) Step 3: Fix `testPrintPath()`

In [TestADS.java](src/test/java/example/project/TestADS.java), please fix `testPrintPath()` following the instructions in the code. 


## Part 2: Mocking

This is about using mocks in testing for reengineering.

### Step 1: Understand `testPredict()` in `TestADS.java`

In [TestADS.java](src/test/java/example/project/TestADS.java), we have a test case `testPredict()`:
```java
package example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.project.domain.Scenario;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestADS {
    @Test
    public void testPredict() {
        ADS ads = new ADS("dummy");
        Scenario scenario = new Scenario("special scenario leading to the prediction of [0, 0]");
        List<Object> prediction = ads.predict(scenario); // the prediction will be null since the ADS is dummy.
        assertEquals(Arrays.asList(0, 0), prediction);
    }
}
```

If you run this test case using the following command:
```bash
mvn test -Dtest=TestADS#testPredict
```
You will see a failure as follows:
```bash
...
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running example.project.TestADS
WARNING: ADS.predict() is not yet implemented.
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.029 s <<< FAILURE! - in example.project.TestADS
[ERROR] example.project.TestADS.testPredict  Time elapsed: 0.018 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <[0, 0]> but was: <null>
...
```
This is because, as noted above in the comment, 
`ads.predict()` is dummy (in the [source code](src/main/java/example/project/ADS.java), 
you can see that the implementation of `predict()` returns `null`).

If a working ADS was given, we could have updated `predict()` to use the working ADS. 
However, in many cases, the legacy software to reengineer might have external dependendies, 
such as an ADS in this example, that are unavailable or difficult to get.

Then should we just give up testing relevant code? No, we can use **mocks**!


### Step 2: Fix `testPredict()` using mocks

Instead of using an incomplete/external ADS, we can use a mock as follows:
```java
ADS ads = mock(); // instead of ADS ads = new ADS("dummy");
```

This allows us to modify the behaviour of `ads.predict()` as we want. 
Based on this, we can fix the above `testPredict()` as follows:
```java
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
    public void testPredict() {
        ADS ads = mock();
        Scenario scenario = new Scenario("special scenario leading to the prediction of [0, 0]");
        when(ads.predict(scenario)).thenReturn(Arrays.asList(0, 0));

        List<Object> prediction = ads.predict(scenario);
        assertEquals(Arrays.asList(0, 0), prediction);
        verify(ads, times(1)).predict(scenario); // verify if ads.predict(scenario) has been called only once
        verify(ads, times(0)).printPath(); // verify if ads.printPath() has been never called
    }
}
```

Please update your test code using the above, so it can pass, and check if it really passes by running the same command:
```bash
mvn test -Dtest=TestADS#testPredict
```

### (Task) Step 3: Fix `testCheckCollisionViolations()` in `TestChecker.java`

In [TestChecker.java](src/test/java/example/project/TestChecker.java), we have a failing test case `testCheckCollisionViolations()`. The reason is basically the same as `testPredict()` above. Now it's your turn to fix this `testCheckCollisionViolations()` using mocks.

**HINT**: Investigate why `checker.checkCollisionViolations(scenario)` internally raises a null pointer exception. 
It's mainly because `simulator.run(scenario)` in [Checker.java](src/main/java/example/project/Checker.java). 
Now, which object should be mocked?

### References
- [Mockito framework](https://site.mockito.org)
- [API documentation](https://javadoc.io/static/org.mockito/mockito-core/5.2.0/org/mockito/Mockito.html#4) for `verify()`

## Part 3: Interface testing

This is about testing interface instead of implementation. 

### Step 1: Understand `testEquality()` in `TestSnapshot.java`

In [TestSnapshot.java](src/test/java/example/project/domain/TestSnapshot.java), please try to understand how `testEquality()` works.

```java
    @Test
    public void testEquality()
    {
        assertEquals(s1.roadType, s2.roadType);
        assertEquals(s1.weatherCondition, s2.weatherCondition);
        assertTrue(Arrays.equals(s1.egoCarPos.toArray(), s2.egoCarPos.toArray()));
        assertTrue(Arrays.equals(s1.carInFrontPos.toArray(), s2.carInFrontPos.toArray()));
    }
```

You can see that it has *four* assertions to check the equality between `s1` and `s2`. 
This is not good for maintainability; for example, what happens if we update [Snapshot.java](src/main/java/example/project/domain/Snapshot.java) to have more attributes than the existing ones? 
We must update the test case *additionally* whenever [Snapshot.java](src/main/java/example/project/domain/Snapshot.java) is updated.

### Step 2: Make `testEquality()` simple

To keep the test case the same while updating the implementation details, we can improve [Snapshot.java](src/main/java/example/project/domain/Snapshot.java) by adding the following lines:
```java
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        Snapshot snapshot = (Snapshot) obj;
        return roadType.equals(snapshot.roadType)
                && weatherCondition.equals(snapshot.weatherCondition)
                && Arrays.equals(egoCarPos.toArray(), snapshot.egoCarPos.toArray())
                && Arrays.equals(carInFrontPos.toArray(), snapshot.carInFrontPos.toArray());
    }
```
Adding the above lines to [Snapshot.java](src/main/java/example/project/domain/Snapshot.java) will allow us to simplify the test case as follows:
```java
    @Test
    public void testEqualitySimple()
    {
        assertEquals(s1, s2);
    }
```

### (Task) Step 3: Fix `testEquality()` in `TestScenario.java`

Now, it's your turn to fix `testEquality()` in [TestScenario.java](src/test/java/example/project/domain/TestScenario.java) according to the instructions provided in the code.

**HINT**: Override `equals()` in [Scenario.java](src/main/java/example/project/domain/Scenario.java).

### References
- [JDK 11 documentation](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Object.html#equals(java.lang.Object)) for `Object.equals()`.
- [Overriding methods](https://docs.oracle.com/javase/tutorial/java/IandI/override.html)
