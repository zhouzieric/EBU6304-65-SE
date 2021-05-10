package pages;

import bean.Booking;
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

public class CusCalendarWhichWeek extends JPanel {

    public static int SHOW_CURRENT_WEEK=0;
    public static int SHOW_NEXT_WEEK=1;

    private JLabel tad = new JLabel("Time/Day");
    private JLabel w1 = new JLabel("<html>"+"Monday<br>"+ getNextWeek.getTodayWeekday(0)+"</html>");
    private JLabel w2 = new JLabel("<html>"+"Tuesday<br>"+getNextWeek.getTodayWeekday(1)+"</html>");
    private JLabel w3 = new JLabel("<html>"+"Wednesday<br>"+getNextWeek.getTodayWeekday(2)+"</html>");
    private JLabel w4 = new JLabel("<html>"+"Thursday<br>"+getNextWeek.getTodayWeekday(3)+"</html>");
    private JLabel w5 = new JLabel("<html>"+"Friday<br>"+getNextWeek.getTodayWeekday(4)+"</html>");
    private JLabel w6 = new JLabel("<html>"+"Saturday<br>"+getNextWeek.getTodayWeekday(5)+"</html>");
    private JLabel w7 = new JLabel("<html>"+"Sunday<br>"+ getNextWeek.getTodayWeekday(6)+"</html>");

    private JLabel[] weektitle={tad,w1,w2,w3,w4,w5,w6,w7};
    private JLabel t1 = new JLabel("9:00-10:00");
    private JLabel t2 = new JLabel("10:30-11:30");
    private JLabel t3 = new JLabel("14:00-15:00");
    private JLabel t4 = new JLabel("15:30-16:30");
    private JLabel t5 = new JLabel("19:00-20:00");
    private JLabel t6 = new JLabel("20:30-21:30");
    private JButton[][] boxTable = new JButton[6][7];
    private JLabel[] timeTitle = {t1,t2,t3,t4,t5,t6};

    private static ColorUIResource LECTURE_ONGOING= MaterialColors.DEEP_ORANGE_700;
    private static ColorUIResource LECTURE_FINISHED=MaterialColors.BROWN_300;
    private static ColorUIResource LECTURE_PAID=MaterialColors.LIGHT_GREEN_400;
    private static ColorUIResource LECTURE_NONE=MaterialColors.BROWN_50;

    private StandardFrame belongsToStandardFrame;
    private String CID;



    public CusCalendarWhichWeek(int whichWeek,StandardFrame belongsToStandardFrame) throws ParseException {
        this.belongsToStandardFrame=belongsToStandardFrame;
        Timer timeToUpdate=new Timer();
        timeToUpdate.schedule(new updateCalendarEveryMin(whichWeek),60000,60000);
        this.updateCalendar(whichWeek);

    }

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


        ArrayList<ArrayList<Lecture>> theWeekLectures = twoweekslectues[whichWeek];
        for(ArrayList<Lecture> leclist:theWeekLectures){
            int i=1;
            for(Lecture l:leclist){

                int[] location=lectureNumToBox.changeToLocation(l.getMark());
                boxTable[location[0]][location[1]].setEnabled(true);
                String content="<html>"+ReadandWrite.getName(Match.getTIDByBID(l.getBookingId()))+"<br>"+l.getContent()+"</html>";
                boxTable[location[0]][location[1]].setText(content);
                if(i==1){
                    boxTable[location[0]][location[1]].setForeground(LECTURE_PAID);
                    boxTable[location[0]][location[1]].addActionListener(new CusCalendarListener.paidAndFinishedListener(this,l));
                }else if(i==2){
                    boxTable[location[0]][location[1]].setForeground(LECTURE_ONGOING);
                    boxTable[location[0]][location[1]].addActionListener(new CusCalendarListener.ongoingListener(this,l));
                }else if(i==3){
                    boxTable[location[0]][location[1]].setForeground(LECTURE_FINISHED);
                    boxTable[location[0]][location[1]].addActionListener(new CusCalendarListener.paidAndFinishedListener(this,l));
                }

            }
            i++;
        }

    }
    private class updateCalendarEveryMin extends TimerTask{
        private int which;

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
}
