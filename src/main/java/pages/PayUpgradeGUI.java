/**
 * This class is a UI to view membership rule and do upgrading
 * @author Gui Jiayi
 * @version 2.4
 */
package pages;

import bean.Customer;
import logic.ChangeInfo;
import logic.DiscountCalculator;
import logic.ReadFlexibleInfo;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PayUpgradeGUI {
    private Customer cus;
    private JFrame frame;
    private JButton bNorm;
    private JButton bGold;
    private JLabel lMemUp;
    private ChangeInfo c;
    private String accNo;
    private ArrayList<String> memRank;


    /**
     * This is a initializer for UI to show
     * @param c
     * let the class be able to change the object ChangeInfo passed
     * @param lMem
     * let the class be able to change the object JLabel passed
     * @param cus
     * let the class be able to change the object Customer passed
     */
    public void go(ChangeInfo c, JLabel lMem, Customer cus){
        lMemUp = lMem;
        this.c = c;
        this.accNo=accNo;
        this.cus = cus;
        DiscountCalculator dc = new DiscountCalculator();
        memRank = Customer.getMemRank();
        int size = memRank.size();

        //top rule illustration
        JPanel pRule = new JPanel();
        pRule.setLayout(new BoxLayout(pRule, BoxLayout.PAGE_AXIS));
        JLabel lRule = new JLabel(size+"-Level Membership Rights   ");
        lRule.setAlignmentX((float) 0.5);
        JLabel lsp = new JLabel("                 ");
        pRule.add(lRule);
        pRule.add(lsp);

        JPanel pRule1 = new JPanel();
        pRule1.setLayout(new GridLayout(size,2));
        pRule.add(pRule1);

        //Gold
        JPanel pGoldTitle = new JPanel();
        pRule1.add(pGoldTitle);
        JLabel lGoldTitle = new JLabel(memRank.get(0)+" Membership Rights     ");
        pGoldTitle.add(lGoldTitle);

        JPanel pGoldRight = new JPanel();
        pGoldRight.setLayout(new BoxLayout(pGoldRight,BoxLayout.Y_AXIS));
        pRule1.add(pGoldRight);
        JLabel lG1 = new JLabel("1.  Appoint personal trainer.          ");
        JLabel lG2 = new JLabel("2.  Unlock all the membership needed videos.         ");

        JLabel lG3 = new JLabel("3.  All charged videos shared a "+ ReadFlexibleInfo.readMemPrice(memRank.get(0)) +" discount.            ");
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
        JLabel lNormTitle = new JLabel(memRank.get(1)+" Membership Rights     ");
        pNormTitle.add(lNormTitle);

        JPanel pNormRight = new JPanel();
        pNormRight.setLayout(new BoxLayout(pNormRight,BoxLayout.Y_AXIS));
        pRule1.add(pNormRight);
        JLabel lNorm1 = new JLabel("1.  Appoint personal trainer.           ");

        JLabel lNorm2 = new JLabel("2.	 All living courses shared a "+ReadFlexibleInfo.readMemPrice(memRank.get(1))+" discount.        ");
        JLabel lNorm3 = new JLabel("3.  A suit of basic sports equipment for free.       ");
        JLabel lNorm4 = new JLabel("(sponge, yoga mat, foam roller).        ");
        pNormRight.add(lNorm1);
        pNormRight.add(lNorm2);
        pNormRight.add(lNorm3);
        pNormRight.add(lNorm4);


        //Left-out membership with similar rules
        for(int i=2;i<size-1;i++){
            JPanel pNonTitle = new JPanel();
            pRule1.add(pNonTitle);
            JLabel lNonTitle = new JLabel(memRank.get(i)+" Membership Rights    ");
            pNonTitle.add(lNonTitle);

            JPanel pNon = new JPanel();
            pNon.setLayout(new BoxLayout(pNon,BoxLayout.Y_AXIS));
            pRule1.add(pNon);
            JLabel lNon1 = new JLabel("1.  Appoint personal trainer.           ");
            JLabel lNon2 = new JLabel("2. All living courses shared a "+ReadFlexibleInfo.readMemPrice(memRank.get(i))+" discount.        ");
            JLabel lNon3 = new JLabel("3.  View all free sports video.     ");
            pNon.add(lNon1);
            pNon.add(lNon2);
            pNon.add(lNon3);
        }

        JPanel pNonTitle = new JPanel();
        pRule1.add(pNonTitle);
        JLabel lNonTitle = new JLabel(memRank.get(size-1)+" Membership Rights    ");
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
        int mem_order =0;
        for(int i=0;i<memRank.size();i++){
            if (mem.equals(memRank.get(i))) mem_order=i;
        }
        if(mem.equals(memRank.get(0))){  //option for highest level membership
            JPanel pGold = new JPanel();
            JLabel lGold = new JLabel("You are already our top level Member   ");
            JLabel lspa = new JLabel("                                               ");
            JLabel lNoneed = new JLabel("No Need For A Upgrade!    ");
            pGold.add(lGold);
            pGold.add(lspa);
            pGold.add(lNoneed);
            pOUp.add(pGold);
        }else{
            // option for left membership with update fee read from file
            for(int i=0;i<mem_order;i++){
                JPanel pGold = new JPanel();
                JLabel lGold = new JLabel(memRank.get(i)+" level membership  ");
                JLabel lPrice = new JLabel("   $"+ReadFlexibleInfo.readMemUpdate(mem,mem_order-i)+"   ");
                bGold = new JButton("UPGRADE");
                pGold.add(lGold);
                pGold.add(lPrice);
                pGold.add(bGold);
                pOUp.add(pGold);

                //register each iteration's button with an inner action event
                //to be able to know what button clicked to update to
                int finalMem_order = mem_order;
                int finalI = i;
                bGold.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int input;
                        System.out.println("1");
                        input=JOptionPane.showConfirmDialog(null,"Are you sure you want to pay $"+ReadFlexibleInfo.readMemUpdate(mem, finalMem_order - finalI)+" to upgrade?    ","Payment",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        //yes=0,no=1
                        if(input==0) {
                            JOptionPane.showMessageDialog(null, "Pay Successfully!   ", "Payment", JOptionPane.DEFAULT_OPTION);
                            c.changeCusInfo(0, memRank.get(finalI));
                            cus.setMembership(memRank.get(finalI));
                            lMemUp.setText("Membership Rank: " + memRank.get(finalI));
                            frame.dispose();
                        }
                    }
                });
            }


            /*if(mem.equals(memRank.get(2))){
                JPanel pNorm = new JPanel();
                JLabel lNorm = new JLabel("Normal Level Membership   ");
                JLabel lPriceN = new JLabel("   $100   ");
                bNorm = new JButton("UPGRADE");
                bNorm.addActionListener(this);
                pNorm.add(lNorm);
                pNorm.add(lPriceN);
                pNorm.add(bNorm);
                pOUp.add(pNorm);
            }*/
        }

        JPanel pAll = new JPanel();
        pAll.setLayout(new BoxLayout(pAll,BoxLayout.Y_AXIS));
        pAll.add(pRule);
        pAll.add(pOption);
        JScrollPane jScrollPane = new JScrollPane(pAll);


        //After Payment
        //lMem.setText("Membership Rank: " + cus.getMembership());


        frame = new JFrame("Membership Upgrade");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Close when Payment is comleted
        frame.setBounds(400,150,800,650);
        frame.setVisible(true);

//        frame.getContentPane().add(BorderLayout.NORTH,pRule);
//       frame.getContentPane().add(BorderLayout.CENTER,pOption);
        frame.getContentPane().add(BorderLayout.CENTER,jScrollPane);
    }



    /*@Override
    public void actionPerformed(ActionEvent e) {
        int input;
        if(e.getSource() == bGold){
            System.out.println("1");
            input=JOptionPane.showConfirmDialog(null,"Are you sure you want to pay $200 to upgrade?    ","Payment",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            //yes=0,no=1
            if(input==0){
                JOptionPane.showMessageDialog(null,"Pay Successfully!   ","Payment",JOptionPane.DEFAULT_OPTION);
                c.changeCusInfo(0,memRank.get(0));
                cus.setMembership(memRank.get(0));
                lMemUp.setText("Membership Rank: "+memRank.get(0));
                frame.dispose();
            }
        }else if(e.getSource() == bNorm){
            System.out.println("2");
            input=JOptionPane.showConfirmDialog(null,"Are you sure you want to pay $100 to upgrade?   ","Payment",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(input==0){
                JOptionPane.showMessageDialog(null,"Pay Successfully!    ","Payment",JOptionPane.DEFAULT_OPTION);
                c.changeCusInfo(0,memRank.get(1));
                cus.setMembership(memRank.get(1));
                lMemUp.setText("Membership Rank: "+memRank.get(1));
                frame.dispose();

            }
        }
    }*/
}
