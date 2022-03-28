package runTimexml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelUtillXml {
	Sheet sheetObj;
	Row rowObj;
	Cell cellObj, cellObjTestcaseID;
	Workbook workbook;
	String cellDataTestcaseID, cellData;
	Set<String> testcaseID = new HashSet<String>();

	@Test
	public Set<String> tain(String testCaseName) {
		// Path of the excel file
		FileInputStream fs;

		try {
			fs = new FileInputStream("src/test/resources/Inn-Flow_TestCase.xlsx");
			workbook = new XSSFWorkbook(fs);// --xlsx
			// workbook = new HSSFWorkbook(fs); //-- xlx
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Sheet sheet = workbook.getSheetAt(0);
		sheetObj = workbook.getSheet(testCaseName);

		int lastRow = sheetObj.getLastRowNum();
	  int colNum=getColumnNumberByColumnName(sheetObj, "RunStatus");
		for (int i = 1; i <= lastRow; i++) {
			rowObj = sheetObj.getRow(i);
			if(rowObj==null)
				continue;
			
			cellObj = rowObj.getCell(colNum);
			if(cellObj==null) {
				continue;
			}else {
			cellData = cellObj.getStringCellValue();

			if (cellData.equalsIgnoreCase("YES")) {
				// System.out.println(cellData);
				rowObj = sheetObj.getRow(i);
				cellObjTestcaseID = rowObj.getCell(colNum-3);
				cellDataTestcaseID = cellObjTestcaseID.getStringCellValue();
				// System.out.println(cellDataTestcaseID);
				testcaseID.add(cellDataTestcaseID);
			}

		}
		}
		return testcaseID;
	}

	public Set<String> getColoumName(String sheetName) {
		Set<String> cellData = new HashSet<String>();
		sheetObj = workbook.getSheet(sheetName);
		rowObj = sheetObj.getRow(0);
		short lastCellNum = rowObj.getLastCellNum();
		for (int i = 0; i < lastCellNum; i++) {
			cellObj = rowObj.getCell(i);
			String cellDatavalue = cellObj.getStringCellValue();
			cellData.add(cellDatavalue);

		}
		return cellData;
	}

	public int getColumnNumberByColumnName(Sheet sheet, String columnName) {
		Row firstRowColumns = sheet.getRow(0);
		int columnNumber = -1;
		int columnCount = firstRowColumns.getLastCellNum();
		for (int i = 0; i <= columnCount - 1; i++) {
			
			if (firstRowColumns.getCell(i).getStringCellValue().toLowerCase().contains(columnName.toLowerCase())) {
				columnNumber = i;
				break;
			}
		}
		return columnNumber;
	}
}