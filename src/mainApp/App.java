package mainApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
import javax.xml.bind.JAXBException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.IOUtils;
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

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class App extends JFrame {

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
	private JPanel panel_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1295, 400);
		contentPane = new JPanel();
		this.setTitle("BiblioAgileJava");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Ajouter");
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
				if(titre.length() == 0 || presentation.length() == 0 || parution.length() == 0 || colonne.length() == 0
						|| rangee.length() == 0 || nomAuteur.length() == 0 || prenomAuteur.length() == 0 
						|| cbEtat.getModel().getSelectedItem() != "Acquis" && personne.length() == 0)
				{
					JOptionPane.showMessageDialog(null,
		        	          "Erreur: Veuillez remplir tous les champs avec un asterisk" , "Erreur",
		        	          JOptionPane.ERROR_MESSAGE);
				}
				else{
					Bibliotheque.Livre.Auteur auteur = new Bibliotheque.Livre.Auteur(nomAuteur,prenomAuteur);
					Bibliotheque.Livre livre = new Bibliotheque.Livre();
					livre.bookID = bookID;
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
				}
			}
		});
		btnNewButton.setBounds(610, 43, 99, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tmodel.removeRow(biblio.getSelectedRow());
			}
		});
		btnNewButton_1.setBounds(474, 43, 99, 23);
		contentPane.add(btnNewButton_1);
		tmodel = new DefaultTableModel(
				new Object[][] {
					{"cand", "zola emile", "un livre", "1986", "4", "5", 0, "https://www.babelio.com/couv/CVT_Germinal_4755.jpeg", "Acquis", ""}
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
		            returnComp .setBackground(bg);
		            bg = null;
		        }
		        return returnComp;
			
			}
		};
		//biblio = new JTable(tmodel);
		biblio.setBorder(new LineBorder(new Color(0, 0, 0)));
		biblio.setBounds(27, 77, 546, 249);
		TableColumnModel tcm = biblio.getColumnModel();
		tcm.removeColumn(tcm.getColumn(6));
		contentPane.add(biblio,BorderLayout.CENTER);
		contentPane.add(biblio.getTableHeader(),BorderLayout.NORTH);
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(null, "Livre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(610, 71, 221, 249);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Titre* :");
		lblNewLabel.setBounds(6, 16, 82, 18);
		panel.add(lblNewLabel);
		
		txtTitre = new JTextField();
		txtTitre.setBounds(98, 16, 113, 18);
		panel.add(txtTitre);
		txtTitre.setColumns(10);
		
		lblAnne = new JLabel("Parution* :");
		lblAnne.setBounds(6, 75, 82, 14);
		panel.add(lblAnne);
		
		JLabel lblNewLabel_1 = new JLabel("Colonne* :");
		lblNewLabel_1.setBounds(6, 100, 82, 14);
		panel.add(lblNewLabel_1);
		
		lblRange = new JLabel("Rang\u00E9e* :");
		lblRange.setBounds(6, 131, 82, 14);
		panel.add(lblRange);
		
		txtParution = new JTextField();
		txtParution.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		        	if (!txtParution.getText().isEmpty()) {
		        		int currentyear = Calendar.getInstance().get(Calendar.YEAR);
						if(Integer.parseInt(txtParution.getText()) > currentyear)
				        {
				        	JOptionPane.showMessageDialog(null,
				        	          "Erreur: Entrez une année inférieur à "+currentyear , "Erreur",
				        	          JOptionPane.ERROR_MESSAGE);
				        	txtParution.setText("");
				        }
					}
		        }
		      });
		
		txtParution.setBounds(98, 72, 113, 20);
		panel.add(txtParution);
		txtParution.setColumns(10);
		
		txtColonne = new JTextField();
		txtColonne.setToolTipText("Entrez un nombre supérieur à 0 et inférieur à 7");
		txtColonne.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		        	if (!txtColonne.getText().isEmpty()) {
						if (Integer.parseInt(txtColonne.getText()) < 0 || Integer.parseInt(txtColonne.getText()) > 6) {
							JOptionPane.showMessageDialog(null,
									"Erreur: Entrez un nombre supérieur à 0 et inférieur à 7", "Erreur",
									JOptionPane.ERROR_MESSAGE);
							txtColonne.setText("");
						} 
					}
		        }
		      });
		
		txtColonne.setBounds(98, 97, 113, 20);
		panel.add(txtColonne);
		txtColonne.setColumns(10);
		
		txtRangee = new JTextField();
		txtRangee.setToolTipText("Entrez un nombre supérieur à 0 et inférieur à 7");
		
		txtRangee.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		        	if (!txtRangee.getText().isEmpty()) {
						if (Integer.parseInt(txtRangee.getText()) < 0 || Integer.parseInt(txtRangee.getText()) > 6) {
							JOptionPane.showMessageDialog(null,
									"Erreur: Entrez un nombre supérieur à 0 et inférieur à 7", "Erreur",
									JOptionPane.ERROR_MESSAGE);
							txtRangee.setText("");
						} 
					}
		        }
		      });
		    
		txtRangee.setBounds(98, 128, 113, 20);
		panel.add(txtRangee);
		txtRangee.setColumns(10);
		
		JLabel lblPresentation = new JLabel("Presentation* :");
		lblPresentation.setBounds(6, 45, 89, 14);
		panel.add(lblPresentation);
		
		txtPresentation = new JTextField();
		txtPresentation.setBounds(98, 45, 114, 20);
		panel.add(txtPresentation);
		txtPresentation.setColumns(10);
		
		JLabel lblEtat = new JLabel("Etat :");
		lblEtat.setBounds(6, 187, 48, 14);
		panel.add(lblEtat);
		
		txtUrl = new JTextField();
		txtUrl.setBounds(98, 159, 113, 20);
		panel.add(txtUrl);
		txtUrl.setColumns(10);
		
		txtPersonne = new JTextField();
		txtPersonne.setBounds(98, 212, 113, 20);
		panel.add(txtPersonne);
		txtPersonne.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Url :");
		lblNewLabel_2.setBounds(6, 162, 48, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("A qui :");
		lblNewLabel_3.setBounds(6, 215, 48, 14);
		panel.add(lblNewLabel_3);
		
		cbEtat = new JComboBox();
		cbEtat.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        if(cbEtat.getModel().getSelectedItem() == "Acquis")
		        {
		        	txtPersonne.setEnabled(false);
		        }
		        else
		        {
		        	txtPersonne.setEnabled(true);
		        }
		    }
		});
		cbEtat.setModel(new DefaultComboBoxModel(new String[] {"Pr\u00EAt\u00E9", "Emprunt\u00E9", "Acquis"}));
		cbEtat.setBounds(98, 183, 113, 22);
		panel.add(cbEtat);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1279, 22);
		contentPane.add(menuBar);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Couverture", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(1053, 71, 202, 249);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		mntmOuvrir = new JMenuItem("Ouvrir");
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
					if(!selectedFile.getName().endsWith("xml")
							|| !selectedFile.getName().endsWith("xsd"))
					{
						JOptionPane.showMessageDialog(null,
			        	          "Erreur: Seulement les fichiers xml ou xsd sont acceptés", "Erreur",
			        	          JOptionPane.ERROR_MESSAGE);
					}
					XmlUtils xml = new XmlUtils();
					List<Bibliotheque.Livre> livres = null;
					try {
						livres = xml.testXmlToObject(selectedFile.getAbsolutePath()).livre;
					} catch (FileNotFoundException | JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(livres != null)
					if(livres.size() > 0)
					for(Bibliotheque.Livre livre : livres)
					{
						tmodel.addRow(livre.parseObject());
					}
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
			}
		});
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
			}
		});
		mnEdition.add(mntmSave);
		
		mntmSaveAs = new JMenuItem("Save as");
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
		
		JMenu mnPropos = new JMenu("\u00C0 propos");
		menuBar.add(mnPropos);
		
		JMenuItem mntmInfos = new JMenuItem("Infos");
		mntmInfos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(App.this,
					    "Version : 1.0",
					    "Infos",
					    JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnPropos.add(mntmInfos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Auteur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(841, 71, 199, 249);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblAuteur = new JLabel("Nom* :");
		lblAuteur.setBounds(10, 16, 56, 14);
		panel_1.add(lblAuteur);
		
		txtNom = new JTextField();
		txtNom.setBounds(76, 13, 113, 20);
		panel_1.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom* :");
		lblPrenom.setBounds(10, 48, 66, 14);
		panel_1.add(lblPrenom);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(76, 44, 113, 20);
		panel_1.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		JLabel lblImage = new JLabel("");
		lblImage.setBounds(10, 24, 180, 220);
		panel_2.add(lblImage);
		
		JButton btnNewButton_2 = new JButton("Editer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editselectedrow = biblio.getSelectedRow();
				if(editselectedrow < 0)
				{
					JOptionPane.showMessageDialog(null,
		        	          "Erreur: selectionnez une ligne", "Erreur",
		        	          JOptionPane.ERROR_MESSAGE);
				}
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
				txtPersonne.setText(tmodel.getValueAt(editselectedrow, 9).toString());
			}
		});
		btnNewButton_2.setBounds(347, 43, 99, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Enregistrer");
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
				int myeditedrow = -1;
				if(myeditedrow == -1)
				{
					JOptionPane.showMessageDialog(null,
		        	          "Erreur: Vous n'avez pas choisi de ligne à éditer" , "Erreur",
		        	          JOptionPane.ERROR_MESSAGE);
				}else
				{
				if(titre.length() == 0 || presentation.length() == 0 || parution.length() == 0 || colonne.length() == 0
						|| rangee.length() == 0 || nomAuteur.length() == 0 || prenomAuteur.length() == 0)
				{
					JOptionPane.showMessageDialog(null,
		        	          "Erreur: Veuillez remplir tous les champs avec un asterisk" , "Erreur",
		        	          JOptionPane.ERROR_MESSAGE);
				}
				else {
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
				}
			}
		});
		btnNewButton_3.setBounds(724, 43, 107, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Export");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XWPFDocument document = new XWPFDocument();
				//header
		        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
				
					//write header content
				CTP ctpHeader = CTP.Factory.newInstance();
		        CTR ctrHeader = ctpHeader.addNewR();
		        CTText ctHeader = ctrHeader.addNewT();
		        Date date = new Date();
		        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String headerText = fmt.format(date) 
					+ "                                                               "
					+ "                                                               "
					+ " Bibliotheque";
				ctHeader.setStringValue(headerText);	
				XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
		        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
		        parsHeader[0] = headerParagraph;
		        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
				// first page
				XWPFParagraph title = document.createParagraph();
				title.setVerticalAlignment(TextAlignment.CENTER);
				title.setAlignment(ParagraphAlignment.CENTER);
				title.setPageBreak(true);
				XWPFRun titleRun = title.createRun();
				titleRun.setText("Bibliotheque");
				titleRun.setBold(true);
				titleRun.setFontFamily("Courier");
				titleRun.setFontSize(20);
				// summary
				XWPFParagraph title2 = document.createParagraph();
				XWPFRun titleRun2 = title2.createRun();
				titleRun2.setText("Sommaire");
				titleRun2.setBold(true);
				titleRun2.setFontFamily("Courier");
				titleRun2.setFontSize(20);
					//title auteurs
				XWPFParagraph title3 = document.createParagraph();
				title3.setBorderBottom(Borders.BASIC_BLACK_DASHES);
				XWPFRun titleRun3 = title3.createRun();
				titleRun3.setText("Auteurs");
				titleRun3.setFontFamily("Courier");
				titleRun3.setFontSize(20);
						//auteurs
				XWPFParagraph pAuthors = document.createParagraph();
				XWPFRun runAuthors = pAuthors.createRun();
				String authors = "";
				docxData = getAuthors();
				for(String author:docxData)
				{
					authors += author + "\n ";
				}
				
				runAuthors.setText(authors);
				runAuthors.setFontFamily("Courier");
				runAuthors.setFontSize(11);
					// title booklended
				XWPFParagraph title4 = document.createParagraph();
				title4.setBorderBottom(Borders.BASIC_BLACK_DASHES);
				XWPFRun titleRun4 = title4.createRun();
				titleRun4.setText("Livres prêtés");
				titleRun4.setFontFamily("Courier");
				titleRun4.setFontSize(20);
						// bookLended data
				
				//create table
				XWPFTable table = document.createTable();
				//create first row
				XWPFTableRow tableRowOne = table.getRow(0);
				tableRowOne.getCell(0).setText("Titre du livre");
				tableRowOne.addNewTableCell().setText("Personne");
				bookLendedByPerson = getBooksLended();
				for(int i = 1; i < bookLendedByPerson.size()+1; i++)
				{
					XWPFTableRow tableRow = table.createRow();
					tableRow.getCell(0).setText(bookLendedByPerson.get(i - 1).get(0));
					tableRow.getCell(1).setText(bookLendedByPerson.get(i - 1).get(1));
				}
				//page break
				XWPFParagraph pagebreak = document.createParagraph();
				pagebreak.setPageBreak(true);
				
				for(List<String>AuthorsInfo:getAuthorsBooks())
				{
					XWPFParagraph info = document.createParagraph();
					XWPFRun inforun = info.createRun();
					String aInfo = "";
					for(String infos:AuthorsInfo) {
						aInfo += infos + "\n";
					}
					inforun.setText(aInfo);
					inforun.setBold(true);
					inforun.setFontFamily("Courier");
					inforun.setFontSize(11);
					
					XWPFParagraph imgPg = document.createParagraph();
					XWPFRun imgrun = imgPg.createRun();
					try {
						String url = AuthorsInfo.get(4);
						InputStream is = new URL(url).openStream();
			    	    imgrun.addBreak();
						imgrun.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, "", Units.toEMU(200), Units.toEMU(200));
					} catch (InvalidFormatException | IOException  e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // 200x200 pixels
						    

					
					XWPFParagraph pagebreak2 = document.createParagraph();
					pagebreak2.setPageBreak(true);
					
				}
				
				//fin du format & génération du docx
				try {
					FileOutputStream out = new FileOutputStream("C:\\Users\\Melih\\Desktop\\Bibliotheque.docx");
					
					document.write(out);
					out.close();
					document.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(27, 43, 89, 23);
		contentPane.add(btnNewButton_4);
		

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
				if(onerow[9].toString() != "" && onerow[8].toString() == "Prêté")
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
