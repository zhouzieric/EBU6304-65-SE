package pages;

import bean.Questionnaire;
import logic.Match;
import logic.QuestionListener;
import mdlaf.utils.MaterialColors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class QuestionaireController{
    private CalendarView belongsTo;

    private JFrame frame;
    private JPanel jp3,jp4,jp1,jp2,jp5,jp;
    private JButton jb1,jb2;
    private JProgressBar pro;
    private int which;
    private Questionnaire questionaire;
    private JTextField jtf1,jtf2,jtf3;
    private JRadioButton jrb1,jrb2,jrb3,jrb4,jrb5,jrb6,jrb7,jrb8,jrb9,jrb10,jrb11,jrb12,jrb13,jrb14,jrb15;
    private JLabel remind;
    private JTextArea detail;

    public QuestionaireController(){
        this.which = 1;
        this.questionaire = new Questionnaire();

        frame = new JFrame();
        remind = new JLabel("");
        remind.setForeground(MaterialColors.LIME_A700);
        jtf1 = new JTextField(10);
        jtf2 = new JTextField(10);
        jtf3 = new JTextField(10);

        jrb1 = new JRadioButton("Weight loss");
        jrb2 = new JRadioButton("Shaping");
        jrb11 = new JRadioButton("HIIT");
        jrb12 = new JRadioButton("Yoga");
        jrb13 = new JRadioButton("Strength");
        jrb14 = new JRadioButton("Aerobics");
        jrb15 = new JRadioButton("Tai Chi");

        //瑜伽，HIIT,强度，有氧运动，太极，减肥，塑性

        jrb3 = new JRadioButton("Everday");
        jrb4 = new JRadioButton("Twice a week");
        jrb5 = new JRadioButton("Once a week");
        jrb6 = new JRadioButton("Once two weeks");
        jrb7 = new JRadioButton("Longer thab above");

        jrb8 = new JRadioButton("Leg");
        jrb9 = new JRadioButton("Belly");
        jrb10 = new JRadioButton("Whole body");

    }

    public void thisPage()
    {
        jp = new JPanel();
        frame.setContentPane(jp);
        frame.setTitle("Questionnaire");
        frame.setBounds(400,200,800, 400);
        frame.setVisible(true);

        if(which ==1){
            JLabel jlb1,jlb2;
            jp1=new JPanel();
            jp2=new JPanel();
            jp3 = new JPanel();
            jp4=new JPanel();
            jp5 = new JPanel();
            pro = new JProgressBar();

            pro.setMinimum(0);
            pro.setMaximum(100);
            pro.setStringPainted(true);
            pro.setValue(which*20);


            jb2=new JButton("NEXT");

            jb2.addActionListener(new QuestionListener(this));

            jp.setLayout(new GridLayout(5,1));
            jp.add(jp4);

            jlb1=new JLabel("Your Height:");
            jlb2=new JLabel("Your Weight:");

            JLabel jlb3=new JLabel("cm");
            JLabel jlb4=new JLabel("kg");

            jp1.add(jlb1);
            jp1.add(jtf1);
            jp1.add(jlb3);

            jp2.add(jlb2);
            jp2.add(jtf2);
            jp2.add(jlb4);

            jp.add(jp1);
            jp.add(jp2);

            jp.add(jp5);
            jp.add(jp3);
            jp4.add(pro);
            jp3.add(jb2);
            jp5.add(remind);

        }else if(which ==2){
            JLabel jlb1;
            jp1 = new JPanel();
            jp2 = new JPanel();
            jp3 = new JPanel();
            jp4=new JPanel();
            jp5 = new JPanel();
            pro = new JProgressBar();

            pro.setMinimum(0);
            pro.setMaximum(100);
            pro.setStringPainted(true);
            pro.setValue(which*20);

            jb1=new JButton("BACK");
            jb2=new JButton("NEXT");

            jb2.addActionListener(new QuestionListener(this));
            jb1.addActionListener(new QuestionListener(this));

            jp.setLayout(new GridLayout(5,1));
            jp.add(jp4);

            jlb1 = new JLabel("Your target:");

            ButtonGroup bg = new ButtonGroup();

            bg.add(jrb1);
            bg.add(jrb2);
            bg.add(jrb11);
            bg.add(jrb12);
            bg.add(jrb13);
            bg.add(jrb14);
            bg.add(jrb15);

            jp2.add(jrb1);
            jp2.add(jrb2);
            jp2.add(jrb11);
            jp2.add(jrb12);
            jp2.add(jrb13);
            jp2.add(jrb14);
            jp2.add(jrb15);


            jp1.add(jlb1);

            jp.add(jp1);
            jp.add(jp2) ;

            jp.add(jp5);
            jp.add(jp3);
            jp4.add(pro);
            jp3.add(jb1);
            jp3.add(jb2);
            jp5.add(remind);

        }else if (which == 3){
            JLabel jlb1;
            jp1 = new JPanel();
            jp2 = new JPanel();
            jp3 = new JPanel();
            jp4=new JPanel();
            jp5=new JPanel();
            pro = new JProgressBar();

            pro.setMinimum(0);
            pro.setMaximum(100);
            pro.setStringPainted(true);
            pro.setValue(which*20);


            jb1=new JButton("BACK");
            jb2=new JButton("NEXT");

            jb2.addActionListener(new QuestionListener(this));
            jb1.addActionListener(new QuestionListener(this));

            jp.setLayout(new GridLayout(5,1));
            jp.add(jp4);

            if(questionaire.getTarget().equals("weightloss")) {
                jlb1 = new JLabel("Your target weight:");
                JLabel jlb4 = new JLabel("kg");
                jp1.add(jlb1);
                jp1.add(jtf3);
                jp1.add(jlb4);
            }else if(questionaire.getTarget().equals("shaping")){
                ButtonGroup bg = new ButtonGroup();
                bg.add(jrb8);
                bg.add(jrb9);
                bg.add(jrb9);
                bg.add(jrb10);

                jp2.add(jrb8);
                jp2.add(jrb9);
                jp2.add(jrb10);

                jlb1 = new JLabel("Body part:");
                jp1.add(jlb1);
            }else{
                detail = new JTextArea(45,30);
                detail.setBackground(Color.white);
                jlb1 = new JLabel("Detailed requirement:");
                jp1.add(jlb1);
                jp2.add(detail);
            }
            jp5.add(remind);
            jp.add(jp1);
            jp.add(jp2);
            jp.add(jp5);
            jp.add(jp3);
            jp4.add(pro);
            jp3.add(jb1);
            jp3.add(jb2);

        }else if(which == 4){
            JLabel jlb1;
            jp1=new JPanel();
            jp2 = new JPanel();
            jp3 = new JPanel();
            jp4=new JPanel();
            jp5=new JPanel();
            pro = new JProgressBar();

            pro.setMinimum(0);
            pro.setMaximum(100);
            pro.setStringPainted(true);
            pro.setValue(which*20);

            jb1=new JButton("BACK");
            jb2=new JButton("SUBMIT");

            jb2.addActionListener(new QuestionListener(this));
            jb1.addActionListener(new QuestionListener(this));

            jp.setLayout(new GridLayout(5,1));
            jp.add(jp4);

            jlb1 = new JLabel("How often do you exerciese?");
            ButtonGroup bg = new ButtonGroup();

            bg.add(jrb3);
            bg.add(jrb4);
            bg.add(jrb5);
            bg.add(jrb6);
            bg.add(jrb7);

            jp1.add(jrb3);
            jp1.add(jrb4);
            jp1.add(jrb5);
            jp1.add(jrb6);
            jp1.add(jrb7);

            jp2.add(jlb1);
            jp5.add(remind);
            jp.add(jp2);
            jp.add(jp1);

            jp.add(jp5);
            jp.add(jp3);
            jp4.add(pro);
            jp3.add(jb1);
            jp3.add(jb2);


        }else{
            JLabel jlb1 = new JLabel();
            jp1 = new JPanel();
            jp3 = new JPanel();
            jp4=new JPanel();
            pro = new JProgressBar();

            pro.setMinimum(0);
            pro.setMaximum(100);
            pro.setStringPainted(true);
            pro.setValue(20*which);

            jp.setLayout(new GridLayout(4,1));
            jp.add(jp4);

            jlb1 = new JLabel("Successfully!  Trainers appear NOW!!");

            jp1.add(jlb1);
            jp.add(jp1);

            jp.add(jp3);
            jp4.add(pro);
            JButton closeQ= new JButton("OK!");
            closeQ.addActionListener(new CloseAct());
            jp3.add(closeQ);


            Match match = new Match();
            System.out.println("this.getQuestionaire().getTarget()="+this.getQuestionaire().getTarget());
            System.out.println("match.get_trainers(this.getQuestionaire().getTarget())"+match.get_trainers(this.getQuestionaire().getTarget()));

            belongsTo.setAllTID(match.get_trainers(this.getQuestionaire().getTarget()));

        }
        jp.revalidate();
    }
    public Questionnaire getQuestionaire(){
        return this.questionaire;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getWhich() {
        return which;
    }

    public void setWhich(int which) {
        this.which = which;
    }

    public CalendarView getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(CalendarView belongsTo) {
        this.belongsTo = belongsTo;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getJp3() {
        return jp3;
    }

    public void setJp3(JPanel jp3) {
        this.jp3 = jp3;
    }

    public JPanel getJp4() {
        return jp4;
    }

    public void setJp4(JPanel jp4) {
        this.jp4 = jp4;
    }

    public JPanel getJp1() {
        return jp1;
    }

    public void setJp1(JPanel jp1) {
        this.jp1 = jp1;
    }

    public JPanel getJp2() {
        return jp2;
    }

    public void setJp2(JPanel jp2) {
        this.jp2 = jp2;
    }

    public JPanel getJp5() {
        return jp5;
    }

    public void setJp5(JPanel jp5) {
        this.jp5 = jp5;
    }

    public JPanel getJp() {
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JButton getJb1() {
        return jb1;
    }

    public void setJb1(JButton jb1) {
        this.jb1 = jb1;
    }

    public JButton getJb2() {
        return jb2;
    }

    public void setJb2(JButton jb2) {
        this.jb2 = jb2;
    }

    public JProgressBar getPro() {
        return pro;
    }

    public void setPro(JProgressBar pro) {
        this.pro = pro;
    }

    public void setQuestionaire(Questionnaire questionaire) {
        this.questionaire = questionaire;
    }

    public JTextField getJtf1() {
        return jtf1;
    }

    public void setJtf1(JTextField jtf1) {
        this.jtf1 = jtf1;
    }

    public JTextField getJtf2() {
        return jtf2;
    }

    public void setJtf2(JTextField jtf2) {
        this.jtf2 = jtf2;
    }

    public JTextField getJtf3() {
        return jtf3;
    }

    public void setJtf3(JTextField jtf3) {
        this.jtf3 = jtf3;
    }

    public JRadioButton getJrb1() {
        return jrb1;
    }

    public void setJrb1(JRadioButton jrb1) {
        this.jrb1 = jrb1;
    }

    public JRadioButton getJrb2() {
        return jrb2;
    }

    public void setJrb2(JRadioButton jrb2) {
        this.jrb2 = jrb2;
    }

    public JRadioButton getJrb3() {
        return jrb3;
    }

    public void setJrb3(JRadioButton jrb3) {
        this.jrb3 = jrb3;
    }

    public JRadioButton getJrb4() {
        return jrb4;
    }

    public void setJrb4(JRadioButton jrb4) {
        this.jrb4 = jrb4;
    }

    public JRadioButton getJrb5() {
        return jrb5;
    }

    public void setJrb5(JRadioButton jrb5) {
        this.jrb5 = jrb5;
    }

    public JRadioButton getJrb6() {
        return jrb6;
    }

    public void setJrb6(JRadioButton jrb6) {
        this.jrb6 = jrb6;
    }

    public JRadioButton getJrb7() {
        return jrb7;
    }

    public void setJrb7(JRadioButton jrb7) {
        this.jrb7 = jrb7;
    }

    public JRadioButton getJrb8() {
        return jrb8;
    }

    public void setJrb8(JRadioButton jrb8) {
        this.jrb8 = jrb8;
    }

    public JRadioButton getJrb9() {
        return jrb9;
    }

    public void setJrb9(JRadioButton jrb9) {
        this.jrb9 = jrb9;
    }

    public JRadioButton getJrb10() {
        return jrb10;
    }

    public void setJrb10(JRadioButton jrb10) {
        this.jrb10 = jrb10;
    }

    public JLabel getRemind() {
        return remind;
    }

    public void setRemind(JLabel remind) {
        this.remind = remind;
    }

    public JRadioButton getJrb11() {
        return jrb11;
    }

    public void setJrb11(JRadioButton jrb11) {
        this.jrb11 = jrb11;
    }

    public JRadioButton getJrb12() {
        return jrb12;
    }

    public void setJrb12(JRadioButton jrb12) {
        this.jrb12 = jrb12;
    }

    public JRadioButton getJrb13() {
        return jrb13;
    }

    public void setJrb13(JRadioButton jrb13) {
        this.jrb13 = jrb13;
    }

    public JRadioButton getJrb14() {
        return jrb14;
    }

    public void setJrb14(JRadioButton jrb14) {
        this.jrb14 = jrb14;
    }

    public JRadioButton getJrb15() {
        return jrb15;
    }

    public void setJrb15(JRadioButton jrb15) {
        this.jrb15 = jrb15;
    }

    public JTextArea getDetail() {
        return detail;
    }

    public void setDetail(JTextArea detail) {
        this.detail = detail;
    }

    private class CloseAct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            belongsTo.updateLeftList();
            try {
                belongsTo.updateRightTable("ready");
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        }
    }
}