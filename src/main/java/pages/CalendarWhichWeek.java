package pages;

import bean.Lecture;
import logic.*;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
/**
 * This page is used for users to check the calendar
 * @author Kezhou Zhang
 * @version 1.0.0
 */
public abstract class CalendarWhichWeek extends JPanel{
    public static int SHOW_CURRENT_WEEK=0;
    public static int SHOW_NEXT_WEEK=1;

    private JLabel tad = new JLabel("Time/Day");
    private JLabel w1 = new JLabel();
    private JLabel w2 = new JLabel();
    private JLabel w3 = new JLabel();
    private JLabel w4 = new JLabel();
    private JLabel w5 = new JLabel();
    private JLabel w6 = new JLabel();
    private JLabel w7 = new JLabel();

    private JLabel[] weektitle={tad,w1,w2,w3,w4,w5,w6,w7};
    private JLabel t1 = new JLabel("9:00-10:00");
    private JLabel t2 = new JLabel("10:30-11:30");
    private JLabel t3 = new JLabel("14:00-15:00");
    private JLabel t4 = new JLabel("15:30-16:30");
    private JLabel t5 = new JLabel("19:00-20:00");
    private JLabel t6 = new JLabel("20:30-21:30");
    private JButton[][] boxTable = new JButton[6][7];
    private JLabel[] timeTitle = {t1,t2,t3,t4,t5,t6};

    public static ColorUIResource LECTURE_ONGOING= MaterialColors.DEEP_ORANGE_700;
    public static ColorUIResource LECTURE_FINISHED=MaterialColors.BROWN_300;
    public static ColorUIResource LECTURE_PAID=MaterialColors.LIGHT_GREEN_400;
    public static ColorUIResource LECTURE_NONE=MaterialColors.BROWN_50;

    private StandardFrame belongsToStandardFrame;
    private String CID;


    /**
     * For constructing a calendar page.
     * @param  whichWeek
     * show calendar of which week:'SHOW_CURRENT_WEEK' or 'SHOW_NEXT_WEEK'
     * @param belongsToStandardFrame
     * the standardFrame associated with
     * @exception ParseException
     * An exception may be thrown during data format conversion
     */
    public CalendarWhichWeek(int whichWeek,StandardFrame belongsToStandardFrame) throws ParseException {
        this.belongsToStandardFrame=belongsToStandardFrame;
        Timer timeToUpdate=new Timer();
        timeToUpdate.schedule(new updateCalendarEveryMin(whichWeek),60000,60000);

        w1 .setText("<html>"+"Monday<br>"+ getNextWeek.getTodayWeekday(0,whichWeek)+"</html>");
        w2 .setText("<html>"+"Tuesday<br>"+getNextWeek.getTodayWeekday(1,whichWeek)+"</html>");
        w3 .setText("<html>"+"Wednesday<br>"+getNextWeek.getTodayWeekday(2,whichWeek)+"</html>");
        w4 .setText("<html>"+"Thursday<br>"+getNextWeek.getTodayWeekday(3,whichWeek)+"</html>");
        w5 .setText("<html>"+"Friday<br>"+getNextWeek.getTodayWeekday(4,whichWeek)+"</html>");
        w6 .setText("<html>"+"Saturday<br>"+getNextWeek.getTodayWeekday(5,whichWeek)+"</html>");
        w7 .setText("<html>"+"Sunday<br>"+ getNextWeek.getTodayWeekday(6,whichWeek)+"</html>");
        this.updateCalendar(whichWeek);

    }

    /**
     * Update the calendar
     * @param  whichWeek
     * show calendar of which week:'SHOW_CURRENT_WEEK' or 'SHOW_NEXT_WEEK'
     * @exception ParseException
     * An exception may be thrown during data format conversion
     */
    public void updateCalendar(int whichWeek) throws ParseException {
        this.removeAll();
        this.setLayout(new GridLayout(9,8,5,5));
        this.add(new JLabel(""));
        this.add(new JLabel("<html><font color=\"#9CCC65\">The color is of</font></html>"));
        this.add(new JLabel("<html><u color=\"#9CCC65\">Paid Lecture</u></html>"));
        this.add(new JLabel("<html><font color=\"#E64A19\">The color is of</font></html>"));
        this.add(new JLabel("<html><u color=\"#E64A19\">Ongoing Lecture</u></html>"));
        this.add(new JLabel("<html><font color=\"#a1887f\">The color is of</font></html>"));
        this.add(new JLabel("<html><u color=\"#a1887f\">Finished Lecture</u></html>"));
        this.add(new JLabel(""));


        CID="C"+ ReadandWrite.rFile("acc_login");

        for (JLabel w : weektitle) {

            this.add(w);
        }

        ArrayList[] twoweekslectues = ReadOrder.twoWeekslectures(readAccLogin.readFile());



        //显示出来就是数组顺序的初始化
        for (int x = 0; x < 6; x++) {
            this.add(timeTitle[x]);
            for (int y = 0; y < 7; y++) {
                boxTable[x][y] = new JButton("free time");//这改成具体显示的课
                boxTable[x][y].setForeground(LECTURE_NONE);
                boxTable[x][y].setEnabled(false);
                this.add(boxTable[x][y]);
                this.revalidate();
            }
        }
        for(int i=0;i<8;i++){
            this.add(new JLabel(""));
        }

    }
    /**
     * The calendar need to be update every minute
     * @author Kezhou Zhang
     * @version 1.0.0
     */

    private class updateCalendarEveryMin extends TimerTask {
        private int which;

        /**
         * construct the timetask towards the certain week
         * @param  which
         * show calendar of which week:'SHOW_CURRENT_WEEK' or 'SHOW_NEXT_WEEK'
         */
        public updateCalendarEveryMin(int which){
            this.which=which;
        }

        @Override
        public void run() {

            try {
                updateCalendar(which);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public StandardFrame getBelongsToStandardFrame() {
        return belongsToStandardFrame;
    }

    public void setBelongsToStandardFrame(StandardFrame belongsToStandardFrame) {
        this.belongsToStandardFrame = belongsToStandardFrame;
    }


    public JLabel getTad() {
        return tad;
    }

    public void setTad(JLabel tad) {
        this.tad = tad;
    }

    public JLabel getW1() {
        return w1;
    }

    public void setW1(JLabel w1) {
        this.w1 = w1;
    }

    public JLabel getW2() {
        return w2;
    }

    public void setW2(JLabel w2) {
        this.w2 = w2;
    }

    public JLabel getW3() {
        return w3;
    }

    public void setW3(JLabel w3) {
        this.w3 = w3;
    }

    public JLabel getW4() {
        return w4;
    }

    public void setW4(JLabel w4) {
        this.w4 = w4;
    }

    public JLabel getW5() {
        return w5;
    }

    public void setW5(JLabel w5) {
        this.w5 = w5;
    }

    public JLabel getW6() {
        return w6;
    }

    public void setW6(JLabel w6) {
        this.w6 = w6;
    }

    public JLabel getW7() {
        return w7;
    }

    public void setW7(JLabel w7) {
        this.w7 = w7;
    }

    public JLabel[] getWeektitle() {
        return weektitle;
    }

    public void setWeektitle(JLabel[] weektitle) {
        this.weektitle = weektitle;
    }

    public JLabel getT1() {
        return t1;
    }

    public void setT1(JLabel t1) {
        this.t1 = t1;
    }

    public JLabel getT2() {
        return t2;
    }

    public void setT2(JLabel t2) {
        this.t2 = t2;
    }

    public JLabel getT3() {
        return t3;
    }

    public void setT3(JLabel t3) {
        this.t3 = t3;
    }

    public JLabel getT4() {
        return t4;
    }

    public void setT4(JLabel t4) {
        this.t4 = t4;
    }

    public JLabel getT5() {
        return t5;
    }

    public void setT5(JLabel t5) {
        this.t5 = t5;
    }

    public JLabel getT6() {
        return t6;
    }

    public void setT6(JLabel t6) {
        this.t6 = t6;
    }

    public JButton[][] getBoxTable() {
        return boxTable;
    }

    public void setBoxTable(JButton[][] boxTable) {
        this.boxTable = boxTable;
    }

    public JLabel[] getTimeTitle() {
        return timeTitle;
    }

    public void setTimeTitle(JLabel[] timeTitle) {
        this.timeTitle = timeTitle;
    }

    public static ColorUIResource getLectureOngoing() {
        return LECTURE_ONGOING;
    }

    public static void setLectureOngoing(ColorUIResource lectureOngoing) {
        LECTURE_ONGOING = lectureOngoing;
    }

    public static ColorUIResource getLectureFinished() {
        return LECTURE_FINISHED;
    }

    public static void setLectureFinished(ColorUIResource lectureFinished) {
        LECTURE_FINISHED = lectureFinished;
    }

    public static ColorUIResource getLecturePaid() {
        return LECTURE_PAID;
    }

    public static void setLecturePaid(ColorUIResource lecturePaid) {
        LECTURE_PAID = lecturePaid;
    }

    public static ColorUIResource getLectureNone() {
        return LECTURE_NONE;
    }

    public static void setLectureNone(ColorUIResource lectureNone) {
        LECTURE_NONE = lectureNone;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }
}
