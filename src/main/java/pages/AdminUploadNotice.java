package pages;

import logic.AdminEvent;

import javax.swing.*;
import java.awt.*;
/**
 *Class that displays administrator uploads notice
 * @author Yixin Li
 * @version 5.6.3
 */
public class AdminUploadNotice extends JPanel {
    private JFrame belongsTo;
    private JPanel container;
    private CardLayout manager;

    private JPanel p1;
    private JPanel p2;
    private JTextArea ta;
    private JButton b;
    private JButton b1;

    private AdminEvent adminEvent;
    /**
     * This a constructor without parameters
     */
    public AdminUploadNotice(){
        ta=new JTextArea(13,30);
        b=new JButton("Upload notice");
        b1=new JButton("Clear previous notice");
        p1=new JPanel();
        p2=new JPanel();
        this.setLayout(new GridLayout(2,1));
        this.add(p1);
        this.add(p2);
        ta.setBackground(Color.white);
        p1.add(ta);
        p2.add(b,BorderLayout.CENTER);
        p2.add(b1,BorderLayout.CENTER);

        adminEvent=new AdminEvent();
        adminEvent.setAdminUploadNotice(this);
        adminEvent.upload_response();


    }

    public JButton getB1() {
        return b1;
    }

    public void setB1(JButton b1) {
        this.b1 = b1;
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

    public JTextArea getTa() {
        return ta;
    }

    public void setTa(JTextArea ta) {
        this.ta = ta;
    }

    public JButton getB() {
        return b;
    }

    public void setB(JButton b) {
        this.b = b;
    }
}
