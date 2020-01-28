package mainApp;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;

import java.io.Reader;
import java.sql.Connection;

public class DataAccess {
	private static void executeStoredProcedureWithParameters() {
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_InsertRole(?,?,?)}")) {
				
				cstmt.setString("Code","JAVA");
				cstmt.setString("Description","INSERTED BY JAVA and SP");
				cstmt.setString("RightId","1");
	
				cstmt.execute();
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	private static void executeStoredProcedure() {
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetRole}")) {
	
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					System.out.println(rs.getString("RoleId"));
					System.out.println(rs.getString("Code"));
					System.out.println(rs.getString("Description"));
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	 public static void main(String[] args) {
		 executeStoredProcedureWithParameters();
	 }
	
}
