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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.BorderLayout;
import com.toedter.calendar.JCalendar;
import java.awt.Label;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


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
    private DefaultTableModel TasktblModel;
    

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private JTextField faculty_search;
    private JTextField research_search;
    private JButton selectedButton = null;
    private JLabel dateTimeLabel;
    private JTable scheduletbl;
    private JTable thrusttbl;
    private JComboBox<String> discipline; 
    private JTable collegetbl;
    private JTable table_2;
    private JTable tasktbl;
    private JTextField textField;
    private JTextField textField_1;

    
   
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
    	setResizable(false);
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\TRIXIE\\Documents\\FOURTH YEAR\\System Integration\\New folder\\TRR\\css\\img\\RDC logo 2.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1495, 843);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        int facultyCount = retrieveFacultyCountFromDatabase();
        int ResearchCount = retrieveResearchCountFromDatabase();
        int ColCount = retrieveColloquiumCount();
        int ForumCount = retrieveForumCount();
        int publishCount = retrievePublishCount();
        int conferenceCount = retrieveConferenceCount();
        		
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int centerX = (screenSize.width - getWidth()) / 2;
	    int centerY = (screenSize.height - getHeight()) / 2;
	    setLocation(centerX, centerY);
	    getContentPane().setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
       
        
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
        panel.setBorder(null);
        panel.setBounds(0, 386, 232, 410);
        menuPanel.add(panel);
        panel.setLayout(new GridLayout(7, 1, 0, 0));
        

        // Create a panel for the content (75%)
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(1400 - (1400 / 7), 834));
        contentPane.add(content);
        content.setLayout(new CardLayout(0, 0));
        
        JPanel dashboard = new JPanel();
        content.add(dashboard, "name_86374878781900");
        dashboard.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.textHighlight);
        panel_1.setBounds(0, 0, 1237, 73);
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
        dateTimeLabel.setBounds(919, 20, 308, 32); // Adjust the position and size as needed
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
        panel_2.setBounds(0, 73, 1237, 723);
        dashboard.add(panel_2);
        panel_2.setLayout(null);
        
        JButton btnNewButton_2 = new JButton("ADD TASK");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		newtask newtask = new newtask(dashboard.this);
    			newtask.setVisible(true);
        	}
        });
        btnNewButton_2.setForeground(SystemColor.controlText);
        btnNewButton_2.setBackground(Color.YELLOW);
        btnNewButton_2.setBounds(793, 684, 434, 29);
        panel_2.add(btnNewButton_2);
        
        JLabel lblNewLabel_4 = new JLabel("FACULTY");
        lblNewLabel_4.setBounds(793, 96, 77, 13);
        panel_2.add(lblNewLabel_4);
        
        JLabel lblNewLabel_4_1 = new JLabel("RESEARCH");
        lblNewLabel_4_1.setBounds(791, 220, 77, 13);
        panel_2.add(lblNewLabel_4_1);
        
        JLabel lblNewLabel_5 = new JLabel("COLLOQUIUM");
        lblNewLabel_5.setBounds(998, 243, 84, 13);
        panel_2.add(lblNewLabel_5);
        
        JLabel lblNewLabel_5_1 = new JLabel("FORUM");
        lblNewLabel_5_1.setBounds(998, 266, 84, 13);
        panel_2.add(lblNewLabel_5_1);
        
        JLabel lblNewLabel_5_1_1 = new JLabel("PUBLISH");
        lblNewLabel_5_1_1.setBounds(998, 289, 84, 13);
        panel_2.add(lblNewLabel_5_1_1);
        
        JLabel lblNewLabel_5_1_1_1 = new JLabel("CONFERENCE");
        lblNewLabel_5_1_1_1.setBounds(998, 312, 110, 13);
        panel_2.add(lblNewLabel_5_1_1_1);
        
        JLabel colloquiumcount = new JLabel(String.valueOf(ColCount));
        colloquiumcount.setBounds(1170, 243, 57, 13);
        panel_2.add(colloquiumcount);
        
        JLabel forumcount = new JLabel(String.valueOf(ForumCount));
        forumcount.setBounds(1170, 266, 57, 13);
        panel_2.add(forumcount);
        
        JLabel publishcount = new JLabel(String.valueOf(publishCount));
        publishcount.setBounds(1170, 289, 57, 13);
        panel_2.add(publishcount);
        
        JLabel conferencecount = new JLabel(String.valueOf(conferenceCount));
        conferencecount.setBounds(1170, 312, 57, 13);
        panel_2.add(conferencecount);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(SystemColor.textHighlight);
        panel_5.setBounds(791, 243, 197, 94);
        panel_2.add(panel_5);
        panel_5.setLayout(null);
        
        JLabel noofresearch = new JLabel(String.valueOf(ResearchCount));
        noofresearch.setForeground(SystemColor.text);
        noofresearch.setHorizontalAlignment(SwingConstants.CENTER);
        noofresearch.setBounds(38, 24, 117, 49);
        panel_5.add(noofresearch);
        noofresearch.setFont(new Font("Tahoma", Font.PLAIN, 40));
        
        JPanel no_of_faculty = new JPanel();
        no_of_faculty.setLayout(null);
        no_of_faculty.setBackground(SystemColor.textHighlight);
        no_of_faculty.setBounds(793, 116, 195, 94);
        panel_2.add(no_of_faculty);
        
        JLabel nooff = new JLabel(String.valueOf(facultyCount));
        nooff.setForeground(SystemColor.text);
        nooff.setHorizontalAlignment(SwingConstants.CENTER);
        nooff.setFont(new Font("Tahoma", Font.PLAIN, 40));
        nooff.setBounds(38, 23, 117, 49);
        no_of_faculty.add(nooff);
        
        JScrollPane taskscrollpane = new JScrollPane();
        taskscrollpane.setBounds(793, 384, 434, 304);
        panel_2.add(taskscrollpane);
        
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(SystemColor.textHighlight);
        panel_4.setBounds(793, 347, 434, 38);
        panel_2.add(panel_4);
        panel_4.setLayout(null);
        
        JLabel lblNewLabel_6 = new JLabel("MY TASK");
        lblNewLabel_6.setBounds(0, 0, 117, 38);
        panel_4.add(lblNewLabel_6);
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_6.setForeground(SystemColor.text);
        lblNewLabel_6.setBackground(SystemColor.textHighlight);
        
        JComboBox tasklevel = new JComboBox();
        tasklevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                String filter = tasklevel.getSelectedItem().toString();

                if ("Priority".equals(filter) || "Regular".equals(filter) || "Urgent".equals(filter)
                        || "Extra".equals(filter) || "Important".equals(filter) || "Low Priority".equals(filter)) {
                    // If a task level is selected, call the loadTaskData method with the filter
                    loadTaskByLevel(TasktblModel, tasklevel);
                } else {
                    // If no task level is selected, call the loadTaskData method without a filter
                	loadAllTaskData(TasktblModel);
                }
            }
        });
        tasklevel.setForeground(SystemColor.text);
        tasklevel.setBorder(null);
		tasklevel.addItem("Priority");
		tasklevel.addItem("Regular");
		tasklevel.addItem("Urgent");
		tasklevel.addItem("Extra");
		tasklevel.addItem("Important");
		tasklevel.addItem("Low Priority");
        tasklevel.setBackground(SystemColor.textHighlight);
        tasklevel.setBounds(206, 5, 218, 29);
        panel_4.add(tasklevel);
        
        TasktblModel = new DefaultTableModel();
        tasktbl = new JTable(TasktblModel) {
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        taskscrollpane.setViewportView(tasktbl);
        
        
        loadAllTaskData(TasktblModel);
        
        JPanel panel_6 = new JPanel();
        panel_6.setBackground(Color.YELLOW);
        panel_6.setBounds(28, 23, 1180, 63);
        panel_2.add(panel_6);
        panel_6.setLayout(null);
        
        JLabel lblNewLabel_8 = new JLabel("Good Morning!");
        lblNewLabel_8.setBounds(0, 0, 1180, 63);
        panel_6.add(lblNewLabel_8);
        lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel panel_7 = new JPanel();
        panel_7.setBackground(SystemColor.info);
        panel_7.setBounds(30, 96, 753, 617);
        panel_2.add(panel_7);
        panel_7.setLayout(null);
        
        JPanel panel_9 = new JPanel();
        panel_9.setBackground(SystemColor.textHighlight);
        panel_9.setBounds(0, 287, 753, 42);
        panel_7.add(panel_9);
        panel_9.setLayout(null);
        
        JLabel lblNewLabel_9 = new JLabel("MONTHLY SCHEDULE");
        lblNewLabel_9.setForeground(SystemColor.text);
        lblNewLabel_9.setBounds(0, 0, 753, 42);
        panel_9.add(lblNewLabel_9);
        lblNewLabel_9.setBackground(SystemColor.textHighlight);
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
        
        scheduletbl = new JTable();
        scheduletbl.setBounds(434, 328, 319, 289);
        panel_7.add(scheduletbl);
        
        JCalendar calendar_1 = new JCalendar();
        calendar_1.getDayChooser().getDayPanel().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		if (e.getClickCount() == 2) {
                    JCalendar calendar = (JCalendar) e.getSource();
                    String selectedDate = calendar.getDate().toString();

                    // Open a new window with the selected date
                    JFrame newFrame = new JFrame("Selected Date");
                    JPanel panel = new JPanel();
                    JLabel label = new JLabel("Selected Date: " + selectedDate);
                    panel.add(label);
                    newFrame.getContentPane().add(panel);
                    newFrame.setSize(200, 100);
//                    newFrame.setLocationRelativeTo(parentFrame);
                    newFrame.setVisible(true);
                }
        	}
        });
        calendar_1.getDayChooser().getDayPanel().setBorder(new EmptyBorder(0, 0, 0, 0));
        calendar_1.getDayChooser().getDayPanel().setForeground(new Color(25, 25, 112));
        calendar_1.setBounds(0, 328, 434, 289);
        panel_7.add(calendar_1);
        calendar_1.setDecorationBackgroundVisible(false);
        calendar_1.setDecorationBackgroundColor(Color.WHITE);
        calendar_1.setWeekOfYearVisible(false);
        
        JPanel panel_8 = new JPanel();
        panel_8.setBackground(SystemColor.textHighlight);
        panel_8.setBounds(66, 83, 71, 81);
        panel_7.add(panel_8);
        panel_8.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("00");
        lblNewLabel_1.setForeground(SystemColor.text);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(16, 29, 45, 25);
        panel_8.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        
        JPanel panel_8_1 = new JPanel();
        panel_8_1.setLayout(null);
        panel_8_1.setBackground(SystemColor.textHighlight);
        panel_8_1.setBounds(203, 83, 71, 81);
        panel_7.add(panel_8_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("00");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_1_1.setBounds(16, 29, 45, 25);
        panel_8_1.add(lblNewLabel_1_1);
        
        JPanel panel_8_1_1 = new JPanel();
        panel_8_1_1.setLayout(null);
        panel_8_1_1.setBackground(SystemColor.textHighlight);
        panel_8_1_1.setBounds(340, 83, 71, 81);
        panel_7.add(panel_8_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("00");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_1_1_1.setBounds(16, 29, 45, 25);
        panel_8_1_1.add(lblNewLabel_1_1_1);
        
        JPanel panel_8_1_1_1 = new JPanel();
        panel_8_1_1_1.setLayout(null);
        panel_8_1_1_1.setBackground(SystemColor.textHighlight);
        panel_8_1_1_1.setBounds(477, 83, 71, 81);
        panel_7.add(panel_8_1_1_1);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("00");
        lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_1_1_1_1.setBounds(16, 29, 45, 25);
        panel_8_1_1_1.add(lblNewLabel_1_1_1_1);
        
        JPanel panel_8_1_1_2 = new JPanel();
        panel_8_1_1_2.setLayout(null);
        panel_8_1_1_2.setBackground(SystemColor.textHighlight);
        panel_8_1_1_2.setBounds(614, 83, 71, 81);
        panel_7.add(panel_8_1_1_2);
        
        JLabel lblNewLabel_1_1_1_2 = new JLabel("00");
        lblNewLabel_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1_2.setForeground(SystemColor.text);
        lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_1_1_1_2.setBounds(16, 29, 45, 25);
        panel_8_1_1_2.add(lblNewLabel_1_1_1_2);
        
        JLabel lblNewLabel_10 = new JLabel("COUNDOWN FOR THE NEXT FORUM AND CONFERENCE");
        lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_10.setBounds(41, 31, 670, 28);
        panel_7.add(lblNewLabel_10);
        
        JLabel lblNewLabel_11 = new JLabel("17th Research Colloquium and 20th Research Forum");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_11.setBounds(62, 236, 634, 28);
        panel_7.add(lblNewLabel_11);
        
        JLabel lblNewLabel_12 = new JLabel("Months");
        lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_12.setBounds(76, 174, 45, 13);
        panel_7.add(lblNewLabel_12);
        
        JLabel lblNewLabel_12_1 = new JLabel("Days");
        lblNewLabel_12_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_12_1.setBounds(213, 174, 45, 13);
        panel_7.add(lblNewLabel_12_1);
        
        JLabel lblNewLabel_12_1_1 = new JLabel("Hours");
        lblNewLabel_12_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_12_1_1.setBounds(350, 174, 45, 13);
        panel_7.add(lblNewLabel_12_1_1);
        
        JLabel lblNewLabel_12_1_1_1 = new JLabel("Minutes");
        lblNewLabel_12_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_12_1_1_1.setBounds(477, 174, 71, 13);
        panel_7.add(lblNewLabel_12_1_1_1);
        
        JLabel lblNewLabel_12_1_1_1_1 = new JLabel("Seconds");
        lblNewLabel_12_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_12_1_1_1_1.setBounds(614, 174, 71, 13);
        panel_7.add(lblNewLabel_12_1_1_1_1);
        
        JLabel lblNewLabel_10_1 = new JLabel("until");
        lblNewLabel_10_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_10_1.setBounds(41, 210, 670, 28);
        panel_7.add(lblNewLabel_10_1);
        
        JPanel faculty = new JPanel();
        faculty.setBackground(Color.WHITE);
        content.add(faculty, "name_86384982282300");
        faculty.setLayout(null);
        
        JScrollPane facultyscrollPane = new JScrollPane();
        facultyscrollPane.setBounds(10, 128, 1217, 658);
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
        btnNewButton_1.setBounds(1077, 83, 150, 35);
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
        sortcombo.setBounds(96, 83, 55, 35);
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

        filtercombo.setBounds(247, 83, 192, 35);
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
        lblNewLabel_2_1.setBounds(161, 92, 76, 13);
        faculty.add(lblNewLabel_2_1);
        
        JButton btnNewButton_1_1 = new JButton("RESET FILTER/SORT");
        btnNewButton_1_1.setBackground(Color.ORANGE);
        btnNewButton_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		loadFacultyData();
        	}
        });
        btnNewButton_1_1.setBounds(449, 83, 168, 35);
        faculty.add(btnNewButton_1_1);
        
        JButton btnNewButton_4 = new JButton("EXPORT");
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try{
     	           JFileChooser jFileChooser = new JFileChooser();
     	           jFileChooser.showSaveDialog(dashboard);
     	           File saveFile = jFileChooser.getSelectedFile();
     	           
     	           if(saveFile != null){
     	               saveFile = new File(saveFile.toString()+".xlsx");
     	               Workbook wb = new XSSFWorkbook();
     	               Sheet sheet = wb.createSheet("Faculty");
     	               
     	               Row rowCol = sheet.createRow(0);
     	               for(int i=0;i<facultytbl.getColumnCount();i++){
     	                   Cell cell = rowCol.createCell(i);
     	                   cell.setCellValue(facultytbl.getColumnName(i));
     	               }
     	               
     	               for(int j=0;j<facultytbl.getRowCount();j++){
     	                   Row row = sheet.createRow(j+1);
     	                   for(int k=0;k<facultytbl.getColumnCount();k++){
     	                       Cell cell = row.createCell(k);
     	                       if(facultytbl.getValueAt(j, k)!=null){
     	                           cell.setCellValue(facultytbl.getValueAt(j, k).toString());
     	                       }
     	                   }
     	               }
     	               FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
     	               wb.write(out);
     	               wb.close();
     	               out.close();
     	               openFile(saveFile.toString());
     	           }else{
     	               JOptionPane.showMessageDialog(null,"Error al generar archivo");
     	           }
     	       }catch(FileNotFoundException e1){
     	           System.out.println(e);
     	       }catch(IOException io){
     	           System.out.println(io);
     	       }
        	}
        });
        btnNewButton_4.setForeground(SystemColor.text);
        btnNewButton_4.setBackground(SystemColor.textHighlight);
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_4.setBounds(917, 83, 150, 35);
        faculty.add(btnNewButton_4);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(SystemColor.textHighlight);
        panel_1_1.setBounds(0, 0, 1237, 73);
        faculty.add(panel_1_1);
        
        JLabel lblNewLabel_3_1 = new JLabel("FACULTY");
        lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3_1.setForeground(SystemColor.text);
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_1.setBounds(25, 20, 160, 32);
        panel_1_1.add(lblNewLabel_3_1);
        
        
        faculty_search = new JTextField();
        faculty_search.setBounds(556, 20, 511, 35);
        panel_1_1.add(faculty_search);
        faculty_search.setColumns(10);
        
        JButton btnNewButton = new JButton("SEARCH");
        btnNewButton.setBounds(1077, 20, 150, 35);
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
        btnNewButton_1_2.setBounds(1077, 83, 150, 35);
        research.add(btnNewButton_1_2);
        
        JButton btnNewButton_1_1_1 = new JButton("RESET FILTER/SORT");
        btnNewButton_1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		researchtableModel.setRowCount(0);
                autoupdate_research.loadResearchData(researchtableModel);
        	}
        });
        btnNewButton_1_1_1.setBackground(Color.ORANGE);
        btnNewButton_1_1_1.setBounds(449, 83, 168, 35);
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
        discipline.setBounds(247, 83, 192, 35);
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
        sortcombo_1.setBounds(96, 83, 55, 35);
        research.add(sortcombo_1);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("FILTER BY:");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2_1_1.setBounds(161, 92, 76, 13);
        research.add(lblNewLabel_2_1_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("SORT BY");
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2_2.setBounds(10, 94, 126, 13);
        research.add(lblNewLabel_2_2);
        
       
        JScrollPane researchscrollPane = new JScrollPane();
        researchscrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
        researchscrollPane.setBounds(10, 128, 1217, 581);
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
        	                String paperstatus = researchtableModel.getValueAt(selectedRow, 2).toString();
//        	         
        	                researchDetails researchDetails = new researchDetails(paperid, papertitle, paperstatus);
        	                researchDetails.setVisible(true);
        	            }
        	        }
        	}
        });
        researchscrollPane.setViewportView(researchtbl);
        
        
        JPanel panel_1_1_1 = new JPanel();
        panel_1_1_1.setLayout(null);
        panel_1_1_1.setBackground(SystemColor.textHighlight);
        panel_1_1_1.setBounds(0, 0, 1237, 73);
        research.add(panel_1_1_1);
        
        JLabel lblNewLabel_3_1_1 = new JLabel("RESEARCH");
        lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3_1_1.setForeground(SystemColor.text);
        lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_1_1.setBounds(25, 20, 160, 32);
        panel_1_1_1.add(lblNewLabel_3_1_1);
        
        research_search = new JTextField();
        research_search.setColumns(10);
        research_search.setBounds(556, 20, 511, 35);
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
        btnNewButton_5.setBounds(1077, 20, 150, 35);
        panel_1_1_1.add(btnNewButton_5);
        
        JButton btnNewButton_4_1 = new JButton("EXPORT");
        btnNewButton_4_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try{
        	           JFileChooser jFileChooser = new JFileChooser();
        	           jFileChooser.showSaveDialog(dashboard);
        	           File saveFile = jFileChooser.getSelectedFile();
        	           
        	           if(saveFile != null){
        	               saveFile = new File(saveFile.toString()+".xlsx");
        	               Workbook wb = new XSSFWorkbook();
        	               Sheet sheet = wb.createSheet("Research");
        	               
        	               Row rowCol = sheet.createRow(0);
        	               for(int i=0;i<researchtbl.getColumnCount();i++){
        	                   Cell cell = rowCol.createCell(i);
        	                   cell.setCellValue(researchtbl.getColumnName(i));
        	               }
        	               
        	               for(int j=0;j<researchtbl.getRowCount();j++){
        	                   Row row = sheet.createRow(j+1);
        	                   for(int k=0;k<researchtbl.getColumnCount();k++){
        	                       Cell cell = row.createCell(k);
        	                       if(researchtbl.getValueAt(j, k)!=null){
        	                           cell.setCellValue(researchtbl.getValueAt(j, k).toString());
        	                       }
        	                   }
        	               }
        	               FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
        	               wb.write(out);
        	               wb.close();
        	               out.close();
        	               openFile(saveFile.toString());
        	           }
        	       }catch(FileNotFoundException e1){
        	           System.out.println(e);
        	       }catch(IOException io){
        	           System.out.println(io);
        	       }
        	}
        });
        btnNewButton_4_1.setForeground(SystemColor.text);
        btnNewButton_4_1.setBackground(SystemColor.textHighlight);
        btnNewButton_4_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_4_1.setBounds(917, 83, 150, 35);
        research.add(btnNewButton_4_1);
        
        JPanel panel_10 = new JPanel();
        panel_10.setBounds(10, 719, 1217, 67);
        research.add(panel_10);
        panel_10.setLayout(new GridLayout(1, 0, 0, 0));
        
        JPanel panel_11 = new JPanel();
        panel_11.setBackground(new Color(205, 133, 63));
        panel_10.add(panel_11);
        panel_11.setLayout(null);
        
        JLabel lblNewLabel_13 = new JLabel("Colloquium");
        lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_13.setBounds(24, 10, 186, 47);
        panel_11.add(lblNewLabel_13);
        
        JLabel research_colcount = new JLabel(String.valueOf(ResearchCount));
        research_colcount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        research_colcount.setBounds(213, 10, 81, 47);
        panel_11.add(research_colcount);
        
        JPanel panel_12 = new JPanel();
        panel_12.setBackground(new Color(218, 165, 32));
        panel_10.add(panel_12);
        panel_12.setLayout(null);
        
        JLabel lblNewLabel_13_2 = new JLabel("Forum");
        lblNewLabel_13_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_13_2.setBounds(22, 10, 188, 47);
        panel_12.add(lblNewLabel_13_2);
        
        JLabel research_forumcount = new JLabel(String.valueOf(ForumCount));
        research_forumcount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        research_forumcount.setBounds(213, 10, 81, 47);
        panel_12.add(research_forumcount);
        
        JPanel panel_13 = new JPanel();
        panel_13.setBackground(new Color(0, 191, 255));
        panel_10.add(panel_13);
        panel_13.setLayout(null);
        
        JLabel lblNewLabel_13_2_1 = new JLabel("Published");
        lblNewLabel_13_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_13_2_1.setBounds(26, 10, 184, 47);
        panel_13.add(lblNewLabel_13_2_1);
        
        JLabel research_publishedcount = new JLabel(String.valueOf(publishCount));
        research_publishedcount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        research_publishedcount.setBounds(213, 10, 81, 47);
        panel_13.add(research_publishedcount);
        
        JPanel panel_14 = new JPanel();
        panel_14.setBackground(new Color(154, 205, 50));
        panel_10.add(panel_14);
        panel_14.setLayout(null);
        
        JLabel lblNewLabel_13_2_1_1 = new JLabel("Conference");
        lblNewLabel_13_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_13_2_1_1.setBounds(26, 10, 184, 47);
        panel_14.add(lblNewLabel_13_2_1_1);
        
        JLabel research_conferencecount = new JLabel(String.valueOf(conferenceCount));
        research_conferencecount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        research_conferencecount.setBounds(213, 10, 81, 47);
        panel_14.add(research_conferencecount);
        
        JPanel logout = new JPanel();
        content.add(logout, "name_86389111069900");
        
        JPanel manage = new JPanel();
        content.add(manage, "name_33315508697500");
        manage.setLayout(null);
        
        JPanel panel_1_2 = new JPanel();
        panel_1_2.setBounds(0, 0, 1237, 73);
        panel_1_2.setLayout(null);
        panel_1_2.setBackground(SystemColor.textHighlight);
        manage.add(panel_1_2);
        
        JLabel lblNewLabel_3_2 = new JLabel("MANAGE ORGANIZATION");
        lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3_2.setForeground(SystemColor.text);
        lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_2.setBounds(25, 20, 245, 32);
        panel_1_2.add(lblNewLabel_3_2);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(0, 74, 1237, 722);
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
        button.setBounds(1118, 40, 107, 21);
        panel_3.add(button);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(39, 78, 1186, 146);
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
        button_1.setBounds(1118, 244, 107, 21);
        panel_3.add(button_1);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(39, 289, 1186, 146);
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
        button_1_1.setBounds(1118, 459, 107, 21);
        panel_3.add(button_1_1);
        
        
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(39, 511, 1186, 146);
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
        
        Button button_2 = new Button("EXPORT");
        button_2.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		try{
     	           JFileChooser jFileChooser = new JFileChooser();
     	           jFileChooser.showSaveDialog(dashboard);
     	           File saveFile = jFileChooser.getSelectedFile();
     	           
     	           if(saveFile != null){
     	               saveFile = new File(saveFile.toString()+".xlsx");
     	               Workbook wb = new XSSFWorkbook();
     	               Sheet sheet = wb.createSheet("Research Thrust");
     	               
     	               Row rowCol = sheet.createRow(0);
     	               for(int i=0;i<thrusttbl.getColumnCount();i++){
     	                   Cell cell = rowCol.createCell(i);
     	                   cell.setCellValue(thrusttbl.getColumnName(i));
     	               }
     	               
     	               for(int j=0;j<thrusttbl.getRowCount();j++){
     	                   Row row = sheet.createRow(j+1);
     	                   for(int k=0;k<thrusttbl.getColumnCount();k++){
     	                       Cell cell = row.createCell(k);
     	                       if(thrusttbl.getValueAt(j, k)!=null){
     	                           cell.setCellValue(thrusttbl.getValueAt(j, k).toString());
     	                       }
     	                   }
     	               }
     	               FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
     	               wb.write(out);
     	               wb.close();
     	               out.close();
     	               openFile(saveFile.toString());
     	           }
     	       }catch(FileNotFoundException e1){
     	           System.out.println(e);
     	       }catch(IOException io){
     	           System.out.println(io);
     	       }
        	}
        });
        button_2.setForeground(SystemColor.text);
        button_2.setBackground(new Color(0, 204, 51));
        button_2.setBounds(999, 40, 107, 21);
        panel_3.add(button_2);
        
        Button button_2_1 = new Button("EXPORT");
        button_2_1.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		try{
     	           JFileChooser jFileChooser = new JFileChooser();
     	           jFileChooser.showSaveDialog(dashboard);
     	           File saveFile = jFileChooser.getSelectedFile();
     	           
     	           if(saveFile != null){
     	               saveFile = new File(saveFile.toString()+".xlsx");
     	               Workbook wb = new XSSFWorkbook();
     	               Sheet sheet = wb.createSheet("College");
     	               
     	               SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
     	               Date currentDate = new Date();
     	               String formattedDate = dateFormat.format(currentDate);
     	               dateTimeLabel.setText(formattedDate);
     	               
     	               Header header = sheet.getHeader();
	     	           header.setLeft("Auto-generated by RDC-Research Management System");
	     	           header.setRight(formattedDate);

//	     	          int targetRow = 0;
//	     	          int firstcol = 0;
//	     	          int lastcol = collegetbl.getRowCount() - 1;
//	     	          sheet.addMergedRegion(new CellRangeAddress(targetRow, targetRow, firstcol, lastcol));
	     	          
//	     	          PICTURE INSERTION
	     	          
//	     	         String imagePath = "C:\\Users\\TRIXIE\\Pictures\\SB19\\download (1).jpg";
//	     	         FileInputStream imageStream = new FileInputStream(imagePath);
//	     	        byte[] imageBytes = IOUtils.toByteArray(imageStream);
//	     	        int pictureIdx = wb.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
//	     	        
//	     	          CreationHelper helper = wb.getCreationHelper();
//		     	      ClientAnchor anchor = helper.createClientAnchor();
//	
//		     	      // Set anchor top and bottom rows (or columns depending on your choice) based on the chosen target cell range
//		     	      anchor.setRow1(targetRow);
//		     	      anchor.setRow2(targetRow);
//		     	      // Set anchor to span the entire range (all columns or rows)
//		     	      anchor.setCol1(0);
//		     	      anchor.setCol2(collegetbl.getColumnCount() - 1);
//		     	      
//		     	     Drawing drawing = sheet.createDrawingPatriarch();
//		     	     drawing.createPicture(anchor, pictureIdx);



     	               Row rowCol = sheet.createRow(0);
     	               for(int i=0;i<collegetbl.getColumnCount();i++){
     	                   Cell cell = rowCol.createCell(i);
     	                   cell.setCellValue(collegetbl.getColumnName(i));
     	               }
     	               
     	               for(int j=0;j<collegetbl.getRowCount();j++){
     	                   Row row = sheet.createRow(j+1);
     	                   for(int k=0;k<collegetbl.getColumnCount();k++){
     	                       Cell cell = row.createCell(k);
     	                       if(collegetbl.getValueAt(j, k)!=null){
     	                           cell.setCellValue(collegetbl.getValueAt(j, k).toString());
     	                       }
     	                   }
     	               }
     	               
     	              Footer footer = sheet.getFooter();
     	          // Set text for left, center, and right sections of the footer
	     	          footer.setLeft("Auto-generated by RDC-Research Management System");
	     	          footer.setRight(formattedDate);

     	               FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
     	               wb.write(out);
     	               wb.close();
     	               out.close();
     	               openFile(saveFile.toString());
     	           }
     	       }catch(FileNotFoundException e1){
     	           System.out.println(e);
     	       }catch(IOException io){
     	           System.out.println(io);
     	       }
        	}
        });
        button_2_1.setForeground(SystemColor.text);
        button_2_1.setBackground(new Color(0, 204, 51));
        button_2_1.setBounds(999, 244, 107, 21);
        panel_3.add(button_2_1);
        
        Button button_2_1_1 = new Button("EXPORT");
        button_2_1_1.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		try{
     	           JFileChooser jFileChooser = new JFileChooser();
     	           jFileChooser.showSaveDialog(dashboard);
     	           File saveFile = jFileChooser.getSelectedFile();
     	           
     	           if(saveFile != null){
     	               saveFile = new File(saveFile.toString()+".xlsx");
     	               Workbook wb = new XSSFWorkbook();
     	               Sheet sheet = wb.createSheet("Department");
     	               
     	               Row rowCol = sheet.createRow(0);
     	               for(int i=0;i<departmenttbl.getColumnCount();i++){
     	                   Cell cell = rowCol.createCell(i);
     	                   cell.setCellValue(departmenttbl.getColumnName(i));
     	               }
     	               
     	               for(int j=0;j<departmenttbl.getRowCount();j++){
     	                   Row row = sheet.createRow(j+1);
     	                   for(int k=0;k<departmenttbl.getColumnCount();k++){
     	                       Cell cell = row.createCell(k);
     	                       if(departmenttbl.getValueAt(j, k)!=null){
     	                           cell.setCellValue(departmenttbl.getValueAt(j, k).toString());
     	                       }
     	                   }
     	               }
     	               FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
     	               wb.write(out);
     	               wb.close();
     	               out.close();
     	               openFile(saveFile.toString());
     	           }
     	       }catch(FileNotFoundException e1){
     	           System.out.println(e);
     	       }catch(IOException io){
     	           System.out.println(io);
     	       }
        	}
        });
        button_2_1_1.setForeground(SystemColor.text);
        button_2_1_1.setBackground(new Color(0, 204, 51));
        button_2_1_1.setBounds(999, 459, 107, 21);
        panel_3.add(button_2_1_1);
        
        
       
        
        JPanel settings = new JPanel();
        content.add(settings, "name_33385328022900");
        settings.setLayout(null);
        
        JPanel panel_1_3 = new JPanel();
        panel_1_3.setBounds(0, 0, 1237, 73);
        panel_1_3.setLayout(null);
        panel_1_3.setBackground(SystemColor.textHighlight);
        settings.add(panel_1_3);
        
        JLabel lblNewLabel_3_3 = new JLabel("SETTINGS");
        lblNewLabel_3_3.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3_3.setForeground(SystemColor.text);
        lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_3.setBounds(25, 20, 160, 32);
        panel_1_3.add(lblNewLabel_3_3);
        
        JLabel lblNewLabel_16 = new JLabel("Account Settings");
        lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_16.setBounds(29, 253, 240, 33);
        settings.add(lblNewLabel_16);
        
        JLabel lblNewLabel_17 = new JLabel("Current Password");
        lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_17.setBounds(29, 305, 217, 33);
        settings.add(lblNewLabel_17);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBounds(244, 305, 323, 33);
        settings.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton_3 = new JButton("Change Password");
        btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_3.setBounds(29, 362, 186, 33);
        settings.add(btnNewButton_3);
        
        JLabel lblNewLabel_16_1 = new JLabel("Import Database");
        lblNewLabel_16_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_16_1.setBounds(29, 103, 240, 33);
        settings.add(lblNewLabel_16_1);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(29, 172, 980, 33);
        settings.add(textField_1);
        
        JButton btnNewButton_3_1 = new JButton("Import");
        btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_3_1.setBounds(1007, 172, 186, 33);
        settings.add(btnNewButton_3_1);
        
        JLabel lblNewLabel_18 = new JLabel("Import new data to the database. (Supports excel files only)");
        lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_18.setBounds(29, 149, 404, 19);
        settings.add(lblNewLabel_18);
        
        JPanel about = new JPanel();
        content.add(about, "name_7246006468200");
        about.setLayout(null);
        
        JPanel panel_1_3_1 = new JPanel();
        panel_1_3_1.setLayout(null);
        panel_1_3_1.setBackground(SystemColor.textHighlight);
        panel_1_3_1.setBounds(0, 0, 1237, 73);
        about.add(panel_1_3_1);
        
        JLabel lblNewLabel_3_3_1 = new JLabel("ABOUT");
        lblNewLabel_3_3_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3_3_1.setForeground(SystemColor.text);
        lblNewLabel_3_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_3_1.setBounds(25, 20, 160, 32);
        panel_1_3_1.add(lblNewLabel_3_3_1);
        
        JLabel lblNewLabel_14 = new JLabel("Developed by");
        lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_14.setBounds(542, 713, 108, 13);
        about.add(lblNewLabel_14);
        
        JLabel lblNewLabel_15 = new JLabel("Trixie Soriano - RDC Intern");
        lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_15.setBounds(477, 736, 235, 13);
        about.add(lblNewLabel_15);
        
        JLabel lblNewLabel_16_1_1 = new JLabel("Research and Development Center Database - Research Management System");
        lblNewLabel_16_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_16_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_16_1_1.setBounds(35, 108, 1173, 33);
        about.add(lblNewLabel_16_1_1);
        
        JLabel lblNewLabel_18_1 = new JLabel("<html>The Research Management System (RMS), also known as the Research and Management Centre Database, is a powerful and all-inclusive platform that is used in academic settings to optimise and streamline many aspects of research activities. This versatile system has a number of features that are intended to improve productivity and make it easier to handle duties linked to research. Among its capabilities are:\r\n\r\n<br>\r\n<br>\r\n\r\n* <b>Task assistance to administrator </b>- The Research Management System (RMS) gives administrators the critical task support they need to effectively oversee and manage the many facets of university-wide research operations. <br><br>\r\n* <b> Sort and filter data </b> - With the system's improved data management features, sorting and filtering data is a breeze. Effective decision-making processes are supported by this functionality, which guarantees that information is accessible and well-organized. <br><br>\r\n* <b> Research and Faculty Management </b> - Administers have the ability to create, amend, and remove research projects and faculty members thanks to the RMS's comprehensive Research and Faculty Management module. Effective staff management is promoted by this dynamic feature, which adjusts to the changing demands of the academic setting.\n <br><br>\r\n* <b> Export to excel </b> - The RMS makes it easy to export data to Excel in response to the growing need for analytical and collaborative research methods. This facilitates the exchange of research findings more quickly and gives users the ability to work together on data-driven projects and do in-depth analysis.\r\n</html>\r\n");
        lblNewLabel_18_1.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_18_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_18_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_18_1.setBounds(35, 205, 1173, 324);
        about.add(lblNewLabel_18_1);
        
        JLabel lblNewLabel_16_1_1_1 = new JLabel("(RDC-RMS)");
        lblNewLabel_16_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_16_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_16_1_1_1.setBounds(35, 140, 1173, 33);
        about.add(lblNewLabel_16_1_1_1);
        
        JLabel lblNewLabel_15_1 = new JLabel("");
        lblNewLabel_15_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_15_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_15_1.setBounds(556, 763, 94, 13);
        about.add(lblNewLabel_15_1);
        
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
                loadAllTaskData(TasktblModel);
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
        		
        		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?");
        		if (result == 0) {
        		    // Close the current window
        		    dispose();

        		    // Create and display the login window
        		    login login = new login();
        		    login.setVisible(true);
        		}
        	}
        });
        
        JButton aboutbtn = new JButton("ABOUT");
        aboutbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		selectButton(aboutbtn);
    	        content.removeAll();
    	        content.revalidate();
    	        content.add(about);
    	        content.revalidate();
    	        content.repaint();
    	        loadFacultyData();
        	}
        });
        aboutbtn.setForeground(SystemColor.text);
        aboutbtn.setBorderPainted(false);
        aboutbtn.setBackground(SystemColor.textHighlight);
        aboutbtn.setHorizontalAlignment(SwingConstants.LEFT);
        aboutbtn.setBackground(SystemColor.textHighlight);
        panel.add(aboutbtn);
        logoutbtn.setBackground(SystemColor.textHighlight);
        panel.add(logoutbtn);
        logoutbtn.setForeground(SystemColor.text);
        
        JLabel lblNewLabel_7 = new JLabel("Administrator");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_7.setForeground(SystemColor.text);
        lblNewLabel_7.setBounds(51, 228, 120, 13);
        menuPanel.add(lblNewLabel_7);
        
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
    
    private static int retrieveFacultyCountFromDatabase() {
        int facultyCount = 0;
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/rdc-rms";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // SQL query to retrieve faculty count, modify this query based on your database schema
            String query = "SELECT COUNT(*) FROM faculty";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    facultyCount = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions better in a real application
        }
        return facultyCount;
    }
    
    private static int retrieveResearchCountFromDatabase() {
    	int ResearchCount = 0;
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/rdc-rms";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // SQL query to retrieve faculty count, modify this query based on your database schema
            String query = "SELECT COUNT(*) FROM research_summary";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ResearchCount = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions better in a real application
        }
        return ResearchCount;
    }
    
    private static int retrieveColloquiumCount() {
    	
    	
    	int ColCount = 0;
    	
    	String url = "jdbc:mysql://localhost:3306/rdc-rms";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // SQL query to retrieve faculty count, modify this query based on your database schema
            String query = "SELECT COUNT(*) FROM research_summary WHERE status = 'Colloquium' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ColCount  = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions better in a real application
        }
        return ColCount ;
    }
    
    private static int retrieveForumCount()
    {

    	int ForumCount = 0;
    	
    	String url = "jdbc:mysql://localhost:3306/rdc-rms";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // SQL query to retrieve faculty count, modify this query based on your database schema
            String query = "SELECT COUNT(*) FROM research_summary WHERE status = 'Forum' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ForumCount  = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions better in a real application
        }
        return ForumCount ;
    }
    
    private static int retrievePublishCount()
    {

    	int PublishedCount = 0;
    	
    	String url = "jdbc:mysql://localhost:3306/rdc-rms";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // SQL query to retrieve faculty count, modify this query based on your database schema
            String query = "SELECT COUNT(*) FROM research_summary WHERE status = 'Published' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	PublishedCount  = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions better in a real application
        }
        return PublishedCount ;
    }

    
    private static int retrieveConferenceCount()
    {

    	int ConferenceCount = 0;
    	
    	String url = "jdbc:mysql://localhost:3306/rdc-rms";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // SQL query to retrieve faculty count, modify this query based on your database schema
            String query = "SELECT COUNT(*) FROM research_summary WHERE status = 'Completed' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	ConferenceCount  = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions better in a real application
        }
        return ConferenceCount ;
    }
    
    public void openFile(String file){
        try{
            File path = new File(file);
            Desktop.getDesktop().open(path);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
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
    
    private void loadAllTaskData(DefaultTableModel TasktblModel) {
        TasktblModel.setRowCount(0);
        autoupdate_task.loadAllTasks(TasktblModel);
    }

    private void loadTaskByLevel(DefaultTableModel TasktblModel, JComboBox<String> tasklevel) {
    	
    	if (TasktblModel == null) {
            TasktblModel = new DefaultTableModel();
        }

        TasktblModel.setRowCount(0);
        String selectedTaskLevel = tasklevel.getSelectedItem().toString();
        autoupdate_task.loadTasksByLevel(TasktblModel, selectedTaskLevel);
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
    
    void refreshTaskTable()
    {
    	TasktblModel.setRowCount(0);
    	loadAllTaskData(TasktblModel);
    }
}
