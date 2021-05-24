package pages;

import javax.swing.*;


public class OrderMenuContainer extends JTabbedPane {
    //private JPanel ctable;
    private OrdersPanel showAllOrders;
    private OrdersPanel showPaidOrders;
    private OrdersPanel showOngoingOrders;
    private OrdersPanel showFinishedOrders;
    private OrdersPanel showCanceledOrders;



    public OrderMenuContainer(){
        super();
        setTabPlacement(JTabbedPane.LEFT);//默认在左，右边不好看

        showAllOrders = new OrdersPanel(0, this);
        showPaidOrders = new OrdersPanel(1, this);
        showOngoingOrders = new OrdersPanel(2, this);
        showFinishedOrders = new OrdersPanel(3, this);
        showCanceledOrders = new OrdersPanel(4, this);

       // System.out.println("i am first here ------------------------------------------------------------");

        this.addTab("All orders",showAllOrders);
        this.addTab("Paid orders",showPaidOrders);
        this.addTab("Ongoing orders",showOngoingOrders);
        this.addTab("Finished orders",showFinishedOrders);
        this.addTab("Canceled orders",showCanceledOrders);
      //  this.addTab("My calendar",ctable);

        this.addChangeListener(e -> {
            if (e.getSource() instanceof JTabbedPane) {
                JTabbedPane jTabbedPane = (JTabbedPane) e.getSource();

                //int i = jTabbedPane.getSelectedIndex();
                OrdersPanel ordersPanel = (OrdersPanel) jTabbedPane.getSelectedComponent();

                ordersPanel.getOrders();
                //jTabbedPane.setSelectedIndex(i);

            }

        });







    }


//
//    public JPanel getCtable() {
//        return ctable;
//    }
//
//    public void setCtable(JPanel ctable) {
//        this.ctable = ctable;
//    }

    public OrdersPanel getShowAllOrders() {
        return showAllOrders;
    }

    public void setShowAllOrders(OrdersPanel showAllOrders) {
        this.showAllOrders = showAllOrders;
    }

    public OrdersPanel getShowPaidOrders() {
        return showPaidOrders;
    }

    public void setShowPaidOrders(OrdersPanel showPaidOrders) {
        this.showPaidOrders = showPaidOrders;
    }

    public OrdersPanel getShowOngoingOrders() {
        return showOngoingOrders;
    }

    public void setShowOngoingOrders(OrdersPanel showOngoingOrders) {
        this.showOngoingOrders = showOngoingOrders;
    }

    public OrdersPanel getShowFinishedOrders() {
        return showFinishedOrders;
    }

    public void setShowFinishedOrders(OrdersPanel showFinishedOrders) {
        this.showFinishedOrders = showFinishedOrders;
    }

    public OrdersPanel getShowCanceledOrders() {
        return showCanceledOrders;
    }

    public void setShowCanceledOrders(OrdersPanel showCanceledOrders) {
        this.showCanceledOrders = showCanceledOrders;
    }
}
