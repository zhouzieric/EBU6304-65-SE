/**
 * This class is a UI for administrator
 * to view booking feedback from customers
 * @author Gui Jiayi
 * @version 4.2
 */
package pages;

import logic.FeedbackRead;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminFeedback extends JPanel{
    private JFrame belongsTo;
    private JPanel container;
    private CardLayout manager;

    /**
     * This is the constructor initializer for UI to show
     */
    public AdminFeedback(){
        String[] headNames = {"Booking ID","Customer ID","Trainer ID","Mark","Comment"};
        ArrayList<String[]> feedback = FeedbackRead.readFeedback();
        Object[][] feedbackInfo = (Object[][])feedback.toArray(new Object[0][0]);
        DefaultTableModel dtb = new DefaultTableModel(feedbackInfo,headNames);
        JTable table = new JTable(dtb);

        //set table style
        table.setSelectionForeground(Color.DARK_GRAY);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.GRAY);

        table.getTableHeader().setFont(new Font(null,Font.BOLD,14));
        //table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setResizingAllowed(false);  //not allow to resize manually
        table.getTableHeader().setReorderingAllowed(false);  //not allow to reorder table manually

        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(500);
        table.setPreferredScrollableViewportSize(new Dimension(1250,600));

        JScrollPane scrollPane = new JScrollPane(table);  //add the component on a scroll pane
        this.add(scrollPane);
    }

    //test activator
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        AdminFeedback af = new AdminFeedback();

        StandardFrame frame = new StandardFrame("ADMIN", MenuPart.MENU_ADMIN);


        //frame.setJMenuBar(mp);

        frame.add(af);
        frame.setSize(1100,600);    //
        frame.setVisible(true);

    }

    public JFrame getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(JFrame belongsTo) {
        this.belongsTo = belongsTo;
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        this.container = container;
    }

    public CardLayout getManager() {
        return manager;
    }

    public void setManager(CardLayout manager) {
        this.manager = manager;
    }
}
