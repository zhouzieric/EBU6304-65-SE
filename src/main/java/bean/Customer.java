
package bean;

import logic.ReadFlexibleInfo;

import java.util.ArrayList;
/**
 * This class is a bean class
 * for Customer Member to store information
 * @author Gui Jiayi
 * @version 1.0
 */
public class Customer extends Member {
    private String membership;
    private String nickname;


    /**
     * This is a constructor to pass Customer parameters
     * @param accountNo
     * account number customer possess
     * @param password
     * password related to the account number
     * @param nickname
     * name where customer use to replace their real name online
     * @param fname
     * customer's first name
     * @param lname
     * customer's last name
     * @param gender
     * customer's gender(male/female)
     * @param phone_num
     * customer's phone number
     * @param email_addr
     * customer's email address
     * @param date_of_birth
     * customer's birthday
     * @param membership
     * customer's current state of member level
     */
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

    /**
     * For constructing without parameters
     */
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

    /**
     * Get the membership name with order from flexi_info file
     * @return ArrayList
     * the membership name in arraylist as string sequence
     */
    public static ArrayList<String> getMemRank(){
        ArrayList<String> memRank = new ArrayList<>();
        String memRow = ReadFlexibleInfo.readFile(1);
        String[] memAll = memRow.split(";");
        for(int i=0;i<memAll.length;i++){
            if(i%3==0) memRank.add(memAll[i]);      //from high level to low level
        }
        return memRank;
    }
    /**
     * Get the membership name with reverse order from flexi_info file
     * @return ArrayList
     * the membership name in arraylist as string sequence
     */
    public static ArrayList<String> getRevMemRank(){
        ArrayList<String> memRankrev = getMemRank();
        ArrayList<String> memRank = new ArrayList<>();
        for(int i=memRankrev.size()-1;i>=0;i--){
            memRank.add(memRankrev.get(i));
        }
        return memRank;
    }





}
