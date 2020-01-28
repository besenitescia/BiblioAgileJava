package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;

import mainApp.Bibliotheque;

public class Livre_dataAccess {
	public static ArrayList<Bibliotheque.Livre> getLivres() {
		ArrayList<Bibliotheque.Livre> livres = new ArrayList<Bibliotheque.Livre>();
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetLivres}")) {
	
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					Bibliotheque.Livre livre = new Bibliotheque.Livre();
					livre.livreId = Integer.parseInt(rs.getString("LivreId"));
					livre.colonne = rs.getString("Colonne");
					livre.rangee = rs.getString("Rangee");
					livre.parution = rs.getString("Parution");
					livre.etat = rs.getString("Etat");
					livre.personne = rs.getString("Responsable");
					livre.url = rs.getString("Url");
					livre.presentation = rs.getString("Presentation");
					livre.titre = rs.getString("Titre");
					Bibliotheque.Livre.Auteur auteur = new Bibliotheque.Livre.Auteur();
					auteur.auteurId = Integer.parseInt(rs.getString("AuteurId"));
					auteur.nom = rs.getString("Nom");
					auteur.prenom = rs.getString("Prenom");
					livre.auteur = auteur;
					livre.bibliothequeId = Integer.parseInt(rs.getString("BibliothequeId"));
					livres.add(livre);
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		return livres;
	}

	public static Bibliotheque.Livre getLivre(String titre, int parution) {
		Bibliotheque.Livre livre = new Bibliotheque.Livre();
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_GetLivreByTitre(?,?)}")) {
				cstmt.setString("titre",titre);
				cstmt.setInt("parution",parution);
				cstmt.execute();
				ResultSet rs = cstmt.getResultSet();
				while(rs.next()) {
					
					livre.livreId = Integer.parseInt(rs.getString("LivreId"));
					livre.colonne = rs.getString("Colonne");
					livre.rangee = rs.getString("Rangee");
					livre.parution = rs.getString("Parution");
					livre.etat = rs.getString("Etat");
					livre.personne = rs.getString("Responsable");
					livre.url = rs.getString("Url");
					livre.presentation = rs.getString("Presentation");
					livre.titre = rs.getString("Titre");
					Bibliotheque.Livre.Auteur auteur = new Bibliotheque.Livre.Auteur();
					auteur.auteurId = Integer.parseInt(rs.getString("AuteurId"));
					auteur.nom = rs.getString("Nom");
					auteur.prenom = rs.getString("Prenom");
					livre.auteur = auteur;
					livre.bibliothequeId = Integer.parseInt(rs.getString("BibliothequeId"));
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		return livre;
	}

	public static void deleteLivre(int livreid) {
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_DeleteLivre(?)}")) {
				
				cstmt.setString("LivreId",String.valueOf(livreid));
	
				cstmt.execute();
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void insertLivre(String titre, String presentation, int parution, int colonne, int rangee,
			String url, String etat, String responsable, int bibliothequeid, int auteurid) {
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_InsertOrUpdateLivre(?,?,?,?,?,?,?,?,?,?,?)}")) {
				cstmt.setString("LivreId", "0");
				cstmt.setString("Titre",titre);
				cstmt.setString("Presentation",presentation);
				cstmt.setString("Parution",String.valueOf(parution));
				cstmt.setString("Colonne",String.valueOf(colonne));
				cstmt.setString("Rangee",String.valueOf(rangee));
				cstmt.setString("Url",url);
				cstmt.setString("Etat",etat);
				cstmt.setString("Responsable",responsable);
				cstmt.setString("BibliothequeId",String.valueOf(bibliothequeid));
				cstmt.setInt("AuteurId",auteurid);
	
				cstmt.execute();
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void updateLivre(int livreid, String titre, String presentation, int parution, int colonne, int rangee,
			String url, String etat, String responsable, int bibliothequeid, int auteurid) {
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Baj;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
			try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
					.prepareCall("{call SP_InsertOrUpdateLivre(?,?,?,?,?,?,?,?,?,?,?)}")) {
				
				cstmt.setString("LivreId",String.valueOf(livreid));
				cstmt.setString("Titre",titre);
				cstmt.setString("Presentation",presentation);
				cstmt.setString("Parution",String.valueOf(parution));
				cstmt.setString("Colonne",String.valueOf(colonne));
				cstmt.setString("Rangee",String.valueOf(rangee));
				cstmt.setString("Url",url);
				cstmt.setString("Etat",etat);
				cstmt.setString("Responsable",responsable);
				cstmt.setString("BibliothequeId",String.valueOf(bibliothequeid));
				cstmt.setInt("AuteurId",auteurid);
	
				cstmt.execute();
			}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
