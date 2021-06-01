package logic;

import bean.Customer;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that verifies information when users and coaches register
 * @author Yixin Li
 * @version 5.6.3
 */
public class Register {

    private static String REG_EXP = "^([0-9]{3}-?[0-9]{8}|[0-9]{4}-?[0-9]{7})$";
    private static String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
/**
 * The method used to authenticate a password.
 * @param  pass_get String to indicate the password typed in.
 * @param label JLabel to give user tips.
 * @return Boolean
 *The return value indicates whether the password is valid or not.
 */
    public Boolean Password(String pass_get,JLabel label) {

        Boolean flag;
        Boolean flag1=false;
        Boolean flag2=false;

        if(pass_get.equals("")){
            label.setText("**Your password can not be blank.**");
            label.setForeground(Color.red);
            return false;
        }

        if (pass_get.length() < 8) {
            label.setText("**Your password's length must be longer than 8.**");
            label.setForeground(Color.red);
            return false;
        }

        for (int i = 0; i < pass_get.length(); i++) {
            if(!Character.isLetterOrDigit(pass_get.charAt(i))){

                label.setText("**Your password must consist of numbers and letters.**");
                label.setForeground(Color.red);
                return false;
            }else if(Character.isLetter(pass_get.charAt(i))){
                flag1=true;
            }else if(Character.isDigit(pass_get.charAt(i))){
                flag2=true;
            }
        }
        if(flag1&&flag2){
            label.setText("");
            flag=true;
        }else{
            label.setText("**Your password must consist of numbers and letters.**");
            label.setForeground(Color.red);
            return false;
        }
        return flag;
    }
    /**
     * The method used to authenticate a password.
     * @param  r String to indicate the password firstly typed in.
     * @param  r1 String to indicate the password secondly typed in.
     * @param label JLabel to give user tips.
     * @return Boolean
     *The return value indicates whether the two passwords are the same.
     */
    public Boolean pass_confir(String r,String r1,JLabel label){

        Boolean flag=true;

        if(r.equals("")){
            label.setText("**It can't be blank.**");
            label.setForeground(Color.red);
            return false;
        }else{
                if(!r.equals(r1)){
                    flag=false;
                    label.setText("**The two passwords must be the same.**");
                    label.setForeground(Color.red);
        }}

        if(flag==true){
            label.setText("");
        }
        return flag;
    }
    /**
     * Verify that the phone number format is correct.
     * @param  phone_get String to indicate the phone number typed in.
     * @param label JLabel to give user tips.
     * @return Boolean
     *The return value indicates whether the phone number entered format is correct.
     */
    public Boolean judge_phonenum(String phone_get,JLabel label){

        Boolean flag;

        if (phone_get.equals("")){
            label.setText("**It can't be blank.**");
            label.setForeground(Color.red);
            return false;
        }

        if( phone_get.matches(REG_EXP) ){
            label.setText("");
            flag=true;
        }

        else{
            label.setText("**Please enter a valid phone number.**");
            label.setForeground(Color.red);
            flag=false;
        }
        return flag;
    }
    /**
     * Verify that the e-mail format is correct.
     * @param  email_get String to indicate the e-mail typed in.
     * @param label JLabel to give user tips.
     * @return Boolean
     *The return value indicates whether the e-mail entered format is correct.
     */
    public  boolean isEmail(String email_get,JLabel label) {
        if (email_get.equals("")){
            label.setText("**It can't be blank.**");
            label.setForeground(Color.red);
            return false;
        }
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(email_get);

        if (m.matches())
        {
            label.setText("");
            return true;
        }
        else{
            label.setText("**Please enter a valid e-mail.**");
            label.setForeground(Color.red);
            return false;
        }
    }
    /**
     * Verify whether the first name, last name and date of birth is blank or not.
     * @param  fn_get String to indicate the first name typed in.
     * @param l1 JLabel to give user tips.
     * @param  ln_get String to indicate the last name typed in.
     * @param l2 JLabel to give user tips.
     * @param  birth_get String to indicate the date of birth typed in.
     * @param l3 JLabel to give user tips.
     * @return Boolean
     *The return value indicates whether the first name, last name and date of birth is blank or not.
     */
    public Boolean name_birth(String fn_get,JLabel l1,String ln_get, JLabel l2,  String birth_get,JLabel l3){

        Boolean flag1=true;
        Boolean flag2=true;
        Boolean flag3=true;

        if(fn_get.equals("")){
            l1.setText("**It can't be blank.**");
            l1.setForeground(Color.red);
            flag1=false;

        }else{
            l1.setText("");
            flag1=true;
        }
        if(ln_get.equals("")){
        l2.setText("**It can't be blank.**");
        l2.setForeground(Color.red);
        flag2=false;
        }else{
        l2.setText("");
        flag2=true; }
        if(birth_get.equals("")){
        l3.setText("**It can't be blank.**");
        l3.setForeground(Color.red);
        flag3=false;
        }else{
        l3.setText("");
        flag3=true;
        }
        return  flag1&flag2&flag3;
    }
    /**
     * Verify whether the nickname format is correct or not.
     * @param  name String to indicate the nickname typed in.
     * @param label JLabel to give user tips.
     * @return Boolean
     *The return value indicates whether the nickname format is correct or not.
     */
    public boolean TestName(String name, JLabel label){
        if(name.equals("")){
            label.setText("**It can't be blank.**");
            label.setForeground(Color.red);
            return false;
        }else if(name.indexOf(';')>=0){
            label.setText("**It can't contain semicolon.**");
            label.setForeground(Color.red);
            return false;
        }else{
            label.setText("");
            return true;}
    }
    /**
     * Determine whether the information filled in by the user or coach registration is legitimate, if so
     * , pay and successfully register to deposit the file.
     * @param  succ JLabel to give user tips.
     * @param  anum String to indicate the customer or trainer's account ID assigned.
     * @param  acc_get String to indicate the customer's nickname entered.
     * @param label JLabel to give user tips.
     * @param  pass_get String to indicate the customer or trainer's password entered.
     * @param  phone_get String to indicate the customer or trainer's phone number entered.
     * @param jl4 JLabel to give user tips.
     * @param  email_get String to indicate the customer or trainer's email_get entered.
     * @param jl5 JLabel to give user tips.
     * @param label2 JLabel to give user tips.
     * @param info String to indicate customer or trainer's information.
     * @param flag3 Boolean to indicate customer or trainer's passwords entered twice are the same or not.
     * @param fn_get String to indicate the customer or trainer's first name entered.
     * @param l1 JLabel to give user tips.
     * @param ln_get String to indicate the customer or trainer's last name entered.
     * @param l2 JLabel to give user tips.
     * @param birth_get String to indicate the customer or trainer's date of birth entered.
     * @param l3 JLabel to give user tips.
     * @param membership String to indicate the customer's membership entered.
     * @return Boolean
     *The return value indicates whether the customer or trainer can submit or not.
     */
    public boolean can_submit(JLabel succ,String anum, String acc_get,JLabel label, String pass_get, String phone_get,
                              JLabel jl4, String email_get, JLabel jl5,JLabel label2,String info,
                              Boolean flag3,String fn_get,JLabel l1,String ln_get, JLabel l2,  String birth_get,JLabel l3,
                              String membership){

        Boolean flag2=Password(pass_get,label2);
        Boolean flag4=judge_phonenum(phone_get,jl4);
        Boolean flag5=isEmail(email_get,jl5);
        Boolean flag6=name_birth(fn_get,l1,ln_get, l2, birth_get, l3);
        Boolean flag7=TestName(acc_get,label);

        if(anum.charAt(0)=='T'){
            flag7=true;
        }

        if(flag2&&flag3&&flag4&&flag5&&flag6&&flag7){

            int input = 1;
            ArrayList<String> memRank = Customer.getMemRank();
            for(int i=0;i<memRank.size();i++){
                if(membership.equals(memRank.get(i))){
                    input = JOptionPane.showConfirmDialog(null,"Are you sure you want to pay $"+ReadFlexibleInfo.readMemUpdate(memRank.get(memRank.size()-1), memRank.size()-1-i)+" to upgrade?   ","Payment",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                }
            }

            if((input==0)||(membership.equals("trainer"))){
                if(input==0) {
                    JOptionPane.showMessageDialog(null, "Pay Successfully!   ", "Payment", JOptionPane.DEFAULT_OPTION);
                } //After Pay Successfully, write membership into file
                String filename="src/main/java/data/"+anum.charAt(0)+"Info/"+anum+".txt";
                try{

                    FileWriter fileWriter=new FileWriter(filename);
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    bufferedWriter.write(info);
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                succ.setFont(new Font (Font.DIALOG, Font.PLAIN, 18));
                succ.setText("You have successfully registered! Your assigned account ID is: "+anum+".");
            }
            return true;
        }else
            return false;

    }

}
