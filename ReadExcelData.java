package marathon2;

import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	public static String[][] readExcel(String fileName, String sheetName) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook("./data/" + fileName + ".xlsx");
		;
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();

		String[][] data = new String[rowCount][columnCount];
		for (int i = 1; i <= rowCount; i++) {

			for (int j = 0; j < columnCount; j++) {

				data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		workbook.close();
		return data;
	}

}
