package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logical.Consultorio;
import logical.Paciente;
import logical.Vacuna;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AgregarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTable table;
	private DefaultTableModel model;
	private Object[] fila;
	private Paciente paciente;
	private JButton botonAgregarvacuna;
	private JButton botonAgregarDosis;
	private int posModificar=-1;
	private JComboBox comboBox;
	private LocalDate localdate = LocalDate.now();
	private String fecha = (DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localdate));
	private JLabel lblNombre;
	private String vacunaAasignarDosis="";
	private int index=-1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarVacuna dialog = new AgregarVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgregarVacuna() {
		setTitle("Agregar vacuna y d\u00F3sis");
		setResizable(false);
		setBounds(100, 100, 555, 586);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(176, 196, 222));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelVacuna = new JPanel();
			panelVacuna.setBackground(new Color(176, 196, 222));
			panelVacuna.setBounds(0, 0, 537, 489);
			contentPanel.add(panelVacuna);
			panelVacuna.setLayout(null);
			{
				txtCedula = new JTextField();
				txtCedula.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						posModificar = Consultorio.getInstance().buscarPaciente(txtCedula.getText());
						if(posModificar != -1)
						{
							paciente = Consultorio.getInstance().getPacientes().get(posModificar);
							System.out.println("Paciente: " + paciente.getNombre());
							lblNombre.setText(paciente.getNombre() + " "  + paciente.getApellidos());
						    listarVacunas();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Este paciente parece no estar registrado");
						}
					}
				});
				txtCedula.setBounds(12, 39, 168, 28);
				panelVacuna.add(txtCedula);
				txtCedula.setColumns(10);
			}
			{
				JLabel lblCedula = new JLabel("C\u00E9dula:");
				lblCedula.setBounds(12, 10, 56, 16);
				panelVacuna.add(lblCedula);
			}
			{
				JScrollPane scrollPane = new JScrollPane();


				scrollPane.setBounds(12, 123, 511, 353);
				panelVacuna.add(scrollPane);
				{
					table = new JTable();
					String[] columnNames = {"Nombre","Tipo", "Dósis aplicada", "Última dósis"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnNames);
					table.setModel(model);

					scrollPane.setViewportView(table);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				}
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						//Obtener index
						if(table.getSelectedRow()>=0){
							//btnEliminar.setEnabled(true);
							//btnModificar.setEnabled(true);
							index = table.getSelectedRow();
							vacunaAasignarDosis = (String) table.getModel().getValueAt(index, 0);
							//posModificar = Integer.valueOf(n);
						    System.out.println("Seleccionado la vacuna: " + vacunaAasignarDosis + " Index: " + index);
						    botonAgregarvacuna.setEnabled(false);
						    botonAgregarDosis.setEnabled(true);
						}
						else
						{
							botonAgregarvacuna.setEnabled(true);
							botonAgregarDosis.setEnabled(false);

						}
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
			}
			
			{
				JLabel lblVacuna = new JLabel("Vacuna:");
				lblVacuna.setBounds(245, 10, 104, 16);
				panelVacuna.add(lblVacuna);
			}
			
			comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Evento a combobox
					
					if(comboBox.getSelectedIndex() != 0)
					{
					
						botonAgregarDosis.setEnabled(false);
						botonAgregarvacuna.setEnabled(true);
					}
					else
					{
						botonAgregarDosis.setEnabled(true);
						botonAgregarvacuna.setEnabled(false);
					}
					
					
					//Creo que le falta el else para que se deshabilite luego
				}
			});
			comboBox.setBounds(235, 39, 288, 28);
			panelVacuna.add(comboBox);
			{
				lblNombre = new JLabel("");
				lblNombre.setBounds(12, 82, 168, 16);
				panelVacuna.add(lblNombre);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(176, 196, 222));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				botonAgregarvacuna = new JButton("Asignar vacuna");
				botonAgregarvacuna.setBackground(new Color(102, 205, 170));
				botonAgregarvacuna.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Evento de asignarle vacuna al paciente
						if(paciente != null)
						{
							String nombreVacuna = comboBox.getSelectedItem().toString();
							Vacuna vacuna = Consultorio.getInstance().getVacunas().get(Consultorio.getInstance().buscarVacuna(nombreVacuna));
							vacuna.setLista(new ArrayList<String>());//Limpiando la dosis que podría tener esta vacuna para no ponersela al paciente
							ArrayList<Vacuna> vacunas = paciente.getVacunas();
							vacunas.add(vacuna);
							paciente.setVacunas(vacunas);
							System.out.println("Posicion a modificar: " + posModificar);
							Consultorio.getInstance().sustituirPaciente(posModificar, paciente);
							JOptionPane.showMessageDialog(null, "Vacuna agregada correctamente");
							listarVacunas();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Debe buscar un paciente primero");
						}
					}
				});
				botonAgregarvacuna.setEnabled(false);
				botonAgregarvacuna.setActionCommand("OK");
				buttonPane.add(botonAgregarvacuna);
				getRootPane().setDefaultButton(botonAgregarvacuna);
			}
			{
				botonAgregarDosis = new JButton("Asignar dosis");
				botonAgregarDosis.setBackground(new Color(205, 92, 92));
				botonAgregarDosis.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(index != -1)
						{
							paciente.getVacunas().get(index).getLista().add(fecha);
							Consultorio.getInstance().sustituirPaciente(posModificar, paciente);
							JOptionPane.showMessageDialog(null, "Dosis administrada correctamente");
							System.out.println("Paciente de la vacuna: " + paciente.getNombre());
							txtCedula.setText("");
							lblNombre.setText("");
							posModificar=-1;
							paciente = null;
							txtCedula.requestFocus();
							//listarVacunas();
							model = (DefaultTableModel) table.getModel();
							model.setRowCount(0);
							table.setModel(model);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Debe buscar un paciente y elegir una vacuna");
						}
						
					}
				});
				botonAgregarDosis.setEnabled(false);
				botonAgregarDosis.setActionCommand("Cancel");
				buttonPane.add(botonAgregarDosis);
			}
		}
		listarCombo();
	}
	
	public void listarCombo()
	{
		comboBox.addItem("Seleccionar");
		for (Vacuna v : Consultorio.getInstance().getVacunas()) {
			comboBox.addItem(v.getName());
		}
	}
	
	private void listarVacunas() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		if(paciente != null)
		{
			for (Vacuna v : paciente.getVacunas()) {
				fila[0] = v.getName();
				fila[1] = v.getTipo();
				fila[2] = v.getLista().size();
				
				fila[3] = v.getLista().size() > 0? v.getLista().get(0): "Nunca";
				model.addRow(fila);
		}
	}
	}
	
}
