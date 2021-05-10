package pages;

import bean.Booking;
import logic.ReadOrder;
import logic.ReadandWrite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrdersPanel extends JScrollPane {
    private JPanel inOrdersPanel;
    private ArrayList<Booking> bookings;
    private OrderMenuContainer orderMenuContainer;

    public OrdersPanel(int targetStatus, OrderMenuContainer orderMenuContainer){
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.orderMenuContainer = orderMenuContainer;
        inOrdersPanel = new JPanel();
        this.getViewport().add(inOrdersPanel);
        inOrdersPanel.setLayout(new BoxLayout(inOrdersPanel,BoxLayout.Y_AXIS));

        //获取登录者ID
        int cNum = ReadandWrite.rFile("acc_login");
        String CID = "C" + cNum;
        this.bookings = ReadOrder.getBookings(CID,targetStatus+""); //why String
        System.out.println("let me see here-----------------------------------------");
        for(Booking b : bookings){
            System.out.println(b.toString());
        }

        getOrders();



    }

    public void getOrders(){
        if(bookings != null){
            int num = bookings.size();
//            int lim = 4;
//
//            if(num < lim)
//                inOrdersPanel.setPreferredSize(new Dimension(0, StandardFrame.frameH));
//            else
//                inOrdersPanel.setPreferredSize(new Dimension(0, StandardFrame.frameH + StandardFrame.frameH * (num / lim)));

            inOrdersPanel.setPreferredSize(new Dimension(0,140*num));
            inOrdersPanel.removeAll();

            for(Booking b : bookings){
                inOrdersPanel.add(Box.createVerticalStrut(14));
                System.out.println("what about here-------------------------------------------");
                JPanel ordersingle = new OrderSingle(b,orderMenuContainer );

                inOrdersPanel.add(ordersingle);
            }
        } else{
            //
            JLabel NoneOrders= new JLabel("You have not booked any live lectures! ");
            inOrdersPanel.add(NoneOrders);
        }
    }

}
