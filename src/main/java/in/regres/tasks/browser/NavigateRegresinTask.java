package in.regres.tasks.browser;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import org.openqa.selenium.WebDriver;

public class NavigateRegresinTask implements Task {

    private WebDriver hisBrowser;
    private String URL;
    private Actor actorRegresin;

    public NavigateRegresinTask(WebDriver hisBrowser, String URL, Actor actorRegresin) {
        this.hisBrowser = hisBrowser;
        this.URL = URL;
        this.actorRegresin = actorRegresin;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actorRegresin = Actor.named("actorRegresin")
                .whoCan(CallAnApi.at(URL));
        actorRegresin.attemptsTo(
                Get.resource("/api/users/2")
        );
        actorRegresin.should(
                seeThatResponse("This data must be returned" ,
                        response -> response.statusCode(200)
                            .body("data.id", equalTo(2)))
        );
    }

    public static NavigateRegresinTask intoHome(WebDriver hisBrowser, String URL, Actor actorRegresin) {
        return Tasks.instrumented(NavigateRegresinTask.class, hisBrowser, URL, actorRegresin);
    }
}
