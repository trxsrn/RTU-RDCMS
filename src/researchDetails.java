import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class researchDetails extends JFrame {
	
	private String paperid;
    private JPanel contentPane;
    private JTable authorstbl;
    private DefaultTableModel authorstableModel;
    private JButton selectedButton = null;
    private JDateChooser dateChooser_1;
    private JDateChooser dateChooser_2;
    private JDateChooser dateChooser_3;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    researchDetails frame = new researchDetails("AAAA", "Research Title", "Colloquium");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public researchDetails(String paperid, String papertitle, String paperstatus) { 
    	this.paperid = paperid;
    	setTitle("RESEARCH DETAILS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(50, 50, 891, 754);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.textHighlight);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\TRIXIE\\Desktop\\RDCMS\\edit-xxl.png"); // Replace with your image path

        // Calculate the desired width and height while maintaining aspect ratio
        int maxWidth = 20; // Adjust this value as needed
        int maxHeight = 20; // Adjust this value as needed
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();

        int newWidth = originalWidth;
        int newHeight = originalHeight;

        // Check if resizing is necessary to fit within maxWidth and maxHeight
        if (originalWidth > maxWidth || originalHeight > maxHeight) {
            double widthRatio = (double) maxWidth / originalWidth;
            double heightRatio = (double) maxHeight / originalHeight;

            double minRatio = Math.min(widthRatio, heightRatio);
            newWidth = (int) (originalWidth * minRatio);
            newHeight = (int) (originalHeight * minRatio);
        }

        // Resize the image while maintaining aspect ratio
        Image resizedImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(36, 200, 806, 162);
        scrollPane.setPreferredSize(new Dimension(1078, 281));
        contentPane.add(scrollPane);

        authorstableModel = new DefaultTableModel();
        authorstbl = new JTable(authorstableModel) {
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        authorstbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Detect double-click
                	
                	 int selectedRow = authorstbl.getSelectedRow();
     	            if (selectedRow >= 0) {
     	                // Get the selected faculty's data from the table model
     	                String facultyid = authorstbl.getValueAt(selectedRow, 0).toString();
//     	         
     	               int response = JOptionPane.showConfirmDialog(
     	                        null, // Use the appropriate parent component
     	                        "Do you want to remove this author?",
     	                        "Confirm Removal",
     	                        JOptionPane.YES_NO_OPTION);

     	                if (response == JOptionPane.YES_OPTION) {
     	                	
     	                	 try {
     	      	                   
           	                    
           	                    // Establish a database connection (You should replace these placeholders)
           	                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");
           	                    
           	                    // Define the SQL DELETE statement
           	                    String deleteQuery = "DELETE FROM research_faculty WHERE faculty = ?";
           	                    
           	                    // Create a PreparedStatement to execute the query
           	                    PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
           	                    
           	                    // Set the parameter for the faculty_id
           	                    preparedStatement.setString(1, facultyid);
           	                    
           	                    // Execute the DELETE statement
           	                    int rowsDeleted = preparedStatement.executeUpdate();
           	                    
           	                    JOptionPane.showMessageDialog(null, "Author sucessfully removed!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
           	                    
           	                    loadAuthors(paperid);
           	                    
           	                    // Close the PreparedStatement and the database connection
           	                    preparedStatement.close();
           	                    connection.close();
           	                } catch (SQLException ex) {
           	                    ex.printStackTrace();
           	                    JOptionPane.showMessageDialog(
           	                        null,
           	                        "Error removing author: " + ex.getMessage(),
           	                        "Error",
           	                        JOptionPane.ERROR_MESSAGE
           	                    );
           	                }
     	                }
     	                
    	            	
     	            }
                	

                }
            }
        });
        scrollPane.setViewportView(authorstbl);

        JLabel lblNewLabel_1_1 = new JLabel("RESEARCH TITLE");
        lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {	
 
        	}
        });
        lblNewLabel_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_1.setBackground(SystemColor.textHighlight);
        lblNewLabel_1_1.setBounds(36, 76, 160, 19);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1_1);

        JTextField researchtitle = new JTextField(papertitle);
        researchtitle.setEnabled(false);
        researchtitle.setEditable(false);
        researchtitle.setHorizontalAlignment(SwingConstants.CENTER);
        researchtitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
        researchtitle.setForeground(Color.BLACK);
        researchtitle.setBounds(36, 105, 806, 55);
        contentPane.add(researchtitle);

        JLabel lblNewLabel_1_3 = new JLabel("PROPONENTS:");
        lblNewLabel_1_3.setForeground(SystemColor.text);
        lblNewLabel_1_3.setBounds(36, 171, 167, 19);
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1_3);
        
        JLabel lblNewLabel_1_3_1_1 = new JLabel("Is it subject for forum?");
        lblNewLabel_1_3_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_3_1_1.setBounds(36, 387, 190, 19);
        contentPane.add(lblNewLabel_1_3_1_1);
        
        JRadioButton chckbxNewCheckBox = new JRadioButton("Yes");
        buttonGroup.add(chckbxNewCheckBox);
        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chckbxNewCheckBox.setForeground(SystemColor.text);
        chckbxNewCheckBox.setBackground(SystemColor.textHighlight);
        chckbxNewCheckBox.setBounds(241, 386, 61, 21);
        contentPane.add(chckbxNewCheckBox);
        
        JLabel lblNewLabel_1_3_1_1_1 = new JLabel("If yes, select the date when will be held");
        lblNewLabel_1_3_1_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_3_1_1_1.setBounds(308, 424, 301, 19);
        contentPane.add(lblNewLabel_1_3_1_1_1);
        
        JDateChooser forumdate = new JDateChooser();
        forumdate.setDateFormatString("MMMM dd, yyyy");
        forumdate.setBounds(590, 379, 252, 27);
        contentPane.add(forumdate);
        
        JLabel lblNewLabel_1_3_1_1_2 = new JLabel("Is it subject for colloquium?");
        lblNewLabel_1_3_1_1_2.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_3_1_1_2.setBounds(36, 424, 227, 19);
        contentPane.add(lblNewLabel_1_3_1_1_2);
        
        JRadioButton chckbxNewCheckBox_1 = new JRadioButton("Yes");
        chckbxNewCheckBox_1.setForeground(SystemColor.text);
        chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chckbxNewCheckBox_1.setBackground(SystemColor.textHighlight);
        chckbxNewCheckBox_1.setBounds(237, 423, 93, 21);
        contentPane.add(chckbxNewCheckBox_1);
        
        JLabel lblNewLabel_1_3_1_1_1_2 = new JLabel("If yes, select the date when will be held");
        lblNewLabel_1_3_1_1_1_2.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_3_1_1_1_2.setBounds(308, 387, 301, 19);
        contentPane.add(lblNewLabel_1_3_1_1_1_2);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        panel.setBounds(0, 0, 877, 40);
        contentPane.add(panel);
        panel.setLayout(null);
        
        

        JLabel lblNewLabel_1 = new JLabel("PAPER ID");
        lblNewLabel_1.setBounds(10, 10, 160, 19);
        panel.add(lblNewLabel_1);
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
                JLabel paperID = new JLabel(paperid);
                paperID.setHorizontalAlignment(SwingConstants.RIGHT);
                paperID.setBounds(329, 10, 513, 19);
                panel.add(paperID);
                paperID.setEnabled(false);
                paperID.setFont(new Font("Tahoma", Font.PLAIN, 15));
                paperID.setForeground(Color.BLACK);
                
                JButton edit_btn = new JButton(resizedIcon);
                edit_btn.setBackground(SystemColor.textHighlight);
                edit_btn.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                        researchtitle.setEditable(true);
                        researchtitle.setEnabled(true);
                	}
                });
                edit_btn.setBounds(797, 76, 45, 19);
                contentPane.add(edit_btn);
                
                JLabel lblNewLabel_1_3_1 = new JLabel("REMARKS:");
                lblNewLabel_1_3_1.setForeground(SystemColor.text);
                lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
                lblNewLabel_1_3_1.setBounds(36, 490, 167, 19);
                contentPane.add(lblNewLabel_1_3_1);
                
                JTextArea textArea = new JTextArea();
                textArea.setBounds(37, 523, 805, 112);
                contentPane.add(textArea);
                
     
                JButton savebtn = new JButton("SAVE CHANGES");
                savebtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int response = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to save changes?",
                            "Save Changes",
                            JOptionPane.YES_NO_OPTION
                        );

                        if (response == JOptionPane.YES_OPTION) {
                            String title = researchtitle.getText(); // Get the text from researchtitle field

                            try {
                                // Establish a database connection (you need to fill in the connection details)
                                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");

                                // Create a SQL query with sorting
                                String query = "UPDATE research_summary "
                                        + "JOIN research_faculty ON research_summary.paper_id = research_faculty.paper_id "
                                        + "SET research_summary.title = ?, research_faculty.title = ? "
                                        + "WHERE research_summary.paper_id = ?";

                                // Create a PreparedStatement
                                PreparedStatement preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setString(1, title);
                                preparedStatement.setString(2, title);
                                preparedStatement.setString(3, paperid); // Set your paper_id here

                                // Execute the update operation
                                int rowsAffected = preparedStatement.executeUpdate();

                                if (rowsAffected > 0) {
                                    JOptionPane.showMessageDialog(null, "Saved Changes!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
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
                    }
                });

                
                savebtn.setBounds(682, 658, 160, 33);
                contentPane.add(savebtn);
                
                JButton btnNewButton_1 = new JButton("ADD PROPONENT");
                btnNewButton_1.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		
                		addnewresearcher addnewresearcher = new addnewresearcher(null, paperid, papertitle, paperstatus);
            			addnewresearcher.setVisible(true);
                	}
                });
                btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
                btnNewButton_1.setBounds(711, 168, 131, 22);
                contentPane.add(btnNewButton_1);
                
                JButton btnNewButton = new JButton("VIEW RESEARCH HISTORY");
                btnNewButton.setBounds(206, 658, 466, 33);
                contentPane.add(btnNewButton);
                
                JButton btnNewButton_2 = new JButton("DELETE RESEARCH");
                btnNewButton_2.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		 String paperid = paperID.getText();
                		 
                		int response = JOptionPane.showConfirmDialog(
                                null,
                                "Are you sure you want to delete this research?",
                                "Delete Research",
                                JOptionPane.YES_NO_OPTION
                            );

                            if (response == JOptionPane.YES_OPTION) {
//                       

                                try {
                                    // Establish a database connection (you need to fill in the connection details)
                                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");

                                    // Create a SQL query with sorting
                                    String query = "DELETE FROM research_summary, research_faculty WHERE paper_id = ?";

                                    // Create a PreparedStatement
                                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                                    preparedStatement.setString(1, paperid); // Set your paper_id here

                                    // Execute the update operation
                                    int rowsAffected = preparedStatement.executeUpdate();

                                    if (rowsAffected > 0) {
                                        JOptionPane.showMessageDialog(null, "Delete Success!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                                        dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Cannot delete.", "INFO", JOptionPane.INFORMATION_MESSAGE);
                                    }

                                    // Close resources (statement and connection)
                                    preparedStatement.close();
                                    connection.close();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    // Handle any exceptions that may occur while querying the database
                                }
                            }
                	}
                });
                btnNewButton_2.setBounds(36, 658, 160, 33);
                contentPane.add(btnNewButton_2);
                
                JDateChooser forumdate_1 = new JDateChooser();
                forumdate_1.setDateFormatString("MMMM dd, yyyy");
                forumdate_1.setBounds(590, 416, 252, 27);
                contentPane.add(forumdate_1);
                
                JLabel lblNewLabel_1_3_1_1_2_1 = new JLabel("Is it subject for conference?");
                lblNewLabel_1_3_1_1_2_1.setForeground(SystemColor.text);
                lblNewLabel_1_3_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
                lblNewLabel_1_3_1_1_2_1.setBounds(36, 461, 227, 19);
                contentPane.add(lblNewLabel_1_3_1_1_2_1);
                
                JRadioButton chckbxNewCheckBox_1_1 = new JRadioButton("Yes");
                chckbxNewCheckBox_1_1.setForeground(SystemColor.text);
                chckbxNewCheckBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
                chckbxNewCheckBox_1_1.setBackground(SystemColor.textHighlight);
                chckbxNewCheckBox_1_1.setBounds(236, 460, 66, 21);
                contentPane.add(chckbxNewCheckBox_1_1);
                
                JLabel lblNewLabel_1_3_1_1_1_1 = new JLabel("If yes, select the date when will be held");
                lblNewLabel_1_3_1_1_1_1.setForeground(SystemColor.text);
                lblNewLabel_1_3_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
                lblNewLabel_1_3_1_1_1_1.setBounds(308, 461, 301, 19);
                contentPane.add(lblNewLabel_1_3_1_1_1_1);
                
                JDateChooser forumdate_1_1 = new JDateChooser();
                forumdate_1_1.setDateFormatString("MMMM dd, yyyy");
                forumdate_1_1.setBounds(590, 453, 252, 27);
                contentPane.add(forumdate_1_1);
                
         loadAuthors(paperid);
    }

	void loadAuthors(String paperid) {
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");
                ) {
            // Prepare an SQL query to retrieve research data based on faculty ID
            String sql = "SELECT `research_faculty`.faculty, `faculty`.name FROM `research_faculty` JOIN `faculty` ON `research_faculty`.faculty = `faculty`.id WHERE `research_faculty`.paper_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, paperid);

            // Execute the query and retrieve the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create a DefaultTableModel to hold the research data
            DefaultTableModel authorstableModel = new DefaultTableModel();

            // Add columns to the table model (you should adjust column names accordingly)
            authorstableModel.addColumn("Faculty ID");
            authorstableModel.addColumn("Faculty Name");

            // Populate the table model with data from the result set
            while (resultSet.next()) {
                String faculty = resultSet.getString("faculty");
                String name = resultSet.getString("name");

                // Add a row to the table model
                authorstableModel.addRow(new String[] { faculty, name});
            }

            // Set the table model for your JTable
            authorstbl.setModel(authorstableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private void loadDates(String paperid) throws ParseException {
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
	        // Prepare an SQL query to retrieve research data based on paper ID
	        String sql = "SELECT colloquium, forum, published FROM research_summary WHERE paper_id = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, paperid);

	        // Execute the query and retrieve the result set
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // Populate the date choosers with data from the result set
	        while (resultSet.next()) {
	            String colloquiumDate = resultSet.getString("colloquium");
	            String forumDate = resultSet.getString("forum");
	            String publishedDate = resultSet.getString("published");

	            // Assuming dateChooser_1, dateChooser_2, dateChooser_3 are your JDateChooser components
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Modify the date format based on your database schema
	            Date colloquium = dateFormat.parse(colloquiumDate);
	            Date forum = dateFormat.parse(forumDate);
	            Date published = dateFormat.parse(publishedDate);

	            // Use the class-level variables directly to set the dates
	            dateChooser_1.setDate(colloquium);
	            dateChooser_2.setDate(forum);
	            dateChooser_3.setDate(published);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	void refreshAuthorsTable() {
		
	       loadAuthors(paperid);
		
	}
	
}