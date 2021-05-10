package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class searchVideos {

    public ArrayList<String[]> searchAllvideos(){
        ArrayList<String[]> outcome= new ArrayList<String[]>();

        String fileName ="src/main/java/data/videos.txt";
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();
            while(oneLine !=null){
                String[] info = oneLine.split(";");
                //String[] nameandformat ={info[0],info[1]};
                outcome.add(info);
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();

        }catch (IOException e){

            System.out.println("Errors occured:所有视频 没搜到:  "+e);
        }

        return outcome;


    }

    public String deleteHTMLBothSides(String input){
        String output;
        String[] a= input.split("<|>");
        output= a[2];
        return output;
    }
}
