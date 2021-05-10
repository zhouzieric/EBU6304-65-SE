package pages;

import logic.changeIcon;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RecordingPlayer extends JPanel {
    private JPanel belongsto;
    private static final int controlButtonHeight=40;
    private static final int controlButtonWidth=50;

    public static void main(String[] args){
        JFrame frame= new JFrame();

        RecordingPlayer recordingPlayer= new RecordingPlayer("Shaping2","mp4");
        frame.getContentPane().add(recordingPlayer);
        frame.setBounds(300,200,500,500);
        frame.setVisible(true);
        recordingPlayer.toPlay();



    }

    private static final int backwardSec=1;
    private static final int forwardSec=1;
    public  EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private JPanel videoInfoPane;
    private JPanel videoPlayPane;
    private JPanel controlsPane;
    private JPanel videoTitlePane;

    //JButton backToMarket;

    JButton pauseButton;
    JButton rewindButton;
    JButton skipButton;

    String url;



    public RecordingPlayer(String videoName, String format){
        url="src/main/java/videos/"+videoName+"."+format;
        System.out.println("要播放的视频地址"+url);
        this.setLayout(new BorderLayout());

        videoTitlePane=new JPanel();//视频标题，包含一些信息
        videoPlayPane=new JPanel();//视频播放板
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        controlsPane=new JPanel();
        videoInfoPane=new JPanel();//视频下方的信息介绍

        this.add(videoTitlePane,BorderLayout.NORTH);//整个布局北中南分布
        this.add(videoPlayPane,BorderLayout.CENTER);
        this.add(videoInfoPane,BorderLayout.SOUTH);

        videoTitlePane.setLayout(new BorderLayout(10,10));
       // backToMarket= new JButton("Back");
        JLabel titleShow= new JLabel(videoName);
        //videoTitlePane.add(backToMarket,BorderLayout.NORTH);
        videoTitlePane.add(titleShow,BorderLayout.CENTER);


        videoPlayPane.setLayout(new BorderLayout());//视频播放分为视频本身和控制板，中南分布
        videoPlayPane.add(mediaPlayerComponent,BorderLayout.CENTER);
        videoPlayPane.add(controlsPane,BorderLayout.SOUTH);
        //控制视频的具体设置
        controlsPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        rewindButton = new JButton();
        rewindButton.setIcon(new changeIcon().iconButton("src/main/image/backward.png",controlButtonWidth-15,controlButtonHeight-15));
        rewindButton.setPreferredSize(new Dimension(controlButtonWidth, controlButtonHeight));
        controlsPane.add(rewindButton);
        pauseButton = new JButton();
        pauseButton.setIcon(new changeIcon().iconButton("src/main/image/pause.png",controlButtonWidth-15,controlButtonHeight-15));
        pauseButton.setPreferredSize(new Dimension(controlButtonWidth, controlButtonHeight));
        controlsPane.add(pauseButton);
        skipButton = new JButton("");
        skipButton.setIcon(new changeIcon().iconButton("src/main/image/forward.png",controlButtonWidth-15,controlButtonHeight-15));
        skipButton.setPreferredSize(new Dimension(controlButtonWidth, controlButtonHeight));
        controlsPane.add(skipButton);

        pauseButton.addActionListener(new pauseAndPlayControl());//监听内容在下面
        rewindButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().skipTime(-backwardSec*1000));
        skipButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().skipTime(forwardSec*10000));

        System.out.println("准备就绪");
   //mediaPlayerComponent.mediaPlayer().media().play("C:\\Users\\Lenovo\\Videos\\Captures\\Stardew Valley 2021-01-17 20-15-54.mp4");
    }

    public void toPlay(){
        System.out.println("开始播放");
        mediaPlayerComponent.mediaPlayer().media().play(url);

    }


    private class pauseAndPlayControl implements ActionListener{
       private int count=1;

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton theButton=(JButton)e.getSource();

            if(count%2==1) {
                theButton.setIcon(new changeIcon().iconButton("src/main/image/play.png",controlButtonWidth-15,controlButtonHeight-15));
                mediaPlayerComponent.mediaPlayer().controls().pause();
            }else{

                theButton.setIcon(new changeIcon().iconButton("src/main/image/pause.png",controlButtonWidth-15,controlButtonHeight-15));
                mediaPlayerComponent.mediaPlayer().controls().play();
            }
            theButton.revalidate();
            count++;
        }
    }

}
