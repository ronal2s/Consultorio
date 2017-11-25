package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JCheckBox;

public class RegVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();

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
		setTitle("Registrar Vacuna");
		setBounds(100, 100, 351, 421);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 334, 244);
			contentPanel.add(scrollPane);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(118, 268, 198, 22);
			contentPanel.add(comboBox);
		}
		{
			JLabel label = new JLabel("Tipo de Vacuna:");
			label.setBounds(10, 268, 102, 16);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Dosis:");
			label.setBounds(10, 309, 102, 16);
			contentPanel.add(label);
		}
		{
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerDateModel(new Date(1511409600000L), null, null, Calendar.DAY_OF_YEAR));
			spinner.setBounds(118, 306, 155, 22);
			contentPanel.add(spinner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("OK");
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
