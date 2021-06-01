package logic;

import bean.Booking;
import bean.Lecture;
import bean.Questionnaire;

import javax.swing.*;
import java.awt.print.Book;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *This class is to read orders from booking.txt and lectures.txt through different screening methods.
 * @author Zhang Kezhou; Wang Heqing; Wang Pei
 * @version 4.0
 */
public class ReadOrder {
    /**
     * Read orders of the user from booking.txt.
     * @param  userId
     * userId of the current user
     * @param state
     * What status of booking information is needed
     * @return
     * The arrayList of Booking that contains the users booking information in specified state
     */
    public static ArrayList<Booking> getBookings(String userId, String state){
        String fileName = "src/main/java/data/booking.TXT";
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        BufferedReader bufferedReader = null;
        int CorT;

        if((userId.charAt(0)+"").equals("C")){
            CorT = 1;
        }else {
            System.out.println("读到是教练账号，正在搜索订单");
            CorT = 2;
        }
        try{
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String oneLine = null;
            //String oneLine = bufferedReader.readLine();
            //while(oneLine !=null){
            while ((oneLine = bufferedReader.readLine()) != null){
                String[] info = oneLine.split(";");

                if(info[CorT].equals(userId) && (info[4].equals(state) || state.equals("0"))){
                //if((info[1].equals(userId) && info[4].equals(state)) || (state.equals("0") && info[1].equals(userId))){
                    Booking b = new Booking();
                    Questionnaire q =new Questionnaire();

                    b.setBookingID(info[0]);
                    b.setCusID(info[1]);
                    b.setTraID(info[2]);
                    b.setCreatDate(info[3]);
                    b.setStatus(Integer.parseInt(info[4]));
                    b.setPrice(Double.parseDouble(info[5]));
                    q.setHeight(Double.parseDouble(info[6]));
                    q.setWeight(Double.parseDouble(info[7]));
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

    /**
     * Merge two arrayList.
     * @param  list1
     * the first list to be merged.
     * @param list2
     * the second list to be merged.
     * @return
     * The arrayList resulting from merging the two list.
     */
    public static ArrayList<Lecture> getMergeList(ArrayList<Lecture> list1, ArrayList<Lecture> list2) {
        ArrayList<Lecture> mergeList = new ArrayList<>();
        mergeList.addAll(list1);
        mergeList.addAll(list2);
        return mergeList;
    }

    /**
     * search the lectures of the customer and classify it
     * @param  CID
     * id of customer to be searched
     * @return  ArrayList[]
     * There are two arrays of lectures, the one for the current week, the one for the next week owned by the user,
     * and each week contains three types of lectures
     */

    //返回一个Arratlist数组，共有2个，当前周，下一周的该用户拥有的Lecture,每一周都包含三类lecture
    public static ArrayList[] twoWeekslectures(String CID) throws ParseException {

        //直接读出所有这个用户的bookingID
        ArrayList<Booking> paidBookings=ReadOrder.getBookings(CID,""+checkDateOut.STATE_PAID+"");
        ArrayList<Booking> ongoingBookings=ReadOrder.getBookings(CID,""+checkDateOut.STATE_ONGOING+"");
        ArrayList<Booking> finishedBookings=ReadOrder.getBookings(CID,checkDateOut.STATE_FINISHED+"");

        ArrayList<ArrayList<Booking>> AllBookingOfCustomer=new ArrayList<>();
        AllBookingOfCustomer.add(paidBookings);
        AllBookingOfCustomer.add(ongoingBookings);
        AllBookingOfCustomer.add(finishedBookings);
        //把所有lectures都读出来
        ArrayList<Lecture> allLectures=new ArrayList<>();
        for(ArrayList<Booking> BList:AllBookingOfCustomer){
            for(Booking B:BList){
                allLectures= getMergeList(allLectures,ReadOrder.getLectures(B.getBookingID()));
            }
        }
        //开始按日期把这周和下周的筛选出来
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<Lecture> currentWeek= new ArrayList<>();
        ArrayList<Lecture> nextWeek= new ArrayList<>();
        for(Lecture l:allLectures){
            Date date = simpleDateFormat.parse(l.getDate());
            Date nextMonday= simpleDateFormat.parse(getNextWeek.getNextWeekday(0));
            Date currentMonday= simpleDateFormat.parse(getNextWeek.getTodayWeekday(0,0));
            //Date1.before(Date2) 当Date1小于Date2时，返回TRUE; 当大于等于时，返回false
            if(date.before(nextMonday) && !date.before(currentMonday)) {
                currentWeek.add(l);
                // Date1.after(Date2) 当Date1大于Date2时，返回TRUE；当小于等于时，返回false
            }else if(!date.before(nextMonday)){  //?
//            }else if(date.after(nextMonday)){
                nextWeek.add(l);
            }
        }
        //判断lecture本身是什么状态
        ArrayList<ArrayList<Lecture>> currentWeekLectures= new ArrayList<>();
        ArrayList<Lecture> paidLectures = new ArrayList<>();
        ArrayList<Lecture> ongoingLectures = new ArrayList<>();
        ArrayList<Lecture> finishedLectures = new ArrayList<>();
        // add并非直接合并为一个数组 而是含三个数组的数组
        currentWeekLectures.add(paidLectures);
        currentWeekLectures.add(ongoingLectures);
        currentWeekLectures.add(finishedLectures);
        ArrayList<ArrayList<Lecture>> nextWeekLectures= new ArrayList<>();

        for (Lecture l : currentWeek) {
            Date date = simpleDateFormat.parse(l.getDate());
            Date now=new Date();

            String strStart=l.getDate()+" "+getNextWeek.getHourbyMark(l.getMark(),0);
            String strEnd=l.getDate()+" "+getNextWeek.getHourbyMark(l.getMark(),1);
//            Date dateStart= simpleDateFormat2.parse(l.getDate()+" "+strStart);
//            Date dateEnd= simpleDateFormat2.parse(l.getDate()+" "+strEnd);
            Date dateStart= simpleDateFormat2.parse(strStart);
            Date dateEnd= simpleDateFormat2.parse(strEnd);
           // System.out.println("==========================="+now);
           // System.out.println("==========================="+dateStart);
          //  System.out.println("==========================="+dateEnd);
            if (now.after(dateEnd) ) {
                //System.out.println("finishes"+l);
                finishedLectures.add(l);
            }else if (now.after(dateStart) && now.before(dateEnd)) {
                //System.out.println("onggonging"+l);
                ongoingLectures.add(l);
            }else{
                paidLectures.add(l);
            }
        }

        nextWeekLectures.add(nextWeek);
        nextWeekLectures.add(new ArrayList<>());
        nextWeekLectures.add(new ArrayList<>());

      //  System.out.println("分开日期后的本周lectures数组的数组中包含的三个lectures数组：");
        for(ArrayList<Lecture> outlecture:currentWeekLectures){
        //    System.out.println(outlecture);
        }
    //    System.out.println("分开日期后的下周lectures数组的数组中包含的三个lectures数组：");
        for(ArrayList<Lecture> outlecture:nextWeekLectures){
         //   System.out.println(outlecture);
        }


        ArrayList[] outcome=new ArrayList[2];

        outcome[0]=currentWeekLectures;
        outcome[1]=nextWeekLectures;
        return outcome;
    }
    /**
     * Search lectures by BID
     * @param  bookingId
     * The BID to be searched
     * @return  ArrayList<Lecture>
     * The corresponding lectures
     */
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

    /**
     * Check to see if a class was missed, in progress, or completed
     * @param  lectureTime
     * lecture mark of the lecture to be checked
     * @return  int
     * 1 is not started, 2 is ongoing, 3 is finished
     */
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
                case 0:
                    startTime = 900;
                    endTime = 1000;
                    break;
                case 1:
                    startTime = 1030;
                    endTime = 1130;
                    break;
                case 2:
                    startTime = 1400;
                    endTime = 1500;
                    break;
                case 3:
                    startTime = 1530;
                    endTime = 1630;
                    break;
                case 4:
                    startTime = 1900;
                    endTime = 2000;
                    break;
                case 5:
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
    /**
     * Cancel all the unfinished lectures of a certian booking and modify booking states in booking.txt.
     * @param  bookingId
     * bookingId
     */
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
                    if(i != bookings.size()-1)
                        pw.println(booking);
                    else
                        pw.print(booking);
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
                    if(i != bookings.size()-1)
                        pw.println(booking);
                    else
                        pw.print(booking);
                    pw.println(booking1);
                    cancelLecture(bookingId+"b");
                  //  System.out.println("2状态取消了");
                }else{
                    booking = bookings.get(i);
                    if(i != bookings.size()-1)
                        pw.println(booking);
                    else
                        pw.print(booking);
                }
            }
            pw.close();
            bw.close();
            fileWriter.close();

        }catch(IOException e){
            System.out.println("写新的订单有问题。");
        }
    }

    /**
     * Cancel all the unfinished lectures of a certian booking and modify lecture states in lectures.txt.
     * @param  bookingId
     * bookingId
     */

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

                    if(i != lectures.size()-1)
                        pw.println(lecture);
                    else
                        pw.print(lecture);
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

                    if(i != lectures.size()-1)
                        pw.println(lecture);
                    else
                        pw.print(lecture);
                }
            }
            pw.close();
            bw.close();
            fileWriter.close();

        }catch(IOException e){
            System.out.println("写新的课程有问题。");
        }
    }

    /**
     * Write the feedback to booking.txt
     * @param bookingId
     * bookingId
     * @param  feedback
     * User's comment to this booking
     * @param rank
     * User'rank to this booking, the rank is between 0 and 5
     */
    public static void addFeedback(String feedback, int rank, String bookingId){
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
                String[] info = bookings.get(i).split(";");
                //System.out.println(bookings.get(i));
                if(info[0].equals(bookingId)){
                    System.out.println("----------------------------------------------------------changed booking id, feed, rank:"+ bookingId + feedback + rank);
                    //System.out.println(info[11]);
                    if (info[11].equals("null")){
                        info[12] = feedback;
                        info[11] = rank+"";
                        for(int j=0;j<info.length;j++)
                            booking = booking + info[j] + ";";
                        //System.out.println(booking);
                    }else
                        booking = bookings.get(i);
                }else {
                    booking = bookings.get(i);
                }
                if(i != bookings.size()-1)
                    pw.println(booking);
                else
                    pw.print(booking);
            }
            pw.close();
            bw.close();
            fileWriter.close();

        }catch(IOException e){
            System.out.println("写新的订单有问题。");
        }
    }
    /**
     * Write contents of lectures to lectures.TXT.
     * @param b The order that lectures belongs to.
     * @param lecsConent The Arraylist of contents for all lectures in the order.
     */
    public static void addContent(Booking b, ArrayList<JTextField> lecsConent){
        ArrayList<String> lectures = new ArrayList<String>();
        String fileName = "src/main/java/data/lectures.TXT";

        try{
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
            System.out.println("Errors occured:加课程内容时，读文件有问题:"+e);
        }

        try {
            File fileNew = new File(fileName);
            FileWriter fileWriter = new FileWriter(fileNew);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bw);

            //System.out.println(lectures.size() + "-------------------------------------");

            int n = 0;
            for(int i=0;i<lectures.size();i++){
                String lecture = "";
                String[] info = lectures.get(i).split(";");
                //System.out.println(bookings.get(i));
                if(info[0].equals(b.getBookingID())){
                    //System.out.println("----------------------------------------------------------changed booking id, feed, rank:"+ bookingId + feedback + rank);
                    //System.out.println(info[11]);
                    info[1] = lecsConent.get(n).getText();
                    n++;
                    for(int j=0;j<info.length;j++)
                        lecture = lecture + info[j] + ";";
                    //System.out.println(booking);
                }else {
                    lecture = lectures.get(i);
                }

                if(i != lectures.size()-1)
                    pw.println(lecture);
                else
                    pw.print(lecture);
            }
            pw.close();
            bw.close();
            fileWriter.close();

        }catch(IOException e){
            System.out.println("写新的课程内容有问题。");
        }
    }

}
