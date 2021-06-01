
package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class is used to read feedback from data file
 * @author Gui Jiayi
 * @version 4.0
 */
public class FeedbackRead {
    /**
     * Get the feedback from feedback file
     * @return Arraylist
     * feedback is returned as a string array arraylist
     */
    public static ArrayList<String[]> readFeedback(){
        ArrayList<String[]> feedback=new ArrayList<>();
        String filePath = "src\\main\\java\\data\\booking.TXT";
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String oLine = br.readLine();
            while (oLine!=null){
                String[] splitF = oLine.split(";");
                if(!splitF[11].equals("null")){ //only show records with feedback
                    //feedback viewing includes bookingID, TrainerID, CustomerID, Mark, Comment
                    String[] getFeedback = {splitF[0],splitF[1],splitF[2],splitF[11],splitF[12]};
                    feedback.add(getFeedback);
                }
                oLine = br.readLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return feedback;
    }
}
