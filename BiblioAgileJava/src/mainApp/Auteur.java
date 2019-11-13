package mainApp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name ="auteur", propOrder= {
		"nom",
		"prenom"
})
public class Auteur {
	@XmlElement(required = true)
	public String nom;
	
	@XmlElement(required = true)
	public String prenom;
	
	public Auteur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
}
