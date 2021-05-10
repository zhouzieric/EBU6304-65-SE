package logic;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class checkDateOut {

    public static int STATE_PAID=1;
    public static int STATE_ONGOING=2;
    public static int STATE_FINISHED=3;
    public static int STATE_CONCELLED=4;

    private Date dateCurrent;
    private Timer[] timers=new Timer[7];
    private static int[] checkHourPoint={10,11,15,16,20,21};
    private static int[] checkMinatePoint={0,30,0,30,0,30};

    public checkDateOut(){//构造体出现时，各个定时器就已经就绪，构造完成无需调其他方法
        this.setACheck();//构造时执行了这个函数
    }

    public void setACheck(){//为每个还没到下课时间设定一个定时器，倒时候会检查一遍文件
                            // 这个函数执行时会执行一遍检查
        dateCurrent=new Date();
        Date compareDate=new Date();

        timers[6]=new Timer();
        timers[6].schedule(new updateTimerTask(),1000);

        for(int i=0;i<6;i++){
            compareDate.setHours(checkHourPoint[i]);
            compareDate.setMinutes(checkMinatePoint[i]);
            compareDate.setSeconds(0);
            if(dateCurrent.before(compareDate)){
                timers[i]=new Timer();
                timers[i].schedule(new updateTimerTask(),compareDate);//定时器需要执行的任务在下面内部类定义了
                System.out.println("创建了一个定时器，他是第"+(i+1)+"个");
            }
        }
    }

    private class updateTimerTask extends TimerTask {//这个类定义了每一个定时器要在每次下课做什么
        //以下是每一次验证都需要的数组，在此列出便于逻辑
        String[] allExpiredBIDs;
        int[] allExpirednum;

        String[] allcancelBIDs;
        int[] allcancelnum;

        ArrayList<String> needToBeONGOING=new ArrayList<>();
        ArrayList<String> needToBeFINISHED=new ArrayList<>();
        ArrayList<String> needToBeCONCELLED=new ArrayList<>();
        ArrayList<String> splitedBooking=new ArrayList<>();
        //结束

        @Override
        public void run(){//这是主执行函数，检查的步骤将会在下方一步步体现
            System.out.println(Thread.currentThread().getName()+"开始执行验证Booking状态是否正确!");
            //this.checkLectureExpired ();//把所有非取消的过期课拿出来放到 allExpired里
            //根据过期课读出所有相关booking
            ArrayList<String> allconcelString =takeOutLectures(true);
            ArrayList<String> allExpiredString =takeOutLectures(false);
            this.splitToTwoArray(true,allconcelString);
            int[] concelBIDTotalnum = seekTotalQuantity(allcancelBIDs);
            this.copmareToDecideStateC(allcancelBIDs,allcancelnum,concelBIDTotalnum);
            //开始处理非取消
            this.splitToTwoArray(false,allExpiredString);
            String[] newallExpiredBIDs=removeAFromB(allcancelBIDs,allExpiredBIDs);
            int[] newallExpirednum=updateRemovedBIDnum(allExpiredBIDs,allExpirednum,newallExpiredBIDs);
            int[] ExpiredBIDTotalnum=seekTotalQuantity(newallExpiredBIDs);
            this.copmareToDecideStateNC(newallExpiredBIDs,newallExpirednum,ExpiredBIDTotalnum);

            this.changeTheLabel();
            this.DoTheSplit();
        }

        //以下函数全是run中需要调用的函数

        public ArrayList<String> takeOutLectures(Boolean ifItIsCancelled){//返回被取消的所有lecture或者没被取消但已经过期的lecture
            ArrayList<String> theCancelledSet = new ArrayList<>();//已经被取消的所有lecture
            ArrayList<String> theExpiredNotCancelSet = new ArrayList<>();//已经过期但没被取消的lecture
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            try{
                String fileName = "src/main/java/data/lectures.TXT";
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String oneLine;
                while((oneLine = bufferedReader.readLine()) !=null){
                    Date now=new Date();
                   // System.out.println("读到的oneline"+oneLine);
                    String[] recorded = oneLine.split(";");
                    Date inhand=sdf.parse(recorded[3]);
                    int[] recordedXY=lectureNumToBox.changeToLocation(Integer.parseInt(recorded[2]));
                    inhand.setHours(checkHourPoint[recordedXY[0]]);
                    inhand.setMinutes(checkMinatePoint[recordedXY[0]]);
                    inhand.setSeconds(0);
                    if(recorded[4].equals("null")){
                        if(now.after(inhand)) {//现在的时间已经过了这节课结束的时间
                                theExpiredNotCancelSet.add(oneLine);
                               // System.out.println("这是没被取消但过期了的课:" + oneLine);
                        }
                        //没过期也不取消不用管

                    }else{
                        theCancelledSet.add(oneLine);
                       // System.out.println("这是被取消了的课:" + oneLine);
                    }
                }
//                System.out.println("现在循环输出数组内容：");
//                for(String in:theCancelledSet){
//                    System.out.println(Thread.currentThread().getName()+"====="+in+"/n");
//
//                }
                bufferedReader.close();
            }catch(IOException | ParseException e){
                System.out.println("Errors occured:获取取消和没取消但过期的课失败:"+e);
            }


        if(ifItIsCancelled){
            return theCancelledSet;

        }else if(!ifItIsCancelled){
            return theExpiredNotCancelSet;

        }
                return theCancelledSet;
        }//返回从lecture里找的被取消的所有lecture或者没被取消但已经过期的lecture

        public int[] seekTotalQuantity(String[] BIDs){//输入一些BID，它会返回每个BID一共有多少lecture，一一对应
            int[] total = new int[BIDs.length];
            try{
                String fileName = "src/main/java/data/lectures.TXT";
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String oneLine;
                while((oneLine = bufferedReader.readLine()) !=null){

                   // System.out.println("读到的oneline"+oneLine);
                    String[] recorded = oneLine.split(";");
                    for(int i=0;i<BIDs.length;i++) {
                        if(BIDs[i].equals(recorded[0])){
                            total[i]++;
                        }
                    }
                }
               // System.out.println("查询得到的个数：");
                for(int d:total){

                    System.out.println(d);
                }
                bufferedReader.close();
            }catch(IOException e){
                System.out.println("Errors occured:用BID查询总数失败:"+e);
            }



            return total;
        }//输入一些BID，它会返回每个BID有多少lecture，一一对应

        public String[] removeAFromB(String[] A, String[] B){//从B中去掉和A相同的元素
            String [] outcome;
            ArrayList<String> midChanger=new ArrayList<>();
            for(int i=0;i<B.length;i++){
                int flag=0;
                for(int j=0;j<A.length;j++){
                    if(B[i].equals(A[j])){flag=1;}

                }
                if(flag==0){midChanger.add(B[i]);}

            }
            int size=midChanger.size();
            outcome = midChanger.toArray(new String[size]);
          //  System.out.println("这是去掉之后的结果：");
            for(String d:outcome){
                System.out.println(d);
            }
            return outcome;
        }//从B中去掉和A相同的元素

        public int[] updateRemovedBIDnum(String[] oldBIDs,int[] oldnums,String[] newBIDs ){//当上面的removeAFromB返回【不含有任何取消的课的订单ID数组】时，这些订单对应的过期课个数统计也应该改正
            int[] outcome=new int[newBIDs.length];
            //ArrayList<Integer> midChanger= new ArrayList<>();

            for(int i=0;i<newBIDs.length;i++){
                for(int j=0;j<oldBIDs.length;j++){
                    if(newBIDs[i].equals(oldBIDs[j])){
                        outcome[i]=oldnums[j];
                    }
                }
            }
               // System.out.println("输出修改后的BID包含的lecture的已过期计数：");
                for(int d:outcome){
                    System.out.println(d);

                }
            return outcome;
        }//当上面的removeAFromB返回【不含有任何取消的课的订单ID数组】时，这些订单对应的过期课个数统计也应该改正

        public void splitToTwoArray(boolean ifItIsConcelled,ArrayList<String> OrignalStrings){//因为这个函数要填两个数组，所以直接在内部赋值，不返回了。这个函数要把独到的原始字符串提取BID和它拥有的lecture数
            ArrayList<String> midChanger= new ArrayList<>();

            for(String oneline:OrignalStrings){
                String[] recorded =oneline.split(";");
                if(!midChanger.contains(recorded[0])){
                    midChanger.add(recorded[0]);
                }

            }
            String[] getBIDs = midChanger.toArray(new String[midChanger.size()]);
            int[] getnum=new int[getBIDs.length];
            for(int h=0;h<getBIDs.length;h++){
                getnum[h]=0;
            }


            for(int j=0;j<getBIDs.length;j++){
                for(String oneline:OrignalStrings){

                    String[] recorded =oneline.split(";");
                     if(recorded[0].equals(getBIDs[j])){
                         getnum[j]++;

                     }
                }
            }
                if(ifItIsConcelled){
                    allcancelBIDs=getBIDs;
                    allcancelnum =getnum;
                }else{
                    allExpiredBIDs=getBIDs;
                    allExpirednum =getnum;
                }



        }//因为这个函数要填两个数组，所以直接在内部赋值，不返回了。这个函数要把独到的原始字符串提取BID和它拥有的lecture数

        public void copmareToDecideStateNC(String[] BIDs, int[] currentNums,int[] totalNums){//判断没取消部分时比较用的函数，将会自动把BID分到相应的字符数组

            for(int i=0;i<BIDs.length;i++){
                if(totalNums[i]==currentNums[i]){
                    needToBeFINISHED.add(BIDs[i]);
                }else{
                    needToBeONGOING.add(BIDs[i]);
                }
            }

        }//判断没取消部分时比较用的函数，将会自动把BID分到相应的字符数组

        public void copmareToDecideStateC(String[] BIDs, int[] currentNums,int[] totalNums){
            for(int i=0;i<BIDs.length;i++){
                if(totalNums[i]==currentNums[i]){
                    needToBeCONCELLED.add(BIDs[i]);
                }else{
                    needToBeFINISHED.add(BIDs[i]+"a");
                    needToBeCONCELLED.add(BIDs[i]+"b");
                    splitedBooking.add(BIDs[i]);
                }
            }
        }//当涉及到有取消时，负责把完全取消订单和半取消订单分类

        public void changeTheLabel(){//改状态,改的Booking.TXT
            ArrayList<String> backup= new ArrayList<>();
            try{
                String fileName = "src/main/java/data/booking.TXT";
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String oneLine;


                while((oneLine = bufferedReader.readLine()) !=null){
                    String[] recorded = oneLine.split(";");
                    //读到一行BID，将逐个类检查是属于哪个类然后直接重写
                    //先对要切开的订单进行比对
                    int flag=0;
                    for(String i_sb:splitedBooking){
                        if( i_sb.equals(recorded[0])){
                            String[] change2 = changeWhichToWhat(recorded,0,recorded[0]+"a").split(";");
                            backup.add(changeWhichToWhat(change2,4,""+STATE_FINISHED+""));
                            change2 = changeWhichToWhat(recorded,0,recorded[0]+"b").split(";");
                            backup.add(changeWhichToWhat(change2,4,""+STATE_CONCELLED+""));
                            flag=1;//以后等于1的都不再校对
                        }
                    }
                    //校对是否是ongoing
                    for(String i_og:needToBeONGOING){
                        if( i_og.equals(recorded[0])&&flag==0){
                            backup.add(changeWhichToWhat(recorded,4,""+STATE_ONGOING+""));
                            flag=1;//以后等于1的都不再校对
                        }
                    }
                    //校对是否是finished
                    for(String i_fi:needToBeFINISHED){
                        if( i_fi.equals(recorded[0])&&flag==0){
                            backup.add(changeWhichToWhat(recorded,4,""+STATE_FINISHED+""));
                            flag=1;//以后等于1的都不再校对
                        }
                    }
                    //校对是否是concelled
                    for(String i_co:needToBeCONCELLED){
                        if( i_co.equals(recorded[0])&&flag==0){
                            backup.add(changeWhichToWhat(recorded,4,""+STATE_CONCELLED+""));
                            flag=1;//以后等于1的都不再校对
                        }
                    }
                    if(flag==0){
                        backup.add(oneLine);
                    }
                }
                bufferedReader.close();
            }catch(IOException  e){
                System.out.println("Errors occured:备份失败"+e);
            }

            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/data/booking.TXT"));
                int count=0;
                for(String in:backup) {
                    bw.write(in);
                    count++;
                    if(count!= backup.size())
                    {bw.newLine();}
                }
                bw.close();

            }catch(IOException  e){
                System.out.println("Errors occured:创建失败"+e);
            }

//            File newfile=new File("src/main/java/data/bookingBackup.TXT");
//            File file = new File("src/main/java/data/booking.TXT");
//            newfile.renameTo(file);
            //file.delete();




        }//改状态

        public String changeWhichToWhat(String[] splitedString,int which,String what){//修改字符串某个值用，直接返回整个字符串
            String outcome="";
            for(int i=0;i<splitedString.length;i++){
                if(which==i){
                    outcome = outcome.concat(what);
                }else {
                    outcome = outcome.concat(splitedString[i]);
                }
                if(i!=(splitedString.length-1)) {
                    outcome = outcome.concat(";");
                }
            }
            return outcome;
        }//修改字符串某个值用，直接返回整个字符串

        public void DoTheSplit(){//到lecture.txt里把lecture对应的要拆分的BID改了
            ArrayList<String> backup= new ArrayList<>();
            try{
                String fileName = "src/main/java/data/lectures.TXT";
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String oneLine;
                while((oneLine = bufferedReader.readLine()) !=null){
                    String[] recorded = oneLine.split(";");
                    int flag=0;
                    for(String i:splitedBooking){
                        if(i.equals(recorded[0])&&recorded[4].equals("null")){
                            backup.add(recorded[0]+"a"+";"+recorded[1]+";"+recorded[2]+";"+recorded[3]+";"+recorded[4]);
                            flag=1;
                        }else if(i.equals(recorded[0])&&!recorded[4].equals("null")){
                            backup.add(recorded[0]+"b"+";"+recorded[1]+";"+recorded[2]+";"+recorded[3]+";"+recorded[4]);
                            flag=1;
                        }
                    }
                    if(flag==0){
                        backup.add(oneLine);
                    }
                }
                bufferedReader.close();
            }catch(IOException  e){
                System.out.println("Errors occured:备份失败"+e);
            }

            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/data/lectures.TXT"));
                int count=0;
               // System.out.println("大小："+backup.size());
                for(String in:backup) {
                    bw.write(in);
                    count++;
                    if(count!= backup.size())
                    {bw.newLine();}
                }
                bw.close();

            }catch(IOException  e){
                System.out.println("Errors occured:创建失败"+e);
            }

        }//到lecture.txt里把lecture对应的要拆分的BID改了



    }

}
