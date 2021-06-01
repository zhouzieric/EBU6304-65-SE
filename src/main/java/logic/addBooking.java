package logic;

import bean.Booking;
import bean.Lecture;
import bean.Questionnaire;
import pages.CalendarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

/**
 * This class implements ActionListener.
 * Response to JButton Pay in ReservationPanel.
 * @author Heqing Wang
 * @version 1.1
 */
public class addBooking implements ActionListener {
    CalendarView belongsTo;
    private String TID;
    private int level;
    private Questionnaire questionaire;
    ArrayList<Integer> selectedLecturesNum;

    /**
     * This method overrides actionPerformed method.
     * Add Booking and Lectures in booking.TXT and lectures.TXT.
     * @param e Presents ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Booking booking = new Booking();
        // get number of the last BookingID
        int bLastNum = ReadandWrite.rFile("booking");
        booking.setBookingID("B" + ++bLastNum);
        int cNum = ReadandWrite.rFile("acc_login");
        booking.setCusID("C" + cNum);
        booking.setTraID(TID);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = formatter.format(new Date());
        booking.setCreatDate(d);
        DiscountCalculator dc = new DiscountCalculator();
        double price = dc.calDiscount(level,selectedLecturesNum.size());
        booking.setPrice(price);
        booking.setQuestionnaire(questionaire);
        ReadandWrite.wFile("booking",booking.toString());

        // write lectures
        for(int i: selectedLecturesNum){
            Lecture lecture = new Lecture();
            lecture.setBookingId("B" + bLastNum);
            lecture.setMark(i);
            getNextWeek getNextWeek = new getNextWeek();
            lecture.setDate(getNextWeek.getNextWeekday(i/6));
            ReadandWrite.wFile("lectures",lecture.toString());
        }

        JOptionPane.showMessageDialog(null,"Payment Successful");

        // then update CalendarView
        try {
            belongsTo.updateRightTable(belongsTo.getTID());
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        belongsTo.getCheckBill().dispose();

    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSelectedLecturesNum(ArrayList<Integer> selectedLecturesNum) {
        this.selectedLecturesNum = selectedLecturesNum;
    }

    public void setQuestionaire(Questionnaire questionaire) {
        this.questionaire = questionaire;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public CalendarView getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(CalendarView belongsTo) {
        this.belongsTo = belongsTo;
    }
}
