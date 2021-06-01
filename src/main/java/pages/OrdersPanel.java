package pages;

import bean.Booking;
import logic.ReadOrder;
import logic.readAccLogin;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class OrdersPanel contains orders in a certain state (all/paid/ongoing/finished/canceled).
 * Since A=a gym member may cancel Paid or Ongoing orders at any time
 * even if some sessions in an Ongoing order have been attended,
 * showFinishedOrders contains orders all finished, and the finished sessions of an canceled order.
 * showCanceledOrders contains orders all canceled, that is, no sessions have been attended in this order;
 * and the canceled sessions of an canceled order.
 * Title: OrdersPanel
 * @author Heqing Wang
 * @version 2.0
 */
public class OrdersPanel extends JScrollPane {
    private JPanel inOrdersPanel;
    private int targetStatus;
    private ArrayList<Booking> bookings;

    /**
     * The constructor of OrdersPanel.
     * @param targetStatus Identifier of a certain state (all:0 paid:1 ongoing:2 finished:3 canceled:4).
     */
    public OrdersPanel(int targetStatus){
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.targetStatus = targetStatus;
        inOrdersPanel = new JPanel();
        this.getViewport().add(inOrdersPanel);
        inOrdersPanel.setLayout(new BoxLayout(inOrdersPanel,BoxLayout.Y_AXIS));

        getOrders();
    }

    /**
     * Get orders in the assigned state. Then for each order creates JPanel OrderSingel.
     * Finally vertical strut is added to avoid self-adapted height of OrderSingel if necessary.
     */
    public void getOrders(){
        this.bookings = ReadOrder.getBookings(readAccLogin.readFile(),targetStatus+"");

        if(bookings != null){
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

            // Calculate the height of all obtained orders. If shorter than that of StandardFrame, add vertical strut.
            int ordersHeight = 14*bookings.size() + 220*feededOrder + 110*(bookings.size()-feededOrder);
            if(ordersHeight < StandardFrame.frameH){
                inOrdersPanel.add(Box.createVerticalStrut(StandardFrame.frameH-ordersHeight));
            }

            this.revalidate();
        } else{
            // no bookings in this state.
            String noBookings = null;
            if((readAccLogin.readFile().charAt(0)+"").equals("C")){
                noBookings = "You have not booked any live lectures!";
            }
            else if((readAccLogin.readFile().charAt(0)+"").equals("T")){
                noBookings = "You have not receice any live orders!";
            }
            JLabel NoneOrders= new JLabel(noBookings);
            inOrdersPanel.add(NoneOrders);
        }
    }

    public JPanel getInOrdersPanel() {
        return this.inOrdersPanel;
    }
}
