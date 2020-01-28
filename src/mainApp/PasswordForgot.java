package mainApp;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBException;

import dataAccess.User_dataAccess;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PasswordForgot extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtMdp;
	private JPasswordField txtMdp2;
	private JTextField txtLogin;
	private JTextField txtMail;
	private JLabel lblReturn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordForgot frame = new PasswordForgot();
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
	public PasswordForgot() {
		setTitle("Mot de passe oubli\u00E9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setBounds(25, 69, 69, 20);
		contentPane.add(lblLogin);
		
		JLabel lblMail = new JLabel("Mail :");
		lblMail.setBounds(25, 118, 69, 20);
		contentPane.add(lblMail);
		
		JLabel lblNouveauMotDe = new JLabel("Nouveau mot de passe :");
		lblNouveauMotDe.setBounds(25, 171, 217, 20);
		contentPane.add(lblNouveauMotDe);
		
		JLabel lblConfirmerVotreMot = new JLabel("Confirmer votre mot de passe :");
		lblConfirmerVotreMot.setBounds(25, 231, 238, 20);
		contentPane.add(lblConfirmerVotreMot);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] pwd = txtMdp.getPassword();
				char[] pwd2 = txtMdp2.getPassword();
				
				if(!pwd.equals(pwd2))
				{
					JOptionPane.showMessageDialog(PasswordForgot.this,
						    "Les mots de passe ne correspondent pas.",
						    "Infos",
						    JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String password = "";
					for(char c : pwd) {
						password += c;
					}
					
					XmlUtils xml = new XmlUtils();
					try {
						Credential cred = xml.XmlToCredential();
						String login = txtLogin.getText().toLowerCase();
						String mail = txtMail.getText().toLowerCase();
						for(Credential.User user : cred.user)
						{
							ArrayList<Credential.User> myusers = new ArrayList<Credential.User>();
							myusers = User_dataAccess.getUserByLogin(login, password);
							if(myusers.size()>0) {
								User_dataAccess.updateUser(myusers.get(0).userID, login, password, mail, false, myusers.get(0).role.roleID, 1);
							}
							
							if(user.login.equals(login) && user.mail.equals(mail))
							{
								Credential.User.Role.Right right = new Credential.User.Role.Right(false, true, false, false, false, false);
								Credential.User.Role role = new Credential.User.Role("USER","User", right);
								Credential.User oneUser = new Credential.User(login, password, mail, false, role);
								cred.user.add(oneUser);
								xml.CredentialToXml(cred);

								Connection frame = new Connection();
								frame.setVisible(true);
								PasswordForgot.this.dispose();
							}
							else {
								JOptionPane.showMessageDialog(PasswordForgot.this,
									    "Login ou mail incorrect. Aucun utilisateur trouvé.",
									    "Infos",
									    JOptionPane.INFORMATION_MESSAGE);
							}
						}
					} catch (FileNotFoundException | JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnModifier.setBounds(165, 303, 115, 29);
		contentPane.add(btnModifier);
		
		txtMdp = new JPasswordField();
		txtMdp.setBounds(278, 168, 182, 26);
		contentPane.add(txtMdp);
		
		txtMdp2 = new JPasswordField();
		txtMdp2.setBounds(278, 228, 182, 26);
		contentPane.add(txtMdp2);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(278, 66, 182, 26);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		txtMail = new JTextField();
		txtMail.setBounds(278, 115, 182, 26);
		contentPane.add(txtMail);
		txtMail.setColumns(10);
		
		lblReturn = new JLabel("<html><u style='color:blue'>Retour</u></html>");
		lblReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection frame = new Connection();
				frame.setVisible(true);
				PasswordForgot.this.dispose();
			}
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				PasswordForgot.this.setCursor(cursor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
				PasswordForgot.this.setCursor(cursor);
			}
		});
		lblReturn.setBounds(25, 16, 130, 20);
		contentPane.add(lblReturn);
	}

}
