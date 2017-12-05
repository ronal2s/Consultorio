package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import logical.Consultorio;
import logical.print;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JPasswordField txtClave;
	private JLabel label;
	
	
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
			//e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/icono.png")));
		setTitle("Iniciar sesi\u00F3n");
		setBounds(100, 100, 656, 401);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCedula.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblCedula.setBounds(271, 182, 81, 16);
			contentPanel.add(lblCedula);
		}
		{
			txtCedula = new JTextField();
			txtCedula.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					cargarFoto(txtCedula.getText()+".jpg");
				}
			});
			txtCedula.setBounds(364, 179, 178, 28);
			contentPanel.add(txtCedula);
			txtCedula.setColumns(10);
		}
		{
			JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
			lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblContrasea.setBounds(271, 235, 81, 16);
			contentPanel.add(lblContrasea);
		}
		{
			JButton btnAcceder = new JButton("Entrar");
			btnAcceder.setBackground(new Color(102, 205, 170));
			btnAcceder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boolean acceso = Consultorio.getInstance().login(txtCedula.getText(), txtClave.getText());
					String tipo ="";
					if(acceso)
					{
						tipo = Consultorio.getInstance().getTipousuario(txtCedula.getText());
						//Principal principal = new Principal(tipo);
						Principal.main(null, tipo);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Usuario y/o clave incorrectos");
					}
				}
			});
			btnAcceder.setBounds(364, 267, 81, 25);
			contentPanel.add(btnAcceder);
			btnAcceder.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAcceder);
		}
		{
			JButton btnSalir = new JButton("  Salir  ");
			btnSalir.setBackground(new Color(205, 92, 92));
			btnSalir.setBounds(447, 267, 95, 25);
			contentPanel.add(btnSalir);
			btnSalir.setActionCommand("Cancel");
		}
		{
			label = new JLabel("");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setIcon(new ImageIcon(Login.class.getResource("/img/if_user_285655.png")));
			label.setBounds(90, 147, 151, 150);
			contentPanel.add(label);
		}
		
		txtClave = new JPasswordField();
		txtClave.setBounds(363, 223, 179, 28);
		contentPanel.add(txtClave);
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(Login.class.getResource("/img/BannerConsultorio.png")));
		label_1.setBounds(15, 16, 604, 115);
		contentPanel.add(label_1);
	}
	
	public void cargarFoto(String ruta)
	{
		File file = new File(ruta);
		if(file.exists())
		{
			label.setIcon(redimensionarImagen(ruta));
		}
		else
		{
			label.setIcon(new ImageIcon(Registro.class.getResource("/img/if_user_285655.png")));

			
			//System.out.println("Este paciente no tiene foto");
		}
		
		
	}
	public ImageIcon redimensionarImagen(String ruta)
	{
		ImageIcon MyImage = new ImageIcon(ruta);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}
}
