package mainApp;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBException;

import dataAccess.User_dataAccess;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Connection extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtMdp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection frame = new Connection();
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
	public Connection() {
		setTitle("Connection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(88, 61, 278, 38);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JButton btnLogin = new JButton("Se connecter");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String login = txtLogin.getText().toLowerCase();
				char[] pwd = txtMdp.getPassword();
				String password = "";
				for(char c : pwd) {
					password += c;
				}
				XmlUtils xml = new XmlUtils();
				boolean isConnected = false;
				List<Credential.User> users = null;
				try {
					users = xml.XmlToCredential().user;
				} catch (FileNotFoundException | JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(users != null)
				if(users.size() > 0)
				for(Credential.User user : users)
				{
					ArrayList<Credential.User> usersfromdb = new ArrayList<Credential.User>();
					usersfromdb = User_dataAccess.getUserByLogin(login, password);
					if(usersfromdb.size() > 0) {
						if(!usersfromdb.get(0).disable) {
							isConnected = true;
							App frame = new App(usersfromdb.get(0));
							frame.setVisible(true);
							Connection.this.dispose();
						}
						else {
							JOptionPane.showMessageDialog(Connection.this,
								    "Votre compte n'a pas encore été activé.",
								    "Erreur",
								    JOptionPane.INFORMATION_MESSAGE);
						}
					}
					if(user.login.equals(login) && user.password.equals(password)) {
						if(!isConnected) {
							isConnected = true;
							App frame = new App(user);
							frame.setVisible(true);
							Connection.this.dispose();
						}
					}
				}
				if(!isConnected)
				{
					JOptionPane.showMessageDialog(Connection.this,
						    "Mot de passe ou login incorrect",
						    "Erreur",
						    JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(88, 233, 278, 38);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Identifiant");
		lblNewLabel.setBounds(184, 37, 83, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe");
		lblNewLabel_1.setBounds(182, 115, 106, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblForgot = new JLabel("<html><u style='color:blue'>Mot de passe oubli\u00E9 ?</u></html>");
		lblForgot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PasswordForgot frame = new PasswordForgot();
				frame.setVisible(true);
				Connection.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                Connection.this.setCursor(cursor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                Connection.this.setCursor(cursor);
			}
		});
		lblForgot.setBounds(159, 188, 169, 20);
		contentPane.add(lblForgot);
		
		JLabel lblRegister = new JLabel("<html><u style='color:blue'>Pas de compte ? S'inscrire</u></html>");
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Inscription frame = new Inscription();
				frame.setVisible(true);
				Connection.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                Connection.this.setCursor(cursor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                Connection.this.setCursor(cursor);
			}
		});
		lblRegister.setBounds(146, 300, 191, 20);
		contentPane.add(lblRegister);
		
		txtMdp = new JPasswordField();
		txtMdp.setBounds(88, 138, 278, 38);
		contentPane.add(txtMdp);
	}
}
