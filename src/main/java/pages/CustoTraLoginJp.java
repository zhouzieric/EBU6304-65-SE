package pages;

import logic.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 *Class that displays customer or trainer login
 * @author Yixin Li
 * @version 5.6.3
 */
public class CustoTraLoginJp extends LoginJpanel {
    private JButton login_but=new JButton("Login");
    private JButton go_back=new JButton("Go Back");
    private JButton register=new JButton("Register");
    /**
     * This a constructor without parameters
     */
    public CustoTraLoginJp(){
        super();
        getP4().setLayout(new GridLayout(1,3));
        getP4().add(getP5());
        getP4().add(getP6());
        getP4().add(getP7());
        getP5().add(getGo_back());
        getP6().add(getLogin_but());
        getP7().add(register);

    }

    public JButton getRegister() {
        return register;
    }

    public void setRegister(JButton register) {
        this.register = register;
    }
    public JButton getGo_back() {
        return go_back;
    }

    public void setGo_back(JButton go_back) {
        this.go_back = go_back;
    }
    public JButton getLogin_but() {
        return login_but;
    }

    public void setLogin_but(JButton login_but) {
        this.login_but = login_but;
    }

}
