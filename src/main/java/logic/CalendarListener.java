package logic;

import bean.Lecture;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import pages.CalendarWhichWeek;
import pages.CusCalendarWhichWeek;
import pages.showAPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 *A collection of listeners for CalendarView.
 * @author Kezhou Zhang
 * @version 1.0.0
 */
public class CalendarListener {
    /**
     * A listener for a button in CalendarView.
     * The button is to tackle the paid and the finished lectures.
     * @author Kezhou Zhang
     * @version 1.0.0
     */

    public static class paidAndFinishedListener implements ActionListener {
        private CalendarWhichWeek calendarWhichWeek;
        Lecture l;
        /**
         * For constructing a listener.
         * @param  calendarWhichWeek
         * to identify the CalendarWhichWeek object listened to.
         * @param l
         * the lecture whose information will be shown
         */
        public paidAndFinishedListener(CalendarWhichWeek calendarWhichWeek, Lecture l){
            this.calendarWhichWeek=calendarWhichWeek;
            this.l=l;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        //    System.out.println("这个lecture是"+l+"获得ID函数结果"+l.getBookingId());
            String[] BIDInfo=Match.getBInfoByBID(l.getBookingId());
            String LectureInfo="Information error";
            if(readAccLogin.WhatType().equals("T")) {
                 LectureInfo = "<html><font color=\"#A1887F\">Booking ID: </font>" + l.getBookingId() + "<br><font color=\"#A1887F\">Student name: </font>" + ReadandWrite.getName(Match.getBInfoByBID(l.getBookingId())[1]) + "<br><font color=\"#A1887F\">Content: </font>" + l.getContent() + "<br><font color=\"#A1887F\">order time: </font>" + BIDInfo[3] + "</html>";
            }else{

                LectureInfo = "<html><font color=\"#A1887F\">Booking ID: </font>" + l.getBookingId() + "<br><font color=\"#A1887F\">Trainer name: </font>" + Match.getTrainerInfo(Match.getTIDByBID(l.getBookingId()))[0] + "<br><font color=\"#A1887F\">Content: </font>" + l.getContent() + "<br><font color=\"#A1887F\">order time: </font>" + BIDInfo[3] + "</html>";
            }


            JOptionPane.showMessageDialog(this.calendarWhichWeek,LectureInfo);
        }
    }
    /**
     * A listener for a button in CalendarView.
     * The button is to tackle the ongoing lectures.
     * @author Kezhou Zhang
     * @version 1.0.0
     */
    public static class ongoingListener implements ActionListener {
        private CalendarWhichWeek calendarWhichWeek;
        Lecture l;
        /**
         * For constructing a listener.
         * @param  calendarWhichWeek
         * to identify the CalendarWhichWeek object listened to.
         * @param l
         * the lecture whose information will be shown
         */
        public ongoingListener(CalendarWhichWeek calendarWhichWeek,Lecture l){

                this.calendarWhichWeek=calendarWhichWeek;
                this.l=l;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(readAccLogin.WhatType().equals("T")){
                JFrame window = new JFrame("Your camera");
                JButton Btn = (JButton) e.getSource();
                JLabel Creminder=new JLabel("Camera is opening!");
                Webcam webcam = Webcam.getDefault();
                webcam.setViewSize(WebcamResolution.VGA.getSize());

                WebcamPanel panel = new WebcamPanel(webcam);
                panel.setFPSDisplayed(true);
                panel.setDisplayDebugInfo(true);
                panel.setImageSizeDisplayed(true);
                panel.setMirrored(true);

                window.add(panel);
                window.setResizable(true);
                window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                window.pack();
                window.setVisible(true);


            }else {
                CardLayout cardLayout = calendarWhichWeek.getBelongsToStandardFrame().getCardManager();
                JPanel container = calendarWhichWeek.getBelongsToStandardFrame().getCardContainer();
                showAPlayer showAPlayer = (showAPlayer) container.getComponent(6);
                showAPlayer.establishALivePlayer();
                cardLayout.show(container, "livePlayer");
            }
        }
    }


}
