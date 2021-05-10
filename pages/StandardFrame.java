package pages;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialOceanicTheme;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;

public class StandardFrame extends JFrame {//new这个类时，根据输入的不同的类型，他将显示不同的menu，但都是卡片布局
                                            //menupart类包含静态类型变量，直接用就行，【谁直接写数谁就死定了】

    final static public int frameH=700;
    final static public int frameW =1200;

    private MenuPart menuPart;
    private JPanel CardContainer;
    private CardLayout CardManager;


    public StandardFrame(String title, int type) throws UnsupportedLookAndFeelException {
        super(title);
        MetalLookAndFeel a= new MaterialLookAndFeel(new MaterialOceanicTheme());//主题
        UIManager.setLookAndFeel(a);

        menuPart = new MenuPart(type);
        CardContainer= new JPanel();
        CardManager = new CardLayout(0,0);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口的一些设置
        this.setBounds(150,20,StandardFrame.frameW,StandardFrame.frameH);

        this.setJMenuBar(menuPart);//绑定菜单

        this.setContentPane(CardContainer);//绑定卡片布局

        CardContainer.setLayout(CardManager);//卡片布局管理器




    }

    public MenuPart getMenuPart() {
        return menuPart;
    }

    public JPanel getCardContainer() {
        return CardContainer;
    }

    public CardLayout getCardManager() {
        return CardManager;
    }


}
