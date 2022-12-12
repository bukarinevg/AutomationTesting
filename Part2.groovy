import org.openqa.selenium.By;
import java.text.SimpleDateFormat
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import javax.swing.JTextField
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
//OpenQA.Selenium.Support.UI.DefaultWait<IWebDriver>
import javax.swing.JOptionPane;
/*
 * 
 * Проверка отображения представлений для пользователя. 
 * 
 */
def cur_check_pos="";
def ln = System.getProperty('line.separator');
df = new SimpleDateFormat('yyyyMMddHHmmss')
def logname = this.getClass().getName()+df.format(new Date())+".txt";
def driverpath="C:\\chromedriver.exe";
def logdir ="E:\\logs";
if (args.length==3) {
	driverpath = args[0];
	logdir = args[1];
	logname = args[2];
}

System.setProperty("webdriver.chrome.driver", driverpath); //Указываем где у нас дравер для управления браузером
WebDriver driver = new ChromeDriver(); //Запуск хрома
boolean PrevStepSucces=false;
try
{

	cur_check_pos="Проверка отображения представлений для пользователя.";
	driver.manage().window().maximize(); //Во весь экран
	en = "English";
	ru = "Russian";
	def link =  'https://212.11.152.7/index.do'; // Переходим на сайт
	driver.get(link);
	cur_check_pos="Вход в систему";
	def username  = "regrVZ1" ; // Логин
	def pass ='Qwerty1234'; // Пароль
	driver.findElement(By.id("LoginUsername")).sendKeys(username); //Вводим логи
	driver.findElement(By.id("LoginPassword")).sendKeys(pass); //Вводим пароль
	element = driver.findElementById("selectedLanguage") //Ищем элемент переключения языка
	element.click(); // Активируем выбор
	element.sendKeys(ru); // Ввводим Русский язык
	element.click(); // Выбираем его
	driver.findElement(By.id("loginBtn")).click() // Жмём войти
	WebDriverWait wait = new WebDriverWait(driver, 20); // Драйвер ожидания элемента
	obr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ROOT/Cлужба поддержки пользователей']")));
	sleep(1000);
	obr.click(); //раскрываем его подпункты



	
	//gt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.//span[text()='Поиск обращения']]")));
	gt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.//span[text()='Очередь обращений']]")));  // Ждём пока раскроется список и станет видимым элемент меню
	gt.click();// Жмём на поиск обращений

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@title,'Очередь Обра')]"))); //Ожидаем появления вкладки с нашим инцидентом


	driver.switchTo().frame(driver.findElement(By.xpath("//*[contains(@title,'Очередь Обра')]"))); //Переключаемся на фрэйм вкладки с поиском обращений

		cur_check_pos="Очередь обращений. Задания на Меня";
		listboxid = driver.findElement(By.xpath("//*[contains(text(),'Представление:')]")).getAttribute("for");
		elistbox = driver.findElementById(listboxid);
		elistbox.click();
		sleep(1000);
		elistbox.sendKeys("Задания на Меня");
		sleep(1000);
		elistbox.click();
		sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElementById("recordListGrid"))); //Ожидаем появления вкладки с нашим инцидентом

		wait.until(ExpectedConditions.invisibilityOf(driver.findElementById("recordListGrid"))); //Ожидаем появления вкладки с нашим инцидентом
		wait.until(ExpectedConditions.visibilityOf(driver.findElementById("recordListGrid"))); //Ожидаем появления вкладки с нашим инцидентом
	

		listboxid2 = driver.findElement(By.xpath("//*[contains(text(),'Представление:')]")).getAttribute("for");
		elistbox2 = driver.findElementById(listboxid2);
		
		sleep(2000);
		elistbox2.click();
		//elistbox2.clear();
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'${listboxid2}Popup_') and contains(@value,'Отчет по ЕАИСТ для выгрузки')]"))).click();
		//elistbox2.sendKeys("На контроле");
		//elistbox2.click();
		
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElementById("recordListGrid"))); //Ожидаем появления вкладки с нашим инцидентом

		wait.until(ExpectedConditions.invisibilityOf(driver.findElementById("recordListGrid"))); //Ожидаем появления вкладки с нашим инцидентом
		wait.until(ExpectedConditions.visibilityOf(driver.findElementById("recordListGrid"))); //Ожидаем появления вкладки с нашим инцидентом
	
	PrevStepSucces=true;
} catch (Exception e)
{

	errline=";"
	srs=this.getClass().getName()+".run("+this.getClass().getName()+".groovy:";
	e.stackTrace.each {
		if (it.toString().contains(srs)) {
			errline = it.toString().substring(it.toString().indexOf(srs));
			errline = errline.substring(0, errline.indexOf(")"));
			println(errline);
		}
	}
	errprnt = "Не удалось выполнить $cur_check_pos $ln Строка с ошибкой в скрипте: $errline $ln $e.message $ln $e.stackTrace";
	println(errprnt);

	//	e.stackTrace.substring this.getClass().getName()+".run("+this.getClass().getName()+".groovy:"
new File("$logdir\\$logname").append(ln+errprnt);

}
if (JOptionPane.showConfirmDialog(null, "Закрыть?", PrevStepSucces==true ? "Проверка прошла успешно" : "Возникли ошибки", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		driver.quit();
	}







