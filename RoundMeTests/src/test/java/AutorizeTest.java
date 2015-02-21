/**
 * Created by shpuli on 21.02.2015.
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class AutorizeTest {

    public static String StartUrl = "http://preproduction.round.me/";
    private final String Login = "shpuli";
    private final String Password = "123456";
    private static WebDriver driver;

    private static WebElement loginText;
    private static WebElement passText;
    private static WebElement loginButton;

    @Before
    public void TestInitialize(){
        driver = new FirefoxDriver();
        driver.get(StartUrl);
        driver.findElement(By.cssSelector("body > header > div > div > a")).click();

        loginText = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > input.gn-field-simple.rm-app-login"));
        passText = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > input.gn-field-simple.rm-app-password"));
        loginButton = driver.findElement(By.cssSelector("body > div.rm-modal > div > div.rm-modal-box > div > div.modal-container > form > button"));
    }
/*
    @After
    public void TestClose(){}
*/
    @Test
    public void AutorizeWithLoginTest() throws InterruptedException {
        loginText.sendKeys(Login);
        passText.sendKeys(Password);
        loginButton.click();

        Assert.assertEquals(StartUrl + "@" + Login, driver.getCurrentUrl());

        Assert.assertNotNull(driver.findElement(By.cssSelector("body > header > div > div > div > button")));
    }
}
