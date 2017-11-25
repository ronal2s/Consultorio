package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logical.Consultorio;
import logical.Empleado;
import logical.Paciente;
import logical.Profesional;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Listar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel model;
	private Object[] fila;
	private String CedulaBuscar;
	private JTextField txtCedula;
	private JTable table;

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
		setBounds(100, 100, 706, 477);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelPacientes = new JPanel();
		panelPacientes.setBounds(0, 0, 690, 405);
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
		txtCedula.setBounds(69, 28, 86, 28);
		panelPacientes.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Listar.class.getResource("/img/if_user-id_285641.png")));
		label.setBounds(10, 25, 57, 34);
		panelPacientes.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 670, 312);
		panelPacientes.add(scrollPane);
		
		table = new JTable();

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
		}
		table.setModel(model);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
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
