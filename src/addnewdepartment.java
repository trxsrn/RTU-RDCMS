import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class addnewdepartment extends JFrame {
	
	private dashboard parentDashboard;

	private connection dbConnection;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox college_comboBox;
	private JTextField nametxtfld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboard parentDashboard = new dashboard();
					addnewdepartment frame = new addnewdepartment(parentDashboard);
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
	public addnewdepartment(dashboard parentDashboard) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 585, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADD NEW DEPARTMENT");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(103, 25, 332, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("COLLEGE");
		lblNewLabel_1.setBounds(33, 94, 132, 13);
		contentPane.add(lblNewLabel_1);
		
		college_comboBox = new JComboBox<>();
		college_comboBox.setBounds(193, 86, 340, 32);
		contentPane.add(college_comboBox);
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
            // Query the database to fetch discipline data
            String query = "SELECT abbreviation FROM college";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Populate the dropdown with the retrieved data
            while (resultSet.next()) {
                String disciplineName = resultSet.getString("abbreviation");
                college_comboBox.addItem(disciplineName);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
		
		JLabel lblNewLabel_1_1 = new JLabel("DEPARTMENT NAME");
		lblNewLabel_1_1.setBounds(33, 144, 132, 13);
		contentPane.add(lblNewLabel_1_1);
		
		nametxtfld = new JTextField();
		nametxtfld.setColumns(10);
		nametxtfld.setBounds(193, 136, 340, 32);
		contentPane.add(nametxtfld);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					 
				 	String college = college_comboBox.getSelectedItem().toString();
                    String name = nametxtfld.getText();
                    
                    if (name.equals(""))
                    {
                    	JOptionPane.showMessageDialog(contentPane, "Insert College Name", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                   
                    // Check if the name already exists
                    if (isNameExists(name)) {
                        // Display an error message or handle the name existence case
                        JOptionPane.showMessageDialog(contentPane, "Department already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Exit the method without adding the author
                    }

                    // Get a database connection
                    Connection connection = dbConnection.getConnection();

                    // Create an SQL INSERT statement
                    String insertSql = "INSERT INTO department (college, name)" +
                            "VALUES (?, ?)";

                    // Prepare the SQL statement with parameters
                    PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
                    preparedStatement.setString(1, college);
                    preparedStatement.setString(2, name);

                    // Execute the SQL INSERT statement
                    preparedStatement.executeUpdate();
                    
                 // After successfully inserting data into the database
              
                    JOptionPane.showMessageDialog(null, "Addition was successful!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    parentDashboard.refreshDepartmentTable();
                    dispose();

                    // Close the database connection
                    dbConnection.closeConnection(connection);

                    // Optionally, show a success message or perform other actions
                    // You can also clear the form fields here
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle any database-related errors here
                }
			}
		});
		btnNewButton.setBounds(448, 200, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(33, 200, 85, 21);
		contentPane.add(btnCancel);
	}
	
	private boolean isNameExists(String name) throws SQLException {
	    Connection connection = dbConnection.getConnection();
	    String checkQuery = "SELECT name FROM department WHERE name = ?";
	    PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
	    checkStatement.setString(1, name);
	    ResultSet resultSet = checkStatement.executeQuery();
	    boolean exists = resultSet.next(); // If the result set has a row, the name exists
	    resultSet.close();
	    checkStatement.close();
	    connection.close();
	    return exists;
	}
}
