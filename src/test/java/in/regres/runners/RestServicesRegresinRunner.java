package in.regres.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/rest_services_regresin.feature"},
        glue = "in.regres.stepdefinitions",
        snippets = SnippetType.CAMELCASE
)
public class RestServicesRegresinRunner {}
