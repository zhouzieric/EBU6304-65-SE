package bean;
/**
 *This class is about gym trainer
 * @author Wang Pei
 * @version 2.0
 */
public class Trainer extends Member {
    private String pro;
    private int rank;

    //    T1;123456789lll;fff;rrr;male;12345678945;123@qq.com;2021-04-14;weightloss;1;
    public Trainer(String accountNo, String password, String fname, String lname, String gender, String phone_num, String email_addr, String date_of_birth, String pro, int rank){
        this.setAccountNo(accountNo);
        this.setPassword(password);
        this.setFname(fname);
        this.setLname(lname);
        this.setGender(gender);
        this.setPhone_num(phone_num);
        this.setEmail_addr(email_addr);
        this.setDate_of_birth(date_of_birth);
        this.pro = pro;
        this.rank = rank;
    }

    public Trainer(){}

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
