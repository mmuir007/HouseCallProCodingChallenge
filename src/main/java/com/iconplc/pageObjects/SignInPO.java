package com.iconplc.pageObjects;

import com.iconplc.util.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;


public class SignInPO extends BasePO {

    public SignInPO( WebDriver driver ) {
        this.driver = driver;
        Assert.assertTrue(isOpened(), "SignInPO page IS NOT OPENED");
    }

    @Override
    public boolean isOpened() {
        boolean result = false;
        List <WebElement> emailElemList = driver.findElements(By.id("email"));
        List <WebElement> passwElemList = driver.findElements(By.id("password"));
        result = emailElemList.size() > 0 && passwElemList.size() > 0;
        if ( result ) {
            Logger.debug("SignInPO page is opened");
        }
        return result;
    }

    public SignInPO setEmail( String email ) {
        Logger.debug("Setting email value to " + email);
        WebElement emailElem = driver.findElement(By.id("email"));
        emailElem.clear();
        emailElem.sendKeys( email );
        return this;
    }

    public SignInPO setPassword( String password ) {
        Logger.debug("Setting password value to " + password);
        WebElement passwordElem = driver.findElement(By.id("password"));
        passwordElem.clear();
        passwordElem.sendKeys( password );
        return this;
    }

    public DashboardPO clickSignInBtn() {
        Logger.debug("Clicking Sign In button");
        WebElement signInElem = driver.findElement(By.xpath("//span[text()='Sign in']"));
        signInElem.click();
        return new DashboardPO( driver );
    }
}
