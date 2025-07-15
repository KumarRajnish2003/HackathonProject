package pageObjects;
 
import java.util.ArrayList;
import java.util.List;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
 
public class HondaUpcomingBikePage extends BasePage{
	private List<String> titles;
	private List<String> prices;
	private List<String> Exp;
	
	public HondaUpcomingBikePage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//li[@class='col-lg-4 txt-c rel modelItem ' and @data-price<'400000']//a")
	List <WebElement> title;
	@FindBy(xpath="//li[@class='col-lg-4 txt-c rel modelItem ' and @data-price<'400000']//div/div/div[1]")
	List <WebElement> price;
	@FindBy(xpath="//li[@class='col-lg-4 txt-c rel modelItem ' and @data-price<'400000']//div/div/div[2]")
	List <WebElement> expLaunch;
	
	public List<String> getTitle(){
		titles=new ArrayList<>();
		for(WebElement s:title) {
			titles.add(s.getAttribute("title"));
		}
		return titles;
	}
	public List<String> getPrice(){
		prices=new ArrayList<>();
		for(WebElement s:price) {
			prices.add(s.getText());
		}
		return prices;
	}
	public List<String> getExpLaunch(){
		Exp=new ArrayList<>();
		for(WebElement s:expLaunch) {
			Exp.add(s.getText());
		}
		return Exp;
	}

 
}