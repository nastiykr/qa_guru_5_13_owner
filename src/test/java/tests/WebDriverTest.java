package tests;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import owner.config.WebDriverConfig;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WebDriverTest {
    WebDriver driver;

    @BeforeEach
    public void initProperties() {
        //System.setProperty("webdriver.remote", "true");
        //System.setProperty("webdriver.url", "https://user1:1234@selenoid.autotests.cloud/wd/hub/");
        //System.setProperty("webdriver.browser", "CHROME");
        //System.setProperty("webdriver.browser.version", "90.0");

        WebDriverConfig config = ConfigFactory
                .create(WebDriverConfig.class, System.getProperties());
        initWebDriver(config);

        System.out.println(config.isRemote());
        System.out.println(config.getURL());
        System.out.println(config.getBrowser());
        System.out.println(config.getBrowserVersion());
    }

    @Test
    public void testWebDriver() {
        driver.get("https://github.com/");
        assertThat(driver.getTitle()).isEqualTo("GitHub: Where the world builds software · GitHub");
        driver.quit();
    }

    private void initWebDriver(WebDriverConfig config){
        if(config.isRemote()) {
            DesiredCapabilities capabilities;

            switch (config.getBrowser()) {
                case OPERA: {
                    capabilities = DesiredCapabilities.opera();
                    break;
                }
                case CHROME: {
                    capabilities = DesiredCapabilities.chrome();
                    break;
                }
                case FIREFOX: {
                    capabilities = DesiredCapabilities.firefox();
                    break;
                }
                default: {
                    capabilities = DesiredCapabilities.chrome();
                }
            }
            capabilities.setCapability("webdriver.browser.version", config.getBrowserVersion());
            driver = new RemoteWebDriver(config.getURL(), capabilities);
        } else {
            driver = new ChromeDriver();
        }
    }
}
