package pages;

import logic.Login;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *Class that display login page
 * @author Yixin Li
 * @version 5.6.3
 */
public class LoginJpanel extends JPanel{

    private final static String filename= "src/main/java/data/acc_login.txt";
    private String accNo_get;
    private String password_get;

    private JFrame belongsTo;

    private JTextField accNo_text=new JTextField(12);

    private JPasswordField password_text=new JPasswordField(12);

    private JLabel login_result=new JLabel("");
    private JLabel label=new JLabel(" Welcome. Please login. ");
    private JPanel p11=new JPanel();
    private JPanel p12=new JPanel();
    private JPanel p1=new JPanel();
    private JPanel p2=new JPanel();
    private JPanel p3=new JPanel(new GridLayout(2,1));
    private JPanel p31=new JPanel();
    private JPanel p32=new JPanel();
    private JPanel p4=new JPanel();
    private JPanel p5=new JPanel();
    private JPanel p6=new JPanel();
    private JPanel p7=new JPanel();
    private JPanel p8=new JPanel();
    private JPanel p9=new JPanel();
    private JPanel p10=new JPanel();
    /**
     * This a constructor without parameters
     */
    public LoginJpanel(){

        login_result.setForeground(Color.red);
        label.setFont(new Font (Font.DIALOG, Font.PLAIN, 21));

        this.setLayout(new BorderLayout());
        p1.setLayout(new GridLayout(4,1));
        p2.setLayout(new GridLayout(2,1));

        this.add(p1,BorderLayout.CENTER);
        this.add(p11,BorderLayout.EAST);
        this.add(p12,BorderLayout.WEST);

        p11.setPreferredSize(new Dimension(230,0));
        p12.setPreferredSize(new Dimension(230,0));

        p1.add(p3);
        p1.add(p2);
        p1.add(p4);
        p1.add(p8);
        p8.add(login_result);
        p2.add(p9);
        p2.add(p10);
        p3.add(p31);
        p3.add(p32);
        p32.add(label);
        p9.add(new JLabel("Account ID: "));
        p9.add(accNo_text);
        p10.add(new JLabel("Password: "));
        p10.add(password_text);

        this.setBounds(0,0,500,500);
    }

    public String getAccNo_get() {
        return accNo_get;
    }

    public void setAccNo_get(String accNo_get) {
        this.accNo_get = accNo_get;
    }

    public String getPassword_get() {
        return password_get;
    }

    public void setPassword_get(String password_get) {
        this.password_get = password_get;
    }

    public String getFilename() {
        return filename;
    }

    public JPanel getP2() {
        return p2;
    }

    public void setP2(JPanel p2) {
        this.p2 = p2;
    }

    public JPanel getP3() {
        return p3;
    }

    public void setP3(JPanel p3) {
        this.p3 = p3;
    }

    public JPanel getP31() {
        return p31;
    }

    public void setP31(JPanel p31) {
        this.p31 = p31;
    }

    public JPanel getP32() {
        return p32;
    }

    public void setP32(JPanel p32) {
        this.p32 = p32;
    }

    public JPanel getP4() {
        return p4;
    }

    public void setP4(JPanel p4) {
        this.p4 = p4;
    }

    public JPanel getP5() {
        return p5;
    }

    public void setP5(JPanel p5) {
        this.p5 = p5;
    }

    public JPanel getP6() {
        return p6;
    }

    public void setP6(JPanel p6) {
        this.p6 = p6;
    }

    public JPanel getP7() {
        return p7;
    }

    public void setP7(JPanel p7) {
        this.p7 = p7;
    }

    public JPanel getP8() {
        return p8;
    }

    public void setP8(JPanel p8) {
        this.p8 = p8;
    }

    public JPanel getP9() {
        return p9;
    }

    public void setP9(JPanel p9) {
        this.p9 = p9;
    }

    public JPanel getP10() {
        return p10;
    }

    public void setP10(JPanel p10) {
        this.p10 = p10;
    }

    public JLabel getLogin_result() {
        return login_result;
    }

    public void setLogin_result(JLabel login_result) {
        this.login_result = login_result;
    }

    public JTextField getAccNo_text() {
        return accNo_text;
    }

    public void setAccNo_text(JTextField accNo_text) {
        this.accNo_text = accNo_text;
    }

    public JPasswordField getPassword_text() {
        return password_text;
    }

    public void setPassword_text(JPasswordField password_text) {
        this.password_text = password_text;
    }

    public JFrame getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(JFrame belongsTo) {
        this.belongsTo = belongsTo;
    }
}
