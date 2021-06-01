package logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains methods reading or writing booking.TXT, lectures.TXT, and notice.TXT.
 * @author Heqing Wang
 * @author Kezhou Zhang
 * @version 2.0
 */
public class ReadandWrite {
    static String baseurl = "src/main/java/data/";

    /**
     * Get the number of the last record in the file.
     * @param fileName Identifier of which file wanted to read.
     * @return int The number of the last record in the file.
     */
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

    /**
     * Write a new line in one file.
     * @param fileName Identifier of which file wanted to write.
     * @param aRow The new row to be written.
     */
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

    /**
     * Get the name of someone.
     * @param oneID The one's ID.
     * @return String The name of that person.
     */
    public static String getName(String oneID){
        String init=oneID.charAt(0)+"";
        String file = baseurl + init + "Info/" + oneID + ".TXT";

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

    /**
     * Get the Notice administrator published.
     * @return String The Notice administrator published.
     */
    public static String readNotice(){
        String notice= "";
        String filename = "src/main/java/data/notice.txt";
        ArrayList<String> rowData = new ArrayList<>();

        try{
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String oneline=bufferedReader.readLine();

            while (oneline!=null){
                notice = notice+oneline;
                oneline=bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return notice;
    }

}
