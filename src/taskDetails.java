import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class taskDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDateChooser dateChooser;
	private JEditorPane editorPane;
	private JComboBox<String> comboBox;
	private DefaultTableModel TasktblModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboard parentDashboard = new dashboard(); 
					taskDetails frame = new taskDetails(parentDashboard, "", "", "", "", "");
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
	public taskDetails(dashboard parentDashboard, String task_Id, String classification, String task, String deadline, String status) {
		this.TasktblModel = TasktblModel;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 582, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int centerX = (screenSize.width - getWidth()) / 2;
	    int centerY = (screenSize.height - getHeight()) / 2;
	    setLocation(centerX, centerY);
	    getContentPane().setLayout(null);
	    
		
		JLabel lblNewLabel = new JLabel("Deadline:");
		lblNewLabel.setBounds(10, 10, 60, 13);
		contentPane.add(lblNewLabel);
		
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("MMMM d, yyyy");
		dateChooser.setBounds(10, 27, 267, 19);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_1 = new JLabel("Task:");
		lblNewLabel_1.setBounds(10, 56, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(10, 79, 539, 143);
		contentPane.add(editorPane);
		
		comboBox = new JComboBox();
		comboBox.addItem("Priority");
		comboBox.addItem("Regular");
		comboBox.addItem("Urgent");
		comboBox.addItem("Extra");
		comboBox.addItem("Important");
		comboBox.addItem("Low Priority");
		comboBox.setBounds(287, 25, 266, 21);
		contentPane.add(comboBox);
		
		JButton btnUpdateTask = new JButton("UPDATE TASK");
		btnUpdateTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Date selectedDate = dateChooser.getDate();
                String taskDescription = editorPane.getText();
                String selectedImportance = (String) comboBox.getSelectedItem();

                // Update the task details in the database
                updateTaskDetails(parentDashboard, task_Id, selectedDate, taskDescription, selectedImportance);
				
			}
		});
		btnUpdateTask.setBounds(20, 232, 149, 21);
		contentPane.add(btnUpdateTask);
		
		JLabel lblClassification = new JLabel("Importance");
		lblClassification.setBounds(287, 10, 113, 13);
		contentPane.add(lblClassification);
		
		JButton btnMarkAsDone = new JButton("MARK AS DONE");
		btnMarkAsDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				markTaskAsDone(parentDashboard, task_Id);
			}
		});
		btnMarkAsDone.setBounds(400, 232, 149, 21);
		contentPane.add(btnMarkAsDone);
		
		JButton btnMarkAsOngoing = new JButton("MARK AS ONGOING");
		btnMarkAsOngoing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				markTaskAsOngoing(parentDashboard, task_Id);
			}
		});
		btnMarkAsOngoing.setBounds(179, 232, 205, 21);
		contentPane.add(btnMarkAsOngoing);
		
		loadTaskData(task_Id);
	}
	
	private void loadTaskData(String task_Id)
	{
		 try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
		        // Prepare an SQL query to retrieve research data based on paper ID
		        String sql = "SELECT * FROM tasks WHERE id = ?";
		        PreparedStatement preparedStatement = connection.prepareStatement(sql);
		        preparedStatement.setString(1, task_Id);

		        // Execute the query and retrieve the result set
		        ResultSet resultSet = preparedStatement.executeQuery();

		        // Populate the date choosers with data from the result set
		        while (resultSet.next()) {
		            
		        	String deadlineStr = resultSet.getString("deadline");
	                String taskDescription = resultSet.getString("task");
	                String importance = resultSet.getString("classification");

	                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
	                Date deadlineDate = dateFormat.parse(deadlineStr);

	                dateChooser.setDate(deadlineDate);
	                editorPane.setText(taskDescription);
	                comboBox.setSelectedItem(importance);
		            

		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void updateTaskDetails(dashboard parentDashboard, String task_Id, Date deadlineDate, String taskDescription, String selectedImportance) {
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
	        	
	        	String task = editorPane.getText();
	        	Date selectedDate = dateChooser.getDate();
	            SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
	            String deadline = sdf.format(selectedDate);
	        	String importance = comboBox.getSelectedItem().toString();	      
	        	
	        	
	            String sql = "UPDATE tasks SET deadline = ?, task = ?, classification = ? WHERE id = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, deadline);
	            preparedStatement.setString(2, task);
	            preparedStatement.setString(3, importance);
	            preparedStatement.setString(4, task_Id);
	
	            int rowsUpdated = preparedStatement.executeUpdate();
	            if (rowsUpdated > 0) {
	            	JOptionPane.showMessageDialog(taskDetails.this, "Updated task successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	            	parentDashboard.refreshTaskTable();
	                dispose(); 
	            }
	            
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	   private void markTaskAsDone(dashboard parentDashboard, String task_Id) {
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
	            String sql = "UPDATE tasks SET status = 'Done' WHERE id = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, task_Id);

	            int rowsUpdated = preparedStatement.executeUpdate();
	            if (rowsUpdated > 0) {
	            	JOptionPane.showMessageDialog(taskDetails.this, "Task marked as Done successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	            	parentDashboard.refreshTaskTable();
	                dispose(); 
	               
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	   
	   private void markTaskAsOngoing(dashboard parentDashboard, String task_Id) {
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
	            String sql = "UPDATE tasks SET status = 'Ongoing' WHERE id = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, task_Id);

	            int rowsUpdated = preparedStatement.executeUpdate();
	            if (rowsUpdated > 0) {
	            	JOptionPane.showMessageDialog(taskDetails.this, "Task marked as Ongoing successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	            	parentDashboard.refreshTaskTable();
	                dispose();
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
