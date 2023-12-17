import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.toedter.calendar.JCalendar;

public class DoubleClickCalendarExample {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Double Click Calendar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JCalendar calendar = new JCalendar();
        calendar.addMouseListener(new DoubleClickListener(frame));

        JPanel panel = new JPanel();
        panel.add(calendar);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class DoubleClickListener extends MouseAdapter {

    private JFrame parentFrame;

    public DoubleClickListener(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

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
            newFrame.add(panel);
            newFrame.setSize(200, 100);
            newFrame.setLocationRelativeTo(parentFrame);
            newFrame.setVisible(true);
        }
    }
}
