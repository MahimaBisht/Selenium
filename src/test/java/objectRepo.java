import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class objectRepo {
	
	WebDriver repoDriver;
	Actions act;
	int position;
	WebDriverWait wait;
	Boolean flag;
	Actions action;
	
	objectRepo(WebDriver drive){
		PageFactory.initElements(drive, this);
		repoDriver = drive;
		wait= new WebDriverWait(repoDriver, 30);
		action = new Actions(repoDriver);
	}
	
// types of relative xpaths

	@FindBy(how = How.XPATH,using="//li[@data-cy='oneWayTrip']")
	WebElement oneway;
	@FindBy(how = How.XPATH,using="//*[@id='root']/div/div[2]/div/div[1]/div[1]/ul/li[2]")
	WebElement roundTrip;
	@FindBy(how = How.XPATH,using="//*[@id=\"root\"]/div/div[2]/div/div[1]/div[1]/ul/li[3]")
	WebElement multicity;
	@FindBy(how=How.XPATH,using="//*[@id='SW']/div[1]/div[1]/ul/li[3]/div[3]")
	WebElement loginModal;
	
	@FindBy(how = How.XPATH, using="//*[@id='SW']/div[1]/div[1]/ul/li[3]")
	WebElement loginBtn;
	public void ignoreOuterLoginModal() {
		loginBtn.click();
	}
	public boolean presenceOfLoginModal() {
		return loginModal.isDisplayed();
	}
	public void selectOneway() {
		oneway.click();
	}
	public void selectRoundTrip() {
		roundTrip.click();
	}
	public void selectMulticity() {
		multicity.click();
	}

	@FindBy(how = How.XPATH, using="//*[@id=\"root\"]/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/label")
	WebElement fromCity;
	
	public void checkForFromCity() {
		fromCity.isDisplayed();
	}
	public void clickOnFromCity() {
		fromCity.click();
	}
	
	@FindBy(how=How.XPATH, using="//p[text()='Chhatrapati Shivaji International Airport']")
	WebElement cityInDropdown;
	
	public void clickCityInDropdown(){
//		System.out.println("fromcity clicked"+cityInDropdown);
		cityInDropdown.click();
	}
	public String getFromCity() {
		return fromCity.getText();
	}
	
	@FindBy(how= How.XPATH, using="//*[@id=\"root\"]/div/div[2]/div/div[1]/div[2]/div[1]/div[2]")
	WebElement toCity;
	
	public void clickOnToCity() {
		toCity.click();
	}
	
	@FindBy(how=How.XPATH, using="//p[contains(@class,'calc60')]")
	List<WebElement> toCityInDropdown;
	public void clickToCityInDropdown() {
		for(WebElement dest: toCityInDropdown) {
			if(dest.getText().equals("Chennai, India")) {
				System.out.println("clicking "+dest.getText()+ toCityInDropdown.size());
				dest.click();
			}
			else {
				System.out.println("removing "+dest.getText()+ toCityInDropdown.size());
				toCityInDropdown.remove(dest);
				System.out.println("removed");
			}
		}
	}
	
	@FindBy(how= How.XPATH, using="//span[@class='lbl_input latoBold appendBottom10']")
	WebElement departure;
	
	public void clickOnDeparture() {
		System.out.println("clicking on departure");
		departure.click();
	}
	
	
	@FindBy(how=How.XPATH, using="//div[@class='DayPicker']/div/div/child::span[2]")
	WebElement next;
	List<WebElement> listofcaptions;
	public void findMonth() {
		flag=false;
		listofcaptions = repoDriver.findElements(By.xpath("//div[@class='DayPicker-Caption']"));
		
		for (WebElement ele: listofcaptions) {
			if(ele.getText().contains("August 2022") && ele.isDisplayed() && ele.isEnabled()) {
				flag=true;
			}
		}
		if (flag==false) {
			wait.until(ExpectedConditions.elementToBeClickable(next));
			next.click();
			findMonth();
		}
	}
	
	@FindBy(how=How.XPATH, using="//div[@class='DayPicker-Day']")
	List<WebElement> days;
	
	public void findNClickDay() {
		flag=false;
		for(WebElement day:days) {
			if(day.getAttribute("aria-label").contentEquals("Fri Aug 19 2022")) {
				day.click();
				flag=true;
				break;
			}
		}
		if(flag==false)
			System.out.println("-----------days not clicked due to some issue-----------");
	}
	
	@FindBy(how=How.XPATH, using="//div[@data-cy='flightTraveller']//child::span")
	WebElement tvlrNClass;
	
	public void clickOnTvlrNClass() {
		System.out.println("clicking on tvl and class");
		tvlrNClass.click();
	}
	
	@FindBy(how=How.XPATH, using="//li[@data-cy='adults-2']")
	WebElement adultCount;
	public void clickOnAdultCount() {
		System.out.println("clicking on adult count");
		adultCount.click();
	}
	
	@FindBy(how=How.XPATH, using="//li[@data-cy='children-3']")
	WebElement childrenCount;
	public void clickOnChildrenCount() {
		System.out.println("clicking on children count");
		childrenCount.click();
	}
	
	@FindBy(how=How.XPATH, using="//li[@data-cy='infants-3']")
	WebElement InfantCount;
	public void clickOnInfantCount() {
		System.out.println("clicking on infant count");
		InfantCount.click();
	}
	
	@FindBy(how=How.XPATH, using="//li[text()='Premium Economy']")
	WebElement tvrlClass;
	public void clickOntvrlClass(String className) throws InterruptedException {
		String path = String.format("//li[text()='"+className+"']");
//		wait.until(ExpectedConditions);
		WebElement ele = repoDriver.findElement(By.xpath(path));
		action.moveToElement(ele).build();
		action.perform();
		Thread.sleep(2000);
		
		repoDriver.findElement(By.xpath(String.format(path+"//child::p"))).click();
//		tvrlClass.click();
		System.out.println("clicked on tvlr class");
	}
	
//	@FindBy(how=How.XPATH, using="//p[@data-cy='infantWarning']")
	@FindBy(how=How.XPATH, using="//p[@id='smallErrorMessage']")
	WebElement warning;
	public String getwarning() {
		return warning.getText();
	}
	public boolean isWarningDisplayed() {
		return warning.isDisplayed();
	}
	
	@FindBy(how=How.XPATH, using="//button[text()='APPLY']")
	WebElement applybtn;
	
	public void clickOnApply() {
		System.out.println("clicking on apply btn");
		applybtn.click();
	}
}
