package sql;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StoredProcData {

	
	static Connection con = null;

	//private static Statement stmt;
	//MULTIDB--MULTIDBUSER2
	
	public static String DB_URL = "jdbc:sqlserver://10.0.0.6:16845;database=MULTIDB ";

	public static String DB_USER = "MULTIDBUSER2";
	
	public static String DB_PASSWORD = "aUmrPV3!Z.33CqZ^";
	
	static	int c,i,j;
	
	static String SQL ;
	
	
	public static void m()throws Throwable {
	
		CallableStatement cstmt = null;
		try {
			
			String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(dbClass).newInstance();

			 con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		//	stmt = con.createStatement();
		  
		//   String SQL = "{call dbo.GetAutoRolePermission()}";
		  
			 
			 cstmt = con.prepareCall (SQL);
		   
		   cstmt.execute();
		   
		  System.out.println(cstmt.getFetchSize()+""+cstmt.getString(0));
		}
		catch (SQLException e) {
			 System.out.println(e);
			
		}
		
	}
	public static void main() throws Throwable { 
		Connection con = null; 
		try { 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			String url = "jdbc:sqlserver://10.0.0.6:16845;database=MULTIDB "; 
			
			con = DriverManager.getConnection(url, DB_USER, DB_PASSWORD); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
	//	String SQL = "select * from table "; 
		CallableStatement cs = con.prepareCall(SQL); 
		
		ResultSet rs = cs.executeQuery();
		
		while(rs.next()){
			
			System.out.println(rs.getString(1));
			
		} rs.close();
		}

	 public static void
	    findDisappearedNumbers()
	    {
		 int[] a = { 1, 2, 4, 5, 6 };
	        int n=a.length;
	        int sum=((n+1)*(n+2))/2;
	        for(int i=0;i<n;i++)
	          sum-=a[i];
	      
	        System.out.println(sum);
	    }
	    public static void main(String[] args)
	    {
	        findDisappearedNumbers();
	      
	    }
	


}
