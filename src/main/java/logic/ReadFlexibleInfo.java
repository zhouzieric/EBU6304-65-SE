
package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 *  This class is a methods library
 *  to read and write from flexi_info file
 *  to get information that are used to build flexibility of our software
 * @author Gui Jiayi, Zhang Kezhou
 * @version 3.2
 */
public class ReadFlexibleInfo {

    /**
     * Get the discount of a given membership
     * @param membership
     * the membership name the member maintains
     * @return float
     * the discount the kind of membership benefit from
     */
    public static float readMemPrice(String membership){
       String memRow = readFile(1);
       float mem_discount = 0;
       ArrayList<String> memName = new ArrayList<>();
       ArrayList<String> memPrice = new ArrayList<>();

        if(memRow!=null){
           String[] splitRow = memRow.split(";");
           for(int i=0;i<splitRow.length;i++){   //split the row read to put name and discount in different arrays
               if((i%3)==0) memName.add(splitRow[i]);
               if((i%3)==1) memPrice.add(splitRow[i]);
           }
          mem_discount = Float.parseFloat(memPrice.get(memName.indexOf(membership)));
       }else System.out.println("Read MemPrice Null!");
        return mem_discount;
    }

    /**
     * Get the update fee from a lower level to this level
     * of a given membership
     * @param membership
     * the membership name the member maintains
     * @param updateNum
     * the level number the member want to update to
     * @return int
     *  fee to update to the given membership from lower level
     */
    public static int readMemUpdate(String membership, int updateNum){
        String memRow = readFile(1);
        int mem_update = 0;
        ArrayList<String> memName = new ArrayList<>();
        ArrayList<String> memUpdate = new ArrayList<>();

        if(memRow!=null){
            String[] splitRow = memRow.split(";");
            for(int i=0;i<splitRow.length;i++){ //split the row read to put name and update fee in different arrays
                if((i%3)==0) memName.add(splitRow[i]);
                if((i%3)==2) memUpdate.add(splitRow[i]);
            }
            int mem_order = memName.indexOf(membership);
//            System.out.println(mem_order);
            if(mem_order>=updateNum){
                for(int i=mem_order;i>mem_order-updateNum;i--){
                    mem_update= mem_update + Integer.parseInt(memUpdate.get(i-1));
                }
            }else{
                System.out.println("Error Input with Too Large Update");
            }
        }else System.out.println("Read MemPrice Null!");
        return mem_update;
    }

    /**
     * Get the price trainer can have for a live training
     * given the trainer rank
     * @param rank
     *  trainer's rank representing its capability
     * @return int
     *  price for a live training
     */
    public static int readRankPrice(int rank){
        int rank_price=0;
        String rankRow = readFile(0);
        ArrayList<Integer> rankR = new ArrayList<>();
        ArrayList<Integer> rankPrice = new ArrayList<>();

        if(rankRow!=null){
            String[] splitRow = rankRow.split(";");
            for(int i=0;i<splitRow.length;i++){ //split the row read to put name and price in different arrays
                if((i%2)==0) rankR.add(Integer.parseInt(splitRow[i]));
                if((i%2)==1) rankPrice.add(Integer.parseInt(splitRow[i]));
            }
            System.out.println(rank + "-------------------------------------------");
            System.out.println(rankR + "-------------------------------------------");
            rank_price = rankPrice.get(rankR.indexOf(rank));
        }else System.out.println("Read rankPrice Null!");

        return rank_price;
    }

    /**
     *  Get the full discount rule from file
     * @return DiscountCalculator
     * a DiscountCalculator object with array rule_spent and rule_minus set
     */
    public static DiscountCalculator readRule(){
        DiscountCalculator dc = new DiscountCalculator();
        String ruleRow = readFile(2);
        ArrayList<Integer> rule_spent = new ArrayList<>();
        ArrayList<Integer> rule_minus = new ArrayList<>();

        if(ruleRow!=null){
            String[] splitRow = ruleRow.split(";");
            for(int i=0;i<splitRow.length;i++){
                if((i%2)==0) rule_spent.add(Integer.parseInt(splitRow[i]));
                if((i%2)==1) rule_minus.add(Integer.parseInt(splitRow[i]));
            }
            dc.setRule_spent(rule_spent);
            dc.setRule_minus(rule_minus);
        }else System.out.println("Read rankPrice Null!");


        return dc;

    }

    /**
     * Get the integer token by knowing the name.
     * @param  membershipName
     * the membership name the member maintains
     * @return  int
     *the integer token
     */
    public static int getIntByStr(String membershipName){

        String[] str=ReadFlexibleInfo.readFile(2).split(";");
        ArrayList<String> levels=new ArrayList<>();
        int i=1;
        for(String a:str){
            if(i%2==1){
                levels.add(a);
            }
        }
        int num=0;
        String[] outcome=levels.toArray(new String[levels.size()]);
        int j=0;
        for(String b:outcome){
            if(b.equals(membershipName)){
                num=j;
            }
            j++;
        }

        return num;
    }

    /**
     * Read according row from flexi_info file
     * @param rowNum
     * The number of row the method needs to read(start from 0)
     * @return String
     * The whole row returned as a String
     */
    public static String readFile(int rowNum){
       String row = null;
        String filename = "src/main/java/data/flexi_info.txt";
        ArrayList<String> rowData = new ArrayList<>();
        try{
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String oneline=bufferedReader.readLine();

            while (oneline!=null){
                rowData.add(oneline);
                oneline=bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(rowNum==0) row=rowData.get(0);
        if(rowNum==1) row=rowData.get(1);
        if(rowNum==2) row=rowData.get(2);
        if(rowNum==3) row=rowData.get(3);
        if(rowNum==4) row=rowData.get(4);

        return row;
    }

}
