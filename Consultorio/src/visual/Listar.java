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
import logical.Paciente;

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
					listarPacientes("Cedula");
				}
				else
				{
					listarPacientes("Todos");
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
			//listarPacientes("Todos");
			break;
		}
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		switch(tipoLista)
		{
		case "Pacientes":
			listarPacientes("Todos");
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
