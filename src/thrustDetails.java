import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class thrustDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField abbtxtfld;
	private JTextField nametxtfld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					thrustDetails frame = new thrustDetails("AAA", "University Thrust", "Description Here");
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
	public thrustDetails(String abb, String name, String desc) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 585, 64);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("ADD NEW RESEARCH THRUST");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(134, 26, 332, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ABBREVIATION");
		lblNewLabel_1.setBounds(33, 94, 132, 13);
		contentPane.add(lblNewLabel_1);
		
		abbtxtfld = new JTextField(abb);
		abbtxtfld.setEnabled(false);
		abbtxtfld.setEditable(false);
		abbtxtfld.setColumns(10);
		abbtxtfld.setBounds(151, 85, 340, 32);
		contentPane.add(abbtxtfld);
		
		JLabel lblNewLabel_1_1 = new JLabel("NAME");
		lblNewLabel_1_1.setBounds(33, 144, 132, 13);
		contentPane.add(lblNewLabel_1_1);
		
		nametxtfld = new JTextField(name);
		nametxtfld.setEnabled(false);
		nametxtfld.setEditable(false);
		nametxtfld.setColumns(10);
		nametxtfld.setBounds(151, 135, 340, 32);
		contentPane.add(nametxtfld);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("DESCRIPTION");
		lblNewLabel_1_1_1.setBounds(33, 199, 132, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		JTextField desctxtfld = new JTextField(desc);
		desctxtfld.setEnabled(false);
		desctxtfld.setEditable(false);
		desctxtfld.setBounds(151, 190, 340, 86);
		contentPane.add(desctxtfld);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(33, 309, 85, 21);
		contentPane.add(btnCancel);
		
		JButton btnNewButton = new JButton("EDIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				abbtxtfld.setEditable(true);
				nametxtfld.setEditable(true);
				desctxtfld.setEditable(true);
				
				abbtxtfld.setEnabled(true);
				nametxtfld.setEnabled(true);
				desctxtfld.setEnabled(true);
				
				btnNewButton.setText("SAVE");
			}
		});
		btnNewButton.setBounds(406, 309, 85, 21);
		contentPane.add(btnNewButton);
	}

}
