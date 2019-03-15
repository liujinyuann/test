
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
		System.setProperty("webdriver.chrome.driver", "D:\\software\\chromedriver_win32\\chromedriver.exe");
		// WebDriverの対象
		WebDriver driver;
		// アクションrの対象
		Actions action;
		// 画像のファイル
		File scrFile;

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
		// 項目値
		String strValue = "";
		// キャプチャー
		String strCapture = "";
		// キャプチャー画像ID
		String strCaptureId = "";
		String strArray[][];
		// 出力リスト
		List<String[][]> outList = new ArrayList<String[][]>();
		File file;

		// 進捗を設定する
		int intPro = 30;
		MainFrame.setIntProgress(intPro);

		for (int i = 0; i < listAll.size(); i++) {
			// WebDriverの対象を新規に作成します
			driver = new ChromeDriver();
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
				Map<String, String> dataMap = list.get(j);
				// 対象種類
				strTaishouShurui = dataMap.get("TaishouShurui").toString();
				// 対象ID
				strId = dataMap.get("Id").toString();
				// 項目値
				strValue = dataMap.get("Value").toString();

				// キャプチャー
				strCapture = dataMap.get("Capture").toString();
				// キャプチャー画像ID
				strCaptureId = dataMap.get("CaptureId").toString();

				// 対象種類が「IE」の場合
				if ("IE".equals(strTaishouShurui)) {
					// 指定されたサイトを開く
					driver.get(strId);
					// キャプチャーが「要」の場合
					if ("要".equals(strCapture)) {
						// 画像のコピー
						scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
						Files.copy(scrFile, new File(strPicPass.concat(strCaptureId).concat(".png")));
						strArray[j][0] = dataMap.get("Setumei").toString();
						strArray[j][1] = strPicPass.concat(strCaptureId).concat(".png");
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
						strArray[j][0] = dataMap.get("Setumei").toString();
						strArray[j][1] = strPicPass.concat(strCaptureId).concat(".png");
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
						strArray[j][0] = dataMap.get("Setumei").toString();
						strArray[j][1] = strPicPass.concat(strCaptureId).concat(".png");
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

}