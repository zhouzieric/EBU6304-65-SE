package logic;

import bean.Questionnaire;
import bean.Customer;
import pages.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.ParseException;

public class SwitchManager {
    private StandardFrame standardFrame;
    private int type;
    ChangeInfo changeInfo;

    public SwitchManager(StandardFrame standardFrame, int type){
        this.standardFrame=standardFrame;
        this.type=type;
        this.installAllListeners(standardFrame,type);
        changeInfo= new ChangeInfo(readAccLogin.readFile());
    }

    private void installAllListeners(StandardFrame standardFrame,int type){
        MenuPart menupart=standardFrame.getMenuPart();
        CardLayout CardManager = standardFrame.getCardManager();
        JPanel CardContainer = standardFrame.getCardContainer();
        if(type==MenuPart.MENU_CUSTOMER) {
            //以下是每一个监听器，没有顺序
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

            menupart.getCalendar().addActionListener((e -> {
                if(!Customer.MEMBERSHIP_JUNIOR.equals(changeInfo.readCusInfo().getMembership())){
                    CardManager.show(CardContainer, "customerCalendar");
                    CustomerCalendar customerCalendar = (CustomerCalendar) CardContainer.getComponent(5);
                    CusCalendarWhichWeek cusCalendarWhichWeek0 = (CusCalendarWhichWeek) customerCalendar.getComponentAt(0);
                    CusCalendarWhichWeek cusCalendarWhichWeek1 = (CusCalendarWhichWeek) customerCalendar.getComponentAt(1);
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

            }));
            menupart.getWantBook().addActionListener((e) -> {
                if(!Customer.MEMBERSHIP_JUNIOR.equals(changeInfo.readCusInfo().getMembership())){
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
            });

            menupart.getVideos().addActionListener(e -> {
                CardManager.show(CardContainer, "recordMarket");
            });

            menupart.getAccinfo().addActionListener(e -> {
                CardManager.show(CardContainer, "pi");
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

    private void checkedMeminLookOrder(int index){
        MenuPart menupart=standardFrame.getMenuPart();
        CardLayout CardManager = standardFrame.getCardManager();
        JPanel CardContainer = standardFrame.getCardContainer();
        if(!Customer.MEMBERSHIP_JUNIOR.equals(changeInfo.readCusInfo().getMembership())){
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
