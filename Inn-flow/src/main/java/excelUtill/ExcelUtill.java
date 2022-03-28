package excelUtill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelUtill {

	
	    static Workbook wbook ;
	    static Sheet sheetObj;	
		static InputStream fi;
		static OutputStream fo;
		static Row rowObj;
		static Cell cellObj;
	
	
	
	private  ExcelUtill() {
		
	}
	

//	public static void main(String[] args) {
//		String path="Inn-Flow_TestCase.xlsx";
//		ExcelUtill.getExcelObj().getDataFromExcelFromTestCase(path, "LogInPage", "TC001ValidLogin", "TestCaseID");
//	}
	//===============================ExcelUtil Singilton============================================//
  private static ExcelUtill excelObj;
	public static  ExcelUtill getExcelObj() {
		if(excelObj==null) {
			excelObj=new ExcelUtill();
		}
		
		return excelObj;
	}
	
	
	
//=====================================WorkBook obj==================================================//	
	
	public Workbook getWorkBook(String dataSheetPath) {
		File xlFile = new File(dataSheetPath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(xlFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] arrPath = dataSheetPath.split("\\.");
		String fileExt = arrPath[1];
		if ("xlsx".equalsIgnoreCase(fileExt)) {
			try {
				wbook = new XSSFWorkbook(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				wbook = new HSSFWorkbook(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return wbook;
	}
	
	//=================================Sheet Obj===============================================//
	public Sheet getSheetObj(Workbook workBook, String sheetName) {
		
		sheetObj = workBook.getSheet(sheetName);
		return sheetObj;
	}
	
	public Row getRowObj(Sheet sheetObj, int rowNum) {
		   rowObj=sheetObj.getRow(rowNum);
		
		return rowObj;
	}
	public String getCellData(Row dataRow, int cellNum) {
		DataFormatter df = new DataFormatter();
		Cell curCell = dataRow.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		return df.formatCellValue(curCell);

	}
//=====================================================================================================//	
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
	//============================================================================================//
	public int getRowNumberByRowID(Sheet sheet, String rowID, String columnName) {
		int rowCount = sheet.getLastRowNum();
		int columnNumber = getColumnNumberByColumnName(sheet, columnName);
		int rowNumber = -1;
		for (int i = 1; i <= rowCount; i++) {
			rowObj = sheet.getRow(i);
			if(rowObj!= null) {
			Cell cell=rowObj.getCell(columnNumber,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			
			if (cell != null && cell.getStringCellValue().equalsIgnoreCase(rowID)) {
				rowNumber = i;
				break;
			}
		 }
		}
		return rowNumber;
	}
	//===========================================================================================//
	public List<Integer> getRowNumbersListByRowID(Sheet sheet, String columnName, String rowID) {
		List<Integer> listData = new ArrayList<Integer>();
		int rowCount = sheet.getLastRowNum();
		int columnNumber = getColumnNumberByColumnName(sheet, columnName);
		for (int i = 1; i <= rowCount; i++) {
			Cell cell = sheet.getRow(i).getCell(columnNumber);
			if (cell != null && cell.getStringCellValue().equalsIgnoreCase(rowID)) {
				listData.add(i);

			}
		}
		return listData;
	}
//======================================================================================================//
	public String [] getValue(String key) {
		String strArr[] = null;
		String value;
		if(key=="EHID") {
			 value=mapObj.get(key);
			strArr =value.split(",");
			return strArr;
		}else {
			 value=mapObj.get(key);
			 strArr=value.split("");
			 return strArr;
		}
		
	}
	//===================getDataFromExcel===========================================================//
public String  getOneDataFromExcel(String pathOfWorkBook, String sheetName,int rowlth,int cellNum) {
	wbook=getWorkBook(pathOfWorkBook);
      sheetObj=getSheetObj(wbook, sheetName);
      String value = null;    
      for (int i = 1; i <= rowlth; i++) {
		
	
     Row rowObj= getRowObj(sheetObj, i);
      if(rowObj==null) {
			 break;
			}
		 
         Cell  cellObj =rowObj.getCell(cellNum,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
		  if(cellObj==null) {
			 break;
			}
		   value = cellObj.getStringCellValue();
		   
      }
       return  value;

}
	

public static  int getLastRowNum() {

	return 	sheetObj.getLastRowNum();
}
//===============================DataBaseSheetData===============================================//























//=======================================excelData=================================================//

	private Map<String,String> mapObj=new HashMap<String,  String>();
	public Map<String, String> getDataFromExcel(String path,String sheetname,String rowName, String colnmName) {
		 

	Workbook wbook = getWorkBook(path);
      sheetObj = getSheetObj(wbook, sheetname);
	int dataRowNum=getRowNumberByRowID(sheetObj, rowName, colnmName);
        
         
           int colunmNum = rowObj.getLastCellNum();
      
	for (int i = 1; i <= colunmNum - 1; i = i + 2) {
		 Row rowObj=sheetObj.getRow(dataRowNum);
         if(rowObj==null)
        	 continue;
         
		Cell cellObj =rowObj.getCell(i);
		if(cellObj==null)
       	 continue;
       	
		  String cellDataName=cellObj.getStringCellValue();
		if(cellDataName.trim().equals("")) {
			break;
		}
		String cellDataValue = rowObj.getCell(i + 1).getStringCellValue();
		mapObj.put(cellDataName, cellDataValue);
	}
	return mapObj;

	}
	
 ///===============================testcase data=======================================//
	
	public Map<String, String> getDataFromExcelFromTestCase(String path,String sheetname,String rowName, String colnmName) {
		int dataRowNum; 

		Workbook wbook = getWorkBook(path);
	      sheetObj = getSheetObj(wbook, sheetname);     
	    	  dataRowNum=getRowNumberByRowID(sheetObj, rowName, colnmName);
	    	  
	    	  for (int i =1 ; i <=7; i ++) {
	         Row rowObj=sheetObj.getRow(dataRowNum);
	         if(rowObj==null) {
					break;
				}
	           int clmNum=getColumnNumberByColumnName(sheetObj, colnmName);
	                    
	        	   dataRowNum++;
	           for(int j=clmNum+1;j<clmNum+2;j=j+2) {
		  
			  Cell  cellObj =rowObj.getCell(j);
			  if(cellObj==null) {
					break;
				}
			  String cellDataName=cellObj.getStringCellValue();
			  
			
			String cellDataValue = rowObj.getCell(j + 1).getStringCellValue();
			mapObj.put(cellDataName, cellDataValue);
		}
	      }
		return mapObj;

		}
		
 //========================================write on testcase===========================================//
public  void writeData(String path,String sheetName,int rowNum,int cellNum,String data)  {
	
//	File file= new File(path);
	/*if(!file.exists())
	wbook=new XSSFWorkbook();
	 fo= new FileOutputStream(path);
	 wbook.write(fo);
	*/   
	
	try {
		fi=new FileInputStream(path);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		wbook= new XSSFWorkbook(fi);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(wbook.getSheetIndex(sheetName)==-1)
	wbook.createSheet(sheetName);
	
	sheetObj=wbook.getSheet(sheetName);
	
	if(sheetObj.getRow(rowNum)==null) 
		sheetObj.createRow(rowNum);
		rowObj=sheetObj.getRow(rowNum);
		
		cellObj=rowObj.createCell(cellNum);
		cellObj.setCellValue(data);
		
		try {
			fo=new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wbook.write(fo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  try {
		fi.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		fo.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
//======================================keyDrivenData....===============================================//


Cell  cellObjTestcaseID;
Workbook workbook;
String cellDataTestcaseID, cellData;
Set<String> testcaseID = new HashSet<String>();
@Test
public Set<String> tain() {
	// Path of the excel file
	FileInputStream fs;

	try {
		fs = new FileInputStream("Inn-Flow_TestCase.xlsx");
		workbook = new XSSFWorkbook(fs);// --xlsx
		// workbook = new HSSFWorkbook(fs); //-- xlx
	} catch (IOException e) {
		e.printStackTrace();
	}
	// Sheet sheet = workbook.getSheetAt(0);
	sheetObj = workbook.getSheet("LogInPage");

	int lastRow = sheetObj.getLastRowNum();
  int colNum=getColumnNumberByColumnName(sheetObj, "Status");
	for (int i = 1; i <= lastRow; i++) {
		rowObj = sheetObj.getRow(i);
		cellObj = rowObj.getCell(colNum);
		if(cellObj==null) {
			continue;
		}else {
		cellData = cellObj.getStringCellValue();

		if (cellData.equalsIgnoreCase("Yes")) {
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
//=====================================write on testcase wise Result ============================//

public  void writeResultInTestCase(String path,String sheetName,String rowName,String cellName,String data)  {
	
//	File file= new File(path);
	/*if(!file.exists())
	wbook=new XSSFWorkbook();
	 fo= new FileOutputStream(path);
	 wbook.write(fo);
	*/   
	
	try {
		fi=new FileInputStream(path);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		wbook= new XSSFWorkbook(fi);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(wbook.getSheetIndex(sheetName)==-1)
	wbook.createSheet(sheetName);
	
	sheetObj=wbook.getSheet(sheetName);
	int rowNum=getRowNumberByRowID(sheetObj, rowName, cellName);
	if(sheetObj.getRow(rowNum)==null) 
		sheetObj.createRow(rowNum);
		rowObj=sheetObj.getRow(rowNum);
		int cellNum=getColumnNumberByColumnName(sheetObj, cellName);
		cellObj=rowObj.createCell(cellNum);
		cellObj.setCellValue(data);
		
		try {
			fo=new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wbook.write(fo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  try {
		fi.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		fo.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
