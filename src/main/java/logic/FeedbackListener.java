package logic;

import bean.Questionnaire;
import pages.CalendarView;
import pages.OrdersPanel;
import pages.QuestionaireController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class FeedbackListener implements ActionListener {
    private FeedbackController feedback;
    private OrdersPanel ordersPanel;

    public FeedbackListener(FeedbackController frame, OrdersPanel ordersPanel){
        this.feedback = frame;
        this.ordersPanel = ordersPanel;
    }
    public void actionPerformed(ActionEvent e){
        System.out.println(feedback.getSelectRate().getValue());
        System.out.println(feedback.getReviewText().getText());

        if(feedback.getReviewText().getText().equals("")){
            feedback.getRemindLabel().setText("Please input your feedback.");
            feedback.thisPage();
        }else{
            ReadOrder.addFeedback(feedback.getReviewText().getText(),feedback.getSelectRate().getValue(),feedback.getBookingId());
            JOptionPane.showMessageDialog(null, "Feedback Successfully!", "",JOptionPane.INFORMATION_MESSAGE);
            feedback.getFrame().setVisible(false);

            ordersPanel.getOrders();
        }




    }
}
