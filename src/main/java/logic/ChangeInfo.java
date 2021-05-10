package logic;

import bean.Customer;
import bean.Trainer;

import java.io.*;
import java.lang.*;
import java.util.ArrayList;

public class ChangeInfo {
    private String accNo;
    private Customer cus;
    private Trainer tra;



    public ChangeInfo(String accNo){ this.accNo = accNo;}

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public Customer readCusInfo(){
        String fileName = openFile();
        String[] readFile;



        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            String oLine = br.readLine();

            if(oLine!=null){
                readFile = oLine.split(";");
                cus = new  Customer(readFile[2], readFile[9], readFile[3],readFile[4],readFile[5], readFile[0],readFile[6], readFile[7], readFile[8]);
                cus.setAccountNo(readFile[0]);
                cus.setPassword(readFile[1]);
                //cus.setDiscount(Double.parseDouble(readFile[10]));
            }else System.out.println("1Txt doesn't have any content");
            br.close();
            fileReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return this.cus;
    }

    public Customer changeCusInfo(int changeType, String changeCon){
        String fileName = openFile();

        try {
                File file = new File(fileName);
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                String oLine = br.readLine();
                String[] info;

                if(oLine!=null){
                    info = oLine.split(";");
                    if(changeType==1){
                        //change AccNo
                        cus.setAccountNo(changeCon);
                        info[0] = changeCon;
                        String[] fn = fileName.split("/");
                       // System.out.println(fn[2]);
                        fn[2] = changeCon+".txt";
                        fileName="";
                        for(int i = 0; i<fn.length;i++){
                            fileName = fileName.concat(fn[i]+"/");
                        }

                        //file.renameTo(new File(fileN));
                    }else if(changeType==2){
                        //change email
                        cus.setEmail_addr(changeCon);
                        info[7] = changeCon;
                    }else if(changeType==3){
                        //change phone num
                        cus.setPhone_num(changeCon);
                        info[6] = changeCon;
                    }else if(changeType==4){
                        //change password
                        cus.setPassword(changeCon);
                        info[1]=changeCon;

                    }else if(changeType==0){
                        //change membership
                        cus.setMembership(changeCon);
                        info[9]=changeCon;
                    }else if(changeType==5){
                        //change nickname
                        cus.setNickname(changeCon);
                        info[2]=changeCon;
                    }

                    oLine = "";
                    for(int i = 0; i<info.length;i++){
                        oLine = oLine + info[i]+";";
                    }

                    br.close();
                    fileReader.close();

                    file.delete();
                    File fileNew = new File(fileName);
                    FileWriter fileWriter = new FileWriter(fileNew);
                    BufferedWriter bw = new BufferedWriter(fileWriter);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.write(oLine);

                    pw.close();
                    bw.close();
                    fileWriter.close();

                }else System.out.println("2Txt doesn't have any content");



            }catch (IOException e){
                e.printStackTrace();
            }


        return  this.cus;
    }

    public Trainer readTraInfo(){
        String fileName = openFile();
        String[] readFile;

        tra = new Trainer();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            String oLine = br.readLine();

            if(oLine!=null){
                readFile = oLine.split(";");
                tra.setAccountNo(readFile[0]);
                tra.setPassword(readFile[1]);
                tra.setFname(readFile[2]);
                tra.setLname(readFile[3]);
                tra.setGender(readFile[4]);
                tra.setPhone_num(readFile[5]);
                tra.setEmail_addr(readFile[6]);
                tra.setDate_of_birth(readFile[7]);
                tra.setPro(readFile[8]);
                tra.setRank(Integer.parseInt(readFile[9]));

            }else System.out.println("3Txt doesn't have any content");

            br.close();
            fileReader.close();

        }catch(IOException e){
            e.printStackTrace();
        }

        return this.tra;
    }

    public Trainer changeTraInfo(int changeType, String changeCon){
        String fileName = openFile();

        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String oLine = br.readLine();
            String[] info;


            if(oLine!=null){
                info = oLine.split(";");
                if(changeType==1){
                    //change AccNo
                    tra.setAccountNo(changeCon);
                    info[0] = changeCon;
                    String[] fn = fileName.split("/");
                   // System.out.println(fn[2]);
                    fn[2] = changeCon+".txt";
                    fileName="";
                    for(int i = 0; i<fn.length;i++){
                        fileName = fileName.concat(fn[i]+"/");
                    }

                    //file.renameTo(new File(fileN));
                }else if(changeType==2){
                    //change email
                    tra.setEmail_addr(changeCon);
                    info[6] = changeCon;
                }else if(changeType==3){
                    //change phone num
                    tra.setPhone_num(changeCon);
                    info[5] = changeCon;
                }else if(changeType==4){
                    //change password
                    tra.setPassword(changeCon);
                    info[1] = changeCon;
                }

                oLine = "";
                for(int i = 0; i<info.length;i++){
                    oLine = oLine + info[i]+";";
                }

                br.close();
                fileReader.close();

                file.delete();
                File fileNew = new File(fileName);
                FileWriter fileWriter = new FileWriter(fileNew);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                PrintWriter pw = new PrintWriter(bw);

                pw.write(oLine);

                pw.close();
                bw.close();
                fileWriter.close();

            }else System.out.println("4Txt doesn't have any content");



        }catch (IOException e){
            e.printStackTrace();
        }


        return  this.tra;
    }

    public String openFile(){
        String initial = accNo.charAt(0)+"";
        String fileName = "src/main/java/data/";
        if(initial.equals("C")){
            fileName = fileName.concat("CInfo/"+accNo+".txt");
        }else if(initial.equals("T")){
            fileName = fileName.concat("TInfo/"+accNo+".txt");

        }else if(initial.equals("A")){

        }else{
            System.out.println("Not Legal Account No");
        }

        return fileName;
    }
}
