package logic;

public class lectureNumToBox {

    public static int[] changeToLocation(int num){
        int x= num %6 ;
        int y= num/6 ;

        int[] outcome= {x,y};
        return outcome;
    }


}
