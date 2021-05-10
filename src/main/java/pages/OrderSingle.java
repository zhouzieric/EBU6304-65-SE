package pages;

import bean.Booking;
import bean.Lecture;
import logic.ReadOrder;
import logic.ReadandWrite;
import logic.getNextWeek;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderSingle extends JPanel {
    OrderMenuContainer orderMenuContainer;
    ArrayList<Lecture> lectures;
    Booking b;

//    public OrderSingle(Booking b, OrdersPanel belongsTo ){
      public OrderSingle(Booking b, OrderMenuContainer orderMenuContainer){
        this.setBorder(BorderFactory.createLineBorder(MaterialColors.GRAY_100,2,true));

        this.orderMenuContainer = orderMenuContainer;
        this.b = b;
        lectures = ReadOrder.getLectures(b.getBookingID());

          //如果有评价
          if(b.getStar() != null && b.getFeedback() != null){
              //System.out.println(b.getBookingID() +b.getStar() +b.getFeedback());
              this.setPreferredSize(new Dimension(0, 140));
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
              this.setPreferredSize(new Dimension(0, 70));
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
        BIDandP.add(new JLabel("<html><font color=\"#A1887F\">Booking ID:</font> " + b.getBookingID() + "</html>"));
        BIDandP.add(new JLabel("<html><font color=\"#A1887F\">Price:</font> " + b.getPrice() + "</html>"));
        JPanel TIDandTN = new JPanel(new GridLayout(1,2));
        TIDandTN.add(new JLabel("<html><font color=\"#A1887F\">Trainer ID:</font> " + b.getTraID() + "</html>"));
        TIDandTN.add(new JLabel("<html><font color=\"#A1887F\">Trainer Name:</font> " + ReadandWrite.getName(b.getTraID()) + "</html>"));
        JPanel CT = new JPanel();
        JLabel createTime = new JLabel("<html><font color=\"#A1887F\">Created Time:</font> " + b.getCreatDate() + "</html>");
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
            } else{
                //弹窗 用户输入反馈
                //需检查是否已有反馈
                String[] possibleValues = { "null", "1", "2", "3", "4", "5" };
                String selectedValue = (String) JOptionPane.showInputDialog(null, "Please grade this order", "Grade Order",
                        JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
                System.out.println(selectedValue);
                String feedback = JOptionPane.showInputDialog("What's your feedback towards this order");
                System.out.println(feedback);
                //if有值
                ReadOrder.addFeedback(feedback,Integer.parseInt(selectedValue),b.getBookingID());
            }

            orderMenuContainer.removeAll();
            orderMenuContainer.setTabPlacement(JTabbedPane.LEFT);

            orderMenuContainer.setShowAllOrders(new OrdersPanel(0, orderMenuContainer));
            orderMenuContainer.setShowPaidOrders(new OrdersPanel(1, orderMenuContainer));
            orderMenuContainer.setShowOngoingOrders(new OrdersPanel(2, orderMenuContainer));
            orderMenuContainer.setShowFinishedOrders(new OrdersPanel(3, orderMenuContainer));
            orderMenuContainer.setShowCanceledOrders(new OrdersPanel(4, orderMenuContainer));

            orderMenuContainer.addTab("All orders",orderMenuContainer.getShowAllOrders());
            orderMenuContainer.addTab("Paid orders",orderMenuContainer.getShowPaidOrders());
            orderMenuContainer.addTab("Ongoing orders",orderMenuContainer.getShowOngoingOrders());
            orderMenuContainer.addTab("Finished orders",orderMenuContainer.getShowFinishedOrders());
            orderMenuContainer.addTab("Canceled orders",orderMenuContainer.getShowCanceledOrders());
//            orderMenuContainer.addTab("My calendar",ctable);
            orderMenuContainer.revalidate();
        }

    }

}