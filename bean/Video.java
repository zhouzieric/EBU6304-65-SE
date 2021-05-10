package bean;

public class Video {

    private String videoName;
    private String coverFormat;
    private String videoFormat;
    private int videoType;
    private String description;
    private int whoCanWatchIt;//能看的最低会员等级

    public Video(){}
    public Video(String videoName,String coverFormat,String videoFormat,int videoType,String description,int whoCanWatchIt){
        this.videoName=videoName;
        this.coverFormat=coverFormat;
        this.videoFormat=videoFormat;
        this.videoType=videoType;
        this.description=description;
        this.whoCanWatchIt=whoCanWatchIt;

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
