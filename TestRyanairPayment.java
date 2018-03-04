import java.io.IOException;
import org.openqa.selenium.By;
import java.text.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;


public class BookRyanairFlight {

	public WebDriver driver;
	
	public static void main(String[] args) throws ParseException {
		public void openBrowser(String url){
			System.setProperty("webdriver.chrome.driver", "C://Users//tkhan//workspace//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(url);
			
		}
		
		public void SelectBookingDetails(){
			//Select From Location
			driver.findElement(By.xpath("//*[@placeholder='Departure airport']")).sendKeys("Dublin");
			//Select To Location
			driver.findElement(By.xpath("//*[@placeholder='Destination airport']")).sendKeys("SXF");
			driver.findElement(By.xpath("//*[@id='search-container']/div[1]/div/form/div[2]/div/div/div[2]/div[3]/div/div/div[2]/popup-content/core-linked-list/div[2]/div[1]/div[3]/span")).click();
			
			//Select Dates (From '18-04-2018' to '22-04-2018')
			driver.findElement(By.xpath("//*[contains(@data-id,'18-04-2018')]/span")).click();
			driver.findElement(By.xpath("//*[contains(@data-id,'22-04-2018')]/span")).click();
			
			//Select Passengers
			//Add an Adult
			driver.findElement(By.xpath("//*[@label='Adults']/div/div[3]/core-inc-dec/button[2]")).click();
			//Add a Child
			driver.findElement(By.xpath("//*[@label='Children']/div/div[3]/core-inc-dec/button[2]")).click();
			
			
			//Click Let's Go button
			driver.findElement(By.xpath("//button[contains( @ng-click,'searchFlights')]")).click();
			
			
			//Select Flight Times and fare types
			driver.findElement(By.xpath("//*[@id='flight-FR~8558~ ~~DUB~04/07/2018 15:10~SXF~04/07/2018 18:30~']")).click();
			driver.findElement(By.xpath("//*[@class='flights-table-fares__fare-radio']")).click();
			
			driver.findElement(By.xpath("//*[@id='flight-FR~8559~ ~~SXF~04/14/2018 18:55~DUB~04/14/2018 20:25~']")).click();
			driver.findElement(By.xpath("//*[@class='flights-table-fares__fare-radio']")).click();
			
			driver.findElement(By.xpath("//*[@id='booking-selection']")).click();
			
			//Enter Credit Card Details
			driver.findElement(By.xpath("//*[@id='cardNumber9403']")).sendKeys("5555 5555 5555 5557");
			
			Select cc_month = new Select(driver.findElement(By.xpath("//*[@id='expiryMonth9403']")));
			cc_month.selectByVisibleText("10");
			Select cc_year = new Select(driver.findElement(By.xpath("//*[@//*[@id='checkout']")));
			cc_year.selectByVisibleText("2018");
		}
		
		public void Submit(){
			driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
			
		}
		
		public void verifyTextPresent(String value)
		{
		  driver.PageSource.Contains(value); ;
		}
		
		public void Close(){
			driver.quit();
		}
	}

}

public class Test_RyanairBooking_Payment {
	
	private BookRyanairFlight Booking;
	
	@Before
	public void OpenBrowser(){
		Booking.openBrowser("http://ryanair.com/ie/en");
	}
	
	
	@After
	public void closeBrowser(){
		Booking.Close();
	}
	
	@Test
	public void CheckCreditCardDetails{
		try {
			Booking.Submit();
			Assert.IsTrue(verifyTextPresent("Card number is invalid"));
			Console.WriteLine("Card number is invalid");
		}
		catch (Exception) {
			Console.WriteLine("Card number is invalid");
		}		
	}
}