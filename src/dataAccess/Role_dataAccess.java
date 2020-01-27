package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;

import mainApp.Credential;

public class Role_dataAccess {
	public static ArrayList<Credential.User.Role> getRoles() {
		ArrayList<Credential.User.Role> roles = new ArrayList<Credential.User.Role>();
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetRoles}")) {
	
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					Credential.User.Role role = new Credential.User.Role();
					role.roleID = Integer.parseInt(rs.getString("RoleId"));
					role.code = rs.getString("Code");
					role.description = rs.getString("Description");
					Credential.User.Role.Right right = new Credential.User.Role.Right();
					right.rightId = Integer.parseInt(rs.getString("RightId"));
					right.create = Boolean.parseBoolean(rs.getString("Create"));
					right.read = Boolean.parseBoolean(rs.getString("Read"));
					right.delete = Boolean.parseBoolean(rs.getString("Delete"));
					right.edit = Boolean.parseBoolean(rs.getString("Edit"));
					right.export = Boolean.parseBoolean(rs.getString("Export"));
					right.save = Boolean.parseBoolean(rs.getString("Save"));
					roles.add(role);
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		return roles;
	}
}
