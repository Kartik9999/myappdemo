package Maps;

import org.testng.annotations.Test;

import com.google.common.io.Files;

import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleMapsTest {

	WebDriver driver;
	@BeforeTest
	void beforetest()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Software\\Selenium\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test(priority=2)
	void test() throws InterruptedException, IOException 
	{
		driver.manage().window().maximize();
		driver.get("https://www.google.com/maps/");
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement search = driver.findElement(By.xpath("//*[@id=\'searchboxinput\']"));
		wait.until(ExpectedConditions.visibilityOf(search));
		search.sendKeys("Wankhede Stadium");
		//driver.findElement(By.xpath("//img[@src='https://fonts.gstatic.com/s/i/googlematerialicons/menu/v6/black-24dp/1x/gm_menu_black_24dp.png']")).click();
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\'passive-assist\']/div/div[2]/div/div/div[2]/div[1]/button[1]/div[1]/img"))));
		//WebElement map = driver.findElement(By.xpath("class=\"widget-settings-button-label\""));
		//wait.until(ExpectedConditions.visibilityOf(map));
		//map.click();
		//driver.findElement(By.xpath("//*[@id=\'settings\']/div/div[2]/div/div[2]/button")).click();
		Thread.sleep(5000);
		//Screenshot functionality
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\Home\\eclipse-workspace\\Demo\\src\\GoogleMpas\\test.png");
		Files.copy(file, dest);
		driver.findElement(By.xpath("//*[@id=\'searchbox-searchbutton\']")).click();
		//Thread.sleep(10000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'pane\']/div/div[1]/div/div/div[5]/div[1]/div/button/img")));
		WebElement text = driver.findElement(By.xpath("//*[@id=\'pane\']/div/div[1]/div/div/div[2]/div[1]/div[1]/div[1]/h1/span[1]"));
		wait.until(ExpectedConditions.visibilityOf(text));
		driver.findElement(By.xpath("//div[@class='searchbox-searchbutton-container']")).click();
		
		WebElement rate_point = driver.findElement(By.xpath("//*[@id=\'pane\']/div/div[1]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[1]/span[1]/span/span"));
		WebElement review = driver.findElement(By.xpath("//*[@id=\"pane\"]//button[@vet='3648']")); //due to less time using relative xpath only here
		
		String text1 = text.getText();
		System.out.println("Text present in left side is:"+text1);
		Assert.assertTrue(text1.contains("Stadium"));
		
		
		String text2 = rate_point.getText();
		String text3 = review.getText();
		System.out.println("Rating points are:"+text2);
		System.out.println("Number of reviews are:"+text3);
		
		/*File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\Home\\eclipse-workspace\\Demo\\src\\GoogleMpas\\test.png");
		Files.copy(file, dest);*/
		System.out.println("Verifying text contents:");
		Assert.assertEquals(text1, "Wankhede Stadium");
		
		driver.close();
	}
	
	@Test(priority=1)
	void QAAgile() throws InterruptedException
	{
		driver.manage().window().maximize();
		driver.get("https://www.qaagility.com");
		String title = driver.getTitle();
		AssertJUnit.assertTrue(title.contains("QAAgility"));
		WebElement size = driver.findElement(By.xpath("//*[@id=\'site-navigation\']/div/div[1]/div[1]/a/img"));
		WebElement footer = driver.findElement(By.xpath("//div[@class='copyright-bar']"));
		String footer_text = footer.getText();
		
		int sizeWidth = size.getSize().getWidth();
		int sizeHeight = size.getSize().getHeight();
		System.out.println("Size of the logo-height & Width are:"+sizeWidth+" "+sizeHeight);
		
		WebElement twitter = driver.findElement(By.xpath("//*[@id=\'custom_html-4\']/div/div[1]/a[2]/i"));
		Actions action = new Actions(driver);
		action.moveToElement(twitter).build().perform();
		Thread.sleep(2000);
		boolean visibility = twitter.isDisplayed();
		System.out.println("Visibility of twitter button:");
		if(visibility)
		{
			System.out.println("Twitter button is displayed");
		}
		AssertJUnit.assertTrue(visibility);
		
		System.out.println("Footer text:"+footer_text);
		System.out.println("Verifying of footer contains text: 'QAAgility Technologies Pvt. Ltd. � 2020. All Rights Reserved.�'");
		Assert.assertTrue(footer_text.contains("QAAgility Technologies Pvt. Ltd. � 2020. All Rights Reserved"));
		driver.close();
	}
	
}
