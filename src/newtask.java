import javax.swing.*;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.BorderLayout;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.awt.Label;
import java.awt.Button;

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
					 dashboard parentDashboard = new dashboard(); 
					newtask frame = new newtask(parentDashboard);
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
	public newtask(dashboard parentDashboard) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("NEW TASK");
		setBounds(100, 100, 612, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int centerX = (screenSize.width - getWidth()) / 2;
	    int centerY = (screenSize.height - getHeight()) / 2;
	    setLocation(centerX, centerY);
	    getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Deadline:");
		lblNewLabel.setBounds(29, 22, 60, 13);
		contentPane.add(lblNewLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("MMMM d, yyyy");
		dateChooser.setBounds(29, 39, 267, 19);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_1 = new JLabel("Task:");
		lblNewLabel_1.setBounds(29, 68, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(29, 91, 539, 143);
		contentPane.add(editorPane);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Priority");
		comboBox.addItem("Regular");
		comboBox.addItem("Urgent");
		comboBox.addItem("Extra");
		comboBox.addItem("Important");
		comboBox.addItem("Low Priority");
		comboBox.setBounds(306, 37, 266, 21);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("ADD TASK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String deadline = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
                String taskDescription = editorPane.getText();
                String classification = comboBox.getSelectedItem().toString();
                String status = "TO DO";

                // Connect to the database and insert data
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");
                    String query = "INSERT INTO tasks (deadline, task, classification, status) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, deadline);
                        preparedStatement.setString(2, taskDescription);
                        preparedStatement.setString(3, classification);
                        preparedStatement.setString(4,  status);
                        preparedStatement.executeUpdate();
                    }
                    connection.close();
                    parentDashboard.refreshTaskTable();
                    JOptionPane.showMessageDialog(contentPane, "Task added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
             
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(contentPane, "Failed to add task. Check console for details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
			}
		});
		btnNewButton.setBounds(461, 244, 106, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblClassification = new JLabel("Importance");
		lblClassification.setBounds(306, 22, 113, 13);
		contentPane.add(lblClassification);

	}
}
