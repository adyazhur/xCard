package Tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseTest {

    private WebDriver driver;

    public void OpenDemoSite(WebDriver driver) {
        driver.get("https://demostore.x-cart.com/");
    }

    private static final String chromeDriverPath= "C:\\SeleniumTests\\xCard\\src\\resorces\\chromedriver.exe";

    @BeforeClass
    public  static void beforeClassSetUp() throws Exception{
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,chromeDriverPath);
    }

    @Before
    public void setUp() throws Exception {
        driver = getDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    /**
     * # <h1>Login to user's regular account</h1>
     * # open site <br/>
     * # open login pop-up.<br/>
     * # enter credentials.<br/>
     * # Submit form.<br/>
     * # <b>Expected result:</b> user logged into account.
     */

    @Test
    public void ValidLoginTest() throws Exception {

        OpenDemoSite(driver);
        driver.findElement(By.xpath("//*[@class='header_bar-sign_in']")).click();
        WebElement loginInput = new WebDriverWait(driver,60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='login-email']")));
        loginInput.sendKeys("Email");
        driver.findElement(By.xpath("//*[@id='login-email']")).sendKeys("login");
        driver.findElement(By.xpath("//*[@id='login-password']")).sendKeys("Password");
        driver.findElement(By.xpath("//*[@class='btn  regular-button  submit']/span[text()=\"Sign in\"]")).click();

        Assert.assertTrue("Customer should be logged in", false);
    }

    @Test
    public void ValidLoginToAdmin() throws Exception {

        OpenDemoSite(driver);
        driver.findElement(By.xpath("//*[@class='tab admin']")).click();
        driver.findElement(By.xpath("//*[@name='login']")).sendKeys("bit-bucket@x-cart.com");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("master");
        driver.findElement(By.xpath("//*[@class='btn  regular-button regular-main-button submit']")).click();

        Assert.assertTrue("Customer should be logged in Admin area", false);
    }

    public WebDriver getDriver() {
        return new ChromeDriver();
    }
}
