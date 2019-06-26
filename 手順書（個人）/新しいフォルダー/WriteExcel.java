package seleniumTest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	/*
	 * 座標点の設定
	 */
	public static class CellPoint {
		// 座標x
		private int x;
		// 座標y
		private int y;

		public CellPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		// 座標xを取得する
		public int getX() {
			return x;
		}

		// 座標xを設定する
		public void setX(int x) {
			this.x = x;
		}

		// 座標yを取得する
		public int getY() {
			return y;
		}

		// 座標yを設定する
		public void setY(int y) {
			this.y = y;
		}
	}

	/*
	 * Excel書き込む
	 */
	public static void writeExcel(String strArray[][], DataFrom dataFrom) {
		// ファイルにデータを書き込むバイトストリーム
		FileOutputStream fileOut = null;
		// 画像ファイル
		File picFile = null;
		// Excelファイル
		File exlFile = null;
		// 座標X
		int intWriteX = 0;
		// ワークブック
		Workbook workbook = null;
		// シート
		Sheet sheet = null;
		// 行
		Row row = null;

		try {
			// 現新環境を取得する
			String strGensin = dataFrom.getStrGensin();
			// 出力ファイル種類を取得する
			String strOutfileType = dataFrom.getStrOutfileType();

			// 現新環境の判断
			if ("新".equals(strGensin)) {
				intWriteX = 13;
			} else {
				intWriteX = 0;
			}
			// 座標点
			CellPoint cellPoint = new CellPoint(intWriteX, 1);

			if ("纏め".equals(strOutfileType)) {
				exlFile = new File("D:/selenium/Output/単体テスト結果.xlsx");
				// ファイルのデータを読み込むバイトストリーム
				FileInputStream fileIn = new FileInputStream(exlFile);
				// ワークブックを取得する
				workbook = new XSSFWorkbook(fileIn);
				sheet = workbook.getSheetAt(0);
				row = sheet.getRow(0);
				fileIn.close();
			} else {
				// ワークブックを新規する
				workbook = new XSSFWorkbook();
				// シートを作る
				sheet = workbook.createSheet("test href");
				row = sheet.createRow(0);
			}

			Drawing<?> patriarch = sheet.createDrawingPatriarch();
			Cell cell = row.createCell(intWriteX);
			cell.setCellValue(strGensin);

			for (int i = 0; i < strArray.length; i++) {
				if (strArray[i][0] != null) {
					if ("纏め".equals(strOutfileType)) {
						// 行を取得
						row = sheet.getRow(cellPoint.getY());
					} else {
						// 行を作る
						row = sheet.createRow(cellPoint.getY());
					}
					// セルを作る
					cell = row.createCell(intWriteX);
					// 値の設定
					cell.setCellValue(strArray[i][0]);
					// 座標の設定
					cellPoint = new CellPoint(cellPoint.getX(), cellPoint.getY() + 1);
				}

				if (strArray[i][1] != null) {
					picFile = new File(strArray[i][1]);
					// 画像書き込む
					cellPoint = writeImg(patriarch, workbook, picFile, cellPoint);
				}
			}

			exlFile = new File("D:/selenium/Output/単体テスト結果.xlsx");
			fileOut = new FileOutputStream(exlFile);
			// Excelファイル書き込む
			workbook.write(fileOut);
			System.out.println("----Excleファイル出力しました------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * 画像書き込む
	 */
	public static CellPoint writeImg(Drawing<?> patriarch, Workbook wb, File file, CellPoint cellPoint)
			throws IOException {

		BufferedImage bufferImg = null;
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		bufferImg = ImageIO.read(file);
		ImageIO.write(bufferImg, "png", byteArrayOut);

		int x1 = cellPoint.getX();
		int y1 = cellPoint.getY();
		// 画像の幅
		int width = bufferImg.getWidth() / 72;
		// 画像の高
		int height = bufferImg.getHeight() / 25;
		System.out.println(width + "..." + height);

		int x2 = x1 + width;
		int y2 = y1 + height;
		// 画像の属性を設定する
		XSSFClientAnchor anchor1 = new XSSFClientAnchor(0, 0, 0, 0, (short) x1, y1, (short) x2, y2);
		// 画像を挿入する
		patriarch.createPicture(anchor1, wb.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG));
		cellPoint = new CellPoint(x1, y2 + 1);
		return cellPoint;
	}

}
