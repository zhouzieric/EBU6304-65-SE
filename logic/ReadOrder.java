package logic;

import bean.Booking;
import bean.Lecture;
import bean.Questionnaire;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadOrder {
    //查询订单信息
    public static ArrayList<Booking> getBookings(String userId, String state){
        String fileName = "src/main/java/data/booking.TXT";
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        BufferedReader bufferedReader = null;

        try{
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String oneLine = null;
            //String oneLine = bufferedReader.readLine();
            //while(oneLine !=null){
            while ((oneLine = bufferedReader.readLine()) != null){
                String[] info = oneLine.split(";");

                if(info[1].equals(userId) && (info[4].equals(state) || state.equals("0"))){
                //if((info[1].equals(userId) && info[4].equals(state)) || (state.equals("0") && info[1].equals(userId))){
                    Booking b = new Booking();
                    Questionnaire q =new Questionnaire();

                    b.setBookingID(info[0]);
                    b.setCusID(userId);
                    b.setTraID(info[2]);
                    b.setCreatDate(info[3]);
                    b.setStatus(Integer.parseInt(info[4]));
                    b.setPrice(Double.parseDouble(info[5]));
                    q.setWeight(Double.parseDouble(info[6]));
                    q.setHeight(Double.parseDouble(info[7]));
                    q.setTarget(info[8]);
                    q.setDetail(info[9]);
                    q.setHowOften(Integer.parseInt(info[10]));
                    b.setQuestionnaire(q);
                    if(!info[11].equals("null"))
                        b.setStar(Integer.parseInt(info[11]));
                    if(!info[12].equals("null"))
                        b.setFeedback(info[12]);
                   // System.out.println(b.toString());

                    bookings.add(b);
                }
                //oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(IOException e){
            System.out.println("Errors occured:查询订单有问题:"+e);
        }
        return bookings;
    }

    public static ArrayList<Lecture> getMergeList(ArrayList<Lecture> list1, ArrayList<Lecture> list2) {
        ArrayList<Lecture> mergeList = new ArrayList<>();
        mergeList.addAll(list1);
        mergeList.addAll(list2);
        return mergeList;
    }

    //返回一个Arratlist数组，共有2个，当前周，下一周的该用户拥有的Lecture,每一周都包含三类lecture
    public static ArrayList[] twoWeekslectures(String CID) throws ParseException {

        ArrayList<ArrayList<String>> AllBIDsOfCustomer=new ArrayList<>();//放的是下面Booking依次排序得3种ID，每种ID一个Arraylist

        ArrayList<Booking> paidBookings=ReadOrder.getBookings(CID,""+checkDateOut.STATE_PAID+"");
        ArrayList<Booking> ongoingBookings=ReadOrder.getBookings(CID,""+checkDateOut.STATE_ONGOING+"");
        ArrayList<Booking> finishedBookings=ReadOrder.getBookings(CID,checkDateOut.STATE_FINISHED+"");
//        for(Booking a:finishedBookings){
//            System.out.println("看看finished读的怎么样："+a.toString());
//        }
//        if(finishedBookings.size()>0){System.out.println("不是空的");}else{
//            System.out.println("是空的:"+finishedBookings.size());
//        }
        //ArrayList<Booking> concelledBookings=ReadOrder.getBookings(CID,""+checkDateOut.STATE_CONCELLED+"");
        ArrayList<ArrayList<Booking>> AllBookingOfCustomer=new ArrayList<>();
        AllBookingOfCustomer.add(paidBookings);
        AllBookingOfCustomer.add(ongoingBookings);
        AllBookingOfCustomer.add(finishedBookings);
       // AllBookingOfCustomer.add(concelledBookings);

        for(ArrayList<Booking> AB:AllBookingOfCustomer){
            ArrayList<String> IDs=new ArrayList<>();
            for(Booking B:AB){
                IDs.add(B.getBookingID());
               // System.out.println("这个是单个ID"+B.getBookingID());
            }


            AllBIDsOfCustomer.add(IDs);
        }
        System.out.println("下面是总booking数组的数组中包含的三个booking数组：");
        for(ArrayList<String> outBooking:AllBIDsOfCustomer){
            System.out.println(outBooking);
        }


        ArrayList<ArrayList<Lecture>> allLecturesOfCustomer=new ArrayList<>();//三种订单分别包含的lectures
        for(ArrayList<String> ABID:AllBIDsOfCustomer){
            ArrayList<Lecture> Ls= new ArrayList<>();

            for(String BID:ABID){
                Ls=getMergeList(Ls,ReadOrder.getLectures(BID));
            }
            allLecturesOfCustomer.add(Ls);
        }
        System.out.println("下面是总lectures数组的数组中包含的三个lectures数组：");
        for(ArrayList<Lecture> outlecture:allLecturesOfCustomer){
            System.out.println(outlecture);
        }
        ArrayList<ArrayList<Lecture>> currentWeekLectures= new ArrayList<>();
        ArrayList<ArrayList<Lecture>> nextWeekLectures= new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(ArrayList<Lecture> outlecture:allLecturesOfCustomer) {
            ArrayList<Lecture> currentOut=new ArrayList<>();
            ArrayList<Lecture> nextOut=new ArrayList<>();
            for(Lecture l:outlecture){
                Date date = simpleDateFormat.parse(l.getDate());
                Date nextMonday= simpleDateFormat.parse(getNextWeek.getNextWeekday(0));
                Date currentMonday= simpleDateFormat.parse(getNextWeek.getTodayWeekday(0));
                if(date.before(nextMonday)){
                    if(!date.before(currentMonday)){currentOut.add(l);}

                }else {
                    nextOut.add(l);
                }
            }
            currentWeekLectures.add(currentOut);
            nextWeekLectures.add(nextOut);
        }
        System.out.println("下面是分开日期后的lectures数组的数组中包含的三个lectures数组：");
        for(ArrayList<Lecture> outlecture:currentWeekLectures){
            System.out.println(outlecture);
        }
        for(ArrayList<Lecture> outlecture:nextWeekLectures){
            System.out.println(outlecture);
        }


        ArrayList[] outcome=new ArrayList[2];

        outcome[0]=currentWeekLectures;
        outcome[1]=nextWeekLectures;
        return outcome;
    }

    //查询课程信息
    public static ArrayList<Lecture> getLectures(String bookingId){
        String fileName = "src/main/java/data/lectures.TXT";
        ArrayList<Lecture> lectures = new ArrayList<Lecture>();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String oneLine;
            //while(oneLine !=null){
            while ((oneLine = bufferedReader.readLine()) != null){
                String[] info = oneLine.split(";");
                if(info[0].equals(bookingId)) {
                    Lecture lecture = new Lecture();

                    lecture.setBookingId(bookingId);
                    lecture.setContent(info[1]);
                    lecture.setMark(Integer.parseInt(info[2]));
                    lecture.setDate(info[3]);
                    lecture.setCancelDate(info[4]);

                    lectures.add(lecture);
                }
                //oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(IOException e){
            System.out.println("Errors occured:查询订单有问题:"+e);
        }
        return lectures;
    }


    //查看某节课是未上，正在进行还是已完成
    public static int isFinished(int lectureTime){
        //System.out.println("***********");
        //System.out.println(lectureTime);
        int lecDay = lectureTime/6 +1;
        int lecTime = lectureTime%6;
        //System.out.println(lecDay+";"+lecTime);

        int startTime=0;
        int endTime=0;

        GetTime get = new GetTime();
        Integer [] time = get.getTime();

        //System.out.println(time[0]+";"+time[1]);

        if(lecDay < time[1])   //1:未开始； 2：进行中  3：已结束
            return 3;
        else if (lecDay >time[1])
            return 1;
        else{
            switch (lecTime) {
                case 1:
                    startTime = 900;
                    endTime = 1000;
                    break;
                case 2:
                    startTime = 1030;
                    endTime = 1130;
                    break;
                case 3:
                    startTime = 1400;
                    endTime = 1500;
                    break;
                case 4:
                    startTime = 1530;
                    endTime = 1630;
                    break;
                case 5:
                    startTime = 1900;
                    endTime = 2000;
                    break;
                case 6:
                    startTime = 2030;
                    endTime = 2130;
                    break;
            }
            if(time[0]<startTime)
                return 1;
            else if(time[0]>endTime)
                return 3;
            else
                return 2;

        }
    }

    // 删除booking
    public static void cancelBooking(String bookingId){

        ArrayList<String> bookings = new ArrayList<String>();
        try{
            String fileName = "src/main/java/data/booking.TXT";
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String oneLine = bufferedReader.readLine();
            while(oneLine !=null){
                bookings.add(oneLine);
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            file.delete();
        }catch(IOException e){
            System.out.println("Errors occured:删除订单有问题:"+e);
        }
        try {
            File fileNew = new File("src/main/java/data/booking.TXT");
            FileWriter fileWriter = new FileWriter(fileNew);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bw);

            for(int i=0;i<bookings.size();i++){
                String booking="";
                String booking1="";
                String [] info = bookings.get(i).split(";");
                if(info[0].equals(bookingId) && info[4].equals("1")){
                    info[4]="4";
                    for(int j=0;j<info.length;j++)
                        booking = booking + info[j] + ";";
                    pw.println(booking);
                    cancelLecture(bookingId);
                  //  System.out.println("1状态取消啦");
                }else if(info[0].equals(bookingId) && info[4].equals("2")){
                    info[0]=bookingId+"a";
                    info[4] = "3";
                    for(int j=0;j<info.length;j++)
                        booking = booking + info[j] + ";";
                    info[0]=bookingId+"b";
                    info[4]="4";
                    for(int j=0;j<info.length;j++)
                        booking1 = booking1 + info[j] + ";";
                    pw.println(booking);
                    pw.println(booking1);
                    cancelLecture(bookingId+"b");
                  //  System.out.println("2状态取消了");
                }else{
                    booking = bookings.get(i);
                    pw.println(booking);
                }
            }
            pw.close();
            bw.close();
            fileWriter.close();

        }catch(IOException e){
            System.out.println("写新的订单有问题。");
        }
    }

    public static void cancelLecture(String bookingId){
        ArrayList<String> lectures = new ArrayList<String>();
        GetTime time = new GetTime();
        try{
            String fileName = "src/main/java/data/lectures.TXT";
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String oneLine = bufferedReader.readLine();
            while(oneLine !=null){
                lectures.add(oneLine);
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            file.delete();
        }catch(IOException e){
            System.out.println("Errors occured:删除课程有问题:"+e);
        }
        try {
            File fileNew = new File("src/main/java/data/lectures.TXT");
            FileWriter fileWriter = new FileWriter(fileNew);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bw);

           // System.out.println("BookingId:"+bookingId);
            if(bookingId.charAt(bookingId.length()-1)=='b'){
               // System.out.println("进来了b");
                for(int i=0;i<lectures.size();i++){
                    String lecture="";
                    String [] info = lectures.get(i).split(";");
                    if((info[0]+"b").equals(bookingId) && isFinished(Integer.parseInt(info[2])) == 1 ){
                       // System.out.println("课程为上");
                        info[0]=info[0]+"b";
                        info[4] = time.timeString();
                        for(int j=0;j<info.length;j++)
                            lecture = lecture+info[j]+";";
                    }else if((info[0]+"b").equals(bookingId)){
                       // System.out.println("课程以上");
                        info[0]=info[0]+"a";
                        for(int j=0;j<info.length;j++)
                            lecture = lecture+info[j]+";";
                    }else
                        lecture = lectures.get(i);

                    pw.println(lecture);
                }
            } else{
                for(int i=0;i<lectures.size();i++){
                    String lecture="";
                    String [] info = lectures.get(i).split(";");
                    if((info[0]).equals(bookingId)){
                        info[4] = time.timeString();
                        for(int j=0;j<info.length;j++)
                            lecture = lecture+info[j]+";";
                    }else
                        lecture = lectures.get(i);
                    pw.println(lecture);
                }
            }
            pw.close();
            bw.close();
            fileWriter.close();

        }catch(IOException e){
            System.out.println("写新的课程有问题。");
        }
    }

    //订单评价
    public static boolean addFeedback(String feedback, int rank, String bookingId){
        boolean isSuccess=false;
        ArrayList<String> bookings = new ArrayList<String>();
        try{
            String fileName = "src/main/java/data/booking.TXT";
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String oneLine = bufferedReader.readLine();
            while(oneLine !=null){
                bookings.add(oneLine);
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            file.delete();
        }catch(IOException e){
            System.out.println("Errors occured:加评论时，读订单有问题:"+e);
        }
        try {
            File fileNew = new File("src/main/java/data/booking.TXT");
            FileWriter fileWriter = new FileWriter(fileNew);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bw);

            for(int i=0;i<bookings.size();i++){
                String booking="";
                String [] info = bookings.get(i).split(";");
                if(info[0].equals(bookingId)){
                    if (info[11] .equals("null")){
                       // System.out.println(info[11]);
                        info[12]=feedback;
                        info[11] = rank+"";
                        for(int j=0;j<info.length;j++)
                            booking = booking + info[j] + ";";
                        isSuccess = true;
                    }else
                        booking = bookings.get(i);
                }else {
                    booking = bookings.get(i);
                }
                pw.println(booking);
            }
            pw.close();
            bw.close();
            fileWriter.close();

        }catch(IOException e){
            System.out.println("写新的订单有问题。");
        }
        return isSuccess;
    }
}
