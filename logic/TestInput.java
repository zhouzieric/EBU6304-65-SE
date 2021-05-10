package logic;

public class TestInput{
    //weight,height,detail
    //包含不是数字的；明显不在范围内的
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