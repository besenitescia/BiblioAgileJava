package mainApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ValidateUser extends JFrame {

	private JPanel contentPane;
	private JTable tableDisabled;
	private JTable tableEnabled;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ValidateUser frame = new ValidateUser();
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
	public ValidateUser() {
		setTitle("Gestions des utilisateurs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1170, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableDisabled = new JTable();
		tableDisabled.setBounds(56, 85, 402, 272);
		contentPane.add(tableDisabled);
		
		tableEnabled = new JTable();
		tableEnabled.setBounds(673, 85, 402, 272);
		contentPane.add(tableEnabled);
		
		JButton btnActiver = new JButton("Activer");
		btnActiver.setBounds(506, 170, 115, 29);
		contentPane.add(btnActiver);
		
		JButton btnDesactiver = new JButton("Desactiver");
		btnDesactiver.setBounds(506, 240, 115, 29);
		contentPane.add(btnDesactiver);
		
		JLabel lblUtilisateursInactif = new JLabel("Utilisateurs inactifs :");
		lblUtilisateursInactif.setBounds(56, 49, 171, 20);
		contentPane.add(lblUtilisateursInactif);
		
		JLabel lblUtilisateursActifs = new JLabel("Utilisateurs actifs :");
		lblUtilisateursActifs.setBounds(673, 49, 171, 20);
		contentPane.add(lblUtilisateursActifs);
	}
}
