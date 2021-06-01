package pages;

import logic.AdminEvent;

import logic.ReadFlexibleInfo;
import logic.changeIcon;
import mdlaf.components.button.MaterialButtonUI;

import javax.swing.*;
import java.awt.*;

/**
 * This page contains all the menu Settings after the user logs in.
 * @author Kezhou Zhang
 * @version 1.0.0
 */
public class MenuPart extends JMenuBar {
    private JTextField searchText;
    private JButton search;
    private JComboBox videoType;
    private JMenu pInfo ;
    private JLabel logo;

    private JMenuItem accinfo;
    private JMenuItem logOut;
    private JMenu Orders;
    private JMenuItem allOrders;
    private JMenuItem PAIDOrders ;
    private JMenuItem INOrders ;
    private JMenuItem FINOrders ;
    private JMenuItem DELOrders ;
    private JMenu calendarTWO;
    private JMenuItem calendar ;
    private JMenuItem wantBook ;
    private JButton videos;


    //管理员
    private JButton log_out;
    private JMenu info_manage;
    private JButton video_manage;
    private JMenuItem cus_info;
    private JMenuItem tra_info;
    private JMenu promotion;
    private JMenuItem add_rule;
    private JMenuItem modify_membership;
    private JMenuItem modify_rankp;
    private JButton upload_b;
    private JMenuItem feedback;

    //教练
    private JButton trainerCalendar;
//    private JMenu Orders;
//    private JMenuItem allOrders;
//    private JMenuItem PAIDOrders ;
//    private JMenuItem INOrders ;
//    private JMenuItem FINOrders ;
//    private JMenuItem DELOrders ;
//    private JButton log_out;




    public static int MENU_CUSTOMER=0;
    public static int MENU_TRAINER=1;
    public static int MENU_ADMIN=2;


    /**
     * For constructing a menu bar.
     * @param type
     * the type of menu
     */
    public MenuPart(int type) {
        logo=new JLabel();
        logo.setIcon(changeIcon.iconButton("src/main/image/logo.png",40,50));
        logo.setPreferredSize(new Dimension(40, 50));
        this.add(logo);
        if(type==MENU_CUSTOMER) {

            searchText = new JTextField("keyword(s)", 20);
            search = new JButton("search");        //搜索
            videoType = new JComboBox();//选择类型筛选
            pInfo = new JMenu("  Me  ");

            accinfo = new JMenuItem("MyAcount");
            logOut = new JMenuItem("Log out");
            Orders = new JMenu("My purchase");
            allOrders = new JMenuItem("All orders");
            PAIDOrders = new JMenuItem("Paid orders");
            INOrders = new JMenuItem("Ongoing orders");
            FINOrders = new JMenuItem("Finished orders");
            DELOrders = new JMenuItem("Canceled orders");
            calendarTWO = new JMenu("Calendar");
            calendar = new JMenuItem("My calendar");
            wantBook = new JMenuItem("Book My Trainer");
            videos = new JButton("Recordings");

            //localVideo = new JToggleButton("Local Video");
            // wantBook.setUI((MaterialButtonUI)MaterialButtonUI.createUI(wantBook));
            videoType.addItem("All Recording Types");//0
            String[] types= ReadFlexibleInfo.readFile(4).split(";");
            for(String str:types){
                videoType.addItem(str);
            }
//            videoType.addItem("Yoga"); //1
//            videoType.addItem("HIIT"); //2
//            videoType.addItem("Strength"); //3
//            videoType.addItem("Aerobics"); //4
//            videoType.addItem("Tai Chi"); //5
//            videoType.addItem("weightloss"); //6
//            videoType.addItem("shaping"); //7


            this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 3));
            this.add(pInfo);
            this.add(Orders);
            this.add(calendarTWO);
            this.add(videos);
            this.add(videoType);
            this.add(searchText);
            this.add(search);


            //this.add(localVideo);

            pInfo.addSeparator();
            pInfo.add(accinfo);
            pInfo.addSeparator();
            pInfo.add(logOut);

            Orders.add(allOrders);
            Orders.addSeparator();
            Orders.add(PAIDOrders);
            Orders.addSeparator();
            Orders.add(INOrders);
            Orders.addSeparator();
            Orders.add(FINOrders);
            Orders.addSeparator();
            Orders.add(DELOrders);


            calendarTWO.add(calendar);
            calendarTWO.addSeparator();
            calendarTWO.add(wantBook);
        }else if(type==MENU_ADMIN){//这里是管理员的菜单
            log_out = new JButton("Log Out");
            upload_b=new JButton("Upload Notice");

            info_manage=new JMenu("Information Management");
            cus_info=new JMenuItem("Customer Information");
            tra_info=new JMenuItem("   Trainer Information");
            feedback=new JMenuItem("        View Feedback");
            video_manage=new JButton("Video Management");

            promotion=new JMenu("Preferential Promotion");
            add_rule=new JMenuItem("   Full Reduction Rule");
            modify_membership=new JMenuItem("Membership Discount");
            modify_rankp=new JMenuItem("            Rank Price");
            this.add(log_out);
            this.add(video_manage);
            this.add(info_manage);
            this.add(promotion);
            this.add(upload_b);

            info_manage.add(cus_info);
            info_manage.addSeparator();
            info_manage.add(tra_info);
            info_manage.addSeparator();
            info_manage.add(feedback);

            promotion.add(modify_rankp);
            promotion.addSeparator();
            promotion.add(add_rule);
            promotion.addSeparator();
            promotion.add(modify_membership);



        }else if(type==MENU_TRAINER){//这里是教练的菜单，这里的组件的监听记得去SwitchManager里注册

            trainerCalendar = new JButton("Calendar");
            Orders = new JMenu("My orders");
            allOrders = new JMenuItem("All orders");
            PAIDOrders = new JMenuItem("Paid orders");
            INOrders = new JMenuItem("Ongoing orders");
            FINOrders = new JMenuItem("Finished orders");
            DELOrders = new JMenuItem("Canceled orders");
            log_out = new JButton("Log Out");

            this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 3));
            this.add(trainerCalendar);
            this.add(Orders);
            this.add(log_out);

            Orders.add(allOrders);
            Orders.addSeparator();
            Orders.add(PAIDOrders);
            Orders.addSeparator();
            Orders.add(INOrders);
            Orders.addSeparator();
            Orders.add(FINOrders);
            Orders.addSeparator();
            Orders.add(DELOrders);
        }
    }

    public JTextField getSearchText() {
        return searchText;
    }

    public JButton getSearch() {
        return search;
    }

    public JComboBox getVideoType() {
        return videoType;
    }

    public JMenu getpInfo() {
        return pInfo;
    }



    public JMenuItem getAccinfo() {
        return accinfo;
    }

    public JMenu getOrders() {
        return Orders;
    }

    public JMenuItem getAllOrders() {
        return allOrders;
    }

    public JMenuItem getWantBook() {
        return wantBook;
    }

    public JMenuItem getPAIDOrders() {
        return PAIDOrders;
    }

    public JMenuItem getINOrders() {
        return INOrders;
    }

    public JMenuItem getFINOrders() {
        return FINOrders;
    }

    public JMenuItem getDELOrders() {
        return DELOrders;
    }

    public JMenuItem getCalendar() {
        return calendar;
    }

    public JButton getVideos() {
        return videos;
    }

    public JLabel getLogo() {
        return logo;
    }

    public void setLogo(JLabel logo) {
        this.logo = logo;
    }

    public JMenuItem getLogOut() {
        return logOut;
    }

    public JButton getLog_out() {
        return log_out;
    }

    public void setLog_out(JButton log_out) {
        this.log_out = log_out;
    }

    public JMenu getInfo_manage() {
        return info_manage;
    }

    public void setInfo_manage(JMenu info_manage) {
        this.info_manage = info_manage;
    }

    public JButton getVideo_manage() {
        return video_manage;
    }

    public void setVideo_manage(JButton video_manage) {
        this.video_manage = video_manage;
    }

    public JMenuItem getCus_info() {
        return cus_info;
    }

    public void setCus_info(JMenuItem cus_info) {
        this.cus_info = cus_info;
    }

    public JMenuItem getTra_info() {
        return tra_info;
    }

    public void setTra_info(JMenuItem tra_info) {
        this.tra_info = tra_info;
    }

    public JMenu getPromotion() {
        return promotion;
    }

    public void setPromotion(JMenu promotion) {
        this.promotion = promotion;
    }

    public JMenuItem getAdd_rule() {
        return add_rule;
    }

    public void setAdd_rule(JMenuItem add_rule) {
        this.add_rule = add_rule;
    }

    public JMenuItem getModify_membership() {
        return modify_membership;
    }

    public void setModify_membership(JMenuItem modify_membership) {
        this.modify_membership = modify_membership;
    }

    public JMenuItem getModify_rankp() {
        return modify_rankp;
    }

    public void setModify_rankp(JMenuItem modify_rankp) {
        this.modify_rankp = modify_rankp;
    }

    public JButton getUpload_b() {
        return upload_b;
    }

    public void setUpload_b(JButton upload_b) {
        this.upload_b = upload_b;
    }

    public JMenuItem getFeedback() {
        return feedback;
    }

    public void setFeedback(JMenuItem feedback) {
        this.feedback = feedback;
    }

    public JButton getTrainerCalendar(){
        return  this.trainerCalendar;
    }
}
