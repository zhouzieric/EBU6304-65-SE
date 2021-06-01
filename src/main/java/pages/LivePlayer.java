package pages;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import logic.changeIcon;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
/**
 *This page is to show the live teaching.
 * @author Kezhou Zhang
 * @version 1.0.0
 */
public class LivePlayer extends JPanel {

    private JPanel belongsto;

    public EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private JPanel videoInfoPane;
    private JPanel videoPlayPane;
    private JPanel videoChatPane;
    private JPanel videoTitlePane;

    //JButton backToMarket;

    String url;


    /**
     * For constructing a page without parameters.
     */
    public LivePlayer(){

        url="src/main/java/videos/Shaping2.mp4";

        System.out.println("直播的视频地址"+url);
        this.setLayout(new BorderLayout());
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        videoTitlePane=new JPanel();//视频标题，包含一些信息
        videoPlayPane=new JPanel();//视频播放板
        videoChatPane=new JPanel();//右侧聊天框
        videoInfoPane=new JPanel();//视频下方的信息介绍

        this.add(videoTitlePane,BorderLayout.NORTH);//整个布局北中南右分布
        this.add(videoPlayPane,BorderLayout.CENTER);
        this.add(videoInfoPane,BorderLayout.SOUTH);
        this.add(videoChatPane,BorderLayout.EAST);
        //视频标题
        videoTitlePane.setLayout(new BorderLayout(10,10));
        Date date = new Date();
        JLabel titleShow= new JLabel(date.toString()+" Live Room");
        videoTitlePane.add(titleShow,BorderLayout.CENTER);
//        //视频播放本身
        videoPlayPane.setLayout(new BorderLayout());
        videoPlayPane.add(mediaPlayerComponent,BorderLayout.CENTER);
        //下方介绍
        Border titleborder = BorderFactory.createTitledBorder("Lecture Information");
        videoInfoPane.setBorder(titleborder);
        videoInfoPane.add(new JLabel("This lecture will learn new things!"));

        //右侧聊天框
        videoChatPane.setLayout(new BorderLayout(5,5));
        JButton cam=new JButton("Open camera");
        cam.addActionListener(e -> {
            JFrame window = new JFrame("Your camera");
            JButton Btn = (JButton) e.getSource();


                Webcam webcam = Webcam.getDefault();
               // webcam.setViewSize(WebcamResolution.VGA.getSize());

                WebcamPanel panel = new WebcamPanel(webcam);
                panel.setFPSDisplayed(true);
                panel.setDisplayDebugInfo(true);
                panel.setImageSizeDisplayed(true);
                panel.setMirrored(true);


                window.add(panel);
                window.setResizable(true);
                window.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        JFrame window = (JFrame) e.getSource();
                        window.dispose();
                    }
                });
                window.pack();
                window.setVisible(true);



        });

        videoChatPane.add(cam,BorderLayout.NORTH);
        JToggleButton mic=new JToggleButton("Open Microphone");
//        mic.addChangeListener(e -> {
//
//            JToggleButton toggleBtn = (JToggleButton) e.getSource();
//            JLabel Mreminder=new JLabel("Microphong is opening!");
//            if(toggleBtn.isSelected()) {
//
//
//                videoChatPane.add(Mreminder,BorderLayout.EAST);
//            }else{
//
//                videoChatPane.remove(Mreminder);
//            }
//        });
        videoChatPane.add(mic,BorderLayout.CENTER);
        System.out.println("准备就绪");
        //mediaPlayerComponent.mediaPlayer().media().play("C:\\Users\\Lenovo\\Videos\\Captures\\Stardew Valley 2021-01-17 20-15-54.mp4");
    }
    /**
     * play the video.
     */
    public void toPlay(){
        System.out.println("开始播放");
        mediaPlayerComponent.mediaPlayer().media().play(url);

    }
    public static void main(String[] args){
        JFrame frame= new JFrame();

        LivePlayer livePlayer=new LivePlayer();
        frame.getContentPane().add(livePlayer);
        frame.setBounds(300,200,500,500);
        frame.setVisible(true);
        livePlayer.toPlay();



    }
}
