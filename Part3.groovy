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
//OpenQA.Selenium.Support.UI.DefaultWait<IWebDriver>

/*
 *
 * Создание ВЗ (с назначением) Требуется проверка перед закрытием ВЗ
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
String KodNum;
System.setProperty("webdriver.chrome.driver", driverpath); //Указываем где у нас дравер для управления браузером
WebDriver driver = new ChromeDriver(); //Запуск хрома
try
{
	
	cur_check_pos = "Вход в систему";
	driver.manage().window().maximize(); //Во весь экран
	en = "English";
	ru = "Russian";
	def link =  'https://212.11.152.7/index.do'; // Переходим на сайт
	driver.get(link);
	if (driver.findElement(By.id("details-button"))){
		driver.findElement(By.id("details-button")).click();
		sleep(1000);
		driver.findElement(By.id("proceed-link")).click();
	}
        def username  = "regrcoordOM" ; // Логин
	def pass ='Qwerty1234'; // Пароль
	driver.findElement(By.id("LoginUsername")).sendKeys(username); //Вводим логи
	driver.findElement(By.id("LoginPassword")).sendKeys(pass); //Вводим пароль
	element = driver.findElementById("selectedLanguage") //Ищем элемент переключения языка
	element.click(); // Активируем выбор
	element.sendKeys(ru); // Ввводим Русский язык
	element.click(); // Выбираем его
	driver.findElement(By.id("loginBtn")).click() // Жмём войти
	WebDriverWait wait = new WebDriverWait(driver, 10); // Драйвер ожидания элемента
	obr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ROOT/Cлужба поддержки пользователей']")));
	sleep(1000);
	obr.click(); //раскрываем его подпункты
	
	cur_check_pos="Выбор Поиск обращений";
	sleep(1000);
	def gt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.//span[text()='Поиск обращения']]")));  // Ждём пока раскроется список и станет видимым элемент меню
	gt.click();// Жмём на поиск обращений
	def i=0;

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@title,'Какие обращения в службу поддержки пользователей')]")));
	driver.switchTo().frame(driver.findElement(By.xpath("//*[contains(@title,'Какие обращения в службу поддержки пользователей')]"))); //Переключаемся на фрэйм вкладки с поиском обращений

	cur_check_pos="Поиск обращения";
	fzid = driver.findElement(By.xpath("//label[contains(text(),'Фаза')]")).getAttribute("for");
	fz = driver.findElement(By.xpath("//*[@id='$fzid']"));
	fz.sendKeys("Классификация");
	fz.click();
	
	omid = driver.findElement(By.xpath("//label[contains(text(),'Группа ОМ')]")).getAttribute("for");
	om = driver.findElement(By.xpath("//*[@id='$omid']"));
	om.sendKeys("ОМ ЕАИСТ");
	
	//def ko = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='instance/incident.id']"))); // Ожидаем появление поля ввода номера инцидента
	//ko.sendKeys("SD689633");// Вписываем туда номер инцидента
	driver.switchTo().defaultContent(); //Возвращаемся в главный фрейм
	driver.switchTo().defaultContent();//Возвращаемся в главный фрейм
	def  w= driver.findElement(By.xpath("//button[@aria-label='Запустить этот поиск (Ctrl+Shift+F6)']"));// Ищем кнопку поиск
	w.click(); // Жмём кнопку
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Обращение:')]"))); //Ожидаем появления вкладки с нашим инцидентом
	
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Service Manager')]"))); //Смена фрэйма
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Обращение')]"))); //Смена фрэйма

	omsid = driver.findElement(By.xpath("//*[contains(text(),'Ответственный ОМ')]")).getAttribute("for");
	oms = driver.findElementById(omsid);
	oms.clear();
	driver.findElementById("${omsid}FillButton").click();
	cur_check_pos="Назначение на KarmanovaAV";

	driver.switchTo().defaultContent();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Выбрать Оператор')]")))
	frm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'popupFrame')]")));// Ожидаем появления окна с выбором
	driver.switchTo().frame(frm);

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'KarmanovaAV')]"))).click();
	try{
	sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'KarmanovaAV')]"))).click();
	} catch (Exception e)
	{}
	
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@id,'popupFrame')]")));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='hpsm_render_container']")));
/*	
driver.switchTo().defaultContent(); //Возвращаемся в главный фрейм
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Service Manager')]"))); //Смена фрэйма
	sleep(1000);
	//driver.switchTo().defaultContent(); //Возвращаемся в главный фрейм
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Сохранить')]"))).click();
	//	driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]")).click();
	sleep(1000);
	driver.switchTo().defaultContent(); 
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'ext-el-mask-msg x-mask-loading')]")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'ext-el-mask-msg x-mask-loading')]")));

	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Service Manager')]"))); //Смена фрэйма
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Обращение')]"))); //Смена фрэйма
	omsid = driver.findElement(By.xpath("//*[contains(text(),'Ответственный ОМ')]")).getAttribute("for");
	oms = driver.findElementById(omsid);
	if(oms.getAttribute("value")!="KarmanovaAV") {
		throw new Exception("Не удалось переназначит ОМ на KarmanovaAV");
	}
	oms.clear();
	driver.findElementById("${omsid}FillButton").click();
	cur_check_pos="Назначение на PanfilovaAI";
	driver.switchTo().defaultContent();
	//driver.switchTo().defaultContent();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Выбрать Оператор')]")))
	frm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'popupFrame')]")));// Ожидаем появления окна с выбором ФИО
	driver.switchTo().frame(frm);

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'PanfilovaAI')]"))).click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@id,'popupFrame')]")));
	// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='hpsm_render_container']")));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='hpsm_render_container']")));
	driver.switchTo().defaultContent(); //Возвращаемся в главный фрейм
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Service Manager')]"))); //Смена фрэйма
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Обращение')]"))); //Смена фрэйма

	sleep(1000);
	driver.switchTo().defaultContent(); //Возвращаемся в главный фрейм
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Service Manager')]"))); //Смена фрэйма
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Сохранить')]"))).click();
	//	driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]")).click();
	sleep(1000);
	driver.switchTo().defaultContent();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'ext-el-mask-msg x-mask-loading')]")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'ext-el-mask-msg x-mask-loading')]")));

	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Service Manager')]"))); //Смена фрэйма
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'Обращение')]"))); //Смена фрэйма
	omsid = driver.findElement(By.xpath("//*[contains(text(),'Ответственный ОМ')]")).getAttribute("for");
	oms = driver.findElementById(omsid);
	if(oms.getAttribute("value")!="PanfilovaAI") {
		throw new Exception("Не удалось переназначит ОМ на PanfilovaAI");
	}


	PrevStepSucces=true;
*/
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
	new File("$logdir\\$logname").append(ln+errprnt);



}
if (JOptionPane.showConfirmDialog(null, "Закрыть?", PrevStepSucces==true ? "Проверка прошла успешно" : "Возникли ошибки", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	driver.quit();
}

