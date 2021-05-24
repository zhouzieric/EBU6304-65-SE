package logic;

import bean.Lecture;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Match{

    public ArrayList<String> get_trainers(String target){//得到教练们的ID
        ArrayList<String> trainers = new ArrayList<String>();
        try{
            String fileName = "src/main/java/data/"+target+"_trainer.TXT";
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();
            while(oneLine !=null){
                //System.out.println(oneLine);
                trainers.add(oneLine);
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(IOException e){
            System.out.println("Errors occured:找教练有问题:"+e);
        }
        for(int i=0;i<trainers.size();i++) {

            System.out.println(i + " " + trainers.get(i));
            ArrayList<String> a=get_lectures(trainers.get(i));
        }

        return trainers;
    }

    public static String getTIDByBID(String BID){
        String TID=null;
        try{
            String fileName = "src/main/java/data/booking.TXT";
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();
            while(oneLine !=null){
                String[] info = oneLine.split(";");
             //   System.out.println(oneLine);
                if(info[0].equals(BID)){
                    TID=info[2];
                }
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();

        }catch(IOException e){
            System.out.println("为课程读取教练ID失败  "+e);
        }
        return TID;
    }

    public static String[] getBInfoByBID(String BID){
        int flag=0;
        String[] BInfo=null;
        String fileName ="src/main/java/data/booking.TXT";
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();

            //System.out.println(oneLine);
           // System.out.println("BID是"+BID);
            while(oneLine !=null&&flag==0){
                String[] info = oneLine.split(";");
                if(info[0].equals(BID)){
                   System.out.println("查到的inf【0】"+info[0]);
                    BInfo=info;
                   flag=1;
                }
                oneLine = bufferedReader.readLine();
            }

            oneLine = bufferedReader.readLine();
           // System.out.println("课表里的BInfo"+BInfo);
            bufferedReader.close();

        }catch (IOException e){

            System.out.println("Errors occured:课程表里订单信息 没搜到:  "+e);
        }

        return BInfo;

    }

    public static String[] getTrainerInfo(String TID){

        String[] TrainerInfo=new String[3];
        String fileName ="src/main/java/data/TInfo/"+TID+".txt";
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();
            String[] info = oneLine.split(";");
                //System.out.println(oneLine);
                if(info[0].equals(TID)&&oneLine !=null){
                    TrainerInfo[0]=info[2]+" "+info[3];
                    TrainerInfo[1]=info[8];
                    TrainerInfo[2]=info[9];
                    System.out.println("搜到的教练是"+TrainerInfo[0]+TrainerInfo[1]+TrainerInfo[2]);
                }
                oneLine = bufferedReader.readLine();

            bufferedReader.close();

        }catch (IOException e){

            System.out.println("Errors occured:教练信息 没搜到:  "+e);
        }

            return TrainerInfo;

    }

    public ArrayList<String> get_lectures(String trainerId){//先读booking再读lecture
        ArrayList<String> bookingList = new ArrayList<String>();//
        ArrayList<String> timeList = new ArrayList<String>();//教练已经被占用的时间，是课程编号
        try{
            String fileName = "src/main/java/data/booking.TXT";
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();
            while(oneLine !=null){
                String[] info = oneLine.split(";");
               // System.out.println(oneLine);
                if(info[2].equals(trainerId)){
                    bookingList.add(info[0]);
                    //System.out.println(info[2]);
                }
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();

        }catch(IOException e){
            System.out.println("Errors occured:booking 没搜到:  "+e);
        }

        try{
            String fileName = "src/main/java/data/lectures.TXT";
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();
            while(oneLine !=null){
                String[] info = oneLine.split(";");
                //System.out.println(oneLine);
                String bookingId = info[0];
                for(int i=0;i<bookingList.size();i++){
                    if(bookingId.equals(bookingList.get(i)))
                        timeList.add(info[2]);
                }
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(IOException e){
            System.out.println("Errors occured:读lecture时间有问题："+e);
        }
       // System.out.println(trainerId);
//        for(int i =0;i<timeList.size();i++)
//            System.out.println(timeList.get(i));//

        return timeList;
    }

    public ArrayList<String> get_NextWeeklectures(String trainerId){//先读booking再读lecture,订课用
        ArrayList<String> bookingList = new ArrayList<String>();//
        ArrayList<String> timeList = new ArrayList<String>();//教练已经被占用的时间，是课程编号
        try{
            String fileName = "src/main/java/data/booking.TXT";
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine ;
            while((oneLine= bufferedReader.readLine()) !=null){
                String[] info = oneLine.split(";");
                // System.out.println(oneLine);
                if(info[2].equals(trainerId)){
                    bookingList.add(info[0]);
                    //System.out.println(info[2]);
                }

            }
            bufferedReader.close();

        }catch(IOException e){
            System.out.println("Errors occured:booking 没搜到:  "+e);
        }

        try{
            String fileName = "src/main/java/data/lectures.TXT";
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();
            while(oneLine !=null){
                String[] info = oneLine.split(";");
                //System.out.println(oneLine);
                String bookingId = info[0];
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //System.out.println("搜到的日期"+info[3]);
                Date theDate=simpleDateFormat.parse(info[3]);
                Date nextMonday= simpleDateFormat.parse(getNextWeek.getNextWeekday(0));


                for(int i=0;i<bookingList.size();i++){

                    if(bookingId.equals(bookingList.get(i))&&!theDate.before(nextMonday))
                        timeList.add(info[2]);
                }
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(IOException | ParseException e){
            System.out.println("Errors occured:订课时，读lecture时间有问题："+e);
        }
        // System.out.println(trainerId);
//        for(int i =0;i<timeList.size();i++)
//            System.out.println(timeList.get(i));//

        return timeList;
    }

    public static Boolean checkIfinflict(ArrayList<Integer> selectedLecturesNum) throws ParseException {
        Boolean ifitis=false;
        ArrayList[] Alllectures=ReadOrder.twoWeekslectures(readAccLogin.readFile());
        ArrayList<ArrayList<Lecture>> lectures=Alllectures[1];
        for(ArrayList<Lecture> ls:lectures){
            for(int n:selectedLecturesNum){
                for(Lecture l:ls){
                    if(l.getMark()==n){
                        ifitis=true;
                    }
                }

            }
        }
        return ifitis;
    }

    public static ArrayList<Integer> CustomerIsNotFree() throws ParseException {
        ArrayList[] Alllectures=ReadOrder.twoWeekslectures(readAccLogin.readFile());
        ArrayList<ArrayList<Lecture>> lectures=Alllectures[1];
        ArrayList<Lecture> merged= new ArrayList<>();
        for(ArrayList<Lecture> ls:lectures){
            merged=ReadOrder.getMergeList(merged,ls);
        }
        ArrayList<Integer> outcome=new ArrayList<>();
        for(Lecture l:merged){
            outcome.add(l.getMark());
        }
        return outcome;
    }
  }