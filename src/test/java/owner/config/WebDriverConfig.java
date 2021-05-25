package owner.config;

import org.aeonbits.owner.Config;

import java.net.URL;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${driver}_driver.properties",
        "classpath:remote_webdriver.properties"})
public interface WebDriverConfig extends Config {

    //@DefaultValue("true")
    @Key("webdriver.remote")
    boolean isRemote();

    //@DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub/")
    @Key("webdriver.url")
    URL getURL();

    //@DefaultValue("CHROME")
    @Key("webdriver.browser")
    BROWSER getBrowser();

    //@DefaultValue("88.0")
    @Key("webdriver.browser.version")
    String getBrowserVersion();

    enum BROWSER {
        CHROME,
        FIREFOX,
        OPERA
    }
}
