
import org.openqa.selenium.By;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedConditions; 
import org.openqa.selenium.support.ui.Select;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
def driverpath="/usr/bin/chromedriver";
System.setProperty("webdriver.chrome.driver", driverpath);
WebDriver driver = new ChromeDriver(); 
WebDriverWait wait = new WebDriverWait(driver, 10);


driver.get("https://www.seleniumeasy.com/selenium-tutorials/css-selectors-tutorial-for-selenium-with-examples");
sleep(1000);
//driver.findElement(By.className("fancybox-close-small")).click();
def element = driver.findElement(By.cssSelector(".easy-breadcrumb_segment-1"));
println("-----------")
println(element);
element.click();
sleep(1000);
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("node-266")));
def a=driver.findElement(By.id("node-266")).findElement(By.tagName("h2"));
println(a)
a.click();
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-search-block-form--2")));	
def c = driver.findElement(By.id("edit-search-block-form--2"));
c.sendKeys("XPATH");
sleep(1000);
def b = driver.findElement(By.cssSelector(".btn-danger")); 
b.click();
