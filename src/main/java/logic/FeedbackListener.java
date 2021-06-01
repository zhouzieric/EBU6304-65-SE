package logic;

import pages.OrdersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *This class is used to respond to user actions on the feedback page, collect user input.
 * @author Wang Pei
 * @version 4.0
 */

public class FeedbackListener implements ActionListener {
    private pages.FeedbackController feedback;
    private OrdersPanel ordersPanel;

    /**
     * Construction of this class
     * @param  frame FeedbackController
     * @param ordersPanel OrderPanel
     */
    public FeedbackListener(pages.FeedbackController frame, OrdersPanel ordersPanel){
        this.feedback = frame;
        this.ordersPanel = ordersPanel;
    }
    /**
     * actionPerformed of the actionlistener in feedback page.
     * @param  e ActionEvent
     */
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
