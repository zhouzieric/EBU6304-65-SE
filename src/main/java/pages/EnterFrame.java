package pages;

import logic.LoginRegisEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *Class that is card panel for login and registration
 * @author Yixin Li
 * @version 5.6.3
 */

public class EnterFrame extends JPanel {


    private CardLayout cardLayout=new CardLayout();
    /**
     * This a constructor to pass parameters
     * @param p1 JPanel to add this cardLayout Jpanel
     * @param p2 JPanel to add this cardLayout Jpanel
     * @param p8 JPanel to add this cardLayout Jpanel
     * @param p3 JPanel to add this cardLayout Jpanel
     * @param p4 JPanel to add this cardLayout Jpanel
     * @param p5 JPanel to add this cardLayout Jpanel
     */
    public EnterFrame(JPanel p1, JPanel p2, JPanel p8, JPanel p3, JPanel p4, JPanel p5){

        this.setLayout(cardLayout);
        this.add(p1,"enter");
        this.add(p2,"C_login");
        this.add(p8,"T_login");
        this.add(p3,"custom_register");
        this.add(p4,"train_register");
        this.add(p5,"A_login");

    }
    public CardLayout getCardLayout() {
        return cardLayout;
    }
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

}
