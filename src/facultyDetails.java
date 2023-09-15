import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class facultyDetails extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JButton selectedButton = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    facultyDetails frame = new facultyDetails("123", "John Doe", "Department of XYZ", "Science College", "Physics");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public facultyDetails(String faculty_Id, String faculty_Name, String faculty_Affiliation, String faculty_College, String faculty_Department) {
    	setTitle("FACULTY DETAILS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 790);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.textHighlight);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(60, 303, 1078, 398);
        scrollPane.setPreferredSize(new Dimension(1078, 281));
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JLabel lblNewLabel_1 = new JLabel("FACULTY ID");
        lblNewLabel_1.setForeground(SystemColor.text);
        lblNewLabel_1.setBounds(73, 63, 160, 19);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1);

        JTextField facultyID = new JTextField(faculty_Id);
        facultyID.setEditable(false);
        facultyID.setEnabled(false);
        facultyID.setFont(new Font("Tahoma", Font.PLAIN, 15));
        facultyID.setForeground(Color.BLACK);
        facultyID.setBounds(279, 62, 278, 19);
        contentPane.add(facultyID);

        JLabel lblNewLabel_1_1 = new JLabel("FULL NAME");
        lblNewLabel_1_1.setForeground(SystemColor.text);
        lblNewLabel_1_1.setBackground(SystemColor.textHighlight);
        lblNewLabel_1_1.setBounds(73, 120, 77, 19);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1_1);

        JTextField facultyName = new JTextField(faculty_Name);
        facultyName.setEnabled(false);
        facultyName.setEditable(false);
        facultyName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        facultyName.setForeground(Color.BLACK);
        facultyName.setBounds(279, 119, 276, 19);
        contentPane.add(facultyName);

        JLabel lblNewLabel_1_2 = new JLabel("AFFILIATION");
        lblNewLabel_1_2.setForeground(SystemColor.text);
        lblNewLabel_1_2.setBounds(73, 180, 112, 19);
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1_2);

        JTextField facultyAffiliation = new JTextField(faculty_Affiliation);
        facultyAffiliation.setEnabled(false);
        facultyAffiliation.setEditable(false);
        facultyAffiliation.setFont(new Font("Tahoma", Font.PLAIN, 15));
        facultyAffiliation.setForeground(Color.BLACK);
        facultyAffiliation.setBounds(279, 180, 279, 18);
        contentPane.add(facultyAffiliation);

        JLabel lblNewLabel_1_3 = new JLabel("COLLEGE/DEPARTMENT:");
        lblNewLabel_1_3.setForeground(SystemColor.text);
        lblNewLabel_1_3.setBounds(73, 242, 167, 19);
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1_3);

        JTextField facultyDept = new JTextField(faculty_College + " - " + faculty_Department);
        facultyDept.setEditable(false);
        facultyDept.setEnabled(false);
        facultyDept.setFont(new Font("Tahoma", Font.PLAIN, 15));
        facultyDept.setForeground(Color.BLACK);
        facultyDept.setBounds(279, 241, 278, 19);
        contentPane.add(facultyDept);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\xampp\\htdocs\\TRR\\css\\img\\RDC logo 2.png"));
        lblNewLabel.setBounds(872, 36, 160, 191);
        contentPane.add(lblNewLabel);
        
        
        JButton savebtn = new JButton("SAVE");
        savebtn.setEnabled(false);
        savebtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		selectButton();
                facultyName.setEnabled(false);
                facultyAffiliation.setEnabled(false);
                facultyDept.setEnabled(false);
                
                facultyName.setEditable(false);
                facultyAffiliation.setEditable(false);
                facultyDept.setEditable(false);
        	}
        });
        savebtn.setBounds(827, 237, 125, 33);
        contentPane.add(savebtn);
        
        JButton editbtn = new JButton("EDIT");
        editbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		selectButton();
        		
                facultyName.setEnabled(true);
                facultyAffiliation.setEnabled(true);
                facultyDept.setEnabled(true);
                
                facultyName.setEditable(true);
                facultyAffiliation.setEditable(true);
                facultyDept.setEditable(true);
                
        	}
        });
        editbtn.setBounds(962, 237, 125, 33);
        contentPane.add(editbtn);
    }
    
    private void selectButton() {
        if (selectedButton != null) {
            selectedButton.setEnabled(true);
            
        }
        
   // You can set it to savebtn or editbtn as needed.
    }

}