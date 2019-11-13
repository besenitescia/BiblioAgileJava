package mainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlUtils {
	
    public Bibliotheque testXmlToObject(String filepath) throws JAXBException, FileNotFoundException {
    	Bibliotheque bibliotheque = new Bibliotheque();
    	
        JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //unmarshaller.setSchema();
        //unmarshaller.setValidating(true);
        //unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        bibliotheque = (Bibliotheque)unmarshaller.unmarshal(new File(filepath));

        //marshaller.marshal(bibliotheque, System.out);
        
        return bibliotheque;
    }
    
    public void testObjectToXml(Object[][] toutleslivres) throws JAXBException, FileNotFoundException {

        List<Bibliotheque.Livre> livres = new ArrayList<Bibliotheque.Livre>();
        for(Object[] livre : toutleslivres)
        {
        	Bibliotheque.Livre.Auteur auteurr= new Bibliotheque.Livre.Auteur();
        	auteurr.nom = livre[1].toString().split(" ")[0].toString();
        	auteurr.prenom = livre[1].toString().split(" ")[1].toString();
        	
        	Bibliotheque.Livre livrre = new Bibliotheque.Livre();
        	livrre.auteur = auteurr;
        	livrre.titre = livre[0].toString();
        	livrre.presentation = livre[2].toString();
        	livrre.parution = livre[3].toString();
        	livrre.colonne = livre[4].toString();
        	livrre.rangee = livre[5].toString();
        	//bookid = livre[6]
        	livrre.url = livre[7].toString();
        	livrre.etat = livre[8].toString();
        	livrre.personne = livre[9].toString();
        	livres.add(livrre);
        }
        Bibliotheque b = new Bibliotheque();
        b.livre = livres;
        JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);

        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        marshaller.marshal(b, new File("biblio.xml"));

        marshaller.marshal(b, System.out);

    }
    
    public void testObjectToXml(Object[][] toutleslivres, File file) throws JAXBException, FileNotFoundException {

        List<Bibliotheque.Livre> livres = new ArrayList<Bibliotheque.Livre>();
        
        for(Object[] livre : toutleslivres)
        {
        	Bibliotheque.Livre.Auteur auteurr= new Bibliotheque.Livre.Auteur();
        	auteurr.nom = livre[1].toString().split(" ")[0].toString();
        	auteurr.prenom = livre[1].toString().split(" ")[1].toString();
        	
        	Bibliotheque.Livre livrre = new Bibliotheque.Livre();
        	livrre.auteur = auteurr;
        	livrre.titre = livre[0].toString();
        	livrre.presentation = livre[2].toString();
        	livrre.parution = livre[3].toString();
        	livrre.colonne = livre[4].toString();
        	livrre.rangee = livre[5].toString();
        	
        	livrre.url = livre[7].toString();
        	livrre.etat = livre[8].toString();
        	livrre.personne = livre[9].toString();
        	livres.add(livrre);
        }
        Bibliotheque b = new Bibliotheque();
        b.livre = livres;
        
        JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);

        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        marshaller.marshal(b, new File(file.getAbsolutePath()));

        marshaller.marshal(b, System.out);

    }
}
