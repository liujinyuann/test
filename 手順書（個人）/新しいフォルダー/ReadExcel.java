package seleniumTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * Excel読み込む
 */
public class ReadExcel {
	public static DataFrom readExcel(DataFrom dataFrom) throws EncryptedDocumentException, IOException {

		File file = new File("D:/selenium/入力データ.xlsx");
		// ファイル存在チェック
		if (!file.exists()) {
			JOptionPane.showMessageDialog(null, "指定されたファイルが見つかりません。\n" + "D:/selenium/入力データ.xlsx", "エラー",
					JOptionPane.ERROR_MESSAGE);
			return dataFrom;
		}
		// ファイルのデータを読み込むバイトストリーム
		FileInputStream fileIn = new FileInputStream(file);
		// ワークブックを取得する
		Workbook workbook = new XSSFWorkbook(fileIn);
		fileIn.close();
		// ワークブック数を取得する
		int sheetCount = workbook.getNumberOfSheets();

		Sheet sheet = workbook.getSheetAt(0);
		// 6行目を取得する
		Row tmp = sheet.getRow(5);
		// 現新環境を取得する
		String strGensin = tmp.getCell(getExcelColIndex("B")).getStringCellValue();
		dataFrom.setStrGensin(strGensin);
		// 7行目を取得する
		tmp = sheet.getRow(6);
		// 座標起始を取得する
		double strIndexStart = tmp.getCell(getExcelColIndex("B")).getNumericCellValue();
		dataFrom.setStrIndexStart(strIndexStart);
		// 8行目を取得する
		tmp = sheet.getRow(7);
		// テスト種類を取得する
		String strTestType = tmp.getCell(getExcelColIndex("B")).getStringCellValue();
		dataFrom.setStrTestType(strTestType);
		// 9行目を取得する
		tmp = sheet.getRow(8);
		// 出力ファイル種類を取得する
		String strOutfileType = tmp.getCell(getExcelColIndex("B")).getStringCellValue();
		dataFrom.setStrOutfileType(strOutfileType);

		Map<String, String> dataMap = new HashMap<String, String>();
		List<Map> list = new ArrayList<Map>();


		// ワークブックをループする
		for (int i = 2; i < sheetCount; i++) {
			sheet = workbook.getSheetAt(i);
			// 行数を取得する
			int rows = sheet.getLastRowNum() + 1;
			// データを取得
			for (int j = 5; j < rows; j++) {
				Row row = sheet.getRow(j);
				dataMap = new HashMap<String, String>();
				// 対象種類を取得する
				dataMap.put("TaishouShurui", row.getCell(getExcelColIndex("H")).getStringCellValue());
				// 対象IDを取得する
				dataMap.put("Id", row.getCell(getExcelColIndex("I")).getStringCellValue());
				// 対象イベントを取得する
				dataMap.put("Event", row.getCell(getExcelColIndex("N")).getStringCellValue());
				// 項目値を取得する
				dataMap.put("Value", row.getCell(getExcelColIndex("O")).getStringCellValue());
				// キャプチャーを取得する
				dataMap.put("Capture", row.getCell(getExcelColIndex("R")).getStringCellValue());
				// キャプチャー画像IDを取得する
				dataMap.put("CaptureId", row.getCell(getExcelColIndex("S")).getStringCellValue());
				// 説明を取得する
				dataMap.put("Setumei", row.getCell(getExcelColIndex("U")).getStringCellValue());
				// シート名を取得する
				dataMap.put("SheetName", sheet.getSheetName());
				list.add(dataMap);
			}
		}
		dataFrom.setList(list);

		return dataFrom;

	}

	/*
	 * インデックスにより、列名を取得する
	 */
	public static String getExcelColName(int celNum) {
		int num = celNum + 1;// celNumは0から
		String tem = "";
		while (num > 0) {
			int lo = (num - 1) % 26;// 余数を取得,AからZまでは26進数である
			tem = (char) (lo + 'A') + tem;
			num = (num - 1) / 26;
		}
		return tem;
	}

	/*
	 * 列名により、インデックスを取得する
	 */
	public static int getExcelColIndex(String colName) {
		int index = -1;
		int num = 65;// Aのユニコルドコード
		int length = colName.length();
		for (int i = 0; i < length; i++) {
			char c = colName.charAt(i);
			if (Character.isDigit(c))
				break;// 指定されたchar値が数字チェック
			index = (index + 1) * 26 + (int) c - num;
		}
		return index;
	}
}