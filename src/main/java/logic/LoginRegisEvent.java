package logic;

import pages.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
/**
 *Class that handles login registration events
 * @author Yixin Li
 * @version 5.6.3
 */
public class LoginRegisEvent {

    private EnterJpanel enterJpanel;
    private LoginJpanel loginJpanel;
    private CustomLoginJp customLoginJp;
    private TraLoginJp traLoginJp;
    private AdminLoginJp adminLoginJp;
    private RegisterJpanel registerJpanel;
    private CustomerRegisJP customerRegisJP;
    private TrainerRegisJP trainerRegisJP;
    private StandardFrame standardFrame;
    private JFrame jFrame;
/**
 * handles login registration events
 * @param  enterFrame EnterFrame to operate
 * @param jFrame JFrame to operate
 */
    public void response(EnterFrame enterFrame, JFrame jFrame){

    //Coaches register and submit information
        trainerRegisJP.getSubmit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register=new Register();
                Login login=new Login();
                trainerRegisJP.setAnum(login.distri_acc("T"));
                trainerRegisJP.setPass_get(trainerRegisJP.getPass1().getText());
                trainerRegisJP.setPass_get1(trainerRegisJP.getPass2().getText());
                trainerRegisJP.setFname_get(trainerRegisJP.getT4().getText());
                trainerRegisJP.setLname_get(trainerRegisJP.getT5().getText());
                trainerRegisJP.setBirth_get(trainerRegisJP.getT6().getText());
                trainerRegisJP.setPhnum_get(trainerRegisJP.getT7().getText());
                trainerRegisJP.setEmail_get(trainerRegisJP.getT8().getText());
                trainerRegisJP.setPro_get(trainerRegisJP.getPro().getSelectedItem().toString());
                trainerRegisJP.setRank_get(trainerRegisJP.getRank().getSelectedItem().toString());
                trainerRegisJP.setPass_confirm(register.pass_confir(trainerRegisJP.getPass_get(),trainerRegisJP
                        .getPass_get1(),trainerRegisJP.getJl3()));
                if(trainerRegisJP.getRadio4().isSelected()){
                    trainerRegisJP.setGender("male");
                }else {
                    trainerRegisJP.setGender("female");;
                }

                trainerRegisJP.setInfo(trainerRegisJP.getAnum()+";"+trainerRegisJP.getPass_get()+";"+trainerRegisJP.getFname_get()+";"+
                        trainerRegisJP.getLname_get()+";"+trainerRegisJP.getGender()+";"+trainerRegisJP.getPhnum_get()+";"+trainerRegisJP.getEmail_get()+
                        ";"+trainerRegisJP.getBirth_get()+";"+trainerRegisJP.getPro_get()+";"+trainerRegisJP.getRank_get()+"\r\n");
                register.can_submit(trainerRegisJP.getSucc(),trainerRegisJP.getAnum(),"",trainerRegisJP.getJl1(),trainerRegisJP.getPass_get(),
                        trainerRegisJP.getPhnum_get(),trainerRegisJP.getJl4(),trainerRegisJP.getEmail_get(),trainerRegisJP.getJl5(),trainerRegisJP.getJl2(),
                        trainerRegisJP.getInfo(),trainerRegisJP.getPass_confirm(),trainerRegisJP.getFname_get(),trainerRegisJP.getJl6(),
                        trainerRegisJP.getLname_get(), trainerRegisJP.getJl7(),trainerRegisJP.getBirth_get() ,trainerRegisJP.getJl8(),"trainer"); }
        });

        //Control customer's gender only single choice
        customerRegisJP.getRadio4().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerRegisJP.getRadio5().setSelected(false);

            }
        });
        //Control customer's gender only single choice
        customerRegisJP.getRadio5().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerRegisJP.getRadio4().setSelected(false);

            }
        });
        //Control trainer's gender only single choice
        trainerRegisJP.getRadio4().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trainerRegisJP.getRadio5().setSelected(false);

            }
        });
        //Control trainer's gender only single choice
        trainerRegisJP.getRadio5().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trainerRegisJP.getRadio4().setSelected(false);

            }
        });

        //trainer login
        traLoginJp.getLogin_but().addActionListener((e)->{
            traLoginJp.setAccNo_get(traLoginJp.getAccNo_text().getText());
            traLoginJp.setPassword_get(traLoginJp.getPassword_text().getText());
            Login login=new Login();
            traLoginJp.getLogin_result().setText(login.login(traLoginJp.getAccNo_get(),traLoginJp.getPassword_get()));
            if(traLoginJp.getLogin_result().getText().equals("Login successfully!")){
                login.setAccLogin(traLoginJp.getAccNo_get());
                try{
                    FileWriter fileWriter=new FileWriter(traLoginJp.getFilename());
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    bufferedWriter.write(traLoginJp.getAccNo_get());
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {

                    new view().cAsTrainerGUI();
                    jFrame.dispose();

                } catch (UnsupportedLookAndFeelException | ParseException unsupportedLookAndFeelException) {
                    unsupportedLookAndFeelException.printStackTrace();
                }
            }else{
                enterFrame.getCardLayout().show(enterFrame,"T_login");
            }
        });

        //customer login
        customLoginJp.getLogin_but().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customLoginJp.setAccNo_get(customLoginJp.getAccNo_text().getText());
                customLoginJp.setPassword_get(customLoginJp.getPassword_text().getText());
                Login login=new Login();
                customLoginJp.getLogin_result().setText(login.login(customLoginJp.getAccNo_get(),customLoginJp.getPassword_get()));
                if(customLoginJp.getLogin_result().getText().equals("Login successfully!")){
                    login.setAccLogin(customLoginJp.getAccNo_get());
                    try{

                        FileWriter fileWriter=new FileWriter(customLoginJp.getFilename());
                        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                        bufferedWriter.write(customLoginJp.getAccNo_get());
                        bufferedWriter.close();
                        fileWriter.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {

                        new view().createAndShowGUI();
                        jFrame.dispose();

                    } catch (UnsupportedLookAndFeelException | ParseException unsupportedLookAndFeelException) {
                        unsupportedLookAndFeelException.printStackTrace();
                    }
                }else{
                    enterFrame.getCardLayout().show(enterFrame,"C_login");

                }
            }
        });

        //Users register to submit information
        customerRegisJP.getSubmit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Register register=new Register();
                Login login=new Login();
                customerRegisJP.setAnum(login.distri_acc("C"));
                customerRegisJP.setAcc_getC(customerRegisJP.getT1().getText());
                customerRegisJP.setPass_get(customerRegisJP.getPass1().getText());
                customerRegisJP.setPass_get1(customerRegisJP.getPass2().getText());
                customerRegisJP.setFname_get(customerRegisJP.getT4().getText());
                customerRegisJP.setLname_get(customerRegisJP.getT5().getText());
                customerRegisJP.setBirth_get(customerRegisJP.getT6().getText());
                customerRegisJP.setPhnum_get(customerRegisJP.getT7().getText());
                customerRegisJP.setEmail_get(customerRegisJP.getT8().getText());
                customerRegisJP.setPass_confirm(register.pass_confir(customerRegisJP.getPass_get(),
                        customerRegisJP.getPass_get1(),customerRegisJP.getJl3()));
                if(customerRegisJP.getRadio4().isSelected()){
                    customerRegisJP.setGender("male");
                }else {
                    customerRegisJP.setGender("female");;
                }
                customerRegisJP.setMembership(customerRegisJP.getMember().getSelectedItem().toString());

                customerRegisJP.setInfo(customerRegisJP.getAnum()+";"+customerRegisJP.getPass_get()+";"+customerRegisJP.getAcc_getC()+";"+
                        customerRegisJP.getFname_get()+";"+customerRegisJP.getLname_get()+";"+customerRegisJP.getGender()+";"+customerRegisJP.getPhnum_get()+
                        ";"+customerRegisJP.getEmail_get()+";"+customerRegisJP.getBirth_get()+";"+customerRegisJP.getMembership()+"\r\n");
                register.can_submit(customerRegisJP.getSucc(),customerRegisJP.getAnum(),customerRegisJP.getAcc_getC(),customerRegisJP.getJl1(),
                        customerRegisJP.getPass_get(),customerRegisJP.getPhnum_get(),customerRegisJP.getJl4(),customerRegisJP.getEmail_get(),customerRegisJP.getJl5(),
                        customerRegisJP.getJl2(),customerRegisJP.getInfo(),customerRegisJP.getPass_confirm(),customerRegisJP.getFname_get(),
                        customerRegisJP.getJl6(),customerRegisJP.getLname_get(), customerRegisJP.getJl7(),customerRegisJP.getBirth_get() ,customerRegisJP.getJl8(),customerRegisJP.getMembership());

            }
        });

        //Switch coach registration to the page from the coach login page
        traLoginJp.getRegister().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                enterFrame.getCardLayout().show(enterFrame,"train_register");

            }
        });

        //Switch from the user login page to the user registration page
        customLoginJp.getLogin_but().addActionListener(new ActionListener() {
            @Override
    public void actionPerformed(ActionEvent e) {

        enterFrame.getCardLayout().show(enterFrame,"custom_register");

    }
});

        //After administrator login successfully what responses
        adminLoginJp.getLogin_but().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminLoginJp.setAccNo_get(adminLoginJp.getAccNo_text().getText());
                adminLoginJp.setPassword_get(adminLoginJp.getPassword_text().getText());
                Login login=new Login();
                adminLoginJp.getLogin_result().setText(login.admin_login(adminLoginJp.getAccNo_get(),adminLoginJp.getPassword_get()));
                if(adminLoginJp.getLogin_result().getText().equals("Login successfully!")){

                    jFrame.setVisible(false);
                    try {
                        new view().openadmin();
                    } catch (UnsupportedLookAndFeelException ex) {
                        ex.printStackTrace();
                    }

                    //Save the ID of the successful login to a file
                    login.setAccLogin(adminLoginJp.getAccNo_get());
                    try{

                        FileWriter fileWriter=new FileWriter(adminLoginJp.getFilename());
                        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                        bufferedWriter.write(adminLoginJp.getAccNo_get());
                        bufferedWriter.close();
                        fileWriter.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                } }
        });

        //Enter page switches to the user login page
        enterJpanel.getB1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginJpanel.getLogin_result().setText("");

                enterFrame.getCardLayout().show(enterFrame,"C_login");

            }
        });

        //Enter page switches to the administrator login page
        enterJpanel.getB2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                loginJpanel.getLogin_result().setText("");
                loginJpanel.getAccNo_text().setText("");
                loginJpanel.getPassword_text().setText("");
                enterFrame.getCardLayout().show(enterFrame,"A_login");

            }
        });

        //Enter page switches to the coach login page
        enterJpanel.getB3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                loginJpanel.getLogin_result().setText("");
                loginJpanel.getAccNo_text().setText("");
                loginJpanel.getPassword_text().setText("");
                enterFrame.getCardLayout().show(enterFrame,"T_login");

            }
        });

        //Return to the enter page from the user login page
        customLoginJp.getGo_back().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                enterFrame.getCardLayout().show(enterFrame,"enter");

            }
        });

        //Return to the enter page from the trainer login page
        traLoginJp.getGo_back().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                enterFrame.getCardLayout().show(enterFrame,"enter");

            }
        });

        //Return to the enter page from the administrator login page
        adminLoginJp.getGo_back().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                enterFrame.getCardLayout().show(enterFrame,"enter");

            }
        });

        //Switch from the user login screen to the user registration page
        customLoginJp.getRegister().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                enterFrame.getCardLayout().show(enterFrame,"custom_register");

            }
        });


        //Switch from the coach login page to the coach registration page
        traLoginJp.getRegister().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                enterFrame.getCardLayout().show(enterFrame,"train_register");

            }
        });

        //Return to the user login page from the user registration page
        customerRegisJP.getGo_Back().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                enterFrame.getCardLayout().show(enterFrame,"C_login");

            }
        });

        //Return from the coach registration page to the coach login page
        trainerRegisJP.getGo_Back().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterFrame.getCardLayout().show(enterFrame,"T_login");
            }
        });
    }

    public EnterJpanel getEnterJpanel() {
        return enterJpanel;
    }

    public void setEnterJpanel(EnterJpanel enterJpanel) {
        this.enterJpanel = enterJpanel;
    }

    public LoginJpanel getLoginJpanel() {
        return loginJpanel;
    }

    public void setLoginJpanel(LoginJpanel loginJpanel) {
        this.loginJpanel = loginJpanel;
    }

    public RegisterJpanel getRegisterJpanel() {
        return registerJpanel;
    }

    public void setRegisterJpanel(RegisterJpanel registerJpanel) {
        this.registerJpanel = registerJpanel;
    }

    public CustomerRegisJP getCustomerRegisJP() {
        return customerRegisJP;
    }

    public void setCustomerRegisJP(CustomerRegisJP customerRegisJP) {
        this.customerRegisJP = customerRegisJP;
    }

    public TrainerRegisJP getTrainerRegisJP() {
        return trainerRegisJP;
    }

    public void setTrainerRegisJP(TrainerRegisJP trainerRegisJP) {
        this.trainerRegisJP = trainerRegisJP;
    }



    public AdminLoginJp getAdminLoginJp() {
        return adminLoginJp;
    }

    public void setAdminLoginJp(AdminLoginJp adminLoginJp) {
        this.adminLoginJp = adminLoginJp;
    }


    public CustomLoginJp getCustomLoginJp() {
        return customLoginJp;
    }

    public void setCustomLoginJp(CustomLoginJp customLoginJp) {
        this.customLoginJp = customLoginJp;
    }

    public TraLoginJp getTraLoginJp() {
        return traLoginJp;
    }

    public void setTraLoginJp(TraLoginJp traLoginJp) {
        this.traLoginJp = traLoginJp;
    }

    public StandardFrame getStandardFrame() {
        return standardFrame;
    }

    public void setStandardFrame(StandardFrame standardFrame) {
        this.standardFrame = standardFrame;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }
}
