package logic;
import pages.AddVideoGUI;
import pages.AdminVideoController;
import pages.RecordingPlayer;
import pages.showAPlayer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;


public class AdminVideoListener implements ActionListener{
    AdminVideoController avc;

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == avc.getbAdd()){    //Add Video
            AddVideoGUI addVideo = new AddVideoGUI(avc,this);
            addVideo.go();


        }else if(e.getSource() == avc.getbDel()){  //Delete Video

            int row =avc.getTable().getSelectedRow();   //acquire the row to be deleted
            if(row!=-1){    //make sure there exist a selected row
                // File Modification on deleting
                //System.out.println(row);
                delVideo(row);

                //delete the row in the GUI
                avc.getTableModel().removeRow(row);
                avc.getTableModel().fireTableDataChanged();
            }


        }else if(e.getSource() == avc.getbView()){  //View Video

            int row = avc.getTable().getSelectedRow();
            //System.out.println(row);

            if(row!=-1){
                String videoName = getVideoName(row);
                System.out.println(videoName);

                RecordingPlayer rp = new RecordingPlayer(videoName,showAPlayer.getFormatUseName(videoName));

                JFrame viewFrame = new JFrame("View Sports Video");
                viewFrame.setBounds(400,150,500,500);
                viewFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        rp.mediaPlayerComponent.release();
                    }
                });
                viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Close when Payment is comleted
                viewFrame.setVisible(true);
                viewFrame.setContentPane(rp);
                rp.toPlay();

            }




        }
    }

    public void setAvc(AdminVideoController avc) {
        this.avc = avc;
    }

    public void delVideo(int row){
        String path = openVideoFile(1);
        int index =0;

        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String oLine = br.readLine();
            File fileNew = new File(path);
            FileWriter fileWriter = new FileWriter(fileNew);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bw);

            while(oLine!=null){
                if(row!=index){
                    pw.write(oLine+"\n");
                }
                index++;
                oLine = br.readLine();
            }

            file.delete();
            br.close();
            fileReader.close();
            pw.close();
            bw.close();
            fileWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public String getVideoName(int row){
        String videoName = "";
        String path = openVideoFile(1);
        int index =0;

        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String oLine = br.readLine();


            while(oLine!=null){
                if(row==index){
                    videoName = oLine.split(";")[0];
                    break;
                }
                index++;
                oLine = br.readLine();
            }

            br.close();
            fileReader.close();


        }catch (IOException e){
            e.printStackTrace();
        }
        return videoName;
    }

    public void addVideo(String row){
        String path =openVideoFile(1);
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file,true);   //add content as appendix to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write(row);
            bufferedWriter.close();
            fileWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean moveVideo(File source,String dest){
        boolean flag = false;
        String path = openVideoFile(2);
        File destFile = new File(path.concat(dest));
        //System.out.println(destFile);
        try{
            if(!destFile.exists()){
                destFile.createNewFile();

                //Byte Transformation
                FileInputStream fileInputStream = new FileInputStream(source);
                FileOutputStream fileOutputStream = new FileOutputStream(destFile);

                byte[] b = new byte[1024];
                int byteLine;
                while ((byteLine=fileInputStream.read(b))!=-1){
                    fileOutputStream.write(b,0,byteLine);
                }
                flag=true;

                fileInputStream.close();
                fileOutputStream.close();

            }else System.out.println("The destination directory has a video/cover with the same name");
        }catch (IOException e){
            e.printStackTrace();
        }

        return flag;
    }

    public String openVideoFile(int flag){
        String path="";
        if(flag==1) {    //when only open video text
            path= "src\\main\\java\\data\\videos.txt";
        }
        if(flag==2) {     //return video and pigture dir
            path="src\\main\\java\\videos\\";
        }
        return path;
    }


}
