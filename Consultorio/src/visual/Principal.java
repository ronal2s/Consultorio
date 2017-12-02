package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import logical.Consultorio;
import logical.Paciente;
import logical.Profesional;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;

public class Principal extends JFrame {

	private JPanel contentPane;
    JPanel panel = new JPanel();;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 


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
					            "�Seguro que desea salir?", "Salir", 
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

		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				//RECORDAR ARREGLAR ESTO
				//init();
				System.out.println("Entraste a la ventana de nuevo");
				grafica1();
				grafica2();
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1088, 510);
		setSize(screenSize.width, screenSize.height - 30);

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
		mntmListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listar listar = new Listar("Profesionales");
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
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
		mntmModificar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro registro = new Registro("ModificarProfesional");
				registro.setModal(true);
				registro.setVisible(true);
			}
		});
		mnProfesionales.add(mntmModificar_1);
		
		JMenu mnEmpleados_1 = new JMenu("Empleados");
		menuBar.add(mnEmpleados_1);
		
		JMenuItem menuItem_5 = new JMenuItem("Listar");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listar registro = new Listar("Empleados");
				registro.setModal(true);
				registro.setVisible(true);
			}
		});
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
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro registro = new Registro("ModificarEmpleado");
				registro.setModal(true);
				registro.setVisible(true);	
			}
		});
		mnEmpleados_1.add(menuItem_7);
		
		JMenu mnAgenda = new JMenu("Agenda");
		menuBar.add(mnAgenda);
		
		JMenuItem mntmListar_2 = new JMenuItem("Listar");
		mntmListar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listar listar = new Listar("Agenda");
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnAgenda.add(mntmListar_2);
		
		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);
		
		JMenuItem mntmCrear = new JMenuItem("Crear");
		mntmCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Registrar consulta
				RegConsulta consulta = new RegConsulta();
				consulta.setModal(true);
				consulta.setVisible(true);
			}
		});
		mnConsultas.add(mntmCrear);
		
		JMenu mnCitas = new JMenu("Citas");
		menuBar.add(mnCitas);
		
		JMenuItem menuItem_3 = new JMenuItem("Crear");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCita cita = new RegCita(-1);
				cita.setModal(true);
				cita.setVisible(true);
			}
		});
		mnCitas.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("Modificar");
		menuItem_4.setVisible(false);
		mnCitas.add(menuItem_4);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenuItem mntmRegistrarEnfermedad = new JMenuItem("Enfermedades");
		mntmRegistrarEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEnfermedad f = new RegEnfermedad();
				f.setModal(true);
				f.setVisible(true);
			}
		});
		mnOpciones.add(mntmRegistrarEnfermedad);
		
		JMenuItem mntmRegistrarVacuna = new JMenuItem("Vacunas");
		mntmRegistrarVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVacuna vacuna = new RegVacuna();
				vacuna.setModal(true);
				vacuna.setVisible(true);
			}
		});
		mnOpciones.add(mntmRegistrarVacuna);
		
		JMenuItem mntmAsignarVacunasdosis = new JMenuItem("Asignar Vacunas/D\u00F3sis");
		mntmAsignarVacunasdosis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarVacuna vacuna = new AgregarVacuna();
				vacuna.setModal(true);
				vacuna.setVisible(true);
			}
		});
		mnOpciones.add(mntmAsignarVacunasdosis);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		try {
			Consultorio.getInstance().cargarDatos();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		grafica1();
		grafica2();
	}
	
    private void grafica1() {
        //getContentPane().add(panel);
        // Fuente de Datos
        DefaultPieDataset data = new DefaultPieDataset();
        /*data.setValue("C", 40);
        data.setValue("Java", 45);*/
        ArrayList<Paciente> pacientes = Consultorio.getInstance().getPacientes();
        int h=0, m=0;
        
        for (int i = 0; i < pacientes.size(); i++) {
			if(pacientes.get(i).getSexo() == 'M')
			{
				h++;
				data.setValue("Hombres", h);
			}
			else
			{
				m++;
				data.setValue("Mujeres", m);
			}
		}
        //data.setValue("Python", 15);
 
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
         "Poblaci�n", 
         data, 
         true, 
         true, 
         false);
        panel.setLayout(null);
        contentPane.setLayout(new BorderLayout(0, 0));
        chart.setBackgroundPaint(Color.gray);

        // Crear el Panel del Grafico con ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(900, 47, screenSize.width/2, screenSize.height/2);
        chartPanel.setMaximumDrawHeight(500);
        getContentPane().add(chartPanel, BorderLayout.EAST);
    }
    
    private void grafica2() {
       // getContentPane().add(panel);
        // Fuente de Datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Profesional p : Consultorio.getInstance().getProfesionales()) {
			dataset.setValue(p.getEdad(),"Cargo", p.getEspecialidad());
		}
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createBarChart3D
        ("Tipo de Profesionales","Profesiones", "Edad", 
        dataset, PlotOrientation.VERTICAL, true,true, false);
        chart.setBackgroundPaint(Color.gray);
        chart.getTitle().setPaint(Color.black); 
        CategoryPlot p = chart.getCategoryPlot(); 
        p.setRangeGridlinePaint(Color.red);
        panel.setLayout(null);
        // Mostrar Grafico
        ChartPanel chartPanel2 = new ChartPanel(chart);
        chartPanel2.setBackground(Color.RED);
       getContentPane().add(chartPanel2, BorderLayout.WEST);
        //panel.add(chartPanel2);
    }

}
