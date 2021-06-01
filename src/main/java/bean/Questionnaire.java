package bean;
/**
 *This class is about questionnarie that filled by member before booking live session
 * @author Wang Pei
 * @version 2.0
 */
public class Questionnaire{
    private String target;
    private String detail;
    private double weight;
    private double height;
    private int howOften;

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getHowOften() {
        return this.howOften;
    }

    public void setHowOften(int howOften) {
        this.howOften = howOften;
    }
}