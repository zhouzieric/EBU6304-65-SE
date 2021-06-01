package bean;

/**
 *This class is about the lecture of member live course
 * @author Wang Pei
 * @version 2.0
 */
public class Lecture {
    private String bookingId;
    private String content;
    private int mark;
    private String date;
    private String cancelDate ;

    public Lecture(){ }

    public String getBookingId(){
        return bookingId;
    }

    public void setBookingId(String bookingId){
        this.bookingId = bookingId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMark() { return mark; }

    public void setMark(int time) {
        this.mark = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return bookingId + ";" + content + ";" + mark + ";" + date + ";" + cancelDate;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }
}