import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sample_icon {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Marquee Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 50);

            // Create a JLabel to display the scrolling text
            JLabel marqueeLabel = new JLabel("This is a horizontal marquee text.     ");
            frame.add(marqueeLabel);

            // Create a Timer to scroll the text
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = marqueeLabel.getText();
                    marqueeLabel.setText(text.substring(1) + text.charAt(0));
                }
            });
            timer.start();

            frame.setVisible(true);
        });
    }
}
