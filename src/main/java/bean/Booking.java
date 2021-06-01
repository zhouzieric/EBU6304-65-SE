package bean;

/**
 *This class is about the booking of member live course
 * @author Wang Pei
 * @version 4.0
 */
public class Booking {
    private String bookingID;
    private String cusID;
    private String traID;
    private String creatDate;
    private Integer status;
    private double price;
    private String feedback;
    private Integer star;
    private Questionnaire questionnaire;

    /**
     * Booking Class construction to initialize variable 'status'.
     */
    public Booking(){
        this.status = 1;
    }


    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getTraID() {
        return traID;
    }

    public void setTraID(String trainerID) {
        this.traID = trainerID;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    @Override
    public String toString() {
        return bookingID + ";" + cusID + ";" + traID + ";" + creatDate + ";" + status + ";" + price + ";" +
                questionnaire.getHeight() + ";" + questionnaire.getWeight() + ";" +
                questionnaire.getTarget() + ";" + questionnaire.getDetail() + ";" + questionnaire.getHowOften() + ";"
                + feedback + ";" + star;
    }
}
