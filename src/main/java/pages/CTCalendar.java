package pages;

import bean.Booking;
import logic.*;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
/**
 *This page is to show two weeks' calendar.
 * @author Kezhou Zhang
 * @version 1.0.0
 */
public class CTCalendar extends JTabbedPane {

    private StandardFrame belongsToStandardFrame;
    private CalendarWhichWeek currentWeekCalendar;
    private CalendarWhichWeek nextWeekCalendar;


    public static int CALENDAR_CUSTOMER=0;
    public static int CALENDAR_TRAINER=1;
    /**
     * For constructing a object.
     * @param belongsToStandardFrame
     * the standardFrame associated with
     * @param type
     * the type of calendar:'C' or 'T'
     * @exception  ParseException
     * An exception may be thrown during data format conversion
     */
    public CTCalendar(StandardFrame belongsToStandardFrame, int type) throws ParseException {
        super();
        this.belongsToStandardFrame=belongsToStandardFrame;
        setTabPlacement(JTabbedPane.LEFT);//默认在左，右边不好看
        if(type==CALENDAR_CUSTOMER) {
            System.out.println("得知是顾客课表！");
            currentWeekCalendar = new CusCalendarWhichWeek(CalendarWhichWeek.SHOW_CURRENT_WEEK, belongsToStandardFrame);
            nextWeekCalendar = new CusCalendarWhichWeek(CalendarWhichWeek.SHOW_NEXT_WEEK, belongsToStandardFrame);
        }else{
            System.out.println("得知是教练课表！");
            currentWeekCalendar = new TraCalendarWhichWeek(CalendarWhichWeek.SHOW_CURRENT_WEEK, belongsToStandardFrame);
            nextWeekCalendar = new TraCalendarWhichWeek(CalendarWhichWeek.SHOW_NEXT_WEEK, belongsToStandardFrame);
        }
        this.addTab("This Week",currentWeekCalendar);
        this.addTab("Next Week",nextWeekCalendar);
    }

    public StandardFrame getBelongsToStandardFrame() {
        return belongsToStandardFrame;
    }

    public void setBelongsToStandardFrame(StandardFrame belongsToStandardFrame) {
        this.belongsToStandardFrame = belongsToStandardFrame;
    }
}
