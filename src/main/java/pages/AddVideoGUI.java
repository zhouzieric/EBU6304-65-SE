/**
 *  This class is a UI for administrator to
 *  add video with detailed information
 * @author Gui Jiayi
 * @version 3.2
 */
package pages;

import bean.Customer;
import bean.Video;
import logic.AdminVideoListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddVideoGUI implements ActionListener{
    private JFrame frame;
    private AdminVideoController avc;
    private AdminVideoListener avl;
    private JButton bVideoChooser;
    private JButton bVideoCoverChooser;
    private JButton bCancel;
    private JButton bSubmit;
    private JLabel lSubWarn;
    private JComboBox<String> cbCategory;
    private JTextArea taIntro;

    private String vName;
    private String vCFormat;
    private String vFormat;
    private String videoText;
    private String videoCoverText;
    private Object[] rowData;
    private int flag=0;
    private JComboBox<String> cbLowest;

    /**
     * This a constructor to pass parameters
     * @param avc
     * let the class be able to change the object AdminVideoController passed
     * @param avl
     * let the class be able to change the object AdminVideoListener passed
     */
    public AddVideoGUI(AdminVideoController avc, AdminVideoListener avl){
        this.avc =avc;
        this.avl = avl;
    }


    /**
     * For constructing without parameters
     */
    public AddVideoGUI(){}

    /**
     * This is a initializer for UI to show
     */
    public void go(){

        JPanel pVideoInfo = new JPanel();
        pVideoInfo.setLayout(new GridLayout(3,2));
        JPanel pSouth = new JPanel();
        pSouth.setLayout(new BoxLayout(pSouth,BoxLayout.Y_AXIS));

        lSubWarn = new JLabel("");
        pSouth.add(lSubWarn);
        JPanel pSubmit = new JPanel();
        pSouth.add(pSubmit);
        bCancel = new JButton("Cancel");
        bCancel.addActionListener(this);
        pSubmit.add(bCancel);
        JLabel l = new JLabel("              ");
        pSubmit.add(l);
        bSubmit = new JButton("Submit");
        bSubmit.addActionListener(this);
        pSubmit.add(bSubmit);


        //Video Chooser
        JPanel pVideoChooser = new JPanel();
        pVideoInfo.add(pVideoChooser);
        //pVideoChooser.setLayout(new BoxLayout(pVideoChooser,BoxLayout.Y_AXIS));
        JLabel labelVideo = new JLabel("Choose Video From File:  ");
        bVideoChooser = new JButton("Choose");
        bVideoChooser.addActionListener(this);
        pVideoChooser.add(labelVideo);
        pVideoChooser.add(bVideoChooser);

        //Video Cover Chooser
        JPanel pVideoCoverChooser = new JPanel();
        pVideoInfo.add(pVideoCoverChooser);
        //pVideoCoverChooser.setLayout(new BoxLayout(pVideoCoverChooser,BoxLayout.Y_AXIS));
        JLabel labelVideoCover = new JLabel("Choose Video Cover From File:  ");
        bVideoCoverChooser = new JButton("Choose");
        bVideoCoverChooser.addActionListener(this);
        pVideoCoverChooser.add(labelVideoCover);
        pVideoCoverChooser.add(bVideoCoverChooser);


        //Video Category
        JPanel pCategory = new JPanel();
        pVideoInfo.add(pCategory);
        JLabel lCategory = new JLabel("Video Category:  ");
        String[] listCategory = new String[]{"Yoga","HIIT","Strength","Aerobics","Tai Chi","Weightloss","Shaping"};
        //lCategory.setLayout(new BoxLayout(lCategory,BoxLayout.Y_AXIS));
        cbCategory = new JComboBox<String>(listCategory);
        cbCategory.setSelectedIndex(0);
        //cbCategory.setLayout(new BoxLayout(cbCategory,BoxLayout.Y_AXIS));
        pCategory.add(lCategory);
        pCategory.add(cbCategory);

        //Video Intro
        JPanel pIntro = new JPanel();
        pVideoInfo.add(pIntro);
        pIntro.setLayout(new BoxLayout(pIntro,BoxLayout.Y_AXIS));
        JLabel lIntro = new JLabel("Input Video Introduction:  ");
        taIntro = new JTextArea(5,5);
        taIntro.setLineWrap(true);     //auto-change-line
        taIntro.setFont(new Font("Noto Sans Display Bold",Font.PLAIN,15));
        JScrollPane jScrollPane = new JScrollPane(taIntro);
        pIntro.add(lIntro);
        pIntro.add(jScrollPane);

        //Video Lowest Class Viewing
        JPanel pLowest = new JPanel();
        pVideoInfo.add(pLowest);
        //pLowest.setLayout(new BoxLayout(pLowest,BoxLayout.Y_AXIS));
        JLabel lLowest = new JLabel("Video Lowest Class Viewing:  ");
        ArrayList<String> memRank = Customer.getMemRank();
        String[] listLowest = memRank.toArray(new String[memRank.size()]);
        cbLowest = new JComboBox<String>(listLowest);
        cbLowest.setSelectedIndex(2);
        pLowest.add(lLowest);
        pLowest.add(cbLowest);


        frame = new JFrame("Add Video Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Close when Payment is comleted
        frame.setBounds(400,150,500,500);
        frame.setVisible(true);

        frame.getContentPane().add(BorderLayout.CENTER,pVideoInfo);
        frame.getContentPane().add(BorderLayout.SOUTH,pSouth);
    }

    /**
     * Action event for all buttons on the AddVideoGUI to response
     * @param e
     * Actionevent for button added
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Video video = new Video();
        if(e.getSource()==bCancel){
            frame.dispose();     //dispose the frame

        }else if(e.getSource()==bSubmit){
           // int index=0;
            if((vName!=null)&&(vFormat!=null)&&(vCFormat!=null)){
                //fill rowData
                rowData = new Object[6];
                rowData[0]=vName;
                rowData[1]=vCFormat;
                rowData[2]=vFormat;
                rowData[3]=cbCategory.getSelectedItem();
                rowData[4]=taIntro.getText();
                rowData[5]=cbLowest.getSelectedItem();

                String rowFile = vName + ";"+vCFormat + ";"+vFormat + ";"+video.revTransCategory((String) cbCategory.getSelectedItem()) + ";"+taIntro.getText()+ ";"+video.revTransMem((String)cbLowest.getSelectedItem());
                System.out.println(rowFile);
                avl.addVideo(rowFile);
                JOptionPane.showMessageDialog(null,"Submit Successfully!   ","Add Video Result",JOptionPane.DEFAULT_OPTION);
                frame.dispose();
                avc.getTableModel().addRow(rowData);
            }else{
                JOptionPane.showMessageDialog(null,"Submit Unsuccessfully!   ","Add Video Result",JOptionPane.ERROR_MESSAGE);
                lSubWarn.setText("**Choose both Video and Video Record to add a Video**");
                lSubWarn.setForeground(Color.red);
            }

        }else if(e.getSource()==bVideoChooser){
            // Choose Video Path
            JFileChooser chooser = new JFileChooser();
            //filter file appendix
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Video File Type","mp4","mvi","mov","mpg","mpeg");
            chooser.setFileFilter(filter);

            int dia = chooser.showOpenDialog(this.avc);  //show dialag chooser

            if(dia==JFileChooser.APPROVE_OPTION){   //get chooser result
                boolean flag;
                File file = chooser.getSelectedFile();
                //System.out.println(file.getAbsolutePath());

                //get source video
                String[] pathSplit = file.getAbsolutePath().split("\\\\");
                videoText = pathSplit[pathSplit.length - 1];
                flag= avl.moveVideo(file,videoText);

                if(flag) {
                    //if move successfully
                    JOptionPane.showMessageDialog(null,"Choose Successfully!   ","Move Video Result",JOptionPane.DEFAULT_OPTION);
                    //set name and format of video if correct
                    vName = videoText.split("\\.")[0];
                    //System.out.println(vName);
                    vFormat = videoText.split("\\.")[1];
                    //System.out.println(vFormat);
                }else   JOptionPane.showMessageDialog(null,"Choose Unsuccessfully!   ","Move Video Result",JOptionPane.ERROR_MESSAGE);

            }


        }else if(e.getSource()==bVideoCoverChooser){
            // Choose Video Path
            JFileChooser chooser = new JFileChooser();
            //filter file appendix
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Video Cover File Type","jpeg","tiff","raw","bmp","gif","png","jpg","svg");
            chooser.setFileFilter(filter);

            int dia = chooser.showOpenDialog(this.avc);  //show dialag chooser

            if(dia==JFileChooser.APPROVE_OPTION){   //get chooser result
                boolean flag;
                File file = chooser.getSelectedFile();
                //System.out.println(file.getAbsolutePath());

                //get source video
                String[] pathSplit = file.getAbsolutePath().split("\\\\");
                videoCoverText = pathSplit[pathSplit.length - 1];
                flag= avl.moveVideo(file,videoCoverText);

                if(flag) {
                    //if move successfully
                    JOptionPane.showMessageDialog(null,"Choose Successfully!   ","Move Cover Result",JOptionPane.DEFAULT_OPTION);
                    //set name and format of video if correct
                    vCFormat = videoCoverText.split("\\.")[1];
                    //System.out.println(vCFormat);
                }else   JOptionPane.showMessageDialog(null,"Choose Unsuccessfully!   ","Move Cover Result",JOptionPane.ERROR_MESSAGE);

            }

        }



    }
}
