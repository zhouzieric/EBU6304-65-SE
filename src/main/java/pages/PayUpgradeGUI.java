package pages;

import bean.Customer;
import logic.ChangeInfo;
import logic.DiscountCalculator;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

public class PayUpgradeGUI implements ActionListener {
    Customer cus;
    JFrame frame;
    JButton bNorm;
    JButton bGold;
    JLabel lMemUp;
    ChangeInfo c;
    String accNo;


    public void go(ChangeInfo c, JLabel lMem, Customer cus){
        lMemUp = lMem;
        this.c = c;
        this.accNo=accNo;
        this.cus = cus;
        DiscountCalculator dc = new DiscountCalculator();

        //top rule illustration
        JPanel pRule = new JPanel();
        pRule.setLayout(new BoxLayout(pRule, BoxLayout.PAGE_AXIS));
        JLabel lRule = new JLabel("3-Level Membership Rights   ");
        lRule.setAlignmentX((float) 0.5);
        JLabel lsp = new JLabel("                 ");
        pRule.add(lRule);
        pRule.add(lsp);

        JPanel pRule1 = new JPanel();
        pRule1.setLayout(new GridLayout(3,2));
        pRule.add(pRule1);

        //Gold
        JPanel pGoldTitle = new JPanel();
        pRule1.add(pGoldTitle);
        JLabel lGoldTitle = new JLabel("Golden Membership Rights     ");
        pGoldTitle.add(lGoldTitle);

        JPanel pGoldRight = new JPanel();
        pGoldRight.setLayout(new BoxLayout(pGoldRight,BoxLayout.Y_AXIS));
        pRule1.add(pGoldRight);
        JLabel lG1 = new JLabel("1.  Appoint personal trainer.          ");
        JLabel lG2 = new JLabel("2.  Unlock all the membership needed videos.         ");
        dc.readMemPrice(Customer.MEMBERSHIP_GOLD);
        JLabel lG3 = new JLabel("3.  All charged videos shared a "+ dc.getMem_discount() +" discount.            ");
        JLabel lG4 = new JLabel("4.  A suit of professional sports equipment for free.          ");
        JLabel lG5 = new JLabel("   (sponge, yoga mat, foam roller, sweatband,           ");
        JLabel lG6 = new JLabel("lightweight barbell, fitness ball).           ");
        pGoldRight.add(lG1);
        pGoldRight.add(lG2);
        pGoldRight.add(lG3);
        pGoldRight.add(lG4);
        pGoldRight.add(lG5);
        pGoldRight.add(lG6);

        //Norm
        JPanel pNormTitle = new JPanel();
        pRule1.add(pNormTitle);
        JLabel lNormTitle = new JLabel("Normal Membership Rights     ");
        pNormTitle.add(lNormTitle);

        JPanel pNormRight = new JPanel();
        pNormRight.setLayout(new BoxLayout(pNormRight,BoxLayout.Y_AXIS));
        pRule1.add(pNormRight);
        JLabel lNorm1 = new JLabel("1.  Appoint personal trainer.           ");
        dc.readMemPrice(Customer.MEMBERSHIP_NORM);
        JLabel lNorm2 = new JLabel("2.	 All living courses shared a "+dc.getMem_discount()+" discount.        ");
        JLabel lNorm3 = new JLabel("3.  A suit of basic sports equipment for free.       ");
        JLabel lNorm4 = new JLabel("(sponge, yoga mat, foam roller).        ");
        pNormRight.add(lNorm1);
        pNormRight.add(lNorm2);
        pNormRight.add(lNorm3);
        pNormRight.add(lNorm4);


        //Non
        JPanel pNonTitle = new JPanel();
        pRule1.add(pNonTitle);
        JLabel lNonTitle = new JLabel("Junior Membership Rights    ");
        pNonTitle.add(lNonTitle);

        JPanel pNon = new JPanel();
        pNon.setLayout(new BoxLayout(pNon,BoxLayout.Y_AXIS));
        pRule1.add(pNon);
        JLabel lNon = new JLabel("1.  View all free sports video.     ");
        pNon.add(lNon);




        //Border
        JLabel lLiner = new JLabel("  --------------------------------------------------------------------------------------------------------------------------------------------------  ");
        JLabel lspace = new JLabel("    ");
        lLiner.setAlignmentX((float) 0.5);
        pRule.add(lLiner);
        pRule.add(lspace);

        //choose upgrade level
        JPanel pOption = new JPanel();
        pOption.setLayout(new BoxLayout(pOption, BoxLayout.Y_AXIS));

        //option top
        JPanel pTop = new JPanel();
        pOption.add(pTop);
        JLabel lNow = new JLabel("You Are Now Our "+cus.getMembership().toUpperCase()+" Member   ");
        pTop.add(lNow);

        //option button
        JPanel pOUp = new JPanel();
        pOUp.setLayout(new BoxLayout(pOUp,BoxLayout.Y_AXIS));
        pOption.add(pOUp);

        String mem = cus.getMembership();
        if(mem.equals(Customer.MEMBERSHIP_GOLD)){
            JPanel pGold = new JPanel();
            JLabel lGold = new JLabel("You are already our top level Member   ");
            JLabel lspa = new JLabel("                                               ");
            JLabel lNoneed = new JLabel("No Need For A Upgrade!    ");
            pGold.add(lGold);
            pGold.add(lspa);
            pGold.add(lNoneed);
            pOUp.add(pGold);
        }else{
            JPanel pGold = new JPanel();
            JLabel lGold = new JLabel("Golden Level Membership  ");
            JLabel lPrice = new JLabel("   $200   ");
            bGold = new JButton("UPGRADE");
            bGold.addActionListener(this);
            pGold.add(lGold);
            pGold.add(lPrice);
            pGold.add(bGold);
            pOUp.add(pGold);
            if(mem.equals(Customer.MEMBERSHIP_JUNIOR)){
                JPanel pNorm = new JPanel();
                JLabel lNorm = new JLabel("Normal Level Membership   ");
                JLabel lPriceN = new JLabel("   $100   ");
                bNorm = new JButton("UPGRADE");
                bNorm.addActionListener(this);
                pNorm.add(lNorm);
                pNorm.add(lPriceN);
                pNorm.add(bNorm);
                pOUp.add(pNorm);
            }
        }


        //After Payment
        //lMem.setText("Membership Rank: " + cus.getMembership());


        frame = new JFrame("Membership Upgrade");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Close when Payment is comleted
        frame.setBounds(400,150,800,650);
        frame.setVisible(true);

        frame.getContentPane().add(BorderLayout.NORTH,pRule);
       frame.getContentPane().add(BorderLayout.CENTER,pOption);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int input;
        if(e.getSource() == bGold){
            System.out.println("1");
            input=JOptionPane.showConfirmDialog(null,"Are you sure you want to pay $200 to upgrade?    ","Payment",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            //yes=0,no=1
            if(input==0){
                JOptionPane.showMessageDialog(null,"Pay Successfully!   ","Payment",JOptionPane.DEFAULT_OPTION);
                c.changeCusInfo(0,Customer.MEMBERSHIP_GOLD);
                cus.setMembership(Customer.MEMBERSHIP_GOLD);
                lMemUp.setText("Membership Rank: "+Customer.MEMBERSHIP_GOLD);
                frame.dispose();
            }
        }else if(e.getSource() == bNorm){
            System.out.println("2");
            input=JOptionPane.showConfirmDialog(null,"Are you sure you want to pay $100 to upgrade?   ","Payment",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(input==0){
                JOptionPane.showMessageDialog(null,"Pay Successfully!    ","Payment",JOptionPane.DEFAULT_OPTION);
                c.changeCusInfo(0,Customer.MEMBERSHIP_NORM);
                cus.setMembership(Customer.MEMBERSHIP_NORM);
                lMemUp.setText("Membership Rank: "+Customer.MEMBERSHIP_NORM);
                frame.dispose();

            }
        }
    }
}
