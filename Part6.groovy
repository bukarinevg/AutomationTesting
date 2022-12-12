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
def driverpath="C:\\chromedriver.exe";
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
//SD396226
try
{
	cur_check_pos = "Вход в систему";
	driver.manage().window().maximize(); //Во весь экран
	en = "English";
	ru = "Russian";
	def link =  'https://212.11.152.7/index.do'; // Переходим на сайт
	driver.get(link);
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
	obr.click(); //раскрываем его подпункты
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.//span[contains(text(),'Создание внутреннего обращения')]]"))).click();
	driver.switchTo().defaultContent();
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[contains(@title,'Создание нового обращения')]"))); //Смена фрэйма

	KodNumID = driver.findElement(By.xpath("//*[contains(text(),'Код обращения')]")).getAttribute("for");
	KodNum = driver.findElementById(KodNumID).getAttribute("value");

	cur_check_pos = "Ввод значений";
	zagid = driver.findElement(By.xpath("//*[contains(text(),'Заголовок')]")).getAttribute("for");
	zag = driver.findElementById(zagid);
	zag.sendKeys("проверка ВЗ");

	dscid = driver.findElement(By.xpath("//label[contains(text(),'Описание')]")).getAttribute("for");
	dsc = driver.findElement(By.xpath("//*[@id='"+dscid+"View']"));
	dsc.click();
	dsc = driver.findElement(By.xpath("//*[@id='"+dscid+"']"));
	zapros="проверка ВЗ";
	dsc.sendKeys(zapros);


	usid = driver.findElement(By.xpath("//*[contains(text(),'Услуга')]")).getAttribute("for");
	us = driver.findElementById(usid);
	us.sendKeys("Личные поручения");

	serid = driver.findElement(By.xpath("//*[contains(text(),'Сервис')]")).getAttribute("for");
	ser = driver.findElementById(serid);
	ser.sendKeys("Прочее");

	fzid = driver.findElement(By.xpath("//*[contains(text(),'№ ФЗ')]")).getAttribute("for");
	fz = driver.findElementById(fzid);
	fz.sendKeys("44-ФЗ");




	gtp2id = driver.findElement(By.xpath("//*[contains(text(),'Группа ТП2')]")).getAttribute("for");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='${gtp2id}FillButton']"))).click();
	driver.switchTo().defaultContent(); //Возвращаемся в главный фрейм

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Выбрать Записи назначений')]")))
	driver.switchTo().defaultContent();
	frm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'popupFrame')]")));// Ожидаем появления окна с выбором ФИО
	driver.switchTo().frame(frm);

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Ответственные ВЗ')]"))).click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@id,'popupFrame')]")));
	driver.switchTo().defaultContent(); //Возвращаемся в главный фрейм
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[contains(@title,'Создание нового обращения')]"))); //Смена фрэйма
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='${gtp2id}' and @value='Ответственные ВЗ']")));


	otp2id = driver.findElement(By.xpath("//label[contains(text(),'Ответственный ТП2')]")).getAttribute("for");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='${otp2id}FillButton']")))
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@id='${otp2id}FillButton']"))).click();
	driver.switchTo().defaultContent(); //Возвращаемся в главный фрейм

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Выбрать Оператор')]")))
	frm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'popupFrame')]")));// Ожидаем появления окна с выбором ФИО
	driver.switchTo().frame(frm);

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'regrVZ2')]"))).click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@id,'popupFrame')]")));

	driver.switchTo().defaultContent(); //Возвращаемся в главный фрейм
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[contains(@title,'Создание нового обращения')]")));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='${otp2id}' and @value='regrVZ2']")));

	tzid = driver.findElement(By.xpath("//*[contains(text(),'Требуется проверка перед закрытием ВЗ')]")).getAttribute("for");

	tz = driver.findElementById(tzid+'Edit');
	tz.click();
	cur_check_pos = "Назначить в группу";
	driver.switchTo().defaultContent(); //Возвращаемся в главный фрейм
	driver.findElement(By.xpath("//button[contains(text(),'Назначить на Группу')]")).click();

	driver.switchTo().defaultContent();
	cur_check_pos = "Проверка фазы";
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[contains(@title,'Создание нового обращения')]"))); //Смена фрэйма
	fzid = driver.findElement(By.xpath("//*[contains(text(),'Фаза')]")).getAttribute("for");
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@id,'${fzid}') and @value='Регистрация']")));

	// driver.switchTo().defaultContent();

	//s  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[contains(@title,'Создание нового обращения')]"))); //Смена фрэйма
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'${fzid}') and @value='Назначено']")));



	fzid = driver.findElement(By.xpath("//*[contains(text(),'Фаза')]")).getAttribute("for");
	fz = driver.findElementById(fzid).getAttribute("value");

	if(fz !="Назначено"){
		throw new Exception("Поля не совпадают с исходными данными");
	}
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
	new File("$logdir\\$logname").append(ln+errprnt);



}
driver.quit();

//	Взятие в работу и решение ВЗ
if(PrevStepSucces) {
	driver2 = new ChromeDriver(); //Запуск хрома
	//SD396226
	try
	{
		PrevStepSucces=false;
		cur_check_pos = "Вход в систему";
		driver2.manage().window().maximize(); //Во весь экран
		en = "English";
		ru = "Russian";
		def link =  'https://212.11.152.7/index.do'; // Переходим на сайт
		driver2.get(link);
		def username  = "regrVZ2" ; // Логин
		def pass ='Qwerty1234'; // Пароль
		driver2.findElement(By.id("LoginUsername")).sendKeys(username); //Вводим логи
		driver2.findElement(By.id("LoginPassword")).sendKeys(pass); //Вводим пароль
		element = driver2.findElementById("selectedLanguage") //Ищем элемент переключения языка
		element.click(); // Активируем выбор
		element.sendKeys(ru); // Ввводим Русский язык
		element.click(); // Выбираем его
		driver2.findElement(By.id("loginBtn")).click() // Жмём войти
		WebDriverWait wait2 = new WebDriverWait(driver2, 20); // Драйвер ожидания элемента
	obr = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ROOT/Cлужба поддержки пользователей']")));
		obr.click(); //раскрываем его подпункты
		
		cur_check_pos="Выбор Поиск обращений";

		def gt = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.//span[text()='Поиск обращения']]")));  // Ждём пока раскроется список и станет видимым элемент меню
		gt.click();// Жмём на поиск обращений
		def i=0;

		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@title,'Какие обращения в службу поддержки пользователей')]")));
		driver2.switchTo().frame(driver2.findElement(By.xpath("//*[@title='Какие обращения в службу поддержки пользователей необходимо отображать?']"))); //Переключаемся на фрэйм вкладки с поиском обращений

		cur_check_pos="Поиск по коду обращения";

		def ko = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='instance/incident.id']"))); // Ожидаем появление поля ввода номера инцидента
		ko.sendKeys(KodNum);// Вписываем туда номер инцидента
		driver2.switchTo().defaultContent(); //Возвращаемся в главный фрейм
		driver2.switchTo().defaultContent();//Возвращаемся в главный фрейм
		def  w= driver2.findElement(By.xpath("//button[@aria-label='Запустить этот поиск (Ctrl+Shift+F6)']"));// Ищем кнопку поиск
		w.click(); // Жмём кнопку
		cur_check_pos="Принять в работу";
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'${KodNum}')]"))); //Ожидаем появления вкладки с нашим инцидентом
		driver2.findElement(By.xpath("//button[contains(@aria-label,'Принять в работу')]")).click();
		driver2.switchTo().defaultContent();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'ext-el-mask-msg x-mask-loading')]")));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'ext-el-mask-msg x-mask-loading')]")));

		wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[contains(@title,'${KodNum}')]"))); //Смена фрэйма
		fzid = driver2.findElement(By.xpath("//*[contains(text(),'Фаза')]")).getAttribute("for");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'${fzid}') and @value='В работе']")));
		cur_check_pos="Установка фазы решено";
		KZID = driver2.findElement(By.xpath("//*[contains(text(),'Код завершения')]")).getAttribute("for");
		KZ = driver2.findElementById(KZID);
		KZ.click();

		driver2.findElement(By.xpath("//*[contains(@id,'${KZID}Popup') and @value='Решено']")).click();

		dscid = driver2.findElement(By.xpath("//label[contains(text(),'Решение')]")).getAttribute("for");
		dsc = driver2.findElement(By.xpath("//*[@id='"+dscid+"View']"));
		dsc.click();
		dsc = driver2.findElement(By.xpath("//*[@id='"+dscid+"']"));
		dsc.sendKeys("Проверка ВЗ");

		podkid = driver2.findElement(By.xpath("//*[contains(text(),'Подкатегория')]")).getAttribute("for");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='${podkid}FillButton']"))).click();
		sleep(2000);
		// driver2.switchTo().defaultContent();
		// wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[contains(@title,'${KodNum}')]"))); //Смена фрэйма
		// wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'${podkid}R') and contains(@value,'внутреннее задание')]")));

		driver2.switchTo().defaultContent();
		driver2.findElement(By.xpath("//button[contains(@aria-label,'Выполнить')]")).click(); // Ищем и нажимаем

		wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[contains(@title,'${KodNum}')]"))); //Смена фрэйма
		fzid = driver2.findElement(By.xpath("//*[contains(text(),'Фаза')]")).getAttribute("for");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'${fzid}') and @value='На контроле']")));
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
		new File("$logdir\\$logname").append(ln+errprnt);



	}
	if (JOptionPane.showConfirmDialog(null, "Закрыть?", PrevStepSucces==true ? "Проверка прошла успешно" : "Возникли ошибки", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		driver2.quit();
	}

}

