package sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import excelUtill.ExcelUtill;
import webUtills.WebUtills;

public class FatchDataFromDataBase {

	
	static Connection con = null;

//	private static Statement stmt;
	//MULTIDB--MULTIDBUSER2
	public static String DB_URL = "jdbc:sqlserver://10.0.0.6:16845;database=MULTIDB ";

	public static String DB_USER = "MULTIDBUSER2";
	
	public static String DB_PASSWORD = "aUmrPV3!Z.33CqZ^";
    
static	int c,i,j;
	
static String q1,q2,q3,sql_string ;



public static void fatchData() {
	//String id="jyoti.rajpal6";
			
	  //{? = call my_procedure(?)};
	// q1="{call GetAutoRolePermission(?)}"; 
	
	String id=WebUtills.getObj().getText();
	
	 q1=" Select * from PermissionMaster pm inner join PermissionRole pr on pr.PermissionID=pm.PermissionID where \r\n" + 
	 		"pr.RoleID=(select distinct RoleID from userRole inner join UserDetails on \r\n" + 
	 		"UserDetails.Role=userRole.RoleID where UserDetails.username='"+id+"' \r\n" + 
	 		") and TenantID='101' and ModuleName='Accounting' and LevelInfo<=2 and pm.permissionid not in(10080,23,33,3410085)\r\n" + 
	 		""; 
	 
    q2 ="Select * from PermissionMaster pm inner join PermissionRole pr on pr.PermissionID=pm.PermissionID where \r\n" + 
    		"pr.RoleID=(select distinct RoleID from userRole inner join UserDetails on \r\n" + 
    		"UserDetails.Role=userRole.RoleID where UserDetails.username='"+id+"' \r\n" + 
    		") and TenantID='101' and ModuleName='LaborManagement' and LevelInfo<2 and pm.permissionid not in(10080,23,33,34)\r\n" + 
    		"";

   q3="Select PermissionID,DisplayName,ItemName from MenuMaster where MenuID<'10'";



String arr[]= {q1,q2,q3}; 
 setUp(arr, 3);
	
}


	public static void setUp(String [] querys, int numOfColumn)  {
	
		try{
			
	String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	Class.forName(dbClass).newInstance();

	 con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

	//stmt = con.createStatement();
       
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	
	for(c=0;c<querys.length;c++) {
	
	try{	
		
		PreparedStatement cs=con.prepareStatement(querys[c]);
		
	ResultSet res = cs.executeQuery();
	
 // for num of sheet 
	
	    int sheetNum=c+1;

	    i=1;
	while (res.next())
	{
		
	//================for no. of column ========================== //
			for ( j = 2; j <= numOfColumn; j++) {
				
	//			System.out.println(res.getString(j));
				String sheetName="Sheet"+sheetNum+"";
  //==================write data in excel sheet ====================//				
			ExcelUtill.getExcelObj().writeData("SQLSheet/DataBase.xlsx",sheetName, i, j-1, res.getString(j));
		
			}
				i++; 
		
	} 
	
	  }catch(Exception e) {
		  
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
