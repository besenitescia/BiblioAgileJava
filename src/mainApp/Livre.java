package mainApp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name ="livre", propOrder= {
		"titre",
		"auteur",
		"parution",
		"colonne",
		"rangee"
})
public class Livre {
	public int bookID;// only for editing row
	@XmlElement(required = true)
	public Auteur auteur;
	
	@XmlElement(required = true)
	public String titre;
	
	@XmlElement(required = true)
	public String parution;
	
	@XmlElement(required = true)
	public String colonne;
	
	@XmlElement(required = true)
	public String rangee;
	
	public Livre (int bookID, Auteur auteur, String titre, String parution, String colonne, String rangee) {
		this.bookID = bookID;
		this.auteur = auteur;
		this.titre = titre;
		this.parution = parution;
		this.colonne = colonne;
		this.rangee = rangee;
		
	}
	public Livre ( Auteur auteur, String titre, String parution, String colonne, String rangee) {
		this.auteur = auteur;
		this.titre = titre;
		this.parution = parution;
		this.colonne = colonne;
		this.rangee = rangee;
		
	}
	
	public String[] parseObject()
	{
		return new String [] {
			this.titre,
			this.auteur.nom + " " + this.auteur.prenom,
			this.parution,
			this.colonne,
			this.rangee,
			String.valueOf(this.bookID)
		};
	}
}
