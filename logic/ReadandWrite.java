package logic;

import java.io.*;
import java.util.Scanner;

public class ReadandWrite {
    //static String baseurl = "G:\\FirstMaven\\src\\main\\java\\data\\";
    static String baseurl = "src/main/java/data/";

    public static int rFile(String fileName){
        String file = baseurl + fileName + ".TXT";
        Scanner scanner = null;
        String lastID = null;
        try{
            scanner = new Scanner(new FileReader(file));
            String tempString = null;
            while((scanner.hasNextLine()&&(tempString=scanner.nextLine())!=null)){
                if(!scanner.hasNextLine())
                    lastID =  tempString.split(";")[0];
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        int lastNum;
        if(lastID.substring(lastID.length()-1,lastID.length()).equals("a") || lastID.substring(lastID.length()-1,lastID.length()).equals("b") )
            lastNum = Integer.parseInt(lastID.substring(1,lastID.length()-1));
        else
            lastNum = Integer.parseInt(lastID.substring(1,lastID.length()));
        return lastNum;
    }

    public static void wFile(String fileName, String aRow){
        String file = baseurl + fileName + ".TXT";
        BufferedWriter bw = null;

        try{
                bw = new BufferedWriter(new FileWriter(file, true));
                bw.write("\n");
                bw.write(aRow);

        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getName(String oneID){
        String init=oneID.charAt(0)+"";
        String    file = baseurl + init + "Info/" + oneID + ".TXT";

        BufferedReader br;
        String l = "";
        try{
            br = new BufferedReader(new FileReader(file));
            l = br.readLine();
            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        if((l.charAt(0)+"").equals("C"))
            return l.split(";")[3] + " " + l.split(";")[4];
        else
            return l.split(";")[2] + " " + l.split(";")[3];
    }


}
