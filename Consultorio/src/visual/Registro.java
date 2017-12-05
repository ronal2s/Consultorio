package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.joda.time.LocalDate;
import org.joda.time.ReadablePartial;
import org.joda.time.Years;

import logical.Consultorio;
import logical.Empleado;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Registro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtMovil;
	private JTextField txtEspecialidad;
	private JPanel panelPaciente;
	private JLabel lblEspecialidad;
	private JComboBox comboCargo = new JComboBox();
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
	private Calendar c = Calendar.getInstance();
	//c.add(Calendar.YEAR, -10);
	private JDateChooser txtFechaNacimiento;
	private DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
	private boolean getPhoto;
	private JLabel labelPhoto;
	private String ruta, cedula;
	
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
		setResizable(false);
		this.tipo = tipo;
		setTitle("Registro");
		setBounds(w, h, 880, 653);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(176, 196, 222));
		panelRegistro = new JPanel();
		panelRegistro.setBounds(0, 0, 858, 334);
		panelRegistro.setBackground(new Color(176, 196, 222));
		setLocationRelativeTo(null);
		contentPanel.add(panelRegistro);
		panelRegistro.setLayout(null);
		
		labelPhoto = new JLabel("");
		labelPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		labelPhoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Seleccionar foto
					seleccionarPhoto();
		
			}


		});
		labelPhoto.setIcon(new ImageIcon(Registro.class.getResource("/img/photo.png")));
		labelPhoto.setBounds(15, 16, 151, 150);
		panelRegistro.add(labelPhoto);
		
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
				try
				{
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
						cargarFoto(txtCedula.getText()+".jpg");

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
						cargarFoto(txtCedula.getText()+".jpg");

					}
					if(tipo.equalsIgnoreCase("ModificarEmpleado"))
					{
						posModificar = Consultorio.getInstance().buscarEmpleado(cedula);
						Empleado empleado = Consultorio.getInstance().getEmpleados().get(posModificar);
						nombre = empleado.getNombre();
						apellidos = empleado.getApellidos();
						direccion = empleado.getDireccion();
						telefono = empleado.getTelefono();
						movil = empleado.getMovil();
						edad = empleado.getEdad();
						fechaNacimiento = empleado.getFechaNacimiento();
						sexoM = empleado.getSexo() == 'M'? true: false;
						sexoF = empleado.getSexo() == 'F'? true: false;
						tipoSangre = empleado.getTipoSangre();
						estadoCivil = empleado.getEstadoCivil();
						comboCargo.setSelectedItem(empleado.getCargo());
						modoModificar(false);
						cargarFoto(txtCedula.getText()+".jpg");
					}
					//Rellenando los valores globales de persona
					txtNombre.setText(nombre);
					txtApellidos.setText(apellidos);
					txtDireccion.setText(direccion);
					txtTelefono.setText(telefono);
					txtMovil.setText(movil);
					spinnerEdad.setValue(edad);
					//Poner la fecha
					//txtFechaNacimiento.setDate(df.format(txtFechaNacimiento.getDate()));
					
					radioMasculino.setSelected(sexoM);
					radioFemenino.setSelected(sexoF);
					comboGrupoSanguineo.setSelectedItem(tipoSangre);
					comboEstadoCivil.setSelectedItem(estadoCivil);

				}
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Error. Puede que la persona a buscar no exista");
					//limpiarCampos();
					dispose();//Hago esto porque luego de que sale un catch no puedo volver a usar el evento ENTER
					//System.out.println("Error buscando: " + e1.getMessage());
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
		lblEdad.setVisible(false);
		lblEdad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEdad.setBounds(635, 20, 71, 20);
		panelRegistro.add(lblEdad);
		
		spinnerEdad = new JSpinner();
		spinnerEdad.setVisible(false);
		spinnerEdad.setBounds(715, 16, 77, 28);
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
		lblGrupoSanguineo.setBounds(180, 182, 137, 20);
		panelRegistro.add(lblGrupoSanguineo);
		
		comboGrupoSanguineo = new JComboBox();
		comboGrupoSanguineo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Tipo A", "Tipo B", "Tipo AB", "Tipo O"}));
		comboGrupoSanguineo.setBounds(329, 182, 219, 28);
		panelRegistro.add(comboGrupoSanguineo);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaNacimiento.setBounds(180, 226, 137, 20);
		panelRegistro.add(lblFechaNacimiento);
		
		txtEspecialidad = new JTextField();
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
		
		txtFechaNacimiento = new JDateChooser(c.getTime());
		txtFechaNacimiento.setBounds(329, 220, 222, 28);
		txtFechaNacimiento.setDateFormatString("dd/MM/yyyy");


		panelRegistro.add(txtFechaNacimiento);
		
		panelPaciente = new JPanel();
		panelPaciente.setBorder(new TitledBorder(null, "Campos espec\u00EDficos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPaciente.setBounds(0, 335, 858, 251);
		contentPanel.add(panelPaciente);
		panelPaciente.setLayout(null);
		panelPaciente.setBackground(new Color(176, 196, 222));
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
			buttonPane.setBackground(new Color(176, 196, 222));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton(nombreBoton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
						String nombre,apellidos,direccion,telefono,movil, fechaNacimiento,tipoSangre, estadoCivil,alergias="",
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
						fechaNacimiento = df.format(txtFechaNacimiento.getDate()) == ""? "No agregado":df.format(txtFechaNacimiento.getDate()) ;
						//fechaNacimiento = txtFechaNacimiento.getText();
						tipoSangre = comboGrupoSanguineo.getSelectedItem().toString();
						String [] partesFecha = df.format(txtFechaNacimiento.getDate()).split("/");
						System.out.print("Fecha: " + partesFecha[2] + "/" + partesFecha[1] + "/" + partesFecha[0]);
						LocalDate birthdate = new LocalDate (Integer.valueOf(partesFecha[0]), Integer.valueOf(partesFecha[1]), Integer.valueOf(partesFecha[2]));
						LocalDate now = new LocalDate();
						Years age = Years.yearsBetween(birthdate, now);
						edad =  age.getYears();//(int) spinnerEdad.getValue();
						System.out.println("Edad: " + edad);
						estadoCivil = (String) comboEstadoCivil.getSelectedItem();
						//Recordar que hay que validar la cedula y todas esas vainas que no lo dejen vacío 
						switch(tipo)
						{
						case "RegistrarPaciente":
							alergias = txtAlergias.getText();
							antecedentes = txtAntecedentes.getText();
							observaciones = txtObservaciones.getText();
							Consultorio.getInstance().crearPaciente(cedula, nombre, apellidos, direccion, telefono, estadoCivil, movil,sexo, fechaNacimiento, 
									tipoSangre,edad, alergias, antecedentes, observaciones);
							JOptionPane.showMessageDialog(null, "Agregado correctamente");
							limpiarCampos();
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
							cargo = txtEspecialidad.getText();
							if(validarClave(clave))
							{
								Profesional profesionalModificado = new Profesional(cedula, nombre, apellidos, direccion, estadoCivil, 
										telefono, movil, sexo, fechaNacimiento, tipoSangre, edad, cargo, clave);
								Consultorio.getInstance().sustituirProfesional(posModificar, profesionalModificado);
								JOptionPane.showMessageDialog(null, "Modificado correctamente");
								limpiarCampos();
							}
							break;
						case "RegistrarEmpleado":
							cargo = comboCargo.getSelectedItem().toString();
							clave = txtClave.getText();
							if(comboCargo.getSelectedIndex()==1)
							{
								if(!Principal.getTipo().equalsIgnoreCase("Administrador"))
									{
										JOptionPane.showMessageDialog(null, "No tienes los suficientes permisos para crear un administrador nuevo.");
									}
								else
								{
									if(validarClave(clave))
									{
										Consultorio.getInstance().crearEmpleado(cedula, nombre, apellidos, direccion, estadoCivil,telefono, movil, sexo, fechaNacimiento, tipoSangre, edad,cargo, clave);
										JOptionPane.showMessageDialog(null, "Agregado correctamente");
										limpiarCampos();
									}
								}
							}
							else
							{
								if(validarClave(clave))
								{
									Consultorio.getInstance().crearEmpleado(cedula, nombre, apellidos, direccion, estadoCivil,telefono, movil, sexo, fechaNacimiento, tipoSangre, edad,cargo, clave);
									JOptionPane.showMessageDialog(null, "Agregado correctamente");
									limpiarCampos();
								}
							}


							break;
						case "ModificarEmpleado":
							clave = txtClave.getText();
							cargo = comboCargo.getSelectedItem().toString();
							if(validarClave(clave))
							{
								Empleado empleadoModificado = new Empleado(cedula, nombre, apellidos, direccion, estadoCivil, 
										telefono, movil, sexo, fechaNacimiento, tipoSangre, edad, cargo, clave);
								Consultorio.getInstance().sustituirEmpleado(posModificar, empleadoModificado);
								JOptionPane.showMessageDialog(null, "Modificado correctamente");
								limpiarCampos();
							}
							break;
						}
						if(getPhoto)
						{
							copiarFoto(ruta);
						}
						}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error, compruebe sus datos");
							System.out.println("Error: " + e1.getMessage());
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
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
			case "ModificarEmpleado":
				nombreBoton = "Modificar";
				okButton.setText(nombreBoton);
				habilitarProfesional(false);
				habilitarPacientes(false);
				habilitarEmpleado(true);
				break;
			}
			setLocationRelativeTo(null);

		}

		}

	
	public void seleccionarPhoto()
	{
		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		file.addChoosableFileFilter(filter);
		int result = file.showSaveDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File selectedFile = file.getSelectedFile();
			ruta = selectedFile.getAbsolutePath();

			
			
			labelPhoto.setIcon(redimensionarImagen(ruta));
			getPhoto = true;
		}
	}
	
	
	public void cargarFoto(String ruta)
	{
		File file = new File(ruta);
		if(file.exists())
		{
			labelPhoto.setIcon(redimensionarImagen(ruta));
		}
		else
		{
			labelPhoto.setIcon(new ImageIcon(Registro.class.getResource("/img/photo.png")));

			
			System.out.println("Este paciente no tiene foto");
		}
		
		
	}
	public void copiarFoto(String ruta) throws FileNotFoundException
	{
		String nuevaRuta = cedula+".jpg";
		//Copiando la imagen
		File archivoEntrada = new File(ruta);
		
		File archivoSalida = new File(nuevaRuta);
		//archivoSalida.mkdirs();
		
		FileInputStream lector = new FileInputStream(archivoEntrada);
		FileOutputStream escritor = new FileOutputStream(archivoSalida);
		
		int unByte;
		try
		{
	        try {
				while ((unByte = lector.read()) != -1)
				   escritor.write(unByte);
				
			    lector.close();
		        escritor.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}catch(Exception e)
		{
			System.out.println("Error copiando el archivo: " + e.getMessage());
		}
	}
	
	public ImageIcon redimensionarImagen(String ruta)
	{
		ImageIcon MyImage = new ImageIcon(ruta);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(labelPhoto.getWidth(), labelPhoto.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}
	
	public void limpiarCampos()
	{
		cargarFoto("");

		try
		{
			txtNombre.setText("");
			txtCedula.requestFocus();
			txtApellidos.setText("");
			txtCedula.setText("");
			txtClave.setText("");
			txtConfirmarClave.setText("");
			txtDireccion.setText("");
			//Limpiar la fecha
			txtFechaNacimiento = new JDateChooser(c.getTime());
			txtMovil.setText("");
			txtTelefono.setText("");
			comboGrupoSanguineo.setSelectedIndex(0);
			spinnerEdad.setValue(0);
			txtAlergias.setText("");
			txtObservaciones.setText("");
			txtAntecedentes.setText("");
			txtEspecialidad.setText("");
			comboCargo.setSelectedIndex(0);

			//txtVacunas.setText("");
		}
		catch(Exception e)
		{
			System.out.println("Hay campos que no estan visibles y no pueden limpiarse: " + e.getMessage());
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
		/*txtCedula.requestFocus();
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
		txtAntecedentes.setEnabled(!enabled);*/
		lblEspecialidad.setVisible(false);
		txtEspecialidad.setVisible(false);

		if(!tipo.equalsIgnoreCase("ModificarPaciente"))
		{
			enabled = true;
			panelPaciente.setVisible(false);
			if(tipo.equalsIgnoreCase("ModificarProfesional"))
			{
				lblEspecialidad.setVisible(enabled);
				txtEspecialidad.setVisible(enabled);
			}
		}
		else
		{
			enabled = false;
		}
		txtClave.setVisible(enabled);
		lblClave.setVisible(enabled);
		txtConfirmarClave.setVisible(enabled);
		lblConfirmarClave.setVisible(enabled);

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
		//comboCargo = new JComboBox();
		comboCargo.setBounds(329, 259, 237, 28);
		panelRegistro.add(comboCargo);
		comboCargo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Administrador", "Secretaria"}));
		comboCargo.setVisible(enabled);
	}
}
