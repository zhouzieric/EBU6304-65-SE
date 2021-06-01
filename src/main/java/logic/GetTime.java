package logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *This class is to get system time.
 * @author Wang Pei
 * @version 3.0
 */
public class GetTime {
    /**
     * Get today's week index and time in specific form
     * @return  Integer Array.
     * The first value is the exact time of the system, format:hour+""+minuteStr.
     * The second value is the week index.
     */
    //获得今天的星期和时间
    public static Integer[] getTime(){
        Integer [] time= new Integer[2];
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        int week_index = c.get(Calendar.DAY_OF_WEEK)-1;
        //System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second);
        //System.out.println(week_index);
        String minuteStr;
        if(minute<=9)
            minuteStr="0"+minute;
        else
            minuteStr=minute+"";

        time[0]= Integer.parseInt(hour+""+minuteStr);
        time[1] = week_index;
        return time;
    }
    /**
     * Get the exact time of the system in standard form
     * @return  String.
     * The exact time of the system, format:yyyy/MM/dd HH:mm:ss.
     */
    public static String timeString(){
        //获得标准的时间用于写入课程取消掉的时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(now);
    }

//    public static void main(String args[]){
//        GetTime t = new GetTime();
//        t.getTime();
//    }
}
