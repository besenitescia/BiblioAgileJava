package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;

import mainApp.Bibliotheque;

public class Auteur_dataAccess {
	public static ArrayList<Bibliotheque.Livre.Auteur> getAuteurs() {
		ArrayList<Bibliotheque.Livre.Auteur> auteurs = new ArrayList<Bibliotheque.Livre.Auteur>();
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetAuteurs}")) {
	
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					Bibliotheque.Livre.Auteur auteur = new Bibliotheque.Livre.Auteur();
					auteur.nom = rs.getString("Nom");
					auteur.prenom = rs.getString("Prenom");
					auteur.auteurId = Integer.parseInt(rs.getString("AuteurId"));
					auteurs.add(auteur);
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		return auteurs;
	}
	
	public static int insertAuteur(String nom, String prenom)
	{
		int auteurid = 0;
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_InsertOrUpdateAuteur(?,?,?)}")) {
				cstmt.setInt("AuteurId",0);
				cstmt.setString("Prenom",nom);
				cstmt.setString("Nom",prenom);
				
				cstmt.execute();
				
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetLastInsertedAuteur}")) {
				
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					auteurid = rs.getInt("AuteurId");
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		return auteurid;
	}
	
	public static void updateAuteur(int auteurId, String nom, String prenom)
	{
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_InsertOrUpdateAuteur(?,?,?)}")) {
				cstmt.setInt("AuteurId",auteurId);
				cstmt.setString("Prenom",nom);
				cstmt.setString("Nom",prenom);
	
				cstmt.execute();
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
