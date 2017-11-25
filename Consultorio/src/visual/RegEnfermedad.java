package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.ImageIcon;

public class RegEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEnfermedad dialog = new RegEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegEnfermedad() {
		setForeground(new Color(176, 224, 230));
		setTitle("Registrar Enfermedad");
		setBounds(100, 100, 645, 497);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setForeground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Nombre:");
			label.setBounds(12, 156, 56, 16);
			contentPanel.add(label);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(76, 153, 198, 22);
			contentPanel.add(txtNombre);
		}
		{
			JScrollPane scrollPaneListadoEnfer = new JScrollPane();
			scrollPaneListadoEnfer.setBounds(288, 153, 332, 249);
			contentPanel.add(scrollPaneListadoEnfer);
		}
		{
			JComboBox cbxTipo = new JComboBox();
			cbxTipo.setBounds(76, 193, 198, 22);
			contentPanel.add(cbxTipo);
		}
		{
			JLabel label = new JLabel("Tipo:");
			label.setBounds(12, 193, 102, 16);
			contentPanel.add(label);
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(null, "Caracter\u00EDsticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 222, 264, 180);
			contentPanel.add(panel);
			{
				JTextPane txtCaracteristicas = new JTextPane();
				txtCaracteristicas.setBounds(12, 24, 240, 143);
				panel.add(txtCaracteristicas);
			}
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(RegEnfermedad.class.getResource("/img/Banner.png")));
			label.setBounds(0, 0, 627, 143);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(176, 196, 222));
			buttonPane.setForeground(new Color(25, 25, 112));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton BtnGuardar = new JButton("Guardar");
				BtnGuardar.setActionCommand("OK");
				buttonPane.add(BtnGuardar);
				getRootPane().setDefaultButton(BtnGuardar);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}

}
