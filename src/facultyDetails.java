import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class facultyDetails extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel individualresearch;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	dashboard parentDashboard = new dashboard(); 
                    facultyDetails frame = new facultyDetails(parentDashboard, "123", "John Doe", "Department of XYZ", "Science College", "Physics");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public facultyDetails(dashboard parentDashboard, String faculty_Id, String faculty_Name, String faculty_Affiliation, String faculty_College, String faculty_Department ) {
        setTitle("FACULTY DETAILS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 589, 835);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.textHighlight);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int centerX = (screenSize.width - getWidth()) / 2;
	    int centerY = (screenSize.height - getHeight()) / 2;
	    setLocation(centerX, centerY);
	    getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 356, 575, 410);
        scrollPane.setPreferredSize(new Dimension(1078, 281));
        contentPane.add(scrollPane);

        individualresearch = new DefaultTableModel();
        table = new JTable(individualresearch) {
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        loadResearchData(faculty_Id);
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		  if (e.getClickCount() == 2) { // Detect double-click
        			  int selectedRow = table.getSelectedRow();
        			  if (selectedRow >= 0) {
        			      String paperid = individualresearch.getValueAt(selectedRow, 0).toString();
        			      String papertitle = individualresearch.getValueAt(selectedRow, 1).toString();
        			      String paperstatus = individualresearch.getValueAt(selectedRow, 2).toString();
        			      researchDetails researchDetails = new researchDetails(parentDashboard, paperid, papertitle, paperstatus);
        			      researchDetails.setVisible(true);
        			  } else {
        			      JOptionPane.showMessageDialog(null, "Please select a row.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        			      System.out.println("Selected Row Index: " + selectedRow);
        			  }
        		  }
        	}
        });
        scrollPane.setViewportView(table);

        JLabel lblNewLabel_1 = new JLabel("FACULTY ID");
        lblNewLabel_1.setForeground(SystemColor.text);
        lblNewLabel_1.setBounds(26, 175, 545, 19);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1);

        JTextField facultyID = new JTextField(faculty_Id);
        facultyID.setBackground(SystemColor.textHighlight);
        facultyID.setEditable(false);
        facultyID.setEnabled(false);
        facultyID.setFont(new Font("Tahoma", Font.PLAIN, 15));
        facultyID.setForeground(SystemColor.text);
        facultyID.setBounds(259, 175, 296, 19);
        contentPane.add(facultyID);

        JLabel lblNewLabel_1_1 = new JLabel("FULL NAME");
        lblNewLabel_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_1.setBackground(SystemColor.textHighlight);
        lblNewLabel_1_1.setBounds(26, 217, 545, 19);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1_1);

        JTextField facultyName = new JTextField(faculty_Name);
        facultyName.setBackground(SystemColor.textHighlight);
        facultyName.setEnabled(false);
        facultyName.setEditable(false);
        facultyName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        facultyName.setForeground(SystemColor.text);
        facultyName.setBounds(259, 217, 296, 19);
        contentPane.add(facultyName);

        JLabel lblNewLabel_1_2 = new JLabel("AFFILIATION");
        lblNewLabel_1_2.setForeground(SystemColor.text);
        lblNewLabel_1_2.setBounds(26, 264, 545, 19);
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1_2);

        JTextField facultyAffiliation = new JTextField(faculty_Affiliation);
        facultyAffiliation.setBackground(SystemColor.textHighlight);
        facultyAffiliation.setEnabled(false);
        facultyAffiliation.setEditable(false);
        facultyAffiliation.setFont(new Font("Tahoma", Font.PLAIN, 15));
        facultyAffiliation.setForeground(SystemColor.text);
        facultyAffiliation.setBounds(259, 264, 296, 18);
        contentPane.add(facultyAffiliation);

        JLabel lblNewLabel_1_3 = new JLabel("COLLEGE/DEPARTMENT:");
        lblNewLabel_1_3.setForeground(SystemColor.text);
        lblNewLabel_1_3.setBounds(26, 314, 545, 19);
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1_3);

        JTextField facultyDept = new JTextField(faculty_College + " - " + faculty_Department);
        facultyDept.setBackground(SystemColor.textHighlight);
        facultyDept.setEditable(false);
        facultyDept.setEnabled(false);
        facultyDept.setFont(new Font("Tahoma", Font.PLAIN, 15));
        facultyDept.setForeground(Color.WHITE);
        facultyDept.setBounds(260, 314, 295, 19);
        contentPane.add(facultyDept);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\xampp\\htdocs\\TRR\\css\\img\\RDC logo 2.png"));
        lblNewLabel.setBounds(872, 36, 160, 191);
        contentPane.add(lblNewLabel);

        JButton savebtn = new JButton("SAVE");
        savebtn.setBackground(new Color(50, 205, 50));
        JButton editbtn = new JButton("EDIT");
        editbtn.setBackground(new Color(255, 215, 0));
        savebtn.setEnabled(false);

        savebtn.setBounds(450, 765, 125, 33);
        contentPane.add(savebtn);

        editbtn.setBounds(124, 765, 327, 33);
        contentPane.add(editbtn);
        
        JButton btnNewButton = new JButton("Remove");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove " + faculty_Name + " as faculty ?");
        		if (result == 0) {
        		    
        		   //enters admin credentials
        			confirmcredentials confirmscredentials = new confirmcredentials();
        			confirmscredentials.setVisible(true);
        		}
        	
        	}
        });
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(Color.RED);
        btnNewButton.setBounds(0, 765, 125, 33);
        contentPane.add(btnNewButton);
        
        savebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // You can add save functionality here if needed
            	int response = JOptionPane.showConfirmDialog(
	                        null, // Use the appropriate parent component
	                        "Are you sure you want save changes?",
	                        "Save Changes",
	                        JOptionPane.YES_NO_OPTION);
            	
	    
                 String name = facultyName.getText(); // Get the text from researchtitle field

                 try {
                     // Establish a database connection (you need to fill in the connection details)
                     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");

                     // Create a SQL query with sorting
                     String query = "UPDATE faculty SET name = ?  WHERE id = ? ";

                     // Create a PreparedStatement
                     PreparedStatement preparedStatement = connection.prepareStatement(query);
                     preparedStatement.setString(1, name);
                     preparedStatement.setString(2, faculty_Id);

                     // Execute the update operation
                     int rowsAffected = preparedStatement.executeUpdate();

                     if (rowsAffected > 0) {
                         	JOptionPane.showMessageDialog(null, "Saved Changes!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                         
			            	facultyName.setEnabled(false);
			            	facultyName.setBackground(SystemColor.textHighlight);
			                facultyName.setForeground(Color.WHITE);
			            	
			            	facultyAffiliation.setEnabled(false);
			            	facultyAffiliation.setBackground(SystemColor.textHighlight);
			                facultyAffiliation.setForeground(Color.WHITE);
			            	
			            	facultyDept.setEnabled(false);
			            	facultyDept.setBackground(SystemColor.textHighlight);
			                facultyDept.setForeground(Color.WHITE);
			            	
			            	savebtn.setEnabled(false);
			            	editbtn.setEnabled(true);
                     } else {
                         JOptionPane.showMessageDialog(null, "No changes were made.", "INFO", JOptionPane.INFORMATION_MESSAGE);
                     }

                     // Close resources (statement and connection)
                     preparedStatement.close();
                     connection.close();
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                     // Handle any exceptions that may occur while querying the database
                 }
            }
        });
        editbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // You can add edit functionality here if needed
            	facultyName.setEnabled(true);
            	facultyName.setBackground(Color.WHITE);
                facultyName.setForeground(Color.BLACK);
            	
            	
            	facultyAffiliation.setEnabled(true);
                facultyAffiliation.setBackground(Color.WHITE);
                facultyAffiliation.setForeground(Color.BLACK);
            	
            	facultyDept.setEnabled(true);
                facultyDept.setBackground(Color.WHITE);
                facultyDept.setForeground(Color.BLACK);
            	
            	facultyName.setEditable(true);
            	facultyAffiliation.setEditable(true);
            	facultyDept.setEditable(true);
            	
            	savebtn.setEnabled(true);
            	editbtn.setEnabled(false);
            }
        });

        // Load research data into the table
        loadResearchData(faculty_Id);
    }

    private void loadResearchData(String faculty_Id) {
        // Define your database connection parameters

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");
                ) {
            // Prepare an SQL query to retrieve research data based on faculty ID
            String sql = "SELECT * FROM research_faculty WHERE faculty = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, faculty_Id);

            // Execute the query and retrieve the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create a DefaultTableModel to hold the research data
            DefaultTableModel researchModel = new DefaultTableModel();
            
            // Add columns to the table model (you should adjust column names accordingly)
            researchModel.addColumn("Paper ID");
            researchModel.addColumn("Title");
            researchModel.addColumn("Status");

            // Populate the table model with data from the result set
            while (resultSet.next()) {
            	 String id = resultSet.getString("paper_id");
            	 String title = resultSet.getString("title");
            	 String status = resultSet.getString("status");
            	 
            	 
                // Add a row to the table model
                researchModel.addRow(new String[] { id, title, status });
//                System.out.println("Loaded research data: " + researchModel.getRowCount() + " rows.");
                
                if (table.getRowCount() > 0) {
                    int firstRowIndex = 0;
                    table.setRowSelectionInterval(firstRowIndex, firstRowIndex);
                }
            }

            // Set the table model for your JTable
            table.setModel(researchModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
