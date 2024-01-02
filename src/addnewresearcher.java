import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addnewresearcher extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
    private DefaultTableModel tableModel;
	private JButton btnNewButton;
    private JTextField author_txtfld;
    private ArrayList<String> name = new ArrayList<>();
    private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboard parentDashboard = new dashboard();
					addnewresearcher frame = new addnewresearcher(parentDashboard, "AAAA", "RESEARCH TITLE", "Colloquium");
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
	public addnewresearcher(dashboard parentDashboard, String paperid, String papertitle, String paperstatus) {
		populateNameList();

		setTitle("ADD NEW RESEARCHERS TO " + paperid);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
        scrollPane.setBounds(37, 110, 517, 198);
        contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("NAME:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(37, 49, 45, 13);
		contentPane.add(lblNewLabel);
		
		author_txtfld = new JTextField();
		author_txtfld.setBounds(94, 43, 363, 26);
		contentPane.add(author_txtfld);
		author_txtfld.setColumns(10);
		
		btnNewButton = new JButton("INSERT");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setBounds(473, 43, 85, 25);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("ADD");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String final_id = paperid;
				String title  = papertitle;
				String status = paperstatus;
				
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdc-rms", "root", "")) {
            	    // Insert data into the research_faculty table for each author
            		String insertResearcher = "INSERT INTO research_faculty (paper_id, title, faculty, college, department, status) VALUES (?, ?, ?, ?, ?, ?)";
            		PreparedStatement researcherStatement = connection.prepareStatement(insertResearcher);

            		for (int i = 0; i < tableModel.getRowCount(); i++) {
            		    String authorId = tableModel.getValueAt(i, 0).toString();
            		    tableModel.getValueAt(i, 1).toString();
            		    String college = tableModel.getValueAt(i, 2).toString();
            		    String department = tableModel.getValueAt(i, 3).toString();

            		    researcherStatement.setString(1, final_id);
            		    researcherStatement.setString(2, title);
            		    researcherStatement.setString(3, authorId); 
            		    researcherStatement.setString(4, college);
            		    researcherStatement.setString(5, department);
            		    researcherStatement.setString(6, status);

            		    researcherStatement.executeUpdate();
            		}
            		
            		int rowsInsertedSummary = researcherStatement.executeUpdate();

            		if (rowsInsertedSummary > 0) {
            			 JOptionPane.showMessageDialog(null, "Addition was successful!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            			 researchDetails researchDetails = new researchDetails(parentDashboard, null, null, null);
            			 researchDetails.loadAuthors(paperid);
            			 researchDetails.refreshAuthorsTable();
            			 
 	                    dispose();
 	                    
            		} else {
            		    System.out.println("Data insertion failed for research_summary.");
            		}
            		
				} catch (SQLException ex) {
            	    ex.printStackTrace();
            	}

			}
		});
		btnNewButton_1.setBounds(251, 337, 85, 26);
		contentPane.add(btnNewButton_1);
		
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


