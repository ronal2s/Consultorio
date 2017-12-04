package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import logical.Cita;
import logical.Consulta;
import logical.Consultorio;
import logical.Enfermedad;
import logical.Paciente;
import logical.Profesional;
import logical.print;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class RegConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private Paciente paciente;
	private JLabel lblNombre;
	private JComboBox cbxProfesional;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JComboBox comboEnfermedad;
	private boolean AHistorial = false;
	private DefaultTableModel model;
	private Object[] fila;
	private JTable table;
	private int posPaciente;
	private JLabel lblImageHere; 
	private Calendar c = Calendar.getInstance();
	private JLabel NombreProfesional;
	
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
		setTitle("Crear Consulta");
		setResizable(false);
		setBounds(100, 100, 658, 688);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 200, 616, 196);
		contentPanel.add(tabbedPane);
		
		JTextArea txtExploracion = new JTextArea();
		tabbedPane.addTab("Exploracion", null, txtExploracion, null);
		
		JTextArea txtSintomas = new JTextArea();
		tabbedPane.addTab("Sintomas", null, txtSintomas, null);
		
		JTextArea txtDiagnostico = new JTextArea();
		tabbedPane.addTab("Diagn\u00F3stico", null, txtDiagnostico, null);
		
		JTextArea txtTratamiento = new JTextArea();
		tabbedPane.addTab("Tratamiento", null, txtTratamiento, null);
		
		 comboEnfermedad = new JComboBox();
		comboEnfermedad.setForeground(new Color(128, 128, 128));
		comboEnfermedad.setFont(new Font("Tahoma", Font.PLAIN, 36));
		comboEnfermedad.setModel(new DefaultComboBoxModel(new String[] {"Ninguna"}));
		tabbedPane.addTab("Enfermedad", null, comboEnfermedad, null);
		
		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		lblPaciente.setBounds(210, 35, 184, 16);
		contentPanel.add(lblPaciente);
		
		JLabel lblProfesional = new JLabel("Profesional:");
		lblProfesional.setBounds(409, 35, 219, 16);
		contentPanel.add(lblProfesional);
		
		cbxProfesional = new JComboBox();
		cbxProfesional.setVisible(false);
		cbxProfesional.setBounds(612, 29, 28, 28);
		contentPanel.add(cbxProfesional);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(409, 99, 81, 16);
		contentPanel.add(lblFecha);
		
		lblImageHere = new JLabel("");
		lblImageHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageHere.setIcon(new ImageIcon(RegConsulta.class.getResource("/img/if_user_285655.png")));
		lblImageHere.setBounds(15, 29, 151, 150);
		contentPanel.add(lblImageHere);
		
		lblNombre = new JLabel("--Name--");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(210, 93, 184, 28);
		contentPanel.add(lblNombre);
		
		txtCedula = new JTextField();
		txtCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Evento al darle enter al textField
				posPaciente = Consultorio.getInstance().buscarPaciente(txtCedula.getText());
				if(posPaciente != -1)
				{
					paciente = Consultorio.getInstance().getPacientes().get(posPaciente);
				    lblNombre.setText(paciente.getNombre() + " " + paciente.getApellidos());
				    llenarTabla();
				    cargarFoto(txtCedula.getText()+".jpg");
				    
				}
			}
		});
		txtCedula.setBounds(210, 55, 183, 28);
		contentPanel.add(txtCedula);
		txtCedula.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser(c.getTime());
		dateChooser.setBounds(409, 130, 219, 28);
		contentPanel.add(dateChooser);
		
		JCheckBox checkBoxAHistorial = new JCheckBox("Pasar a historial clinico");
		checkBoxAHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Evento del checkbox
				/*if(checkBoxAHistorial.isSelected())
				{
					AHistorial = true;
				}
				else
				{
					AHistorial = false;
				}*/
				
				AHistorial = checkBoxAHistorial.isSelected()? true: false;
			}
		});
		checkBoxAHistorial.setBounds(409, 170, 219, 28);
		contentPanel.add(checkBoxAHistorial);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 412, 616, 168);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		String[] columnNames = {"Fecha","Síntomas","Exploración", "Diagnóstico", "Tratamiento", "Enfermedad"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		NombreProfesional = new JLabel("....");
		NombreProfesional.setHorizontalAlignment(SwingConstants.LEFT);
		NombreProfesional.setBounds(409, 55, 194, 28);
		contentPanel.add(NombreProfesional);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(176, 196, 222));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.setBackground(new Color(102, 205, 170));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Profesional doctor = null;
						String sintomas="";
						String anamnesis="";
						String exploracion=""; 
						String diagnostico="";
						String tratamiento="";
						String enfermedad="";
						Cita cita = null;
						
						try
						{
							String fecha = df.format(dateChooser.getDate());

						String[] partes = cbxProfesional.getSelectedItem().toString().split("-");
						int pos = Consultorio.getInstance().buscarProfesional(partes[1]);
						doctor = Consultorio.getInstance().getProfesionales().get(pos);//Esto hay que cambiarlo luego
						sintomas = txtSintomas.getText();
						exploracion = txtExploracion.getText();
						diagnostico = txtDiagnostico.getText();
						tratamiento = txtTratamiento.getText();
						enfermedad = comboEnfermedad.getSelectedItem().toString();
						cita = paciente.getCitas().get(0);//OJO
						Consultorio.getInstance().crearConsulta(fecha, paciente, doctor, sintomas, exploracion, diagnostico, tratamiento, enfermedad, cita, AHistorial);
						paciente.getCitas().remove(0);
						Consultorio.getInstance().sustituirPaciente(posPaciente, paciente);
						
						//OJO
						//RECORDAR IMPRIMIR ESTA VAINA y el historial
						JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
						JOptionPane.showMessageDialog(null, "Imprimiendo consulta");
						String texto = "Fecha: " + fecha + "\n" +
										"\nPaciente:\n" + paciente.getNombre() + " " + paciente.getApellidos() + "\n"+
										"\nProfesional encargado:\n" + doctor.getNombre() + " " + doctor.getApellidos() + "\n" +
										"Sintomas:\n" + sintomas + "\n" + "\nExploración:\n" + exploracion + "\n" + "\nDiagnósico:\n" + diagnostico + "\n" +
										"\nEnfermedad:\n" + enfermedad + "\n" +
										"\nTratamiento:\n" + tratamiento;
						print.texto = texto;
						print imprimir = new print();
						imprimir.imprimir();
								
					}catch(Exception es)
					{
						
					JOptionPane.showMessageDialog(null, "Este paciente no tiene cita");
					}
					}
				});
				
				JButton btnImprimirHistorial = new JButton("Imprimir historial");
				btnImprimirHistorial.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(posPaciente != -1)
						{
						try {
							Consultorio.getInstance().imprimirHistorial(posPaciente);
							JOptionPane.showMessageDialog(null, "Se generó un archivo de texto en la carpeta de su programa");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch(Exception es)
						{
							JOptionPane.showMessageDialog(null, "Necesita buscar un paciente primero");
						}
					}
					}
				});
				buttonPane.add(btnImprimirHistorial);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setBackground(new Color(205, 92, 92));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setBackground(new Color(176, 196, 222));
		//actualizarProfesionales();
		llenarEnfermedades();
		if ( Consultorio.posProfesional != -1 ) {
			Profesional p = Consultorio.getInstance().getProfesionales().get(Consultorio.posProfesional);
		NombreProfesional.setText(p.getNombre()+" "+p.getApellidos());
		}
		
		
		
	}
	//Debajo del constructor
	public void cargarFoto(String ruta)
	{
		File file = new File(ruta);
		if(file.exists())
		{
			lblImageHere.setIcon(redimensionarImagen(ruta));
		}
		else
		{
			lblImageHere.setIcon(new ImageIcon(Registro.class.getResource("/img/if_user_285655.png")));

			
			//System.out.println("Este paciente no tiene foto");
		}
		
		
	}
	public ImageIcon redimensionarImagen(String ruta)
	{
		ImageIcon MyImage = new ImageIcon(ruta);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(lblImageHere.getWidth(), lblImageHere.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}
	public void llenarTabla()
	{

		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<Consulta> consultas = paciente.getHistoriaClinica();
		System.out.println("Tamaño consulta lista: " + consultas.size());
			for (Consulta c : consultas) {
				fila[0] = c.getFecha();
				fila[1] =  c.getSintomas();
				fila[2] =  c.getExploracion();
				fila[3] =  c.getDiagnostico();
				fila[4] =  c.getTratamiento();
				fila[5] =  c.getEnfermedad();
				model.addRow(fila);
			}
		
	}
	
	public void actualizarProfesionales()
	{
		cbxProfesional.addItem("Seleccionar");
		for (Profesional p : Consultorio.getInstance().getProfesionales()) {
			cbxProfesional.addItem(p.getNombre() + " " + p.getApellidos() + "-" + p.getCedula());
		}
	}
	
	public void llenarEnfermedades()
	{
		for (Enfermedad e : Consultorio.getInstance().getEnfermedades()) {
			comboEnfermedad.addItem(e.getNombre());
		}
	}
}
