package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;

import mainApp.Bibliotheque;

public class Mylogger_dataAccess {
	//only inserts
	public static void insertLog(String type, int level, String message, Date date)
	{
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_InsertLog(?,?,?,?)}")) {
				
				cstmt.setString("Type",type);
				cstmt.setString("Message",message);
				cstmt.setString("Level",String.valueOf(level));
				cstmt.setString("Date",String.valueOf(date));
	
				cstmt.execute();
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
