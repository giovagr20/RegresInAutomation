package in.regres.stepdefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.regres.models.PathModels;
import in.regres.tasks.browser.NavigateRegresinTask;
import in.regres.tasks.services.GetRegresinTask;
import in.regres.tasks.services.PostRegresinTask;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class RestServicesRegresinStepDefinitions {

    @Managed(driver = "chrome")
    private WebDriver hisBrowser;
    private Actor userRegresin;

    @Before
    public void setUp(){
        PathModels.setURL("https://reqres.in");
        userRegresin = Actor.named("userRegresin").whoCan(CallAnApi.at(PathModels.getURL()));
    }

    @Given("^user GET URL$")
    public void userGETURL() {
        userRegresin.attemptsTo(NavigateRegresinTask.intoHome(hisBrowser,
                PathModels.getURL(), userRegresin)
        );
    }

    @When("^user POST URL$")
    public void userPOSTURL() {
        userRegresin.attemptsTo(PostRegresinTask.postRegresinTask(hisBrowser,
                PathModels.getURL(), userRegresin));
    }


    @Then("^user GET new URL$")
    public void userGETNewURL() {
        userRegresin.attemptsTo(GetRegresinTask.getRegresinTask(hisBrowser,
                PathModels.getURL(), userRegresin));
    }
}
