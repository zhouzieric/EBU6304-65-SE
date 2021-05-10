package pages;

import pages.Chooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//用户和教练注册页面的类(CustomerRegisJP和TrainerRegisJP继承它)
public class RegisterJpanel extends JPanel {

    private String anum="";
    private String pass_get="";
    private String pass_get1="";
    private String fname_get="";
    private String lname_get="";
    private String birth_get="";
    private String phnum_get="";
    private String email_get="";
    private String info="";
    private String info2="";
    private String gender="";

    private Boolean pass_confirm;

    private JPanel p1=new JPanel(new GridLayout(3,1));
    private JPanel p111=new JPanel(new GridLayout(1,2));
    private JPanel p1111=new JPanel();
    private JPanel p1112=new JPanel();
    private JPanel p112=new JPanel();
    private JPanel p113=new JPanel();
    private JPanel p2=new JPanel(new GridLayout(10,2));
    private JPanel p211=new JPanel();
    private JPanel p212=new JPanel(new GridLayout(2,1));
    private JPanel p2121=new JPanel();
    private JPanel p2122=new JPanel();
    private JPanel p221=new JPanel();
    private JPanel p222=new JPanel(new GridLayout(2,1));
    private JPanel p2221=new JPanel();
    private JPanel p2222=new JPanel();
    private JPanel p231=new JPanel();
    private JPanel p232=new JPanel(new GridLayout(2,1));
    private JPanel p2321=new JPanel();
    private JPanel p2322=new JPanel();
    private JPanel p241=new JPanel();
    private JPanel p242=new JPanel(new GridLayout(2,1));
    private JPanel p2421=new JPanel();
    private JPanel p2422=new JPanel();
    private JPanel p251=new JPanel();
    private JPanel p252=new JPanel(new GridLayout(2,1));
    private JPanel p2521=new JPanel();
    private JPanel p2522=new JPanel();
    private JPanel p261=new JPanel();
    private JPanel p262=new JPanel();
    private JPanel p271=new JPanel();
    private JPanel p272=new JPanel(new GridLayout(2,1));
    private JPanel p2721=new JPanel();
    private JPanel p2722=new JPanel();
    private JPanel p281=new JPanel();
    private JPanel p282=new JPanel(new GridLayout(2,1));
    private JPanel p2821=new JPanel();
    private JPanel p2822=new JPanel();
    private JPanel p291=new JPanel();
    private JPanel p292=new JPanel(new GridLayout(2,1));
    private JPanel p2921=new JPanel();
    private JPanel p2922=new JPanel();
    private  JPanel p71=new JPanel();
    private  JPanel p72=new JPanel();
    private JPanel p3=new JPanel(new GridLayout(1,2));
    private JPanel p31=new JPanel();
    private JPanel p32=new JPanel();

    private JLabel succ=new JLabel("");
    private JLabel l1=new JLabel("Nickname: ");
    private JLabel l2=new JLabel("Password: ");
    private JLabel l3=new JLabel("Confirm your password again: ");
    private JLabel l4=new JLabel("First name:");
    private JLabel l5=new JLabel("Last name:");
    private JLabel l6=new JLabel("Gender: ");
    private JLabel l7=new JLabel("Date of birth: ");
    private JLabel l8=new JLabel("Phone number: ");
    private JLabel l9=new JLabel("E-mail address: ");
    private JLabel jl=new JLabel("----------------------------------------------------------------------------------------------------------PERSONAL INFORMATION----------------------------------------------------------------------------------------");
    private JLabel jl1=new JLabel("**Your username can't contain ';'.**");
    private JLabel jl2=new JLabel("**Your password must consist of numbers and letters.**");
    private JLabel jl3=new JLabel("**Please make sure that the passwords you entered are the same.**");
    private JLabel jl4=new JLabel("");
    private JLabel jl5=new JLabel("");
    private JLabel jl6=new JLabel("");
    private JLabel jl7=new JLabel("");
    private JLabel jl8=new JLabel("");

    private JTextField t1=new JTextField("");
    private JTextField t4=new JTextField("");
    private JTextField t5=new JTextField("");
    private JTextField t6=new JTextField("");
    private JTextField t7=new JTextField("");
    private JTextField t8=new JTextField("");

    private JPasswordField pass1=new JPasswordField("");
    private JPasswordField pass2=new JPasswordField("");

    private JRadioButton radio4=new JRadioButton("Male",true);
    private JRadioButton radio5=new JRadioButton("Female",false);

    private JButton submit=new JButton("Submit");
    private JButton go_Back=new JButton("Go Back to log in");


    public RegisterJpanel(){

        this.setLayout(new BorderLayout());

        t1.setColumns(15);
        pass1.setColumns(15);
        pass2.setColumns(15);
        t4.setColumns(15);
        t5.setColumns(15);
        t6.setColumns(15);
        t7.setColumns(15);
        t8.setColumns(15);

        jl1.setForeground(Color.gray);
        jl2.setForeground(Color.gray);
        jl3.setForeground(Color.gray);
        jl4.setForeground(Color.gray);
        jl5.setForeground(Color.gray);

        jl1.setFont(new Font (Font.DIALOG, Font.PLAIN, 11));
        jl2.setFont(new Font (Font.DIALOG, Font.PLAIN, 11));
        jl3.setFont(new Font (Font.DIALOG, Font.PLAIN, 11));
        jl4.setFont(new Font (Font.DIALOG, Font.PLAIN, 11));
        jl5.setFont(new Font (Font.DIALOG, Font.PLAIN, 11));
        jl6.setFont(new Font (Font.DIALOG, Font.PLAIN, 11));
        jl7.setFont(new Font (Font.DIALOG, Font.PLAIN, 11));
        jl8.setFont(new Font (Font.DIALOG, Font.PLAIN, 11));

        Chooser ser = Chooser.getInstance();
        ser.register(t6);

        this.add(p1,BorderLayout.NORTH);
        this.add(p2,BorderLayout.CENTER);
        this.add(p3,BorderLayout.SOUTH);

        p1.add(p111);
        p1.add(p112);
        p1.add(p113);
        p111.add(p1111);
        p111.add(p1112);
        p112.add(jl);
        p113.add(succ);
        p2.add(p211);
        p2.add(p212);
        p2.add(p221);
        p2.add(p222);
        p222.add(p2221);
        p222.add(p2222);
        p221.add(l2);
        p2221.add(pass1);
        p2222.add(jl2);
        p2.add(p231);
        p2.add(p232);
        p232.add(p2321);
        p232.add(p2322);
        p231.add(l3);
        p2321.add(pass2);
        p2322.add(jl3);
        p2.add(p241);
        p2.add(p242);
        p242.add(p2421);
        p242.add(p2422);
        p241.add(l4);
        p2421.add(t4);
        p2422.add(jl6);
        p2.add(p251);
        p2.add(p252);
        p252.add(p2521);
        p252.add(p2522);
        p251.add(l5);
        p2521.add(t5);
        p2522.add(jl7);
        p2.add(p261);
        p2.add(p262);
        p261.add(l6);
        p262.add(radio4);
        p262.add(radio5);
        p2.add(p271);
        p2.add(p272);
        p272.add(p2721);
        p272.add(p2722);
        p271.add(l7);
        p2721.add(t6);
        p2722.add(jl8);
        p2.add(p281);
        p2.add(p282);
        p281.add(l8);
        p282.add(p2821);
        p282.add(p2822);
        p2821.add(t7);
        p2822.add(jl4);
        p2.add(p291);
        p2.add(p292);
        p291.add(l9);
        p292.add(p2921);
        p292.add(p2922);
        p2921.add(t8);
        p2922.add(jl5);
        p2.add(p71);
        p2.add(p72);
        p3.add(p31);
        p3.add(p32);
        p31.add(go_Back);
        p32.add(submit);

    }

    public String getAnum() {
        return anum;
    }

    public void setAnum(String anum) {
        this.anum = anum;
    }

    public String getPass_get() {
        return pass_get;
    }

    public void setPass_get(String pass_get) {
        this.pass_get = pass_get;
    }

    public String getPass_get1() {
        return pass_get1;
    }

    public void setPass_get1(String pass_get1) {
        this.pass_get1 = pass_get1;
    }

    public String getFname_get() {
        return fname_get;
    }

    public void setFname_get(String fname_get) {
        this.fname_get = fname_get;
    }

    public String getLname_get() {
        return lname_get;
    }

    public void setLname_get(String lname_get) {
        this.lname_get = lname_get;
    }

    public String getBirth_get() {
        return birth_get;
    }

    public void setBirth_get(String birth_get) {
        this.birth_get = birth_get;
    }

    public String getPhnum_get() {
        return phnum_get;
    }

    public void setPhnum_get(String phnum_get) {
        this.phnum_get = phnum_get;
    }

    public String getEmail_get() {
        return email_get;
    }

    public void setEmail_get(String email_get) {
        this.email_get = email_get;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getPass_confirm() {
        return pass_confirm;
    }

    public void setPass_confirm(Boolean pass_confirm) {
        this.pass_confirm = pass_confirm;

    }
    public JPanel getP71() {
        return p71;
    }

    public void setP71(JPanel p71) {
        this.p71 = p71;
    }

    public JPanel getP72() {
        return p72;
    }

    public void setP72(JPanel p72) {
        this.p72 = p72;
    }

    public JLabel getSucc() {
        return succ;
    }

    public void setSucc(JLabel succ) {
        this.succ = succ;
    }

    public JPanel getP1() {
        return p1;
    }

    public void setP1(JPanel p1) {
        this.p1 = p1;
    }

    public JLabel getJl() {
        return jl;
    }

    public void setJl(JLabel jl) {
        this.jl = jl;
    }

    public JLabel getJl1() {
        return jl1;
    }

    public void setJl1(JLabel jl1) {
        this.jl1 = jl1;
    }

    public JLabel getJl2() {
        return jl2;
    }

    public void setJl2(JLabel jl2) {
        this.jl2 = jl2;
    }

    public JLabel getJl3() {
        return jl3;
    }

    public void setJl3(JLabel jl3) {
        this.jl3 = jl3;
    }

    public JLabel getJl4() {
        return jl4;
    }

    public void setJl4(JLabel jl4) {
        this.jl4 = jl4;
    }

    public JLabel getJl5() {
        return jl5;
    }

    public void setJl5(JLabel jl5) {
        this.jl5 = jl5;
    }

    public JLabel getJl6() {
        return jl6;
    }

    public void setJl6(JLabel jl6) {
        this.jl6 = jl6;
    }

    public JLabel getJl7() {
        return jl7;
    }

    public void setJl7(JLabel jl7) {
        this.jl7 = jl7;
    }

    public JLabel getJl8() {
        return jl8;
    }

    public void setJl8(JLabel jl8) {
        this.jl8 = jl8;
    }

    public JTextField getT1() {
        return t1;
    }

    public void setT1(JTextField t1) {
        this.t1 = t1;
    }

    public JTextField getT4() {
        return t4;
    }

    public void setT4(JTextField t4) {
        this.t4 = t4;
    }

    public JTextField getT5() {
        return t5;
    }

    public void setT5(JTextField t5) {
        this.t5 = t5;
    }

    public JTextField getT6() {
        return t6;
    }

    public void setT6(JTextField t6) {
        this.t6 = t6;
    }

    public JTextField getT7() {
        return t7;
    }

    public void setT7(JTextField t7) {
        this.t7 = t7;
    }

    public JTextField getT8() {
        return t8;
    }

    public void setT8(JTextField t8) {
        this.t8 = t8;
    }

    public JPasswordField getPass1() {
        return pass1;
    }

    public void setPass1(JPasswordField pass1) {
        this.pass1 = pass1;
    }

    public JPasswordField getPass2() {
        return pass2;
    }

    public void setPass2(JPasswordField pass2) {
        this.pass2 = pass2;
    }

    public JRadioButton getRadio4() {
        return radio4;
    }

    public void setRadio4(JRadioButton radio4) {
        this.radio4 = radio4;
    }

    public JRadioButton getRadio5() {
        return radio5;
    }

    public void setRadio5(JRadioButton radio5) {
        this.radio5 = radio5;
    }

    public JButton getSubmit() {
        return submit;
    }

    public void setSubmit(JButton submit) {
        this.submit = submit;
    }
    public JButton getGo_Back() {
        return go_Back;
    }

    public void setGo_Back(JButton go_Back) {
        this.go_Back = go_Back;
    }

    public JPanel getP2() {
        return p2;
    }

    public void setP2(JPanel p2) {
        this.p2 = p2;
    }

    public JPanel getP211() {
        return p211;
    }

    public void setP211(JPanel p211) {
        this.p211 = p211;
    }

    public JPanel getP212() {
        return p212;
    }

    public void setP212(JPanel p212) {
        this.p212 = p212;
    }

    public JPanel getP2121() {
        return p2121;
    }

    public void setP2121(JPanel p2121) {
        this.p2121 = p2121;
    }

    public JPanel getP2122() {
        return p2122;
    }

    public void setP2122(JPanel p2122) {
        this.p2122 = p2122;
    }

    public JLabel getL1() {
        return l1;
    }

    public void setL1(JLabel l1) {
        this.l1 = l1;
    }

    public JPanel getP3() {
        return p3;
    }

    public void setP3(JPanel p3) {
        this.p3 = p3;
    }
}
