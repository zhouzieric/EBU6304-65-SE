package logic;

import mdlaf.utils.MaterialColors;
import pages.OrdersPanel;

import javax.swing.*;
import java.awt.*;

public class FeedbackController {
    private OrdersPanel ordersPanel;

    private JFrame frame;
    private JPanel emptyPanel,infoPanel,ratePanel,reviewPanel,buttonPanel,jPanel;
    private JSlider selectRate;
    private JLabel rateLabel,reviewLabel,remindLabel;
    private JTextField reviewText;
    private JButton submitButton;
    private String bookingId;
    private String target;

    public FeedbackController(String bookingId,String target, OrdersPanel belongsTo){
        this.bookingId=bookingId;
        this.target = target;
        this.ordersPanel = belongsTo;
        frame = new JFrame();
        remindLabel = new JLabel("");
        remindLabel.setForeground(MaterialColors.LIME_A700);
    }

    public void thisPage(){
        infoPanel = new JPanel();
        ratePanel= new JPanel();
        reviewPanel = new JPanel();
        emptyPanel = new JPanel();
        buttonPanel = new JPanel();
        jPanel = new JPanel();
        submitButton = new JButton("Submit");
        jPanel.setLayout(new GridLayout(5,1));
        rateLabel = new JLabel("Booking Id:  "+bookingId);
        reviewLabel = new JLabel("Lecture Content:  "+target);
        infoPanel.add(rateLabel);
        infoPanel.add(reviewLabel);

        rateLabel = new JLabel("Please rate this booking:");
        selectRate = new JSlider(0,5,3);
        selectRate.setMajorTickSpacing(1);
        //  selectRate.setPaintTicks(true);
        selectRate.setPaintLabels(true);

        ratePanel.add(rateLabel);
        ratePanel.add(selectRate);

        reviewLabel = new JLabel("Comment: ");
        reviewText = new JTextField(20);
        reviewPanel.add(reviewLabel);
        reviewPanel.add(reviewText);

        buttonPanel.add(submitButton);
        submitButton.addActionListener(new FeedbackListener(this, ordersPanel));

        emptyPanel.add(remindLabel);

        jPanel.add(emptyPanel);
        jPanel.add(infoPanel);
        jPanel.add(ratePanel);
        jPanel.add(reviewPanel);
        jPanel.add(buttonPanel);

        frame.setContentPane(jPanel);
        frame.setTitle("Feedback");
        frame.setBounds(400,200,800, 400);
        // frame.setSize(800,400);
        frame.setVisible(true);
    }

    public JSlider getSelectRate() {
        return selectRate;
    }

    public void setSelectRate(JSlider selectRate) {
        this.selectRate = selectRate;
    }

    public JTextField getReviewText() {
        return reviewText;
    }

    public void setReviewText(JTextField reviewText) {
        this.reviewText = reviewText;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getRemindLabel() {
        return remindLabel;
    }

    public void setRemindLabel(JLabel remindLabel) {
        this.remindLabel = remindLabel;
    }
}
