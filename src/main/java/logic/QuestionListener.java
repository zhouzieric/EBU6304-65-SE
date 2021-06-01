package logic;

import pages.QuestionaireController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
/**
 *This class is used to respond to user actions on the questionnaire page, collect user input.
 * @author Wang Pei
 * @version 2.0
 */
public class QuestionListener implements ActionListener {
    QuestionaireController frame;

    /**
     * Construction of this class
     * @param  frame QuestionaireController
     */
    public QuestionListener(QuestionaireController frame){
        this.frame = frame;
    }

    /**
     * actionPerformed of the actionlistener in questionnaire page.
     * @param  event ActionEvent
     */
    public void actionPerformed(ActionEvent event){
        if (event.getSource()== frame.getJb2()){
            String remind = getInput();
            //System.out.println(remind);
            if(remind.equals("ok")){
                frame.getRemind().setText("");
                frame.setWhich(frame.getWhich()+1);
            }else{
                frame.getRemind().setText(remind);
            }
            frame.thisPage();
        }
        else if(event.getSource()== frame.getJb1()){
            //System.out.println("Click jb1 "+frame.getWhich());
            frame.setWhich(frame.getWhich()-1);
            frame.thisPage();
        }
    }
    /**
     * Check whether user input is valid, if valid, then which++ so that it can go to next question, else remind user to answer this question against.
     * @return String
     * if the input is valid, reutrn ok, else return the remind information.
     */
    public String getInput(){
        if (frame.getWhich() == 1){
            String height="";
            String weight="";
            String remindH;
            String remindW;
            TestInput test = new TestInput();

            height = frame.getJtf1().getText();
            remindH = test.isHeight(height);

            weight = frame.getJtf2().getText();
            remindW = test.isWeight(weight);

            if(remindH.equals("ok")){
                if(remindW.equals("ok")){
                    frame.getQuestionaire().setHeight(Double.parseDouble(height));
                    frame.getQuestionaire().setWeight(Double.parseDouble(weight));
                    return "ok";
                }else
                    return remindW;
            }else{
                if(remindW.equals("ok"))
                    return remindH;
                else
                    return remindH+"   "+remindW;
            }
        }else if(frame.getWhich() == 2){
            ArrayList<JRadioButton> targets = frame.getTargetTypes();
            String target="";
            for(int i=0;i<targets.size();i++){
                System.out.println("是否读到了所有的选项"+targets.get(i).getText());
                if(targets.get(i).isSelected())
                    target = targets.get(i).getText();
            }
            if(target.equals(""))
                return "Please select your target";
            else{
                target=target.toLowerCase();
                frame.getQuestionaire().setTarget(target);
                return "ok";
            }
        }else if(frame.getWhich()==3){
            if(frame.getQuestionaire().getTarget().equals("weightloss")) {
                String weight="";
                String remindW;
                weight = frame.getJtf3().getText();

                TestInput test = new TestInput();
                remindW = test.isWeight(weight);

                if(remindW.equals("ok")){
                    if(Double.parseDouble(weight) >= frame.getQuestionaire().getWeight())
                        remindW="Your targer weight should be smaller than your real weight.";
                    else
                        frame.getQuestionaire().setDetail(frame.getJtf3().getText());
                }
                return remindW;
            }else if(frame.getQuestionaire().getTarget().equals("shaping")){
                String part;
                if (frame.getJrb8().isSelected()){
                    part="leg";
                    frame.getQuestionaire().setDetail(part);
                    return "ok";
                }else if (frame.getJrb9().isSelected()) {
                    part="belly";
                    frame.getQuestionaire().setDetail(part);
                    return "ok";
                }else if (frame.getJrb10().isSelected()) {
                    part = "wholebody";
                    frame.getQuestionaire().setDetail(part);
                    return "ok";
                }else{
                    return "Please select one.";
                }
            }else{
                if(frame.getDetail().getText().equals(""))
                    return "Please input your requirements.";
                else{
                    System.out.println("这是内容："+frame.getDetail().getText());
                    frame.getQuestionaire().setDetail(frame.getDetail().getText());
                    return "ok";
                }
            }

        }
        else if(frame.getWhich() == 4){
            int howOften=0;
            if (frame.getJrb3().isSelected()){
                howOften=1;
                frame.getQuestionaire().setHowOften(howOften);
                return "ok";
            }else if (frame.getJrb4().isSelected()) {
                howOften = 2;
                frame.getQuestionaire().setHowOften(howOften);
                return "ok";
            }else if (frame.getJrb5().isSelected()) {
                howOften = 3;
                frame.getQuestionaire().setHowOften(howOften);
                return "ok";
            }else if (frame.getJrb6().isSelected()) {
                howOften = 4;
                frame.getQuestionaire().setHowOften(howOften);
                return "ok";
            }else if (frame.getJrb6().isSelected()) {
                howOften = 5;
                frame.getQuestionaire().setHowOften(howOften);
                return "ok";
            }else{
                return "please select one";
            }
        }
        else{
            return "ok";
        }
    }
}
