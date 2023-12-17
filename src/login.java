import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;


public class login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	
	public login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\xampp\\htdocs\\TRR\\css\\img\\RDC logo.png"));
		getContentPane().setForeground(new Color(255, 250, 250));
		setForeground(new Color(30, 144, 255));
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 598, 480);
	    contentPane = new JPanel();
	    setUndecorated(true);
	    setVisible(true);
	       
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int centerX = (screenSize.width - getWidth()) / 2;
	    int centerY = (screenSize.height - getHeight()) / 2;
	    setLocation(centerX, centerY);
	    getContentPane().setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("RESEARCH AND DEVELOPMENT CENTER DATABASE");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblNewLabel.setBounds(63, 270, 463, 45);
	    getContentPane().add(lblNewLabel);
	    
	    passwordField = new JPasswordField();
	    passwordField.setBounds(211, 328, 154, 19);
	    getContentPane().add(passwordField);
	    
	    JButton btnNewButton = new JButton("LOGIN");
	    btnNewButton.setForeground(SystemColor.text);
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		String password = passwordField.getText().toString();
	    		
	    		if (password.equals("admin@123"))
	    		{
		    		dashboard dashboard = new dashboard();
	    			dashboard.setVisible(true);
	    			dispose();
	    		}
	    		else
	    		{
	    			 JOptionPane.showMessageDialog(null, "Invalid Password ", "Error", JOptionPane.ERROR_MESSAGE);
	    		}

	    	}
	    	
	    	
	    });
	    btnNewButton.setBackground(new Color(0, 0, 205));
	    btnNewButton.setBounds(211, 357, 154, 31);
	    getContentPane().add(btnNewButton);
	    
	    JLabel lblNewLabel_1 = new JLabel("Forgot Password?");
	    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1.setBounds(212, 422, 154, 13);
	    getContentPane().add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_2 = new JLabel("");
	    lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\TRIXIE\\Documents\\FOURTH YEAR\\System Integration\\New folder\\TRR\\css\\img\\RDC logo 2.png"));
	    lblNewLabel_2.setBounds(211, 67, 161, 182);
	    getContentPane().add(lblNewLabel_2);
	    
	    JPanel panel = new JPanel();
	    panel.setBounds(551, 136, -551, -127);
	    getContentPane().add(panel);
	}
}