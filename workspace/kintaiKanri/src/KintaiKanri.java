import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class KintaiKanri {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// chromedriverサービスアドレス
				System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
				// WebDriverの対象
				WebDriver driver;
				// アクションの対象
				Actions action;
				// WebElement
				WebElement Option = null;

				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--start-maximized");

				// WebDriverの対象を新規に作成します
				driver = new ChromeDriver(chromeOptions);
				// アクションの対象を新規に作成します
				action = new Actions(driver);

				// 指定されたサイトを開く
				driver.get("http://54.199.214.226/time4/srv/");


				action.sendKeys(driver.findElement(By.id("txtUserId")), "jinyuan.liu").perform();
				action.sendKeys(driver.findElement(By.id("txtPassWord")), "1qaz!QAZ").perform();

				action.moveToElement(driver.findElement(By.className("LoginButton"))).perform();
				action.click().perform();

				action.moveToElement(driver.findElement(By.id("menuTimeInput"))).perform();
				action.click().perform();

				action.moveToElement(driver.findElement(By.linkText("勤怠一覧"))).perform();
				action.click().perform();

				action.moveToElement(driver.findElement(By.cssSelector("[" + "onclick=\"submitTransfer(event, null, null, new Array('transferredAction', 'jp.mosp.time.input.action.AttendanceCardAction', 'transferredIndex', '0'), 'TM1106');\"" + "]"))).perform();
				action.click().perform();

////				削除
//				for (int i =1; i<30 ;i++) {
//					action.moveToElement(driver.findElement(By.id("eventNext"))).perform();
//					action.click().perform();
//
//					action.moveToElement(driver.findElement(By.id("btnDelete"))).perform();
//					action.click().perform();
//
//					dealPotentialAlert(driver, true);
//				}

//				//入力
				for (int i =0; i<30 ;i++) {

					action.sendKeys(driver.findElement(By.name("txtStartTimeHour")), "09").perform();
					action.sendKeys(driver.findElement(By.name("txtStartTimeMinute")), "00").perform();
					action.sendKeys(driver.findElement(By.name("txtEndTimeHour")), "18").perform();
					action.sendKeys(driver.findElement(By.name("txtEndTimeMinute")), "00").perform();
					action.sendKeys(driver.findElement(By.name("txtTimeComment")), "開発一部共通PJ").perform();

					action.moveToElement(driver.findElement(By.id("btnRegist"))).perform();
					action.click().perform();
					dealPotentialAlert(driver, true);

					action.moveToElement(driver.findElement(By.id("eventNext"))).perform();
					action.click().perform();
					dealPotentialAlert(driver, true);
				}

	}

	public static boolean dealPotentialAlert(WebDriver driver,boolean option) {
	    boolean flag = false;
	    try {
	        Alert alert = driver.switchTo().alert();
	        if (null == alert)
	            throw new NoAlertPresentException();
	        try {
	            if (option) {
	                alert.accept();
	                System.out.println("Accept the alert: " + alert.getText());
	            } else {
	                alert.dismiss();
	                System.out.println("Dismiss the alert: " + alert.getText());
	            }
	            flag = true;
	        } catch (WebDriverException ex) {
	            if (ex.getMessage().startsWith("Could not find"))
	                System.out.println("There is no alert appear!");
	            else
	                throw ex;
	        }
	    } catch (NoAlertPresentException e) {
	        System.out.println("There is no alert appear!");
	    }
	    return flag;
	}

}
