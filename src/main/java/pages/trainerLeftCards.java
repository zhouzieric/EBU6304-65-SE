package pages;

import mdlaf.utils.MaterialColors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

public class trainerLeftCards extends JButton {

    public trainerLeftCards(String TID,String name,String speciality,String level,CalendarView panel){
        super();

       this.setBorder(BorderFactory.createLineBorder(MaterialColors.GRAY_100,2));
       // this.setForeground(MaterialColors.TEAL_400);
        this.setText("<html><b> <font color=\"#26A69Avv\" >"+name+"<font><br>"+" speciality:"+speciality+"<br>"+" level:"+level+"</b></html>");

        this.addActionListener((e)->{
            System.out.println("卡片传参的TID："+TID);
            panel.setTID(TID);
            try {
                panel.updateRightTable(TID);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }


        });

    }


}
