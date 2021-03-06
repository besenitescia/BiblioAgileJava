package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.JAXBException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

import dataAccess.Auteur_dataAccess;
import dataAccess.Livre_dataAccess;
import dataAccess.User_dataAccess;
import mainApp.Bibliotheque.Livre;



public class App extends JFrame {
	static Mylogger log = new Mylogger("C:\\Users\\Burak\\Desktop\\java\\");
	private ArrayList<Bibliotheque.Livre> livresxml;
	public boolean xmlOpened = false;
	private JPanel contentPane;
	private JTable biblio;
	private JTextField txtTitre;
	private JTextField txtNom;
	private JTextField txtRangee;
	private JTextField txtParution;
	private JMenu mnFichier;
	private JMenuItem mntmOuvrir;
	private JMenuItem mntmFermer;
	private JMenuItem mntmQuitter;
	private JMenu mnEdition;
	private JMenuItem mntmSave;
	private JMenuItem mntmSaveAs;
	private JMenuBar menuBar;
	private JLabel lblAuteur;
	private JLabel lblRange;
	private JLabel lblAnne;
	private JTextField txtColonne;
	private JTextField txtPrenom;
	private DefaultTableModel tmodel;
	private int editselectedrow;
	private String editbookid;
	private List<String> docxData;
	private List<List<String>> bookLendedByPerson;
	private JTextField txtPresentation;
	private JTextField txtUrl;
	private JTextField txtPersonne;
	private JComboBox cbEtat;
	private JTextField txtSearch;
	private JLabel lblSearch;
	private JMenuItem mntmOuvrirDatabase;
	private JMenuItem mntmSynchroniser;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					App frame = new App();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public App(Credential.User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 630);
		contentPane = new JPanel();
		this.setTitle("BiblioAgileJava");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setEnabled(user.role.right.create);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titre = txtTitre.getText();
				String presentation = txtPresentation.getText();
				String parution = txtParution.getText();
				String colonne = txtColonne.getText();
				String rangee = txtRangee.getText();
				String nomAuteur = txtNom.getText();
				String prenomAuteur = txtPrenom.getText();
				String url = txtUrl.getText();
				String etat = cbEtat.getModel().getSelectedItem().toString();
				String personne = txtPersonne.getText();
				int bookID = tmodel.getRowCount() + 1;
				Bibliotheque.Livre.Auteur auteur = new Bibliotheque.Livre.Auteur(nomAuteur,prenomAuteur);
				Bibliotheque.Livre livre = new Bibliotheque.Livre();
				livre.livreId = bookID;
				livre.auteur = auteur;
				livre.titre = titre;
				livre.presentation = presentation;
				livre.parution = parution;
				livre.colonne = colonne;
				livre.rangee = rangee;
				livre.url = url;
				livre.etat = etat;
				livre.personne = personne;
				tmodel.addRow(livre.parseObject());
				txtTitre.setText("");
				txtPresentation.setText("");
				txtParution.setText("");
				txtColonne.setText("");
				txtRangee.setText("");
				txtNom.setText("");
				txtPrenom.setText("");
				txtUrl.setText("");
				cbEtat.setSelectedIndex(0);
				txtPersonne.setText("");
				int auteurid = Auteur_dataAccess.insertAuteur(nomAuteur, prenomAuteur);
				Livre_dataAccess.insertLivre(titre, presentation, Integer.parseInt(parution), Integer.parseInt(colonne), Integer.parseInt(rangee), url, etat, personne, 1, auteurid);
			}
		});
		btnNewButton.setBounds(15, 218, 99, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setEnabled(user.role.right.delete);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = biblio.getSelectedRow();
				String id = tmodel.getValueAt(selectedrow, 6).toString();
				int lid = Integer.parseInt(id);
				tmodel.removeRow(biblio.getSelectedRow());
				Livre_dataAccess.deleteLivre(lid);
			}
		});
		btnNewButton_1.setBounds(132, 45, 114, 23);
		contentPane.add(btnNewButton_1);
		tmodel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Titre", "Auteur", "Presentation", "Parution", "Colonne", "Rang\u00E9e", "BookID", "Url", "Etat", "Personne"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Object.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		biblio = new JTable(tmodel) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
		        Component returnComp = super.prepareRenderer(renderer, row, column);
		        Color alternateColor = new Color(252,242,206);
		        Color whiteColor = Color.WHITE;
		        if (!returnComp.getBackground().equals(getSelectionBackground())){
		            Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
		            returnComp.setBackground(bg);
		            bg = null;
		        }
		        return returnComp;
			
			}
		};
		//biblio = new JTable(tmodel);
		biblio.setBorder(new LineBorder(new Color(0, 0, 0)));
		biblio.setBounds(15, 77, 682, 125);
		TableColumnModel tcm = biblio.getColumnModel();
		tcm.removeColumn(tcm.getColumn(6));
		contentPane.add(biblio,BorderLayout.CENTER);
		contentPane.add(biblio.getTableHeader(),BorderLayout.NORTH);
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(null, "Livre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(15, 245, 337, 313);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Titre :");
		lblNewLabel.setBounds(15, 26, 82, 18);
		panel.add(lblNewLabel);
		
		txtTitre = new JTextField();
		txtTitre.setEnabled(user.role.right.edit);
		txtTitre.setBounds(126, 26, 184, 18);
		panel.add(txtTitre);
		txtTitre.setColumns(10);
		
		lblAnne = new JLabel("Parution :");
		lblAnne.setBounds(15, 100, 82, 14);
		panel.add(lblAnne);
		
		JLabel lblNewLabel_1 = new JLabel("Colonne :");
		lblNewLabel_1.setBounds(15, 134, 82, 14);
		panel.add(lblNewLabel_1);
		
		lblRange = new JLabel("Rang\u00E9e :");
		lblRange.setBounds(15, 170, 82, 14);
		panel.add(lblRange);
		
		txtParution = new JTextField();
		txtParution.setEnabled(user.role.right.edit);
		txtParution.setBounds(126, 97, 184, 20);
		panel.add(txtParution);
		txtParution.setColumns(10);
		
		txtColonne = new JTextField();
		txtColonne.setEnabled(user.role.right.edit);
		txtColonne.setBounds(126, 131, 184, 20);
		panel.add(txtColonne);
		txtColonne.setColumns(10);
		
		txtRangee = new JTextField();
		txtRangee.setEnabled(user.role.right.edit);
		txtRangee.setBounds(126, 167, 184, 20);
		panel.add(txtRangee);
		txtRangee.setColumns(10);
		
		JLabel lblPresentation = new JLabel("Presentation :");
		lblPresentation.setBounds(16, 64, 113, 14);
		panel.add(lblPresentation);
		
		txtPresentation = new JTextField();
		txtPresentation.setEnabled(user.role.right.edit);
		txtPresentation.setBounds(126, 61, 184, 20);
		panel.add(txtPresentation);
		txtPresentation.setColumns(10);
		
		JLabel lblEtat = new JLabel("Etat :");
		lblEtat.setBounds(15, 243, 48, 14);
		panel.add(lblEtat);
		
		txtUrl = new JTextField();
		txtUrl.setEnabled(user.role.right.edit);
		txtUrl.setBounds(126, 203, 184, 20);
		panel.add(txtUrl);
		txtUrl.setColumns(10);
		
		txtPersonne = new JTextField();
		txtPersonne.setEnabled(user.role.right.edit);
		txtPersonne.setBounds(126, 277, 184, 20);
		panel.add(txtPersonne);
		txtPersonne.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Url :");
		lblNewLabel_2.setBounds(15, 206, 48, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("A qui :");
		lblNewLabel_3.setBounds(15, 280, 54, 14);
		panel.add(lblNewLabel_3);
		
		cbEtat = new JComboBox();
		
		cbEtat.setEnabled(user.role.right.edit);
		cbEtat.setModel(new DefaultComboBoxModel(new String[] {"Pr\u00EAt\u00E9", "Emprunt\u00E9", "Acquis"}));
		cbEtat.setBounds(126, 239, 184, 22);
		panel.add(cbEtat);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1279, 22);
		contentPane.add(menuBar);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		ArrayList<Bibliotheque.Livre> meslivres = new ArrayList<Bibliotheque.Livre>();
		mntmOuvrir = new JMenuItem("Ouvrir");
		mntmOuvrir.setEnabled(user.role.right.read);
		mntmOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				//file.setFileFilter();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XML FILES", "xml", "xsd");
				file.setFileFilter(filter);
				int result = file.showOpenDialog(contentPane);
				if(result == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = file.getSelectedFile();
					XmlUtils xml = new XmlUtils();
					List<Bibliotheque.Livre> livres = null;
					try {
						livres = xml.testXmlToObject(selectedFile.getAbsolutePath()).livre;
					} catch (FileNotFoundException | JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					livresxml = (ArrayList<Livre>) livres;
					if(livres != null)
					if(livres.size() > 0)
					for(Bibliotheque.Livre livre : livres)
					{
						meslivres.add(livre);
						tmodel.addRow(livre.parseObject());
					}
					xmlOpened = true;
				}
			}
		});
		mnFichier.add(mntmOuvrir);
		
		mntmFermer = new JMenuItem("Fermer");
		mntmFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count =tmodel.getRowCount();
				for(int i = 0; i < count; i++)
				{
					tmodel.removeRow(0);
				}
				log.write(0, "file closed by " + user.role.description + " : " + user.login);
			}
		});
		
		JMenuItem mntmExport = new JMenuItem("Export");
		mntmExport.setEnabled(user.role.right.export);
		mntmExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Export exportdoc = new Export(meslivres);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				XWPFDocument document = new XWPFDocument();
//				//header
//		        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
//		        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
//				
//					//write header content
//				CTP ctpHeader = CTP.Factory.newInstance();
//		        CTR ctrHeader = ctpHeader.addNewR();
//		        CTText ctHeader = ctrHeader.addNewT();
//		        Date date = new Date();
//		        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//				String headerText = fmt.format(date) 
//					+ "                                                               "
//					+ "                                                               "
//					+ " Bibliotheque";
//				ctHeader.setStringValue(headerText);	
//				XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
//		        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
//		        parsHeader[0] = headerParagraph;
//		        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
//				// first page
//				XWPFParagraph title = document.createParagraph();
//				title.setVerticalAlignment(TextAlignment.CENTER);
//				title.setAlignment(ParagraphAlignment.CENTER);
//				title.setPageBreak(true);
//				XWPFRun titleRun = title.createRun();
//				titleRun.setText("Bibliotheque");
//				titleRun.setBold(true);
//				titleRun.setFontFamily("Courier");
//				titleRun.setFontSize(20);
//				// summary
//				XWPFParagraph title2 = document.createParagraph();
//				XWPFRun titleRun2 = title2.createRun();
//				titleRun2.setText("Sommaire");
//				titleRun2.setBold(true);
//				titleRun2.setFontFamily("Courier");
//				titleRun2.setFontSize(20);
//					//title auteurs
//				XWPFParagraph title3 = document.createParagraph();
//				title3.setBorderBottom(Borders.BASIC_BLACK_DASHES);
//				XWPFRun titleRun3 = title3.createRun();
//				titleRun3.setText("Auteurs");
//				titleRun3.setFontFamily("Courier");
//				titleRun3.setFontSize(20);
//						//auteurs
//				XWPFParagraph pAuthors = document.createParagraph();
//				XWPFRun runAuthors = pAuthors.createRun();
//				String authors = "";
//				docxData = getAuthors();
//				for(String author:docxData)
//				{
//					authors += author + "\n ";
//				}
//				
//				runAuthors.setText(authors);
//				runAuthors.setFontFamily("Courier");
//				runAuthors.setFontSize(11);
//					// title booklended
//				XWPFParagraph title4 = document.createParagraph();
//				title4.setBorderBottom(Borders.BASIC_BLACK_DASHES);
//				XWPFRun titleRun4 = title4.createRun();
//				titleRun4.setText("Livres pr�t�s");
//				titleRun4.setFontFamily("Courier");
//				titleRun4.setFontSize(20);
//						// bookLended data
//				
//				//create table
//				XWPFTable table = document.createTable();
//				//create first row
//				XWPFTableRow tableRowOne = table.getRow(0);
//				tableRowOne.getCell(0).setText("Titre du livre");
//				tableRowOne.addNewTableCell().setText("Personne");
//				bookLendedByPerson = getBooksLended();
//				for(int i = 1; i < bookLendedByPerson.size()+1; i++)
//				{
//					XWPFTableRow tableRow = table.createRow();
//					tableRow.getCell(0).setText(bookLendedByPerson.get(i - 1).get(0));
//					tableRow.getCell(1).setText(bookLendedByPerson.get(i - 1).get(1));
//				}
//				//page break
//				XWPFParagraph pagebreak = document.createParagraph();
//				pagebreak.setPageBreak(true);
//				
//				for(List<String>AuthorsInfo:getAuthorsBooks())
//				{
//					XWPFParagraph info = document.createParagraph();
//					XWPFRun inforun = info.createRun();
//					String aInfo = "";
//					for(String infos:AuthorsInfo) {
//						aInfo += infos + "\n";
//					}
//					inforun.setText(aInfo);
//					inforun.setBold(true);
//					inforun.setFontFamily("Courier");
//					inforun.setFontSize(11);
//					
//					XWPFParagraph imgPg = document.createParagraph();
//					XWPFRun imgrun = imgPg.createRun();
//					try {
//						String url = AuthorsInfo.get(4);
//						InputStream is = new URL(url).openStream();
//			    	    imgrun.addBreak();
//						imgrun.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, "", Units.toEMU(200), Units.toEMU(200));
//					} catch (InvalidFormatException | IOException  e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} // 200x200 pixels
//						    
//
//					
//					XWPFParagraph pagebreak2 = document.createParagraph();
//					pagebreak2.setPageBreak(true);
//					
//				}
//				
//				//fin du format & g�n�ration du docx
//				try {
//					FileOutputStream out = new FileOutputStream("C:\\Users\\Burak\\Desktop\\java\\Bibliotheque.docx");
//					
//					document.write(out);
//					out.close();
//					document.close();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
				log.write(0, "export done by " + user.role.description + " : " + user.login);
			}
		});
		
		JMenuItem mntmDisconnect = new JMenuItem("Se d\u00E9connecter");
		mnFichier.add(mntmDisconnect);
		mntmDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection frame = new Connection();
				frame.setVisible(true);
				App.this.dispose();
			}
		});
		mntmOuvrirDatabase = new JMenuItem("Ouvrir database");
		mntmOuvrirDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Bibliotheque.Livre> livres = Livre_dataAccess.getLivres();
				if(livres != null)
				if(livres.size() > 0)
				for(Bibliotheque.Livre livre : livres)
				{
					meslivres.add(livre);
					String[] test = livre.parseObject();
					tmodel.addRow(livre.parseObject());
				}
			}
		});
		mnFichier.add(mntmOuvrirDatabase);

		mntmSynchroniser = new JMenuItem("Synchroniser");
		mntmSynchroniser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Bibliotheque.Livre> livresdb = new ArrayList<Bibliotheque.Livre>();
				ArrayList<Credential.User> usersdb = new ArrayList<Credential.User>();
				XmlUtils xml1 = new XmlUtils();
				List<Credential.User> users = new ArrayList<Credential.User>();
				if(!xmlOpened) {
					JFileChooser file = new JFileChooser();
					//file.setFileFilter();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("XML FILES", "xml", "xsd");
					file.setFileFilter(filter);
					int result = file.showOpenDialog(contentPane);
					if(result == JFileChooser.APPROVE_OPTION)
					{
						File selectedFile = file.getSelectedFile();
						XmlUtils xml = new XmlUtils();
						List<Bibliotheque.Livre> livres = null;
						
						try {
							livres = xml.testXmlToObject(selectedFile.getAbsolutePath()).livre;
							users = xml1.XmlToCredential().user;
						} catch (FileNotFoundException | JAXBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						livresdb = Livre_dataAccess.getLivres();
						//update or add books
						boolean addTodb = false;
						boolean addToxml = false;
						boolean elementDontExists = false;
						if(livresdb.size() > livres.size())
							addToxml = true;
						if(livresdb.size() < livres.size())
							addTodb = true;
						if(addToxml)
						{
							for(Bibliotheque.Livre bookdb : livresdb)
							{
								elementDontExists = true;
								for(Bibliotheque.Livre bookxml : livres)
								{
									if(bookxml.titre.equals(bookdb.titre))
									{
										bookxml.livreId = bookdb.livreId;
										bookxml.titre = bookdb.titre;
										bookxml.presentation = bookdb.presentation;
										bookxml.colonne = bookdb.colonne;
										bookxml.parution = bookdb.parution;
										bookxml.rangee = bookdb.rangee;
										bookxml.url = bookdb.url;
										bookxml.personne = bookdb.personne;
										bookxml.bibliothequeId = bookdb.bibliothequeId;
										if(bookxml.auteur == null)
											bookxml.auteur = new Bibliotheque.Livre.Auteur();
										bookxml.auteur.auteurId = bookdb.auteur.auteurId;
										bookxml.auteur.nom = bookdb.auteur.nom;
										bookxml.auteur.prenom = bookdb.auteur.prenom;
										elementDontExists = false;
									}
									else
									{
										elementDontExists = true;
									}
								}
								if(elementDontExists)
								{
									livres.add(bookdb);
								}
							}
						}
						if(addTodb) {
							for(Bibliotheque.Livre bookxml : livres)
							{
								elementDontExists = true;
								for(Bibliotheque.Livre bookdb : livresdb)
								{
									if(bookxml.titre.equals(bookdb.titre))
									{
										bookdb.titre = bookxml.titre;
										bookdb.presentation = bookxml.presentation;
										bookdb.colonne = bookxml.colonne;
										bookdb.parution = bookxml.parution;
										bookdb.rangee = bookxml.rangee;
										bookdb.url = bookxml.url;
										bookdb.personne = bookxml.personne;
										bookdb.bibliothequeId = bookxml.bibliothequeId;
										if(bookdb.auteur == null)
											bookdb.auteur = new Bibliotheque.Livre.Auteur();
										bookdb.auteur.auteurId = bookxml.auteur.auteurId;
										bookdb.auteur.nom = bookxml.auteur.nom;
										bookdb.auteur.prenom = bookxml.auteur.prenom;
										Auteur_dataAccess.updateAuteur(bookdb.auteur.auteurId, bookdb.auteur.nom, bookdb.auteur.prenom);
										Livre_dataAccess.updateLivre(bookdb.livreId, bookdb.titre, bookdb.presentation,
												Integer.parseInt(bookdb.parution), Integer.parseInt(bookdb.colonne), Integer.parseInt(bookdb.rangee),
												bookdb.url, bookdb.etat, bookdb.personne, bookdb.bibliothequeId, bookxml.auteur.auteurId);
										elementDontExists = false;
									}
									else
									{
										elementDontExists = true;
									}
								}
								if(elementDontExists)
								{
									livresdb.add(bookxml);
									int auteurid = Auteur_dataAccess.insertAuteur(bookxml.auteur.nom, bookxml.auteur.prenom);
									Livre_dataAccess.insertLivre(bookxml.titre, bookxml.presentation, 
											Integer.parseInt(bookxml.parution), Integer.parseInt(bookxml.colonne), Integer.parseInt(bookxml.rangee),
											bookxml.url, bookxml.etat, bookxml.personne,
											bookxml.bibliothequeId, auteurid);
								}
							}
						}
						xmlOpened = true;
						//update or add users
						usersdb = User_dataAccess.getUsers();
						
						boolean adduserTodb = false;
						boolean adduserToxml = false;
						if(usersdb.size() > users.size())
							adduserToxml = true;
						if(usersdb.size() < users.size())
							adduserTodb = true;
						if(adduserToxml) {
							//start
							for(Credential.User userdb : usersdb)
							{
								elementDontExists = true;
								for(Credential.User userxml : users)
								{
									if(userdb.userID == userxml.userID)
									{
										userxml.login = userdb.login;
										userxml.mail = userdb.mail;
										userxml.credentialId = userdb.credentialId;
										userxml.password = userdb.password;
										userxml.role = userdb.role;
										userxml.userID = userdb.userID;
										userxml.disable = userdb.disable;
										elementDontExists = false;
									}
									else
									{
										elementDontExists = true;
									}
								}
								if(elementDontExists)
								{
									users.add(userdb);
								}
							}
							//end
						}
						if(adduserTodb) {
							//ici
							for(Credential.User userxml : users)
							{
								elementDontExists = true;
								for(Credential.User userdb : usersdb)
								{
									if(userdb.userID == userxml.userID)
									{
										userdb.login = userxml.login;
										userdb.mail = userxml.mail;
										userdb.credentialId = userxml.credentialId;
										userdb.password = userxml.password;
										userdb.role = userxml.role;
										userdb.userID = userxml.userID;
										userdb.disable = userxml.disable;
										User_dataAccess.updateUser(userdb.userID, userdb.login, userdb.password,
												userdb.mail, userdb.disable, userdb.role.roleID, userdb.credentialId);
										elementDontExists = false;
									}
									else
									{
										elementDontExists = true;
									}
								}
								if(elementDontExists)
								{
									usersdb.add(userxml);
									//sql
									User_dataAccess.insertUser(userxml.login, userxml.password, userxml.mail, userxml.role.roleID, userxml.credentialId);
								}
							}
							//
						}
					}
				}
				else {
					//livresxml
					
					try {
						users = xml1.XmlToCredential().user;
					} catch (FileNotFoundException | JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					livresdb = Livre_dataAccess.getLivres();
					//update or add books
					boolean addTodb = false;
					boolean addToxml = false;
					boolean elementDontExists = false;
					if(livresdb.size() > livresxml.size())
						addToxml = true;
					if(livresdb.size() < livresxml.size())
						addTodb = true;
					if(addToxml)
					{
						for(Bibliotheque.Livre bookdb : livresdb)
						{
							elementDontExists = true;
							for(Bibliotheque.Livre bookxml : livresxml)
							{
								if(bookxml.titre.equals(bookdb.titre))
								{
									bookxml.livreId = bookdb.livreId;
									bookxml.titre = bookdb.titre;
									bookxml.presentation = bookdb.presentation;
									bookxml.colonne = bookdb.colonne;
									bookxml.parution = bookdb.parution;
									bookxml.rangee = bookdb.rangee;
									bookxml.url = bookdb.url;
									bookxml.personne = bookdb.personne;
									bookxml.bibliothequeId = bookdb.bibliothequeId;
									if(bookxml.auteur == null)
										bookxml.auteur = new Bibliotheque.Livre.Auteur();
									bookxml.auteur.auteurId = bookdb.auteur.auteurId;
									bookxml.auteur.nom = bookdb.auteur.nom;
									bookxml.auteur.prenom = bookdb.auteur.prenom;
									elementDontExists = false;
								}
								else
								{
									elementDontExists = true;
								}
							}
							if(elementDontExists)
							{
								livresxml.add(bookdb);
							}
						}
					}
					if(addTodb) {
						for(Bibliotheque.Livre bookxml : livresxml)
						{
							elementDontExists = true;
							for(Bibliotheque.Livre bookdb : livresdb)
							{
								if(bookxml.titre.equals(bookdb.titre))
								{
									bookdb.titre = bookxml.titre;
									bookdb.presentation = bookxml.presentation;
									bookdb.colonne = bookxml.colonne;
									bookdb.parution = bookxml.parution;
									bookdb.rangee = bookxml.rangee;
									bookdb.url = bookxml.url;
									bookdb.personne = bookxml.personne;
									bookdb.bibliothequeId = bookxml.bibliothequeId;
									if(bookdb.auteur == null)
										bookdb.auteur = new Bibliotheque.Livre.Auteur();
									bookdb.auteur.auteurId = bookxml.auteur.auteurId;
									bookdb.auteur.nom = bookxml.auteur.nom;
									bookdb.auteur.prenom = bookxml.auteur.prenom;
									Auteur_dataAccess.updateAuteur(bookdb.auteur.auteurId, bookdb.auteur.nom, bookdb.auteur.prenom);
									Livre_dataAccess.updateLivre(bookdb.livreId, bookdb.titre, bookdb.presentation,
											Integer.parseInt(bookdb.parution), Integer.parseInt(bookdb.colonne), Integer.parseInt(bookdb.rangee),
											bookdb.url, bookdb.etat, bookdb.personne, bookdb.bibliothequeId, bookxml.auteur.auteurId);
									elementDontExists = false;
								}
							}
							if(elementDontExists)
							{
								livresdb.add(bookxml);
								int auteurid = Auteur_dataAccess.insertAuteur(bookxml.auteur.nom, bookxml.auteur.prenom);
								Livre_dataAccess.insertLivre(bookxml.titre, bookxml.presentation, 
										Integer.parseInt(bookxml.parution), Integer.parseInt(bookxml.colonne), Integer.parseInt(bookxml.rangee),
										bookxml.url, bookxml.etat, bookxml.personne,
										bookxml.bibliothequeId, auteurid);
							}
						}
					}
					xmlOpened = true;
					//update or add users
					usersdb = User_dataAccess.getUsers();
					
					boolean adduserTodb = false;
					boolean adduserToxml = false;
					if(usersdb.size() > users.size())
						adduserToxml = true;
					if(usersdb.size() < users.size())
						adduserTodb = true;
					if(adduserToxml) {
						//start
						for(Credential.User userdb : usersdb)
						{
							elementDontExists = false;
							for(Credential.User userxml : users)
							{
								if(userdb.userID == userxml.userID)
								{
									userxml.login = userdb.login;
									userxml.mail = userdb.mail;
									userxml.credentialId = userdb.credentialId;
									userxml.password = userdb.password;
									userxml.role = userdb.role;
									userxml.userID = userdb.userID;
									userxml.disable = userdb.disable;
								}
								else
								{
									elementDontExists = true;
								}
							}
							if(elementDontExists)
							{
								users.add(userdb);
							}
						}
						//end
					}
					if(adduserTodb) {
						//ici
						for(Credential.User userxml : users)
						{
							elementDontExists = false;
							for(Credential.User userdb : usersdb)
							{
								if(userdb.userID == userxml.userID)
								{
									userdb.login = userxml.login;
									userdb.mail = userxml.mail;
									userdb.credentialId = userxml.credentialId;
									userdb.password = userxml.password;
									userdb.role = userxml.role;
									userdb.userID = userxml.userID;
									userdb.disable = userxml.disable;
									User_dataAccess.updateUser(userdb.userID, userdb.login, userdb.password,
											userdb.mail, userdb.disable, userdb.role.roleID, userdb.credentialId);
								}
								else
								{
									elementDontExists = true;
								}
							}
							if(elementDontExists)
							{
								usersdb.add(userxml);
								//sql
								User_dataAccess.insertUser(userxml.login, userxml.password, userxml.mail, userxml.role.roleID, userxml.credentialId);
							}
						}
						//
					}
				}
			}
		});
		mnFichier.add(mntmSynchroniser);
		mnFichier.add(mntmExport);
		mnFichier.add(mntmFermer);
		
		mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFichier.add(mntmQuitter);
		
		mnEdition = new JMenu("Edition");
		menuBar.add(mnEdition);
		
		mntmSave = new JMenuItem("Save");
		mntmSave.setEnabled(user.role.right.save);
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XmlUtils xml = new XmlUtils();
				Object[][] allvalues = getTableData(biblio);
				try {
					xml.testObjectToXml(allvalues);
				} catch (FileNotFoundException | JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				log.write(0, "file saved by " + user.role.description + " : " + user.login);
			}
		});
		mnEdition.add(mntmSave);
		
		mntmSaveAs = new JMenuItem("Save as");
		mntmSaveAs.setEnabled(user.role.right.save);
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileC = new JFileChooser();
				if (fileC.showSaveDialog(contentPane) == JFileChooser.APPROVE_OPTION) {
					File file = fileC.getSelectedFile();
					XmlUtils xml = new XmlUtils();
					Object[][] allvalues = getTableData(biblio);
					try {
						xml.testObjectToXml(allvalues,file);
					} catch (FileNotFoundException | JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
				
			}
		});
		mnEdition.add(mntmSaveAs);
		
		JMenu mnUtilisateurs = new JMenu("Utilisateurs");
		menuBar.add(mnUtilisateurs);
		
		JMenuItem mntmGrer = new JMenuItem("G\u00E9rer");
		mntmGrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidateUser frame = new ValidateUser();
				frame.setVisible(true);
			}
		});
		mnUtilisateurs.add(mntmGrer);
		
		JMenu mnPropos = new JMenu("\u00C0 propos");
		menuBar.add(mnPropos);
		
		JMenuItem mntmInfos = new JMenuItem("Infos");
		mntmInfos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About frame = new About();
				frame.setVisible(true);
			}
		});
		mnPropos.add(mntmInfos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Auteur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(367, 245, 330, 313);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblAuteur = new JLabel("Nom :");
		lblAuteur.setBounds(10, 29, 56, 14);
		panel_1.add(lblAuteur);
		
		txtNom = new JTextField();
		txtNom.setEnabled(user.role.right.edit);
		txtNom.setBounds(91, 26, 229, 20);
		panel_1.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setBounds(10, 61, 66, 14);
		panel_1.add(lblPrenom);
		
		txtPrenom = new JTextField();
		txtPrenom.setEnabled(user.role.right.edit);
		txtPrenom.setBounds(91, 58, 229, 20);
		panel_1.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		JLabel lblImage = new JLabel("");
		lblImage.setBounds(10, 91, 305, 209);
		panel_1.add(lblImage);
		
		JButton btnNewButton_2 = new JButton("Editer");
		btnNewButton_2.setEnabled(user.role.right.edit);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editselectedrow = biblio.getSelectedRow();
				editbookid = tmodel.getValueAt(editselectedrow, 6).toString();
				txtTitre.setText(tmodel.getValueAt(editselectedrow, 0).toString());
				txtNom.setText(tmodel.getValueAt(editselectedrow, 1).toString().split(" ")[0]);
				txtPrenom.setText(tmodel.getValueAt(editselectedrow, 1).toString().split(" ")[1]);
				txtPresentation.setText(tmodel.getValueAt(editselectedrow, 2).toString());
				txtParution.setText(tmodel.getValueAt(editselectedrow, 3).toString());
				txtColonne.setText(tmodel.getValueAt(editselectedrow, 4).toString());
				txtRangee.setText(tmodel.getValueAt(editselectedrow, 5).toString());
				txtUrl.setText(tmodel.getValueAt(editselectedrow, 7).toString());
				
				
				//img = img.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
				Icon icon;
				try {
					icon = new ImageIcon(new URL(txtUrl.getText()));
					lblImage.setIcon(icon);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cbEtat.setSelectedItem(tmodel.getValueAt(editselectedrow, 8).toString());
				if(tmodel.getValueAt(editselectedrow, 9) != null)
					txtPersonne.setText(tmodel.getValueAt(editselectedrow, 9).toString());
			}
		});
		btnNewButton_2.setBounds(15, 45, 99, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Enregistrer");
		btnNewButton_3.setEnabled(user.role.right.edit);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titre = txtTitre.getText();
				String presentation = txtPresentation.getText();
				String parution = txtParution.getText();
				String colonne = txtColonne.getText();
				String rangee = txtRangee.getText();
				String nomAuteur = txtNom.getText();
				String prenomAuteur = txtPrenom.getText();
				String url = txtUrl.getText();
				String etat = cbEtat.getModel().getSelectedItem().toString();
				String personne = txtPersonne.getText();
				
				Bibliotheque.Livre livre = Livre_dataAccess.getLivre(titre, Integer.parseInt(parution));
				Auteur_dataAccess.updateAuteur(livre.auteur.auteurId, nomAuteur, prenomAuteur);
				Livre_dataAccess.updateLivre(livre.livreId, titre, presentation, Integer.parseInt(parution), Integer.parseInt(colonne), Integer.parseInt(rangee), url, etat, personne, livre.bibliothequeId, livre.auteur.auteurId);
				int myeditedrow = -1;
				int z = tmodel.getRowCount();
				for(int i = 0; i < z ; i++)
				{
					if(tmodel.getValueAt(i, 6).toString().contains(editbookid))
						myeditedrow = i;
				}
				tmodel.setValueAt(titre, myeditedrow, 0);
				tmodel.setValueAt(nomAuteur+ " " +prenomAuteur, myeditedrow, 1);
				tmodel.setValueAt(presentation, myeditedrow, 2);
				tmodel.setValueAt(parution, myeditedrow, 3);
				tmodel.setValueAt(colonne, myeditedrow, 4);
				tmodel.setValueAt(rangee, myeditedrow, 5);
				tmodel.setValueAt(url, myeditedrow, 7);
				tmodel.setValueAt(etat, myeditedrow, 8);
				tmodel.setValueAt(personne, myeditedrow, 9);
				txtTitre.setText("");
				txtPresentation.setText("");
				txtParution.setText("");
				txtColonne.setText("");
				txtRangee.setText("");
				txtNom.setText("");
				txtPrenom.setText("");
				txtUrl.setText("");
				cbEtat.setSelectedIndex(0);
				txtPersonne.setText("");
				lblImage.setIcon(null);
			}
		});
		btnNewButton_3.setBounds(132, 218, 114, 23);
		contentPane.add(btnNewButton_3);
		
		RowSorter<? extends TableModel> rs = biblio.getRowSorter();
        if (rs == null) {
        	biblio.setAutoCreateRowSorter(true);
            rs = biblio.getRowSorter();
        }

        TableRowSorter<? extends TableModel> rowSorter =
                (rs instanceof TableRowSorter) ? (TableRowSorter<? extends TableModel>) rs : null;

		txtSearch = new JTextField();
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				searchBook();
			}
			public void removeUpdate(DocumentEvent e) {
				searchBook();
			}
			public void insertUpdate(DocumentEvent e) {
				searchBook();
			}
			public void searchBook()
			{
				if(txtSearch.getText().length() > 2) {
					rowSorter.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
				}
				else {
					rowSorter.setRowFilter(null);
				}
			}
		});
		txtSearch.setBounds(514, 43, 183, 26);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		lblSearch = new JLabel("Rechercher :");
		lblSearch.setBounds(410, 46, 89, 20);
		contentPane.add(lblSearch);
		

	}
	
	public Object[][] getTableData (JTable table) {
	    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
	    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
	    Object[][] tableData = new Object[nRow][nCol];
	    for (int i = 0 ; i < nRow ; i++)
	        for (int j = 0 ; j < nCol ; j++)
	            tableData[i][j] = dtm.getValueAt(i,j);
	    return tableData;
	}
	
	public int getRowByValue(DefaultTableModel model, Object value) {
	    for (int i = model.getRowCount() - 1; i >= 0; --i) {
	        for (int j = model.getColumnCount() - 1; j >= 0; --j) {
	            if (model.getValueAt(i, j).equals(Integer.parseInt(value.toString()))) {
	                // what if value is not unique?
	                return i;
	            }
	        }
	    }
	    return -1;
	 }
	public List<String> getAuthors()
	{
		List<String> a = new ArrayList<String>();
		Object[][] allvalues = getTableData(biblio);
		for(Object[] onerow : allvalues)
		{
			a.add(onerow[1].toString());
		}
		return a;
	}
	public List<List<String>> getBooksLended()
	{
		List<List<String>> result = new ArrayList<List<String>>();
		Object[][] allvalues = getTableData(biblio);
		for(int i = 0; i < allvalues.length; i++)
		{
			result.add(new ArrayList<String>());
		}
		for(Object[] onerow : allvalues)
		{
			for(int i = 0; i < allvalues.length; i++) {
				if(onerow[9].toString() != "" && onerow[8].toString() == "Pr�t�")
				{
					result.get(i).add(onerow[0].toString());
					result.get(i).add(onerow[9].toString());
				}
			}
		}
		return result;
	}
	
	public List<List<String>> getAuthorsBooks()
	{
		List<List<String>> result= new ArrayList<List<String>>();
		Object[][] allvalues = getTableData(biblio);
		int a = allvalues.length;
		for(String author:getAuthors())
		{
			List<String> books = new ArrayList<String>();
			books.add(author);
			result.add(books);
		}
		for(Object[] onerow : allvalues)
		{
			for(int i = 0; i < result.size(); i++) {
				if(result.get(i).contains(onerow[1].toString()))
				{
					result.get(i).add(onerow[0].toString());
					result.get(i).add(onerow[2].toString());
					result.get(i).add(onerow[3].toString());
					result.get(i).add(onerow[7].toString());
					result.get(i).add(onerow[8].toString());
					result.get(i).add(onerow[9].toString());
				}
			}
		}
		return result;
	}
}
