package logic;

import javax.swing.*;
import java.awt.*;
/**
 * providing a operation to handle a image for a proper size.
 * @author Kezhou Zhang
 * @version 1.0.0
 */
public class changeIcon {
    /**
     * to output a proper icon.
     * @param  addImage
     * the image will be handled
     * @param w
     * the wanted width
     * @param h
     * the wanted height
     * @return the wanted icon
     * the icon has been handled in proper format
     */
    public static Icon iconButton(String addImage,int w,int h){
        ImageIcon okIcon = new ImageIcon(addImage);
        okIcon.setImage(okIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        return okIcon;
    }
}
