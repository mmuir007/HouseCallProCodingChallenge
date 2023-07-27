package com.iconplc.pageObjects;

import com.iconplc.enums.CustomerLocationType;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tinylog.Logger;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AddNewCustomerPO extends BasePO {

    public AddNewCustomerPO( WebDriver driver ) {
        this.driver = driver;
        Assert.isTrue(isOpened(), "AddNewCustomerPO IS NOT OPENED");
    }

    @Override
    public boolean isOpened() {
        List<WebElement> headerElemList = driver.findElements( By.xpath( "//h2[text()='Add new customer']" ) );
        boolean result = headerElemList.size() > 0;
        if ( result ) {
            Logger.debug("AddNewCustomerPO page is opened");
        }
        return result;
    }

    public AddNewCustomerPO setFirstName( String firstName ) {
        Logger.debug("Setting First Name to " + firstName );
        WebElement firstNameElem = driver.findElement( By.id( "customer-dialog-first-name" ) );
        firstNameElem.clear();
        firstNameElem.sendKeys( firstName );
        return this;
    }

    public AddNewCustomerPO setLastName( String lastName ) {
        Logger.debug("Setting Last Name to " + lastName );
        WebElement lastNameElem = driver.findElement( By.name( "last_name" ) );
        lastNameElem.clear();
        lastNameElem.sendKeys( lastName );
        return this;
    }

    public AddNewCustomerPO setMobileNumber( String mobileNumber ) {
        Logger.debug("Setting Mobile Phone Number to " + mobileNumber );
        WebElement mobileNumberElem = driver.findElement( By.name( "mobile_number" ) );
        mobileNumberElem.clear();
        mobileNumberElem.sendKeys( mobileNumber );
        return this;
    }

    public AddNewCustomerPO setCompany( String companyName ) {
        Logger.debug("Setting Company Name to " + companyName );
        WebElement companyElem = driver.findElement( By.name( "company" ) );
        companyElem.clear();
        companyElem.sendKeys( companyName );
        return this;
    }

    /**
     * Display Name is the name shown on Invoices.
     *
     * @param displayName  The Name to be shown on Invoices
     * @return - the AddNewCustomerPO page object.
     */
    public AddNewCustomerPO setDisplayName( String displayName ) {
        Logger.debug("Setting Display Name to " + displayName );
        WebElement displayNameElem = driver.findElement( By.name( "company" ) );
        displayNameElem.clear();
        displayNameElem.sendKeys( displayName );
        return this;
    }

    public AddNewCustomerPO setHomeNumber( String homePhoneNumber ) {
        Logger.debug("Setting Home Phone Number to " + homePhoneNumber );
        WebElement homePhoneNumberElem = driver.findElement( By.name( "home_number" ) );
        homePhoneNumberElem.clear();
        homePhoneNumberElem.sendKeys( homePhoneNumber );
        return this;
    }

    public AddNewCustomerPO setRole( String jobTitle ) {
        Logger.debug("Setting Role to " + jobTitle );
        WebElement roleElem = driver.findElement( By.name( "job_title" ) );
        roleElem.clear();
        roleElem.sendKeys( jobTitle );
        return this;
    }

    public AddNewCustomerPO setEmail( String emailAddress ) {
        Logger.debug("Setting Email to " + emailAddress );
        WebElement emailAddressElem = driver.findElement( By.name( "email" ) );
        emailAddressElem.clear();
        emailAddressElem.sendKeys( emailAddress );
        return this;
    }

    public AddNewCustomerPO setWorkNumber( String workPhoneNumber ) {
        Logger.debug("Setting Work Phone Number to " + workPhoneNumber );
        WebElement workPhoneNumberElem = driver.findElement( By.name( "work_number" ) );
        workPhoneNumberElem.clear();
        workPhoneNumberElem.sendKeys( workPhoneNumber );
        return this;
    }

    public AddNewCustomerPO selectCustomerLocationType( CustomerLocationType customerLocationType ) {
        Logger.debug("Setting Customer Location Type to " + customerLocationType.getText() );
        var xpath = String.format( "//span[text()='%s']", customerLocationType.getText() );
        WebElement customerLocationTypeElem = driver.findElement(By.xpath(xpath));
        customerLocationTypeElem.click();
        return this;
    }

    public AddNewCustomerPO clickEmailBtn() {
        Logger.debug("Clicking the + Email button");
        WebElement emailBtnElem = driver.findElement( By.xpath( "//span[text()='+ Email']" ) );
        emailBtnElem.click();
        return this;
    }

    public AddNewCustomerPO clickPhoneBtn() {
        Logger.debug("Clicking the + Phone button");
        WebElement phoneBtnElem = driver.findElement( By.xpath( "//span[text()='+ Phone']" ) );
        phoneBtnElem.click();
        return this;
    }

    public AddNewCustomerPO setAddressStreet( String street ) {
        Logger.debug("Setting Street to " + street );
        WebElement addressStreetElem = driver.findElement( By.id("service_addresses[0].streetAutoComplete" ) );
        addressStreetElem.clear();
        addressStreetElem.sendKeys( street );
        return this;
    }

    public AddNewCustomerPO setAddressUnit( String unit ) {
        Logger.debug("Setting Unit to " + unit );
        WebElement addressUnitElem = driver.findElement( By.name("service_addresses[0].street_line_2" ) );
        addressUnitElem.clear();
        addressUnitElem.sendKeys( unit );
        return this;
    }

    public AddNewCustomerPO setAddressCity( String city ) {
        Logger.debug("Setting City to " + city );
        WebElement addressCityElem = driver.findElement( By.name("service_addresses[0].city" ) );
        addressCityElem.click();
        return this;
    }

    public AddNewCustomerPO selectState( String stateAbbrev ) {
        Logger.debug("Selecting State to " + stateAbbrev );
        var stateDivElem = driver.findElement( By.id( "mui-component-select-service_addresses[0].state" ) );
        stateDivElem.click();
        sleep( SHORT_WAIT );
        var xpath = String.format("//li[@data-value='%s']", stateAbbrev );
        var wait = new WebDriverWait( driver, Duration.of(30L, ChronoUnit.SECONDS ) );
        wait.until( ExpectedConditions.presenceOfElementLocated( By.xpath( xpath ) ) );
        WebElement stateLiElem = wait.until( ExpectedConditions.elementToBeClickable( By.xpath( xpath ) ) );
        stateLiElem.click();
        return this;
    }

    public AddNewCustomerPO setAddressZip( String zip ) {
        Logger.debug("Setting Zip to " + zip );
        WebElement addressZipElem = driver.findElement( By.name( "service_addresses[0].zip" ) );
        addressZipElem.clear();
        addressZipElem.sendKeys( zip );
        return this;
    }

    public AddNewCustomerPO setAddressNotes( String notes ) {
        Logger.debug("Setting Address Notes to " + notes );
        WebElement addressNotesElem = driver.findElement( By.name( "service_addresses[0].notes" ) );
        addressNotesElem.clear();
        addressNotesElem.sendKeys( notes );
        return this;
    }

    public AddNewCustomerPO clickAddressBtn() {
        Logger.debug("Clicking + Address button" );
        WebElement addressBtnElem = driver.findElement( By.xpath( "//span[text()='+ Address']" ) );
        addressBtnElem.click();
        return this;
    }

    public AddNewCustomerPO setCustomerNotes( String customerNotes ) {
        Logger.debug("Setting Customer Notes to " + customerNotes );
        WebElement customerNotesElem = driver.findElement( By.name( "notes" ) );
        customerNotesElem.clear();
        customerNotesElem.sendKeys( customerNotes );
        return this;
    }

    public AddNewCustomerPO setThisCustomerBillsTo( String thisCustomerBillsTo ) {
        Logger.debug("Setting ThisCustomerBillsTo to " + thisCustomerBillsTo );
        WebElement customerBillsToElem = driver.findElement(
                By.xpath( "//label[text()='This customer bills to']/following-sibling::div/input" ) );
        customerBillsToElem.clear();
        customerBillsToElem.sendKeys( thisCustomerBillsTo );
        return this;
    }

    public AddNewCustomerPO addCustomerTag( String tag ) {
        Logger.debug("Adding a Customer tag " + tag );
        WebElement customerTagElem = driver.findElement( By.xpath( "//input[@data-testid='customer-tag-selector']" ) );
        customerTagElem.clear();
        customerTagElem.sendKeys( tag );
        customerTagElem.sendKeys( Keys.ENTER );
        return this;
    }

    public AddNewCustomerPO setLeadSource( String leadSource ) {
        Logger.debug("Setting a Lead Source " + leadSource );
        WebElement customerNotesElem = driver.findElement(
                By.xpath( "//input[@data-testid='lead-sources-autocomplete']" ) );
        customerNotesElem.clear();
        customerNotesElem.sendKeys( leadSource );
        return this;
    }

    public AddNewCustomerPO clickSendNotifications() {
        Logger.debug("Clicking Send Notifications checkbox" );
        WebElement sendNotificationsElem = driver.findElement(
                By.xpath( "//span[text()='Send notifications']/preceding-sibling::div/input" ) );
        sendNotificationsElem.click();
        return this;
    }

    public NewJobPO clickCancelBtn() {
        Logger.debug("Clicking the Cancel button");
        WebElement cancelBtnElem = driver.findElement( By.xpath( "//span[text()='Cancel']" ) );
        cancelBtnElem.click();
        return new NewJobPO( driver );
    }

    public NewJobPO clickCreateCustomerBtn() {
        Logger.debug("Clicking the Create Customer button");
        WebElement submitBtnElem = driver.findElement( By.xpath( "//button[@type='submit']" ) );
        submitBtnElem.click();
        return new NewJobPO( driver );
    }
}
