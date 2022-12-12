
import org.openqa.selenium.By;
import java.text.SimpleDateFormat
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
//import org.openqa.selenium.support.ui;
import org.openqa.selenium.support.ui.Select;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import java.text.SimpleDateFormat;

def driverpath="/usr/bin/chromedriver";
System.setProperty("webdriver.chrome.driver", driverpath);
WebDriver driver = new ChromeDriver(); 
WebDriverWait wait = new WebDriverWait(driver, 10);
def link = 'https://212.11.152.7/index.do';

driver.get(link);
