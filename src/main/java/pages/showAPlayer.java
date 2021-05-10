package pages;

import logic.readAccLogin;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class showAPlayer extends JPanel {

    private RecordingPlayer playerPane;
    private LivePlayer playerPaneLive;

    private JPanel container;
    private CardLayout manager;

    private RecordMarket brotherPan;

    private String videoName;
    private JButton backToMarket;

    public showAPlayer(){
        this.setLayout(new BorderLayout(10,10));
        this.setPreferredSize(new Dimension(0, 0));

    }
    public void establishAPlayer(){
            System.out.println("正在创建一个播放器。。");

        if("C".equals(readAccLogin.WhatType())){
            playerPane = new RecordingPlayer(videoName,getFormatUseName(videoName));
            backToMarket=new JButton("back");
            this.add(backToMarket,BorderLayout.NORTH);
            this.add(playerPane,BorderLayout.CENTER);

            backToMarket.addActionListener(e->{
            playerPane.mediaPlayerComponent.release();
            this.removeAll();
            getManager().show(getContainer(), "recordMarket");
             });
        }else{
            playerPane = new RecordingPlayer(videoName,getFormatUseName(videoName));
            this.add(playerPane,BorderLayout.CENTER);
        }


            playerPane.setPreferredSize(new Dimension(0, 0));
            playerPane.toPlay();
            this.revalidate();


    }

    public void establishALivePlayer(){
        System.out.println("正在创建一个直播间。。");


            playerPaneLive = new LivePlayer();
            backToMarket=new JButton("Leave This Room");
            this.add(backToMarket,BorderLayout.NORTH);
            this.add(playerPaneLive,BorderLayout.CENTER);

            backToMarket.addActionListener(e->{
                playerPaneLive.mediaPlayerComponent.release();
                this.removeAll();
                getManager().show(getContainer(), "recordMarket");
            });



        playerPaneLive.setPreferredSize(new Dimension(0, 0));
        playerPaneLive.toPlay();
        this.revalidate();


    }

    public static String getFormatUseName(String video){
        String format=null;
        String fileName ="src/main/java/data/videos.txt";
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine;
            while ((oneLine = bufferedReader.readLine())!=null) {
                String[] info = oneLine.split(";");
                //System.out.println(oneLine);

                if (info[0].equals(video)) {
                    format = info[2];
                    System.out.println("搜到的视频格式" + info[2]);
                    break;
                }

            }
            bufferedReader.close();

        }catch (IOException e){

            System.out.println("Errors occured:视频格式 没搜到:  "+e);
        }
        return format;

    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        this.container = container;
    }

    public CardLayout getManager() {
        return manager;
    }

    public void setManager(CardLayout manager) {
        this.manager = manager;
    }

    public RecordMarket getBrotherPan() {
        return brotherPan;
    }

    public void setBrotherPan(RecordMarket brotherPan) {
        this.brotherPan = brotherPan;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
}
