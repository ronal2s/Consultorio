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

public class Vacunas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Vacunas dialog = new Vacunas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Vacunas() {
		setTitle("Crear Vacuna");
		setBounds(100, 100, 352, 458);
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
			JLabel label = new JLabel("Nombre:");
			label.setBounds(12, 266, 56, 16);
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(120, 263, 198, 22);
			contentPanel.add(textField);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(120, 303, 198, 22);
			contentPanel.add(comboBox);
		}
		{
			JLabel label = new JLabel("Tipo de Vacuna:");
			label.setBounds(12, 303, 102, 16);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Dosis:");
			label.setBounds(12, 344, 102, 16);
			contentPanel.add(label);
		}
		{
			JSpinner spinner = new JSpinner();
			spinner.setBounds(120, 341, 165, 22);
			contentPanel.add(spinner);
		}
		{
			JLabel label = new JLabel("cc");
			label.setBounds(292, 344, 26, 16);
			contentPanel.add(label);
		}
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

}
