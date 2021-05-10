package pages;

import logic.searchVideos;
import pages.StandardFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

public class RecordMarket extends JScrollPane {


    private JPanel jPanel;
    private JPanel container;
    private CardLayout manager;

    private showAPlayer brotherPan;

    public RecordMarket(){

        jPanel=new JPanel();
        this.getViewport().add(jPanel);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setPreferredSize(new Dimension(0, 0));
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);//一直竖着滚动

        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER,80,50));//完美的间距
        jPanel.setPreferredSize(new Dimension(0, StandardFrame.frameH*3));//防止横划


        ArrayList<String[]> Allvideos = new searchVideos().searchAllvideos();

        for(String[] aVideo: Allvideos){

               ImageIcon pic = new ImageIcon("src/main/java/videos/"+aVideo[0]+"."+aVideo[1]);
               JLabel a = new JLabel();
               pic.setImage(pic.getImage().getScaledInstance(200, 150,Image.SCALE_DEFAULT ));
               a.setText("<html>"+aVideo[0]+"</html>");
               a.setIcon(pic);
               a.setVerticalTextPosition(JLabel.BOTTOM);
               a.setHorizontalTextPosition(JButton.CENTER);
               jPanel.add(a);
               a.setPreferredSize(new Dimension(200, 200));
               a.addMouseListener(new imageLabelListener());


        }












    }

    private class imageLabelListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            JLabel theLabel=(JLabel) e.getSource();
            System.out.println("这是取到的"+new searchVideos().deleteHTMLBothSides(theLabel.getText()));
            //selectedVideo=new searchVideos().deleteHTMLBothSides(theLabel.getText());
            getBrotherPan().setVideoName(new searchVideos().deleteHTMLBothSides(theLabel.getText()));
            getBrotherPan().establishAPlayer();
            getManager().show(getContainer(),"showAPlayer");
        }
        public void mouseEntered(MouseEvent e){
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        //鼠标移出文本范围，恢复鼠标样式；
        public  void mouseExited(MouseEvent e){
            setCursor(Cursor.getDefaultCursor());
        }

    }


    public showAPlayer getBrotherPan() {
        return brotherPan;
    }

    public void setBrotherPan(showAPlayer brotherPan) {
        this.brotherPan = brotherPan;
    }

    public CardLayout getManager() {
        return manager;
    }

    public void setManager(CardLayout manager) {
        this.manager = manager;
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        this.container = container;
    }
    //    public String getSelectedVideo() {
//        return selectedVideo;
//    }
//
//    public void setSelectedVideo(String selectedVideo) {
//        this.selectedVideo = selectedVideo;
//    }
}
