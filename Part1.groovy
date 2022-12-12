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
//import org.openqa.selenium.support.ui;
import org.openqa.selenium.support.ui.Select;
//OpenQA.Selenium.Support.UI.DefaultWait<IWebDriver>
import javax.swing.JOptionPane;
//import java.text.DateFormat;
import java.text.SimpleDateFormat
/*
 * 
 * Проверка возможности авторизации пользователя в системе. 
 * 
 */

def cur_check_pos="";
def ln = System.getProperty('line.separator');
df = new SimpleDateFormat('yyyyMMddHHmmss')
def logname = this.getClass().getName()+df.format(new Date())+".txt";
def driverpath="/usr/bin/chromedriver";
def logdir ="E:\\logs";
if (args.length==3) {
	driverpath = args[0];
	logdir = args[1];
	logname = args[2];
}
boolean PrevStepSucces=false;
System.setProperty("webdriver.chrome.driver", driverpath); //Указываем где у нас дравер для управления браузером
WebDriver driver = new ChromeDriver(); //Запуск хрома


try
{

	//println(filename);



	driver.manage().window().maximize(); //Во весь экран
	def en = "English";
	def ru = "Russian";
	def link =  'https://212.11.152.7/index.do'; // Переходим на сайт
	driver.get(link);
if (driver.findElement(By.id("details-button"))){
driver.findElement(By.id("details-button")).click();
sleep(1000);
driver.findElement(By.id("proceed-link")).click();
}
	def username  = "regrVZ1" ; // Логин
	def pass ='Qwerty1234'; // Пароль
	cur_check_pos="Проверка возможности авторизации пользователя в системе";
	driver.findElement(By.id("LoginUsername")).sendKeys(username); //Вводим логи
	driver.findElement(By.id("LoginPassword")).sendKeys(pass); //Вводим пароль
	def element = driver.findElementById("selectedLanguage"); //Ищем элемент переключения языка
	element.click(); // Активируем выбор
	element.sendKeys(ru); // Ввводим Русский язык
	element.click(); // Выбираем его
	driver.findElement(By.id("loginBtn")).click(); // Жмём войти
	WebDriverWait wait = new WebDriverWait(driver, 20); // Драйвер ожидания элемента
	obr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ROOT/Cлужба поддержки пользователей']")));
	//def obr = driver.findElement(By.id("ROOT/Cлужба поддержки пользователей")); // Ишем элемент в меню
	sleep(1000);

	/*
	 * 
	 * Проверка работоспособности инструмента поиска 
	 * 
	 */

		obr.click(); //раскрываем его подпункты
	
		//gt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.//span[text()='Поиск обращения']]")));

		cur_check_pos="Выбор Поиск обращений";

		def gt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.//span[text()='Поиск обращения']]")));  // Ждём пока раскроется список и станет видимым элемент меню
		gt.click();// Жмём на поиск обращений
		def i=0;
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@title,'Какие обращения в службу поддержки пользователей')]")));
		driver.switchTo().frame(driver.findElement(By.xpath("//*[contains(@title,'Какие обращения в службу поддержки пользователей')]"))); //Переключаемся на фрэйм вкладки с поиском обращений

		cur_check_pos="Поиск по коду обращения";

		def ko = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='instance/incident.id']"))); // Ожидаем появление поля ввода номера инцидента
		ko.sendKeys("SD331970");// Вписываем туда номер инцидента
		driver.switchTo().defaultContent(); //Возвращаемся в главный фрейм
		driver.switchTo().defaultContent();//Возвращаемся в главный фрейм
		def  w= driver.findElement(By.xpath("//button[@aria-label='Запустить этот поиск (Ctrl+Shift+F6)']"));// Ищем кнопку поиск
		w.click(); // Жмём кнопку
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'SD331970')]"))); //Ожидаем появления вкладки с нашим инцидентом



		driver.findElement(By.xpath("//li[.//span[contains(text(),'SD331970')]]/a[@class='x-tab-strip-close']")).click(); // Ищем и нажимаем на закрытие вкладки с инцидентом

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Какие обращения в службу поддержки пользователей необходимо отображать?']"))); // Ждём возврата к вкладке с поиском
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@title='Какие обращения в службу поддержки пользователей необходимо отображать?']"))); // Переходим на фрэйм этой вкладки
		ko = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='instance/incident.id']"))); // Ищем поле с инцидентом куда мы ввели номер
		ko.clear(); // Очищаем его

		cur_check_pos="Поиск по контакту";

		ko = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='instance/callback.contact']")));  //Ищем поле для вода контакта
		def contact="Еремина Марина Константиновна";
		ko.sendKeys(contact); // Ввводим туда нужную нам персону

		driver.switchTo().defaultContent(); // Возвращаемся в главный фрэйм
		driver.switchTo().defaultContent(); // Возвращаемся в главный фрэйм
		w= driver.findElement(By.xpath("//button[@aria-label='Запустить этот поиск (Ctrl+Shift+F6)']")); //Ищем кнопку поиска
		w.click(); // Жмём её

		driver.switchTo().defaultContent(); // Возвращаемся в главный фрэйм
		driver.switchTo().defaultContent(); // Возвращаемся в главный фрэйм

		//wait.until(ExpectedConditions.invisibilityOf(w));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Обращение')]")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@title='Service Manager']")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@title='Список записей']")));//ищем фрейм с таблицей результатов поиска

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+ contact +"')]"))); //Проверяем что появилась запись с контактом

	/*driver.switchTo().frame(driver.findElement(By.xpath("//*[@title='Какие обращения в службу поддержки пользователей необходимо отображать?']"))); //Переключаемся на фрэйм вкладки с поиском обращений
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Еремина Марина Константиновна']")));*/
	/*
	 * 
	 * Выход из системы
	 * 
	 */

		driver.switchTo().defaultContent(); // Возвращаемся в главный фрэйм
		driver.switchTo().defaultContent(); // Возвращаемся в главный фрэйм

		cur_check_pos="Информация о пользователе";

sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Информация о пользователе')]")).click(); // Жмём иконку пользователя

		cur_check_pos="Выход";

		driver.findElement(By.xpath("//button[contains(text(),'Выход')]")).click(); // Жмём выход
		Alert alert_box = driver.switchTo().alert(); // Выбираем диалоговое окно
		alert_box.accept(); // Жмём ОК

		driver.switchTo().defaultContent(); // Возвращаемся в главный фрэйм
		driver.switchTo().defaultContent(); // Возвращаемся в главный фрэйм
		driver.findElement(By.xpath("//*[contains(text(),'Выход из системы успешно выполнен')]"));

	
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
