import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class confirmcredentials extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					confirmcredentials frame = new confirmcredentials();
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
	public confirmcredentials() {
		setTitle("ENTER ADMIN PASSWORD ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(86, 93, 210, 27);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("REMOVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String password = passwordField.getText().toString();
				
				if (password.equals("admin@123"))
				{
					JOptionPane.showMessageDialog(null, "Remove Success!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else if (password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Kindly input a password ", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Password ", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				}
		});
		btnNewButton.setBounds(132, 140, 119, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Enter the password to confirm removal.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(69, 56, 246, 13);
		contentPane.add(lblNewLabel);
	}
}
