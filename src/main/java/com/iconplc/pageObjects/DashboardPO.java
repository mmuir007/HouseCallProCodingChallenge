package com.iconplc.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.tinylog.Logger;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DashboardPO extends BasePO {

    private static final String NEW_JOB_BTN_XPATH = "//span[text()='New']";

    public DashboardPO( WebDriver driver ) {
        this.driver = driver;
        Assert.assertTrue( isOpened(), "DashboardPO IS NOT OPENED");
    }

    @Override
    public boolean isOpened() {
        List<WebElement> emailElemList = driver.findElements( By.xpath( NEW_JOB_BTN_XPATH ) );
        boolean result = emailElemList.size() > 0;
        if ( result ) {
            Logger.debug("DashboardPO page is opened.");
        }
        return result;
    }

    public NewJobPO clickNewJobBtn() {
        Logger.debug("Opening the New menu");
        var signInElem = driver.findElement( By.xpath( NEW_JOB_BTN_XPATH ) );
        signInElem.click();

        var wait=new WebDriverWait(driver, Duration.of(30L, ChronoUnit.SECONDS));
        WebElement jobListItemElem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath( "//li[@data-dd-action-name='navbar-new-job-button']" )
        ));
        Logger.debug("Clicking the Job option");
        jobListItemElem.click();
        return new NewJobPO( driver );
    }

}
