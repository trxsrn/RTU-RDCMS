import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.ImageIcon;

public class addnewfaculty extends JFrame {
	
	private connection dbConnection;
	private JPanel contentPane;
	private JLabel idtxtfld;
	private JTextField nametxtfld;
	private dashboard parentDashboard;
	private int authorIDCounter = 1; 
	private JComboBox<String> college_comboBox; 
	private JComboBox department_comboBox;
	private JTextField orgtxtfld;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                // Create a dashboard object (you need to adjust this based on your code)
	                dashboard parentDashboard = new dashboard(); // Replace 'dashboard' with your actual class name

	                // Pass the dashboard object as an argument
	                addnewfaculty frame = new addnewfaculty(parentDashboard);
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
	public addnewfaculty(dashboard parentDashboard) {
		setTitle("ADD NEW FACULTY");
		this.parentDashboard = parentDashboard;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 528, 717);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("FACULTY ID:");
		lblNewLabel_1.setBounds(47, 275, 101, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("NAME");
		lblNewLabel_1_1.setBounds(47, 309, 101, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("COLLEGE");
		lblNewLabel_2.setBounds(47, 398, 101, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("DEPARTMENT");
		lblNewLabel_2_1.setBounds(47, 444, 101, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("AFFILIATION");
		lblNewLabel_2_1_1.setBounds(47, 356, 154, 13);
		contentPane.add(lblNewLabel_2_1_1);
		
		idtxtfld = new JLabel();
		idtxtfld.setEnabled(false);
		idtxtfld.setBounds(196, 278, 252, 19);
		contentPane.add(idtxtfld);
		idtxtfld.setText(getNextAuthorID());
		
		nametxtfld = new JTextField();
		nametxtfld.setColumns(10);
		nametxtfld.setBounds(196, 312, 252, 19);
		contentPane.add(nametxtfld);
		
		
		
		college_comboBox = new JComboBox<>();
		college_comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	Object selectedAffiliationObj = college_comboBox.getSelectedItem();
				
		        if (selectedAffiliationObj != null) {
		        	String selectedCollege = college_comboBox.getSelectedItem().toString();

			        // Clear existing items in the department_comboBox
			        department_comboBox.removeAllItems();

			        // Fetch and populate the department_comboBox based on the selected college
			        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
			            String query = "SELECT name FROM department WHERE college = ?";
			            PreparedStatement statement = connection.prepareStatement(query);
			            statement.setString(1, selectedCollege);
			            ResultSet resultSet = statement.executeQuery();

			            // Populate the department_comboBox with the retrieved department names
			            while (resultSet.next()) {
			                String departmentName = resultSet.getString("name");
			                department_comboBox.addItem(departmentName);
			            }
			        } catch (SQLException ex) {
			            ex.printStackTrace();
			        }
		        }
		        
		        else
		        {
//		        	JOptionPane.showMessageDialog(contentPane, "Please select an college.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		        // Get the selected college from the college_comboBox
		        
		    }
		});

		college_comboBox.setBounds(196, 394, 252, 21);
		contentPane.add(college_comboBox);
		
		
		 orgtxtfld = new JTextField();
	     orgtxtfld.setColumns(10);
	     orgtxtfld.setBounds(196, 394, 252, 21);
		
		department_comboBox = new JComboBox<>();
		department_comboBox.setBounds(196, 440, 252, 21);
		contentPane.add(department_comboBox);
		
		JComboBox<String> affiliation_comboBox = new JComboBox<String>();
		affiliation_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				college_comboBox.removeAllItems();
				Object selectedAffiliationObj = affiliation_comboBox.getSelectedItem();

				contentPane.remove(college_comboBox);
		        contentPane.remove(orgtxtfld);
				
		        if (selectedAffiliationObj != null) {
		        	
		        	String affiliation = selectedAffiliationObj.toString();
		        	
					if("Research Thrust".equals(affiliation))
					{
						contentPane.add(college_comboBox);
						try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
				            // Query the database to fetch discipline data
				            String query = "SELECT abbreviation FROM research_thrust";
				            PreparedStatement statement = connection.prepareStatement(query);
				            ResultSet resultSet = statement.executeQuery();
	
				            // Populate the dropdown with the retrieved data
				            while (resultSet.next()) {
				                String disciplineName = resultSet.getString("abbreviation");
				                college_comboBox.removeAllItems();
				                college_comboBox.addItem(disciplineName);
				                
				            }
				        } catch (SQLException e1) {
				            e1.printStackTrace();
				        }
						lblNewLabel_2.setText("NAME");
						department_comboBox.setEnabled(false);
						department_comboBox.setForeground(Color.LIGHT_GRAY);
						
						
					}
					else if("Organization".equals(affiliation))
					{
						 contentPane.add(orgtxtfld);
						 department_comboBox.setEnabled(false);
					}
					else if("Freelancer".equals(affiliation))
					{
						contentPane.add(orgtxtfld);
						college_comboBox.setEnabled(false);
						orgtxtfld.setEnabled(false);
						department_comboBox.setEnabled(false);
						department_comboBox.setForeground(Color.LIGHT_GRAY);
					}
					else
					{
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
						lblNewLabel_2.setText("COLLEGE");
						department_comboBox.setEnabled(true);
						department_comboBox.setForeground(Color.BLACK);
						college_comboBox.setEnabled(true);
					}
		        }
		        else
		        {
		        	JOptionPane.showMessageDialog(contentPane, "Please select an affiliation.", "Error", JOptionPane.ERROR_MESSAGE);

		        }
				
				contentPane.revalidate();
                contentPane.repaint();
			}
		});
		
		affiliation_comboBox.addItem("University");
		affiliation_comboBox.addItem("Research Thrust");
		affiliation_comboBox.addItem("Organization");
		affiliation_comboBox.addItem("Freelancer");
		
		affiliation_comboBox.setBounds(196, 352, 252, 21);
		contentPane.add(affiliation_comboBox);
	
		JButton btnNewButton = new JButton("CANCEL");
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnNewButton.setBounds(170, 528, 85, 32);
		contentPane.add(btnNewButton);
		
		dbConnection = new connection();
		
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBackground(SystemColor.textHighlight);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
					 
					 	String facultyID = generateUniqueFacultyID();
	                    String name = nametxtfld.getText();
	                    String affiliation = affiliation_comboBox.getSelectedItem().toString();                    
	                    String college;
	                    String department;

	                    // Handle different affiliations
	                    if ("Organization".equals(affiliation)) {
	                        college = orgtxtfld.getText();
	                        department = "N/A"; // or set it to an empty string, depending on your database schema
	                    } 
	                    else if ("Research Thrust".equals(affiliation))
	                    {
	                    	 college = college_comboBox.getSelectedItem().toString();
		                     department = "N/A"; 
	                    }
	                    else if ("Freelancer".equals(   affiliation))
	                    {
	                    	college = "N/A";
	                    	department = "N/A";
	                    }
	                    
	                    else {
	                        college = college_comboBox.getSelectedItem().toString();
	                        department = department_comboBox.getSelectedItem().toString();
	                    }
	                    
	                    

	                    // Check if the name is empty or null
	                    if (name == null || name.trim().isEmpty()) {
	                        JOptionPane.showMessageDialog(contentPane, "Insert Author Name", "Error", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    }
	                    
	                    
	                    // Check if the name already exists
	                    if (isNameExists(name)) {
	                        // Display an error message or handle the name existence case
	                        JOptionPane.showMessageDialog(contentPane, "Author name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
	                        return; // Exit the method without adding the author
	                    }

	                    // Get a database connection
	                    Connection connection = dbConnection.getConnection();

	                    // Create an SQL INSERT statement
	                    String insertSql = "INSERT INTO faculty (id, name, affiliation,college, department) " +
	                            "VALUES (?, ?, ?, ?, ?)";

	                    // Prepare the SQL statement with parameters
	                    PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
	                    preparedStatement.setString(1, facultyID);
	                    preparedStatement.setString(2, name);
	                    preparedStatement.setString(3, affiliation);
	                    preparedStatement.setString(4, college);
	                    preparedStatement.setString(5, department);

	                    // Execute the SQL INSERT statement
	                    preparedStatement.executeUpdate();
	                    
	                 // After successfully inserting data into the database
	                    parentDashboard.refreshFacultyTable(); //
	              
	                    JOptionPane.showMessageDialog(null, "Addition was successful!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

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
		btnAdd.setBounds(265, 528, 85, 32);
		contentPane.add(btnAdd);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\TRIXIE\\Pictures\\RDCMS ICONS\\70749-200.png"));
		lblNewLabel_3.setBounds(153, 58, 191, 174);
		contentPane.add(lblNewLabel_3);
	}

	private String generateUniqueFacultyID() throws SQLException {
	        Connection connection = dbConnection.getConnection();
	        String facultyID = null;
	        boolean isUnique = false;
	        int counter = 1;

	        while (!isUnique) {
	            facultyID = String.format("RDC-%04d", counter);
	            String checkQuery = "SELECT id FROM faculty WHERE id = ?";
	            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
	            checkStatement.setString(1, facultyID);
	            ResultSet resultSet = checkStatement.executeQuery();

	            if (!resultSet.next()) {
	                isUnique = true; // ID is unique, break out of the loop
	            } else {
	                counter++; // ID already exists, increment the counter and check again
	            }

	            resultSet.close();
	            checkStatement.close();
	        }

	        connection.close();
	        return facultyID;
	    }
	 
	 private boolean isNameExists(String name) throws SQLException {
	        Connection connection = dbConnection.getConnection();
	        String checkQuery = "SELECT name FROM faculty WHERE name = ?";
	        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
	        checkStatement.setString(1, name);
	        ResultSet resultSet = checkStatement.executeQuery();
	        boolean exists = resultSet.next(); // If the result set has a row, the name exists
	        resultSet.close();
	        checkStatement.close();
	        connection.close();
	        return exists;
	    }
	 
	// Add this method to your addnewfaculty class
	 private String getNextAuthorID() {
	     String nextAuthorID = "RDC-0001"; // Default value if no records are found

	     try {
	         // Get a database connection
	         Connection connection = dbConnection.getConnection();

	         // Create an SQL query to retrieve the next available author ID
	         String query = "SELECT MAX(id) FROM faculty";

	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	         ResultSet resultSet = preparedStatement.executeQuery();

	         if (resultSet.next()) {
	             String lastAuthorID = resultSet.getString(1);
	             if (lastAuthorID != null) {
	                 // Increment the last author ID
	                 int nextID = Integer.parseInt(lastAuthorID.substring(4)) + 1;
	                 nextAuthorID = String.format("RDC-%04d", nextID);
	             }
	         }

	         // Close the database connection
	         dbConnection.closeConnection(connection);
	     } catch (SQLException ex) {
	         ex.printStackTrace();
	         // Handle any database-related errors here
	     }

	     return nextAuthorID;
	 }
}