package techproed.hooks;

import io.cucumber.java.*;
import techproed.utilities.Driver;

import java.time.Duration;

import static techproed.base_url.BaseUrl.setup;

public class Hooks {
    @Before("@UI")
    public void before_ui(){
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        Driver.getDriver().manage().window().maximize();
    }

    @After("@UI")
    public void tearDown() {
        Driver.closeDriver();
    }
}
