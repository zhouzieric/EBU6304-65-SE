package bean;

public class Customer extends Member {
    private String membership;
    private String nickname;

    public static final String MEMBERSHIP_GOLD="gold";
    public static final String MEMBERSHIP_NORM="norm";
    public static final String MEMBERSHIP_JUNIOR="junior";


    public Customer(String nickname, String membership, String fname,String lname,String gender, String accountNo, String phone_num, String email_addr, String date_of_birth){
        this.membership = membership;
        this.accountNo = accountNo;
        this.date_of_birth = date_of_birth;
        this.email_addr = email_addr;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.phone_num = phone_num;

    }

    public Customer(){}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }







}
