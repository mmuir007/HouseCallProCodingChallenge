package com.iconplc.pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.tinylog.Logger;

import java.util.List;

public class JobPO extends BasePO {

    public JobPO( WebDriver driver ) {
        this.driver = driver;
        Assert.assertTrue( isOpened(), "JobPO PAGE IS NOT OPENED" );
    }

    @Override
    public boolean isOpened() {
        List<WebElement> headerElemList = driver.findElements( By.xpath( "//span[text()='Job']" ) );
        boolean result = headerElemList.size() > 0;
        if ( result ) {
            Logger.debug("JobPO page is opened.");
        }
        return result;
    }

    public boolean hasActivityFeedGotDate( String date ) {
        Logger.info("Checking for date in Job Invoice data in Activity Feed...");
        boolean result = false;
        var xpath = "//p[contains(.,'Job created')]/parent::div/parent::div/parent::div/following-sibling::div/p";
        List<WebElement> jobCreatedElemList = driver.findElements( By.xpath( xpath ) );
        if ( jobCreatedElemList.size() > 0 ) {
            var elemText = jobCreatedElemList.get(0).getText();
            if ( elemText.contains( date ) ) {
                result = true;
            } else {
                Logger.error("  EXPECTED JOB CREATION DATE IS " + date );
                Logger.error("ACTUAL JOB CREATION DATETIME IS " + elemText );

                takeScreenshot("hasActivityFeedGotDate.png" );
            }
        }
        return result;
    }
}
