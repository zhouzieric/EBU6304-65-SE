package logic;

import bean.Customer;
import bean.Member;
import pages.PayUpgradeGUI;
import pages.PersonalInfoController;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

public class PersonalInfoListener implements ActionListener {
    PersonalInfoController pi;
    ChangeInfo readInfo;
    Member mem;
    int flag;



    @Override
    public void actionPerformed(ActionEvent e) {
        Login log = new Login();
        Register reg = new Register();
        Boolean indicator=false;
        mem = pi.getMem();
        readInfo = pi.getReadInfo();
        flag = pi.getFlag();

        if(e.getSource() == pi.getButtonMem()){
            PayUpgradeGUI pug = new PayUpgradeGUI();

            Customer cus = (Customer)mem;
            pug.go(pi.getReadInfo(), pi.getlMem(), cus);

            //pay and choose memberhship status
            //file info change

        }/**
         else if(e.getSource() == bAcc){
         //change accNo
         String accNo = JOptionPane.showInputDialog(null,"Enter New Account No. : ","Change Account No.", JOptionPane.OK_CANCEL_OPTION);
         String acc_isexist = (String) log.getAccInfo(accNo).get(2);

         if(accNo != null){
         indicator = reg.Account(accNo,acc_isexist,lAccWarn,mem.getAccountNo().charAt(0));
         }
         if(indicator) {
         if (flag == 1) {
         mem = readInfo.changeCusInfo(1, accNo);
         } else if (flag == 2) {
         mem = readInfo.changeTraInfo(1, accNo);
         }
         acc_login=mem.getAccountNo();
         readInfo.setAccNo(acc_login);
         //System.out.
         lAcc.setText("Account No.:  " + mem.getAccountNo());
         }
         }*/else if(e.getSource() == pi.getbE()){
            //change email
            String email = JOptionPane.showInputDialog(pi,"Enter New Email Address: ","Change Email Address", JOptionPane.OK_CANCEL_OPTION);
            if(email != null){
                indicator = reg.isEmail(email,pi.getlEWarn());
            }

            if(indicator) {
                if (flag == 1) {
                    mem = readInfo.changeCusInfo(2, email);
                } else if (flag == 2) {
                    mem = readInfo.changeTraInfo(2, email);
                }
                pi.getlEWarn().setText(" ");
                pi.getlE().setText("Email:  " + mem.getEmail_addr());
            }

        }else if(e.getSource() == pi.getbPN()){
            //change phone num
            String phone_number = JOptionPane.showInputDialog(pi,"Enter New Phone Number: ","Change Phone Number", JOptionPane.OK_CANCEL_OPTION);
            if(phone_number != null){
                indicator = reg.judge_phonenum(phone_number,pi.getlPNWarn());
            }

            if(indicator) {
                if (flag == 1) {
                    mem = readInfo.changeCusInfo(3, phone_number);
                } else if (flag == 2) {
                    mem = readInfo.changeTraInfo(3, phone_number);
                }
                pi.getlPNWarn().setText(" ");
                pi.getlPN().setText("Phone Num:   " + mem.getPhone_num());
            }
        }else if(e.getSource() == pi.getbPW()){
            //change password
            String password= JOptionPane.showInputDialog(pi,"Enter New Password: ","Change Password", JOptionPane.OK_CANCEL_OPTION);
            if(password != null){
                indicator=reg.Password(password,pi.getlPWWarn());
            }

            if(indicator){
                //System.out.println("Enter");
                if(flag==1){
                    mem = readInfo.changeCusInfo(4,password);
                }else if(flag==2){
                    mem = readInfo.changeTraInfo(4,password);
                }
                pi.getlPWWarn().setText(" ");
                pi.getlPW().setText("Password:   " +hidePW(mem.getPassword()));
            }

        }else if(e.getSource() == pi.getbPWShow()){
            //show password
            pi.getlPW().setText("Password:   "+mem.getPassword());
        }else if(e.getSource() == pi.getbPWHide()){
            //hide password
            pi.getlPW().setText("Password:   "+hidePW(mem.getPassword()));
        }else if(e.getSource() == pi.getbNick()){
            //change nickname
            String nickname= JOptionPane.showInputDialog(pi,"Enter New Nickname: ","Change NickName", JOptionPane.OK_CANCEL_OPTION);
            if(nickname != null){
                indicator=reg.TestName(nickname,pi.getlNickWarn());
            }

            if(indicator){
                //System.out.println("Enter");
                if(flag==1) {
                    mem = readInfo.changeCusInfo(5, nickname);
                }
                Customer cus = (Customer) mem;
                pi.getlNickWarn().setText(" ");
                pi.getlNick().setText("Nick Name:   "+ cus.getNickname());
            }
        }

    }

    public String hidePW(String password){
        String ask = "";
        for(int i =0;i<password.length();i++){
            ask = ask.concat("*");
        }
        return ask;
    }

    /**
    public void setMem(Member mem) {
        this.mem = mem;
    }

    public void setReadInfo(ChangeInfo readInfo) {
        this.readInfo = readInfo;
    }
    */

    public void setPi(PersonalInfoController pi) {
        this.pi = pi;
    }
}
