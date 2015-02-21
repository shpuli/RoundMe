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

public class RegisterTest {
    public static String StartUrl = "http://preproduction.round.me/";
    private final String Login = "shpulitest";
    private final String Password = "123456";
    private static WebDriver driver;

    @Before
    public void TestInitialize(){
        driver = new FirefoxDriver();
        driver.get(StartUrl);
    }

        @After
        public void TestClose(){
      //      driver.quit();
        }

    @Test
    public void CorrectRegisterTest(){
        driver.findElement(By.cssSelector("body > header > div > div > button")).click();
        driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container.join > form > input.gn-field-simple.rm-app-username"))
                .sendKeys(Login);
        driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container.join > form > input.gn-field-simple.rm-app-email"))
                .sendKeys(Login + "@gmail.com");
        driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container.join > form > input.gn-field-simple.rm-app-password"))
                .sendKeys(Password);
        driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container.join > form > button"))
                .click();

        Assert.assertEquals(StartUrl + "@" + Login, driver.getCurrentUrl());

        Assert.assertNotNull(driver.findElement(By.cssSelector("body > header > div > div > div > button")));
    }

    @Test
    public void AutorizeTest(){
        WebElement loginText, passText, loginButton;

        driver.findElement(By.cssSelector("body > header > div > div > a")).click();

        loginText = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > input.gn-field-simple.rm-app-login"));
        passText = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > input.gn-field-simple.rm-app-password"));
        loginButton = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > button"));

        loginText.sendKeys(Login);
        passText.sendKeys(Password);
        loginButton.click();

        Assert.assertEquals(StartUrl + "@" + Login, driver.getCurrentUrl());

        Assert.assertNotNull(driver.findElement(By.cssSelector("body > header > div > div > div > button")));

        driver.findElement(By.cssSelector("body > header > div > div > div > a > span.drop-arrow")).click();
    }

    @Test
    public void ChangePassTest(){
        WebElement loginText, passText, loginButton;

        driver.findElement(By.cssSelector("body > header > div > div > a")).click();

        loginText = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > input.gn-field-simple.rm-app-login"));
        passText = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > input.gn-field-simple.rm-app-password"));
        loginButton = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > button"));

        loginText.sendKeys(Login);
        passText.sendKeys(Password);
        loginButton.click();

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

        Assert.assertNotNull(driver.findElement(By.cssSelector("div > div > div.ui-pnotify-text")));

        driver.findElement(By.cssSelector("body > header > div > div > div > a > span.drop-arrow")).click();
    }
}
