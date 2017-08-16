import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by saadabdullah on 8/15/17.
 */
public class RegistrationTestScript {

    WebDriver driver;

    @Before
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    @Test
    public void test(){
        navigateToPage();

        closePopUp();
        varifyHomePage();
        dealyFor(1000);

        clickAccountLink();
        closePopUp();
        varifyRegisterPage();
        dealyFor(1000);

        clickRegisterLink();
        closePopUp();
        varifyCreateNewUserPage();
        dealyFor(1000);

        registerUser();







    }


    @After
    public void tearDown(){

        //driver.close();
        //driver.quit();
    }

    public void dealyFor(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void navigateToPage(){

        driver.navigate().to("https://www.eversource.com");

    }

    public void closePopUp(){


        ExplicitWaitUtils wait = new ExplicitWaitUtils(driver);

        WebElement dialougeBox = null;

        try {

            dialougeBox = wait.waitForElement(By.xpath("//div[contains(@class, 'modal-content col-xs-12')]"), 10);

            //dialougeBox = driver.findElement(By.xpath("//div[contains(@class, 'modal-content col-xs-12')]"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        if(dialougeBox != null){
            WebElement closeButton = driver.findElement(By.xpath("//div[contains(@class, 'modal-content col-xs-12')]/div/button"));
            closeButton.click();
        }else {
            System.out.println(" continue");
        }

    }


    public void varifyHomePage(){
        String expected = "Eversource | Residential";
        String actual = driver.getTitle().trim();
        Assert.assertEquals(expected,actual);
    }

    public void clickAccountLink(){
        driver.findElement(By.xpath("//ul[@class='nav nav-tabs']/li/a[text()='My Account']")).click();
    }

    public void varifyRegisterPage() {
        String expected = "Eversource | Log In";
        String actual = driver.getTitle().trim();
        Assert.assertEquals(expected, actual);
    }

    public void clickRegisterLink(){
        driver.findElement(By.xpath("html/body/div[1]/div/div[3]/div/div/div/div/div/div/form/div/div[2]/input")).click();
    }

    public void varifyCreateNewUserPage() {
        String expected = "Eversource | Registration - Create new User ID";
        String actual = driver.getTitle().trim();
        Assert.assertEquals(expected, actual);
    }

    public void registerUser(){
        WebElement firstName = driver.findElement(By.xpath(".//*[@id='FirstName']"));
        firstName.sendKeys("Saad");

        WebElement lastName = driver.findElement(By.xpath(".//*[@id='LastName']"));
        lastName.sendKeys("Abdullah");

        WebElement email = driver.findElement(By.xpath(".//*[@id='Email']"));
        email.sendKeys("saadabdullah@mail.com");

        WebElement confirEmail = driver.findElement(By.xpath(".//*[@id='ConfirmEmail']"));
        confirEmail.sendKeys("saadabdullah@mail.com");

        WebElement userID = driver.findElement(By.xpath(".//*[@id='WebId']"));
        userID.sendKeys("Saad123");

        WebElement password = driver.findElement(By.xpath(".//*[@id='Password'] "));
        password.sendKeys("1234567asdf");

        WebElement confirmPassword = driver.findElement(By.xpath(".//*[@id='ConfirmPassword']"));
        confirmPassword.sendKeys("1234567asdf");

        WebElement dropDownBoxBUtton = driver.findElement(By.xpath("//button[@class='btn dropdown-toggle selectpicker btn-select']"));
        dropDownBoxBUtton.click();



       //TODO : Write code for dropdown

        WebElement secretAns = driver.findElement(By.xpath(".//*[@id='SecretAnswer']"));
        secretAns.sendKeys("Enargy");


    }

}
