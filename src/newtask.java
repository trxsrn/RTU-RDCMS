import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class newtask extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newtask frame = new newtask();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public newtask() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("NEW TASK");
		setBounds(100, 100, 450, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Deadline:");
		lblNewLabel.setBounds(29, 35, 60, 13);
		contentPane.add(lblNewLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(29, 51, 364, 19);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_1 = new JLabel("Task:");
		lblNewLabel_1.setBounds(29, 80, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(29, 103, 364, 131);
		contentPane.add(editorPane);
		
		JButton btnNewButton = new JButton("ADD TASK");
		btnNewButton.setBounds(162, 249, 85, 21);
		contentPane.add(btnNewButton);
	}
}
