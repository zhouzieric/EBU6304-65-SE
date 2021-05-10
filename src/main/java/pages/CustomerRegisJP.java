package pages;

import logic.Login;
import logic.Register;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//用户注册页面
public class CustomerRegisJP extends RegisterJpanel {

    private JLabel label=new JLabel("Membership: ");

    private JRadioButton radio6=new JRadioButton("gold");
    private JRadioButton radio7=new JRadioButton("nor",true);
    private JRadioButton radio8=new JRadioButton("junior");

    private String acc_getC="";
    private String membership="";

    public CustomerRegisJP(){
        super();
        getP212().add(getP2121());
        getP212().add(getP2122());
        getP211().add(getL1());
        getP2121().add(getT1());
        getP2122().add(getJl1());
        getP71().add(label);
        getP72().add(radio6);
        getP72().add(radio7);
        getP72().add(radio8);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JRadioButton getRadio6() {
        return radio6;
    }

    public void setRadio6(JRadioButton radio6) {
        this.radio6 = radio6;
    }

    public JRadioButton getRadio7() {
        return radio7;
    }

    public void setRadio7(JRadioButton radio7) {
        this.radio7 = radio7;
    }

    public JRadioButton getRadio8() {
        return radio8;
    }

    public void setRadio8(JRadioButton radio8) {
        this.radio8 = radio8;
    }

    public String getAcc_getC() {
        return acc_getC;
    }

    public void setAcc_getC(String acc_getC) {
        this.acc_getC = acc_getC;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }
}
