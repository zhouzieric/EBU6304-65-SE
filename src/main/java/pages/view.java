package pages;

import logic.AdminViewInfo;
import logic.LoginRegisEvent;
import logic.SwitchManager;
import logic.checkDateOut;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialOceanicTheme;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Locale;

public class view {

	public static String fontName="NotoSansDisplay-Bold";

    public  void createAndShowGUI() throws UnsupportedLookAndFeelException, ParseException {
        checkDateOut checkDateOut = new checkDateOut();
        view.initGlobalFontSetting(fontName);
        StandardFrame frame = new StandardFrame("Customer Page",MenuPart.MENU_CUSTOMER);//一个带菜单的标准窗口

        JPanel CardContainer = frame.getCardContainer();//重新命名布局和容器
        CardLayout CardManager = frame.getCardManager();


        //以下面板都是并列地位
        RecordMarket recordMarket = new RecordMarket();
        OrderMenuContainer orderMenuContainer= new OrderMenuContainer();
        PersonalInfoController pi = new PersonalInfoController();
        showAPlayer showAPlayer = new showAPlayer();
        showAPlayer livePlayer = new showAPlayer();
        CustomerCalendar customerCalendar = new CustomerCalendar(frame);
        //为止

        CalendarView calendarView = new CalendarView();
        calendarView.setBelongsTo(frame);
        recordMarket.setBrotherPan(showAPlayer);
        recordMarket.setManager(CardManager);
        recordMarket.setContainer(CardContainer);
        showAPlayer.setBrotherPan(recordMarket);
        showAPlayer.setManager(CardManager);
        showAPlayer.setContainer(CardContainer);


        //作为card加入,不要随意改动顺序，有编号区分
        CardContainer.add(recordMarket,"recordMarket");//0
        CardContainer.add(orderMenuContainer,"orderMenuContainer");//1
        CardContainer.add(calendarView,"calendarView");//2
        CardContainer.add(showAPlayer,"showAPlayer");//3
        CardContainer.add(pi,"pi");//4
        CardContainer.add(customerCalendar,"customerCalendar");//5
        CardContainer.add(livePlayer,"livePlayer");//6


        //首先能看见的面板
        CardManager.show(CardContainer,"recordMarket");

        //安装所有监听器，在这之前必须保证所有面板都加入了，不然有的监听器安不上
        //这个实例不用调用方法，实例化之后就自动安装
        SwitchManager switchManager= new SwitchManager(frame,MenuPart.MENU_CUSTOMER);

        frame.setResizable(true);

        frame.setVisible(true);
    }

    public void openLogin() throws UnsupportedLookAndFeelException {
        view.initGlobalFontSetting(fontName);
        MetalLookAndFeel a= new MaterialLookAndFeel(new MaterialOceanicTheme());//主题
        UIManager.setLookAndFeel(a);


        //enter，登录注册面板
        EnterJpanel enterJpanel=new EnterJpanel();
        LoginJpanel loginJpanel=new LoginJpanel();
        RegisterJpanel registerJpanel=new RegisterJpanel();
        CustomerRegisJP customerRegisJP=new CustomerRegisJP();
        TrainerRegisJP trainerRegisJP=new TrainerRegisJP();
        CustomLoginJp customLoginJp=new CustomLoginJp();
        TraLoginJp traLoginJp=new TraLoginJp();
        AdminLoginJp adminLoginJp=new AdminLoginJp();
        EnterFrame enterFrame=new EnterFrame(enterJpanel,customLoginJp,traLoginJp,customerRegisJP,trainerRegisJP,adminLoginJp);

        //enter，登录注册窗口
        JFrame jFrame=new JFrame("Welcome");
        loginJpanel.setBelongsTo(jFrame);
        jFrame.setSize(1000, 800);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        Container contentPane=new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(enterFrame, BorderLayout.CENTER);
        jFrame.add(contentPane,BorderLayout.CENTER);



        //响应
        LoginRegisEvent loginRegisEvent=new LoginRegisEvent();
        loginRegisEvent.setjFrame(jFrame);
        loginRegisEvent.setCustomerRegisJP(customerRegisJP);
        loginRegisEvent.setEnterJpanel(enterJpanel);
        loginRegisEvent.setLoginJpanel(loginJpanel);
        loginRegisEvent.setRegisterJpanel(registerJpanel);
        loginRegisEvent.setCustomerRegisJP(customerRegisJP);
        loginRegisEvent.setTrainerRegisJP(trainerRegisJP);
        loginRegisEvent.setCustomLoginJp(customLoginJp);
        loginRegisEvent.setTraLoginJp(traLoginJp);
        loginRegisEvent.setAdminLoginJp(adminLoginJp);

        loginRegisEvent.setAdminLoginJp(adminLoginJp);
        loginRegisEvent.response(enterFrame,jFrame);

    }

    //打开管理员界面
    public void openadmin() throws UnsupportedLookAndFeelException {
        view.initGlobalFontSetting(fontName);
        MetalLookAndFeel a = new MaterialLookAndFeel(new MaterialOceanicTheme());//主题
        UIManager.setLookAndFeel(a);

        //管理员窗口
        StandardFrame standardFrame=new StandardFrame("ADMIN",MenuPart.MENU_ADMIN);
        standardFrame.setVisible(true);
        JPanel CardContainer = standardFrame.getCardContainer();
        CardLayout CardManager = standardFrame.getCardManager();

        //管理员面板
        AdminVideoController adminVideoController=new AdminVideoController();
        AdminViewInfo adminViewInfo=new AdminViewInfo();
        AdminManageInfoJP cusInfo=new AdminManageInfoJP(adminViewInfo.view_info("C"),adminViewInfo.getColumnTitle1(),"");
        AdminManageInfoJP traInfo=new AdminManageInfoJP(adminViewInfo.view_info("T"),adminViewInfo.getColumnTitle(),"TraRank");
        AdminManageInfoJP rankPrice=new AdminManageInfoJP(adminViewInfo.get_promotionInfo("rank_price",3),adminViewInfo.getColumnTitle_rankp(),"rankprice");
        AdminManageInfoJP memDiscount=new AdminManageInfoJP(adminViewInfo.get_promotionInfo("membership_discount",3),adminViewInfo.getColumnTitle_mem(),"memDis");
        AdminManageInfoJP disRule=new AdminManageInfoJP(adminViewInfo.get_promotionInfo("discount_rule",5),adminViewInfo.getColumnTile_dis(),"disRule");

        adminVideoController.setBelongsTo(standardFrame);
        adminVideoController.setContainer(CardContainer);
        adminVideoController.setManager(CardManager);
        cusInfo.setBelongsTo(standardFrame);
        cusInfo.setContainer(CardContainer);
        cusInfo.setManager(CardManager);
        traInfo.setBelongsTo(standardFrame);
        traInfo.setContainer(CardContainer);
        traInfo.setManager(CardManager);
        rankPrice.setBelongsTo(standardFrame);
        rankPrice.setContainer(CardContainer);
        rankPrice.setManager(CardManager);
        memDiscount.setBelongsTo(standardFrame);
        memDiscount.setContainer(CardContainer);
        memDiscount.setManager(CardManager);
        disRule.setBelongsTo(standardFrame);
        disRule.setContainer(CardContainer);
        disRule.setManager(CardManager);

        CardContainer.add(adminVideoController,"manage_video");
        CardContainer.add(cusInfo,"custom_info");
        CardContainer.add(traInfo,"train_info");
        CardContainer.add(rankPrice,"modify_rank_price");
        CardContainer.add(memDiscount,"modify_membership_discount");
        CardContainer.add(disRule,"modify_discount_rule");

        SwitchManager switchManager= new SwitchManager(standardFrame,MenuPart.MENU_ADMIN);

        LoginRegisEvent loginRegisEvent=new LoginRegisEvent();
        loginRegisEvent.setStandardFrame(standardFrame);

    }

    public static void initGlobalFontSetting(String filename) throws UnsupportedLookAndFeelException {

        Font TITLEFONT=null;
        //InputStream is = null;
        //BufferedInputStream bis = null;
        FileInputStream bis=null;
        String url="src/main/tffs/"+filename+".ttf";

        try {
            File file = new File(url);
             bis = new FileInputStream(file);
            //is = view.class.getResourceAsStream(url);
           // bis = new BufferedInputStream(is);
            TITLEFONT = Font.createFont(Font.TRUETYPE_FONT, bis).deriveFont(Font.BOLD,15f);
            System.out.println("字体是！"+TITLEFONT);


        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bis.close();
                //is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            FontUIResource fontRes = new FontUIResource(TITLEFONT);
        for(Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();){
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }

//如果想跳过登录把下面恢复
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ParseException {
        // 显示应用 GUI
    Locale.setDefault(Locale.ENGLISH);
        new view().createAndShowGUI();

    }
}