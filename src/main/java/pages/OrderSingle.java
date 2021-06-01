package pages;

import bean.Booking;
import bean.Lecture;
import logic.*;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class creates creates JPanel for each order.
 * If the user is a gym member, it will show BookingID, Price of this order, TrainerID, Trainer Name and Created/Canceled Date;
 * If the user is a gym trainer, it will show BookingID, Price of this order, CustomerID, Customer Name and Created/Canceled Date.
 * For a Paid or Ongoing order, member may cancel it; for a Finished order, member may give feedback.
 * For a Paid order, trainer may add content for lectures.
 * Title: OrderSingle
 * @author Heqing Wang
 * @version 3.0
 */
public class OrderSingle extends JPanel {
    final static String color = "\"#A1887F\"";
    OrdersPanel ordersPanel;
    Booking b;
    ArrayList<Lecture> lectures;

    private JFrame belongsTo;
    JDialog content;

    /**
     * The constructor of OrdersPanel.
     * If the order have been given feedback, its height is 220; otherwise 110.
     * @param b The booking this JPanel constructed for.
     * @param belongsTo The OrdersPanel this OrderSingle belongs to.
     */
    public OrderSingle(Booking b, OrdersPanel belongsTo ){
        this.setBorder(BorderFactory.createLineBorder(MaterialColors.GRAY_100,2,true));

        this.ordersPanel = belongsTo;
        this.b = b;
        lectures = ReadOrder.getLectures(b.getBookingID());

        // if the order have been given feedback, it has 2 rows.
        if(b.getStar() != null && b.getFeedback() != null){
            this.setPreferredSize(new Dimension(0, 220));
            this.setLayout(new GridLayout(2,1));

            // row1 shows details of this order and a JButton.
            JPanel row1  = new JPanel();
            createRow1(row1);
            this.add(row1);

            // row2 shows feedback of this order.
            JPanel row2 = new JPanel();
            row2.setLayout(new GridLayout(2,1));
            JPanel row21 = new JPanel();
            row21.add(new JLabel("Star: "));
            for(int i = 0; i < b.getStar(); i++){
                JLabel s = new JLabel();
                ImageIcon icon = new ImageIcon("src/main/image/Star.png");
                icon.setImage(icon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
                s.setIcon(icon);
                row21.add(s);
            }
            JLabel feed = new JLabel("<html>Feedback: " + b.getFeedback() + "<html>",JLabel.CENTER);
            row2.add(row21);
            row2.add(feed);
            this.add(row2);
        }
        else{
            // if the order have not been given feedback, it has only 1 row.
            this.setPreferredSize(new Dimension(0, 110));
            createRow1(this);
        }
    }

    /**
     * This method construct the first row of JPanel OrderSingle.
     * This row has three columns, being details of this order, lectures and a JButton respectively.
     * @param row1 The JPanel in which content to be added.
     */
    private void createRow1(JPanel row1){
        row1.setLayout(new GridLayout(1,3));
        row1.setBorder(BorderFactory.createLineBorder(MaterialColors.GRAY_100,2,true));
        // set col1
        JPanel col1 = new JPanel(new GridLayout(3,1));
        col1.setBorder(BorderFactory.createLineBorder(MaterialColors.GRAY_100,2,true));
        JPanel BIDandP = new JPanel(new GridLayout(1,2));
        BIDandP.add(new JLabel("<html><font color=" + color + ">Booking ID:</font> " + b.getBookingID() + "</html>"));
        BIDandP.add(new JLabel("<html><font color=" + color + ">Price:</font> " + b.getPrice() + "</html>"));
        JPanel IDandN = new JPanel(new GridLayout(1,2));
        if((readAccLogin.readFile().charAt(0)+"").equals("C")){
            IDandN.add(new JLabel("<html><font color=" + color + ">Trainer ID:</font> " + b.getTraID() + "</html>"));
            IDandN.add(new JLabel("<html><font color=" + color + ">Trainer Name:</font> " +"<br>" + ReadandWrite.getName(b.getTraID()) + "</html>"));
        } else if((readAccLogin.readFile().charAt(0)+"").equals("T")){
            IDandN.add(new JLabel("<html><font color=" + color + ">Customer ID:</font> " + b.getCusID() + "</html>"));
            IDandN.add(new JLabel("<html><font color=" + color + ">Customer Name:</font> " +"<br>" + ReadandWrite.getName(b.getCusID()) + "</html>"));
        }
        JPanel CT = new JPanel();
        JLabel createTime = new JLabel("<html><font color=" + color + ">Created Time:</font> " + b.getCreatDate() + "</html>");
        CT.add(createTime);
        col1.add(BIDandP);
        col1.add(IDandN);
        col1.add(CT);
        row1.add(col1);

        // declare col2 and col3 first. Content will be added according to order itself.
        JLabel lecInfo;
        StringBuilder lecInfoString = new StringBuilder("<html>");
        JButton canORfeed;
        String jbText = "No-Operation";

        // if a Paid or Ongoing order
        if(b.getStatus() == 1 || b.getStatus() ==2){
            if(b.getStatus() == 1){
                // a Paid order
                for(Lecture l : lectures){
                    lecInfoString.append(l.getDate()).append(getNextWeek.getTPbyMark(l.getMark())).append("<br>");
                }
                // as a trainer, add content for lectures if haven't done so
                if((readAccLogin.readFile().charAt(0)+"").equals("T")){
                    if(lectures.get(0).getContent().equals("null"))
                        jbText = "Add content for lectures";
                    else
                        jbText = "Content added. No-Operation";
                }
            }
            else {
                // a Ongoing order
                for(Lecture l : lectures){
                    lecInfoString.append(l.getDate()).append(getNextWeek.getTPbyMark(l.getMark()));
                    switch (ReadOrder.isFinished(l.getMark())){
                        case 1: //未开始
                            lecInfoString.append(" (to be started)<br>");
                            break;
                        case 2: //进行中
                            lecInfoString.append(" (doing)<br>");
                            break;
                        case 3: //已完成
                            lecInfoString.append(" (done)<br>");
                            break;
                    }
                }
//                if((readAccLogin.readFile().charAt(0)+"").equals("T")){
//                    jbText = "No-Operation";
//                }
            }
            // as a member, these two type order can be canceled
            if((readAccLogin.readFile().charAt(0)+"").equals("C"))
                jbText = "Cancel";

        } else{ // if a Finished or Canceled order
            if(b.getStatus() ==3){
                // a finished order
                for(Lecture l : lectures){
                    lecInfoString.append(l.getDate()).append(getNextWeek.getTPbyMark(l.getMark())).append("<br>");
                }
                // as a member, feedback may be given to a finished order if haven't done so.
                if((readAccLogin.readFile().charAt(0)+"").equals("C")) {
                    if (b.getFeedback() == null && b.getStar() == null)
                        jbText = "Feedback";
                    else{
                        jbText = "Feedbacked. No-Operation.";
                    }
                }
//                } else if((readAccLogin.readFile().charAt(0)+"").equals("T")){
//                    jbText = "No-Operation";
//                }
            }else {
                // a canceled order. Canceled Date will be displayed instead of Created Date
                CT.remove(createTime);
                CT.add(new JLabel("<html><font color=" + color + ">Canceled Date:</font> " + lectures.get(0).getCancelDate() + "</html>"));

                for(Lecture l : lectures){
                    lecInfoString.append(l.getDate()).append(" ").append(getNextWeek.getTPbyMark(l.getMark())).append("<br>");
                }

                jbText = "Concelled. No-Operation";
            }
        }

        lecInfoString.append("</html>");
        lecInfo = new JLabel(lecInfoString.toString(),JLabel.CENTER);
        row1.add(lecInfo);
        row1.add(canORfeed = new JButton(jbText));
        canORfeed.addActionListener(new cORfListener(this));

    }

    /**
     * This inner class implements ActionListener.
     * Response to Feedback or Cancel or Add content for lectures.
     */
    private class cORfListener implements ActionListener{
        OrderSingle orderSingle;

        /**
         * The constructor of cORfListener.
         * This row has three columns, being details of this order, lectures and a JButton respectively.
         * @param orderSingle The JPanel OrderSingle this listener belongs to.
         */
        public cORfListener(OrderSingle orderSingle){
            this.orderSingle = orderSingle;
        }

        /**
         * This method overrides actionPerformed method.
         * In case Feedback, a FeedbackController instance will be created in which member may give stars and feedback towards this order.
         * In case Cancel, details in the booking.TXT and lectures.TXT will be changed, then the order will be displayed in showFinishedOrders or/and showCanceledOrders.
         * In case Add content for lectures, a JPanel LecContentController will be created in which trainer may add contents.
         * @param e Presents ActionEvent.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
            if(button.getText().equals("Cancel")){
                ReadOrder.cancelBooking(b.getBookingID());
                ordersPanel.getOrders();

            } else if(button.getText().equals("Feedback")){
//                if(ReadOrder.canFeedORContent(b.getBookingID(),1)){
                    FeedbackController feedback = new FeedbackController(b.getBookingID(),b.getQuestionnaire().getTarget(), ordersPanel);
                    feedback.getFrame().addWindowFocusListener(new WindowFocusListener() {
                        public void windowGainedFocus(WindowEvent e) {
                        }
                        public void windowLostFocus(WindowEvent e) {
                            e.getWindow().toFront();
                        }
                    });

                    feedback.thisPage();
//                }else{
//                    JOptionPane.showMessageDialog(null, "You have reviewed this order.", null,JOptionPane.INFORMATION_MESSAGE);
//                }

            }else if(button.getText().equals("Add content for lectures")){
//                if(ReadOrder.canFeedORContent(b.getBookingID(),2)){
                    LecContentController lecContentController = new LecContentController(ordersPanel, orderSingle, b);

                    content = new JDialog(belongsTo, "Add Content for Lectures", true);
                    content.setBounds(400, 200, 400, 500);

                    content.add(lecContentController);
                    content.setVisible(true);
                    content.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            JDialog dia = (JDialog) e.getSource();
                            dia.dispose();
                        }
                    });
//                }else{
//                    JOptionPane.showMessageDialog(null, "You have added contents for this order.", null,JOptionPane.INFORMATION_MESSAGE);
//                }
            }
        }

    }

    public JDialog getContent() {
        return content;
    }
}