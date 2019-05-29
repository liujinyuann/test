
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Pms extends Thread {

	// 進捗
	public static Date dateYMD;

	// 進捗を設定
	public static void setStrYMD(Date dareYmd) {
		dateYMD = dareYmd;
	}

	public void run() {

		DataFrom dataFrom = MainFrame.dataFrom;
		try {
			mainTest();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			// メッセージを設定
//			dataFrom.setStrMsg(e.getMessage());
			dataFrom.setStrMsg("入力失敗。");
			// コードを設定
			dataFrom.setStrCode("1");
		} catch (Exception e) {
			e.printStackTrace();
			// メッセージを設定
//			dataFrom.setStrMsg(e.getMessage());
			dataFrom.setStrMsg("入力失敗。");
			// コードを設定
			dataFrom.setStrCode("1");
		}
	}

	public static DataFrom mainTest() throws IOException, InterruptedException, Exception {
		// 進捗を設定する
		MainFrame.setIntProgress(1);
		DataFrom dataFrom = MainFrame.dataFrom;
		// Excel読み込み
		ReadExcel.readExcel(dataFrom);
		// 進捗を設定する
		MainFrame.setIntProgress(25);

		// chromedriverサービスアドレス
		System.setProperty("webdriver.chrome.driver", "D:\\PMSInput\\chromedriver.exe");
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
		driver.get("http://pms.dhc.com.cn/");

		action.sendKeys(driver.findElement(By.id("txtUserName")), dataFrom.getStrUserId()).perform();
		action.sendKeys(driver.findElement(By.id("txtPassword")), dataFrom.getStrPwd()).perform();

		String inputValue = JOptionPane.showInputDialog("確認コードを入力ください。");

		MainFrame.jf_1.setExtendedState(JFrame.ICONIFIED);

		action.sendKeys(driver.findElement(By.name("CaptchaControl1")), inputValue).perform();

		action.moveToElement(driver.findElement(By.name("btnLogin"))).perform();
		action.click().perform();

		try {
			action.moveToElement(driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_lblSceneClose"))).perform();
			action.click().perform();
			Thread.sleep(100);
		} catch (Exception e) {
			Thread.sleep(100);
		}

		String strKozinKanri = "";
		String strKintaiShinsei = "";
		String strKyuukalbl = "";
		String strYukyuKyuka = "";
		String strNisshiNyuryoku = "";

		WebElement eleLanguage = driver.findElement(By.id("quickdemoLanguage"));
		String selectedSale = eleLanguage.getText();
		if (!"日本語".equals(selectedSale)) {
			strKozinKanri = "个人管理";
			strKintaiShinsei = "考勤申请";
			strKyuukalbl = "休假";
			strYukyuKyuka = "年休假";
			strNisshiNyuryoku = "日志录入";
		} else {
			strKozinKanri = "個人管理";
			strKintaiShinsei = "勤怠申請";
			strKyuukalbl = "休暇";
			strYukyuKyuka = "有給休暇";
			strNisshiNyuryoku = "日誌入力";
		}

		action.moveToElement(driver.findElement(By.linkText(strKozinKanri))).perform();
		action.click().perform();
		Thread.sleep(500);

		action.moveToElement(driver.findElement(By.linkText(strKintaiShinsei))).perform();
		action.click().perform();

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
		// 正常
		String strNormalHour = "";
		// 夜間
		String strNightHour = "";
		// 徹夜
		String strMidNightHour = "";

		// Excel情報
		Map<String, String> dataMap = new HashMap<String, String>();
		File file;

		// 進捗を設定する
		int intPro = 30;
		MainFrame.setIntProgress(intPro);

		String[] strShusshaArr;
		String[] strTaishaArr;
		String strStartTimeHour;
		int intStartTimeMinute;
		String strStartTimeMinute;
		String strEndTimeHour;
		int intEndTimeMinute;
		String strEndTimeMinute;
		String strHiduke;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar calc = Calendar.getInstance();

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

				if ((!"0.0".equals(strShussha) && !"0.0".equals(strTaisha)) || strKyuuka != "") {

					Select selectPjName = new Select(
							driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_ddlProjectName")));
					selectPjName.selectByVisibleText(dataFrom.getStrPjName());

					if (strKyuuka == "") {
						strShusshaArr = strShussha.split("\\.");
						strTaishaArr = strTaisha.split("\\.");

						strStartTimeHour = String.format("%0" + 2 + "d", Integer.parseInt(strShusshaArr[0]));
						if (Integer.parseInt(strShusshaArr[1]) > 10) {
							intStartTimeMinute = Integer.parseInt(strShusshaArr[1]) / 10 * 6;
						} else {
							intStartTimeMinute = Integer.parseInt(strShusshaArr[1]) * 6;
						}
						strStartTimeMinute = String.format("%0" + 2 + "d", intStartTimeMinute);
						strEndTimeHour = String.format("%0" + 2 + "d", Integer.parseInt(strTaishaArr[0]));
						if (Integer.parseInt(strTaishaArr[1]) > 10) {
							intEndTimeMinute = Integer.parseInt(strTaishaArr[1]) / 10 * 6;
						} else {
							intEndTimeMinute = Integer.parseInt(strTaishaArr[1]) * 6;
						}
						strEndTimeMinute = String.format("%0" + 2 + "d", intEndTimeMinute);

						Select selectDdlKind = new Select(
								driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_ddlKind")));
						selectDdlKind.selectByVisibleText("外出");
					} else {
						Select selectDdlKind = new Select(
								driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_ddlKind")));
						selectDdlKind.selectByVisibleText(strKyuukalbl);

						Select selectDdlHOLIDAY = new Select(
								driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_ddlHOLIDAY_CODE")));
						selectDdlHOLIDAY.selectByVisibleText(strYukyuKyuka);

						strStartTimeHour = "09";
						strStartTimeMinute = "00";
						strEndTimeHour = "18";
						strEndTimeMinute = "00";
					}

					calc.setTime(dateYMD);
					calc.add(calc.DATE, j);
					strHiduke = sdf.format(calc.getTime());
					Thread.sleep(500);

					action.sendKeys(driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_txtStartDate")),
							strHiduke).perform();
					Thread.sleep(100);
					action.sendKeys(driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_txtEndDate")), strHiduke)
							.perform();
					Thread.sleep(100);

					action.sendKeys(driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_txtStartTime")),
							strStartTimeHour + strStartTimeMinute).perform();
					Thread.sleep(100);
					action.sendKeys(driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_txtEndTime")),
							strEndTimeHour + strEndTimeMinute).perform();

					Thread.sleep(100);
					action.moveToElement(driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_btnCreat")))
							.perform();
					action.click().perform();
					Thread.sleep(500);

					try {
						action.moveToElement(driver.findElement(By.id("popup_ok"))).perform();
						action.click().perform();
						Thread.sleep(100);
						action.moveToElement(driver.findElement(By.linkText(strKintaiShinsei))).perform();
						action.click().perform();
						Thread.sleep(100);
					} catch (Exception e) {
						Thread.sleep(100);
					}

					dealPotentialAlert(driver, true);
				}

				intPro = intPro + 5;
				// 進捗を設定する
				if (intPro < 55) {
					MainFrame.setIntProgress(intPro);
				}
			}
			// ブラウザを閉じる
//			driver.quit();
		}

		action.moveToElement(driver.findElement(By.linkText(strNisshiNyuryoku))).perform();
		action.click().perform();

		WebElement ele;

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

				// 正常
				strNormalHour = dataMap.get("NormalHour").toString();
				// 夜間
				strNightHour = dataMap.get("NightHour").toString();
				// 徹夜
				strMidNightHour = dataMap.get("MidNightHour").toString();

				if (!"0.0".equals(strShussha) && !"0.0".equals(strTaisha)) {
					strShusshaArr = strShussha.split("\\.");
					strTaishaArr = strTaisha.split("\\.");

					strStartTimeHour = String.format("%0" + 2 + "d", Integer.parseInt(strShusshaArr[0]));
					if (Integer.parseInt(strShusshaArr[1]) > 10) {
						intStartTimeMinute = Integer.parseInt(strShusshaArr[1]) / 10 * 6;
					} else {
						intStartTimeMinute = Integer.parseInt(strShusshaArr[1]) * 6;
					}
					strStartTimeMinute = String.format("%0" + 2 + "d", intStartTimeMinute);
					strEndTimeHour = String.format("%0" + 2 + "d", Integer.parseInt(strTaishaArr[0]));
					if (Integer.parseInt(strTaishaArr[1]) > 10) {
						intEndTimeMinute = Integer.parseInt(strTaishaArr[1]) / 10 * 6;
					} else {
						intEndTimeMinute = Integer.parseInt(strTaishaArr[1]) * 6;
					}
					strEndTimeMinute = String.format("%0" + 2 + "d", intEndTimeMinute);

					calc.setTime(dateYMD);
					calc.add(calc.DATE, j);
					strHiduke = sdf.format(calc.getTime());
					Thread.sleep(500);

					ele = driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_txtDate"));
					ele.clear();
					Thread.sleep(100);
					action.sendKeys(ele, strHiduke).perform();
					Thread.sleep(100);

					action.moveToElement(driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_ddlProject")))
							.perform();
					action.click().perform();
					Thread.sleep(100);
					action.click().perform();
					Select selectPjName = new Select(
							driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_ddlProject")));
					selectPjName.selectByVisibleText(dataFrom.getStrPjName());
					Thread.sleep(1500);

					if (!"".equals(driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_txtTotalNormalHour"))
							.getText())) {
						Thread.sleep(100);
						continue;
					}

					if (!"".equals(
							driver.findElement(By.xpath(".//*[@id='77702483-367b-45bd-8a34-aadc3beaab16']/td[8]"))
									.getText())) {
						Thread.sleep(100);
						continue;
					}

					if (!"0.0".equals(strNormalHour)) {
						driver.findElement(By.xpath(".//*[@id='77702483-367b-45bd-8a34-aadc3beaab16']/td[8]")).click();
						Thread.sleep(200);
						action.sendKeys(driver.findElement(By.id("1_NormalHour")), strNormalHour).perform();
						Thread.sleep(200);
					}
					if (!"0.0".equals(strNightHour)) {
						driver.findElement(By.xpath(".//*[@id='77702483-367b-45bd-8a34-aadc3beaab16']/td[9]")).click();
						Thread.sleep(200);
						action.sendKeys(driver.findElement(By.id("1_NightHour")), strNightHour).perform();
						Thread.sleep(200);
					}
					if (!"0.0".equals(strMidNightHour)) {
						driver.findElement(By.xpath(".//*[@id='77702483-367b-45bd-8a34-aadc3beaab16']/td[10]")).click();
						Thread.sleep(200);
						action.sendKeys(driver.findElement(By.id("1_MidNightHour")), strMidNightHour).perform();
						Thread.sleep(200);
					}

					if (j == list.size() - 1) {
						action.moveToElement(driver.findElement(By.id("ctl00_ctl00_userContent_mainBody_rbtFace_1"))).perform();
						Thread.sleep(200);
						action.click().perform();
						Thread.sleep(200);
					}

					action.moveToElement(driver.findElement(By.id("ctl00_ctl00_userContent_topToolBar_btnSave")))
							.perform();
					action.click().perform();
					Thread.sleep(1500);
					dealPotentialAlert(driver, true);
				}

				intPro = intPro + 5;
				// 進捗を設定する
				if (intPro < 95) {
					MainFrame.setIntProgress(intPro);
				}
			}
			// ブラウザを閉じる
//			driver.quit();
		}

//		if (!"日本語".equals(selectedSale)) {
//			Select selectLanguage = new Select(
//					driver.findElement(By.id("quickdemoLanguage")));
//			selectLanguage.selectByVisibleText(selectedSale);
//		}

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
