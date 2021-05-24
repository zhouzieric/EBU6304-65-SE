package bean;

public class Customer extends Member {
    private String membership;
    private String nickname;

    public static final String MEMBERSHIP_GOLD="gold";
    public static final String MEMBERSHIP_NORM="norm";
    public static final String MEMBERSHIP_JUNIOR="junior";

//    C1;12w34fgh;123ddddd;Lisa;Moneban;female;12345678900;12312423@qq.com;2000-4-8;gold;
    public Customer(String accountNo, String password, String nickname,String fname,String lname,String gender, String phone_num, String email_addr, String date_of_birth, String membership){
        this.setAccountNo(accountNo);
        this.setPassword(password);
        this.nickname = nickname;
        this.setFname(fname);
        this.setLname(lname);
        this.setGender(gender);
        this.setPhone_num(phone_num);
        this.setEmail_addr(email_addr);
        this.setDate_of_birth(date_of_birth);
        this.membership = membership;
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
