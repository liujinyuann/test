package seleniumTest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.google.common.io.Files;

public class aspNetTest {

	public static void main(String[] args) throws IOException, InterruptedException {

		DataFrom dataFrom = new DataFrom();
		// Excel読み込み
		ReadExcel.readExcel(dataFrom);

		String strPass = "D:\\selenium\\Output\\pic\\".concat(dataFrom.getStrGensin()).concat("\\");
		// chromedriverサービスアドレス
		System.setProperty("webdriver.chrome.driver", "D:\\software\\chromedriver_win32\\chromedriver.exe");
		// WebDriverの対象を新規に作成します
		WebDriver driver = new ChromeDriver();
		Actions action = new Actions(driver);
		File scrFile;

		List<Map> fromList = dataFrom.getList();
		// 画像出力のパス
		String strPicPass = "";
		// シート名
		String strSheetName = "";
		// 対象種類
		String strTaishouShurui = "";
		// 対象ID
		String strId = "";
		// 項目値
		String strValue = "";
		// シート名
		String strSheetNameBk = "";
		// キャプチャー
		String strCapture = "";
		// キャプチャー画像ID
		String strCaptureId = "";
		String strArray[][] = new String[fromList.size()][2];
		File file;

		for (int i = 0; i < fromList.size(); i++) {
			// 対象種類
			strTaishouShurui = fromList.get(i).get("TaishouShurui").toString();
			// 対象ID
			strId = fromList.get(i).get("Id").toString();
			// 項目値
			strValue = fromList.get(i).get("Value").toString();
			// シート名
			strSheetName = fromList.get(i).get("SheetName").toString();
			// キャプチャー
			strCapture = fromList.get(i).get("Capture").toString();
			// キャプチャー画像ID
			strCaptureId = fromList.get(i).get("CaptureId").toString();
			// 画像出力のパス
			strPicPass = strPass.concat(strSheetName).concat("\\");
			if (strSheetNameBk != strSheetName) {
				// フォルダの存在チェック
				file = new File(strPicPass);
				// フォルダ存在なしの場合、新規する
				if (!file.exists()) {
					file.mkdir();
				}
				strSheetNameBk = strSheetName;
			}

			// 対象種類が「IE」の場合
			if ("IE".equals(strTaishouShurui)) {
				// 指定されたサイトを開く
				driver.get(strId);
				// キャプチャーが「要」の場合
				if ("要".equals(strCapture)) {
					// 画像のコピー
					scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					Files.copy(scrFile, new File(strPicPass.concat(strCaptureId).concat(".png")));
					strArray[i][0] = fromList.get(i).get("Setumei").toString();
					strArray[i][1] = strPicPass.concat(strCaptureId).concat(".png");
				}
			}

			// 対象種類が「Edit」の場合
			if ("Edit".equals(strTaishouShurui)) {
				action.sendKeys(driver.findElement(By.id(strId)), strValue).perform();
				// キャプチャーが「要」の場合
				if ("要".equals(strCapture)) {
					// 画像のコピー
					scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					Files.copy(scrFile, new File(strPicPass.concat(strCaptureId).concat(".png")));
					strArray[i][0] = fromList.get(i).get("Setumei").toString();
					strArray[i][1] = strPicPass.concat(strCaptureId).concat(".png");
				}
			}

			// 対象種類が「Link」の場合
			if ("Link".equals(strTaishouShurui)) {
				action.moveToElement(driver.findElement(By.id(strId))).perform();
				action.click().perform();
				// キャプチャーが「要」の場合
				if ("要".equals(strCapture)) {
					// 画像のコピー
					scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					Files.copy(scrFile, new File(strPicPass.concat(strCaptureId).concat(".png")));
					strArray[i][0] = fromList.get(i).get("Setumei").toString();
					strArray[i][1] = strPicPass.concat(strCaptureId).concat(".png");
				}
			}

		}
		// ブラウザを閉じる
		driver.quit();

		// Excel書き込む
		WriteExcel.writeExcel(strArray, dataFrom);

	}

}
