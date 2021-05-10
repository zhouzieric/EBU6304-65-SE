package logic;

import javax.swing.*;
import java.awt.*;

public class changeIcon {

    public Icon iconButton(String addImage,int w,int h){
        ImageIcon okIcon = new ImageIcon(addImage);
        okIcon.setImage(okIcon.getImage().getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH));
        return okIcon;
    }
}
