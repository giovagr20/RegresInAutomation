package in.regres.tasks.services;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class PostRegresinTask implements Task {

    private WebDriver hisBrowser;
    private String URL;
    private Actor actorRegresin;

    public PostRegresinTask(WebDriver hisBrowser, String URL, Actor actorRegresin) {
        this.hisBrowser = hisBrowser;
        this.URL = URL;
        this.actorRegresin = actorRegresin;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actorRegresin = Actor.named("actorRegresin")
                .whoCan(CallAnApi.at(URL));
        actorRegresin.attemptsTo(
                Post.to("/api/users")
                        .with(
                                request -> request.header("Content-Type", "application/json")
                                        .body("{\"name\":\"Giovanni Gomez\",\"job\":\"Software Engineer\"}")

                        )
        );

        actorRegresin.should(
                seeThatResponse("The user must be created",
                        response -> response.statusCode(201))

        );
    }

    public static PostRegresinTask postRegresinTask(WebDriver hisBrowser, String URL, Actor actorRegresin){
        return Tasks.instrumented(PostRegresinTask.class, hisBrowser, URL, actorRegresin);
    }
}
