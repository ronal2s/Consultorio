package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Label;
import com.toedter.calendar.JDateChooser;

import logical.Cita;
import logical.Consultorio;
import logical.Paciente;
import logical.Profesional;

import javax.swing.JSpinner;
import java.awt.TextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class RegCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPaciente;
	private JTextField txtDescripcion;
	private JTable table;
	private JTextField txtDuracion;
	private JLabel lblNombrePaciente;
	private Paciente paciente;
	private JComboBox comboProfesional;
	private JComboBox comboSala;
	private JDateChooser dateFecha;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private TextArea txtNota;
	private DefaultTableModel model;
	private Object[] fila;
	private JTextField txtHora;
	private int posCita = -1;
	private JButton okButton;
	private Calendar c = Calendar.getInstance();
	private JComboBox comboTipoCita;
	private Cita cita = null;


	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			RegCita dialog = new RegCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public RegCita(int posCita) {
		setResizable(false);
		//PosCita será para llamar a esta ventana desde la ventana de Listar, para así saber la posición de la lista de citas a listar
		this.posCita = posCita;
		setBounds(100, 100, 931, 592);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(176, 196, 222));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(176, 196, 222));
			panel.setBounds(0, 0, 915, 468);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblPaciente = new JLabel("Paciente:");
			lblPaciente.setBounds(10, 15, 62, 14);
			panel.add(lblPaciente);
			
			txtPaciente = new JTextField();
			txtPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Evento enter
					int posPaciente = Consultorio.getInstance().buscarPaciente(txtPaciente.getText());
					if(posPaciente != -1)
					{
						paciente = Consultorio.getInstance().getPacientes().get(posPaciente);
						lblNombrePaciente.setText(paciente.getNombre() + " " + paciente.getApellidos());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Este paciente no parece estar registrado");
					}
				}
			});
			txtPaciente.setToolTipText("Cedula del paciente");
			txtPaciente.setBounds(70, 8, 141, 28);
			panel.add(txtPaciente);
			txtPaciente.setColumns(10);
			
			JLabel lblDescripcinDeLa = new JLabel("Descripci\u00F3n de la cita:");
			lblDescripcinDeLa.setBounds(10, 58, 155, 14);
			panel.add(lblDescripcinDeLa);
			
			txtDescripcion = new JTextField();
			txtDescripcion.setColumns(10);
			txtDescripcion.setBounds(10, 83, 192, 28);
			panel.add(txtDescripcion);
			
			JLabel lblProfesional = new JLabel("Profesional:");
			lblProfesional.setBounds(10, 123, 116, 14);
			panel.add(lblProfesional);
			
			comboProfesional = new JComboBox();
			comboProfesional.setBounds(10, 148, 192, 28);
			panel.add(comboProfesional);
			
			JLabel lblSala = new JLabel("Sala:");
			lblSala.setBounds(217, 58, 116, 14);
			panel.add(lblSala);
			
			comboSala = new JComboBox();
			comboSala.setModel(new DefaultComboBoxModel(new String[] {"Sala 1", "Sala 2", "Sala 3", "Sala 4", "Sala 5"}));
			comboSala.setBounds(217, 83, 187, 28);
			panel.add(comboSala);
			
			JLabel lblTipoDeCita = new JLabel("Tipo de cita:");
			lblTipoDeCita.setBounds(217, 123, 116, 14);
			panel.add(lblTipoDeCita);
			
			dateFecha = new JDateChooser(c.getTime());
			dateFecha.setBounds(10, 205, 192, 28);
			panel.add(dateFecha);
			dateFecha.setDate(dateFecha.getDate());
			
			txtNota = new TextArea();
			txtNota.setBounds(10, 265, 394, 193);
			panel.add(txtNota);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(414, 83, 491, 375);
			panel.add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
			String[] columnNames = {"#","Fecha","Hora","Descripción","Profesional", "Paciente"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);
			table.setModel(model);
			txtDuracion = new JTextField();
			txtDuracion.setVisible(false);
			txtDuracion.setColumns(10);
			txtDuracion.setBounds(217, 205, 77, 28);
			panel.add(txtDuracion);
			
			lblNombrePaciente = new JLabel("");
			lblNombrePaciente.setBounds(220, 10, 491, 28);
			panel.add(lblNombrePaciente);
			
			txtHora = new JTextField();
			txtHora.setColumns(10);
			txtHora.setBounds(217, 205, 77, 28);
			panel.add(txtHora);
			
			JLabel lblCitasRegistradasPor = new JLabel("Citas registradas por el momento");
			lblCitasRegistradasPor.setHorizontalAlignment(SwingConstants.CENTER);
			lblCitasRegistradasPor.setBounds(414, 58, 491, 14);
			panel.add(lblCitasRegistradasPor);
			
			comboTipoCita = new JComboBox();
			comboTipoCita.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Revisi\u00F3n", "Seguimiento"}));
			comboTipoCita.setBounds(217, 151, 187, 28);
			panel.add(comboTipoCita);
			
			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(10, 180, 69, 20);
			panel.add(lblFecha);
			
			JLabel lblNota = new JLabel("Nota:");
			lblNota.setBounds(10, 236, 69, 20);
			panel.add(lblNota);
			
			JLabel lblHora = new JLabel("Hora:");
			lblHora.setBounds(217, 180, 69, 20);
			panel.add(lblHora);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(176, 196, 222));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Registrar");
				okButton.setBackground(new Color(102, 205, 170));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Evento agregar
						if(comboProfesional.getSelectedIndex() != 0 && txtHora.getText().length() >0 && comboTipoCita.getSelectedIndex() != 0)
						{
						
						try
						{
						//Crear cita
						String descripcion = "", sala="", tipo="", fecha="", duracion="", hora="",nota="";
						String[] partes = comboProfesional.getSelectedItem().toString().split("-");
						String cedula = partes[1]; System.out.println("Cedula doctor: " + cedula);
						Profesional doctor = null;//En el combo box vamos a cargar los doctores con el formato [NOMBRE + APELLIDO - CEDULA]
						//Cita cita = null;
						int pos = -1;
						pos = Consultorio.getInstance().buscarProfesional(cedula);
						doctor = Consultorio.getInstance().getProfesionales().get(pos); System.out.println("Doctor: " + doctor.getNombre());
						descripcion = txtDescripcion.getText();
						sala = comboSala.getSelectedItem().toString();
						tipo = comboTipoCita.getSelectedItem().toString();
						fecha = df.format(dateFecha.getDate());
						duracion = txtDuracion.getText();
						hora = txtHora.getText();
						nota = txtNota.getText();
						if(posCita == -1)
						{
							
							cita = new Cita(paciente, descripcion, sala, doctor, tipo, fecha, duracion, hora,nota);
							ArrayList<Cita> citas = doctor.getCitas();
							citas.add(cita);
							doctor.setCitas(citas);
							Consultorio.getInstance().sustituirProfesional(pos, doctor);
							Consultorio.getInstance().getCitas().add(cita);
							//Agregarle la cita al paciuente
							citas = paciente.getCitas();
							citas.add(cita);
							paciente.setCitas(citas);
							Consultorio.getInstance().sustituirPaciente(pos, paciente);
							
							System.out.println("Consultorio tiene: " + Consultorio.getInstance().getCitas().size() + " citas");
							listarCitas();
							limpiarCampos();
							JOptionPane.showMessageDialog(null, "Agregado correctamente");
						}
						else
						{
							//Para modificar
							//paciente = cita.getPaciente();
							cita = new Cita(cita.getPaciente(), descripcion, sala, doctor, tipo, fecha, duracion, hora,nota);
							Consultorio.getInstance().sustituirCita(posCita, cita);
							listarCitas();
							limpiarCampos();
							JOptionPane.showMessageDialog(null, "Modificado correctamente");
							dispose();
							
						}
					}catch(Exception e2)
						{
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error, revise los datos");
						System.out.println("Error es: " + e2.getCause());
						e2.printStackTrace();
						}
						}
					
					else
					{
						JOptionPane.showMessageDialog(null, "Asegurese de llenar los datos correctamente");
					}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBackground(new Color(205, 92, 92));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		actualizarProfesionales();
		listarCitas();
		if(posCita != -1)
		{
			rellenarCita();
			okButton.setText("Modificar");
		}
	}
	
	public void actualizarProfesionales()
	{
		comboProfesional.addItem("Seleccionar");
		for (Profesional p : Consultorio.getInstance().getProfesionales()) {
			comboProfesional.addItem(p.getNombre() + " " + p.getApellidos() + "-" + p.getCedula());
		}
	}
	private void rellenarCita()
	{
		cita = Consultorio.getInstance().getCitas().get(posCita);
		txtPaciente.setText(cita.getPaciente().getCedula());
		txtPaciente.setEnabled(false);
		lblNombrePaciente.setText(cita.getPaciente().getNombre() +" "+ cita.getPaciente().getApellidos());
		txtDescripcion.setText(cita.getDescripcion());
		txtDuracion.setText(cita.getDuracion());
		txtHora.setText(cita.getHora());
		txtNota.setText(cita.getNota());
		comboTipoCita.setSelectedItem(cita.getTipo());
		comboProfesional.setSelectedItem(cita.getDoctor().getNombre() + " " + cita.getDoctor().getApellidos() + "-" + cita.getDoctor().getCedula());
	}
	private void limpiarCampos() {
		// TODO Auto-generated method stub
		txtPaciente.setText("");
		txtDuracion.setText("");
		txtDescripcion.setText("");
		txtHora.setText("");
		txtNota.setText("");
		comboTipoCita.setSelectedIndex(0);;
		lblNombrePaciente.setText("");
		txtPaciente.requestFocus();
	}
	public void listarCitas() 
	{
		int n =0;
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<Cita> citas = Consultorio.getInstance().getCitas();;
		
		System.out.println("Tamaño citas lista: " + citas.size());
			for (Cita cita : citas) {
				fila[0] = n+1;
				fila[1] =  cita.getFecha();
				fila[2] =  cita.getHora();
				fila[3] =  cita.getDescripcion();
				fila[4] =  cita.getDoctor().getNombre() + " " + cita.getDoctor().getApellidos();
				fila[5] =  cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellidos();
				n++;
				model.addRow(fila);
			}
		
	}
}
