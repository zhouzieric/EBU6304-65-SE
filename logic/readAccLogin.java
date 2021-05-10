package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readAccLogin {

    public static String readFile(){
        String filename="src/main/java/data/acc_login.txt";
        //Acc: Read Account Info Logic
        String acc_login=null;
        try{
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String oneline=bufferedReader.readLine();
            acc_login = oneline.split("\r\n")[0];
            System.out.println(acc_login);  //

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return acc_login;
    }

    public static String WhatType(){
        String ID=readAccLogin.readFile();
        String type=null;
        type=ID.substring(0,1);
        System.out.println("现在的用户类型是："+"|"+type+"|");

        return type;
    }
}
