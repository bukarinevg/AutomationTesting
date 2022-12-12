
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
if (driver.findElement(By.id("details-button"))){
driver.findElement(By.id("details-button")).click();
sleep(1000);
driver.findElement(By.id("proceed-link")).click();
}
def username = "regrcoordOM" ;
def pass ='Qwerty1234';

driver.findElement(By.id("LoginUsername")).sendKeys(username);
driver.findElement(By.id("LoginPassword")).sendKeys(pass);
element = driver.findElementById("selectedLanguage")

driver.findElement(By.id("loginBtn")).click()

obr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ROOT/Cлужба поддержки пользователей']")));
sleep(1000);
obr.click();
sleep(1000);
def gt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.//span[text()='Поиск обращения']]")));
gt.click();
def i=0;
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@title,'Какие обращения в службу поддержки пользователей')]")));
//Переключаемся на фрэйм вкладки с поиском обращений
driver.switchTo().frame(driver.findElement(By.xpath("//*[contains(@title,'Какие обращения в службу поддержки пользователей')]"))); 
fzid = driver.findElement(By.xpath("//label[contains(text(),'Фаза')]")).getAttribute("for");
fz = driver.findElement(By.xpath("//*[@id='$fzid']"));
fz.sendKeys("Классификация");
fz.click();


def direction= driver.findElement(By.id("X39FillButton"));
direction.click();

driver.switchTo().defaultContent();
driver.switchTo().defaultContent();

wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("popupFrame")));
def gto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen-list-1-56")));
def eaist = driver.findElement(By.id("ext-gen-list-1-56"));
eaist.click();
def gti = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Поддержка пользователей ЕАИСТ")));
def helpusers = driver.findElement(By.linkText("Поддержка пользователей ЕАИСТ"));
helpusers.click();
driver.switchTo().defaultContent();
sleep(3000);


def secomd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Отмена (Alt+F3)']")));

//def gtir = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Отмена (Alt+F3)']")));
def exit = driver.findElement(By.xpath("//button[@aria-label='Отмена (Alt+F3)']"));
webElement = wait.until(ExpectedConditions.visibilityOf(exit));
		
/*exit.click();
find= driver.findElement(By.xpath("//button[@aria-label='Запустить этот поиск (Ctrl+Shift+F6)']")); //Ищем кнопку поиска
find.click(); // Жмём её
*/
/*
def gtp = wait.until(ExpectedConditions.visibilityOfElementLocated(driver.findElement(By.xpath("//span[@id='ext-gen-top352']")).findElement(By.tagName("h2")))) ;
driver.switchTo().frame(driver.findElement(By.id("popupFrame"))); 
def gto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen-list-1-56")));
def eaist = driver.findElement(By.id("ext-gen-list-1-56"));
eaist.click();
def gti = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen-list-1-105")));
def helpusers = driver.findElement(By.id("ext-gen-list-1-107"));
helpusers.click();
*/

/*

fzid = driver.findElement(By.xpath("//label[contains(text(),'Направление')]")).getAttribute("for");
fzid.sendKeys("ЕАИСТ");
fz.click();
driver.switchTo().defaultContent();
def w= driver.findElement(By.xpath("//button[@aria-label='Запустить этот поиск (Ctrl+Shift+F6)']"));
w.click();
driver.switchTo().defaultContent();	
driver.switchTo().defaultContent();
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Информация о пользователе')]")));
sleep(1000);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Обращение:')]")))
wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Service Manager')]")));
wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Обращение')]")));;
fzid = driver.findElement(By.xpath("//label[contains(text(),'Описание')]")).getAttribute("for");
fz = driver.findElement(By.xpath("//*[@id='$fzid']"));
fz.sendKeys("TEST");
*/
