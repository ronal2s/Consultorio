package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.apple.dnssd.TXTRecord;

import logical.Consultorio;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JPasswordField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			Consultorio.getInstance().loadMe();
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
		setLocationRelativeTo(null);
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblCedula.setBounds(265, 152, 81, 16);
			contentPanel.add(lblCedula);
		}
		{
			txtCedula = new JTextField();
			txtCedula.setBounds(358, 149, 178, 28);
			contentPanel.add(txtCedula);
			txtCedula.setColumns(10);
		}
		{
			JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblContrasea.setBounds(265, 191, 81, 16);
			contentPanel.add(lblContrasea);
		}
		{
			JButton btnAcceder = new JButton("Acceder");
			btnAcceder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boolean acceso = Consultorio.getInstance().login(txtCedula.getText(), txtClave.getText());
					String tipo ="";
					if(acceso)
					{
						tipo = Consultorio.getInstance().getTipousuario(txtCedula.getText());
						//Principal principal = new Principal(tipo);
						Principal.main(null, tipo);
						
						//principal.setVisible(true);
						
						dispose();
					}
				}
			});
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
		
		txtClave = new JPasswordField();
		txtClave.setBounds(357, 193, 179, 28);
		contentPanel.add(txtClave);
	}
}
