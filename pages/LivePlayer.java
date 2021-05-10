package pages;

import logic.changeIcon;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Date;

public class LivePlayer extends JPanel {

    private JPanel belongsto;

    public EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private JPanel videoInfoPane;
    private JPanel videoPlayPane;
    private JPanel videoChatPane;
    private JPanel videoTitlePane;

    //JButton backToMarket;

    String url;



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

        System.out.println("准备就绪");
        //mediaPlayerComponent.mediaPlayer().media().play("C:\\Users\\Lenovo\\Videos\\Captures\\Stardew Valley 2021-01-17 20-15-54.mp4");
    }

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
