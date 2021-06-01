package pages;

import bean.Lecture;
import logic.*;

import javax.swing.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.TimerTask;
/**
 * This page is used for customers to check the calendar
 * @author Kezhou Zhang
 * @version 1.0.0
 */
public class CusCalendarWhichWeek extends CalendarWhichWeek {

    private JButton[][] boxTable = new JButton[6][7];

    /**
     * For constructing a calendar page.
     * @param  whichWeek
     * show calendar of which week:'SHOW_CURRENT_WEEK' or 'SHOW_NEXT_WEEK'
     * @param belongsToStandardFrame
     * the standardFrame associated with
     * @exception ParseException
     * An exception may be thrown during data format conversion
     */
    public CusCalendarWhichWeek(int whichWeek,StandardFrame belongsToStandardFrame) throws ParseException {
        super(whichWeek,belongsToStandardFrame);

    }
    /**
     * Update the calendar
     * @param  whichWeek
     * show calendar of which week:'SHOW_CURRENT_WEEK' or 'SHOW_NEXT_WEEK'
     * @exception ParseException
     * An exception may be thrown during data format conversion
     */
    @Override
    public void updateCalendar(int whichWeek) throws ParseException {
        System.out.println("开始初始化顾客课表！");
        super.updateCalendar(whichWeek);
        boxTable=this.getBoxTable();
        ArrayList[] twoweekslectues = ReadOrder.twoWeekslectures(readAccLogin.readFile());
        ArrayList<ArrayList<Lecture>> theWeekLectures = twoweekslectues[whichWeek];
        int i=1;
        for(ArrayList<Lecture> leclist:theWeekLectures){

            for(Lecture l:leclist){

                int[] location=lectureNumToBox.changeToLocation(l.getMark());
                boxTable[location[0]][location[1]].setEnabled(true);
                String content="<html>"+ReadandWrite.getName(Match.getTIDByBID(l.getBookingId()))+"<br>"+l.getContent()+"</html>";
                boxTable[location[0]][location[1]].setText(content);
                if(i==1){
                    boxTable[location[0]][location[1]].setForeground(CalendarWhichWeek.LECTURE_PAID);
                    boxTable[location[0]][location[1]].addActionListener(new CalendarListener.paidAndFinishedListener(this,l));
                }else if(i==2){
                    boxTable[location[0]][location[1]].setForeground(CalendarWhichWeek.LECTURE_ONGOING);
                    boxTable[location[0]][location[1]].addActionListener(new CalendarListener.ongoingListener(this,l));
                }else if(i==3){
                    boxTable[location[0]][location[1]].setForeground(CalendarWhichWeek.LECTURE_FINISHED);
                    boxTable[location[0]][location[1]].addActionListener(new CalendarListener.paidAndFinishedListener(this,l));
                }

            }
            i++;
        }

    }

}
