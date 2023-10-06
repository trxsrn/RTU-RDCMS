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
import java.awt.Label;
import java.awt.Button;

public class dashboard extends JFrame {
	

	private JPanel contentPane;
    private JTable facultytbl;
    private JTable researchtbl;
    private JTable departmenttbl;
    private DefaultTableModel facultytableModel;
    private DefaultTableModel researchtableModel;
    private DefaultTableModel thrusttblModel;
    private DefaultTableModel collegetblModel;
    private DefaultTableModel DepartmenttblModel;
    

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private JTextField faculty_search;
    private JTextField research_search;
    private JButton selectedButton = null;
    private JTable table_1;
    private JLabel dateTimeLabel;
    private JTable table;
    private JTable thrusttbl;
    private JComboBox<String> discipline; 
    private JTable collegetbl;
    private JTable table_2;

    
   
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    dashboard frame = new dashboard();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public dashboard() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\xampp\\htdocs\\TRR\\css\\img\\RDC logo 2.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1406, 843);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        
//	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//	    int centerX = (screenSize.width - getWidth()) / 2;
//	    int centerY = (screenSize.height - getHeight()) / 2;
//	    setLocation(centerX, centerY);
//	    getContentPane().setLayout(null);
//	    
        // Create a panel for the menu (25%)
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(SystemColor.textHighlight);
        menuPanel.setPreferredSize(new Dimension(1400 / 6, 834));
        contentPane.add(menuPanel);
        menuPanel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 31, 232, 167);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\TRIXIE\\Documents\\FOURTH YEAR\\System Integration\\New folder\\TRR\\css\\img\\RDC logo 2.png"));
        menuPanel.add(lblNewLabel);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 386, 232, 410);
        menuPanel.add(panel);
        panel.setLayout(new GridLayout(6, 1, 0, 0));
        

        // Create a panel for the content (75%)
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(1400 - (1400 / 6), 834));
        contentPane.add(content);
        content.setLayout(new CardLayout(0, 0));
        
        JPanel dashboard = new JPanel();
        content.add(dashboard, "name_86374878781900");
        dashboard.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.textHighlight);
        panel_1.setBounds(0, 0, 1152, 73);
        dashboard.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_3 = new JLabel("DASHBOARD");
        lblNewLabel_3.setForeground(SystemColor.text);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3.setBounds(25, 20, 160, 32);
        panel_1.add(lblNewLabel_3);

        
        dateTimeLabel = new JLabel();
        dateTimeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        dateTimeLabel.setForeground(SystemColor.text);
        dateTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        dateTimeLabel.setBounds(820, 20, 308, 32); // Adjust the position and size as needed
        panel_1.add(dateTimeLabel);

        // Create a Timer that updates the label every second (1000 milliseconds)
        int delay = 1000; // 1000 milliseconds = 1 second
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDateTimeLabel();
            }
        });
        timer.start();
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(0, 73, 1152, 723);
        dashboard.add(panel_2);
        panel_2.setLayout(null);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(SystemColor.textHighlight);
        panel_4.setBounds(795, 237, 347, 29);
        panel_2.add(panel_4);
        panel_4.setLayout(null);
        
        Label label_1 = new Label("MY TASK");
        label_1.setAlignment(Label.CENTER);
        label_1.setBounds(90, 0, 135, 29);
        label_1.setForeground(Color.WHITE);
        label_1.setFont(new Font("Dialog", Font.PLAIN, 10));
        panel_4.add(label_1);
        
        table_1 = new JTable();
        table_1.setBounds(795, 266, 347, 418);
        panel_2.add(table_1);
        
        JButton btnNewButton_2 = new JButton("ADD TASK");
        btnNewButton_2.setForeground(Color.WHITE);
        btnNewButton_2.setBackground(Color.YELLOW);
        btnNewButton_2.setBounds(791, 684, 349, 29);
        panel_2.add(btnNewButton_2);
        
        table = new JTable();
        table.setBounds(795, 60, 347, 167);
        panel_2.add(table);
        
        JLabel lblNewLabel_1 = new JLabel("Events");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(795, 21, 117, 29);
        panel_2.add(lblNewLabel_1);
        
        JLabel lblNewLabel_4 = new JLabel("FACULTY");
        lblNewLabel_4.setBounds(47, 424, 77, 13);
        panel_2.add(lblNewLabel_4);
        
        JLabel lblNewLabel_4_1 = new JLabel("RESEARCH");
        lblNewLabel_4_1.setBounds(477, 424, 77, 13);
        panel_2.add(lblNewLabel_4_1);
        
        JLabel lblNewLabel_5 = new JLabel("COLLOUIUM");
        lblNewLabel_5.setBounds(470, 590, 117, 13);
        panel_2.add(lblNewLabel_5);
        
        JLabel lblNewLabel_5_1 = new JLabel("FORUM");
        lblNewLabel_5_1.setBounds(470, 622, 117, 13);
        panel_2.add(lblNewLabel_5_1);
        
        JLabel lblNewLabel_5_1_1 = new JLabel("PUBLISH");
        lblNewLabel_5_1_1.setBounds(470, 645, 117, 13);
        panel_2.add(lblNewLabel_5_1_1);
        
        JLabel lblNewLabel_5_1_1_1 = new JLabel("CONFERENCE");
        lblNewLabel_5_1_1_1.setBounds(470, 675, 117, 13);
        panel_2.add(lblNewLabel_5_1_1_1);
        
        JLabel lblNewLabel_5_2 = new JLabel("99");
        lblNewLabel_5_2.setBounds(687, 590, 57, 13);
        panel_2.add(lblNewLabel_5_2);
        
        JLabel lblNewLabel_5_1_2 = new JLabel("99");
        lblNewLabel_5_1_2.setBounds(687, 622, 57, 13);
        panel_2.add(lblNewLabel_5_1_2);
        
        JLabel lblNewLabel_5_1_1_2 = new JLabel("99");
        lblNewLabel_5_1_1_2.setBounds(687, 645, 57, 13);
        panel_2.add(lblNewLabel_5_1_1_2);
        
        JLabel lblNewLabel_5_1_1_1_1 = new JLabel("99");
        lblNewLabel_5_1_1_1_1.setBounds(687, 675, 57, 13);
        panel_2.add(lblNewLabel_5_1_1_1_1);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(SystemColor.activeCaption);
        panel_5.setBounds(470, 464, 276, 94);
        panel_2.add(panel_5);
        panel_5.setLayout(null);
        
        JLabel lblNewLabel_5_2_1 = new JLabel("396");
        lblNewLabel_5_2_1.setBounds(105, 23, 117, 49);
        panel_5.add(lblNewLabel_5_2_1);
        lblNewLabel_5_2_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
        
        JPanel panel_5_1 = new JPanel();
        panel_5_1.setLayout(null);
        panel_5_1.setBackground(SystemColor.activeCaption);
        panel_5_1.setBounds(47, 464, 276, 94);
        panel_2.add(panel_5_1);
        
        JLabel lblNewLabel_5_2_1_2 = new JLabel("352");
        lblNewLabel_5_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
        lblNewLabel_5_2_1_2.setBounds(105, 23, 117, 49);
        panel_5_1.add(lblNewLabel_5_2_1_2);
        
        JLabel lblNewLabel_5_3 = new JLabel("CEA");
        lblNewLabel_5_3.setBounds(47, 590, 117, 13);
        panel_2.add(lblNewLabel_5_3);
        
        JLabel lblNewLabel_5_1_3 = new JLabel("CBEA");
        lblNewLabel_5_1_3.setBounds(47, 622, 117, 13);
        panel_2.add(lblNewLabel_5_1_3);
        
        JLabel lblNewLabel_5_1_1_3 = new JLabel("CAS");
        lblNewLabel_5_1_1_3.setBounds(47, 645, 117, 13);
        panel_2.add(lblNewLabel_5_1_1_3);
        
        JLabel lblNewLabel_5_1_1_1_2 = new JLabel("CONFERENCE");
        lblNewLabel_5_1_1_1_2.setBounds(47, 671, 117, 13);
        panel_2.add(lblNewLabel_5_1_1_1_2);
        
        JLabel lblNewLabel_5_2_2 = new JLabel("99");
        lblNewLabel_5_2_2.setBounds(266, 586, 57, 13);
        panel_2.add(lblNewLabel_5_2_2);
        
        JLabel lblNewLabel_5_1_2_1 = new JLabel("99");
        lblNewLabel_5_1_2_1.setBounds(266, 618, 57, 13);
        panel_2.add(lblNewLabel_5_1_2_1);
        
        JLabel lblNewLabel_5_1_1_2_1 = new JLabel("99");
        lblNewLabel_5_1_1_2_1.setBounds(266, 641, 57, 13);
        panel_2.add(lblNewLabel_5_1_1_2_1);
        
        JLabel lblNewLabel_5_1_1_1_1_1 = new JLabel("99");
        lblNewLabel_5_1_1_1_1_1.setBounds(266, 671, 57, 13);
        panel_2.add(lblNewLabel_5_1_1_1_1_1);
        
        JPanel faculty = new JPanel();
        faculty.setBackground(Color.WHITE);
        content.add(faculty, "name_86384982282300");
        faculty.setLayout(null);
        
        JScrollPane facultyscrollPane = new JScrollPane();
        facultyscrollPane.setBounds(10, 128, 1124, 607);
        faculty.add(facultyscrollPane);
        
        facultytableModel = new DefaultTableModel();
        facultytbl = new JTable(facultytableModel) {
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        facultytbl.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		  if (e.getClickCount() == 2) { // Detect double-click
        	            int selectedRow = facultytbl.getSelectedRow();
        	            if (selectedRow >= 0) {
        	                // Get the selected faculty's data from the table model
        	                String faculty_Id = facultytableModel.getValueAt(selectedRow, 0).toString();
        	                String faculty_Name = facultytableModel.getValueAt(selectedRow, 1).toString();
        	                String faculty_Affiliation = facultytableModel.getValueAt(selectedRow, 2).toString();
        	                String faculty_College = facultytableModel.getValueAt(selectedRow, 3).toString();
        	                String faculty_Department = facultytableModel.getValueAt(selectedRow, 4).toString();
        	                
        	                
        	                //Gets the data and transfers it to the next window 
        	                facultyDetails facultyDetails = new facultyDetails(faculty_Id, faculty_Name, faculty_Affiliation, faculty_College, faculty_Department);
        	                facultyDetails.setVisible(true);
        	                
        	            }
        	        }
        	}
        });
        facultyscrollPane.setViewportView(facultytbl);
        
        JButton btnNewButton_1 = new JButton("ADD");
        btnNewButton_1.setBackground(new Color(50, 205, 50));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		addnewfaculty addnewfaculty = new addnewfaculty(dashboard.this);
    			addnewfaculty.setVisible(true);
    	
        	}
        });
        btnNewButton_1.setBounds(857, 83, 277, 35);
        faculty.add(btnNewButton_1);
        
        JComboBox sortcombo = new JComboBox();
        sortcombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sort = sortcombo.getSelectedItem().toString();
                String orderBy = "ORDER BY name"; // Replace 'column_name' with the actual column name
                
                if ("A-Z".equals(sort)) {
                    // Sort in ascending order
                    orderBy += " ASC";
                } else if ("Z-A".equals(sort)) {
                    // Sort in descending order
                    orderBy += " DESC";
                }

                try {
                    // Establish a database connection (you need to fill in the connection details)
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");
                    
                    // Create a SQL query with sorting
                    String query = "SELECT * FROM faculty " + orderBy;
                    
                    // Create a PreparedStatement
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    
                    // Execute the query and retrieve the results
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    // Clear the table model
                    facultytableModel.setRowCount(0);
                    
                    // Populate the table with the sorted data
                    while (resultSet.next()) {
                        // Extract data from the result set (adjust column names accordingly)
                    	String facultyId = resultSet.getString("id");
                        String facultyName = resultSet.getString("name");
                        String facultyAffiliation = resultSet.getString("affiliation");
                        String facultyCollege = resultSet.getString("college");
                        String facultyDepartment = resultSet.getString("department");
                        
                        // Add the data to the table model
                        facultytableModel.addRow(new Object[]{facultyId, facultyName, facultyAffiliation, facultyCollege,facultyDepartment});
                    }
                    
                    // Close resources (result set, statement, and connection)
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle any exceptions that may occur while querying the database
                }
            }
        });
        sortcombo.setBounds(96, 83, 192, 35);
        faculty.add(sortcombo);
        
        sortcombo.addItem("A-Z");
        sortcombo.addItem("Z-A");
        
        JComboBox filtercombo = new JComboBox();
        filtercombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filter = filtercombo.getSelectedItem().toString();
                
                if (!"SELECT".equals(filter)) {
                    try {
                        // Establish a database connection (you need to fill in the connection details)
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");
                        
                        // Create a SQL query with a WHERE clause to filter data
                        String query = "SELECT * FROM faculty WHERE college = ?";
                        
                        // Create a PreparedStatement and set the filter value as a parameter
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, filter);
                        
                        // Execute the query and retrieve the results
                        ResultSet resultSet = preparedStatement.executeQuery();
                        
                        // Clear the table model
                        facultytableModel.setRowCount(0);
                        
                        // Populate the table with the filtered data
                        while (resultSet.next()) {
                            // Extract data from the result set (adjust column names accordingly)
                            String facultyId = resultSet.getString("id");
                            String facultyName = resultSet.getString("name");
                            String facultyAffiliation = resultSet.getString("affiliation");
                            String facultyCollege = resultSet.getString("college");
                            String facultyDepartment = resultSet.getString("department");
                            
                            // Add the data to the table model
                            facultytableModel.addRow(new Object[]{facultyId, facultyName, facultyAffiliation, facultyCollege,facultyDepartment});
                        }
                        
                        // Close resources (result set, statement, and connection)
                        resultSet.close();
                        preparedStatement.close();
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        // Handle any exceptions that may occur while querying the database
                    }
                } else {
                    // If "SELECT" is selected, show all rows by reloading the full data
                    loadFacultyData();
                }
            }
        });

        filtercombo.setBounds(384, 83, 192, 35);
        faculty.add(filtercombo);
        
		filtercombo.addItem("CEA");
		filtercombo.addItem("CED");
		filtercombo.addItem("CBEA");
		filtercombo.addItem("CAS");
		filtercombo.addItem("GS");
		filtercombo.addItem("IHK");
        
        JLabel lblNewLabel_2 = new JLabel("SORT BY");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(10, 94, 126, 13);
        faculty.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("FILTER BY:");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2_1.setBounds(298, 94, 76, 13);
        faculty.add(lblNewLabel_2_1);
        
        JButton btnNewButton_1_1 = new JButton("RESET FILTER/SORT");
        btnNewButton_1_1.setBackground(Color.ORANGE);
        btnNewButton_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		loadFacultyData();
        	}
        });
        btnNewButton_1_1.setBounds(586, 83, 261, 35);
        faculty.add(btnNewButton_1_1);
        
        JButton btnNewButton_4 = new JButton("GENERATE A PDF");
        btnNewButton_4.setForeground(SystemColor.text);
        btnNewButton_4.setBackground(SystemColor.textHighlight);
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_4.setBounds(10, 745, 1124, 41);
        faculty.add(btnNewButton_4);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(SystemColor.textHighlight);
        panel_1_1.setBounds(0, 0, 1152, 73);
        faculty.add(panel_1_1);
        
        JLabel lblNewLabel_3_1 = new JLabel("FACULTY");
        lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3_1.setForeground(SystemColor.text);
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_1.setBounds(25, 20, 160, 32);
        panel_1_1.add(lblNewLabel_3_1);
        
        
        faculty_search = new JTextField();
        faculty_search.setBounds(471, 20, 511, 35);
        panel_1_1.add(faculty_search);
        faculty_search.setColumns(10);
        
        JButton btnNewButton = new JButton("SEARCH");
        btnNewButton.setBounds(992, 20, 150, 35);
        panel_1_1.add(btnNewButton);
        btnNewButton.setForeground(SystemColor.text);
        btnNewButton.setBackground(SystemColor.textHighlight);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String searchQuery = faculty_search.getText().trim();
                FacultySearch.searchFacultyData(facultytableModel, searchQuery);
        	}
        });
        
        JPanel research = new JPanel();
        research.setBackground(new Color(255, 255, 255));
        content.add(research, "name_86387158939100");
        research.setLayout(null);
        
        JButton btnNewButton_1_2 = new JButton("ADD");
        btnNewButton_1_2.setBackground(new Color(50, 205, 50));
        btnNewButton_1_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		addnewresearch addnewresearch = new addnewresearch(dashboard.this);
    			addnewresearch.setVisible(true);
        	}
        });
        btnNewButton_1_2.setBounds(857, 83, 277, 35);
        research.add(btnNewButton_1_2);
        
        JButton btnNewButton_1_1_1 = new JButton("RESET FILTER/SORT");
        btnNewButton_1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		researchtableModel.setRowCount(0);
                autoupdate_research.loadResearchData(researchtableModel);
        	}
        });
        btnNewButton_1_1_1.setBackground(Color.ORANGE);
        btnNewButton_1_1_1.setBounds(586, 83, 261, 35);
        research.add(btnNewButton_1_1_1);
        
        discipline = new JComboBox<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
            // Query the database to fetch discipline data
            String query = "SELECT abbreviation FROM research_thrust";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Populate the dropdown with the retrieved data
            while (resultSet.next()) {
                String disciplineName = resultSet.getString("abbreviation");
                discipline.addItem(disciplineName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        discipline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filter = discipline.getSelectedItem().toString();
                
                if (!"SELECT".equals(filter)) {
                    try {
                        // Establish a database connection (you need to fill in the connection details)
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");
                        
                        // Modify the query to use LIKE and pass the filter as a parameter
                        String query = "SELECT * FROM research_summary WHERE paper_id LIKE ?";
                        
                        // Create a PreparedStatement and set the filter value as a parameter
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "%" + filter + "%");
                        
                        // Execute the query and retrieve the results
                        ResultSet resultSet = preparedStatement.executeQuery();
                        
                        // Clear the table model
                        researchtableModel.setRowCount(0);
                        
                        boolean recordsFound = false;
                        
                        // Populate the table with the filtered data
                        while (resultSet.next()) {
                            // Extract data from the result set (adjust column names accordingly)
                            String paperId = resultSet.getString("paper_id");
                            String title = resultSet.getString("title");
                            String status = resultSet.getString("status");
                            
                            // Add the data to the table model
                            researchtableModel.addRow(new Object[]{paperId, title, status});
                            
                            recordsFound = true;
                        }
                        
                        // Close resources (result set, statement, and connection)
                        resultSet.close();
                        preparedStatement.close();
                        connection.close();
                        
                        if (!recordsFound) {
                            // Display a message in the table itself
                            researchtableModel.addRow(new Object[]{"No records found", "", ""});
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        // Handle any exceptions that may occur while querying the database
                    }
                } else {
                    // If "SELECT" is selected, show all rows by reloading the full data
                    loadResearchData();
                }
            }
        });
        discipline.setBounds(384, 83, 192, 35);
        research.add(discipline);
        
        
        JComboBox sortcombo_1 = new JComboBox();
        sortcombo_1.addItem("A-Z");
        sortcombo_1.addItem("Z-A");
        sortcombo_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 String sort = sortcombo_1.getSelectedItem().toString();
                 String orderBy = "ORDER BY title"; // Replace 'column_name' with the actual column name
                 
                 if ("A-Z".equals(sort)) {
                     // Sort in ascending order
                     orderBy += " ASC";
                 } else if ("Z-A".equals(sort)) {
                     // Sort in descending order
                     orderBy += " DESC";
                 }

                 try {
                     // Establish a database connection (you need to fill in the connection details)
                     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");
                     
                     // Create a SQL query with sorting
                     String query = "SELECT * FROM research_summary " + orderBy;
                     
                     // Create a PreparedStatement
                     PreparedStatement preparedStatement = connection.prepareStatement(query);
                     
                     // Execute the query and retrieve the results
                     ResultSet resultSet = preparedStatement.executeQuery();
                     
                     // Clear the table model
                     researchtableModel.setRowCount(0);
                     
                     // Populate the table with the sorted data
                     while (resultSet.next()) {
                         // Extract data from the result set (adjust column names accordingly)
                     	String paperId = resultSet.getString("paper_id");
                         String title = resultSet.getString("title");
                         String status = resultSet.getString("status");
                         // Add the data to the table model
                         researchtableModel.addRow(new Object[]{paperId, title, status});
                     }
                     
                     // Close resources (result set, statement, and connection)
                     resultSet.close();
                     preparedStatement.close();
                     connection.close();
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                     // Handle any exceptions that may occur while querying the database
                 }
             }
        });
        sortcombo_1.setBounds(96, 83, 192, 35);
        research.add(sortcombo_1);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("FILTER BY:");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2_1_1.setBounds(298, 94, 76, 13);
        research.add(lblNewLabel_2_1_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("SORT BY");
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2_2.setBounds(10, 94, 126, 13);
        research.add(lblNewLabel_2_2);
        
       
        JScrollPane researchscrollPane = new JScrollPane();
        researchscrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
        researchscrollPane.setBounds(10, 128, 1124, 607);
        research.add(researchscrollPane);
        
        researchtableModel = new DefaultTableModel();
        researchtbl = new JTable(researchtableModel) {
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        researchtbl.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		  if (e.getClickCount() == 2) { // Detect double-click
        	            int selectedRow = researchtbl.getSelectedRow();
        	            if (selectedRow >= 0) {
        	                // Get the selected faculty's data from the table model
        	                String paperid = researchtableModel.getValueAt(selectedRow, 0).toString();
        	                String papertitle = researchtableModel.getValueAt(selectedRow, 1).toString();
        	                String paperfaculty = researchtableModel.getValueAt(selectedRow, 2).toString();
//        	         
        	                researchDetails researchDetails = new researchDetails(paperid, papertitle);
        	                researchDetails.setVisible(true);
        	            }
        	        }
        	}
        });
        researchscrollPane.setViewportView(researchtbl);
        
        
        JPanel panel_1_1_1 = new JPanel();
        panel_1_1_1.setLayout(null);
        panel_1_1_1.setBackground(SystemColor.textHighlight);
        panel_1_1_1.setBounds(0, 0, 1152, 73);
        research.add(panel_1_1_1);
        
        JLabel lblNewLabel_3_1_1 = new JLabel("RESEARCH");
        lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3_1_1.setForeground(SystemColor.text);
        lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_1_1.setBounds(25, 20, 160, 32);
        panel_1_1_1.add(lblNewLabel_3_1_1);
        
        research_search = new JTextField();
        research_search.setColumns(10);
        research_search.setBounds(471, 20, 511, 35);
        panel_1_1_1.add(research_search);
        
        JButton btnNewButton_5 = new JButton("SEARCH");
        btnNewButton_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String searchQuery = research_search.getText().trim();
                ResearchSearch.searchResearchData(researchtableModel, searchQuery);
        	}
        });
        btnNewButton_5.setForeground(SystemColor.text);
        btnNewButton_5.setBackground(SystemColor.textHighlight);
        btnNewButton_5.setBounds(992, 20, 150, 35);
        panel_1_1_1.add(btnNewButton_5);
        
        JButton btnNewButton_4_1 = new JButton("GENERATE A PDF");
        btnNewButton_4_1.setForeground(SystemColor.text);
        btnNewButton_4_1.setBackground(SystemColor.textHighlight);
        btnNewButton_4_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_4_1.setBounds(10, 745, 1124, 41);
        research.add(btnNewButton_4_1);
        
        JPanel logout = new JPanel();
        content.add(logout, "name_86389111069900");
        
        JPanel manage = new JPanel();
        content.add(manage, "name_33315508697500");
        manage.setLayout(null);
        
        JPanel panel_1_2 = new JPanel();
        panel_1_2.setLayout(null);
        panel_1_2.setBackground(SystemColor.textHighlight);
        panel_1_2.setBounds(0, 0, 1152, 73);
        manage.add(panel_1_2);
        
        JLabel lblNewLabel_3_2 = new JLabel("MANAGE ORGANIZATION");
        lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3_2.setForeground(SystemColor.text);
        lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_2.setBounds(25, 20, 245, 32);
        panel_1_2.add(lblNewLabel_3_2);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(0, 74, 1152, 722);
        manage.add(scrollPane_2);
        
        JPanel panel_3 = new JPanel();
        scrollPane_2.setViewportView(panel_3);
        panel_3.setLayout(null);
        
        Label label = new Label("Research Thrust");
        label.setBounds(39, 40, 184, 21);
        panel_3.add(label);
        
        Button button = new Button("ADD");
        button.setForeground(SystemColor.text);
        button.setBackground(SystemColor.textHighlight);
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		addnewresearchthrust addnewresearchthrust = new addnewresearchthrust(dashboard.this);
        		addnewresearchthrust.setVisible(true);
        		
        	}
        });
        button.setBounds(982, 40, 108, 21);
        panel_3.add(button);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(39, 78, 1057, 146);
        panel_3.add(scrollPane);
        thrusttblModel = new DefaultTableModel();
        thrusttbl = new JTable(thrusttblModel) {
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        thrusttbl.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		  if (e.getClickCount() == 2) { // Detect double-click
        	            int selectedRow = thrusttbl.getSelectedRow();
        	            if (selectedRow >= 0) {
        	                // Get the selected faculty's data from the table model
        	                String abb = thrusttblModel.getValueAt(selectedRow, 0).toString();
        	                String name = thrusttblModel.getValueAt(selectedRow, 1).toString();
        	                String desc = thrusttblModel.getValueAt(selectedRow, 2).toString();
//        	         
        	                thrustDetails thrustDetails = new thrustDetails(abb, name, desc);
        	                thrustDetails.setVisible(true);
        	            }
        	        }
        	}
        });
        scrollPane.setViewportView(thrusttbl);
        
        Label label_2 = new Label("College");
        label_2.setBounds(39, 244, 184, 21);
        panel_3.add(label_2);
        
        Button button_1 = new Button("ADD");
        button_1.setForeground(SystemColor.text);
        button_1.setBackground(SystemColor.textHighlight);
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		addnewcollege addnewcollege = new addnewcollege(dashboard.this);
        		addnewcollege.setVisible(true);
        	}
        });
        button_1.setBounds(982, 244, 108, 21);
        panel_3.add(button_1);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(39, 289, 1057, 146);
        panel_3.add(scrollPane_1);
        
        collegetblModel = new DefaultTableModel();
        collegetbl = new JTable(collegetblModel) {
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        collegetbl.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		  if (e.getClickCount() == 2) { // Detect double-click
        	            int selectedRow = collegetbl.getSelectedRow();
        	            if (selectedRow >= 0) {
        	                // Get the selected faculty's data from the table model
        	                String abb = collegetblModel.getValueAt(selectedRow, 0).toString();
        	                String name = collegetblModel.getValueAt(selectedRow, 1).toString();
        	                String desc = collegetblModel.getValueAt(selectedRow, 2).toString();
//        	         
        	                collegeDetails collegeDetails = new collegeDetails(abb, name, desc);
        	                collegeDetails.setVisible(true);
        	            }
        	        }
        	}
        });
        scrollPane_1.setViewportView(collegetbl);
        
        Label label_2_1 = new Label("Department");
        label_2_1.setBounds(39, 459, 184, 21);
        panel_3.add(label_2_1);
        
        Button button_1_1 = new Button("ADD");
        button_1_1.setForeground(SystemColor.text);
        button_1_1.setBackground(SystemColor.textHighlight);
        button_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		addnewdepartment addnewdepartment = new addnewdepartment(dashboard.this);
        		addnewdepartment.setVisible(true);
  
        	}
        });
        button_1_1.setBounds(982, 459, 108, 21);
        panel_3.add(button_1_1);
        
        
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(39, 511, 1057, 146);
        panel_3.add(scrollPane_3);
        
        DepartmenttblModel = new DefaultTableModel();
        departmenttbl = new JTable(DepartmenttblModel) {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        departmenttbl.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		  if (e.getClickCount() == 2) { // Detect double-click
        	            int selectedRow = departmenttbl.getSelectedRow();
        	            if (selectedRow >= 0) {
        	                // Get the selected faculty's data from the table model
        	                String college = DepartmenttblModel.getValueAt(selectedRow, 0).toString();
        	                String name = DepartmenttblModel.getValueAt(selectedRow, 1).toString();
//        	         
        	            }
        	        }
        	}
        });
        scrollPane_3.setViewportView(departmenttbl);
        
        
       
        
        JPanel settings = new JPanel();
        content.add(settings, "name_33385328022900");
        settings.setLayout(null);
        
        JPanel panel_1_3 = new JPanel();
        panel_1_3.setLayout(null);
        panel_1_3.setBackground(SystemColor.textHighlight);
        panel_1_3.setBounds(0, 0, 1152, 73);
        settings.add(panel_1_3);
        
        JLabel lblNewLabel_3_3 = new JLabel("SETTINGS");
        lblNewLabel_3_3.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3_3.setForeground(SystemColor.text);
        lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_3.setBounds(25, 20, 160, 32);
        panel_1_3.add(lblNewLabel_3_3);
        
        JButton dashboardbtn = new JButton("DASHBOARD");
        dashboardbtn.setBorderPainted(false);
        dashboardbtn.setHorizontalAlignment(SwingConstants.LEFT);
        dashboardbtn.setBackground(SystemColor.textHighlight);
        dashboardbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		selectButton(dashboardbtn);
                content.removeAll();
                content.revalidate();
                content.add(dashboard);
                content.revalidate();
                content.repaint();
                loadResearchData();
        		
        	}
        });
        panel.add(dashboardbtn);
        dashboardbtn.setForeground(SystemColor.text);
        
        JButton researchbtn = new JButton("RESEARCH");
        researchbtn.setBorderPainted(false);
        researchbtn.setHorizontalAlignment(SwingConstants.LEFT);
        researchbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		  	selectButton(researchbtn);
        	        content.removeAll();
        	        content.revalidate();
        	        content.add(research);
        	        content.revalidate();
        	        content.repaint();
        	        loadResearchData();
 
        	}
        });
        panel.add(researchbtn);
        researchbtn.setForeground(SystemColor.text);
        researchbtn.setBackground(SystemColor.textHighlight);
        
        JButton facultybtn = new JButton("FACULTY");
        facultybtn.setBorderPainted(false);
        facultybtn.setHorizontalAlignment(SwingConstants.LEFT);
        facultybtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		  	selectButton(facultybtn);
        	        content.removeAll();
        	        content.revalidate();
        	        content.add(faculty);
        	        content.revalidate();
        	        content.repaint();
        	        loadFacultyData();
        		
        	}
        });
        panel.add(facultybtn);
        facultybtn.setForeground(SystemColor.text);
        facultybtn.setBackground(SystemColor.textHighlight);
        
        JButton managebtn = new JButton("MANAGE ORGANIZATION");
        managebtn.setBorderPainted(false);
        managebtn.setHorizontalAlignment(SwingConstants.LEFT);
        managebtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		selectButton(managebtn);
    	        content.removeAll();
    	        content.revalidate();
    	        content.add(manage);
    	        content.revalidate();
    	        content.repaint();
    	        loadResearchThrust();
    	        loadCollege();
    	        loadDepartment();
        	}
        });
        managebtn.setForeground(SystemColor.text);
        managebtn.setBackground(SystemColor.textHighlight);
        panel.add(managebtn);
        
        JButton settingsbtn = new JButton("SETTINGS");
        settingsbtn.setBorderPainted(false);
        settingsbtn.setHorizontalAlignment(SwingConstants.LEFT);
        settingsbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		selectButton(settingsbtn);
    	        content.removeAll();
    	        content.revalidate();
    	        content.add(settings);
    	        content.revalidate();
    	        content.repaint();
    	        loadFacultyData();
        	}
        });
        panel.add(settingsbtn);
        settingsbtn.setForeground(SystemColor.text);
        settingsbtn.setBackground(SystemColor.textHighlight);
        
        JButton logoutbtn = new JButton("LOGOUT");
        logoutbtn.setBorderPainted(false);
        logoutbtn.setHorizontalAlignment(SwingConstants.LEFT);
        logoutbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        	}
        });
        logoutbtn.setBackground(SystemColor.textHighlight);
        panel.add(logoutbtn);
        logoutbtn.setForeground(SystemColor.text);
        
    }
    
    private void updateDateTimeLabel() {
        // Create a SimpleDateFormat object to format the date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");

        // Get the current date and time
        Date currentDate = new Date();

        // Format the date and time as a string
        String formattedDate = dateFormat.format(currentDate);

        // Set the formatted date and time as the text of the JLabel
        dateTimeLabel.setText(formattedDate);
    }


    private void loadFacultyData() {
        // Call the loadFacultyData method from the FacultyDataAccess class
    	facultytableModel.setRowCount(0);
        autoupdate_faculty.loadFacultyData(facultytableModel);
    }
    
    private void loadResearchData() {
        // Call the loadFacultyData method from the FacultyDataAccess class
    	researchtableModel.setRowCount(0);
        autoupdate_research.loadResearchData(researchtableModel);
    }
    
    private void loadResearchThrust() {
        // Call the loadFacultyData method from the FacultyDataAccess class
    	thrusttblModel.setRowCount(0);
        load_researchthrust.loadResearchThrust(thrusttblModel);
    }
    
    private void loadCollege() {
        // Call the loadFacultyData method from the FacultyDataAccess class
    	collegetblModel.setRowCount(0);
        loadcollege.loadCollege(collegetblModel);
    }
    
    private void loadDepartment() {
        // Call the loadFacultyData method from the FacultyDataAccess class
    	DepartmenttblModel.setRowCount(0);
        loadDepartment.loadDepartment(DepartmenttblModel);
    }
    
    private void selectButton(JButton button) {
        if (selectedButton != null) {
            selectedButton.setBackground(SystemColor.textHighlight);
        }
        button.setBackground(new Color(249, 228, 23));
        selectedButton = button;
    }

    
    void refreshFacultyTable() {
        facultytableModel.setRowCount(0); // Clear the current table data
        loadFacultyData(); // Load the updated faculty data
    }
    
    void refreshResearchTable() {
        researchtableModel.setRowCount(0); // Clear the current table data
        loadResearchData(); // Load the updated faculty data
    }
    
    void refreshThrustTable() {
    	thrusttblModel.setRowCount(0); // Clear the current table data
    	loadResearchThrust(); // Load the updated faculty data
    }
    
    void refreshCollegeTable() {
    	collegetblModel.setRowCount(0); // Clear the current table data
    	loadCollege(); // Load the updated faculty data
    }
    
    void refreshDepartmentTable() {
    	DepartmenttblModel.setRowCount(0); // Clear the current table data
    	loadDepartment(); // Load the updated faculty data
    }
}
