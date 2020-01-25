package mainApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

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
		txtLogin.setBounds(124, 61, 213, 38);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JButton btnLogin = new JButton("Se connecter");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = txtLogin.getText();
				char[] pwd = txtMdp.getPassword();
				String password = pwd.toString();
				
				
			}
		});
		btnLogin.setBounds(124, 233, 213, 38);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Identifiant");
		lblNewLabel.setBounds(184, 37, 83, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe");
		lblNewLabel_1.setBounds(182, 115, 106, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblForgot = new JLabel("Mot de passe oubli\u00E9 ?");
		lblForgot.setBounds(159, 188, 169, 20);
		contentPane.add(lblForgot);
		
		JLabel lblRegister = new JLabel("Pas de compte ? S'inscrire");
		lblRegister.setBounds(146, 300, 191, 20);
		contentPane.add(lblRegister);
		
		txtMdp = new JPasswordField();
		txtMdp.setBounds(124, 138, 213, 38);
		contentPane.add(txtMdp);
	}
}
