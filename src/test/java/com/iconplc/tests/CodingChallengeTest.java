package com.iconplc.tests;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.iconplc.pageObjects.*;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.tinylog.Logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;


public class CodingChallengeTest {

    private WebDriver driver;
    private NewJobPO newJobPO;
    private DashboardPO dashboardPO;
    private AddNewCustomerPO addNewCustomerPO;
    private JobPO jobPO;
    private final Faker faker = new Faker();
    private LocalDateTime saveJobTime;

    @BeforeMethod
    public void init() {
        Logger.info("Setting up the CodingChallengeTest driver");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout( Duration.of(60L, ChronoUnit.SECONDS ) );
        driver.manage().timeouts().implicitlyWait( Duration.of(30L, ChronoUnit.SECONDS ) );
        driver.manage().window().maximize();

        Logger.info("Opening URL https://pro.housecallpro.com/pro/log_in");
        driver.get( "https://pro.housecallpro.com/pro/log_in" );
    }

    @Test
    public void test1() {
        login();
        navigateToAddNewCustomerPage();
        createCustomer();
        fillOutLineItem();
        addPrivateNotes();
        saveJob();
        checkActivityFeed();
        Logger.info( "Done!" );
    }

    public void login() {
        Logger.info("Logging into app...");
        var signInPO = new SignInPO( driver );
        dashboardPO = signInPO
                .setEmail( "tsp2opt@gmail.com" )
                .setPassword( "Winter22mute!" )
                .clickSignInBtn();
        Logger.info("... logged into app");
    }

    public void navigateToAddNewCustomerPage() {
        Logger.info("Navigating to Add New Customer page...");
        newJobPO = dashboardPO.clickNewJobBtn();
        addNewCustomerPO = newJobPO.clickNewCustomerBtn();
        Logger.info("...opened Add New Customer page");
    }

    public void createCustomer() {
        Logger.info("Creating a Customer...");
        var customerName = faker.name();
        var company = faker.company();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-US"), new RandomService() );
        String emailAddress = fakeValuesService.bothify("tsp2opt+????##@gmail.com" );

        Logger.debug("...adding Name, Mobile Phone Number, Company, Home Phone Number, Role and Email...");
        addNewCustomerPO
                .setFirstName( customerName.firstName() )
                .setLastName( customerName.lastName() )
                .setMobileNumber( "(858) 226-6418" )
                .setCompany( company.name() )
                .setHomeNumber( "(858) 566-9162" )
                .setRole( "Plumber" )
                .setEmail( emailAddress );

        Logger.debug("...adding Address...");
        var address = faker.address();
        addNewCustomerPO
                .setAddressStreet( address.streetAddressNumber() + " " + address.streetName() )
                .setAddressCity( address.city() )
                .selectState( address.stateAbbr() )
                .setAddressZip( address.zipCode() )
                .setAddressNotes("This is not a real address");

        Logger.debug("...adding Customer Note, This Customer Bills To, Customer Tag, and Lead Source...");
        addNewCustomerPO
                .setCustomerNotes( "The above is test data; it's not a real Customer" )
                .setThisCustomerBillsTo( "Nowhere" )
                .addCustomerTag( "TEST" )
                .setLeadSource( "Bob" );

        Logger.debug("...clicking CREATE CUSTOMER button");
        newJobPO = addNewCustomerPO.clickCreateCustomerBtn();
        Logger.info("Customer created.");
    }

    public void fillOutLineItem() {
        Logger.info("Filling out Line Item...");
        newJobPO.setLineItemName( "showerhead" );
        newJobPO.setLineItemQuantity( "2.00" );
        newJobPO.setLineItemUnitPrice( "25.00" );
        newJobPO.setLineItemUnitCost( "24.00" );
        Logger.info("...Line Item filled out.");

        SoftAssert softAssert = new SoftAssert();
        Logger.debug("Testing Line Cost value");
        softAssert.assertTrue( newJobPO.lineCostHasValue("$50.00" ), "LINE COST HAS BAD VALUE" );
        Logger.debug("Testing Subtotal value");
        softAssert.assertTrue( newJobPO.subtotalCostHasValue("$50.00" ),
               "SUBTOTAL COST HAS BAD VALUE" );
        Logger.debug("Testing Total value");
        softAssert.assertTrue( newJobPO.totalCostHasValue("$50.00" ),"TOTAL COST HAS BAD VALUE" );
        softAssert.assertAll();
        Logger.info("Line Item filled out.");
    }

    public void addPrivateNotes() {
        Logger.info("Writing a Private Note...");
        newJobPO.writePrivateNotes( "This is a test" );
        Logger.info("...Private Note written.");
    }

    public void saveJob() {
        Logger.info("Clicking the Save Job button...");
        jobPO = newJobPO.clickSaveJobBtn();
        Logger.info("Save Job button clicked.");

        Logger.info("Remembering the Date for later testing of Activity Feed.");
        saveJobTime = LocalDateTime.now();
    }

    public void checkActivityFeed() {
        Logger.info("Checking the Job Invoice Date in Activity Feed...");
        var dtf = DateTimeFormatter.ofPattern("EEE M/dd/yy");
        var saveJobDateTime = saveJobTime.format( dtf );
        Assert.assertTrue( jobPO.hasActivityFeedGotDate( saveJobDateTime ), "WRONG JOB INVOICE DATE" );
        Logger.info("Job Invoice Date checked.");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
