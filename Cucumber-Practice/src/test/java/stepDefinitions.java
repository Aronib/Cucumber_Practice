import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class stepDefinitions {

    public WebDriver driver;
    public WebDriverWait wait;
    int timeoutInSeconds = 10;  // Replace with your desired timeout value

    Duration timeout = Duration.ofSeconds(timeoutInSeconds);
    @Given("User visits e-commerce website.")
    public void user_visits_e_commerce_website() throws Exception{
        // Write code here that turns the phrase above into concrete actions

        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops=new FirefoxOptions();
        ops.addArguments("--headed"); //uncomment if you want to run in headless mode
        driver = new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://www.automationpractice.pl/index.php?");
    }
    @When("User enters valid {string} and valid {string}.")
    public void user_enters_valid_credentials(String email, String password) throws Exception{
        // Write code here that turns the phrase above into concrete actions
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated (By.className("login")));// wait until getting the login button
        WebElement btnLogin=driver.findElement(By.className("login"));
        btnLogin.click();
        WebElement txtEmail=driver.findElement(By.id("email"));
        txtEmail.sendKeys(email);
        WebElement txtPassword=driver.findElement(By.id("passwd"));
        txtPassword.sendKeys(password);
        WebElement btnSubmitLogin=driver.findElement(By.id("SubmitLogin"));
        btnSubmitLogin.click();
    }
    @Then("Users logged in succesfully.")
    public void users_logged_in_succesfully() throws Exception{
        // Write code here that turns the phrase above into concrete actions
        WebElement lblUserName=driver.findElement(By.xpath("//span[contains(text(),'Aro R')]"));
        Assert.assertEquals(lblUserName.getText(),"Aro R");
    }
    @After
    public void closeBrowser(){
        driver.quit();
    }

}
