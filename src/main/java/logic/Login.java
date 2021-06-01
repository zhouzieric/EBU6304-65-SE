package logic;

import java.io.*;
import java.util.ArrayList;

/**
 *Class that verifies information when users, coaches, and administrators log in and register
 * @author Yixin Li
 * @version 5.6.3
 */
public class Login {


     private String AccLogin="";
/**
 * Verify administrator login information.
 * @param  accNo_get String to indicate the administrator's account ID typed in.
 * @param  password_get String to indicate the administrator's password typed in
 * @return  String
 *The return value indicates the result whether the login is successful or not.
 */

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

    /**
     * Return the administrator login information in the file.
     * @param  fileName String to indicate the file name to be read.
     * @param  accNo_get String to indicate the administrator's account ID in the file.
     * @return  ArrayList
     *The return value indicates the administrator login information containing account ID and password.
     */
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

    /**
     * Verify customer and trainer's login information.
     * @param  accNo_get String to indicate the customer and trainer's account ID typed in.
     * @param  password_get String to indicate the customer and trainerr's password typed in
     * @return  String
     *The return value indicates the result whether the login is successful or not.
     */
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

    /**
     * Determine whether the account ID entered is the same as the account ID in the file.
     * @param  accNo_file String to indicate the account ID in the file.
     * @param  accNo_get String to indicate the account ID typed in.
     * @return  Boolean
     *The return value indicates the result whether the account ID entered is the same as the account ID in the file.
     */
    public boolean judge_accNo(String accNo_file,String accNo_get){
        if(accNo_file.equals(accNo_get)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Determine whether the password entered is the same as the password in the file.
     * @param  password_file String to indicate the password in the file.
     * @param  password_get String to indicate the password typed in.
     * @return  int
     *The return value indicates the result whether the password entered is the same as the password in the file.
     */
    public int judge_pass(String password_file, String password_get){
        if(password_file.equals(password_get)){
            return 1;
        }
        else{
            return 0;
        }
    }
    /**
     * Automatically assigned an account ID to customers or trainers when they sign up.
     * @param  I I String to indicate whether the identify is customer or trainer.
     * @return String
     *The return value indicates the account ID that automatically assigned.
     */
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
        return accNo;
    }
    /**
     * Return the customer or trainer's login information in the file.
     * @param  accNo_get String to indicate the customer or trainer's account ID typed in.
     * @return  ArrayList
     *The return value indicates the customer or trainer's login information containing account ID and password.
     */
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