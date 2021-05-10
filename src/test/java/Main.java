import java.awt.BorderLayout;
/*from  w  w  w  .j ava2 s .  co  m*/
import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class Main {

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextField textField = new JTextField();

        JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        BoundedRangeModel brm = textField.getHorizontalVisibility();
        scrollBar.setModel(brm);
        panel.add(textField);
        panel.add(scrollBar);


        frame.add(panel, BorderLayout.NORTH);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }
}
     