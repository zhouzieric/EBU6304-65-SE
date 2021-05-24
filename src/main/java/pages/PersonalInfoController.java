package pages;

import bean.Customer;
import bean.Member;
import bean.Trainer;
import logic.ChangeInfo;
import logic.Login;
import logic.PersonalInfoListener;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

public class PersonalInfoController extends JPanel {
    private PersonalInfoListener pil;
    private Login log;
    private ChangeInfo readInfo;
    private Member mem;
    private JLabel lMem;
    private JLabel lNick;
    private JLabel lPW;
    private JLabel lPN;
    private JLabel lE;
    private JLabel lRank;
    private JLabel lProfess;
    private JLabel lAccWarn;
    private JLabel lPNWarn;
    private JLabel lEWarn;
    private JLabel lPWWarn;
    private JLabel lNickWarn;

    private JButton bPWShow;
    private JButton bNick;
    private JButton bPWHide;
    private JButton buttonMem;
    private JButton bAcc;
    private JButton bPW;
    private JButton bPN;
    private JButton bE;
    private String acc_login;
    private JLabel lspace;
    private int flag = 0;


    public PersonalInfoController(){
        pil = new PersonalInfoListener();
        String filename="src/main/java/data/acc_login.txt";
        //Acc: Read Account Info Logic
        try{
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String oneline=bufferedReader.readLine();
            acc_login = oneline.split("\r\n")[0];
            System.out.println(acc_login);

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        readInfo = new ChangeInfo(acc_login);
        if((acc_login.charAt(0)+"").equals("C")){
            mem = new Customer();
            mem = readInfo.readCusInfo();
            flag =1;
        }else if((acc_login.charAt(0)+"").equals("T")){
            mem = new Trainer();
            mem = readInfo.readTraInfo();
            flag = 2;
        }else if((acc_login.charAt(0)+"").equals("A")){

        }

        //pil.setMem(mem);
        //pil.setReadInfo(readInfo);



        JPanel panelAcc = new JPanel();
        this.add(panelAcc);
        panelAcc.setLayout(new GridLayout(1,2));
        //panelAcc.setPreferredSize(new Dimension(0,0));





        JPanel panALeft = new JPanel();
        panALeft.setLayout(new GridLayout(2,1));
        //panALeft.setPreferredSize(new Dimension(0,0));

        JPanel panARight = new JPanel();
        panARight.setLayout(new BoxLayout(panARight,BoxLayout.PAGE_AXIS));
        //panARight.setPreferredSize(new Dimension(0,0));

        panelAcc.add(panALeft);
        panelAcc.add(panARight);


        //Acc: Left side Info
        JPanel pPro = new JPanel();
        panALeft.add(pPro);

        pPro.setLayout(new BorderLayout());
        ///pPro.setPreferredSize(new Dimension(0,100));
        //pPro.setBorder(BorderFactory.createEtchedBorder());
        JPanel pw = new JPanel();
        //pw.setPreferredSize(new Dimension(50,0));
        JPanel pe = new JPanel();
        //pe.setPreferredSize(new Dimension(50,0));
        //JPanel ps = new JPanel();
        //ps.setPreferredSize(new Dimension(0,100));
        //JPanel pn = new JPanel();
        //pn.setPreferredSize(new Dimension(0,100));
        JPanel pc = new JPanel();

        JLabel lPro = new JLabel();
        Icon icon = new ImageIcon("src/main/image/"+mem.getAccountNo()+".jpg");
        lPro.setIcon(icon);
        lPro.setBorder(BorderFactory.createLineBorder(Color.gray));
        pc.add(lPro);

        pPro.add(pc,BorderLayout.CENTER);
        pPro.add(pw,BorderLayout.WEST);
        pPro.add(pe,BorderLayout.EAST);
        //pPro.add(ps,BorderLayout.SOUTH);
        //pPro.add(pn,BorderLayout.NORTH);


        JPanel pBelow = new JPanel();
        panALeft.add(pBelow);
        pBelow.setLayout(new BoxLayout(pBelow,BoxLayout.Y_AXIS));
        JLabel l0 = new JLabel(" ");
        JLabel l1 = new JLabel("  ");
        JLabel l2 = new JLabel("  ");
        JLabel l3 = new JLabel("  ");
        pBelow.add(l0);
        pBelow.add(l1);
        pBelow.add(l2);
        pBelow.add(l3);

        if(flag==1) {
            Customer cus = (Customer) mem;
            lMem = new JLabel("Membership Rank: " + cus.getMembership()+"    ");
            lMem.setAlignmentX((float) 0.5);
            //System.out.println("Here");
            buttonMem = new JButton("Membership Upgrade");
            buttonMem.setAlignmentX((float) 0.5);
            buttonMem.addActionListener(pil);
            lspace = new JLabel("     ");
            pBelow.add(lMem);
            pBelow.add(lspace);
            pBelow.add(buttonMem);
        }else if(flag==2){
            Trainer tra = (Trainer) mem;
            lProfess = new JLabel("Professional Category: "+tra.getPro()+"    ");
            lProfess.setAlignmentX((float) 0.5);
            lRank = new JLabel("Rank Grade: "+tra.getRank());
            lRank.setAlignmentX((float) 0.5);
            //buttonPro= new JButton("Professionals Change");  //button to change profess
            //buttonPro.setAlignmentX((float) 0.5);
            //buttonPro.addActionListener(this);
            lspace = new JLabel("     ");
            pBelow.add(lProfess);
            pBelow.add(lspace);
            pBelow.add(lRank);
            //pBelow.add(buttonMem);
        }







        //Acc: Right side Info
        //AccNo
        JPanel PAcc = new JPanel();
        panARight.add(PAcc);
        JLabel lAcc = new JLabel("Account No.:  "+mem.getAccountNo()+"   ");
        //bAcc = new JButton("Change");
        //bAcc.addActionListener(this);
        //lAccWarn = new JLabel();
        PAcc.add(lAcc);
        //PAcc.add(bAcc);
        //PAcc.add(lAccWarn);

        if(flag==1) {
            //NickName
            JPanel PNick = new JPanel();
            panARight.add(PNick);
            PNick.setLayout(new GridLayout(2,1));
            JPanel panelNick1 = new JPanel();
            JPanel panelNick2 = new JPanel();
            PNick.add(panelNick1);
            PNick.add(panelNick2);

            Customer customer1 = (Customer) mem;
            lNick = new JLabel("Nick Name: " + customer1.getNickname()+"    ");
            bNick = new JButton("Change");
            bNick.addActionListener(pil);
            panelNick1.add(lNick);
            panelNick1.add(bNick);

            lNickWarn = new JLabel();
            panelNick2.add(lNickWarn);
        }

        //Password
        JPanel PPassword= new JPanel();
        panARight.add(PPassword);
        PPassword.setLayout(new GridLayout(2,1));
        JPanel ppass1 = new JPanel();
        JPanel ppass2 = new JPanel();
        PPassword.add(ppass1);
        PPassword.add(ppass2);

        //password replaced by asterisk
        lPW = new JLabel("Password:  "+hidePW(mem.getPassword())+"    ");
        bPWShow = new JButton("Show");
        bPWShow.addActionListener(pil);
        bPWHide = new JButton("Hide");
        bPWHide.addActionListener(pil);
        bPW = new JButton("Change");
        bPW.addActionListener(pil);
        ppass1.add(lPW);
        ppass1.add(bPWShow);
        ppass1.add(bPWHide);
        ppass1.add(bPW);

        lPWWarn = new JLabel();
        ppass2.add(lPWWarn);


        //User Name
        JPanel PName= new JPanel();
        panARight.add(PName);
        //PName.setPreferredSize(new Dimension(0,0));
        JLabel lName = new JLabel("User Name:  "+mem.getFname()+" "+mem.getLname()+"   ");
        PName.add(lName);


        //Gender && date-of-birth
        JPanel PGD= new JPanel();
        panARight.add(PGD);
        //PGD.setPreferredSize(new Dimension(0,0));
        JLabel lGen = new JLabel("Gender:  "+mem.getGender()+"   ");
        JLabel lBirth = new JLabel("   Birthday:   "+mem.getDate_of_birth()+"   ");
        PGD.add(lGen);
        PGD.add(lBirth);

        //Phone Number
        JPanel PPhoneNum= new JPanel();
        panARight.add(PPhoneNum);
        PPhoneNum.setLayout(new GridLayout(2,1));
        JPanel pphone1 = new JPanel();
        JPanel pphone2 = new JPanel();
        PPhoneNum.add(pphone1);
        PPhoneNum.add(pphone2);

        lPN = new JLabel("Phone Num:  "+mem.getPhone_num()+"    ");
        bPN = new JButton("Change");
        bPN.addActionListener(pil);
        pphone1.add(lPN);
        pphone1.add(bPN);

        lPNWarn = new JLabel();
        pphone2.add(lPNWarn);

        //Email-Address
        JPanel PEmail= new JPanel();
        panARight.add(PEmail);
        PEmail.setLayout(new GridLayout(2,1));
        JPanel pemail1 = new JPanel();
        JPanel pemail2 = new JPanel();
        PEmail.add(pemail1);
        PEmail.add(pemail2);

        lE = new JLabel("Email:  "+mem.getEmail_addr()+"    ");
        bE = new JButton("Change");
        bE.addActionListener(pil);
        pemail1.add(lE);
        pemail1.add(bE);

        lEWarn = new JLabel();
        pemail2.add(lEWarn);


        pil.setPi(this);


    }

//    public static void main(String[] args){
//        JFrame frame = new JFrame("Personal Information");
//        PersonalInfoController pi = new PersonalInfoController();
//        frame.getContentPane().add(pi);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800,600);
//        frame.setVisible(true);
//    }

    public String hidePW(String password){
        String ask = "";
        for(int i =0;i<password.length();i++){
            ask = ask.concat("*");
        }
        return ask;
    }

    public PersonalInfoListener getPil() {
        return pil;
    }

    public Login getLog() {
        return log;
    }

    public ChangeInfo getReadInfo() {
        return readInfo;
    }

    public Member getMem() {
        return mem;
    }

    public JLabel getlMem() {
        return lMem;
    }

    public JLabel getlNick() {
        return lNick;
    }

    public JLabel getlPW() {
        return lPW;
    }

    public JLabel getlPN() {
        return lPN;
    }

    public JLabel getlE() {
        return lE;
    }

    public JLabel getlRank() {
        return lRank;
    }

    public JLabel getlProfess() {
        return lProfess;
    }

    public JLabel getlAccWarn() {
        return lAccWarn;
    }

    public JLabel getlPNWarn() {
        return lPNWarn;
    }

    public JLabel getlEWarn() {
        return lEWarn;
    }

    public JLabel getlPWWarn() {
        return lPWWarn;
    }

    public JLabel getlNickWarn() {
        return lNickWarn;
    }

    public JButton getbPWShow() {
        return bPWShow;
    }

    public JButton getbNick() {
        return bNick;
    }

    public JButton getbPWHide() {
        return bPWHide;
    }

    public JButton getButtonMem() {
        return buttonMem;
    }

    public JButton getbAcc() {
        return bAcc;
    }

    public JButton getbPW() {
        return bPW;
    }

    public JButton getbPN() {
        return bPN;
    }

    public JButton getbE() {
        return bE;
    }

    public String getAcc_login() {
        return acc_login;
    }

    public JLabel getLspace() {
        return lspace;
    }

    public int getFlag() {
        return flag;
    }
}
