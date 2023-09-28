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

    private JPanel contentPane;
    private JTable authorstbl;
    private DefaultTableModel authorstableModel;
    private JButton selectedButton = null;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    researchDetails frame = new researchDetails("AAAA", "Research Title");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public researchDetails(String paperid, String papertitle) { 
    	setTitle("RESEARCH DETAILS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(50, 50, 782, 903);
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
        scrollPane.setBounds(36, 243, 694, 162);
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
                        int response = JOptionPane.showConfirmDialog(
                                null, // Use the appropriate parent component
                                "Do you want to remove this author?",
                                "Confirm Removal",
                                JOptionPane.YES_NO_OPTION);

                        if (response == JOptionPane.YES_OPTION) {
                            // User confirmed removal; perform removal from the database
                            removeAuthor(selectedRow); // You need to implement this method
                        }
                    }
                }
            }

			private void removeAuthor(int selectedRow) {
				// TODO Auto-generated method stub
				
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

        JTextField facultyName = new JTextField(papertitle);
        facultyName.setEnabled(false);
        facultyName.setEditable(false);
        facultyName.setHorizontalAlignment(SwingConstants.CENTER);
        facultyName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        facultyName.setForeground(Color.BLACK);
        facultyName.setBounds(36, 105, 694, 70);
        contentPane.add(facultyName);

        JLabel lblNewLabel_1_3 = new JLabel("PROPONENTS:");
        lblNewLabel_1_3.setForeground(SystemColor.text);
        lblNewLabel_1_3.setBounds(36, 200, 167, 19);
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1_3);
        
        JLabel lblNewLabel_1_3_1_1 = new JLabel("Is it subject for forum?");
        lblNewLabel_1_3_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_3_1_1.setBounds(36, 426, 190, 19);
        contentPane.add(lblNewLabel_1_3_1_1);
        
        JRadioButton chckbxNewCheckBox = new JRadioButton("Yes");
        buttonGroup.add(chckbxNewCheckBox);
        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chckbxNewCheckBox.setForeground(SystemColor.text);
        chckbxNewCheckBox.setBackground(SystemColor.textHighlight);
        chckbxNewCheckBox.setBounds(533, 425, 93, 21);
        contentPane.add(chckbxNewCheckBox);
        
        JLabel lblNewLabel_1_3_1_1_1 = new JLabel("If yes, select the date when will be held");
        lblNewLabel_1_3_1_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_3_1_1_1.setBounds(36, 469, 301, 19);
        contentPane.add(lblNewLabel_1_3_1_1_1);
        
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(364, 459, 366, 33);
        contentPane.add(dateChooser);
        
        JRadioButton chckbxNotYet = new JRadioButton("Not Yet");
        buttonGroup.add(chckbxNotYet);
        chckbxNotYet.setForeground(SystemColor.text);
        chckbxNotYet.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chckbxNotYet.setBackground(SystemColor.textHighlight);
        chckbxNotYet.setBounds(637, 425, 93, 21);
        contentPane.add(chckbxNotYet);
        
        JLabel lblNewLabel_1_3_1_1_1_1 = new JLabel("Accomplished date:");
        lblNewLabel_1_3_1_1_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_3_1_1_1_1.setBounds(36, 510, 301, 19);
        contentPane.add(lblNewLabel_1_3_1_1_1_1);
        
        JDateChooser dateChooser_1 = new JDateChooser();
        dateChooser_1.setBounds(364, 502, 366, 33);
        contentPane.add(dateChooser_1);
        
        JLabel lblNewLabel_1_3_1_1_2 = new JLabel("Is it subject for colloquium?");
        lblNewLabel_1_3_1_1_2.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_3_1_1_2.setBounds(36, 546, 227, 19);
        contentPane.add(lblNewLabel_1_3_1_1_2);
        
        JRadioButton chckbxNewCheckBox_1 = new JRadioButton("Yes");
        chckbxNewCheckBox_1.setForeground(SystemColor.text);
        chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chckbxNewCheckBox_1.setBackground(SystemColor.textHighlight);
        chckbxNewCheckBox_1.setBounds(533, 545, 93, 21);
        contentPane.add(chckbxNewCheckBox_1);
        
        JRadioButton chckbxNotYet_1 = new JRadioButton("Not Yet");
        chckbxNotYet_1.setForeground(SystemColor.text);
        chckbxNotYet_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chckbxNotYet_1.setBackground(SystemColor.textHighlight);
        chckbxNotYet_1.setBounds(637, 545, 93, 21);
        contentPane.add(chckbxNotYet_1);
        
        JDateChooser dateChooser_2 = new JDateChooser();
        dateChooser_2.setBounds(364, 579, 366, 33);
        contentPane.add(dateChooser_2);
        
        JLabel lblNewLabel_1_3_1_1_1_2 = new JLabel("If yes, select the date when will be held");
        lblNewLabel_1_3_1_1_1_2.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_3_1_1_1_2.setBounds(36, 589, 301, 19);
        contentPane.add(lblNewLabel_1_3_1_1_1_2);
        
        JLabel lblNewLabel_1_3_1_1_1_1_1 = new JLabel("Accomplished date:");
        lblNewLabel_1_3_1_1_1_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_3_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_3_1_1_1_1_1.setBounds(36, 630, 301, 19);
        contentPane.add(lblNewLabel_1_3_1_1_1_1_1);
        
        JDateChooser dateChooser_1_1 = new JDateChooser();
        dateChooser_1_1.setBounds(364, 622, 366, 33);
        contentPane.add(dateChooser_1_1);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        panel.setBounds(0, 0, 768, 40);
        contentPane.add(panel);
        panel.setLayout(null);
        
        

        JLabel lblNewLabel_1 = new JLabel("PAPER ID");
        lblNewLabel_1.setBounds(10, 10, 160, 19);
        panel.add(lblNewLabel_1);
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
                JLabel facultyID = new JLabel(paperid);
                facultyID.setHorizontalAlignment(SwingConstants.RIGHT);
                facultyID.setBounds(245, 10, 513, 19);
                panel.add(facultyID);
                facultyID.setEnabled(false);
                facultyID.setFont(new Font("Tahoma", Font.PLAIN, 15));
                facultyID.setForeground(Color.BLACK);
                
                JButton edit_btn = new JButton(resizedIcon);
                edit_btn.setBackground(SystemColor.textHighlight);
                edit_btn.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                        facultyName.setEditable(true);
                        facultyName.setEnabled(true);
                	}
                });
                edit_btn.setBounds(163, 76, 45, 19);
                contentPane.add(edit_btn);
                
                JLabel lblNewLabel_2 = new JLabel("New label");
                lblNewLabel_2.setForeground(Color.RED);
                lblNewLabel_2.setBounds(333, 81, 397, 13);
                contentPane.add(lblNewLabel_2);
                
                JLabel lblNewLabel_1_3_1 = new JLabel("NOTES:");
                lblNewLabel_1_3_1.setForeground(SystemColor.text);
                lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
                lblNewLabel_1_3_1.setBounds(36, 673, 167, 19);
                contentPane.add(lblNewLabel_1_3_1);
                
                JTextArea textArea = new JTextArea();
                textArea.setBounds(36, 702, 694, 64);
                contentPane.add(textArea);
                
                JButton btnNewButton = new JButton("SAVE CHANGES");
                btnNewButton.setBounds(278, 791, 182, 21);
                contentPane.add(btnNewButton);
                
         loadAuthors(paperid);
    }

	private void loadAuthors(String paperid) {
		
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

	private void selectButton() {
        if (selectedButton != null) {
            selectedButton.setEnabled(true);
            
        }
        
   // You can set it to savebtn or editbtn as needed.
    }
}