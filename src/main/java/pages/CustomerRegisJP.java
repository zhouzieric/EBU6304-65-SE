package pages;

import bean.Customer;
import logic.ReadFlexibleInfo;

import javax.swing.*;
import java.util.ArrayList;
/**
 * Class that displays the customer registers
 * @author Yixin Li
 * @version 5.6.3
 */

public class CustomerRegisJP extends RegisterJpanel {

    private JLabel label=new JLabel("Membership: ");


    private JComboBox member=new JComboBox();

    private String acc_getC="";
    private String membership="";
    /**
     * This a constructor without parameters
     */
    public CustomerRegisJP(){
        super();
        getP212().add(getP2121());
        getP212().add(getP2122());
        getP211().add(getL1());
        getP2121().add(getT1());
        getP2122().add(getJl1());
        getP71().add(label);
        getP72().add(member);

        ArrayList<String> memRank=Customer.getMemRank();
        for(int i=0;i<memRank.size();i++){
            member.addItem(memRank.get(i));
        }


    }

    public JComboBox getMember() {
        return member;
    }

    public void setMember(JComboBox member) {
        this.member = member;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
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
