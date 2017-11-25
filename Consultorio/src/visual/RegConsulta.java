package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.ImageIcon;

public class RegConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegConsulta dialog = new RegConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegConsulta() {
		setBounds(100, 100, 658, 756);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 284, 616, 196);
		contentPanel.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 493, 616, 168);
		contentPanel.add(scrollPane);
		
		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setBounds(184, 143, 56, 16);
		contentPanel.add(lblPaciente);
		
		JLabel lblApellidos = new JLabel("--1st 2nd Name--");
		lblApellidos.setBounds(252, 143, 99, 16);
		contentPanel.add(lblApellidos);
		
		JLabel lblNewLabel = new JLabel("Num:");
		lblNewLabel.setBounds(184, 209, 56, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNumPaciente = new JLabel("----");
		lblNumPaciente.setBounds(252, 209, 56, 16);
		contentPanel.add(lblNumPaciente);
		
		JLabel lblProfesional = new JLabel("Profesional:");
		lblProfesional.setBounds(409, 143, 81, 16);
		contentPanel.add(lblProfesional);
		
		JComboBox cbxProfesional = new JComboBox();
		cbxProfesional.setBounds(409, 163, 219, 22);
		contentPanel.add(cbxProfesional);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(409, 198, 81, 16);
		contentPanel.add(lblFecha);
		
		JSpinner spnFecha = new JSpinner();
		spnFecha.setModel(new SpinnerDateModel(new Date(1511409600000L), null, null, Calendar.DAY_OF_YEAR));
		spnFecha.setBounds(409, 218, 173, 22);
		contentPanel.add(spnFecha);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(RegConsulta.class.getResource("/img/Banner.png")));
		lblNewLabel_1.setBounds(0, 0, 665, 123);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblImageHere = new JLabel("");
		lblImageHere.setIcon(new ImageIcon(RegConsulta.class.getResource("/img/if_user_285655.png")));
		lblImageHere.setBounds(24, 120, 148, 151);
		contentPanel.add(lblImageHere);
		
		JLabel lblNombre = new JLabel("--Name--");
		lblNombre.setBounds(252, 172, 56, 16);
		contentPanel.add(lblNombre);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(RegConsulta.class.getResource("/img/if_calendar-clock_299096.png")));
		lblNewLabel_2.setBounds(594, 209, 31, 31);
		contentPanel.add(lblNewLabel_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
