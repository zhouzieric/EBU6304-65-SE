package logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class getNextWeek {
    public static int getNum(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(dayOfWeek == 0) //今天是周天
            return -6;
        else
            return 1-dayOfWeek; //周二：-1 周三-2 ... 周天：1
    }

    public static String getNextWeekday(int i){  //仅返回日期
        int num = getNum();
        GregorianCalendar t = new GregorianCalendar();
        t.add(GregorianCalendar.DATE, num+7+i); //i从0-6 要算下周几
        Date nWeekday = t.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String d = formatter.format(nWeekday);
        return d;
    }

    public static String getTodayWeekday(int i){
        int num = getNum();
        GregorianCalendar t = new GregorianCalendar();
        t.add(GregorianCalendar.DATE, num+i);
        Date nWeekday = t.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String d = formatter.format(nWeekday);
        return d;


    }

    public static String getTPbyMark(int i){  //仅返回时间
        //
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

    public static String displayTP(int i){  //返回日期+时间
        //
        int a = i / 6;  //a从0-6

        String d = getNextWeekday(a);
        String t = getTPbyMark(i);
        String tp = d + t;

        return tp;
    }

}
