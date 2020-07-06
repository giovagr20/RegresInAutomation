package in.regres.tasks.services;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.openqa.selenium.WebDriver;


public class GetRegresinTask implements Task {

    private WebDriver hisBrowser;
    private String URL;
    private Actor actorRegresin;

    public GetRegresinTask(WebDriver hisBrowser, String URL, Actor actorRegresin) {
        this.hisBrowser = hisBrowser;
        this.URL = URL;
        this.actorRegresin = actorRegresin;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actorRegresin = Actor.named("actorRegresin")
                .whoCan(CallAnApi.at(URL));
        actorRegresin.attemptsTo(
                Get.resource("/api/unknown/23")
        );
        actorRegresin.should(
                seeThatResponse("Data must show an error",
                        response -> response.statusCode(404))
        );
    }

    public static GetRegresinTask getRegresinTask(WebDriver hisBrowser, String URL, Actor actorRegresin){
        return Tasks.instrumented(GetRegresinTask.class, hisBrowser, URL, actorRegresin);
    }
}
