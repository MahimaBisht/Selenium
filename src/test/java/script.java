
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class script {

	private objectRepo repoInsts;
	WebDriverWait wait;
	WebDriver driver;
		
		@BeforeTest
		public void setup() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\mahbisht\\Documents\\WORK_SPACES_STS\\DRIVERS\\chromedriver.exe");
			driver = new ChromeDriver();

			//implicit wait examples
			//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			
			
			driver.manage().window().maximize();
			driver.navigate().to("https://www.makemytrip.com/");
			
			for(int i=0;i<10;i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SHIFT,Keys.SUBTRACT));
			}
			
			repoInsts = new objectRepo(driver);
			wait = new WebDriverWait(driver,60);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(repoInsts.loginModal));
				Assert.assertEquals(repoInsts.presenceOfLoginModal(), true);
				if(repoInsts.loginModal.isDisplayed()) {
					repoInsts.ignoreOuterLoginModal();
				}
			}catch(Exception e) {System.out.println("exception in ignoring login box "+e);}
			
			if (driver.findElement(By.xpath("//div[@class='langCard  fixedCard bounceAni']")).isDisplayed()==true) {
				driver.findElement(By.className("langCardClose")).click();
			}
		}
		@Test
		public void testTitle() {
			Assert.assertEquals(driver.getTitle(), "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");
		}
		
		@Test
		public void testnothing() throws Exception{
			try {
				wait.until(ExpectedConditions.elementToBeClickable(repoInsts.oneway));
				repoInsts.selectOneway();
				wait.until(ExpectedConditions.visibilityOf(repoInsts.fromCity));
				repoInsts.checkForFromCity();
				repoInsts.clickOnFromCity();
				repoInsts.clickCityInDropdown();
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOf(repoInsts.toCity));
				repoInsts.clickOnToCity();
			}catch(Exception e) {System.out.println("1st exception"+e);}
			try {
				repoInsts.clickToCityInDropdown();
			}catch(Exception e) {System.out.println("exception under clickToCityInDropdown "+e);}
			try {
//				System.out.println("click on departure");
				repoInsts.clickOnDeparture();
//				System.out.println("find month");
				repoInsts.findMonth();
//				System.out.println("click on day");
				repoInsts.findNClickDay();
//				System.out.println("clicked day");
			}catch(Exception e) {System.out.println("exception "+e);}
		}
		@Test
		public void testWarning() {
			try {
				
				repoInsts.clickOnTvlrNClass();
				repoInsts.clickOnAdultCount();
				repoInsts.clickOnChildrenCount();
				repoInsts.clickOnInfantCount();
				repoInsts.clickOntvrlClass("Premium Economy");
			}
			catch(Exception e) 
				{
					System.out.println("exception under test warning "+e);
				}
			Assert.assertEquals(repoInsts.getwarning(), "Number of infants cannot be more than adults");		
			repoInsts.clickOnApply();
			}
		@Test
		public void testNoWarning() {
			try {
				repoInsts.clickOnTvlrNClass();
				}
			catch(StaleElementReferenceException e) {
					System.out.println(e);
				}
			try {
				repoInsts.clickOnAdultCount();
				repoInsts.clickOnChildrenCount();
				repoInsts.clickOnInfantCount();
				Assert.assertEquals(repoInsts.isWarningDisplayed(), false);
			}
			catch(ElementClickInterceptedException e) {
				System.out.println(e);
			}
			catch(TimeoutException e) {
				System.out.println(e);
			}
			catch(NoSuchElementException e) 
			{
				Assert.assertEquals(e.toString().contains("org.openqa.selenium.NoSuchElementException:"), true);
			}
			repoInsts.clickOnApply();
		}
		@AfterTest
		public void clearUp() throws InterruptedException {
			Thread.sleep(5000);
			
		}
}
