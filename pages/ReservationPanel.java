package pages;


import bean.Questionnaire;
import logic.DiscountCalculator;
import logic.ReadandWrite;
import logic.addBooking;
import logic.getNextWeek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReservationPanel extends JPanel {

    CalendarView belongsTo;
    JLabel tp;
    private String selected="";
    private String TID;
    //private String cusID="";
    private int level;
    private ArrayList<Integer> selectedLecturesNum;
    private Questionnaire questionaire;
    addBooking addbooking;

    public ReservationPanel(String TID, int level, ArrayList<Integer> selectedLecturesNum,CalendarView belongsTo){
        this.belongsTo=belongsTo;
        this.TID = TID;
        this.level = level;
        this.selectedLecturesNum = selectedLecturesNum;
        this.setLayout(new BorderLayout());

        //
        JLabel RI = new JLabel("Reservation Information", JLabel.CENTER);
        RI.setFont(new java.awt.Font("Dialog", 1, 20));
        this.add(RI, BorderLayout.NORTH );

        //
        JPanel rpCenter = new JPanel();
        rpCenter.setLayout(new GridLayout(2,1));
        //
        JPanel row1 = new JPanel();
        row1.setLayout(new GridLayout(2,2));
        int bLastNum = ReadandWrite.rFile("booking");
        JLabel BookingID = new JLabel("Booking ID: " +"B" + ++bLastNum,JLabel.CENTER);
        int cNum = ReadandWrite.rFile("acc_login");
        String CID = "C" + cNum;
        JLabel UserName = new JLabel("User Name: "+ ReadandWrite.getName(CID),JLabel.CENTER);
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
        DiscountCalculator dc = new DiscountCalculator();
        JLabel Price = new JLabel("Price: " + dc.calDiscount(level,selectedLecturesNum.size()),JLabel.CENTER);
        JLabel TrainerName = new JLabel("Trainer Name: "+ ReadandWrite.getName(TID),JLabel.CENTER);
        row1.add(BookingID);
        row1.add(TrainerName);
        row1.add(Price);
        row1.add(UserName);
        //
        JPanel row2 = new JPanel();
//        row2.setLayout(new GridLayout(len(),1));
        JLabel s = new JLabel("Selected Time Periods:",JLabel.CENTER);
        tp = new JLabel(selected,JLabel.CENTER);
        row2.setLayout(new GridLayout(2,1));
        row2.add(s);
        row2.add(tp);
        rpCenter.add(row1);
        rpCenter.add(row2);
        this.add(rpCenter, BorderLayout.CENTER);

        //
        JPanel rpSouth = new JPanel();
        JLabel warn = new JLabel("Please check above information before submitting the reservation.");
        JButton sub = new JButton("    Pay    ");
        addbooking= new addBooking();
        addbooking.setBelongsTo(belongsTo);
        sub.addActionListener(addbooking);

        rpSouth.add(warn);
        rpSouth.add(sub);

        this.add(rpSouth,BorderLayout.SOUTH);

    }

    public void setSelected(String selected) {
        tp.setText("<html>"+selected+"</html>");
    }

    public String getSelected() {
        return selected;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

//    public String getCuID() {
//        return cusID;
//    }
//
//    public void setCuID(String cuID) {
//        this.cusID = cuID;
//    }

    public int getLevel() {
        return level;
    }

   public void setLevel(int level) {
        this.level = level;
       System.out.println("已经设定level："+this.level);
    }

    public ArrayList<Integer> getSelectedLecturesNum() {
        return selectedLecturesNum;
    }

    public Questionnaire getQuestionaire() {
        return questionaire;
    }

    public addBooking getAddbooking() {
        return addbooking;
    }

    public void setAddbooking(addBooking addbooking) {
        this.addbooking = addbooking;
    }

    public void setQuestionaire(Questionnaire questionaire) {
        this.questionaire = questionaire;
    }

    public void setSelectedLecturesNum(ArrayList<Integer> selectedLecturesNum) {
        this.selectedLecturesNum = selectedLecturesNum;
    }
}
