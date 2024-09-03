package validationTest;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/default-cucumber-reports/cucumber.json",
                "html:target/default-cucumber-reports/index.html",
        },
        features = "src/test/resources/features",
        glue = "validationTest",
        dryRun = false
)
public class CukesRunner {

}
