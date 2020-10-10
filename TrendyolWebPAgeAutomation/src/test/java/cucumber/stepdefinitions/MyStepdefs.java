package cucumber.stepdefinitions;

import cucumber.api.java.en.Given;

public class MyStepdefs {
    @Given("^firs test$")
    public void firsTest() {

        System.out.println("Test done!");
    }
}
