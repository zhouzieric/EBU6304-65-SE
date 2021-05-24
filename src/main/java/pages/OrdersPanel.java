package pages;

import bean.Booking;
import logic.ReadOrder;
import logic.ReadandWrite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrdersPanel extends JScrollPane {
    private JPanel inOrdersPanel;
    private int targetStatus;
    private OrderMenuContainer orderMenuContainer;
    private ArrayList<Booking> bookings;

    public OrdersPanel(int targetStatus, OrderMenuContainer orderMenuContainer){
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.targetStatus = targetStatus;
        this.orderMenuContainer = orderMenuContainer;
        inOrdersPanel = new JPanel();
        this.getViewport().add(inOrdersPanel);
        inOrdersPanel.setLayout(new BoxLayout(inOrdersPanel,BoxLayout.Y_AXIS));

        getOrders();
    }

    public void getOrders(){
        //获取登录者ID
        int cNum = ReadandWrite.rFile("acc_login");
        String CID = "C" + cNum;
        this.bookings = ReadOrder.getBookings(CID,targetStatus+""); //why String

        if(bookings != null){
//            int num = bookings.size();
//            int lim = 4;
//
//            if(num < lim)
//                inOrdersPanel.setPreferredSize(new Dimension(0, StandardFrame.frameH));
//            else
//                inOrdersPanel.setPreferredSize(new Dimension(0, StandardFrame.frameH + StandardFrame.frameH * (num / lim)));

//            inOrdersPanel.setPreferredSize(new Dimension(0,140*num));
            int feededOrder = 0;
            inOrdersPanel.removeAll();

            for(Booking b : bookings){
                inOrdersPanel.add(Box.createVerticalStrut(14));
                JPanel ordersingle = new OrderSingle(b,this);
                if(b.getStar() != null && b.getFeedback() != null){
                    feededOrder++;
                }
                inOrdersPanel.add(ordersingle);
            }

            //all及finished涉及反馈
            //若不含反馈6. <6
            //若反馈1个 非反馈<4
            int ordersHeight = 14*bookings.size() + 220*feededOrder + 110*(bookings.size()-feededOrder);
            if(ordersHeight < StandardFrame.frameH){
                inOrdersPanel.add(Box.createVerticalStrut(StandardFrame.frameH-ordersHeight));
            }

            this.revalidate();


        } else{
            //
            JLabel NoneOrders= new JLabel("You have not booked any live lectures! ");
            inOrdersPanel.add(NoneOrders);
        }
    }

    public JPanel getInOrdersPanel() {
        return this.inOrdersPanel;
    }
}
