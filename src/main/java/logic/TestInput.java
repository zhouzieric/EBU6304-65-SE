package logic;

/**
 *This class is to test whether the input from questionnaire page is valid.
 * @author Wang Pei
 * @version 2.0
 */

public class TestInput{

    /**
     * Check whether weight input by user is valid.
     * @param  input
     * Weight input by user.
     * @return  String
     * If input is valid, return ok, else return the reason why it is invalid.
     */
    public String isWeight(String input){
        if(input.equals("")){
            return "Please input your weight";
        }
        double weight=0;
        try{
            weight = Double.parseDouble(input);
        }catch(Exception e){
            return "Please input a valid weight";
        }

        if(weight<= 30)
            return "The weight entered is too small";
        else if(weight >=200)
            return "The weight entered is too big";
        else
            return "ok";
    }
    /**
     * Check whether height input by user is valid.
     * @param  input
     * Height input by user.
     * @return  String
     * If input is valid, return ok, else return the reason why it is invalid.
     */

public String isHeight(String input){
        if(input.equals("")){
            return "Please input your height";
        }
        double weight=0;
        try{
            weight = Double.parseDouble(input);
        }catch(Exception e){
            return "Please input a valid height";
        }

        if(weight<= 130)
            return "The height entered is too small";
        else if(weight >=210)
            return "The height entered is too big";
        else
            return "ok";
    }
}