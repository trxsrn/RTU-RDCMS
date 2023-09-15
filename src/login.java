import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;


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
	    setBounds(100, 100, 450, 300);
	    contentPane = new JPanel();
	    setUndecorated(true);
	    setVisible(true);
	       
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int centerX = (screenSize.width - getWidth()) / 2;
	    int centerY = (screenSize.height - getHeight()) / 2;
	    setLocation(centerX, centerY);
	    getContentPane().setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("Research Management System");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblNewLabel.setBounds(112, 125, 234, 45);
	    getContentPane().add(lblNewLabel);
	    
	    passwordField = new JPasswordField();
	    passwordField.setBounds(146, 183, 154, 19);
	    getContentPane().add(passwordField);
	    
	    JButton btnNewButton = new JButton("LOGIN");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		dashboard dashboard = new dashboard();
	    		dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
    			dashboard.setVisible(true);
    			dispose();
	    	}
	    	
	    	
	    });
	    btnNewButton.setBackground(new Color(0, 0, 205));
	    btnNewButton.setBounds(146, 212, 154, 31);
	    getContentPane().add(btnNewButton);
	    
	    JLabel lblNewLabel_1 = new JLabel("Forgot Password?");
	    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1.setBounds(147, 277, 154, 13);
	    getContentPane().add(lblNewLabel_1);
	}
}