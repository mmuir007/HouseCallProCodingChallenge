package com.iconplc.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.tinylog.Logger;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NewJobPO extends BasePO {

    public NewJobPO( WebDriver driver ) {
        this.driver = driver;
        Assert.assertTrue(isOpened(), "NewJobPO IS NOT OPENED");
    }

    @Override
    public boolean isOpened() {
        List<WebElement> headerElemList = driver.findElements( By.xpath("//h5[text()='New job']" ) );
        boolean result = headerElemList.size() > 0;
        if( result ) {
            Logger.debug("NewJobPO page is opened.");
        }
        return result;
    }

    public AddNewCustomerPO clickNewCustomerBtn() {
        Logger.debug("Clicking New Customer button");
        WebElement addNewCustomerBtn = driver.findElement( By.xpath( "//span[text()='+ New customer']" ) );
        addNewCustomerBtn.click();
        return new AddNewCustomerPO( driver );
    }

    public NewJobPO setLineItemName( String itemName ) {
        Logger.debug("Setting Line Item Name to " + itemName );
        var wait=new WebDriverWait(driver, Duration.of(30L, ChronoUnit.SECONDS));
        WebElement itemNameElem = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath( "//input[@data-testid='line-item-name']" ) ) );
        sleep( SHORT_WAIT );
        itemNameElem.clear();
        itemNameElem.sendKeys( itemName );
        return this;
    }

    public NewJobPO setLineItemQuantity( String quantity ) {
        Logger.debug("Setting Line Item Quantity to " + quantity );
        WebElement itemQuantityElem = driver.findElement( By.id( "qty" ) );
        itemQuantityElem.sendKeys( Keys.HOME );
        itemQuantityElem.sendKeys( quantity );
        return this;
    }

    public NewJobPO setLineItemUnitPrice( String unitPrice ) {
        Logger.debug("Setting Line Item Unit Price to " + unitPrice );
        WebElement itemQuantityElem = driver.findElement( By.id( "unit-price" ) );
        itemQuantityElem.sendKeys( Keys.HOME );
        itemQuantityElem.sendKeys( unitPrice );
        return this;
    }

    public NewJobPO setLineItemUnitCost( String unitCost ) {
        Logger.debug("Setting Line Item Unit Cost to " + unitCost );
        WebElement itemUnitCostElem = driver.findElement(
                By.xpath( "//label[text()='Unit cost']/parent::div/div/input" ) );
        itemUnitCostElem.sendKeys( Keys.HOME );
        itemUnitCostElem.sendKeys( unitCost );
        return this;
    }

    public boolean lineCostHasValue( String expectedLineCost ) {
        Logger.debug("Checking if Line Cost has value " + expectedLineCost );
        sleep( SHORT_WAIT );

        var wait=new WebDriverWait(driver, Duration.of(30L, ChronoUnit.SECONDS));
        WebElement itemLineCostElem = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath( "//div/div[3]/p/span" ) ) );

        var actualLineCost = itemLineCostElem.getText();
        boolean result = expectedLineCost.equals( actualLineCost );
        if( !result ) {
            Logger.error("EXPECTED LINE COST DIFFERS FROM ACTUAL LINE COST");
            Logger.error("EXPECTED LINE COST = " + expectedLineCost );
            Logger.error("  ACTUAL LINE COST = " + actualLineCost );
            takeScreenshot( "lineCostHasValue.png" );
        }
        return result;
    }

    public boolean subtotalCostHasValue( String expectedSubtotalCost ) {
        Logger.debug("Checking if Subtotal has value " + expectedSubtotalCost );
        WebElement itemSubtotalCostElem = driver.findElement(
                By.xpath( "//p[text()='Subtotal']/parent::div/following-sibling::div/p" ) );

        var wait=new WebDriverWait(driver, Duration.of(30L, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.textToBePresentInElement(itemSubtotalCostElem, expectedSubtotalCost ) );

        var actualSubtotalCost = itemSubtotalCostElem.getText();
        boolean result = expectedSubtotalCost.equals( actualSubtotalCost );
        if( !result ) {
            Logger.error("EXPECTED SUBTOTAL DIFFERS FROM ACTUAL SUBTOTAL");
            Logger.error("EXPECTED SUBTOTAL = " + expectedSubtotalCost );
            Logger.error("  ACTUAL SUBTOTAL = " + actualSubtotalCost );
            takeScreenshot("subtotalCostHasValue.png" );
        }
        return result;
    }

    public boolean totalCostHasValue( String expectedTotalCost ) {
        Logger.debug("Checking if Total has value " + expectedTotalCost );
        WebElement itemTotalCostElem = driver.findElement(
                By.xpath( "//h6[@data-testid='lineItems__totalAmount']/span" ) );

        var wait=new WebDriverWait(driver, Duration.of(30L, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.textToBePresentInElement(itemTotalCostElem, expectedTotalCost ) );
        var actualTotalCost = itemTotalCostElem.getText();
        boolean result = expectedTotalCost.equals( actualTotalCost );
        if( !result ) {
            Logger.error("EXPECTED TOTAL DIFFERS FROM ACTUAL TOTAL");
            Logger.error("EXPECTED TOTAL = " + expectedTotalCost );
            Logger.error("  ACTUAL TOTAL = " + actualTotalCost );
            takeScreenshot( "totalCostHasValue.png" );
        }
        return result;
    }

    public NewJobPO writePrivateNotes( String privateNotes ) {
        Logger.debug( "Adding Private Notes with value '" + privateNotes + "'" );
        WebElement openPrivateNotes = driver.findElement( By.xpath( "//p[text()='Private notes']" ) );
        openPrivateNotes.click();
        var wait=new WebDriverWait(driver, Duration.of(30L, ChronoUnit.SECONDS));
        WebElement privateNotesElem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath( "//textarea[@data-testid='private-notes-textfield']" )
        ));
        privateNotesElem.clear();
        privateNotesElem.sendKeys( privateNotes );
        return this;
    }

    public JobPO clickSaveJobBtn() {
        Logger.debug( "Clicking the Save Job button");
        WebElement saveJobElem = driver.findElement(
                By.xpath( "//button[@data-testid='createJobContainer__saveBtn']" ) );
        saveJobElem.click();
        return new JobPO( driver );
    }
}
