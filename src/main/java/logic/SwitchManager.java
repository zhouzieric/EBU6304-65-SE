package logic;

import bean.Questionnaire;
import bean.Customer;
import pages.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
/**
 *This class is used to centrally manage listening on MenuPart and assist the CardContainer display page.
 * @author Kezhou Zhang
 * @version 5.0.0
 */
public class SwitchManager {
    private StandardFrame standardFrame;
    private int type;
    ChangeInfo changeInfo;
    ArrayList<String> memRank;
    /**
     * For constructing a SwitchManager.
     * @param  standardFrame
     * standardFrame associated with it
     * @param type
     * the menupart type
     */
    public SwitchManager(StandardFrame standardFrame, int type){
        this.standardFrame=standardFrame;
        this.type=type;
        this.installAllListeners(standardFrame,type);
        changeInfo= new ChangeInfo(readAccLogin.readFile());
        memRank = Customer.getMemRank();
    }

    /**
     * Install all menu listeners
     * @param  standardFrame
     * standardFrame associated with it
     * @param type
     * the menupart type
     */
    private void installAllListeners(StandardFrame standardFrame,int type){

        MenuPart menupart=standardFrame.getMenuPart();
        CardLayout CardManager = standardFrame.getCardManager();
        JPanel CardContainer = standardFrame.getCardContainer();

        menupart.getLogo().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame company= new JFrame("About Us");
                JPanel content= new JPanel();
                JLabel title= new JLabel("<html><font size='18'> About us : </font></html>");
                JLabel foot=new JLabel("<html><font size='4'> copyright@<i>London Fitness<i> </font></html>");

                JPanel info=new JPanel();
                JPanel contractus=new JPanel();

                company.getContentPane().setLayout(new BorderLayout(10,10));
                company.getContentPane().add(content,BorderLayout.CENTER);
                company.getContentPane().add(title,BorderLayout.NORTH);
                company.getContentPane().add(foot,BorderLayout.SOUTH);

                content.setLayout(new BorderLayout(10,10));
                content.add(info,BorderLayout.CENTER);
                content.add(contractus,BorderLayout.SOUTH);

                Border border = BorderFactory.createBevelBorder(1);  //新建Border实例

                info.setBorder(BorderFactory.createTitledBorder(border, "Company Information"));
                contractus.setBorder(BorderFactory.createTitledBorder(border, "Contract Us"));
                info.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
                contractus.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));

                info.add(new JLabel("<html>\"London Fitness\" is a small gym operating in London.<br>"+"London Fitness is a VIP membership-based place that integrates sports, Fitness and entertainment.<br>" +
                        "London Fitness has perfect facilities, spacious and pleasant environment, <br>and professional course setting is leading the new fashion of leisure and Fitness.<br>" +
                        "London Fitness has advanced Fitness equipment, 100 square meters of spacious exercise hall,<br> exquisite and thoughtful nursery room, and elegant leisure bar,<br> all of which will create a perfect space for Fitness and entertainment for you.<br>" +
                        "The gym now offers: yoga, tai chi, aerobics, HIIT, weight loss and strength training and courses."+"</html>"));
                contractus.add(new JLabel("<html>If you have more questions about the software, you can contact with us through:<br>" +
                        "Email:   <i>londonfitness@outlook.com</i><br>" +
                        "Phone:   <i>86-400-099-6061</i><br></html>"));

                info.setPreferredSize(new Dimension(0,100));
                company.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                company.setVisible(true);
                company.setResizable(false);
                company.setBounds(400,100,900,500);
            }
        });

        if(type==MenuPart.MENU_CUSTOMER) {
            //以下是每一个监听器，没有顺序
            menupart.getAllOrders().addActionListener((e -> {

                this.checkedMeminLookOrder(0);
                RecordMarket r = (RecordMarket) CardContainer.getComponent(0);
                if(r.getBrotherPan().isPlaying()) {

                    r.getBrotherPan().getPlayerPane().getMediaPlayerComponent().release();
                }
            }));
            menupart.getPAIDOrders().addActionListener((e -> {

                this.checkedMeminLookOrder(1);
                RecordMarket r = (RecordMarket) CardContainer.getComponent(0);
                if(r.getBrotherPan().isPlaying()) {

                    r.getBrotherPan().getPlayerPane().getMediaPlayerComponent().release();
                }
            }));
            menupart.getINOrders().addActionListener((e -> {

                this.checkedMeminLookOrder(2);
                RecordMarket r = (RecordMarket) CardContainer.getComponent(0);
                if(r.getBrotherPan().isPlaying()) {

                    r.getBrotherPan().getPlayerPane().getMediaPlayerComponent().release();
                }
            }));
            menupart.getFINOrders().addActionListener((e -> {

                this.checkedMeminLookOrder(3);
                RecordMarket r = (RecordMarket) CardContainer.getComponent(0);
                if(r.getBrotherPan().isPlaying()) {

                    r.getBrotherPan().getPlayerPane().getMediaPlayerComponent().release();
                }
            }));
            menupart.getDELOrders().addActionListener((e -> {

                this.checkedMeminLookOrder(4);
                RecordMarket r = (RecordMarket) CardContainer.getComponent(0);
                if(r.getBrotherPan().isPlaying()) {

                    r.getBrotherPan().getPlayerPane().getMediaPlayerComponent().release();
                }
            }));

            menupart.getCalendar().addActionListener((e -> {

                if(!memRank.get(memRank.size()-1).equals(changeInfo.readCusInfo().getMembership())){
                    CardManager.show(CardContainer, "customerCalendar");
                    CTCalendar CTCalendar = (CTCalendar) CardContainer.getComponent(5);
                    CusCalendarWhichWeek cusCalendarWhichWeek0 = (CusCalendarWhichWeek) CTCalendar.getComponentAt(0);
                    CusCalendarWhichWeek cusCalendarWhichWeek1 = (CusCalendarWhichWeek) CTCalendar.getComponentAt(1);
                    try {
                        cusCalendarWhichWeek0.updateCalendar(0);
                        cusCalendarWhichWeek1.updateCalendar(1);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }else{
                    int opt = JOptionPane.showConfirmDialog(standardFrame,
                            "You're not a premium member yet. Would you like to become a Premium Member?", "Tips",
                            JOptionPane.YES_NO_OPTION);
                    if (opt == JOptionPane.YES_OPTION) {
                        //确认继续操作
                        CardManager.show(CardContainer, "pi");
                        PersonalInfoController a= (PersonalInfoController)CardContainer.getComponent(4);
                        a.getButtonMem().doClick();
                    }

                }
                RecordMarket r = (RecordMarket) CardContainer.getComponent(0);
                if(r.getBrotherPan().isPlaying()) {

                    r.getBrotherPan().getPlayerPane().getMediaPlayerComponent().release();
                }
            }));
            menupart.getWantBook().addActionListener((e) -> {

                if(!memRank.get(memRank.size()-1).equals(changeInfo.readCusInfo().getMembership())){
                    //JDialog questionnaire= new JDialog(standardFrame,"Fill in your information",true);
                    QuestionaireController question = new QuestionaireController();
                    Questionnaire questionaire = question.getQuestionaire();
                    question.setBelongsTo((CalendarView) CardContainer.getComponent(2));
                    question.getBelongsTo().setQuestionaire(questionaire);
                    question.getFrame().addWindowFocusListener(new WindowFocusListener() {
                        public void windowGainedFocus(WindowEvent e) {
                        }

                        public void windowLostFocus(WindowEvent e) {
                            e.getWindow().toFront();
                        }
                    });
                    question.thisPage();
                    CardManager.show(CardContainer, "calendarView");
                }else{
                    int opt = JOptionPane.showConfirmDialog(standardFrame,
                            "You're not a premium member yet. Would you like to become a Premium Member?", "Tips",
                            JOptionPane.YES_NO_OPTION);
                    if (opt == JOptionPane.YES_OPTION) {
                        //确认继续操作
                        CardManager.show(CardContainer, "pi");
                        PersonalInfoController a= (PersonalInfoController)CardContainer.getComponent(4);
                        a.getComponent(2);
                    }

                }
                RecordMarket r = (RecordMarket) CardContainer.getComponent(0);
                if(r.getBrotherPan().isPlaying()) {

                    r.getBrotherPan().getPlayerPane().getMediaPlayerComponent().release();
                }
            });

            menupart.getVideos().addActionListener(e -> {
                CardManager.show(CardContainer, "recordMarket");
                RecordMarket r=(RecordMarket)CardContainer.getComponent(0);
                r.updateTheMarket(0);
                if(r.getBrotherPan().isPlaying()) {
                    r.getBrotherPan().getPlayerPane().getMediaPlayerComponent().release();
                }

            });

            menupart.getAccinfo().addActionListener(e -> {

                CardManager.show(CardContainer, "pi");
                RecordMarket r = (RecordMarket) CardContainer.getComponent(0);
                if(r.getBrotherPan().isPlaying()) {

                    r.getBrotherPan().getPlayerPane().getMediaPlayerComponent().release();
                }
            });

            menupart.getLogOut().addActionListener(e -> {

                int opt = JOptionPane.showConfirmDialog(standardFrame,
                        "Are you sure you want to log out?", "Confirm log out",
                        JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.YES_OPTION) {
                    //确认继续操作
                    try {
                        new view().openLogin();
                    } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
                        unsupportedLookAndFeelException.printStackTrace();
                    }
                    standardFrame.dispose();
                }
            });
            menupart.getVideoType().addItemListener(e -> {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    RecordMarket a= (RecordMarket)CardContainer.getComponent(0);
                    if (e.getItem().equals("All Recording Types") ) {  // 查看触发的选项

                        a.updateTheMarket(0);
                    }  else {
                        String[] types=ReadFlexibleInfo.readFile(4).split(";");
                        int i=1;
                        for(String str:types){
                            if(e.getItem().equals(str) ){a.updateTheMarket(i);}
                            i++;
                        }


                    }
                }
                CardManager.show(CardContainer, "recordMarket");
                RecordMarket r = (RecordMarket) CardContainer.getComponent(0);
                if(r.getBrotherPan().isPlaying()) {

                    r.getBrotherPan().getPlayerPane().getMediaPlayerComponent().release();
                }
            });

            menupart.getSearchText().addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    menupart.getSearchText().setText("");
                }

                @Override
                public void focusLost(FocusEvent e) {
                   // menupart.getSearchText().setText("Keyword(s)");
                }
            });
            menupart.getSearch().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    String content=menupart.getSearchText().getText();
                    System.out.println("拿到"+content);
                    ArrayList<String[]> videos= searchVideos.searchAllvideos();
                    ArrayList<String> names=new ArrayList<>();
                    for(String[] str:videos){
                        if(str[0].contains(content)){
                            names.add(str[0]);
                            System.out.println("加入"+str[0]);
                        }
                    }
                    RecordMarket r = (RecordMarket) CardContainer.getComponent(0);
                    r.updateTheMarket(names);
                }
            });

        }else if(type==MenuPart.MENU_ADMIN){//在这写管理员部分菜单监听
            menupart.getVideo_manage().addActionListener(e -> {
                CardManager.show(CardContainer, "manage_video");
            });
            menupart.getCus_info().addActionListener(e -> {
                CardManager.show(CardContainer, "custom_info");
            });
            menupart.getTra_info().addActionListener(e -> {
                CardManager.show(CardContainer, "train_info");
            });
            menupart.getModify_rankp().addActionListener(e -> {
                CardManager.show(CardContainer, "modify_rank_price");
            });
            menupart.getModify_membership().addActionListener(e -> {
                CardManager.show(CardContainer, "modify_membership_discount");
            });
            menupart.getAdd_rule().addActionListener(e -> {
                CardManager.show(CardContainer, "modify_discount_rule");
            });
            menupart.getUpload_b().addActionListener(e -> {
                CardManager.show(CardContainer, "upload_notice");
            });
            menupart.getFeedback().addActionListener(e -> {
                CardManager.show(CardContainer, "feedback");
            });

            menupart.getLog_out().addActionListener(e -> {
                int opt = JOptionPane.showConfirmDialog(standardFrame,
                        "Are you sure you want to log out?", "Confirm log out",
                        JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.YES_OPTION) {
                    //确认继续操作
                    try {
                        new view().openLogin();
                    } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
                        unsupportedLookAndFeelException.printStackTrace();
                    }
                    standardFrame.dispose();
                }
            });
        }else if (type==MenuPart.MENU_TRAINER){//在这写教练部分菜单监听
            menupart.getTrainerCalendar().addActionListener((e -> {
                CardManager.show(CardContainer, "trainerCalendar");
                CTCalendar CTCalendar = (CTCalendar) CardContainer.getComponent(0);
                TraCalendarWhichWeek traCalendarWhichWeek0 = (TraCalendarWhichWeek) CTCalendar.getComponentAt(0);
                TraCalendarWhichWeek traCalendarWhichWeek1 = (TraCalendarWhichWeek) CTCalendar.getComponentAt(1);
                try {
                    traCalendarWhichWeek0.updateCalendar(0);
                    traCalendarWhichWeek1.updateCalendar(1);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }));

            menupart.getAllOrders().addActionListener((e -> {
                this.checkedMeminLookOrder(0);
            }));
            menupart.getPAIDOrders().addActionListener((e -> {
                this.checkedMeminLookOrder(1);
            }));
            menupart.getINOrders().addActionListener((e -> {
                this.checkedMeminLookOrder(2);
            }));
            menupart.getFINOrders().addActionListener((e -> {
                this.checkedMeminLookOrder(3);
            }));
            menupart.getDELOrders().addActionListener((e -> {
                this.checkedMeminLookOrder(4);
            }));

            menupart.getLog_out().addActionListener(e -> {
                int opt = JOptionPane.showConfirmDialog(standardFrame,
                        "Are you sure you want to log out?", "Confirm log out",
                        JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.YES_OPTION) {
                    //确认继续操作
                    try {
                        new view().openLogin();
                    } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
                        unsupportedLookAndFeelException.printStackTrace();
                    }
                    standardFrame.dispose();
                }
            });

        }
    }
    /**
     * Check membership status, if not, then boot to upgrade
     * @param  index
     * the page index customer selects
     */
    private void checkedMeminLookOrder(int index){
        MenuPart menupart=standardFrame.getMenuPart();
        CardLayout CardManager = standardFrame.getCardManager();
        JPanel CardContainer = standardFrame.getCardContainer();
        if(!memRank.get(memRank.size()-1).equals(changeInfo.readCusInfo().getMembership())){
            CardManager.show(CardContainer, "orderMenuContainer");
            OrderMenuContainer a = (OrderMenuContainer) CardContainer.getComponent(1);
            a.setSelectedIndex(index);
        }else{
            int opt = JOptionPane.showConfirmDialog(standardFrame,
                    "You're not a premium member yet. Would you like to become a Premium Member?", "Tips",
                    JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                //确认继续操作
                CardManager.show(CardContainer, "pi");
                PersonalInfoController a= (PersonalInfoController)CardContainer.getComponent(4);
                a.getButtonMem().doClick();
            }

        }
    }

}
