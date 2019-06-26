
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class KintaiKanriNyuuryoku extends Thread {

	// 進捗
	public static String strYMD = "";

	// 進捗を設定
	public static void setStrYMD(String strYmd) {
		strYMD = strYmd;
	}

	public void run() {

		DataFrom dataFrom = MainFrame.dataFrom;
		try {
			mainTest();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			// メッセージを設定
			dataFrom.setStrMsg(e.getMessage());
			// コードを設定
			dataFrom.setStrCode("1");
		}
	}

	public static DataFrom mainTest() throws IOException, InterruptedException {
		// 進捗を設定する
		MainFrame.setIntProgress(1);
		DataFrom dataFrom = MainFrame.dataFrom;
		// Excel読み込み
		ReadExcel.readExcel(dataFrom);
		// 進捗を設定する
		MainFrame.setIntProgress(25);

		// chromedriverサービスアドレス
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
		// WebDriverの対象
		WebDriver driver;
		// アクションの対象
		Actions action;

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");

		// WebDriverの対象を新規に作成します
		driver = new ChromeDriver(chromeOptions);
		// アクションの対象を新規に作成します
		action = new Actions(driver);

		// 指定されたサイトを開く
		driver.get("http://54.199.214.226/time4/srv/");

		action.sendKeys(driver.findElement(By.id("txtUserId")), dataFrom.getStrUserId()).perform();
		action.sendKeys(driver.findElement(By.id("txtPassWord")), dataFrom.getStrPwd()).perform();

		action.moveToElement(driver.findElement(By.className("LoginButton"))).perform();
		action.click().perform();

		action.moveToElement(driver.findElement(By.id("menuTimeInput"))).perform();
		action.click().perform();

		action.moveToElement(driver.findElement(By.linkText("勤怠一覧"))).perform();
		action.click().perform();

		action.moveToElement(driver.findElement(By.cssSelector("["
				+ "onclick=\"submitTransfer(event, null, null, new Array('transferredAction', 'jp.mosp.time.input.action.AttendanceCardAction', 'transferredIndex', '0'), 'TM1106');\""
				+ "]"))).perform();
		action.click().perform();

		// シート名を取得する
		List<String> listSheetName = dataFrom.getListSheetName();
		// Excel情報を取得する
		List<List> listAll = dataFrom.getList();
		// 日
		String strNiti = "";
		// 出社
		String strShussha = "";
		// 退社
		String strTaisha = "";
		// 休暇
		String strKyuuka = "";

		// Excel情報
		Map<String, String> dataMap = new HashMap<String, String>();
		File file;

		// 進捗を設定する
		int intPro = 30;
		MainFrame.setIntProgress(intPro);

		String[] strShusshaArr;
		String[] strTaishaArr;
		String strStartTimeHour;
		double intStartTimeMinute;
		String strStartTimeMinute;
		String strEndTimeHour;
		double intEndTimeMinute;
		String strEndTimeMinute;

		for (int i = 0; i < listAll.size(); i++) {

			List<Map> list = listAll.get(i);
			for (int j = 0; j < list.size(); j++) {
				dataMap = list.get(j);
				// 日
				strNiti = dataMap.get("Niti").toString();
				// 出社
				strShussha = dataMap.get("Shussha").toString();
				// 退社
				strTaisha = dataMap.get("Taisha").toString();
				// 休暇
				strKyuuka = dataMap.get("Kyuuka").toString();

				if (!"0.0".equals(strShussha) && !"0.0".equals(strTaisha)) {
					strShusshaArr = strShussha.split("\\.");
					strTaishaArr = strTaisha.split("\\.");

					strStartTimeHour = String.format("%0" + 2 + "d", Integer.parseInt(strShusshaArr[0]));
					if (Integer.parseInt(strShusshaArr[1]) > 10) {
//						intStartTimeMinute = Integer.parseInt(strShusshaArr[1])/10 * 6;
						intStartTimeMinute = Integer.parseInt(strShusshaArr[1]) * 0.6;
					} else {
						intStartTimeMinute = Integer.parseInt(strShusshaArr[1]) * 6;
					}
					strStartTimeMinute = String.format("%0" + 2 + "d", (int) intStartTimeMinute);
					strEndTimeHour = String.format("%0" + 2 + "d", Integer.parseInt(strTaishaArr[0]));
					if (Integer.parseInt(strTaishaArr[1]) > 10) {
//						intEndTimeMinute = Integer.parseInt(strTaishaArr[1])/10 * 6;
						intEndTimeMinute = Integer.parseInt(strTaishaArr[1]) * 0.6;
					} else {
						intEndTimeMinute = Integer.parseInt(strTaishaArr[1]) * 6;
					}
					strEndTimeMinute = String.format("%0" + 2 + "d", (int) intEndTimeMinute);

					action.sendKeys(driver.findElement(By.name("txtStartTimeHour")), strStartTimeHour).perform();
					action.sendKeys(driver.findElement(By.name("txtStartTimeMinute")), strStartTimeMinute).perform();
					action.sendKeys(driver.findElement(By.name("txtEndTimeHour")), strEndTimeHour).perform();
					action.sendKeys(driver.findElement(By.name("txtEndTimeMinute")), strEndTimeMinute).perform();
					action.sendKeys(driver.findElement(By.name("txtTimeComment")), dataFrom.getStrPjName()).perform();

					action.moveToElement(driver.findElement(By.id("btnRegist"))).perform();
					action.click().perform();
					dealPotentialAlert(driver, true);
				}

				if (j < list.size() - 1) {
					action.moveToElement(driver.findElement(By.id("eventNext"))).perform();
					action.click().perform();
					dealPotentialAlert(driver, true);
				}

				intPro = intPro + 5;
				// 進捗を設定する
				if (intPro < 85) {
					MainFrame.setIntProgress(intPro);
				}
			}
			// ブラウザを閉じる
//			driver.quit();
		}

		action.moveToElement(driver.findElement(By.linkText("勤怠報告-勤怠一覧"))).perform();
		action.click().perform();

		// 進捗を設定する
		MainFrame.setIntProgress(100);
		dataFrom.setStrMsg("入力完了。");
		dataFrom.setStrCode("0");

		return dataFrom;

	}

	public static boolean dealPotentialAlert(WebDriver driver, boolean option) {
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
