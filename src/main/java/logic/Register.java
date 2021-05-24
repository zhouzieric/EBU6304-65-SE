package logic;

import bean.Customer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

////用户，教练注册时校验信息的类
public class Register {

    private static String REG_EXP = "^([0-9]{3}-?[0-9]{8}|[0-9]{4}-?[0-9]{7})$";
    private static String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    //用于验证密码的方法
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

    //用于验证两次输入的密码是否一致
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

    //用于验证电话号码格式是否正确
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

    //用于验证邮箱号格式是否正确
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

    //用于验证姓名和生日是否为空
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

    //用于验证昵称格式是否正确
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

    //提交信息存入文件
    public boolean can_submit(JLabel succ,String anum, String acc_get,JLabel label, String pass_get, String phone_get,JLabel jl4, String email_get, JLabel jl5,JLabel label2,String info,Login login,String I,Boolean flag3,String fn_get,JLabel l1,String ln_get, JLabel l2,  String birth_get,JLabel l3, String membership){

        Boolean flag2=Password(pass_get,label2);
        Boolean flag4=judge_phonenum(phone_get,jl4);
        Boolean flag5=isEmail(email_get,jl5);
        Boolean flag6=name_birth(fn_get,l1,ln_get, l2, birth_get, l3);
        Boolean flag7=TestName(acc_get,label);

        if(anum.charAt(0)=='T'){
            flag7=true;
        }

        if(flag2&&flag3&&flag4&&flag5&&flag6&&flag7){

            //如果是用户需要支付
            int input = 1;
            if(membership.equals(Customer.MEMBERSHIP_GOLD)){
                input = JOptionPane.showConfirmDialog(null,"Are you sure you want to pay $200 to upgrade?    ","Payment",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            }else if(membership.equals(Customer.MEMBERSHIP_NORM)){
                input = JOptionPane.showConfirmDialog(null,"Are you sure you want to pay $100 to upgrade?   ","Payment",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            }
            if((input==0)||((input ==1)&&(membership.equals("trainer")))){
                if(input==0){
                    JOptionPane.showMessageDialog(null,"Pay Successfully!   ","Payment",JOptionPane.DEFAULT_OPTION);
                }

                //After Pay Successfully, write membership into file
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
