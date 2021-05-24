package pages;

import bean.Booking;
import bean.Lecture;
import logic.FeedbackController;
import logic.ReadOrder;
import logic.ReadandWrite;
import logic.getNextWeek;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

public class OrderSingle extends JPanel {
    OrdersPanel ordersPanel;
    ArrayList<Lecture> lectures;
    Booking b;
    final String color = "\"#A1887F\"";

    public OrderSingle(Booking b, OrdersPanel belongsTo ){
//      public OrderSingle(Booking b, OrderMenuContainer orderMenuContainer){
        this.setBorder(BorderFactory.createLineBorder(MaterialColors.GRAY_100,2,true));

        this.ordersPanel = belongsTo;
        this.b = b;
        lectures = ReadOrder.getLectures(b.getBookingID());

          //如果有评价
          if(b.getStar() != null && b.getFeedback() != null){
              //System.out.println(b.getBookingID() +b.getStar() +b.getFeedback());
              this.setPreferredSize(new Dimension(0, 220));
              this.setLayout(new GridLayout(2,1));

              JPanel row1  = new JPanel();
              createRow1(row1);
              this.add(row1);

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
//              System.out.println(b.getBookingID() +b.getStar() +b.getFeedback());
              this.setPreferredSize(new Dimension(0, 110));
              createRow1(this);
          }


    }

    private void createRow1(JPanel row1){
        //System.out.println("i am here---------------------------------------------------------------");
        row1.setLayout(new GridLayout(1,3));
        //row1.setPreferredSize(new Dimension(500, 70));
        row1.setBorder(BorderFactory.createLineBorder(MaterialColors.GRAY_100,2,true));
        //
        JPanel col1 = new JPanel(new GridLayout(3,1));
        col1.setBorder(BorderFactory.createLineBorder(MaterialColors.GRAY_100,2,true));
        JPanel BIDandP = new JPanel(new GridLayout(1,2));
        BIDandP.add(new JLabel("<html><font color=" + color + ">Booking ID:</font> " + b.getBookingID() + "</html>"));
        BIDandP.add(new JLabel("<html><font color=" + color + ">Price:</font> " + b.getPrice() + "</html>"));
        JPanel TIDandTN = new JPanel(new GridLayout(1,2));
        TIDandTN.add(new JLabel("<html><font color=" + color + ">Trainer ID:</font> " + b.getTraID() + "</html>"));
        TIDandTN.add(new JLabel("<html><font color=" + color + ">Trainer Name:</font> " +"<br>" + ReadandWrite.getName(b.getTraID()) + "</html>"));
        JPanel CT = new JPanel();
        JLabel createTime = new JLabel("<html><font color=" + color + ">Created Time:</font> " + b.getCreatDate() + "</html>");
        CT.add(createTime);
        col1.add(BIDandP);
        col1.add(TIDandTN);
        col1.add(CT);
        row1.add(col1);

        JLabel lecInfo;
        StringBuilder lecInfoString = new StringBuilder("<html>");

        JButton canOrfeed;
        String jbText;

        //已支付或进行中 1*3
        //完成或取消中 若已评价 2*1 未评价1*3
        //课程的date属性已知 需根据标号算时间段
        if(b.getStatus() == 1 || b.getStatus() ==2){
            //已支付：显示所有课程 进行中：显示所有课程（标明是否完成
            if(b.getStatus() == 1){
                //已支付
                for(Lecture l : lectures){
                    lecInfoString.append(l.getDate()).append(getNextWeek.getTPbyMark(l.getMark())).append("<br>");
                }
            }
            else {
                //进行中
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
            }
            jbText = "Cancel";
        } else{
            //已完成 未取消完成订单 及 取消订单中完成部分
            //已取消 取消订单中取消课程
            if(b.getStatus() ==3){
                //已完成
                for(Lecture l : lectures){
                    lecInfoString.append(l.getDate()).append(getNextWeek.getTPbyMark(l.getMark())).append("<br>");
                }
                jbText = "Feedback";
            }else {
                //已取消
                //已取消课程显示创建时间 及 取消时间（全部课程未上取消订单
                //infoString += "<br>    Canceled Date: " + lectures.get(0).getCancelDate();
                CT.remove(createTime);
                CT.add(new JLabel("Canceled Date: " + lectures.get(0).getCancelDate()));

                for(Lecture l : lectures){
                    lecInfoString.append(l.getDate()).append(" ").append(getNextWeek.getTPbyMark(l.getMark())).append("<br>");
//                    System.out.println(lecInfoString);
                }
                jbText = "Concelled. No-Operation";
            }
        }

        lecInfoString.append("</html>");
        lecInfo = new JLabel(lecInfoString.toString(),JLabel.CENTER);
        row1.add(lecInfo);
        row1.add(canOrfeed = new JButton(jbText));
        canOrfeed.addActionListener(new cORfListener());

    }

    private class cORfListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
            if(button.getText().equals("Cancel")){
                System.out.println("点击了取消");
                System.out.println("id:"+b.getBookingID());
                ReadOrder.cancelBooking(b.getBookingID());

                ordersPanel.getOrders();
            } else if(button.getText().equals("Feedback")){
                //需检查是否已有反馈
                if(ReadOrder.canFeedback(b.getBookingID())){
                    FeedbackController feedback = new FeedbackController(b.getBookingID(),b.getQuestionnaire().getTarget(), ordersPanel);
                    feedback.getFrame().addWindowFocusListener(new WindowFocusListener() {
                        public void windowGainedFocus(WindowEvent e) {
                        }
                        public void windowLostFocus(WindowEvent e) {
                            e.getWindow().toFront();
                        }
                    });

                    feedback.thisPage();
                }else{
                    JOptionPane.showMessageDialog(null, "You have reviewed this order.", null,JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }


}