package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 656, 399);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblUsuario.setBounds(265, 152, 81, 16);
			contentPanel.add(lblUsuario);
		}
		{
			textField = new JTextField();
			textField.setBounds(358, 149, 178, 22);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblContrasea.setBounds(265, 191, 81, 16);
			contentPanel.add(lblContrasea);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(358, 188, 178, 22);
			contentPanel.add(textField_1);
		}
		{
			JButton btnAcceder = new JButton("Acceder");
			btnAcceder.setBounds(334, 251, 79, 25);
			contentPanel.add(btnAcceder);
			btnAcceder.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAcceder);
		}
		{
			JButton btnSalir = new JButton("  Salir  ");
			btnSalir.setBounds(439, 251, 79, 25);
			contentPanel.add(btnSalir);
			btnSalir.setActionCommand("Cancel");
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(Login.class.getResource("/img/if_user_285655.png")));
			label.setBounds(86, 131, 167, 128);
			contentPanel.add(label);
		}
		{
			JLabel lblSgh = new JLabel("SGH");
			lblSgh.setForeground(new Color(128, 128, 128));
			lblSgh.setFont(new Font("Tahoma", Font.BOLD, 80));
			lblSgh.setBounds(169, 13, 178, 105);
			contentPanel.add(lblSgh);
		}
		{
			JLabel lblSeattle = new JLabel("Seattle");
			lblSeattle.setForeground(new Color(128, 128, 128));
			lblSeattle.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblSeattle.setBounds(351, 40, 100, 22);
			contentPanel.add(lblSeattle);
		}
		{
			JLabel lblGrace = new JLabel("Grace");
			lblGrace.setForeground(new Color(128, 128, 128));
			lblGrace.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblGrace.setBounds(351, 55, 100, 30);
			contentPanel.add(lblGrace);
		}
		{
			JLabel lblHospital = new JLabel("Hospital");
			lblHospital.setForeground(new Color(128, 128, 128));
			lblHospital.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblHospital.setBounds(351, 77, 100, 22);
			contentPanel.add(lblHospital);
		}
	}

}