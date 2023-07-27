package com.iconplc.pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Base class for the Page Object (PO) classes.
 */
public abstract class BasePO {

    protected WebDriver driver;

    protected static final long SHORT_WAIT = 5000L;

    /**
     * Returns true if the page is displayed, else false.
     *
     * @return true if the page is displayed, else false.
     */
    public abstract boolean isOpened();

    public void sleep( long waitTimeInMillis ) {
        try {
            Logger.debug( "Sleeping for " + waitTimeInMillis + " milliseconds" );
            Thread.sleep( waitTimeInMillis );
        } catch( InterruptedException e ) {
            // do nothing
        }
    }

    public void takeScreenshot( String fileName ) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs( OutputType.FILE );
        try {
            FileUtils.copyFile( source, new File( fileName ) );
            Logger.error("WROTE SCREENSHOT TO Screen.png");
        } catch( IOException ioe ) {
            System.err.println( "ERROR " + ioe.getMessage() );
            ioe.printStackTrace( System.err );
        }
    }
}
