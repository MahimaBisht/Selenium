import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class objectRepo {
	
	WebDriver repoDriver;
	Actions act;
	int position;
	String xpathWeeks;
	
	objectRepo(WebDriver drive){
		PageFactory.initElements(drive, this);
		repoDriver = drive;
	}
	
// types of relative xpaths
// 
//	

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
	
	@FindBy(how= How.XPATH, using="//*[@id=\"root\"]/div/div[2]/div/div[1]/div[2]/div[1]/div[3]")
	WebElement departure;
	
	public void clickOnDeparture() {
		System.out.println("clicking on departure");
		departure.click();
	}
	
	@FindBy(how=How.XPATH, using="//div[contains(@aria-label,'Thu Aug 12 2021')]")
	WebElement day;
	public void clickOnDay() {
		System.out.println("clicking on day");
		day.click();
	}
	
	@FindBy(how=How.XPATH, using="/html/body/div[1]/div/div[3]/div/div/div[2]/div[1]/div[5]/label/span")
	WebElement tvlrNClass;
	
	public void clickOnTvlrNClass() {
		System.out.println("clicking on tvl and class");
		tvlrNClass.click();
	}
	
	@FindBy(how=How.XPATH, using="//li[contains(@data-cy,'adults-2')]")
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
	public void clickOntvrlClass() {
		System.out.println("clicking on tvlr class");
		tvrlClass.click();
	}
	
	@FindBy(how=How.XPATH, using="//p[@data-cy='infantWarning']")
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
