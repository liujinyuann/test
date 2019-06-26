
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/*
 * Excel読み込む
 */
public class ReadExcel {
	public static DataFrom readExcel(DataFrom dataFrom) throws EncryptedDocumentException, IOException {

		File file = new File(dataFrom.getStrInFile());
		Sheet sheet = null;
		// ファイルのデータを読み込むバイトストリーム
		FileInputStream fileIn = new FileInputStream(file);
		// ワークブックを取得する
		Workbook workbook = new HSSFWorkbook(fileIn);
		fileIn.close();
		// ワークブック数を取得する
		int sheetCount = workbook.getNumberOfSheets();
		// 進捗を設定する
		int intPro = 5;
		MainFrame.setIntProgress(intPro);

		// Excel情報
		Map<String, String> dataMap = new HashMap<String, String>();
		List<Map> list = new ArrayList<Map>();
		List<List> listAll = new ArrayList<List>();
		List<String> listSheetName = new ArrayList<String>();

		// ワークブックをループする
		for (int i = 0; i < sheetCount-1; i++) {
			sheet = workbook.getSheetAt(i);
			// 行数を取得する
//			int rows = sheet.getLastRowNum() + 1;
			Row row = sheet.getRow(0);
			int strDate = (int) row.getCell(getExcelColIndex("C")).getNumericCellValue();

			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
			String minDateStr = "1899-12-30";
			Calendar calc =Calendar.getInstance();

			int maxDate =0;

			try {
				calc.setTime(sdf.parse(minDateStr));
	            calc.add(calc.DATE, strDate);
	            Date minDate = calc.getTime();
	            KintaiKanriNyuuryoku.setStrYMD(sdf.format(minDate));
	            maxDate = calc.getActualMaximum(Calendar.DATE);
			} catch (ParseException e1) {
	            e1.printStackTrace();
	        }

			int rows = 7 + maxDate;
			// リストを初期化
			list = new ArrayList<Map>();
			// データを取得
			for (int j = 7; j < rows; j++) {
				row = sheet.getRow(j);
				dataMap = new HashMap<String, String>();

				try {
					// 日を取得する
					dataMap.put("Niti", String.valueOf(row.getCell(getExcelColIndex("B")).getNumericCellValue()));
				} catch (Exception e) {
					dataMap.put("Niti", String.valueOf(row.getCell(getExcelColIndex("B")).getStringCellValue()));
				}

				// 出社を取得する
				dataMap.put("Shussha", String.valueOf(row.getCell(getExcelColIndex("E")).getNumericCellValue() * 24));
				// 退社を取得する
				dataMap.put("Taisha", String.valueOf(row.getCell(getExcelColIndex("F")).getNumericCellValue() * 24));
				// 休暇を取得する
				dataMap.put("Kyuuka", row.getCell(getExcelColIndex("P")).getStringCellValue());

				list.add(dataMap);

				// 進捗を設定する
				intPro ++;
				if (intPro < 20) {
					MainFrame.setIntProgress(intPro);
				}
			}
			// シート名を取得する
			listSheetName.add(sheet.getSheetName());
			listAll.add(list);
		}
		dataFrom.setListSheetName(listSheetName);
		dataFrom.setList(listAll);

		// 進捗を設定する
		MainFrame.setIntProgress(20);

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