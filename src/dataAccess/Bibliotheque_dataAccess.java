package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;

import mainApp.Bibliotheque;

public class Bibliotheque_dataAccess {
	public static ArrayList<Bibliotheque> getBibliotheques() {
		ArrayList<Bibliotheque> bibliotheques = new ArrayList<Bibliotheque>();
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetBibliotheques}")) {
	
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					Bibliotheque bibliotheque = new Bibliotheque();
					bibliotheque.bibliothequeId = Integer.parseInt(rs.getString("BibliothequeId"));
					bibliotheque.nom = rs.getString("Nom");
					bibliotheques.add(bibliotheque);
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		return bibliotheques;
	}
}
