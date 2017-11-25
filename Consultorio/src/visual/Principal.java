package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.Consultorio;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					frame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        if (JOptionPane.showConfirmDialog(frame, 
					            "¿Seguro que desea salir?", "Salir", 
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					        	try {
								//System.out.print("ss");
					        			Consultorio.getInstance().guardarDatos();
					        		//	Banco.getInstance().imprimirRevision("402");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					            System.exit(0);
					        }
					    }
					});	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 435);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPacientes = new JMenu("Pacientes");
		menuBar.add(mnPacientes);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listar listar = new Listar("Pacientes");
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnPacientes.add(mntmListar);
		
		JMenuItem mntmAgregar = new JMenuItem("Agregar");
		mntmAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registro registro = new Registro("RegistrarPaciente");
				registro.setModal(true);
				registro.setVisible(true);
			}
		});
		mnPacientes.add(mntmAgregar);
		
		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro modificar = new Registro("ModificarPaciente");
				modificar.setModal(true);
				modificar.setVisible(true);
			}
		});
		mnPacientes.add(mntmModificar);
		
		JMenu mnProfesionales = new JMenu("Profesionales");
		menuBar.add(mnProfesionales);
		
		JMenuItem mntmListar_1 = new JMenuItem("Listar");
		mnProfesionales.add(mntmListar_1);
		
		JMenuItem mntmAgregar_1 = new JMenuItem("Agregar");
		mntmAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registro registro = new Registro("RegistrarProfesional");
				registro.setModal(true);
				registro.setVisible(true);

			}
		});
		mnProfesionales.add(mntmAgregar_1);
		
		JMenuItem mntmModificar_1 = new JMenuItem("Modificar");
		mnProfesionales.add(mntmModificar_1);
		
		JMenu mnEmpleados_1 = new JMenu("Empleados");
		menuBar.add(mnEmpleados_1);
		
		JMenuItem menuItem_5 = new JMenuItem("Listar");
		mnEmpleados_1.add(menuItem_5);
		
		JMenuItem menuItem_6 = new JMenuItem("Agregar");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro registro = new Registro("RegistrarEmpleado");
				registro.setModal(true);
				registro.setVisible(true);

			}
		});
		mnEmpleados_1.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("Modificar");
		mnEmpleados_1.add(menuItem_7);
		
		JMenu mnEmpleados = new JMenu("Usuarios");
		menuBar.add(mnEmpleados);
		
		JMenuItem menuItem_2 = new JMenuItem("Listar");
		mnEmpleados.add(menuItem_2);
		
		JMenuItem menuItem = new JMenuItem("Modificar");
		mnEmpleados.add(menuItem);
		
		JMenu mnAgenda = new JMenu("Agenda");
		menuBar.add(mnAgenda);
		
		JMenuItem mntmListar_2 = new JMenuItem("Listar");
		mnAgenda.add(mntmListar_2);
		
		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);
		
		JMenuItem mntmCrear = new JMenuItem("Crear");
		mnConsultas.add(mntmCrear);
		
		JMenuItem mntmModificar_2 = new JMenuItem("Modificar");
		mnConsultas.add(mntmModificar_2);
		
		JMenu mnCitas = new JMenu("Citas");
		menuBar.add(mnCitas);
		
		JMenuItem menuItem_3 = new JMenuItem("Crear");
		mnCitas.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("Modificar");
		mnCitas.add(menuItem_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		try {
			Consultorio.getInstance().cargarDatos();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
