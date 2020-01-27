package mainApp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bibliothequeId"
	,"nom"
	,"livre"
})
@XmlRootElement(name = "bibliotheque")
public class Bibliotheque {
	@XmlElement(required = true)
	public int bibliothequeId;
	
	@XmlElement(required = true)
	public String nom;
	
    @XmlElement(required = true)
    protected List<Bibliotheque.Livre> livre;
    /**
     * Gets the value of the livre property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the livre property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLivre().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bibliotheque.Livre }
     * 
     * 
     */
    public List<Bibliotheque.Livre> getLivre() {
        if (livre == null) {
            livre = new ArrayList<Bibliotheque.Livre>();
        }
        return this.livre;
    }
    
    
    
    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="titre" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="auteur">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="presentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="parution" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="colonne" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="rangee" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
    	"bookID",
        "titre",
        "auteur",
        "presentation",
        "parution",
        "colonne",
        "rangee",
        "url",
        "etat",
        "personne",
        "bibliothequeId"
    })
    public static class Livre {
    	@XmlElement(required = true)
    	public int bookID;// only for editing row
        @XmlElement(required = true)
        public String titre;
        @XmlElement(required = true)
        public Bibliotheque.Livre.Auteur auteur;
        @XmlElement(required = true)
        public String presentation;
        @XmlElement(required = true)
        public String parution;
        @XmlElement(required = true)
        public String colonne;
        @XmlElement(required = true)
        public String rangee;
        @XmlElement(required = true)
        public String url;
        @XmlElement(required = true)
        public String etat;
        @XmlElement(required = true)
        public String personne;
        @XmlElement(required = true)
        public int bibliothequeId;
        
        public Livre() {}
        
    	public Livre (int bookID, Auteur auteur, String titre, String presentation, String parution, String colonne, String rangee, String url, String etat, String personne) {
    		this.bookID = bookID;
    		this.auteur = auteur;
    		this.titre = titre;
    		this.presentation = presentation;
    		this.parution = parution;
    		this.colonne = colonne;
    		this.rangee = rangee;
    		this.url = url;
    		this.etat = etat;
    		this.personne = personne;
    	}
    	public Livre ( Auteur auteur, String titre, String presentation, String parution, String colonne, String rangee, String url, String etat, String personne) {
    		this.auteur = auteur;
    		this.titre = titre;
    		this.presentation = presentation;
    		this.parution = parution;
    		this.colonne = colonne;
    		this.rangee = rangee;
    		this.url = url;
    		this.etat = etat;
    		this.personne = personne;
    	}
    	
    	public String[] parseObject()
    	{
    		return new String [] {
    			this.titre,
    			this.auteur.nom + " " + this.auteur.prenom,
    			this.presentation,
    			this.parution,
    			this.colonne,
    			this.rangee,
    			String.valueOf(this.bookID),
    			this.url,
    			this.etat,
    			this.personne
    		};
    	}
        /**
         * <p>Classe Java pour anonymous complex type.
         * 
         * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "auteurId",
    		"nom",
            "prenom"
        })
        public static class Auteur {
        	
        	@XmlElement(required = true)
        	public int auteurId;
        	
            @XmlElement(required = true)
            public String nom;
            
            @XmlElement(required = true)
            public String prenom;
            
            public Auteur() {
            	
            }
            
        	public Auteur(String nom, String prenom) {
        		this.nom = nom;
        		this.prenom = prenom;
        	}

        }

    }

}
