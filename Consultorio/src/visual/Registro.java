package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Registro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtMovil;
	private JTextField txtEspecialidad;
	private JTextField txtFechaNacimiento;
	private JPanel panelPaciente;
	private JLabel lblEspecialidad;
	private JComboBox comboCargo;
	private JPanel panelRegistro;
	private int w=100,h=100,x=880,y=670;
	private JPasswordField txtClave;
	private JLabel lblClave;
	private JPasswordField txtConfirmarClave;
	private JLabel lblConfirmarClave;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			Registro dialog = new Registro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public Registro(String tipo) {
		setTitle("Registro");
		setBounds(w, h, x, y);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panelRegistro = new JPanel();
		panelRegistro.setBounds(0, 0, 858, 322);
		setLocationRelativeTo(null);
		contentPanel.add(panelRegistro);
		panelRegistro.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Registro.class.getResource("/imgs/photo.png")));
		label.setBounds(15, 16, 151, 128);
		panelRegistro.add(label);
		
		JLabel lblCdula = new JLabel("C\u00E9dula: ");
		lblCdula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdula.setBounds(180, 16, 71, 20);
		panelRegistro.add(lblCdula);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(260, 12, 202, 28);
		panelRegistro.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(180, 60, 71, 20);
		panelRegistro.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(260, 56, 202, 28);
		panelRegistro.add(txtNombre);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(565, 56, 227, 28);
		panelRegistro.add(txtApellidos);
		
		JLabel lblApellido = new JLabel("Apellido/s:");
		lblApellido.setBounds(477, 60, 89, 20);
		panelRegistro.add(lblApellido);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccin.setBounds(180, 106, 71, 20);
		panelRegistro.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(260, 102, 532, 28);
		panelRegistro.add(txtDireccion);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelfono.setBounds(180, 146, 71, 20);
		panelRegistro.add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(260, 142, 202, 28);
		panelRegistro.add(txtTelefono);
		
		JLabel lblMvil = new JLabel("M\u00F3vil:");
		lblMvil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMvil.setBounds(477, 146, 71, 20);
		panelRegistro.add(lblMvil);
		
		txtMovil = new JTextField();
		txtMovil.setColumns(10);
		txtMovil.setBounds(565, 142, 227, 28);
		panelRegistro.add(txtMovil);
		
		JPanel panel = new JPanel();
		panel.setBounds(15, 175, 144, 86);
		panelRegistro.add(panel);
		panel.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Masculino");
		
		JRadioButton rdbtnFemenino = new JRadioButton("Femenino");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(rdbtnFemenino))
					.addContainerGap(128, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(rdbtnNewRadioButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnFemenino)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEdad.setBounds(179, 186, 71, 20);
		panelRegistro.add(lblEdad);
		
		JSpinner spinnerEdad = new JSpinner();
		spinnerEdad.setBounds(259, 182, 77, 28);
		panelRegistro.add(spinnerEdad);
		spinnerEdad.setModel(new SpinnerNumberModel(1, 1, 150, 1));
		
		JLabel lblEstadoCivil = new JLabel("Estado civil:");
		lblEstadoCivil.setBounds(561, 186, 91, 20);
		panelRegistro.add(lblEstadoCivil);
		
		JComboBox comboEstadoCivil = new JComboBox();
		comboEstadoCivil.setBounds(667, 182, 125, 28);
		panelRegistro.add(comboEstadoCivil);
		comboEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Casado", "Soltero"}));
		
		JLabel lblGrupoSanguineo = new JLabel("Grupo Sanguineo");
		lblGrupoSanguineo.setBounds(351, 186, 151, 20);
		panelRegistro.add(lblGrupoSanguineo);
		
		JComboBox comboGrupoSanguineo = new JComboBox();
		comboGrupoSanguineo.setBounds(487, 182, 61, 28);
		panelRegistro.add(comboGrupoSanguineo);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaNacimiento.setBounds(180, 226, 137, 20);
		panelRegistro.add(lblFechaNacimiento);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFechaNacimiento.setText("DD/MM/AAAA");
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(329, 222, 237, 28);
		panelRegistro.add(txtFechaNacimiento);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setText("Este campo es del tipo Profesional y ya");
		txtEspecialidad.setBackground(SystemColor.info);
		txtEspecialidad.setBounds(329, 259, 463, 28);
		panelRegistro.add(txtEspecialidad);
		txtEspecialidad.setColumns(10);
		
		lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setBounds(214, 263, 103, 20);
		panelRegistro.add(lblEspecialidad);
		lblEspecialidad.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblClave = new JLabel("Clave:");
		lblClave.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClave.setBounds(248, 299, 69, 20);
		panelRegistro.add(lblClave);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(329, 296, 151, 28);
		panelRegistro.add(txtClave);
		
		txtConfirmarClave = new JPasswordField();
		txtConfirmarClave.setBounds(641, 296, 151, 28);
		panelRegistro.add(txtConfirmarClave);
		
		lblConfirmarClave = new JLabel("Confirmar clave:");
		lblConfirmarClave.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmarClave.setBounds(495, 299, 134, 20);
		panelRegistro.add(lblConfirmarClave);
		
		panelPaciente = new JPanel();
		panelPaciente.setBorder(new TitledBorder(null, "Campos espec\u00EDficos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPaciente.setBounds(0, 324, 858, 251);
		contentPanel.add(panelPaciente);
		panelPaciente.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(15, 16, 828, 219);
		panelPaciente.add(tabbedPane);
		
		JTextArea txtObservaciones = new JTextArea();
		tabbedPane.addTab("Observaciones", null, txtObservaciones, null);
		
		JTextArea txtAntecedentes = new JTextArea();
		tabbedPane.addTab("Antecedentes", null, txtAntecedentes, null);
		
		JTextArea txtAlergias = new JTextArea();
		tabbedPane.addTab("Alergias", null, txtAlergias, null);
		
		JTextArea txtVacunas = new JTextArea();
		tabbedPane.addTab("Vacunas", null, txtVacunas, null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setBackground(new Color(102, 205, 170));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBackground(new Color(205, 92, 92));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			switch(tipo)
			{
			case "RegistrarPaciente": 
				habilitarEmpleado(false);
				habilitarProfesional(false);
				habilitarPacientes(true);
				break;
			case "RegistrarProfesional":
				habilitarPacientes(false);
				habilitarEmpleado(false);
				habilitarProfesional(true);
				break;
			case "RegistrarEmpleado":
				habilitarProfesional(false);
				habilitarPacientes(false);
				habilitarEmpleado(true);
				break;
			}

		}
			setLocationRelativeTo(null);

		}
	
	public void configuracionDefault()
	{
		panelPaciente.setEnabled(false);
		txtEspecialidad.setVisible(false);
		lblEspecialidad.setVisible(false);
		txtClave.setVisible(false);

	}
	public void habilitarPacientes(boolean enabled)
	{
		
		panelPaciente.setEnabled(enabled);
		txtClave.setVisible(false);
		lblClave.setVisible(false);
		txtConfirmarClave.setVisible(false);
		lblConfirmarClave.setVisible(false);
		w=100; h=100; x=880; y=enabled?670:400;
		setBounds(w, h, x, y);

	}
	public void habilitarProfesional(boolean enabled)
	{
		txtEspecialidad.setVisible(enabled);
		lblEspecialidad.setVisible(enabled);
		txtClave.setVisible(enabled);
		lblClave.setVisible(enabled);
		txtConfirmarClave.setVisible(enabled);
		lblConfirmarClave.setVisible(enabled);
		w=100; h=100; x=880; y=enabled?400:670;;
		setBounds(w, h, x, y);
	}
	
	public void habilitarEmpleado(boolean enabled)
	{//Usuario = empleado
		lblEspecialidad.setVisible(enabled);
		txtClave.setVisible(enabled);
		lblClave.setVisible(enabled);
		txtConfirmarClave.setVisible(enabled);
		lblConfirmarClave.setVisible(enabled);
		lblEspecialidad.setText("Cargo:");
		comboCargo = new JComboBox();
		comboCargo.setBounds(329, 259, 237, 28);
		panelRegistro.add(comboCargo);
		comboCargo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Administrador", "Secretaria"}));
		comboCargo.setVisible(enabled);
	}
}
