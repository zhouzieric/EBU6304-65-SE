package logic;
/**
 * providing a operation to handle the num matrix.
 * @author Kezhou Zhang
 * @version 1.0.0
 */
public class lectureNumToBox {
    /**
     * to output a proper int[].
     * @param  num
     * change the lecture mark to a matrix coordinates
     * @return int[]
     * the coordinates
     */
    public static int[] changeToLocation(int num){
        int x= num %6 ;
        int y= num/6 ;
        return new int[]{x, y};
    }


}
