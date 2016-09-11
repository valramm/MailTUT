package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class SampleTestNgTest {

    private WebDriver driver;

    @BeforeTest
    public void setupSelenium() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void MailTest() {

        driver.get("https://tut.by/");

        String login = "LindeTill@tut.by";
        String pass = "LindeTill1984";
        String count;

        driver.findElement(By.xpath("//a[contains(@data-target-popup,'authorize')]")).click();
        driver.findElement(By.xpath("//input[@name='login']")).clear();
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='password']")).clear();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
        driver.findElement(By.xpath("//input[contains(@class,'auth__enter')]")).click();
        driver.findElement(By.xpath("//a[contains(@href,'mail') and contains(@class,'topbar__link')]")).click();

        count = driver.findElement(By.xpath("//span/a[contains(@href,'inbox')]")).getText();

        if (count != null) {
            out.println("You have " + count + " unread messages");
        } else {
            out.println("You have no unread messages");
        }
    }

    @AfterTest
    public void closeSelenium() {
        driver.quit();
    }
}