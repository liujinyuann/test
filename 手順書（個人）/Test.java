package seleniumTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.io.Files;

public class Test {

	private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "D:\\software\\chromedriver_win32\\chromedriver.exe");// chromedriverサービスアドレス
		WebDriver driver = new ChromeDriver(); // WebDriverの対象を新規に作成します
		driver.get("http://www.baidu.com");// 指定されたサイトを開く
		File scrFile = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(scrFile, new File("d:\\screenfile1.png"));

//        driver.findElement(By.id("kw")).sendKeys(new  String[] {"hello"});//kwの元素のidを探し、ハローを入力します
//        driver.findElement(By.id("su")).click(); //クリックボタンを押す

		By inputbox = By.id("kw");
		By searchButton = By.id("su");

		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(inputbox), "hello").perform();
		action.moveToElement(driver.findElement(searchButton)).perform();
		scrFile = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(scrFile, new File("d:\\screenfile2.png"));
		action.click().perform();
		Thread.sleep(2000);
		scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(scrFile, new File("d:\\screenfile3.png"));

//        FileOutputStream fileOut = null;
//        BufferedImage bufferImg = null;
//
//        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//        bufferImg = ImageIO.read(new File("d:/456.jpeg"));
//        ImageIO.write(bufferImg, "jpg", byteArrayOut);
//
//        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFSheet sheet1 = wb.createSheet("test picture");
//        HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
//        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255,(short) 1, 1, (short) 13, 50);
//        patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
//        fileOut = new FileOutputStream("D:/测试Excel.xls");
//        wb.write(fileOut);
//        fileOut.close();

//		String basePath = "D:\\";
//		Workbook wb = new HSSFWorkbook();
//		FileInputStream fis = new FileInputStream(basePath + "456.jpeg");
//		byte[] bytes = IOUtils.toByteArray(fis);
//		int pictureIdx = wb.addPicture(bytes, wb.PICTURE_TYPE_JPEG);
//		fis.close();
//
//		Sheet sheet = wb.createSheet("sheet1");
//		Drawing drawing = sheet.createDrawingPatriarch();
//		CreationHelper helper = wb.getCreationHelper();
//		ClientAnchor anchor = helper.createClientAnchor();
//		anchor.setCol1(3);
//		anchor.setRow1(2);
//		Picture pict = drawing.createPicture(anchor, pictureIdx);
//		pict.resize();
//		String file = "生成的EXCEL.xls";
//		if (wb instanceof XSSFWorkbook)
//			file += "x";
//		FileOutputStream fos = new FileOutputStream(basePath + file);
//		wb.write(fos);
//		fos.close();

//		try {
//			WorkBook workBook = new WorkBook();
//
//			// Inserting image
//			workBook.addPicture(1, 0, 15, 50, "d:\\screenfile1.png");
//			workBook.addPicture(1, 51, 15, 100, "d:\\screenfile2.png");
//			PictureShape pictureShape = workBook.addPicture(1, 101, 15, 150, "d:\\screenfile3.png");
//			ShapeFormat shapeFormat = pictureShape.getFormat();
//			shapeFormat.setPlacementStyle(ShapeFormat.PlacementFreeFloating);
//			pictureShape.setFormat();
//
//			workBook.write("d:\\pic.xls");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		Map<String, String> dataMap=new HashMap<String, String>();
        dataMap.put("BankName", "BankName");
        dataMap.put("Addr", "Addr");
        dataMap.put("Phone", "Phone");
        List<Map> list=new ArrayList<Map>();
        list.add(dataMap);
        writeExcel(list, 3, "D:/writeExcel.xlsx");

		try {
			/**
			 * WebDriverはスマートに待つ方法。 dr.manage().timeouts().implicitlyWait(arg0, arg1）；
			 * Arg0：待ち時間，int タイプ ； Arg1：待ち時間の単位 TimeUnit.SECONDS 普通は秒を単位とする。
			 */
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * dr.quit()およびdr.close()は、両方のブラウザから脱退することが可能です,簡単に両者の違いを言いなさい：
		 * close，ページを複数開いた場合は、きれいに閉まらない，現在のページだけを閉じています。
		 * quit，すべてのWebdriverの窓を開いたのです，だからquitの一番のcase脱退方法をオススメします。
		 */
		driver.quit();// ブラウザを脱退する

	}

	public static void writeExcel(List<Map> dataList, int cloumnCount,String finalXlsxPath){
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
                String name = dataMap.get("BankName").toString();
                String address = dataMap.get("Addr").toString();
                String phone = dataMap.get("Phone").toString();
                for (int k = 0; k <= columnNumCount; k++) {
                // 在一行内循环
                Cell first = row.createCell(0);
                first.setCellValue(name);

                Cell second = row.createCell(1);
                second.setCellValue(address);

                Cell third = row.createCell(2);
                third.setCellValue(phone);
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

	/**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @param filename
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook();
        }
        return wb;
    }

}
