package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;

import mainApp.Credential;

public class Right_dataAccess {
	public static ArrayList<Credential.User.Role.Right> getRights() {
		ArrayList<Credential.User.Role.Right> rights = new ArrayList<Credential.User.Role.Right>();
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetRights}")) {
	
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					Credential.User.Role.Right right = new Credential.User.Role.Right();
					right.create = Boolean.parseBoolean(rs.getString("Create"));
					right.read = Boolean.parseBoolean(rs.getString("Read"));
					right.delete = Boolean.parseBoolean(rs.getString("Delete"));
					right.edit = Boolean.parseBoolean(rs.getString("Edit"));
					right.export = Boolean.parseBoolean(rs.getString("Export"));
					right.save = Boolean.parseBoolean(rs.getString("Save"));
					rights.add(right);
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		return rights;
	}
}
