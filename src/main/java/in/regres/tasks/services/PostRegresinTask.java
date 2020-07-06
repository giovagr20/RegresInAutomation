package in.regres.tasks.services;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class PostRegresinTask implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
    }

    public static PostRegresinTask postRegresinTask(){
        return Tasks.instrumented(PostRegresinTask.class);
    }
}
