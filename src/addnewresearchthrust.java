import java.awt.Dimension;
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
import java.awt.Toolkit;

import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class addnewresearchthrust extends JFrame {
	
	private dashboard parentDashboard;

	private connection dbConnection;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField abbtxtfld;
	private JTextField nametxtfld;
	private JEditorPane desctxtfld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboard parentDashboard = new dashboard();
					addnewresearchthrust frame = new addnewresearchthrust(parentDashboard);
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
	public addnewresearchthrust(dashboard parentDashboard) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 599, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int centerX = (screenSize.width - getWidth()) / 2;
	    int centerY = (screenSize.height - getHeight()) / 2;
	    setLocation(centerX, centerY);
	    getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 585, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADD NEW RESEARCH THRUST");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(134, 26, 332, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ABBREVIATION");
		lblNewLabel_1.setBounds(33, 94, 132, 13);
		contentPane.add(lblNewLabel_1);
		
		abbtxtfld = new JTextField();
		abbtxtfld.setBounds(151, 85, 340, 32);
		contentPane.add(abbtxtfld);
		abbtxtfld.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("NAME");
		lblNewLabel_1_1.setBounds(33, 144, 132, 13);
		contentPane.add(lblNewLabel_1_1);
		
		nametxtfld = new JTextField();
		nametxtfld.setColumns(10);
		nametxtfld.setBounds(151, 135, 340, 32);
		contentPane.add(nametxtfld);
		
		desctxtfld = new JEditorPane();
		desctxtfld.setBounds(151, 190, 340, 86);
		contentPane.add(desctxtfld);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("DESCRIPTION");
		lblNewLabel_1_1_1.setBounds(33, 199, 132, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					 
				 	String abb = abbtxtfld.getText();
                    String name = nametxtfld.getText();
                    String description = desctxtfld.getText();
                    
                    if (name.equals(""))
                    {
                    	JOptionPane.showMessageDialog(contentPane, "Insert Research Thrust Full Name", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (abb.equals(""))
                    {
                    	JOptionPane.showMessageDialog(contentPane, "Insert Abbreviation", "Error", JOptionPane.ERROR_MESSAGE);
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
                    String insertSql = "INSERT INTO research_thrust (abbreviation, name, description)" +
                            "VALUES (?, ?, ?)";

                    // Prepare the SQL statement with parameters
                    PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
                    preparedStatement.setString(1, abb);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, description);

                    // Execute the SQL INSERT statement
                    preparedStatement.executeUpdate();
                    
                 // After successfully inserting data into the database
              
                    JOptionPane.showMessageDialog(null, "Addition was successful!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    parentDashboard.refreshThrustTable();
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
		btnNewButton.setBounds(406, 309, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(33, 309, 85, 21);
		contentPane.add(btnCancel);
	}
	
	private boolean isNameExists(String name) throws SQLException {
	    Connection connection = dbConnection.getConnection();
	    String checkQuery = "SELECT name FROM research_thrust WHERE name = ?";
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
