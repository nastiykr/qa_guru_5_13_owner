package tests;

import owner.config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WebDriverConfigTest {
    @Test
    public void ConfigTest() {
        System.setProperty("webdriver.url", "https://user1:1234@selenoid.autotests.cloud/wd/hub/");
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

        assertThat(config.getURL().toString()).isEqualTo("https://user1:1234@selenoid.autotests.cloud/wd/hub/");

        System.out.println(config.isRemote());
        System.out.println(config.getURL());
        System.out.println(config.getBrowser());
        System.out.println(config.getBrowserVersion());
    }
}
