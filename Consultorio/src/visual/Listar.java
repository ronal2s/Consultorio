package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import logical.Cita;
import logical.Consultorio;
import logical.Empleado;
import logical.Paciente;
import logical.Profesional;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Observer;
import javax.swing.UIManager;

public class Listar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel model;
	private Object[] fila;
	private String CedulaBuscar;
	private JTextField txtCedula;
	private JTable table;
	private JDateChooser dateChooser;
	private String fechaBuscar="";
	private JLabel labelPhoto;
	private JButton botonBuscarFecha;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private int posModificar=-1;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			Listar dialog = new Listar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public Listar(String tipoLista) {
		setBounds(100, 100, 706, 490);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelPacientes = new JPanel();
		panelPacientes.setBounds(0, 0, 690, 533);
		contentPanel.add(panelPacientes);
		panelPacientes.setLayout(null);

		txtCedula = new JTextField();
		txtCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Buscar por filtro
				if(!txtCedula.getText().equalsIgnoreCase(""))
				{
					CedulaBuscar = txtCedula.getText();
					if(tipoLista.equalsIgnoreCase("Pacientes"))
					{
						listarPacientes("Cedula");	
					}
					if(tipoLista.equalsIgnoreCase("Profesionales"))
					{
						listarProfesionales("Cedula");	
					}
					if(tipoLista.equalsIgnoreCase("Empleados"))
					{
						listarEmpleados("Cedula");	
					}
				}
				else
				{
					if(tipoLista.equalsIgnoreCase("Pacientes"))
					{
						listarPacientes("Todos");	
					}
					if(tipoLista.equalsIgnoreCase("Profesionales"))
					{
						listarProfesionales("Todos");	
					}
					if(tipoLista.equalsIgnoreCase("Empleados"))
					{
						listarEmpleados("Todos");	
					}
				}

			}
		});
		txtCedula.setToolTipText("Escribe la cedula del paciente a buscar");
		txtCedula.setBounds(69, 28, 150, 28);
		panelPacientes.add(txtCedula);
		txtCedula.setColumns(10);
		
		labelPhoto = new JLabel("");
		labelPhoto.setIcon(new ImageIcon(Listar.class.getResource("/img/if_user-id_285641.png")));
		labelPhoto.setBounds(10, 25, 57, 34);
		panelPacientes.add(labelPhoto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 670, 312);
		panelPacientes.add(scrollPane);
		dateChooser = new JDateChooser();
		dateChooser.setVisible(false);
		dateChooser.setBounds(413, 28, 172, 28);
		dateChooser.setDateFormatString("dd/MM/yyyy");		
		botonBuscarFecha = new JButton("Buscar");
		botonBuscarFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Evento del boton
				
				try
				{
					fechaBuscar = df.format(dateChooser.getDate());
					listarAgenda("Fecha");
				}catch(Exception e2)
				{
					listarAgenda("Todos");
				}
			}
		});
		botonBuscarFecha.setVisible(false);
		botonBuscarFecha.setBounds(255, 28, 89, 28);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Obtener index
				int index;
				if(table.getSelectedRow()>=0){
					//btnEliminar.setEnabled(true);
					//btnModificar.setEnabled(true);
					index = table.getSelectedRow();
					posModificar = (int)table.getModel().getValueAt(index, 0);
					//posModificar = Integer.valueOf(n);
				    System.out.println("Seleccionado la cita: " + posModificar);				
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Manejar la tabla
		String[] columnNames = {""};
		switch(tipoLista)
		{
		case "Pacientes":
			String[] pacientes = {"Paciente", "Cedula", "Fecha Nacimiento", "Teléfono", "Móvil", "Observaciones"};
			columnNames = pacientes;
			break;
		case "Profesionales":
			String[] profesionales = {"Profesional", "Especialidad", "Cedula", "Fecha Nacimiento", "Teléfono", "Móvil","Citas"};
			columnNames = profesionales;
			break;
		case "Empleados":
			String[] empleados = {"Empleado", "Cargo", "Cedula", "Fecha Nacimiento", "Teléfono", "Móvil"};
			columnNames = empleados;
			break;
		case "Agenda":
			String[] citas = {"#","Fecha","Hora","Descripción","Profesional"};
			columnNames = citas;
			break;
		}
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		switch(tipoLista)
		{
		case "Pacientes":
			listarPacientes("Todos");
			break;
		case "Profesionales":
			listarProfesionales("Todos");
			break;
		case "Empleados":
			listarEmpleados("Todos");
			break;
		case "Agenda":
			txtCedula.setVisible(false);
			labelPhoto.setVisible(false);
			dateChooser.setBounds(txtCedula.getBounds().x, txtCedula.getBounds().y, dateChooser.getBounds().width, dateChooser.getBounds().height);
			dateChooser.setVisible(true);
			botonBuscarFecha.setVisible(true);
			listarAgenda("Todos");
			break;
		}
		table.setModel(model);
		scrollPane.setViewportView(table);
		

		panelPacientes.add(dateChooser);
		
		
		panelPacientes.add(botonBuscarFecha);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Modificar");
				okButton.setBackground(UIManager.getColor("Button.background"));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegCita cita = new RegCita(posModificar);
						cita.setModal(true);
						cita.setVisible(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Eliminar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}
	public void listarAgenda(String tipo)
	{
		int n =0;
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<Cita> citas = Consultorio.getInstance().getCitas();
		System.out.println("Tamaño citas lista: " + citas.size());
		switch(tipo)
		{
		case "Todos":
			for (Cita cita : citas) {
				fila[0] = n;
				fila[1] =  cita.getFecha();
				fila[2] =  cita.getHora();
				fila[3] =  cita.getDescripcion();
				fila[4] =  cita.getDoctor().getNombre() + " " + cita.getDoctor().getApellidos();
				n++;
				model.addRow(fila);
			}
			break;
		case "Fecha":
			for (Cita cita : citas) {
				if(cita.getFecha().equalsIgnoreCase(fechaBuscar))
				{
					fila[0] = n;
					fila[1] =  cita.getFecha();
					fila[2] =  cita.getHora();
					fila[3] =  cita.getDescripcion();
					fila[4] =  cita.getDoctor().getNombre() + " " + cita.getDoctor().getApellidos();
					n++;
					model.addRow(fila);
				}
			}
			break;
		}
	}
	
	public void listarProfesionales(String tipo) 
	{
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<Profesional> 	profesionales = Consultorio.getInstance().getProfesionales();;
		switch(tipo)
		{

		case "Todos":
			System.out.println("Tamaño profesionales lista: " + profesionales.size());
			for (Profesional profesional : profesionales) {
				fila[0] = profesional.getNombre() + " " + profesional.getApellidos();
				fila[1] = profesional.getEspecialidad();
				fila[2] = profesional.getCedula();
				fila[3] = profesional.getFechaNacimiento();
				fila[4] = profesional.getTelefono();
				fila[5] = profesional.getMovil();
				fila[6] = profesional.getCitas().size();;
				model.addRow(fila);
			}
			break;
		case "Cedula":
			for (Profesional profesional : profesionales) {
				if(profesional.getCedula().equalsIgnoreCase(CedulaBuscar))
				{
					fila[0] = profesional.getNombre() + " " + profesional.getApellidos();
					fila[1] = profesional.getEspecialidad();
					fila[2] = profesional.getCedula();
					fila[3] = profesional.getFechaNacimiento();
					fila[4] = profesional.getTelefono();
					fila[5] = profesional.getMovil();
					fila[6] = profesional.getCitas().size();;
					model.addRow(fila);
				}
			}
			break;
		}
	}
	public void listarEmpleados(String tipo) 
	{
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<Empleado> empleados = Consultorio.getInstance().getEmpleados();;
		switch(tipo)
		{
		case "Todos":
			System.out.println("Tamaño empleados lista: " + empleados.size());
			for (Empleado empleado : empleados) {
				fila[0] = empleado.getNombre() + " " + empleado.getApellidos();
				fila[1] = empleado.getCargo();
				fila[2] = empleado.getCedula();
				fila[3] = empleado.getFechaNacimiento();
				fila[4] = empleado.getTelefono();
				fila[5] = empleado.getMovil();
				model.addRow(fila);
			}
			break;
		case "Cedula":
			for (Empleado empleado : empleados) {
				if(empleado.getCedula().equalsIgnoreCase(CedulaBuscar))
				{
					fila[0] = empleado.getNombre() + " " + empleado.getApellidos();
					fila[1] = empleado.getCargo();
					fila[2] = empleado.getCedula();
					fila[3] = empleado.getFechaNacimiento();
					fila[4] = empleado.getTelefono();
					fila[5] = empleado.getMovil();
					model.addRow(fila);
				}
			}
			break;
		}
	}
	public void listarPacientes(String tipo)
	{
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<Paciente> pacientes;
		switch(tipo)
		{

		case "Todos":
			pacientes = Consultorio.getInstance().getPacientes();
			System.out.println("Tamaño pacientes lista: " + pacientes.size());
			for (Paciente paciente : pacientes) {
				System.out.println("Tamaño pacientes lista: " + pacientes.size());

				fila[0] = paciente.getNombre() + " " + paciente.getApellidos();
				fila[1] = paciente.getCedula();
				fila[2] = paciente.getFechaNacimiento();
				fila[3] = paciente.getTelefono();
				fila[4] = paciente.getMovil();
				fila[5] = paciente.getObservaciones();
				model.addRow(fila);
			}
			break;
		case "Cedula":
			pacientes = Consultorio.getInstance().getPacientes();
			System.out.println("Tamaño pacientes lista: " + pacientes.size());
			for (Paciente paciente : pacientes) {
				if(paciente.getCedula().equalsIgnoreCase(CedulaBuscar))
				{
					fila[0] = paciente.getNombre() + " " + paciente.getApellidos();
					fila[1] = paciente.getCedula();
					fila[2] = paciente.getFechaNacimiento();
					fila[3] = paciente.getTelefono();
					fila[4] = paciente.getMovil();
					fila[5] = paciente.getObservaciones();
					model.addRow(fila);
				}
			}
			break;
		}
	}
}
