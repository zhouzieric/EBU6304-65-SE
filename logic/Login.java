package logic;

import java.io.*;
import java.util.ArrayList;

//用户，教练，管理员登录注册时校验信息的类
public class Login {


     private String AccLogin="";

     //管理员登录验证
     public String admin_login(String accNo_get, String password_get){

         String password_file;
         String filename="src/main/java/data/AInfo/admin_Id&Pass.txt";
         String acc_isexist = (String) getAdInfo(filename, accNo_get).get(2);
         if(accNo_get.equals("")){
             return "**Account ID can't be blank!**";
         }
         if (acc_isexist.equals("1")) {

             password_file = (String) getAdInfo(filename, accNo_get).get(1);

             if (password_file.equals(password_get)) {
                 return "Login successfully!";
             } else {
                 return "**Your password is incorrect!**";
             }
         }

         else {
             return "**Your account ID doesn't exist! Please confirm it again!**";
         }
     }

     //返回文件中管理员的ID及密码
    public ArrayList getAdInfo(String fileName,String accNo_get){
        ArrayList<String> accInfo= new ArrayList<>();

        String accNo="";
        String password="";

        try
        {
            FileReader fileReader=new FileReader(fileName);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String oneLine=bufferedReader.readLine();


            while(oneLine=="\r\n"||oneLine!=null)
            {

                accNo=oneLine.split(";")[0];
                password=oneLine.split(";")[1];

                if(judge_accNo(accNo.trim(),accNo_get)==true){
                    break;
                }

                oneLine=bufferedReader.readLine();
            }

            bufferedReader.close();

            fileReader.close();
        }catch(IOException e)
        {
            System.out.println("ERROR");
            System.exit(1);
        }

        accInfo.add(accNo.trim());
        accInfo.add(password);
        if(judge_accNo(accNo.trim(),accNo_get)==true){
            accInfo.add("1");
        }else{
            accInfo.add("0");
        }
        return accInfo;
    }

    //用户及教练登录
    public String login(String accNo_get, String password_get) {

        String password_file;
        String acc_isexist = (String) getAccInfo(accNo_get).get(2);

        if(accNo_get.equals("")){
            return "**Account ID can't be blank!**";
        }
        if (acc_isexist.equals("1")) {

            password_file = (String) getAccInfo(accNo_get).get(1);

            if (judge_pass(password_file, password_get) == 1) {
                return "Login successfully!";
            } else {
                return "**Your password is incorrect!**";
            }
        }
        else {
                return "**Your account ID doesn't exist! Please confirm it again!**";
            }
    }


    public boolean judge_accNo(String accNo_file,String accNo_get){
        if(accNo_file.equals(accNo_get)){
            return true;
        }
        else{
            return false;
        }
    }

    //判断输入的密码是否正确
    public int judge_pass(String password_file, String password_get){
        if(password_file.equals(password_get)){
            return 1;
        }
        else{
            return 0;
        }
    }

    //确定给用户和教练分配的ID
    public String distri_acc(String I){

        int file_num=1;
        File file;
        Boolean flag=true;
        String accNo;

        while(flag){
            file = new File("src/main/java/data/"+I+"Info/"+I+file_num+".txt");

            if (file.exists()){
                file_num=file_num+1;

            }else {
                flag=false;
            }

        }
        accNo = I + file_num;
      //  System.out.println(accNo);
        return accNo;
    }

    //得到用户和教练在文件中存的ID和密码
    public ArrayList getAccInfo(String accNo_get){
    ArrayList<String> accInfo= new ArrayList<>();

    if(accNo_get.equals("")){
        accInfo.add("");
        accInfo.add("");
        accInfo.add("0");
        return accInfo;

    }

    File file = new File("src/main/java/data/"+accNo_get.charAt(0)+"Info/"+accNo_get+".txt");
    String accNo="";
    String password="";

    if (file.exists()) {
        try
        {

            FileReader fileReader=new FileReader("src/main/java/data/"+accNo_get.charAt(0)+"Info/"+accNo_get+".txt");
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String oneLine=bufferedReader.readLine();
            accNo=oneLine.split(";")[0];
            password=oneLine.split(";")[1];
            bufferedReader.close();
            fileReader.close();
        }catch(IOException e)
        {
            System.out.println("ERROR");
            System.exit(1);
        }

        accInfo.add(accNo.trim());
        accInfo.add(password.trim());
        accInfo.add("1");
    } else {
        accInfo.add(accNo.trim());
        accInfo.add(password.trim());
        accInfo.add("0");
    }
    return accInfo; }

    public String getAccLogin() {
        return AccLogin;
    }

    public void setAccLogin(String accLogin) {
        AccLogin = accLogin;
    }
}