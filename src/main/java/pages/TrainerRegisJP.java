package pages;

import logic.Login;
import logic.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//教练注册页面
public class TrainerRegisJP extends RegisterJpanel {

    private String pro_get="";
    private String rank_get="";

    private JLabel label71=new JLabel("Profession: ");
    private JLabel label72=new JLabel("Rank: ");
    private JLabel label73=new JLabel();

    private JComboBox pro=new JComboBox();
    private JComboBox rank=new JComboBox();

    public TrainerRegisJP(){

        super();
        pro.addItem("Weightloss");
        pro.addItem("Shaping");
        pro.addItem("Yoga");
        pro.addItem("Taichi");
        pro.addItem("Strength");
        pro.addItem("HIIT");
        pro.addItem("Aerobics");
        rank.addItem(1);
        rank.addItem(2);
        rank.addItem(3);

        label73.setForeground(Color.gray);

        getP211().setVisible(false);
        getP212().setVisible(false);
        getP3().setPreferredSize(new Dimension(0,60));
        getP71().add(label71);
        getP71().add(pro);
        getP72().add(label72);
        getP72().add(rank);
        getP72().add(label73);
    }

    public String getPro_get() {
        return pro_get;
    }

    public void setPro_get(String pro_get) {
        this.pro_get = pro_get;
    }

    public String getRank_get() {
        return rank_get;
    }

    public void setRank_get(String rank_get) {
        this.rank_get = rank_get;
    }

    public JComboBox getRank() {
        return rank;
    }

    public void setRank(JComboBox rank) {
        this.rank = rank;
    }

    public JComboBox getPro() {
        return pro;
    }

    public void setPro(JComboBox pro) {
        this.pro = pro;
    }
}
