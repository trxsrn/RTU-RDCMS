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
import java.awt.Toolkit;

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
import javax.swing.JSeparator;
import javax.swing.JTree;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class researchDetails extends JFrame {
	
	private String paperid;
    private JPanel contentPane;
    private JTable authorstbl;
    private DefaultTableModel authorstableModel;
    private JButton selectedButton = null;
    private JDateChooser f_sched;
    private JDateChooser col_sched;
    private JDateChooser con_sched;
    private JDateChooser f_accomplished;
    private JDateChooser col_accomplished;
    private JDateChooser conference_accomplished;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	dashboard parentDashboard = new dashboard(); 
                    researchDetails frame = new researchDetails(parentDashboard, "AAAA", "Research Title", "Colloquium");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public researchDetails(dashboard parentDashboard, String paperid, String papertitle, String paperstatus) {
    	setResizable(false); 
    	this.paperid = paperid;
    	setTitle("RESEARCH DETAILS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(50, 50, 891, 824);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.textHighlight);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int centerX = (screenSize.width - getWidth()) / 2;
	    int centerY = (screenSize.height - getHeight()) / 2;
	    setLocation(centerX, centerY);
	    getContentPane().setLayout(null);
        
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
        scrollPane.setBounds(30, 200, 816, 162);
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
        lblNewLabel_1_1.setBounds(30, 76, 160, 19);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1_1);

        JTextArea researchtitle = new JTextArea(papertitle);
        researchtitle.setEnabled(false);
        researchtitle.setEditable(false);
        researchtitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
        researchtitle.setForeground(Color.BLACK);
        researchtitle.setBounds(30, 105, 816, 55);
        contentPane.add(researchtitle);

        JLabel lblNewLabel_1_3 = new JLabel("PROPONENTS:");
        lblNewLabel_1_3.setForeground(SystemColor.text);
        lblNewLabel_1_3.setBounds(30, 170, 167, 19);
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1_3);
        
        JLabel lblNewLabel_1_3_1_1 = new JLabel("Forum");
        lblNewLabel_1_3_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_1_3_1_1.setBounds(30, 387, 190, 19);
        contentPane.add(lblNewLabel_1_3_1_1);
        
        f_sched = new JDateChooser();
        f_sched.setDateFormatString("MMMM dd, yyyy");
        f_sched.setBounds(30, 448, 252, 27);
        contentPane.add(f_sched);
        
        JLabel lblNewLabel_1_3_1_1_2 = new JLabel("Colloquium");
        lblNewLabel_1_3_1_1_2.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_1_3_1_1_2.setBounds(312, 387, 227, 19);
        contentPane.add(lblNewLabel_1_3_1_1_2);
        
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
                edit_btn.setBounds(797, 76, 49, 19);
                contentPane.add(edit_btn);
                
                JLabel lblNewLabel_1_3_1 = new JLabel("REMARKS:");
                lblNewLabel_1_3_1.setAlignmentX(Component.CENTER_ALIGNMENT);
                lblNewLabel_1_3_1.setForeground(SystemColor.text);
                lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
                lblNewLabel_1_3_1.setBounds(30, 570, 167, 19);
                contentPane.add(lblNewLabel_1_3_1);
                
                JTextArea textArea = new JTextArea();
                textArea.setBounds(30, 599, 816, 73);
                contentPane.add(textArea);
                
     
                
                JButton btnNewButton_1 = new JButton("ADD PROPONENT");
                btnNewButton_1.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		
                		addnewresearcher addnewresearcher = new addnewresearcher(null, paperid, papertitle, paperstatus);
            			addnewresearcher.setVisible(true);
                	}
                });
                btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
                btnNewButton_1.setBounds(711, 168, 135, 22);
                contentPane.add(btnNewButton_1);
                
                JButton btnNewButton = new JButton("VIEW RESEARCH HISTORY");
                btnNewButton.setBounds(206, 732, 466, 33);
                contentPane.add(btnNewButton);
                
                JButton btnNewButton_2 = new JButton("DELETE RESEARCH");
                btnNewButton_2.setBackground(Color.RED);
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
                                    String query = "DELETE FROM research_summary WHERE paper_id = ?";

                                    // Create a PreparedStatement
                                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                                    preparedStatement.setString(1, paperid); // Set your paper_id here

                                    // Execute the update operation
                                    int rowsAffected = preparedStatement.executeUpdate();

                                    if (rowsAffected > 0) {
                                    	
                                    	String query1 = "DELETE FROM research_summary WHERE paper_id = ?";

                                        PreparedStatement preparedStatement1 = connection.prepareStatement(query);
                                        preparedStatement.setString(1, paperid); // Set your paper_id here
                                        
                                        int rowsAffected1 = preparedStatement.executeUpdate();
                                        
                                        if (rowsAffected > 0) {
                                        
                                        	parentDashboard.refreshResearchTable();
	                                        JOptionPane.showMessageDialog(null, "Delete Success!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
	                                        dispose();
                                        }
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
                btnNewButton_2.setBounds(30, 732, 166, 33);
                contentPane.add(btnNewButton_2);
                
                con_sched = new JDateChooser();
                con_sched.setDateFormatString("MMMM dd, yyyy");
                con_sched.setBounds(594, 448, 252, 27);
                contentPane.add(con_sched);
                
                col_sched = new JDateChooser();
                col_sched.setDateFormatString("MMMM dd, yyyy");
                col_sched.setBounds(312, 448, 252, 27);
                contentPane.add(col_sched);
                
                JButton btnNewButton_3 = new JButton("MARK AS DONE");
                btnNewButton_3.setBounds(30, 682, 815, 40);
                contentPane.add(btnNewButton_3);
                
                f_accomplished = new JDateChooser();
                f_accomplished.setDateFormatString("MMMM dd, yyyy");
                f_accomplished.setBounds(30, 508, 252, 27);
                contentPane.add(f_accomplished);
                
                col_accomplished = new JDateChooser();
                col_accomplished.setDateFormatString("MMMM dd, yyyy");
                col_accomplished.setBounds(312, 508, 252, 27);
                contentPane.add(col_accomplished);
                
                conference_accomplished = new JDateChooser();
                conference_accomplished.setDateFormatString("MMMM dd, yyyy");
                conference_accomplished.setBounds(594, 508, 252, 27);
                contentPane.add(conference_accomplished);
                
                JLabel lblNewLabel = new JLabel("Scheduled Date");
                lblNewLabel.setForeground(Color.WHITE);
                lblNewLabel.setBounds(30, 425, 141, 13);
                contentPane.add(lblNewLabel);
                
                JLabel lblScheduleDate = new JLabel("Scheduled Date");
                lblScheduleDate.setForeground(Color.WHITE);
                lblScheduleDate.setBounds(314, 425, 123, 13);
                contentPane.add(lblScheduleDate);
                
                JLabel lblNewLabel_2_1 = new JLabel("Scheduled Date");
                lblNewLabel_2_1.setForeground(Color.WHITE);
                lblNewLabel_2_1.setBounds(594, 425, 110, 13);
                contentPane.add(lblNewLabel_2_1);
                
                JLabel lblAccomplishedDate = new JLabel("Accomplished Date");
                lblAccomplishedDate.setForeground(Color.WHITE);
                lblAccomplishedDate.setBounds(30, 485, 141, 13);
                contentPane.add(lblAccomplishedDate);
                
                JLabel lblAccomplishedDate_1 = new JLabel("Accomplished Date");
                lblAccomplishedDate_1.setForeground(Color.WHITE);
                lblAccomplishedDate_1.setBounds(314, 485, 123, 13);
                contentPane.add(lblAccomplishedDate_1);
                
                JLabel lblNewLabel_2_1_1 = new JLabel("Accomplished Date");
                lblNewLabel_2_1_1.setForeground(Color.WHITE);
                lblNewLabel_2_1_1.setBounds(594, 485, 110, 13);
                contentPane.add(lblNewLabel_2_1_1);
                
                JButton savebtn = new JButton("SAVE CHANGES");
                savebtn.setBackground(Color.GREEN);
                savebtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int response = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to save changes?",
                            "Save Changes",
                            JOptionPane.YES_NO_OPTION
                        );

                        if (response == JOptionPane.YES_OPTION) {
                        	
                            String title = researchtitle.getText(); 
                            Date forumsched = f_sched.getDate();
                            Date colsched = col_sched.getDate();
                            Date conferencesched = con_sched.getDate();
                            Date forumacc = f_accomplished.getDate();
                            Date colacc = col_accomplished.getDate();
                            Date conacc = conference_accomplished.getDate();
                            
                            SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
            	            String forumsc = sdf.format(forumsched);
            	            String colsc = sdf.format(colsched);
            	            String conferencesc = sdf.format(conferencesched);
            	            String forumac = sdf.format(forumacc);
            	            String colac = sdf.format(conacc);
            	            String conac = sdf.format(conacc);
            	           
                            
                            try {
                                // Establish a database connection (you need to fill in the connection details)
                                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");

                                // Create a SQL query with sorting
                                String query = "UPDATE research_summary "
                                        + "JOIN research_faculty ON research_summary.paper_id = research_faculty.paper_id "
                                        + "SET research_summary.title = ?, research_faculty.title = ?, research_summary.colloquium_sched = ?, research_faculty.colloquium_sched = ?,  research_summary.forum_sched = ?, research_faculty.forum_sched = ?, research_summary.conference_sched = ?, research_faculty.conference_sched = ?, research_summary.colloquium_accomplished = ?, research_faculty.colloquium_accomplished = ?,  research_summary.forum_accomplished = ?, research_faculty.forum_accomplished = ?, research_summary.conference_accomplished = ?, research_faculty.conference_accomplished = ?"
                                        + "WHERE research_summary.paper_id = ?";

                                // Create a PreparedStatement
                                PreparedStatement preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setString(1, title);
                                preparedStatement.setString(2, title);
                                preparedStatement.setString(3, colsc);
                                preparedStatement.setString(4, colsc);
                                preparedStatement.setString(5, forumsc);
                                preparedStatement.setString(6, forumsc);
                                preparedStatement.setString(7, conferencesc);
                                preparedStatement.setString(8, conferencesc);
                                preparedStatement.setString(9, colac);
                                preparedStatement.setString(10, colac);
                                preparedStatement.setString(11, forumac);
                                preparedStatement.setString(12, forumac);
                                preparedStatement.setString(13, conac);
                                preparedStatement.setString(14, conac);
                                preparedStatement.setString(15, paperid);

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

                
                savebtn.setBounds(682, 732, 164, 33);
                contentPane.add(savebtn);
                

                
                JLabel lblNewLabel_1_3_1_1_2_1 = new JLabel("Conference");
                lblNewLabel_1_3_1_1_2_1.setForeground(SystemColor.text);
                lblNewLabel_1_3_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
                lblNewLabel_1_3_1_1_2_1.setBounds(594, 387, 227, 19);
                contentPane.add(lblNewLabel_1_3_1_1_2_1);
                
         loadAuthors(paperid);
         try {
			loadDates(paperid);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	        String sql = "SELECT colloquium_sched, forum_sched, conference_sched, colloquium_accomplished, forum_accomplished, conference_accomplished FROM research_summary WHERE paper_id = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, paperid);

	        // Execute the query and retrieve the result set
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // Populate the date choosers with data from the result set
	        while (resultSet.next()) {
	            String colloquiumschedDate = resultSet.getString("colloquium_sched");
	            String forumschedDate = resultSet.getString("forum_sched");
	            String conferenceschedDate = resultSet.getString("conference_sched");
	            String colloquiumaccDate = resultSet.getString("colloquium_accomplished");
	            String forumaccDate = resultSet.getString("forum_accomplished");
	            String conferenceaccDate = resultSet.getString("conference_accomplished");

	            // Assuming col_sched, f_sched, and conference_accomplished are your JDateChooser components
	            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy"); // Modify the date format based on your database schema
	            Date colloquium = (colloquiumschedDate != null) ? dateFormat.parse(colloquiumschedDate) : null;
	            Date forum = (forumschedDate != null) ? dateFormat.parse(forumschedDate) : null;
	            Date conference = (conferenceschedDate != null) ? dateFormat.parse(conferenceschedDate) : null;
	            Date colloquiumacc = (colloquiumaccDate != null) ? dateFormat.parse(colloquiumaccDate) : null;
	            Date forumacc = (forumaccDate != null) ? dateFormat.parse(forumaccDate) : null;
	            Date conferenceacc = (conferenceaccDate != null) ? dateFormat.parse(conferenceaccDate) : null;

	            // Use the class-level variables directly to set the dates
	            col_sched.setDate(colloquium);
	            f_sched.setDate(forum);
	            con_sched.setDate(conference);
	            f_accomplished.setDate(colloquiumacc);   
	            col_accomplished.setDate(forumacc);
	            conference_accomplished.setDate(conferenceacc);
	            

	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	void refreshAuthorsTable() {
		
		   authorstableModel.setRowCount(0);
	       loadAuthors(paperid);
		
	}
}