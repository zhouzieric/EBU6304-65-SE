package bean;

public class Video {

    private String videoName;
    private String coverFormat;
    private String videoFormat;
    private int videoType;
    private String description;
    private int whoCanWatchIt;//能看的最低会员等级

    public static final String CATEGORY_YOGA="Yoga";
    public static final String CATEGORY_HIIT="HIIT";
    public static final String CATEGORY_STRENGTH="Strength";
    public static final String CATEGORY_AEROBICS="Aerobics";
    public static final String CATEGORY_TAICHI="Tai Chi";
    public static final String CATEGORY_WEIGHTLOSS="Weightloss";
    public static final String CATEGORY_SHAPING="Shaping";




    public Video(){}
    public Video(String videoName,String coverFormat,String videoFormat,int videoType,String description,int whoCanWatchIt){
        this.videoName=videoName;
        this.coverFormat=coverFormat;
        this.videoFormat=videoFormat;
        this.videoType=videoType;
        this.description=description;
        this.whoCanWatchIt=whoCanWatchIt;

    }

    public String transCategory(int categoryInt){
        String categoryStr="";
        switch (categoryInt) {
            case 1:
                categoryStr = CATEGORY_YOGA;
                break;
            case 2:
                categoryStr =CATEGORY_HIIT;
                break;
            case 3:
                categoryStr =CATEGORY_STRENGTH;
                break;
            case 4:
                categoryStr =CATEGORY_AEROBICS;
                break;
            case 5:
                categoryStr =CATEGORY_TAICHI;
                break;
            case 6:
                categoryStr =CATEGORY_WEIGHTLOSS;
                break;
            case 7:
                categoryStr =CATEGORY_SHAPING;
                break;

        }
        return categoryStr;
    }

    public int revTransCategory(String categoryStr){
        int categoryInt =0;
        switch (categoryStr){
            case CATEGORY_YOGA:
               categoryInt =1;
               break;
            case CATEGORY_HIIT:
                categoryInt =2;
                break;
            case CATEGORY_STRENGTH:
                categoryInt =3;
                break;
            case CATEGORY_AEROBICS:
                categoryInt =4;
                break;
            case CATEGORY_TAICHI:
                categoryInt =5;
                break;
            case CATEGORY_WEIGHTLOSS:
                categoryInt =6;
                break;
            case CATEGORY_SHAPING:
                categoryInt =7;
                break;
        }
        return categoryInt;
    }

    public String transMem(int memInt){
        String memStr="";
        Customer cus = new Customer();

        switch (memInt){
            case 0:
                memStr = cus.MEMBERSHIP_JUNIOR;
                break;
            case 1:
                memStr = cus.MEMBERSHIP_NORM;
                break;
            case 2:
                memStr = cus.MEMBERSHIP_GOLD;
                break;
        }
        return memStr;
    }

    public int revTransMem(String memStr){
        int memInt =-1;
        Customer cus = new Customer();
        if(memStr.equals(cus.MEMBERSHIP_JUNIOR)) memInt = 0;
        if(memStr.equals(cus.MEMBERSHIP_NORM)) memInt = 1;
        if(memStr.equals(cus.MEMBERSHIP_GOLD)) memInt = 2;

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
