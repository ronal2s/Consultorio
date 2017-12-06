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
		setBounds(100, 100, 411, 456);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(176, 196, 222));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(15, 16, 373, 244);
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
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Varicela ", "Fiebre amarilla", "Polio oral", "Sarampi\u00F3n", "Rub\u00E9ola", "Parotiditis", "Rabia", "Hepatitis A", "Hepatitis B"}));
			cbxTipo.setBounds(93, 314, 202, 28);
			contentPanel.add(cbxTipo);
		}
		{
			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(15, 320, 63, 16);
			contentPanel.add(lblTipo);
		}
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(15, 276, 86, 16);
		contentPanel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(95, 276, 200, 28);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblAgregarVacuna = new JLabel("");
		lblAgregarVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtNombre.setEnabled(true);
				cbxTipo.setEnabled(true);
				//spnDosis.setEnabled(true);
				btnGuardar.setEnabled(true);
			}
		});
		lblAgregarVacuna.setIcon(new ImageIcon(RegVacuna.class.getResource("/img/if_add_370092.png")));
		lblAgregarVacuna.setBounds(310, 280, 28, 24);
		contentPanel.add(lblAgregarVacuna);
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
						if(cbxTipo.getSelectedIndex() != 0)
						{
							if(txtNombre.getText().length() >0){
								String name = txtNombre.getText();
								String tipo = cbxTipo.getSelectedItem().toString();
								
								Consultorio.getInstance().crearVacuna(name, tipo);
								JOptionPane.showMessageDialog(null, "Agregado correctamente");
								listarVacunas();
								txtNombre.requestFocus();
								clean();
							}
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
