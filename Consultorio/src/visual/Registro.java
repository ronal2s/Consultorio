package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import logical.Consultorio;
import logical.Paciente;
import logical.Profesional;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JComboBox comboGrupoSanguineo;
	private JSpinner spinnerEdad;
	private JRadioButton radioMasculino;
	private JRadioButton radioFemenino;
	private JTextArea txtObservaciones;
	private JTextArea txtAntecedentes;
	private JTextArea txtAlergias;
	private JComboBox comboEstadoCivil;
	private int posModificar;
	private String nombreBoton;
	private JButton okButton;
	private String tipo;
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
		this.tipo = tipo;
		setTitle("Registro");
		setBounds(w, h, 880, 653);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panelRegistro = new JPanel();
		panelRegistro.setBounds(0, 0, 858, 334);
		setLocationRelativeTo(null);
		contentPanel.add(panelRegistro);
		panelRegistro.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Registro.class.getResource("/img/photo.png")));
		label.setBounds(15, 16, 151, 128);
		panelRegistro.add(label);
		
		JLabel lblCdula = new JLabel("C\u00E9dula: ");
		lblCdula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdula.setBounds(180, 16, 71, 20);
		panelRegistro.add(lblCdula);
		
		txtCedula = new JTextField();
		txtCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Evento de darle a enter a Cedula
				String cedula = txtCedula.getText();
				//Datos personales globales
				String nombre = "", apellidos= "", direccion= "", telefono= "", movil= "", fechaNacimiento= "", tipoSangre= "", estadoCivil= "";
				int edad=0;
				boolean sexoM = false,sexoF = false;
				
				if(posModificar != -1)
				{

					if(tipo.equalsIgnoreCase("ModificarPaciente"))
					{
						posModificar = Consultorio.getInstance().buscarPaciente(cedula);
						Paciente paciente =Consultorio.getInstance().getPacientes().get(posModificar); 
						txtObservaciones.setText(paciente.getObservaciones());
						txtAntecedentes.setText(paciente.getAntecedentes());
						txtAlergias.setText(paciente.getAlergias());
						nombre = paciente.getNombre();
						apellidos = paciente.getApellidos();
						direccion = paciente.getDireccion();
						telefono = paciente.getTelefono();
						movil = paciente.getMovil();
						edad = paciente.getEdad();
						fechaNacimiento = paciente.getFechaNacimiento();
						sexoM = paciente.getSexo() == 'M'? true: false;
						sexoF = paciente.getSexo() == 'F'? true: false;
						tipoSangre = paciente.getTipoSangre();
						estadoCivil = paciente.getEstadoCivil();
						modoModificar(false);

					}
					if(tipo.equalsIgnoreCase("ModificarProfesional"))
					{
						posModificar = Consultorio.getInstance().buscarProfesional(cedula);
						Profesional profesional = Consultorio.getInstance().getProfesionales().get(posModificar);
						nombre = profesional.getNombre();
						apellidos = profesional.getApellidos();
						direccion = profesional.getDireccion();
						telefono = profesional.getTelefono();
						movil = profesional.getMovil();
						edad = profesional.getEdad();
						fechaNacimiento = profesional.getFechaNacimiento();
						sexoM = profesional.getSexo() == 'M'? true: false;
						sexoF = profesional.getSexo() == 'F'? true: false;
						tipoSangre = profesional.getTipoSangre();
						estadoCivil = profesional.getEstadoCivil();
						txtEspecialidad.setText(profesional.getEspecialidad());
						modoModificar(false);
					}
					//Rellenando los valores globales de persona
					txtNombre.setText(nombre);
					txtApellidos.setText(apellidos);
					txtDireccion.setText(direccion);
					txtTelefono.setText(telefono);
					txtMovil.setText(movil);
					spinnerEdad.setValue(edad);
					txtFechaNacimiento.setText(fechaNacimiento);
					radioMasculino.setSelected(sexoM);
					radioFemenino.setSelected(sexoF);
					comboGrupoSanguineo.setSelectedItem(tipoSangre);
					comboEstadoCivil.setSelectedItem(estadoCivil);

				}
			}


		});
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
		
		radioMasculino = new JRadioButton("Masculino");
		radioMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioFemenino.setSelected(false);
				radioMasculino.setSelected(true);

			}
		});
		
		radioFemenino = new JRadioButton("Femenino");
		radioFemenino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioMasculino.setSelected(false);
				radioFemenino.setSelected(true);

			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(radioMasculino)
						.addComponent(radioFemenino))
					.addContainerGap(128, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(radioMasculino)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(radioFemenino)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEdad.setBounds(179, 186, 71, 20);
		panelRegistro.add(lblEdad);
		
		spinnerEdad = new JSpinner();
		spinnerEdad.setBounds(259, 182, 77, 28);
		panelRegistro.add(spinnerEdad);
		spinnerEdad.setModel(new SpinnerNumberModel(1, 1, 150, 1));
		
		JLabel lblEstadoCivil = new JLabel("Estado civil:");
		lblEstadoCivil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstadoCivil.setBounds(561, 186, 91, 20);
		panelRegistro.add(lblEstadoCivil);
		
		comboEstadoCivil = new JComboBox();
		comboEstadoCivil.setBounds(667, 182, 125, 28);
		panelRegistro.add(comboEstadoCivil);
		comboEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Casado", "Soltero"}));
		
		JLabel lblGrupoSanguineo = new JLabel("Grupo Sanguineo");
		lblGrupoSanguineo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGrupoSanguineo.setBounds(351, 186, 103, 20);
		panelRegistro.add(lblGrupoSanguineo);
		
		comboGrupoSanguineo = new JComboBox();
		comboGrupoSanguineo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "...", "...", "..."}));
		comboGrupoSanguineo.setBounds(459, 182, 89, 28);
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
		panelPaciente.setBounds(0, 335, 858, 251);
		contentPanel.add(panelPaciente);
		panelPaciente.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(15, 16, 828, 219);
		panelPaciente.add(tabbedPane);
		
		txtObservaciones = new JTextArea();
		tabbedPane.addTab("Observaciones", null, txtObservaciones, null);
		
		txtAntecedentes = new JTextArea();
		tabbedPane.addTab("Antecedentes", null, txtAntecedentes, null);
		
		txtAlergias = new JTextArea();
		tabbedPane.addTab("Alergias", null, txtAlergias, null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton(nombreBoton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String cedula, nombre,apellidos,direccion,telefono,movil, fechaNacimiento,tipoSangre, estadoCivil,alergias="",
								antecedentes="",observaciones="", cargo="",clave="";
						char sexo;
						int edad;
						cedula = txtCedula.getText();
						nombre = txtNombre.getText();
						apellidos = txtApellidos.getText();
						direccion = txtDireccion.getText();
						telefono = txtTelefono.getText();
						movil = txtMovil.getText();
						sexo = radioMasculino.isSelected()?'M': 'F';
						fechaNacimiento = txtFechaNacimiento.getText();
						tipoSangre = comboGrupoSanguineo.getSelectedItem().toString();
						edad = (int) spinnerEdad.getValue();
						estadoCivil = (String) comboEstadoCivil.getSelectedItem();
						switch(tipo)
						{
						case "RegistrarPaciente":
							alergias = txtAlergias.getText();
							antecedentes = txtAntecedentes.getText();
							observaciones = txtObservaciones.getText();
							Consultorio.getInstance().crearPaciente(cedula, nombre, apellidos, direccion, telefono, estadoCivil, movil,sexo, fechaNacimiento, 
									tipoSangre,edad, alergias, antecedentes, observaciones);
							JOptionPane.showMessageDialog(null, "Agregado correctamente");
							//limpiarCampos();
							System.out.println("Hay " + Consultorio.getInstance().getPacientes().size() + " pacientes.");
							break;
						case "ModificarPaciente":
							Paciente pacienteModificado = new Paciente(cedula, nombre, apellidos, direccion, estadoCivil, 
									telefono, movil, sexo, fechaNacimiento, tipoSangre, edad, alergias, antecedentes, observaciones);
							Consultorio.getInstance().sustituirPaciente(posModificar, pacienteModificado);
							JOptionPane.showMessageDialog(null, "Modificado correctamente");
							limpiarCampos();
							break;
						case "RegistrarProfesional":
							String especialidad = txtEspecialidad.getText();
							clave = txtClave.getText();
							if(validarClave(clave))
							{
								Consultorio.getInstance().crearProfesional(cedula, nombre, apellidos, direccion, estadoCivil,telefono, movil, sexo, fechaNacimiento, tipoSangre, edad,especialidad, clave);
								JOptionPane.showMessageDialog(null, "Agregado correctamente");
								limpiarCampos();

							}

							break;
						case "ModificarProfesional":
							clave = txtClave.getText();
							if(validarClave(clave))
							{
								Profesional profesionalModificado = new Profesional(cedula, nombre, apellidos, direccion, estadoCivil, 
										telefono, movil, sexo, fechaNacimiento, tipoSangre, edad, cargo, clave);
								Consultorio.getInstance().sustituirProfesional(posModificar, profesionalModificado);
								JOptionPane.showMessageDialog(null, "Modificado correctamente");
								limpiarCampos();
							}
							break;
						}
						
					}
				});
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
				nombreBoton = "Registrar";
				okButton.setText(nombreBoton);
				habilitarEmpleado(false);
				habilitarProfesional(false);
				habilitarPacientes(true);
				break;
			case "ModificarPaciente":
				nombreBoton = "Modificar";
				okButton.setText(nombreBoton);
				modoModificar(true);
				break;
			case "RegistrarProfesional":
				nombreBoton = "Registrar";
				okButton.setText(nombreBoton);
				habilitarPacientes(false);
				habilitarEmpleado(false);
				habilitarProfesional(true);
				break;
			case "ModificarProfesional":
				nombreBoton = "Modificar";
				okButton.setText(nombreBoton);
				modoModificar(true);
				habilitarProfesional(true);
				break;
			case "RegistrarEmpleado":
				nombreBoton = "Registrar";
				okButton.setText(nombreBoton);
				habilitarProfesional(false);
				habilitarPacientes(false);
				habilitarEmpleado(true);
				break;
			}

		}
			setLocationRelativeTo(null);
		}
	
	public void limpiarCampos()
	{
		try
		{
			txtCedula.requestFocus();
			txtApellidos.setText("");
			txtCedula.setText("");
			txtClave.setText("");
			txtConfirmarClave.setText("");
			txtDireccion.setText("");
			txtEspecialidad.setText("");
			txtFechaNacimiento.setText("");
			txtMovil.setText("");
			txtNombre.setText("");
			txtTelefono.setText("");
			comboCargo.setSelectedIndex(0);
			comboGrupoSanguineo.setSelectedIndex(0);
			spinnerEdad.setValue(0);
			txtAlergias.setText("");
			txtObservaciones.setText("");
			txtAntecedentes.setText("");
			//txtVacunas.setText("");
		}
		catch(Exception e)
		{
			System.out.println("Hay campos que no estan visibles y no pueden limpiarse");
		}
	}
	
	public boolean validarClave(String clave)
	{
		boolean correcto = false;
		if(clave.equalsIgnoreCase(txtConfirmarClave.getText()))
		{
			correcto = true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "La confirmación de la clave no coincide");
			txtClave.setText("");
			txtConfirmarClave.setText("");
			txtClave.requestFocus();
		}
		return correcto;
	}
	
	public void modoModificar(boolean enabled)
	{
		txtCedula.requestFocus();
		txtApellidos.setEnabled(!enabled);
		txtClave.setEnabled(!enabled);
		txtConfirmarClave.setEnabled(!enabled);
		txtDireccion.setEnabled(!enabled);
		txtFechaNacimiento.setEnabled(!enabled);
		txtMovil.setEnabled(!enabled);
		txtNombre.setEnabled(!enabled);
		txtTelefono.setEnabled(!enabled);
		comboGrupoSanguineo.setEnabled(!enabled);
		spinnerEdad.setEnabled(!enabled);
		txtAlergias.setEnabled(!enabled);
		txtObservaciones.setEnabled(!enabled);
		txtAntecedentes.setEnabled(!enabled);
		if(!tipo.equalsIgnoreCase("ModificarPaciente"))
		{
			enabled = true;
			panelPaciente.setVisible(false);
		}
		else
		{
			enabled = false;
		}
		txtClave.setVisible(enabled);
		lblClave.setVisible(enabled);
		txtConfirmarClave.setVisible(enabled);
		lblConfirmarClave.setVisible(enabled);
		lblEspecialidad.setVisible(enabled);
		txtEspecialidad.setVisible(enabled);

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
		panelPaciente.setVisible(enabled);
		//txtVacunas.setVisible(false);
		txtClave.setVisible(false);
		lblClave.setVisible(false);
		txtConfirmarClave.setVisible(false);
		lblConfirmarClave.setVisible(false);
		//txtVacunas.setVisible(false);
		w=100; h=100; x=880; y=enabled?670:400;
		setBounds(w, h, x, y);

	}
	public void habilitarProfesional(boolean enabled)
	{
		panelPaciente.setVisible(false);
		txtEspecialidad.setVisible(enabled);
		lblEspecialidad.setVisible(enabled);
		txtClave.setVisible(enabled);
		lblClave.setVisible(enabled);
		txtConfirmarClave.setVisible(enabled);
		lblConfirmarClave.setVisible(enabled);
		w=100; h=100; x=880; y=enabled?410:670;;
		setBounds(w, h, x, y);
	}
	
	public void habilitarEmpleado(boolean enabled)
	{//Usuario = empleado
		panelPaciente.setVisible(false);
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
