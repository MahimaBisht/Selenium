import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.google.common.io.Files;

public class Xpath_practice {

	WebDriver driver;
	//xpath="//li[@data-cy='oneWayTrip']"
	//xpath="//li[@data-cy='roundTrip']"
	//xpath="//li[@data-cy='mulitiCityTrip']"
	
	@FindBy(xpath="//section[@class='modalMain ']")
	static WebElement emailSection;
	
	public static boolean isEmailSectionDisplayed() {
		return emailSection.isDisplayed();
	}
	
//	xpath to get selected element: //div[@class='mBizAccount__header']//child::p[1] ITS NOT WORKING
//	p[text()='BACK TO PERSONAL ACCOUNT'] not working
	//*[contains(text(),'BACK TO PERSONAL ACCOUNT')] not working
	
	@FindBy(xpath="//*[contains(@class,'leftPersonalAccount')]")
	static WebElement bckToPersonal;
	
	public static String clickOnBckToPersonal() {
		bckToPersonal.click();bckToPersonal.click();
		return bckToPersonal.getText();
	}
	@FindBy(xpath="//li[@data-cy='mulitiCityTrip']//preceding::li")
	static WebElement oneway;
	
	public static String clickOneway() {
		oneway.click();
		return oneway.getText();
	}
	
	@FindBy(how = How.XPATH, using="//*[@id='SW']/div[1]/div[1]/ul/li[3]")
	static WebElement loginBtn;
	public static void ignoreOuterLoginModal() {
		loginBtn.click();
	}
	
	Xpath_practice(){
		driver = new ChromeDriver();
		PageFactory.initElements(driver, this);	
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mahbisht\\Documents\\WORK_SPACES_STS\\DRIVERS\\chromedriver_1.exe");
		Xpath_practice xpath = new Xpath_practice();
		xpath.driver.get("https://www.makemytrip.com/");
		xpath.driver.manage().window().maximize();
		xpath.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		ignoreOuterLoginModal();
		try {
			if(isEmailSectionDisplayed()==true) {
				System.out.println("email section displayed");
				if(bckToPersonal.isEnabled()) {
					System.out.println("inside clickbcktopersonal");
					System.out.println(clickOnBckToPersonal());
				}
			}
		}
		catch(Exception e) {System.out.println(e);System.out.println("inside catch 1");}
		try {
			
			System.out.println(clickOneway());
			TakesScreenshot ss = (TakesScreenshot)xpath.driver;
			File ssFile=ss.getScreenshotAs(OutputType.FILE);
			File destFile= new File("C:\\Users\\mahbisht\\Pictures\\Screenshots\\xpathSS.png");
			Files.copy(ssFile,destFile);
		}
		catch(Exception e) { System.out.println(e);}
		
		Thread.sleep(5000);
//		xpath.driver.quit();
	}

}
