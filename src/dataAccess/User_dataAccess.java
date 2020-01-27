package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;

import mainApp.Credential;

public class User_dataAccess {
	public static ArrayList<Credential.User> getUsers() {
		ArrayList<Credential.User> users = new ArrayList<Credential.User>();
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetUsers}")) {
	
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					Credential.User user = new Credential.User();
					user.login = rs.getString("Login");
					user.password = rs.getString("Password");
					user.mail = rs.getString("Mail");
					user.userID = Integer.parseInt(rs.getString("UserId"));
					user.disable = Boolean.parseBoolean(rs.getString("Disable"));
					user.credentialId = Integer.parseInt(rs.getString("CredentialId"));
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
					role.right = right;
					user.role = role;
					users.add(user);
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		return users;
	}

	public static ArrayList<Credential.User> getDisabledUsers(){
		ArrayList<Credential.User> users = new ArrayList<Credential.User>();
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetDisabledUsers}")) {
	
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					Credential.User user = new Credential.User();
					user.login = rs.getString("Login");
					user.password = rs.getString("Password");
					user.mail = rs.getString("Mail");
					user.userID = Integer.parseInt(rs.getString("UserId"));
					user.disable = Boolean.parseBoolean(rs.getString("Disable"));
					user.credentialId = Integer.parseInt(rs.getString("CredentialId"));
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
					role.right = right;
					user.role = role;
					users.add(user);
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		return users;
	
	}
	
	public static ArrayList<Credential.User> getEnabledUsers(){
		ArrayList<Credential.User> users = new ArrayList<Credential.User>();
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetEnabledUsers}")) {
	
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					Credential.User user = new Credential.User();
					user.login = rs.getString("Login");
					user.password = rs.getString("Password");
					user.mail = rs.getString("Mail");
					user.userID = Integer.parseInt(rs.getString("UserId"));
					user.disable = Boolean.parseBoolean(rs.getString("Disable"));
					user.credentialId = Integer.parseInt(rs.getString("CredentialId"));
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
					role.right = right;
					user.role = role;
					users.add(user);
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		return users;
	
	}
	
	public static ArrayList<Credential.User> getUserByLogin(String login, String password){
		ArrayList<Credential.User> users = new ArrayList<Credential.User>();
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetUserByLogin(?,?)}")) {
				
				cstmt.setString("login",login);
				cstmt.setString("password",password);
	
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					Credential.User user = new Credential.User();
					user.login = rs.getString("Login");
					user.password = rs.getString("Password");
					user.mail = rs.getString("Mail");
					user.userID = Integer.parseInt(rs.getString("UserId"));
					user.disable = Boolean.parseBoolean(rs.getString("Disable"));
					user.credentialId = Integer.parseInt(rs.getString("CredentialId"));
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
					role.right = right;
					user.role = role;
					users.add(user);
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		return users;
	}
	
	public static void insertUser(String login, String password, String mail, int roleid, int credentialid) {
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_InsertRole(?,?,?,?,?,?)}")) {
				
				cstmt.setString("login",login);
				cstmt.setString("password",password);
				cstmt.setString("mail",mail);
				cstmt.setString("roleid",String.valueOf(roleid));
				cstmt.setString("credentialid",String.valueOf(credentialid));
				cstmt.setString("disable","1");
	
				cstmt.execute();
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void updateUser(int userid, String login, String password, String mail, boolean disable, int roleid, int credentialid) {
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_InsertRole(?,?,?,?,?,?)}")) {
				
				cstmt.setString("userid",String.valueOf(userid));
				cstmt.setString("login",login);
				cstmt.setString("password",password);
				cstmt.setString("mail",mail);
				cstmt.setString("roleid",String.valueOf(roleid));
				cstmt.setString("credentialid",String.valueOf(credentialid));
				cstmt.setString("disable",String.valueOf(disable));
	
				cstmt.execute();
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
