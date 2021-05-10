package logic;

import pages.AdminManageInfoJP;
import pages.MenuPart;
import pages.StandardFrame;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;


//处理管理员页面切换的类
public class AdminEvent implements ActionListener, MouseListener, TableModelListener {

    private StandardFrame standardFrame;
    private MenuPart menuPart;
    private AdminManageInfoJP adminManageInfoJp;

   private String type;
    //暂时不用
    @Override
    public void mouseClicked(MouseEvent e) {
//        int row=adminManageInfoJp.getCus_table().getSelectedRow();
//        int col=adminManageInfoJp.getCus_table().getSelectedColumn();
//        System.out.println(adminManageInfoJp.getCus_table().getValueAt(row,col));
    }

    //暂时不用
    @Override
    public void tableChanged(TableModelEvent e) {
//        int row=adminManageInfoJp.getCus_table().getSelectedRow();
//        int col=adminManageInfoJp.getCus_table().getSelectedColumn();
//        System.out.println(adminManageInfoJp.getCus_table().getValueAt(row,col));
    }

    //修改文件中的信息的方法
    public void modify(int row, String value,String filename){
        try {
            //src\main\java\data\discount_rule.txt
            File file = new File("src/main/java/data/"+filename+".txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String oLine = br.readLine();
            String[] info;
            String content= "";
            int j=0;
            while(oLine!=null) {
                j++;
                info = oLine.split(";");
                if(j==(row+1)){
                    info[1] = value;
                }

                for (int i = 0; i < info.length; i++) {
                    content = content + info[i] + ";";
                }
                content=content+"\r\n";
                oLine = br.readLine();
            }
            br.close();
            fileReader.close();

            file.delete();
            File fileNew = new File("src/main/java/data/"+filename+".txt");
            FileWriter fileWriter = new FileWriter(fileNew);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bw);

            pw.write(content);

            pw.close();
            bw.close();
            fileWriter.close();

        }catch (IOException ie){
            ie.printStackTrace();
        }
    }
    //修改
    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource()==adminManageInfoJp.getModify()) {
            int row = adminManageInfoJp.getTable().getSelectedRow();
          //  System.out.println(row);
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a record!");
                return;
            }
            //修改rank对应的价格
            if (type.equals("rankprice")) {
                String value = JOptionPane.showInputDialog(adminManageInfoJp, "Enter New Price: ", "Modify Membership Price", JOptionPane.OK_CANCEL_OPTION);
                if (value.equals("")) {
                    int col = adminManageInfoJp.getTable().getSelectedColumn();
                    value = String.valueOf(adminManageInfoJp.getTable().getValueAt(row, col));
                }
                adminManageInfoJp.getTable().setValueAt(value, row, 1);
                modify(row, value, "rank_price");
            }
            //修改满减规则
            else if (type.equals("disRule")) {
                String value = JOptionPane.showInputDialog(adminManageInfoJp, "Enter New Rule: ", "Modify Membership Rule", JOptionPane.OK_CANCEL_OPTION);
                if (value.equals("")) {
                    int col = adminManageInfoJp.getTable().getSelectedColumn();
                    value = String.valueOf(adminManageInfoJp.getTable().getValueAt(row, col));
                }
                adminManageInfoJp.getTable().setValueAt(value, row, 1);
                modify(row, value, "discount_rule");
            }
            //修改membership对应的折扣
            else if (type.equals("memDis")) {
                String value = JOptionPane.showInputDialog(adminManageInfoJp, "Enter New Discount: ", "Modify Membership Discount", JOptionPane.OK_CANCEL_OPTION);
                if (Float.parseFloat(value) <= 1) {
                    adminManageInfoJp.getTable().setValueAt(value, row, 1);
                    modify(row, value, "membership_discount");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a number less than 1!");
                }
            }
            //修改教练的rank
            else if (type.equals("TraRank")) {
                String value = JOptionPane.showInputDialog(adminManageInfoJp, "Enter New Rank: ", "Change Rank", JOptionPane.OK_CANCEL_OPTION);
                if (value.equals("")) {
                    int col = adminManageInfoJp.getTable().getSelectedColumn();
                    value = String.valueOf(adminManageInfoJp.getTable().getValueAt(row, col));
                }
                if (value.equals("1") || value.equals("2") || value.equals("3")) {
                    adminManageInfoJp.getTable().setValueAt(value, row, 9);
                    ChangeInfo changeInfo = new ChangeInfo("T" + (row + 1));
                    String fileName = changeInfo.openFile();
                    try {
                        File file = new File(fileName);
                        FileReader fileReader = new FileReader(file);
                        BufferedReader br = new BufferedReader(fileReader);
                        String oLine = br.readLine();
                        String[] info;

                        if (oLine != null) {

                            info = oLine.split(";");
                            info[9] = value;
                            oLine = "";
                            for (int i = 0; i < info.length; i++) {
                                oLine = oLine + info[i] + ";";
                            }

                            br.close();
                            fileReader.close();

                            file.delete();
                            File fileNew = new File(fileName);
                            FileWriter fileWriter = new FileWriter(fileNew);
                            BufferedWriter bw = new BufferedWriter(fileWriter);
                            PrintWriter pw = new PrintWriter(bw);

                            pw.write(oLine);
                            pw.close();
                            bw.close();
                            fileWriter.close();
                        } else System.out.println("4Txt doesn't have any content");
                    } catch (IOException ie) {
                        ie.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter 1, 2, or 3!");
                }}}}



    public MenuPart getMenuPart() {
        return menuPart;
    }

    public void setMenuPart(MenuPart menuPart) {
        this.menuPart = menuPart;
    }


    public StandardFrame getStandardFrame() {
        return standardFrame;
    }

    public void setStandardFrame(StandardFrame standardFrame) {
        this.standardFrame = standardFrame;
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public AdminManageInfoJP getAdminManageInfoJp() {
        return adminManageInfoJp;
    }

    public void setAdminManageInfoJp(AdminManageInfoJP adminManageInfoJp) {
        this.adminManageInfoJp = adminManageInfoJp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
