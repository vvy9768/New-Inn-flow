package sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import excelUtill.ExcelUtill;
import webUtills.WebUtills;

public class FatchDataFromDataBase {

	
	static Connection con = null;

	private static Statement stmt;
	
	public static String DB_URL = "jdbc:sqlserver://10.0.0.6:16845;database=MULTIDB;";

	public static String DB_USER = "IFQATester";
	
	public static String DB_PASSWORD = "chrhx?+#NjhFMT5Y";
    
static	int c,i,j;
	




public static void fatchData() {
	String role=WebUtills.getObj().getText();
	String q1="select  distinct MenuMaster.ItemName , MenuMaster.DisplayName,PermissionMaster.DisplayPermissionName, PermissionMaster.ModuleName \r\n" + 
			"from PermissionRole  inner join UserRole on PermissionRole.RoleID=UserRole.RoleID  inner join PermissionMaster\r\n" + 
			"on PermissionMaster.PermissionID=PermissionRole.PermissionID inner join MenuMaster on  PermissionMaster.PermissionID=MenuMaster.PermissionID\r\n" + 
			"where RoleName='"+role+"' and \r\n" + 
			"PermissionRole.TenantID='101' and PermissionMaster.ModuleName ='Accounting'  and PermissionMaster.LevelInfo>='1' ";
	
   String q2 ="select  distinct MenuMaster.ItemName , MenuMaster.DisplayName,PermissionMaster.DisplayPermissionName, PermissionMaster.ModuleName \r\n" + 
		"from PermissionRole  inner join UserRole on PermissionRole.RoleID=UserRole.RoleID  inner join PermissionMaster\r\n" + 
		"on PermissionMaster.PermissionID=PermissionRole.PermissionID inner join MenuMaster on  PermissionMaster.PermissionID=MenuMaster.PermissionID\r\n" + 
		"where RoleName='"+role+"' and \r\n" + 
		"PermissionRole.TenantID='101' and PermissionMaster.ModuleName ='LaborManagement'  and PermissionMaster.LevelInfo>='1' ";

  String q3="Select DisplayName,PermissionID,ItemName,MenuID from MenuMaster where MenuID<'10'";
//


String arr[]= {q1,q2,q3}; 
 setUp(arr, 4);
	
}

	public static void setUp(String [] querys, int numOfColumn)  {
	try{


	String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	Class.forName(dbClass).newInstance();

	Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

	stmt = con.createStatement();
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	
	// Executive Num of querys in array ,
	for(c=0;c<querys.length;c++) {
	
	//Try catch for result 	
	try{	

	ResultSet res = stmt.executeQuery(querys[c]);
 // for num of sheet 	 
	    int sheetNum=c+1;

	    i=1;
	while (res.next())
	{
		// for num of column 
			for ( j = 1; j <= numOfColumn; j++) {
				System.out.println(res.getString(j));
				String sheetName="Sheet"+sheetNum+"";
			ExcelUtill.getExcelObj().writeData("SQLSheet/dataBase.xlsx", sheetName, i, j, res.getString(j));
		}
		i++; 
		
	} 
	
	  }
	
	  catch(Exception e)
	  {
	   e.printStackTrace();
	  }
	}
	
	tearDown();
	}

	
	public static void tearDown()  {

	if (con != null) {
	try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	}
	
	
	
	
	
	
	
}
