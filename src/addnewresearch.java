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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.components.JSpinField;

public class addnewresearch extends JFrame {

    private JPanel contentPane;
    private JTextField textField_1;
    private JTextField author_txtfld;
    private JTable table;
    private DefaultTableModel tableModel;
    private Connection conn;
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> authorlist = new ArrayList<>();
    private ArrayList<String> authorname = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                addnewresearch frame = new addnewresearch();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public addnewresearch() {
        initializeDatabaseConnection();
        populateNameList();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 906, 699);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ADD NEW RESEARCH");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(363, 68, 169, 13);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("TITLE");
        lblNewLabel_1.setBounds(48, 190, 81, 57);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("AUTHOR");
        lblNewLabel_1_1.setBounds(48, 267, 81, 13);
        contentPane.add(lblNewLabel_1_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(139, 187, 659, 60);
        contentPane.add(textField_1);

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
        
        
        
        

        JButton btnNewButton = new JButton("SUBMIT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String title = textField_1.getText();
              
              // Assuming you want to retrieve the IDs, college, and department from the table
              ArrayList<String> storedIds = new ArrayList<>();
              ArrayList<String> storedColleges = new ArrayList<>();
              ArrayList<String> storedDepartments = new ArrayList<>();

              int rowCount = tableModel.getRowCount();
              int columnId = 0; // Assuming the ID is in the first column (0-indexed)
              int columnCollege = 1; // Assuming the college is in the second column (1-indexed)
              int columnDepartment = 2; // Assuming the department is in the third column (2-indexed)
////
              for (int i = 0; i < rowCount; i++) {
                  String id = tableModel.getValueAt(i, columnId).toString();
                  String college = tableModel.getValueAt(i, columnCollege).toString();
                  String department = tableModel.getValueAt(i, columnDepartment).toString();
                 
                  storedIds.add(id);
                  storedColleges.add(college);
                  storedDepartments.add(department);
              }
////
              // Now, the ArrayLists contain the IDs, colleges, and departments for each author
////              
              StringBuilder concatenatedIds = new StringBuilder();

              for (String id : storedIds) {
                  concatenatedIds.append(id).append(';');
              }

              // Remove the trailing semicolon if it exists
              if (concatenatedIds.length() > 0) {
                 concatenatedIds.setLength(concatenatedIds.length() - 1);
             }
////
////              // Establish a database connection
              try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
                  String insert = "INSERT INTO research (title, faculty, college, department) VALUES (?, ?, ?, ?)";                  
                  PreparedStatement preparedStatement = connection.prepareStatement(insert);                  preparedStatement.setString(1, title);                  preparedStatement.setString(2, concatenatedIds.toString());                  preparedStatement.setString(3, String.join(";", storedColleges));                  preparedStatement.setString(4, String.join(";", storedDepartments));                  // Execute the insert statement                  int rowsInserted = preparedStatement.executeUpdate();                  
                 if (rowsInserted > 0) {
                     System.out.println("Data inserted successfully.");
                 } else {
                     System.out.println("Data insertion failed.");
                 }
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
//          	
          
            }
        });
        btnNewButton.setBounds(713, 569, 85, 33);
        contentPane.add(btnNewButton);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(48, 569, 85, 33);
        contentPane.add(btnCancel);

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
                        tableModel.addRow(new Object[] { authorId, author, college, department });
                        
                        author_txtfld.setText("");
                        
                        // Store the ID in another table (you should implement this part)
                        // PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO other_table (author_id) VALUES (?)");
                        // insertStatement.setInt(1, authorId);
                        // insertStatement.executeUpdate();
                    } else {
                        // Author does not exist
                        // Display an alert or message to the user
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
        		
        	}
        });
        btnNewButton_1.setBounds(708, 257, 90, 33);
        contentPane.add(btnNewButton_1);

        // Create a table model with a single column for authors
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Author Name");
        tableModel.addColumn("College");
        tableModel.addColumn("Department");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(48, 313, 750, 230);
        contentPane.add(scrollPane);
        
        JComboBox discipline = new JComboBox();
        discipline.setBounds(139, 144, 226, 33);
        contentPane.add(discipline);
        
        discipline.addItem("CAH - Culture, Arts, and Humanities");
        discipline.addItem("PSS -Psychology & Social Sciences");
        discipline.addItem("BIT - Business Innovation & Technopreneurship");
        discipline.addItem("HKSS - Human Kinetics & Sports Science");
        discipline.addItem("ET - Engineering & Technology");
        discipline.addItem("PIS - Policy & International Studies ");
        discipline.addItem("UPPB - Urban Agriculture & Plant Biotechnology");
        discipline.addItem("MB - Mushroom Biotechnology ");
        discipline.addItem("GIES - Gender & Inclusive Education Studies");
        discipline.addItem("RE - Research to Extension");
        discipline.addItem("ASSST - Astronomy & Space Science Satellite Technology"); 
        discipline.addItem("ECCS - Environmental & Climate Change Studies");
        discipline.addItem("DSSA - Data Science & Smart Analytics");
 
        
        JLabel lblNewLabel_1_1_1 = new JLabel("PAPER ID");
        lblNewLabel_1_1_1.setBounds(48, 154, 81, 13);
        contentPane.add(lblNewLabel_1_1_1);
        
        JYearChooser year = new JYearChooser();
        year.setBounds(554, 144, 112, 33);
        contentPane.add(year);
        
        JSpinner number = new JSpinner();
        number.setBounds(676, 144, 122, 33);
        contentPane.add(number);
        
        JComboBox month = new JComboBox();
        month.setBounds(375, 144, 169, 33);
        contentPane.add(month);
        
        month.addItem("January");
        month.addItem("February");
        month.addItem("March");
        month.addItem("April");
        month.addItem("May");
        month.addItem("June");
        month.addItem("July");
        month.addItem("August");
        month.addItem("September");
        month.addItem("November");
        month.addItem("December");
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
}