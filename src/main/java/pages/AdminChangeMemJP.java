package pages;

import logic.AdminEvent;



import logic.AdminEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
 *Class that displays hierarchy information
 * @author Yixin Li
 * @version 5.6.3
 */
public class AdminChangeMemJP extends JPanel {
    private JFrame belongsTo;
    private JPanel container;
    private CardLayout manager;

    private JButton modify;
    private JButton delete_b;
    private JButton add_b;
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane jsp;
    private JPanel jp;
    private JPanel p1;
    private JPanel p2;

    private AdminEvent adminEvent;
    /**
     * This a constructor to pass parameters
     * @param tabledata
     * Object[][] to show the table information
     * @param columntitle
     * Object[] to show the table header
     * @param type
     * String to indication which type of marketing information to show
     */
    public AdminChangeMemJP(Object[][] tabledata, Object[] columntitle, String type){

        this.setLayout(new BorderLayout());
        jp=new JPanel();
        p1=new JPanel();
        p2=new JPanel();
        delete_b=new JButton("Delete");
        add_b=new JButton("Add");
        model=new DefaultTableModel(tabledata,columntitle);
        modify=new JButton("Modify");
        table=new JTable(model);
        jsp=new JScrollPane(table);
        jp.setLayout(new GridLayout(1,2));
        jp.add(p1);
        jp.add(p2);
        jp.setPreferredSize(new Dimension(0,100));
        this.add(modify, BorderLayout.NORTH);
        this.add(jsp,BorderLayout.CENTER);
        this.add(jp,BorderLayout.SOUTH);
        p1.add(delete_b,BorderLayout.CENTER);
        p2.add(add_b,BorderLayout.CENTER);

        adminEvent=new AdminEvent();
        adminEvent.setAdminChangeMemJP(this);
        adminEvent.setType(type);
        adminEvent.memdis_response();


    }

    public AdminEvent getAdminEvent() {
        return adminEvent;
    }

    public void setAdminEvent(AdminEvent adminEvent) {
        this.adminEvent = adminEvent;
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

    public JButton getModify() {
        return modify;
    }

    public void setModify(JButton modify) {
        this.modify = modify;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JButton getDelete_b() {
        return delete_b;
    }

    public void setDelete_b(JButton delete_b) {
        this.delete_b = delete_b;
    }

    public JButton getAdd_b() {
        return add_b;
    }

    public void setAdd_b(JButton add_b) {
        this.add_b = add_b;
    }
}
