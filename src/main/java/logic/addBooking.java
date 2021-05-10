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

public class addBooking implements ActionListener {

    CalendarView belongsTo;
    private String TID;
    private String cusID;
    private int level;
    private Questionnaire questionaire;
    ArrayList<Integer> selectedLecturesNum;

    //public addBooking(String TID, String cusID, int level, ArrayList<Integer> selectedLecturesNum, Questionnaire questionaire){
    public addBooking(){
        this.TID=TID;
        //this.cusID=cusID;
        this.level=level;
//        System.out.println("addbooking里被给的level是："+level);
//        System.out.println("addbooking里的level是："+this.level);
        this.selectedLecturesNum=selectedLecturesNum;
        this.questionaire=questionaire;
    }
    public void actionPerformed(ActionEvent e) {
        //点完pay将做这些事
        //写入booking
        Booking booking = new Booking();

        int bLastNum = ReadandWrite.rFile("booking");
       // System.out.println(bLastNum);
        booking.setBookingID("B" + ++bLastNum);
        int cNum = ReadandWrite.rFile("acc_login");
        booking.setCusID("C" + cNum);
        booking.setTraID(TID);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = formatter.format(new Date());
        booking.setCreatDate(d);
        DiscountCalculator dc = new DiscountCalculator();
        double price = dc.calDiscount(level,selectedLecturesNum.size());

//        double unitPrice = 0;
//        switch (level){
//            case 1:
//                unitPrice = 40;
//                break;
//            case 2:
//                unitPrice = 55;
//                break;
//            case 3:
//                unitPrice = 80;
//                break;
//        }
//        System.out.println("unitPrice="+unitPrice);
//        System.out.println("selectedLecturesNum大小"+selectedLecturesNum.size());
//        double price = unitPrice * selectedLecturesNum.size();

        booking.setPrice(price);
        booking.setQuestionnaire(questionaire);
        ReadandWrite.wFile("booking",booking.toString());

        //写lectures
        for(int i: selectedLecturesNum){
            Lecture lecture = new Lecture();
            lecture.setBookingId("B" + bLastNum);
            lecture.setMark(i);
            lecture.setContent(questionaire.getTarget());
            getNextWeek getNextWeek = new getNextWeek();
            lecture.setDate(getNextWeek.getNextWeekday(i/6));
            ReadandWrite.wFile("lectures",lecture.toString());
        }
        //弹窗
        JOptionPane.showMessageDialog(null,"Success");
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
