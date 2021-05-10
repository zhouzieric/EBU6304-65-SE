package pages;

import bean.Questionnaire;

import logic.CusCalendarListener;
import logic.Match;
import logic.getNextWeek;
import logic.lectureNumToBox;

import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;

public class CalendarView extends JPanel {

    private JFrame belongsTo;
    private getNextWeek getNextWeek=new getNextWeek();
    private JPanel weekTable;
    private JLabel tad = new JLabel("Time/Day");
    private JLabel w1 = new JLabel("<html>"+"Monday<br>"+getNextWeek.getNextWeekday(0)+"</html>");
    private JLabel w2 = new JLabel("<html>"+"Tuesday<br>"+getNextWeek.getNextWeekday(1)+"</html>");
    private JLabel w3 = new JLabel("<html>"+"Wednesday<br>"+getNextWeek.getNextWeekday(2)+"</html>");
    private JLabel w4 = new JLabel("<html>"+"Thursday<br>"+getNextWeek.getNextWeekday(3)+"</html>");
    private JLabel w5 = new JLabel("<html>"+"Friday<br>"+getNextWeek.getNextWeekday(4)+"</html>");
    private JLabel w6 = new JLabel("<html>"+"Saturday<br>"+getNextWeek.getNextWeekday(5)+"</html>");
    private JLabel w7 = new JLabel("<html>"+"Sunday<br>"+getNextWeek.getNextWeekday(6)+"</html>");
    private JLabel[] weektitle={tad,w1,w2,w3,w4,w5,w6,w7};

    private JLabel t1 = new JLabel("9:00-10:00");
    private JLabel t2 = new JLabel("10:30-11:30");
    private JLabel t3 = new JLabel("14:00-15:00");
    private JLabel t4 = new JLabel("15:30-16:30");
    private JLabel t5 = new JLabel("19:00-20:00");
    private JLabel t6 = new JLabel("20:30-21:30");
    private JCheckBox[][] boxTable = new JCheckBox[6][7];
    private JLabel[] timeTitle = {t1,t2,t3,t4,t5,t6};

    private JPanel inSelectT;
    private JButton comfirm;
    private JPanel rightP;

    private ArrayList<String> AllTID=null;
    private ArrayList<Integer> selectedLecturesNum;
    private Questionnaire questionaire=null;
    private String cusID;
    private String TID;
    private int level;

    ReservationPanel reservationPanel;
    JDialog checkBill;


    public CalendarView() throws ParseException {

        JSplitPane splitPane = new JSplitPane();//母板
        JScrollPane selectT = new JScrollPane();//左
      inSelectT= new JPanel();
       selectT.getViewport().add(inSelectT);
        rightP= new JPanel();//右
         weekTable= new JPanel();


//        JButton comfirm = new JButton("Comfirm Selected");
//        comfirm.addActionListener((e)->{
//            ReservationPanel reservationPanel=new ReservationPanel();
//            reservationPanel.setSelected(checkLectureSelected());
//            reservationPanel.revalidate();//setcusID,setTID,setlevel,setquestionnaire
//            reservationPanel.setTID(TID);
//            reservationPanel.setSelectedLecturesNum(selectedLecturesNum);
//            JDialog checkBill = new JDialog(belongsTo,"Check the Reservation",true);
//            checkBill.setBounds(400,200,600,500);
//            //checkBill.dispose();
//
//            checkBill.add(reservationPanel);
//            checkBill.setVisible(true);
//        });




        // 设置左右两边显示的组件
        splitPane.setLeftComponent(selectT);
        splitPane.setRightComponent(rightP);
        // 拖动分隔条时连续重绘组件
        splitPane.setContinuousLayout(true);
        // 设置分隔条的初始位置
        splitPane.setDividerLocation(230);
        rightP.setLayout(new BorderLayout());
       // rightP.add(comfirm,BorderLayout.SOUTH);
        rightP.add(weekTable,BorderLayout.CENTER);
      //  comfirm.setBackground((Color) MaterialColors.AMBER_600);
//
        //用竖不用横
        selectT.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        selectT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.setLayout(new BorderLayout());
        this.add(splitPane,BorderLayout.CENTER);
        splitPane.setPreferredSize(new Dimension(0,0));
        //让教练竖着排


        inSelectT.setLayout(new BoxLayout(inSelectT,BoxLayout.Y_AXIS));

        weekTable.setLayout(new GridLayout(7,8,5,5));


       this.updateRightTable("none");
        this.updateLeftList();//默认显示




    }


    public void updateRightTable(String state) throws ParseException {
        if(state.equals("none")){
            weekTable.removeAll();
            JLabel NoneNote= new JLabel("Fill in your information, then we will GO!");
            weekTable.add(NoneNote);

        }else if(state.equals("ready")){
            weekTable.removeAll();
            JLabel NoneNote= new JLabel("Now you can select your trainer");
            weekTable.add(NoneNote);


        }else{
            //       JCheckBox x1y2 = new JCheckBox("button");x1y2.setBackground((Color) MaterialColors.ORANGE_A100);
            weekTable.removeAll();
            for (JLabel w : weektitle) {

                weekTable.add(w);
            }
            //显示出来就是数组顺序的初始化
            for (int x = 0; x < 6; x++) {
                weekTable.add(timeTitle[x]);
                for (int y = 0; y < 7; y++) {
                    boxTable[x][y] = new JCheckBox("Availiable");
                    boxTable[x][y].setBackground(MaterialColors.ORANGE_900);
                    weekTable.add(boxTable[x][y]);
                    this.revalidate();
                }
            }
            //这里打算写读到的已经被订的课
            ArrayList<String> AllLecturesNum = new Match().get_lectures(TID);
            for(String showLec:AllLecturesNum){
                int[] TheLocation =lectureNumToBox.changeToLocation(Integer.parseInt(showLec));
                boxTable[TheLocation[0]][TheLocation[1]].setEnabled(false);
                boxTable[TheLocation[0]][TheLocation[1]].setText("Not Availiable");
                boxTable[TheLocation[0]][TheLocation[1]].setBackground(MaterialColors.BROWN_900);
            }
            ArrayList<Integer> AllExsitedLecturenums=Match.CustomerIsNotFree();
            for(int nums:AllExsitedLecturenums){
                int[] TheLocation =lectureNumToBox.changeToLocation(nums);

                    boxTable[TheLocation[0]][TheLocation[1]].setEnabled(false);
                    boxTable[TheLocation[0]][TheLocation[1]].setText("Not Free");
                    boxTable[TheLocation[0]][TheLocation[1]].setBackground(MaterialColors.BROWN_900);

            }
            comfirm = new JButton("Comfirm Selected Lectures!");
            rightP.add(comfirm,BorderLayout.SOUTH);

            comfirm.setForeground(MaterialColors.TEAL_A700);

            //comfirm.setBackground((Color) MaterialColors.AMBER_600);
            comfirm.addActionListener((e)->{

               // String useless=checkLectureSelected();
                if(!"NOT SELECTED".equals(checkLectureSelected())) {
                    try {
                        if(!Match.checkIfinflict(this.selectedLecturesNum)) {
                            System.out.println("支付页面得到了：TID：" + this.TID + "LEVEL:" + Match.getTrainerInfo(this.TID)[2] + "questionnaire:" + questionaire + "选择的课程" + selectedLecturesNum);
                            reservationPanel = new ReservationPanel(this.TID, Integer.parseInt(Match.getTrainerInfo(this.TID)[2]), selectedLecturesNum, this);
                            reservationPanel.setSelected(checkLectureSelected());
                            reservationPanel.getAddbooking().setLevel(Integer.parseInt(Match.getTrainerInfo(this.TID)[2]));
                            //reservationPanel.setLevel(Integer.parseInt(Match.getTrainerInfo(TID)[2]));
                            reservationPanel.getAddbooking().setQuestionaire(questionaire);
                            reservationPanel.getAddbooking().setTID(this.TID);
                            reservationPanel.getAddbooking().setSelectedLecturesNum(selectedLecturesNum);
                            reservationPanel.revalidate();
    //                reservationPanel.setSelectedLecturesNum(selectedLecturesNum);
                            checkBill = new JDialog(belongsTo, "Check the Reservation", true);
                            checkBill.setBounds(400, 200, 600, 500);
                            //checkBill.dispose();

                            checkBill.add(reservationPanel);
                            checkBill.setVisible(true);
                            checkBill.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosing(WindowEvent e) {
                                    super.windowClosing(e);
                                    JDialog dia = (JDialog) e.getSource();
                                    dia.dispose();
                                }
                            });
                        }else{

                            JOptionPane.showMessageDialog(this,"You are not free during this time period!!");
                        }
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(this,"You haven't selected lectures!!");
                }
            });

        }


    }

    public void updateLeftList(){
        if(AllTID!=null) {
            //长度取决于搜到多少教练和一个窗口能显示多少教练，所以这里应该先得到获取结果
            int num = AllTID.size();
            int lim = 7;

            //尽量让窗口长度合适
            if (num < lim)
                inSelectT.setPreferredSize(new Dimension(0, StandardFrame.frameH));
            else
                inSelectT.setPreferredSize(new Dimension(0, StandardFrame.frameH + StandardFrame.frameH * (num / lim)));

            inSelectT.removeAll();
            for (String showT : AllTID) {
                //这里写读取教练信息
                //首先我需要读取，然后放到一个二维数组里，我需要四个信息:ID，名字，特长，等级，最后一个是更新的表
                String[] trainerInfo =Match.getTrainerInfo(showT);
                inSelectT.add(Box.createVerticalStrut(10));

                inSelectT.add(new trainerLeftCards(showT, trainerInfo[0], trainerInfo[1], trainerInfo[2], this));

                inSelectT.revalidate();
            }





        }else{
            JLabel NoneNote= new JLabel("Fill in your information, then we will GO!");
            inSelectT.add(NoneNote);
        }


    }

    public void setBelongsTo(JFrame jFrame){
        this.belongsTo=jFrame;

    }

    public Questionnaire getQuestionaire() {
        return questionaire;
    }

    public void setQuestionaire(Questionnaire questionaire) {
        this.questionaire = questionaire;
    }

    private String checkLectureSelected(){
        String selected="<html>";
        String str="";
        ArrayList<Integer> selectedLecturesNum =new ArrayList<Integer>();


        for(int x=0;x<6;x++){

            for(int y=0;y<7;y++) {
                if(boxTable[x][y].isSelected()){
                    selectedLecturesNum.add(y*6+x);
                   str = new getNextWeek().displayTP(y*6+x);
                    selected=selected+"<br>"+str;}
            }
        }
        this.selectedLecturesNum=selectedLecturesNum;
        System.out.println("课程号们是"+this.selectedLecturesNum);


        selected=selected+"</html>";
       if(selected.equals("<html></html>")){return "NOT SELECTED";}

        return selected;

    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public JFrame getBelongsTo() {
        return belongsTo;
    }

    public ArrayList<String> getAllTID() {
        return AllTID;
    }

    public void setAllTID(ArrayList<String> allTID) {
        AllTID = allTID;
        this.updateLeftList();
    }

    public ReservationPanel getReservationPanel() {
        return reservationPanel;
    }

    public void setReservationPanel(ReservationPanel reservationPanel) {
        this.reservationPanel = reservationPanel;
    }

    public JDialog getCheckBill() {
        return checkBill;
    }

    public void setCheckBill(JDialog checkBill) {
        this.checkBill = checkBill;
    }
}
