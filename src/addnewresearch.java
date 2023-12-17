import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import com.toedter.calendar.JYearChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class addnewresearch extends JFrame {

	private JPanel contentPane;
    private JTextArea textField_1;
    private JTextField author_txtfld;
    private JTable table;
    private DefaultTableModel tableModel;
    private Connection conn;
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> authorlist = new ArrayList<>();
    private ArrayList<String> authorname = new ArrayList<>();
    private JComboBox<String> discipline; 
    private JComboBox<String> month; 
    private String selectedDiscipline;
    private int selectedYear;
    private JSpinner number;
    private JYearChooser year;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	dashboard parentDashboard = new dashboard();
                addnewresearch frame = new addnewresearch(parentDashboard);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public addnewresearch(dashboard parentDashboard) {
        initializeDatabaseConnection();
        populateNameList();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 906, 699);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.textHighlight);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int centerX = (screenSize.width - getWidth()) / 2;
	    int centerY = (screenSize.height - getHeight()) / 2;
	    setLocation(centerX, centerY);
	    getContentPane().setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("TITLE");
        lblNewLabel_1.setForeground(SystemColor.text);
        lblNewLabel_1.setBounds(48, 190, 81, 57);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("AUTHOR");
        lblNewLabel_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_1.setBounds(48, 267, 81, 13);
        contentPane.add(lblNewLabel_1_1);
        
//      RESEARCH TITLE  
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(139, 187, 659, 60);
        contentPane.add(scrollPane_1);
        
        //TITLE PART TEXTFIELD
        textField_1 = new JTextArea();
        scrollPane_1.setViewportView(textField_1);
        textField_1.setColumns(10);
        
        // Create a table model with a single column for authors
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Author Name");
        tableModel.addColumn("College");
        tableModel.addColumn("Department");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	}
        });
        scrollPane.setBounds(48, 313, 750, 230);
        contentPane.add(scrollPane);


        //AUTOCOMPLETE AUTHOR NAMES
        author_txtfld = new JTextField();
        author_txtfld.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_BACK_SPACE:
                        break;
                    case KeyEvent.VK_ENTER:
                        author_txtfld.setText(author_txtfld.getText());
                        break;
                    default:
                        SwingUtilities.invokeLater(() -> {
                            String txt = author_txtfld.getText();
                            autocomplete(txt);
                        });
                }
            }
        });
        author_txtfld.setColumns(10);
        author_txtfld.setBounds(139, 257, 559, 33);
        contentPane.add(author_txtfld);
        
        //ADD AUTHOR IN THE LIST OF THE PROPONENTS
        JButton btnNewButton_1 = new JButton("ADD");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String author = author_txtfld.getText();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");

                    // Check if the author exists in the database
                    PreparedStatement checkStatement = connection.prepareStatement("SELECT id, college, department FROM faculty WHERE name = ?");
                    checkStatement.setString(1, author);
                    ResultSet resultSet = checkStatement.executeQuery();

                    if (resultSet.next()) {
                        // Author exists, retrieve data
                        String authorId = resultSet.getString("id");
                        String college = resultSet.getString("college");
                        String department = resultSet.getString("department");

                        // Add the data to the table
                        tableModel.addRow(new Object[]{authorId, author, college, department});

                        author_txtfld.setText("");
                        
                        

                 
                    } else {
                        // Author does not exist
                        int choice = JOptionPane.showConfirmDialog(null, "Author doesn't exist. Do you want to add a new author?", "Author Not Found", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            // Open a new window to add a new author
                            addnewfaculty addnewfaculty = new addnewfaculty(parentDashboard);
                            addnewfaculty.setVisible(true);
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton_1.setBounds(708, 257, 90, 33);
        contentPane.add(btnNewButton_1);
        btnNewButton_1.setBounds(708, 257, 90, 33);
        contentPane.add(btnNewButton_1);

        
        JLabel lblNewLabel_1_1_1 = new JLabel("PAPER ID");
        lblNewLabel_1_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_1_1.setBounds(48, 154, 81, 13);
        contentPane.add(lblNewLabel_1_1_1);
        
        discipline = new JComboBox<>();
        discipline.setBounds(139, 144, 226, 33);
        contentPane.add(discipline);
        
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
        Map<String, String> monthCodeMap = new LinkedHashMap<>();
        monthCodeMap.put("January", "1");
        monthCodeMap.put("February", "2");
        monthCodeMap.put("March", "3");
        monthCodeMap.put("April", "4");
        monthCodeMap.put("May", "5");
        monthCodeMap.put("June", "6");
        monthCodeMap.put("July", "7");
        monthCodeMap.put("August", "8");
        monthCodeMap.put("September", "9");
        monthCodeMap.put("October", "10");
        monthCodeMap.put("November", "11");
        monthCodeMap.put("December", "12");
        
        year = new JYearChooser();
        year.getSpinner().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		generatePaperID();
        	}
        });
        year.setBounds(554, 144, 112, 33);
        contentPane.add(year);
        
        
        number = new JSpinner();
        number.setBounds(676, 144, 122, 33);
        contentPane.add(number);
        
//        SpinnerNumberModel numberModel = new SpinnerNumberModel(initialValue, minValue, maxValue, stepSize);
        SpinnerNumberModel numberModel = (SpinnerNumberModel) number.getModel(); // Replace min, max, and step size with appropriate values
        int newMaxValue = 100; // set your new maximum value here
        numberModel.setMaximum(newMaxValue);
        number.setModel(numberModel);


        month = new JComboBox();
        month.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		generatePaperID();
        	}
        });
        month.setBounds(375, 144, 169, 33);
        contentPane.add(month);
        
        
        for (String monthName : monthCodeMap.keySet()) {
            month.addItem(monthName);
        }
        
        
        discipline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        
            	generatePaperID();
            }
        });
        

        	

        JButton btnNewButton = new JButton("SUBMIT");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String selectedDiscipline =  discipline.getSelectedItem().toString();
            	String selectedmonth = month.getSelectedItem().toString();
            	String monthcode = monthCodeMap.get(selectedmonth);
            	int selectedYear = year.getYear();
            	String nextPaperIdCount = number.getValue().toString();
            	String final_id = selectedDiscipline + " - " + monthcode + " - " + selectedYear + " - " + nextPaperIdCount;
            	String title = textField_1.getText();
            	String status = "Colloquium";

            	// Establish a database connection
            	try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
            	    // Insert data into the research_faculty table for each author
            		String insertFaculty = "INSERT INTO research_faculty (paper_id, title, faculty, college, department, status) VALUES (?, ?, ?, ?, ?, ?)";
            		PreparedStatement facultyStatement = connection.prepareStatement(insertFaculty);

            		for (int i = 0; i < tableModel.getRowCount(); i++) {
            		    String authorId = tableModel.getValueAt(i, 0).toString();
            		    tableModel.getValueAt(i, 1).toString();
            		    String college = tableModel.getValueAt(i, 2).toString();
            		    String department = tableModel.getValueAt(i, 3).toString();

            		    facultyStatement.setString(1, final_id);
            		    facultyStatement.setString(2, title);
            		    facultyStatement.setString(3, authorId); 
            		    facultyStatement.setString(4, college);
            		    facultyStatement.setString(5, department);
            		    facultyStatement.setString(6, status);

            		    facultyStatement.executeUpdate();

            		}

            		// Create the insert statement for research_summary
            		String insertSummary = "INSERT INTO research_summary (paper_id, title, status) VALUES (?, ?, ?)";
            		PreparedStatement summaryStatement = connection.prepareStatement(insertSummary);
            		summaryStatement.setString(1, final_id);
            		summaryStatement.setString(2, title);
            		summaryStatement.setString(3, status);

            		// Execute the insert statement for research_summary
            		int rowsInsertedSummary = summaryStatement.executeUpdate();

            		if (rowsInsertedSummary > 0) {
            			 JOptionPane.showMessageDialog(null, "Addition was successful!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            			 parentDashboard.refreshResearchTable(); //
 	                    dispose();
 	                    
            		} else {
            		    System.out.println("Data insertion failed for research_summary.");
            		}
            	} catch (SQLException ex) {
            	    ex.printStackTrace();
            	}
        	}
        });
        btnNewButton.setForeground(SystemColor.text);
        btnNewButton.setBackground(new Color(0, 255, 0));
        btnNewButton.setBounds(436, 569, 362, 33);
        contentPane.add(btnNewButton);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setForeground(SystemColor.text);
        btnCancel.setBackground(new Color(255, 0, 0));
        btnCancel.setBounds(48, 569, 362, 33);
        contentPane.add(btnCancel);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        panel.setBounds(0, 0, 892, 72);
        contentPane.add(panel);
        panel.setLayout(null);
        
	    JLabel lblNewLabel = new JLabel("ADD NEW RESEARCH");
	    lblNewLabel.setBounds(295, 26, 277, 20);
	    panel.add(lblNewLabel);
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    }

	private void initializeDatabaseConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateNameList() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "");
             PreparedStatement statement = connection.prepareStatement("SELECT name FROM faculty");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                this.name.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void autocomplete(String txt) {
        String complete = "";
        int start = txt.length();
        int last = txt.length();
        int a;
        for (a = 0; a < name.size(); a++) {
            if (name.get(a).toString().startsWith(txt)) {
                complete = name.get(a).toString();
                last = complete.length();
                break;
            }
        }
        if (last > start) {
            author_txtfld.setText(complete);
            author_txtfld.setCaretPosition(last);
            author_txtfld.moveCaretPosition(start);
        }
    }
    
    private List<String> getCollegesAsList() {
        List<String> colleges = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String college = tableModel.getValueAt(i, 2).toString();
            colleges.add(college);
        }
        return colleges;
    }

    private List<String> getDepartmentsAsList() {
        List<String> departments = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String department = tableModel.getValueAt(i, 3).toString();
            departments.add(department);
        }
        return departments;
    }
    
    private void generatePaperID() {
        selectedDiscipline = discipline.getSelectedItem().toString();
        int selectedMonth = month.getSelectedIndex() + 1;
        selectedYear = year.getYear();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
            String query = "SELECT MAX(CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(TRIM(paper_id), ' - ', -1), ' -', 1) AS UNSIGNED)) " +
                           "FROM research_summary WHERE SUBSTRING_INDEX(TRIM(paper_id), ' - ', 1) = ? " +
                           "AND SUBSTRING_INDEX(SUBSTRING_INDEX(TRIM(paper_id), ' - ', 2), ' - ', -1) = ? " +
                           "AND SUBSTRING_INDEX(SUBSTRING_INDEX(TRIM(paper_id), ' - ', 3), ' - ', -1) = ?";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedDiscipline);
            statement.setInt(2, selectedMonth);
            statement.setInt(3, selectedYear);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int previousCount = resultSet.getInt(1);
                SwingUtilities.invokeLater(() -> {
                    number.setValue(previousCount + 1);
                    System.out.println("Previous Count: " + previousCount);
                });
            } else {
                SwingUtilities.invokeLater(() -> {
                    number.setValue(1);
                    System.out.println("No previous records found, setting default count to 1.");
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}