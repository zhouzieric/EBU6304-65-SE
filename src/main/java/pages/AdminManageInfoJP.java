package pages;

import logic.AdminEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
 *Class that displays relevant marketing rules
 * @author Yixin Li
 * @version 5.6.3
 */
public class AdminManageInfoJP extends JPanel {
    private JFrame belongsTo;
    private JPanel container;
    private CardLayout manager;

    private JButton modify;
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane jsp;

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
    public AdminManageInfoJP(Object[][] tabledata, Object[] columntitle, String type){

        this.setLayout(new BorderLayout());
        model=new DefaultTableModel(tabledata,columntitle);
        modify=new JButton("Modify");
        table=new JTable(model);
        jsp=new JScrollPane(table);
        this.add(modify, BorderLayout.NORTH);
        this.add(jsp,BorderLayout.CENTER);

        adminEvent=new AdminEvent();
        adminEvent.setAdminManageInfoJp(this);
        adminEvent.setType(type);
        adminEvent.modify_response();

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
}
