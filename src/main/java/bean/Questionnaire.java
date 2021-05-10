package bean;

public class Questionnaire{
    private String target;
    private String detail;
    private double weight;
    private double height;
    private int howOften;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getHowOften() {
        return howOften;
    }

    public void setHowOften(int howOften) {
        this.howOften = howOften;
    }
}