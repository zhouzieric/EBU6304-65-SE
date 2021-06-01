
package logic;

import bean.Customer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class is used to calculate discount
 * based on the membership of the member,
 * the live training number booked, the trainer rank booked
 * and total price with full discount rule
 * @author Gui Jiayi
 * @version 2.0
 */
public class DiscountCalculator {
    //Used to calculate total price
    private ArrayList<Integer> rule_spent;
    private ArrayList<Integer> rule_minus;
    private float mem_discount;
    private int rPrice;
    private double totalPri;

    /**
     * For constructing without parameters
     */
    public DiscountCalculator(){}

    /**
     * Get the total price of all kinds of deduction and calculation of a booking
     * @param rank
     * the rank of the trainer booked
     * @param lec_num
     * number of live lectures booked
     * @return double
     * total price after calculation
     */
    public double calDiscount(int rank, int lec_num){
        String acc_login=readAccLogin.readFile();
        ChangeInfo changeInfo = new ChangeInfo(acc_login);
        Customer cus=changeInfo.readCusInfo();
        mem_discount=ReadFlexibleInfo.readMemPrice(cus.getMembership()); //get membership discount


        rPrice = ReadFlexibleInfo.readRankPrice(rank); System.out.println(rPrice);  //get rank price
        this.setRule_minus(ReadFlexibleInfo.readRule().getRule_minus());
        this.setRule_spent(ReadFlexibleInfo.readRule().getRule_spent());
        totalPri= lec_num*rPrice*mem_discount;  //get total price before full deduction
        System.out.println(totalPri);

        for(int i=rule_spent.size();i>0;i--){   //calculate the price under full reduction rule
            if(totalPri>=rule_spent.get(i-1)){
                totalPri= totalPri-rule_minus.get(i-1);
                System.out.println(rule_spent.get(i-1)+"-"+rule_minus.get(i-1));
                break;
            }
        }

        return this.totalPri;
    }

   /* public void readMemPrice(String membership){
        String filename="src/main/java/data/membership_discount.txt";
        try{
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String oneline=bufferedReader.readLine();

            while (oneline!=null){
                String readMembership = oneline.split(";")[0];
                if(readMembership.equals(membership)){
                    mem_discount = Float.parseFloat(oneline.split(";")[1]);
                    break;
                }else oneline=bufferedReader.readLine();
            }
            if(mem_discount==.0) System.out.println("Membership discount doesn't get");


            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readRankPrice(int rank){
        String filename="src/main/java/data/rank_price.txt";
        int rank_price=0;
        //Read Rank Info
        try{
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String oneline=bufferedReader.readLine();

            while (oneline!=null){
               int readRank = Integer.parseInt(oneline.split(";")[0]);
               if(readRank==rank){
                   rank_price= Integer.parseInt(oneline.split(";")[1]);
                   break;
               }
               else oneline = bufferedReader.readLine();
            }
            //if enter rank doesn't exist
            if(rank_price==0){
                System.out.println("Your passed rank doesn't exist");
            }

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rank_price;
    }

    public void readRule(){
        String filename="src/main/java/data/discount_rule.txt";
        //init
        rule_spent = new ArrayList<>();
        rule_minus = new ArrayList<>();

        try{
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String oneline=bufferedReader.readLine();

            while (oneline!=null){
                rule_spent.add(Integer.parseInt(oneline.split(";")[0]));
                rule_minus.add(Integer.parseInt(oneline.split(";")[1]));
                oneline=bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }*/

   public static void main(String[] args){
        DiscountCalculator dc = new DiscountCalculator();
        System.out.println(dc.calDiscount(2,4));
    }

    public float getMem_discount() {
        return mem_discount;
    }

    public ArrayList<Integer> getRule_spent() {
        return rule_spent;
    }

    public void setRule_spent(ArrayList<Integer> rule_spent) {
        this.rule_spent = rule_spent;
    }

    public ArrayList<Integer> getRule_minus() {
        return rule_minus;
    }

    public void setRule_minus(ArrayList<Integer> rule_minus) {
        this.rule_minus = rule_minus;
    }
}
