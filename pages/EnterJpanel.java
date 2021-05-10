package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//enter界面
public class EnterJpanel extends JPanel{

    private JLabel l1=new JLabel("");
    private JLabel l2=new JLabel("Your Identity:");
    private JLabel l3=new JLabel("Life lies in movement.        ——Voltaire");

    private JButton b1=new JButton("Customer");
    private JButton b2=new JButton("Administrator");
    private JButton b3=new JButton("Trainer");

    private JPanel p11=new JPanel(new FlowLayout());
    private JPanel p2=new JPanel(new BorderLayout());
    private JPanel p21=new JPanel();
    private JPanel p22=new JPanel();
    private JPanel p3=new JPanel();

    public EnterJpanel(){
        this.setLayout(new BorderLayout());
        this.add(p11,BorderLayout.NORTH);
        p11.setPreferredSize(new Dimension(0,200));
        l2.setFont(new Font (Font.DIALOG, Font.BOLD, 18));

        this.add(p2,BorderLayout.CENTER);
        l1.setFont(new Font ("Impact", Font.ITALIC, 40));
        p11.add(l1);
        l3.setFont(new Font (Font.DIALOG, Font.PLAIN, 15));
        p2.add(p21,BorderLayout.NORTH);
        p21.setPreferredSize(new Dimension(0,60));
        p21.add(l2);
        p2.add(p22,BorderLayout.CENTER);

        p22.add(b1);
        p22.add(b2);
        p22.add(b3);
        this.add(p3,BorderLayout.SOUTH);
        p3.setPreferredSize(new Dimension(0,100));
        p3.add(l3);

    }

    public JLabel getL1() {
        return l1;
    }

    public void setL1(JLabel l1) {
        this.l1 = l1;
    }


    public JPanel getP2() {
        return p2;
    }

    public void setP2(JPanel p2) {
        this.p2 = p2;
    }
    public JButton getB1() {
        return b1;
    }

    public void setB1(JButton b1) {
        this.b1 = b1;
    }

    public JButton getB2() {
        return b2;
    }

    public void setB2(JButton b2) {
        this.b2 = b2;
    }

    public JButton getB3() {
        return b3;
    }

    public void setB3(JButton b3) {
        this.b3 = b3;
    }


}