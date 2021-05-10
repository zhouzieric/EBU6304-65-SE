package pages;

import bean.Booking;
import logic.*;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class CustomerCalendar extends JTabbedPane {

    private StandardFrame belongsToStandardFrame;
    private CusCalendarWhichWeek currentWeekCalendar;
    private CusCalendarWhichWeek nextWeekCalendar;

    public CustomerCalendar(StandardFrame belongsToStandardFrame) throws ParseException {
        super();
        this.belongsToStandardFrame=belongsToStandardFrame;
        setTabPlacement(JTabbedPane.LEFT);//默认在左，右边不好看
        currentWeekCalendar=new CusCalendarWhichWeek(CusCalendarWhichWeek.SHOW_CURRENT_WEEK,belongsToStandardFrame);
        nextWeekCalendar=new CusCalendarWhichWeek(CusCalendarWhichWeek.SHOW_NEXT_WEEK,belongsToStandardFrame);

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
