package cucumber.scenarios.glue.hooks;

import io.cucumber.java.Before;

public class CucumberHooks {

    @Before
    public void beforeMethod() {
        System.out.println("Before all");
    }

}
