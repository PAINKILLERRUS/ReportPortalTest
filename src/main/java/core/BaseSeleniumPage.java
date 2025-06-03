package core;

import lombok.Setter;
import org.openqa.selenium.WebDriver;

public abstract class BaseSeleniumPage {

    @Setter
    protected static WebDriver driver;
}
