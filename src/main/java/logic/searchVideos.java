package logic;

import bean.Video;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class searchVideos {
    /**
     * Search all videos information
     * @return  ArrayList
     * All videos' information
     */
    public static ArrayList<String[]> searchAllvideos(){
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
    /**
     * Search videos by type and membership
     * @param  type
     * the video type want to be searched
     * @return  ArrayList
     * The corresponding videos
     */
    public static ArrayList<Video> searchVideosBytypeAndMembership(int type){
        ArrayList<Video> outcome= new ArrayList<>();

        String fileName ="src/main/java/data/videos.txt";
        // String[] types=ReadFlexibleInfo.readFile(4).split(";");

        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine;
            while((oneLine= bufferedReader.readLine()) !=null){
                String[] info = oneLine.split(";");
                if(type==Video.CATEGORY_ALL&&(ReadFlexibleInfo.getIntByStr(new ChangeInfo(readAccLogin.readFile()).readCusInfo().getMembership()))<=Integer.parseInt(info[5])){
                    outcome.add(new Video(info[0],info[1],info[2],Integer.parseInt(info[3]),info[4],Integer.parseInt(info[5])));
                }else if (new ChangeInfo(readAccLogin.readFile()).readCusInfo().getMembership().equals(ReadFlexibleInfo.readFile(2).split(";")[0])){
                    outcome.add(new Video(info[0],info[1],info[2],Integer.parseInt(info[3]),info[4],Integer.parseInt(info[5])));
                }else if (type==Integer.parseInt(info[3])&&(ReadFlexibleInfo.getIntByStr(new ChangeInfo(readAccLogin.readFile()).readCusInfo().getMembership()))<=Integer.parseInt(info[5])){
                    outcome.add(new Video(info[0],info[1],info[2],Integer.parseInt(info[3]),info[4],Integer.parseInt(info[5])));
                }
            }
            bufferedReader.close();

        }catch (IOException e){

            System.out.println("Errors occured:所有视频 没搜到:  "+e);
        }

        return outcome;


    }
    /**
     * Search videos by names
     * @param  names
     * the video type want to be searched
     * @return  ArrayList
     * The corresponding videos
     */
    public static ArrayList<Video> searchVideosByName(ArrayList<String> names){
        ArrayList<Video> outcome= new ArrayList<>();

        String fileName ="src/main/java/data/videos.txt";
        // String[] types=ReadFlexibleInfo.readFile(4).split(";");

        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine;
            while((oneLine= bufferedReader.readLine()) !=null){
                String[] info = oneLine.split(";");
                for(String name:names){
                    if(name.equals(info[0])){
                        outcome.add(new Video(info[0],info[1],info[2],Integer.parseInt(info[3]),info[4],Integer.parseInt(info[5])));
                    }
                }
            }
            bufferedReader.close();

        }catch (IOException e){

            System.out.println("Errors occured:search视频 没搜到:  "+e);
        }

        return outcome;

    }
    /**
     * To delete the 'html' string
     * @param  input
     * the string need to be modified
     * @return  String
     * The modified string
     */
    public static String deleteHTMLBothSides(String input){
        String output;
        String[] a= input.split("<|>");
        output= a[2];
        return output;
    }
}
