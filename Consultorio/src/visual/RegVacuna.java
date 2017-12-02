package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jdk.nashorn.internal.scripts.JO;
import logical.Consultorio;
import logical.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTable table;
	private JComboBox cbxTipo;
	private JSpinner spnDosis;
	private JButton btnGuardar;
	private JButton btnSalir;
	private DefaultTableModel model;
	private Object[] fila;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegVacuna dialog = new RegVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegVacuna() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegVacuna.class.getResource("/img/if_7_375260.png")));
		setTitle("Registrar Vacuna");
		setBounds(100, 100, 391, 584);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(176, 196, 222));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 137, 373, 244);
			contentPanel.add(scrollPane);
			
			table = new JTable();
			String[] columnNames = {"Nombre","Tipo"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);
			table.setModel(model);
			scrollPane.setViewportView(table);
		} 
		{
			cbxTipo = new JComboBox();
			cbxTipo.setEnabled(false);
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "VIVA ATENUADA", "INACTIVADA", "SUBUNIDAD", "TOXOIDE", "COMBINADA", "ADN", "VECTOR RECOMBINANTES"}));
			cbxTipo.setBounds(120, 429, 198, 22);
			contentPanel.add(cbxTipo);
		}
		{
			JLabel label = new JLabel("Tipo de Vacuna:");
			label.setBounds(12, 429, 102, 16);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Dosis:");
			label.setVisible(false);
			label.setBounds(12, 470, 102, 16);
			contentPanel.add(label);
		}
		{
			spnDosis = new JSpinner();
			spnDosis.setVisible(false);
			spnDosis.setEnabled(false);
			spnDosis.setModel(new SpinnerDateModel(new Date(1511409600000L), null, null, Calendar.DAY_OF_YEAR));
			spnDosis.setBounds(120, 467, 155, 22);
			contentPanel.add(spnDosis);
		}
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 392, 56, 16);
		contentPanel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(118, 394, 200, 22);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblAgregarVacuna = new JLabel("");
		lblAgregarVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtNombre.setEnabled(true);
				cbxTipo.setEnabled(true);
				spnDosis.setEnabled(true);
				btnGuardar.setEnabled(true);
			}
		});
		lblAgregarVacuna.setIcon(new ImageIcon(RegVacuna.class.getResource("/img/if_add_370092.png")));
		lblAgregarVacuna.setBounds(333, 392, 28, 24);
		contentPanel.add(lblAgregarVacuna);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(RegVacuna.class.getResource("/img/Banner.png")));
		label_1.setBounds(0, 0, 373, 137);
		contentPanel.add(label_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(176, 196, 222));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setBackground(new Color(102, 205, 170));
				btnGuardar.setEnabled(false);
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cbxTipo.getSelectedIndex() != 0 || txtNombre.getText().equalsIgnoreCase(""))
						{
						String name = txtNombre.getText();
						String tipo = cbxTipo.getSelectedItem().toString();
						
						Consultorio.getInstance().crearVacuna(name, tipo);
						JOptionPane.showMessageDialog(null, "Agregado correctamente");
						listarVacunas();
						
						clean();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Debe seleccionar un nombre y un tipo");
						}
					}

					
				});
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.setBackground(new Color(205, 92, 92));
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		 
		listarVacunas();
	}
	private void listarVacunas() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
				for (Vacuna v : Consultorio.getInstance().getVacunas()) {
					fila[0] = v.getName();
					fila[1] = v.getTipo();
					//fila[2] = v.getDosis();
					model.addRow(fila);
				}
			
	}

	private void clean() {
		txtNombre.setText("");
		cbxTipo.setSelectedIndex(0);
		//spnDosis.
	}
	
	
}
