/**
 * Created by shpuli on 22.02.2015.
 */
import java.security.Key;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class LikeTest {
    private static String StartUrl = "http://preproduction.round.me/";
    private final String Login = "shpulitest";
    private final String Password = "123456";
    private final String UserToFind = "test3";
    private final String SpaceToLike = "Boracay";
    private static WebDriver driver;

    @Before
    public void testInitialize(){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("plugin.state.flash", 0);

        driver = new FirefoxDriver(profile);

        driver.navigate().to(StartUrl);

        driver.findElement(By.cssSelector("body > header > div > div > a")).click();

        driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > input.gn-field-simple.rm-app-login"))
                .sendKeys(Login);
        driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > input.gn-field-simple.rm-app-password"))
                .sendKeys(Password);
        driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > button"))
                .click();
    }

    @After
    public void testClose(){
        driver.quit();
    }

    @Test
    public void likeSomeSpace(){
        driver.findElement(By.cssSelector("body > header > div > ul > li:nth-child(3) > a")).click();
        driver.findElement(By.cssSelector("body > header > div > ul > li:nth-child(3) > a")).click();
        driver.findElement(By.cssSelector("body > nav > div > div > div > form > input.text-input.rm-search-field"))
                .sendKeys(UserToFind + Keys.ENTER);

        driver.findElement(By.cssSelector("#scroller > section > div > div.wrapper > section > div > div > div > div:nth-child(1) > div > div > div > div > a"))
                .click();

        driver.findElement(By.linkText(SpaceToLike)).click();

        driver.findElement(By.cssSelector("#scroller > section > div > div.js-viewport.viewer-inner > div.js-header-region > div > div > div.panorama-controls-wrapper > div.panorama-header-controls > div > div > div.js-tooltip.js-like.panorama-header-control-button.panorama-header-control-like"))
                .click();

        driver.navigate().to(StartUrl + "@" + Login);
        driver.findElement(By.cssSelector("body > nav > div > div > ul > li.rm-nav-favorites.rm-app-link-favorites > a"))
                .click();

        Assert.assertNotNull(driver.findElement(By.linkText(SpaceToLike)));
    }
}
