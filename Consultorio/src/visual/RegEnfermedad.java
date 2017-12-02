package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logical.Consulta;
import logical.Consultorio;
import logical.Enfermedad;

import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

public class RegEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextPane txtCaracteristicas;
	private JComboBox cbxTipo;
	private JButton btnGuardar;
	private JButton btnSalir;
	private JTable table;
	private DefaultTableModel model;
	private Object[] fila;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEnfermedad dialog = new RegEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegEnfermedad() {
		setResizable(false);
		setForeground(new Color(176, 224, 230));
		setTitle("Registrar Enfermedad");
		setBounds(100, 100, 645, 497);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setForeground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Nombre:");
			label.setBounds(12, 161, 56, 16);
			contentPanel.add(label);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setEnabled(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(76, 158, 176, 22);
			contentPanel.add(txtNombre);
		}
		{
			JScrollPane scrollPaneListadoEnfer = new JScrollPane();
			scrollPaneListadoEnfer.setBounds(288, 153, 332, 249);
			contentPanel.add(scrollPaneListadoEnfer);
			{
				table = new JTable();
				String[] columnNames = {"Tipo","Nombre", "Características"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);

				scrollPaneListadoEnfer.setViewportView(table);
			}
		}
		{
			cbxTipo = new JComboBox();
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Aguda", "Cr\u00F3nica", "Espor\u00E1dica", "End\u00E9mica", "Epid\u00E9mica", "Infecciosa", "No Infecciosa"}));
			cbxTipo.setEnabled(false);
			cbxTipo.setBounds(76, 193, 198, 22);
			contentPanel.add(cbxTipo);
		}
		{ 
			JLabel label = new JLabel("Tipo:");
			label.setBounds(12, 193, 102, 16);
			contentPanel.add(label);
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(null, "Caracter\u00EDsticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 222, 264, 180);
			contentPanel.add(panel);
			{
				txtCaracteristicas = new JTextPane();
				txtCaracteristicas.setEnabled(false);
				txtCaracteristicas.setBounds(12, 24, 240, 143);
				panel.add(txtCaracteristicas);
			}
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(RegEnfermedad.class.getResource("/img/Banner.png")));
			label.setBounds(0, 0, 627, 143);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("");
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					txtCaracteristicas.setEnabled(true);
					txtNombre.setEnabled(true);
					cbxTipo.setEnabled(true);
					btnGuardar.setEnabled(true);
				}
			});
			label.setIcon(new ImageIcon(RegEnfermedad.class.getResource("/img/if_add_370092.png")));
			label.setBounds(258, 155, 30, 22);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(176, 196, 222));
			buttonPane.setForeground(new Color(25, 25, 112));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setBackground(new Color(102, 205, 170));
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = txtNombre.getText();
						String tipo = String.valueOf(cbxTipo.getSelectedIndex());
						String caracteristicas = txtCaracteristicas.getText();
						if(name.equalsIgnoreCase("") || tipo.equalsIgnoreCase("Seleccionar"))
						{
							JOptionPane.showMessageDialog(null, "Debe escribir un nombre y seleccionar un tipo");
							txtNombre.requestFocus();
						}
						else
						{
						Consultorio.getInstance().crearEnfermedad(name, tipo, caracteristicas);
						clean();
						JOptionPane.showMessageDialog(null, "Agregado correctamente");
						llenarTabla();
						}
					}

					
				});
				btnGuardar.setEnabled(false);
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.setBackground(new Color(205, 92, 92));
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		llenarTabla();
	}
	
	public void llenarTabla()
	{

		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<Enfermedad> enfermedades = Consultorio.getInstance().getEnfermedades();
		System.out.println("Tamaño enfermedad lista: " + enfermedades.size());
			for (Enfermedad c : enfermedades) {
				fila[0] = c.getTipo();
				fila[1] =  c.getNombre();
				fila[2] = c.getCaracteristicas();
				model.addRow(fila);
			}
		
	}
	
	private void clean() {
				
	}
	
	
}
