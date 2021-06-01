package pages;

import bean.Booking;
import bean.Lecture;
import logic.ReadOrder;
import logic.getNextWeek;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class shows details of the questionnaire of the order,
 * and allows trainer to add content for each lectures.
 * Title: LecContentController
 * @author Heqing Wang
 * @version 2.0
 */
public class LecContentController extends JPanel {
    final String color = "\"#A1887F\"";

    private OrdersPanel ordersPanel;
    private OrderSingle belongsTo;
    private Booking booking;
    private ArrayList<Lecture> lectures;
    private JButton sub;

    /**
     * The constructor of LecContentController.
     * @param ordersPanel
     * ordersPanel associated with
     * @param booking
     * The order that lectures belongs to.
     * @param belongsTo
     * The OrderSingle this LecContentController belongs to.
     */
    public LecContentController(OrdersPanel ordersPanel, OrderSingle belongsTo, Booking booking){
        this.ordersPanel = ordersPanel;
        this.belongsTo = belongsTo;
        this.booking = booking;
        this.lectures = ReadOrder.getLectures(booking.getBookingID());
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Add Content", JLabel.CENTER);
        title.setFont(new Font("Dialog", 1, 20));
        this.add(title, BorderLayout.NORTH );

        JPanel mainContent = new JPanel(new GridLayout(2,1));

        // questInfo shows details of questionnaire such as target and abilities
        JPanel qestInfo = new JPanel(new GridLayout(5,1));
        qestInfo.add(new JLabel("<html><font color=" + color + ">Customer's Height:</font> " + booking.getQuestionnaire().getHeight() + "</html>", JLabel.CENTER));
        qestInfo.add(new JLabel("<html><font color=" + color + ">Customer's Weight:</font> " + booking.getQuestionnaire().getWeight() + "</html>", JLabel.CENTER));
        qestInfo.add(new JLabel("<html><font color=" + color + ">Customer's Target:</font> " + booking.getQuestionnaire().getTarget() + "</html>", JLabel.CENTER));
        if(booking.getQuestionnaire().getTarget().equals("weightloss"))
            qestInfo.add(new JLabel("<html><font color=" + color + ">Target Weight:</font> " + booking.getQuestionnaire().getDetail() + "</html>", JLabel.CENTER));
        else
            qestInfo.add(new JLabel("<html><font color=" + color + ">Detailed Requirement:</font> " + booking.getQuestionnaire().getDetail() + "</html>", JLabel.CENTER));
        String exFrequency = null;
        switch (booking.getQuestionnaire().getHowOften()){
            case 1:
                exFrequency = "Everday";
                break;
            case 2:
                exFrequency = "Once a week";
                break;
            case 3:
                exFrequency = "Twice a week";
                break;
            case 4:
                exFrequency = "Once two weeks";
                break;
            case 5:
                exFrequency = "Longer than once two weeks";
                break;
        }
        qestInfo.add(new JLabel("<html><font color=" + color + ">Exercise Frequency:</font> " + exFrequency + "</html>", JLabel.CENTER));

        // lecsInfo shows all lectures of the order and allow trainer to add content
        JPanel lecsInfo = new JPanel( new GridLayout(lectures.size()+2,1));
        lecsInfo.add(new JLabel(""));
        lecsInfo.add(new JLabel("Please add content for following lectures", JLabel.CENTER));
        ArrayList<JTextField> lecsConent = new ArrayList<JTextField>();
        for(int i=0; i<lectures.size(); i++){
            JTextField lecCon = new JTextField(10);
            lecsConent.add(lecCon);
        }
        for(int i=0; i<lectures.size(); i++){
            JPanel eachLec = new JPanel();
            JLabel lecInfo = new JLabel(lectures.get(i).getDate() + getNextWeek.getTPbyMark(lectures.get(i).getMark()));
            eachLec.add(lecInfo);
            eachLec.add(lecsConent.get(i));
            lecsInfo.add(eachLec);
        }

        mainContent.add(qestInfo);
        mainContent.add(lecsInfo);
        this.add(mainContent, BorderLayout.CENTER);

        sub = new JButton("Submit");
        sub.addActionListener((e)->{
            int flag = 0;

            for(JTextField c : lecsConent){
                if(c.getText().equals("")){
                    flag = 1;
                    JOptionPane.showMessageDialog(null,"Please add content for all lectures!");
                    break;
                }
            }

            if(flag == 0){
                ReadOrder.addContent(booking, lecsConent);
                JOptionPane.showMessageDialog(null,"Contents added!");
                belongsTo.getContent().dispose();
                ordersPanel.getOrders();
            }
        });

        this.add(sub, BorderLayout.SOUTH);
    }
}
