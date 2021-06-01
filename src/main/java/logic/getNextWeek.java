package logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class contains methods calculating date and time
 * @author Heqing Wang
 * @author Kezhou Zhang
 * @version 2.0
 */
public class getNextWeek {
    /** Get a number which Today plus will get this week's Monday
     * @return int Today plus which to get this Monday
     */
    public static int getNum(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(dayOfWeek == 0)  // if today is Sunday
            return -6;
        else
            return 1-dayOfWeek; //Monday:0 Tuesday:-1 Wednesday:-2 ... Sat: -5
    }

    /**
     * Get the date of a certain day of next week
     * @param i Identifier of which day of next week. (Monday:0 Tuesday:1 Wednesday:2 ... Sunday: 6)
     * @return String The date of a certain day of next week
     */
    public static String getNextWeekday(int i){
        int num = getNum();
        GregorianCalendar t = new GregorianCalendar();
        t.add(GregorianCalendar.DATE, num+7+i);
        Date nWeekday = t.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String d = formatter.format(nWeekday);
        return d;
    }

    /**
     * Get the date of a certain day of this/next week
     * @param i Identifier of which day of this/next week. (Monday:0 Tuesday:1 Wednesday:2 ... Sunday: 6)
     * @param whichWeek The Identifier of which week. (this week:0  next week:1)
     * @return String The date of a certain day of this/next week
     */
    public static String getTodayWeekday(int i,int whichWeek){
        String d = null;
        if(whichWeek==0) {
            int num = getNum();
            GregorianCalendar t = new GregorianCalendar();
            t.add(GregorianCalendar.DATE, num + i);
            Date nWeekday = t.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
           d = formatter.format(nWeekday);

        }else{
            d=getNextWeekday(i);
        }
        return d;
    }

    /**
     * Get the time period of a certain session.
     * @param i Identifier of which session. Each day has six sessions.
     *          Monday's six sessions:0-5; Tuesday' six sessions: 6-11 ... Sunday' six sessions: 36-41
     * @return String The time period of a certain session
     */
    public static String getTPbyMark(int i){
        // which session of one day (0:first 1:second ... 5:sixth
        int b = i % 6;

        String t = "";
        switch (b){
            case 0:
                t = "   09:00 - 10:00 am";
                break;
            case 1:
                t = "   10:30 - 11:30 am";
                break;
            case 2:
                t = "   14:00 - 15:00 pm";
                break;
            case 3:
                t = "   15:30 - 16:30 pm";
                break;
            case 4:
                t = "   19:00 - 20:00 pm";
                break;
            case 5:
                t = "   20:30 - 21:30 pm";
                break;
        }

        return t;
    }

    /**
     * Get the date and time period of a certain session.
     * @param i Identifier of which session. Each day has six sessions.
     *          Monday's six sessions:0-5; Tuesday' six sessions: 6-11 ... Sunday' six sessions: 36-41
     * @return String The date and time period of a certain session
     */
    public static String displayTP(int i){
        int a = i / 6;

        String d = getNextWeekday(a);
        String t = getTPbyMark(i);
        String tp = d + t;

        return tp;
    }

    /**
     * Get the start/end time of a certain session.
     * @param i Identifier of which session. Each day has six sessions.
     *          Monday's six sessions:0-5; Tuesday' six sessions: 6-11 ... Sunday' six sessions: 36-41
     * @param StartOrEnd The Identifier of whether start time or end time of a session is wanted. (start:1)
     * @return String The start/end time of a certain session.
     */
    public static String getHourbyMark(int i,int StartOrEnd){
        // which session of one day (0:first 1:second ... 5:sixth
        int b = i % 6;

        String t = "";
        if(StartOrEnd==1) {
            switch (b) {
                case 0:
                    t = "10:00:00";
                    break;
                case 1:
                    t = "11:30:00";
                    break;
                case 2:
                    t = "15:00:00";
                    break;
                case 3:
                    t = "16:30:00";
                    break;
                case 4:
                    t = "20:00:00";
                    break;
                case 5:
                    t = "21:30:00";
                    break;
            }
        }else{
            switch (b) {
                case 0:
                    t = "09:00:00";
                    break;
                case 1:
                    t = "10:30:00";
                    break;
                case 2:
                    t = "14:00:00";
                    break;
                case 3:
                    t = "15:30:00";
                    break;
                case 4:
                    t = "19:00:00";
                    break;
                case 5:
                    t = "20:30:00";
                    break;
            }
        }
        return t;
    }
}
