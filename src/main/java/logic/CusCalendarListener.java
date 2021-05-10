package logic;

import bean.Lecture;
import pages.CusCalendarWhichWeek;
import pages.showAPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CusCalendarListener {


    public static class paidAndFinishedListener implements ActionListener {
        private CusCalendarWhichWeek cusCalendarWhichWeek;
        Lecture l;

        public paidAndFinishedListener(CusCalendarWhichWeek cusCalendarWhichWeek, Lecture l){
            this.cusCalendarWhichWeek=cusCalendarWhichWeek;
            this.l=l;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        //    System.out.println("这个lecture是"+l+"获得ID函数结果"+l.getBookingId());
            String[] BIDInfo=Match.getBInfoByBID(l.getBookingId());
            String LectureInfo="<html><font color=\"#A1887F\">Booking ID: </font>"+l.getBookingId()+"<br><font color=\"#A1887F\">Trainer name: </font>"+Match.getTrainerInfo(Match.getTIDByBID(l.getBookingId()))[0]+"<br><font color=\"#A1887F\">Content: </font>"+l.getContent()+"<br><font color=\"#A1887F\">order time: </font>"+BIDInfo[3]+"</html>";
            JOptionPane.showMessageDialog(this.cusCalendarWhichWeek,LectureInfo);
        }
    }

    public static class ongoingListener implements ActionListener {
        private CusCalendarWhichWeek cusCalendarWhichWeek;
        Lecture l;
        public ongoingListener(CusCalendarWhichWeek cusCalendarWhichWeek,Lecture l){
            this.cusCalendarWhichWeek=cusCalendarWhichWeek;
            this.l=l;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cardLayout=cusCalendarWhichWeek.getBelongsToStandardFrame().getCardManager();
            JPanel container= cusCalendarWhichWeek.getBelongsToStandardFrame().getCardContainer();
            showAPlayer showAPlayer= (showAPlayer)container.getComponent(6);
            showAPlayer.establishALivePlayer();
            cardLayout.show(container,"livePlayer");
        }
    }


}
