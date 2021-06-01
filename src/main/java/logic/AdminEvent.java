package logic;

import pages.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
/**
 *class that administrators modify user coach information, modify marketing rules, upload notification
 * @author Yixin Li
 * @version 5.6.3
 */
public class AdminEvent{

    private StandardFrame standardFrame;
    private MenuPart menuPart;
    private AdminManageInfoJP adminManageInfoJp;
    private AdminChangeMemJP adminChangeMemJP;
    private AdminUploadNotice adminUploadNotice;

    private String type;

/**
 * Write the modified content to a file
 * @param filename
 * String to indicate the file name to write to
 * @param content
 * String to indicate the content to write to the file
 * @exception IOException
 * involving read file, may throw
 */

    public void write_to_f(String filename, String content) throws IOException {
        File fileNew = new File(filename);
        FileWriter fileWriter = new FileWriter(fileNew);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        PrintWriter pw = new PrintWriter(bw);

        pw.write(content);

        pw.close();
        bw.close();
        fileWriter.close();
    }
/**
 * Modify promotional content of flexi_info.txt
 * @param  row int Number to indicate the row selected of the table
 * @param col int Number to indicate the column selected of the table
 * @param value String to indicate the value that administrator want to modify
 * @param type int Number to indicate the relevant row of the flexi_into.txt
 */
    public void modify(int row, int col, String value,int type){

        String[] info;
        String content= "";
        try {
            File file = new File("src/main/java/data/flexi_info.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String oLine = br.readLine();
            int j=0;
            while(oLine!=null) {
                if(j==type){
                    info = oLine.split(";");
                    if(type==1){

                    if(col==1){
                        info[3*row+1] = value;
                    }else if(col==0){
                        info[3*row]=value;
                    }else{
                        info[3*row+2]=value;
                    }}else{
                        if(col==1){
                            info[2*row+1] = value;
                        }else{
                            info[2*row]=value;
                        }
                }
                for (int i = 0; i < info.length; i++) {
                    content = content + info[i] + ";";
                }
                    content=content+"\r\n";
                }else{
                    content+=oLine+"\r\n";
                }
                j++;
                oLine = br.readLine();

            }
            br.close();
            fileReader.close();
            file.delete();
            write_to_f("src/main/java/data/flexi_info.txt",content);

        }catch (IOException ie){
            ie.printStackTrace();
        }
    }

/**
 * modify user and coach information files
 * @param  identity String to indicate customer or trainer's information to be modified
 * @param value String to indicate the value that administrator want to modify
 * @param row int Number to indicate the row selected of the table
 * @param col int Number to indicate the column selected of the table
 */
    public void modify_TraCus(String identity, String value, int row, int col){
        adminManageInfoJp.getTable().setValueAt(value, row, col);
        ChangeInfo changeInfo = new ChangeInfo(identity + (row + 1));
        String fileName = changeInfo.openFile();
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String oLine = br.readLine();
            String[] info;

            if (oLine != null) {

                info = oLine.split(";");
                info[col] = value;
                oLine = "";
                for (int i = 0; i < info.length; i++) {
                    oLine = oLine + info[i] + ";";
                }

                br.close();
                fileReader.close();

                file.delete();

                write_to_f(fileName,oLine);
            } else System.out.println("trainer info doesn't have any content");
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

/**
 * Delete membership and corresponding discounts and prices
 * @param row int Number to indicate the row selected of the table
 * @param type int Number to indicate the relevant row of the flexi_into.txt
 */

    public void modify_memdis(int row,int type){
            String[] info;
            String content= "";
            try {

                File file = new File("src/main/java/data/flexi_info.txt");
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                String oLine = br.readLine();

                int j=0;
                while(oLine!=null) {

                    if(j==type){
                        info = oLine.split(";");


                            info[3*row+1] = "";
                            info[3*row]="";
                            info[3*row+2]="";
                        for(int i = 0; i < info.length; i++){
                            if((i!=(3*row+1))&(i!=(3*row))&(i!=(3*row+2))){
                                content = content + info[i] + ";";
                            }

                        }
                        content=content+"\r\n";
                    }else{
                        content+=oLine+"\r\n";
                    }
                    j++;
                    oLine = br.readLine();
                }
                br.close();
                fileReader.close();

                file.delete();

                write_to_f("src/main/java/data/flexi_info.txt",content);

            }catch (IOException ie){
                ie.printStackTrace();
            }

    }
/**
 * Sort the member information in the file from the smallest to the largest discount
 */

    public void forder(){
        String[] info;
        String content= "";
        try {

            File file = new File("src/main/java/data/flexi_info.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String oLine = br.readLine();

            int j=0;
            while(oLine!=null) {

                if(j==1){
                    ArrayList<Membership> memberships=new ArrayList<Membership>();
                    info=oLine.split(";");
                    for(int i=0;i<info.length/3;i++){
                        memberships.add(new Membership(info[3*i],Float.parseFloat(info[3*i+1]),info[3*i+2]));

                    }

                    Collections.sort(memberships);
                    Iterator <Membership> membershipIterator=memberships.iterator();
                    String order1 = "";

                    while (membershipIterator.hasNext()){
                        Membership mem=membershipIterator.next();
                        order1=order1+mem.getKind()+";"+mem.getPrice()+";"+mem.getFee()+";";

                    }
                    oLine=order1;
                }
                content+=oLine+"\r\n";
                j++;
                oLine = br.readLine();

            }
            br.close();
            fileReader.close();

            file.delete();

            write_to_f("src/main/java/data/flexi_info.txt",content);

        }catch (IOException ie){
            ie.printStackTrace();
        }
    }

/**
 * Sort the membership information in the table from the smallest to the largest discount
 * @param  table JTable to be modified
 */

    public void torder(JTable table){
        String content=ReadFlexibleInfo.readFile(1);
        String[] info=content.split(";");
        for(int r=0;r<info.length/3;r++){

            table.setValueAt(info[3*r], r, 0);
            table.setValueAt(info[3*r+1], r, 1);
            table.setValueAt(info[3*r+2], r, 2);

        }
    }

/**
 * Add a new membership hierarchy
 * @param type int Number to indicate the relevant row of the flexi_into.txt
 * @param value1 String to indicate the membership that administrator want to modify
 * @param value2 String to indicate the discount that administrator want to modify
 * @param value3 String to indicate the price that administrator want to modify
 */

    public void add(int type,String value1, String value2, String value3){

        String content= "";
        try {

            File file = new File("src/main/java/data/flexi_info.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String oLine = br.readLine();

            int j=0;
            while(oLine!=null) {

                if(j==type){
                    oLine=oLine+value1+";"+value2+";"+value3+";";

                }
                content+=oLine+"\r\n";


                j++;
                oLine = br.readLine();

            }

            br.close();
            fileReader.close();

            file.delete();
            File fileNew = new File("src/main/java/data/flexi_info.txt");
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
/**
 * Upload new notice and clear previous notice
 */
    public void upload_response(){
        adminUploadNotice.getB().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sentence=adminUploadNotice.getTa().getText();
                String[] words=sentence.split(" ");
                if(words.length>15){
                    JOptionPane.showMessageDialog(null,
                            "The number of words you entered can be only less than 15.");
                    return;
                }else{

                    int opt=JOptionPane.showConfirmDialog(standardFrame,
                            "Are you sure you want to upload the notice? ", "Tips",
                            JOptionPane.YES_NO_OPTION);
                    if(opt == JOptionPane.YES_OPTION){
                        try {
                            write_to_f("src/main/java/data/notice.TXT",sentence);
                        } catch (IOException ex) {
                            ex.printStackTrace();


                }
                        JOptionPane.showMessageDialog(null, "Upload Successfully!");
                        return;
            }else{
                        return;
                    }
                }}
        });
        adminUploadNotice.getB1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opt=JOptionPane.showConfirmDialog(standardFrame,
                        "Are you sure you want to clear the previous notice? ", "Tips",
                        JOptionPane.YES_NO_OPTION);
                if(opt == JOptionPane.YES_OPTION){
                    try {
                        write_to_f("src/main/java/data/notice.TXT","");
                    } catch (IOException ex) {
                        ex.printStackTrace();

                    }
                    adminUploadNotice.getTa().setText("");
                    JOptionPane.showMessageDialog(null, "Clear Successfully!");
                    return;
                }else{
                    return;
                }
            }
        });
    }
    /**
     * Modify relevant membership information
     */
    public void memdis_response(){

        adminChangeMemJP.getModify().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = adminChangeMemJP.getTable().getSelectedRow();
            int col = adminChangeMemJP.getTable().getSelectedColumn();
            String value="";
            if (col == 1) {
                value = JOptionPane.showInputDialog(adminChangeMemJP, "Enter New Discount: ",
                        "Modify Membership Discount", JOptionPane.OK_CANCEL_OPTION);
                if (value==null) {
return;


                }if(value.equals("")){
                    JOptionPane.showMessageDialog(null, "It can't be blank!");
                    return;
                }
                else{if (Float.parseFloat(value) <= 1) {
                    adminChangeMemJP.getTable().setValueAt(value, row, col);
                    modify(row, col, value, 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a number less than 1!");
                }}
            } else if(col==0){
               value = JOptionPane.showInputDialog(adminChangeMemJP, "Enter New Membership: ",
                        "Modify Membership", JOptionPane.OK_CANCEL_OPTION);
                if (value==null) {
return;

                }
                if(value.equals("")){
                    JOptionPane.showMessageDialog(null, "It can't be blank!");
                    return;
                }
                adminChangeMemJP.getTable().setValueAt(value, row, col);
                modify(row, col, value, 1);
            }
            else{
                value = JOptionPane.showInputDialog(adminChangeMemJP, "Enter New Upgrade Fee: ",
                        "Modify Membership", JOptionPane.OK_CANCEL_OPTION);
                if (value==null) {
                    return;

                }
                if(value.equals("")){
                    JOptionPane.showMessageDialog(null, "It can't be blank!");
                    return;
                }
                adminChangeMemJP.getTable().setValueAt(value, row, col);
                modify(row, col, value, 1);
            }
                forder();
                torder(adminChangeMemJP.getTable());
            }});

        adminChangeMemJP.getDelete_b().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = adminChangeMemJP.getTable().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a record!");
                return;}
            else{
                int opt=JOptionPane.showConfirmDialog(standardFrame,
                        "Are you sure you want to delete " +
                                String.valueOf(adminChangeMemJP.getTable().getValueAt(row, 0))+"?", "Tips",
                        JOptionPane.YES_NO_OPTION);
                if(opt == JOptionPane.YES_OPTION){
                    adminChangeMemJP.getModel().removeRow(row);
                    modify_memdis(row,1);
                }}}});
        adminChangeMemJP.getAdd_b().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opt=JOptionPane.showConfirmDialog(standardFrame,
                        "Are you sure you want to add a membership?", "Tips",
                        JOptionPane.YES_NO_OPTION);
                if(opt == JOptionPane.YES_OPTION){
                    String value1 = JOptionPane.showInputDialog(adminChangeMemJP, "Enter New Membership: ",
                            "Add Membership", JOptionPane.OK_CANCEL_OPTION);
                    if (value1==null) {
                        return;
                    }
                    if(value1.equals("")){
                        JOptionPane.showMessageDialog(null, "It can't be blank!");
                        return;
                    }
                    String value2 = JOptionPane.showInputDialog(adminChangeMemJP, "Enter New Discount: ",
                            "Add Membership", JOptionPane.OK_CANCEL_OPTION);
                    if (value2==null) {
                        return;
                    }
                    if(value2.equals("")){
                        JOptionPane.showMessageDialog(null, "It can't be blank!");
                        return;
                    }
                    String value3 = JOptionPane.showInputDialog(adminChangeMemJP, "Enter New Upgrade Fee: ",
                            "Add Membership", JOptionPane.OK_CANCEL_OPTION);
                    if (value3==null) {
                        return;
                    }
                    if(value3.equals("")){
                        JOptionPane.showMessageDialog(null, "It can't be blank!");
                        return;
                    }
                    Vector v=new Vector();
                    v.addElement(value1);
                    v.addElement(value2);
                    v.addElement(value3);
                    adminChangeMemJP.getModel().addRow(v);
                    add(1,value1,value2,value3);
                    forder();
                    torder(adminChangeMemJP.getTable());

                }

            }
        });

    }
    /**
     * Modify customer or trainer's information
     */
    public void modify_response(){
        adminManageInfoJp.getModify().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = adminManageInfoJp.getTable().getSelectedRow();
                int col = adminManageInfoJp.getTable().getSelectedColumn();
                String value = "";
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a record!");
                    return;
                }
                //modify rank's price
                if (type.equals("rankprice")) {
                    if(col==1){
                        value = JOptionPane.showInputDialog(adminManageInfoJp, "Enter New Price: ",
                                "Modify Membership Price", JOptionPane.OK_CANCEL_OPTION);

                    if (value==null) {
return;

                    }
                    if(value.equals("")){
                        JOptionPane.showMessageDialog(null, "It can't be blank!");
                        return;
                    }
                    }

                    else{
                        value = JOptionPane.showInputDialog(adminManageInfoJp, "Enter New Rank: ",
                                "Modify Rank", JOptionPane.OK_CANCEL_OPTION);
                        if (value==null) {
                            return;}
                        if(value.equals("")){
                            JOptionPane.showMessageDialog(null, "It can't be blank!");
                            return;
                        }
                    }

                    adminManageInfoJp.getTable().setValueAt(value, row, col);
                    modify(row, col, value,0);
                }
                //modify full reduction rules
                else if (type.equals("disRule")) {
                    if(col==1){
                        value = JOptionPane.showInputDialog(adminManageInfoJp, "Enter New Price to Subtract: ",
                                "Modify Membership Discount", JOptionPane.OK_CANCEL_OPTION);


                    }else{
                        value = JOptionPane.showInputDialog(adminManageInfoJp, "Enter Original Price: ",
                                "Modify Membership Discount", JOptionPane.OK_CANCEL_OPTION);
                    }if (value==null) {
                        return;}
                    if(value.equals("")){
                        JOptionPane.showMessageDialog(null, "It can't be blank!");
                        return;
                    }

                    adminManageInfoJp.getTable().setValueAt(value, row, col);
                    modify(row,col, value,2);
                }

                //modify trainer's information
                else if (type.equals("trainer")) {

                    if((col!=8)&&(col!=9)){
                        JOptionPane.showMessageDialog(null, "You can just modify the trainer's profession and rank!");
                        return;

                    }

                    if(col==9){
                        value = JOptionPane.showInputDialog(adminManageInfoJp, "Enter New Rank: ", "Change Rank", JOptionPane.OK_CANCEL_OPTION);
                        if (value==null) {
                            return;}
                        if(value.equals("")){
                            JOptionPane.showMessageDialog(null, "It can't be blank!");
                            return;

                        }
                        if (value.equals("1") || value.equals("2") || value.equals("3")) {
                            adminManageInfoJp.getTable().setValueAt(value, row, col);
                            modify_TraCus("T",value,row,col);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter 1, 2, or 3!");
                        }}
                    else if(col==8){
                        value = JOptionPane.showInputDialog(adminManageInfoJp, "Enter New Profession: ", "Change Profession", JOptionPane.OK_CANCEL_OPTION);
                        if (value==null) {
                            return;}
                        if(value.equals("")){
                            JOptionPane.showMessageDialog(null, "It can't be blank!");
                            return;
                        }

                        adminManageInfoJp.getTable().setValueAt(value, row, col);
                        modify_TraCus("T",value,row,col);
                    }

                }else
                    //modify customer's information
                    if(type.equals("customer")){
                    if(col!=9){
                        JOptionPane.showMessageDialog(null, "You can just modify the customer's membership!");
                        return;
                    }else
                    {
                        value = JOptionPane.showInputDialog(adminManageInfoJp, "Enter New Membership: ", "Change Membership", JOptionPane.OK_CANCEL_OPTION);
                        if (value==null) {
                            return;}
                        if(value.equals("")){
                            JOptionPane.showMessageDialog(null, "It can't be blank!");
                            return;
                        }

                        adminManageInfoJp.getTable().setValueAt(value, row, col);
                        modify_TraCus("C",value,row,col);
                    }

                }
            }

        });
    }

    public AdminChangeMemJP getAdminChangeMemJP() {
        return adminChangeMemJP;
    }

    public void setAdminChangeMemJP(AdminChangeMemJP adminChangeMemJP) {
        this.adminChangeMemJP = adminChangeMemJP;
    }

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

    public AdminUploadNotice getAdminUploadNotice() {
        return adminUploadNotice;
    }

    public void setAdminUploadNotice(AdminUploadNotice adminUploadNotice) {
        this.adminUploadNotice = adminUploadNotice;
    }
    /**
     *Class that represents information related to membership level
     * @author Yixin Li
     * @version 5.6.3
     */
    private class Membership implements Comparable<Membership>{
        private String kind;
        private Float price;
        private String fee;
        public Membership(String kind, Float price, String fee){
            this.kind=kind;
            this.price=price;
            this.fee=fee;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        @Override
        public int compareTo(Membership o) {
            if(this.price==o.price){
                return 0;
            }
            else if(this.price>o.price){
                return 1;
            }else{
                return -1;
            }
        }
    }


}
