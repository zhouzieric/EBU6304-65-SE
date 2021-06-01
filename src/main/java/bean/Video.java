
package bean;

import logic.ReadFlexibleInfo;

import java.util.ArrayList;
/**
 *A bean for every video to save its information.
 * @author Kezhou Zhang, Gui Jiayi
 * @version 1.0.0
 */
public class Video {

    private String videoName;
    private String coverFormat;
    private String videoFormat;
    private int videoType;
    private String description;
    private int whoCanWatchIt;

    public static final int CATEGORY_ALL=0;



    /**
     * For constructing without parameters
     */
    public Video(){}
    /**
     * For constructing a video.
     * @param  videoName
     * the name of video
     * @param coverFormat
     * the cover's format of video
     * @param videoFormat
     * the format of video
     * @param videoType
     * the video type. to classify the videos
     * @param description
     * the description of the video
     * @param whoCanWatchIt
     * The level of permission to watch a video
     */
    public Video(String videoName,String coverFormat,String videoFormat,int videoType,String description,int whoCanWatchIt){
        this.videoName=videoName;
        this.coverFormat=coverFormat;
        this.videoFormat=videoFormat;
        this.videoType=videoType;
        this.description=description;
        this.whoCanWatchIt=whoCanWatchIt;

    }

    /**
     * convert a number token to a category name
     * @param  categoryInt
     * a number token of what type of video
     * @return  String
     * category name
     */
    public String transCategory(int categoryInt){
        String categoryRow = ReadFlexibleInfo.readFile(3);
        String[] category = categoryRow.split(";");

        return category[categoryInt-1];
    }

    /**
     * convert a String category name to a associated number token
     * @param  categoryStr
     * a String of video type name
     * @return  int
     * a number token representing specified video category
     */
    public int revTransCategory(String categoryStr){
        String categoryRow = ReadFlexibleInfo.readFile(3);
        String[] category = categoryRow.split(";");
        int categoryInt =0;
        for(int i=0;i<category.length;i++){
            if(category[i].equals(categoryStr)) categoryInt=i+1;
        }
        return categoryInt;
    }

    /**
     * convert a number token to a associated membership name
     * @param  memInt
     * a number token of what type of membership
     * @return  String
     * membership name
     */
    public String transMem(int memInt){
        ArrayList<String> memRank = Customer.getRevMemRank();
        return memRank.get(memInt);
    }

    /**
     * convert a String membership name to a associated number token
     * @param  memStr
     * a String of the name of membership
     * @return  int
     * associated number token of that given membership
     */
    public int revTransMem(String memStr){
        ArrayList<String> memRank = Customer.getRevMemRank();
        int memInt =-1;
        for(int i=0;i<memRank.size();i++){
            if(memStr.equals(memRank.get(i))) memInt=i;
;        }
        return memInt;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getCoverFormat() {
        return coverFormat;
    }

    public void setCoverFormat(String coverFormat) {
        this.coverFormat = coverFormat;
    }

    public String getVideoFormat() {
        return videoFormat;
    }

    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }

    public int getVideoType() {
        return videoType;
    }

    public void setVideoType(int videoType) {
        this.videoType = videoType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWhoCanWatchIt() {
        return whoCanWatchIt;
    }

    public void setWhoCanWatchIt(int whoCanWatchIt) {
        this.whoCanWatchIt = whoCanWatchIt;
    }
}
