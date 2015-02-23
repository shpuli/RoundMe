/**
 * Created by shpuli on 22.02.2015.
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class RegisterTest {
    private static String StartUrl = "http://preproduction.round.me/";
    private final String Login = "shpulitest3";
    private final String Password = "123456";
    private static WebDriver driver;

    @Before
    public void testInitialize(){
        driver = new FirefoxDriver();
        driver.navigate().to(StartUrl);
    }

    @After
    public void testClose(){
        driver.quit();
    }

    @Test
    public void correctRegisterTest(){
        driver.findElement(By.cssSelector("body > header > div > div > button")).click();
        driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container.join > form > input.gn-field-simple.rm-app-username"))
                .sendKeys(Login);
        driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container.join > form > input.gn-field-simple.rm-app-email"))
                .sendKeys(Login + "@gmail.com");
        driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container.join > form > input.gn-field-simple.rm-app-password"))
                .sendKeys(Password);
        driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container.join > form > button"))
                .click();

        Assert.assertEquals("Wrong page opened", StartUrl + "@" + Login, driver.getCurrentUrl());
        Assert.assertNotNull("Not autorized", driver.findElement(By.cssSelector("body > header > div > div > div > button")));
    }

    @Test
    public void autorizeTest(){
        tryToLogin(Login, Password);

        Assert.assertEquals("Wrong page opened", StartUrl + "@" + Login, driver.getCurrentUrl());
        Assert.assertNotNull("Not autorized", driver.findElement(By.cssSelector("body > header > div > div > div > button")));
    }

    @Test
    public void changePassTest(){
        Actions action = new Actions(driver);

        tryToLogin(Login, Password);

        driver.findElement(By.cssSelector("#scroller > section > div > header > div > div.profile-head-content-out > div > button"))
                .click();

        driver.findElement(By.cssSelector("#scroller > section > div > section.rm-app-settings-password > div > div > div > div.rm-row.settings > div:nth-child(1) > input"))
                .sendKeys(Password);
        driver.findElement(By.cssSelector("#scroller > section > div > section.rm-app-settings-password > div > div > div > div.rm-row.settings > div:nth-child(2) > input"))
                .sendKeys(Password + "new");
        driver.findElement(By.cssSelector("#scroller > section > div > section.rm-app-settings-password > div > div > div > div.rm-row.settings > div:nth-child(3) > input"))
                .sendKeys(Password + "new");

        driver.findElement(By.cssSelector("#scroller > section > div > section.rm-app-settings-password > div > div > div > div.row-btn-container.pass > button"))
                .click();

        Assert.assertEquals("Wrong text message on password change",
                "PASSWORD HAS BEEN CHANGED!",
                driver.findElement(By.cssSelector("div > div > div.ui-pnotify-text")).getText());

        action.moveToElement(driver.findElement(By.cssSelector("body > header > div > div > div > a > span.avatar > img")))
                .build()
                .perform();
        driver.findElement(By.cssSelector("body > header > div > div > div > ul > div > li:nth-child(9) > a"))
                .click();

        tryToLogin(Login, Password);

        Assert.assertEquals("Wrong text message on log in",
                "INVALID LOGIN OR PASSWORD",
                driver.findElement(By.cssSelector("div > div > div.ui-pnotify-text")).getText());

        driver.navigate().refresh();

        tryToLogin(Login, Password + "new");

        Assert.assertEquals("Wrong page opened", StartUrl + "@" + Login, driver.getCurrentUrl());
        Assert.assertNotNull("Not autorized", driver.findElement(By.cssSelector("body > header > div > div > div > button")));
    }

    private void tryToLogin(String login, String password){
        WebElement loginText, passText, loginButton;

        driver.findElement(By.cssSelector("body > header > div > div > a")).click();

        loginText = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > input.gn-field-simple.rm-app-login"));
        passText = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > input.gn-field-simple.rm-app-password"));
        loginButton = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > button"));

        loginText.sendKeys(login);
        passText.sendKeys(password);
        loginButton.click();
    }
}
