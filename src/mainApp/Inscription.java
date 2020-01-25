package mainApp;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBException;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Inscription extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField txtMail;
	private JPasswordField txtMdp;
	private JLabel lblReturn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inscription frame = new Inscription();
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
	public Inscription() {
		setTitle("Inscription");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(213, 59, 209, 26);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		txtMail = new JTextField();
		txtMail.setBounds(213, 183, 209, 26);
		contentPane.add(txtMail);
		txtMail.setColumns(10);
		
		JButton btnRegister = new JButton("S'inscrire");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XmlUtils xml = new XmlUtils();
				try {
					Credential creds = xml.XmlToCredential();
					String login = txtLogin.getText().toLowerCase();
					String mail = txtMail.getText().toLowerCase();
					char[] pwd = txtMdp.getPassword();
					String password = "";
					for(char c : pwd) {
						password += c;
					}
					Credential.User.Role.Right right = new Credential.User.Role.Right(false, true, false, false, false, false);
					Credential.User.Role role = new Credential.User.Role("USER","User", right);
					Credential.User oneUser = new Credential.User(login, password, mail, false, role);
					creds.user.add(oneUser);
					xml.CredentialToXml(creds);
				} catch (FileNotFoundException | JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Connection frame = new Connection();
				frame.setVisible(true);
				Inscription.this.dispose();
			}
		});
		btnRegister.setBounds(191, 249, 115, 29);
		contentPane.add(btnRegister);
		
		JLabel lblMail = new JLabel("Mail :");
		lblMail.setBounds(65, 186, 69, 20);
		contentPane.add(lblMail);
		
		JLabel lblMdp = new JLabel("Mot de passe :");
		lblMdp.setBounds(65, 122, 109, 20);
		contentPane.add(lblMdp);
		
		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setBounds(65, 62, 69, 20);
		contentPane.add(lblLogin);
		
		txtMdp = new JPasswordField();
		txtMdp.setBounds(213, 119, 209, 26);
		contentPane.add(txtMdp);
		
		lblReturn = new JLabel("<html><u style='color:blue'>Retour</u></html>");
		lblReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection frame = new Connection();
				frame.setVisible(true);
				Inscription.this.dispose();
			}
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				Inscription.this.setCursor(cursor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
				Inscription.this.setCursor(cursor);
			}
		});
		lblReturn.setBounds(30, 16, 69, 20);
		contentPane.add(lblReturn);
	}
}
