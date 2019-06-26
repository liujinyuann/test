
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

public class AspNetTest extends Thread {

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
		} catch (Exception e) {
			e.printStackTrace();
			// メッセージを設定
			dataFrom.setStrMsg("システムエラー発生。");
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

		String strFilePass = "";
		if ("纏め".equals(dataFrom.getStrOutfileType())) {
			strFilePass = new File(dataFrom.getStrOutFolder()).getParent();
		} else {
			strFilePass = dataFrom.getStrOutFolder();
		}
		String strPass = strFilePass.concat("\\pic\\").concat(dataFrom.getStrGensin()).concat("\\");
		// chromedriverサービスアドレス
//		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "D:\\IEDriverServer\\IEDriverServer.exe");
		// WebDriverの対象
		WebDriver driver;
		// アクションの対象
		Actions action;
		// WebElement
		WebElement Option = null;

		// シート名を取得する
		List<String> listSheetName = dataFrom.getListSheetName();
		// Excel情報を取得する
		List<List> listAll = dataFrom.getList();
		// 画像出力のパス
		String strPicPass = "";
		// シート名
		String strSheetName = "";
		// 対象種類
		String strTaishouShurui = "";
		// 対象ID
		String strId = "";
		// 対象NAME
		String strName = "";
		// 対象LINK
		String strLink = "";
		// 自定義
		String strOther = "";
		// 対象イベント
		String strEvent = "";
		// 項目値
		String strValue = "";
		// 対象
		String strTaishou = "";

		String strArray[][];
		// 出力リスト
		List<String[][]> outList = new ArrayList<String[][]>();
		// Excel情報
		Map<String, String> dataMap = new HashMap<String, String>();
		File file;

		// 進捗を設定する
		int intPro = 30;
		MainFrame.setIntProgress(intPro);

		for (int i = 0; i < listAll.size(); i++) {
			// WebDriverの対象を新規に作成します
//			driver = new ChromeDriver();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			// アクションの対象を新規に作成します
			action = new Actions(driver);
			// シート名
			strSheetName = listSheetName.get(i);
			// 画像出力のパス
			strPicPass = strPass.concat(strSheetName).concat("\\");
			// フォルダの存在チェック
			file = new File(strPicPass);
			// フォルダ存在なしの場合、新規する
			if (!file.exists()) {
				file.mkdirs();
			}

			List<Map> list = listAll.get(i);
			strArray = new String[list.size()][2];
			for (int j = 0; j < list.size(); j++) {
				dataMap = list.get(j);
				// 対象種類
				strTaishouShurui = dataMap.get("TaishouShurui").toString();
				// 対象ID
				strId = dataMap.get("Id").toString();
				// 対象NAME
				strName = dataMap.get("Name").toString();
				// 対象LINK
				strLink = dataMap.get("Link").toString();
				// 自定義
				strOther = dataMap.get("Other").toString();
				// 対象イベント
				strEvent = dataMap.get("Event").toString();
				// 項目値
				strValue = dataMap.get("Value").toString();

				// 対象イベントが「Open」の場合
				if ("Open".equals(strEvent)) {
					// 指定されたサイトを開く
					driver.get(strId);
					// 画像のコピー
					filesCopy(strArray, dataMap, driver, strPicPass, j);
				} else {
					// 対象ID空以外の場合
					if (!"".equals(strId)) {
						strTaishou = strId;
						Option = driver.findElement(By.id(strId));
					} else if (!"".equals(strName)) {
						// 対象NAME空以外の場合
						strTaishou = strName;
						Option = driver.findElement(By.name(strName));
					} else if (!"".equals(strLink)) {
						// 対象NAME空以外の場合
						strTaishou = strLink;
						Option = driver.findElement(By.linkText(strLink));
					} else if (!"".equals(strOther)) {
						// 自定義空以外の場合
						strTaishou = strOther;
						Option = driver.findElement(By.cssSelector("[" + strOther + "]"));
					} else {
						strTaishou = "";
						// 対象種類が「ScrollBar」の場合
						if (!"ScrollBar".equals(strTaishouShurui)) {
							dataFrom.setStrMsg("対象が指定しない、入力ファイルの対象ID、対象NAME、自定義が確認ください。");
							dataFrom.setStrCode("1");
							return dataFrom;
						}
					}
				}

				// 対象イベントが「Edit」の場合
				if ("Edit".equals(strEvent)) {
//					action.sendKeys(Option, strValue).perform();
					Option.sendKeys(strValue);
					// 画像のコピー
					filesCopy(strArray, dataMap, driver, strPicPass, j);
				}

				// 対象イベントが「Click」の場合
				if ("Click".equals(strEvent)) {
					// 対象種類が「Select」の場合
					if ("Select".equals(strTaishouShurui)) {
						Select select = new Select(Option);
						select.selectByVisibleText(strValue);
					} else {
						action.moveToElement(Option).perform();
						action.click().perform();
						Thread.sleep(1000);
					}
					// 画像のコピー
//					filesCopy(strArray, dataMap, driver, strPicPass, j);
					dealPotentialAlert(driver, true);
					filesCopy(strArray, dataMap, driver, strPicPass, j);
				}

				// 対象種類が「ScrollBar」の場合
				if ("ScrollBar".equals(strTaishouShurui)) {
					if ("".equals(strTaishou)) {
						((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight/4)");
						// 画像のコピー
						filesCopy(strArray, dataMap, driver, strPicPass, j);
						((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
						// 画像のコピー
						filesCopy(strArray, dataMap, driver, strPicPass, j);
					} else {
						action.moveToElement(Option).perform();
						((JavascriptExecutor) driver)
								.executeScript(strTaishou + ".scrollTo(0,document.body.scrollHeight/4)");
						// 画像のコピー
						filesCopy(strArray, dataMap, driver, strPicPass, j);
						((JavascriptExecutor) driver)
								.executeScript(strTaishou + ".scrollTo(0,document.body.scrollHeight)");
						// 画像のコピー
						filesCopy(strArray, dataMap, driver, strPicPass, j);
					}
				}

				intPro = intPro + 5;
				// 進捗を設定する
				if (intPro < 85) {
					MainFrame.setIntProgress(intPro);
				}
			}
			// 出力リストを設定する
			outList.add(strArray);
			// ブラウザを閉じる
			driver.quit();
		}

		// 進捗を設定する
		MainFrame.setIntProgress(85);
		// Excel書き込む
		WriteExcel.writeExcel(outList, dataFrom);

		return dataFrom;

	}

	/*
	 * 画像のコピー
	 */
	public static String[][] filesCopy(String strArray[][], Map<String, String> dataMap, WebDriver driver,
			String strPicPass, int j) throws IOException {
		// キャプチャー
		String strCapture = dataMap.get("Capture").toString();
		// キャプチャー画像ID
		String strCaptureId = dataMap.get("CaptureId").toString();
		// 画像のファイル
		File scrFile;
		// キャプチャーが「要」の場合
		if ("要".equals(strCapture)) {
			// 画像のコピー
//			scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			scrFile = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(scrFile, new File(strPicPass.concat(strCaptureId).concat(".png")));
			strArray[j][0] = dataMap.get("Setumei").toString();
			strArray[j][1] = strPicPass.concat(strCaptureId).concat(".png");
		}
		return strArray;
	}

	/*
	 * 提示メッセージの操作
	 */
	public static boolean dealPotentialAlert(WebDriver driver, boolean option) {
		boolean flag = false;
		try {
			Alert alert = driver.switchTo().alert();
			if (null == alert)
				throw new NoAlertPresentException();
			try {
				if (option) {
					File scrFile = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
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
