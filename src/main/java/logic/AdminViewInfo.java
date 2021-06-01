package logic;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *class that administrators view user and coach information as well as promotional information
 * @author Yixin Li
 * @version 5.6.3
 */

public class AdminViewInfo {

    private Object[] columnTitle = {"AccountID" , "Password" , "First Name","Last Name","Gender","Phone Number","E-mail",
            "Date of birth","Profession","Rank"};
    private Object[] columnTitle1 = {"AccountID" , "Password" , "Nickname","First Name","Last Name","Gender","Phone Number",
            "E-mail","Date of birth","Membership"};
    private  Object[] columnTitle_mem={"Membership","Discount","Upgrade Fee"};
    private Object[] columnTile_dis={"Price","Price to subtract"};
    private Object[] columnTitle_rankp={"Rank","Price"};

/**
 * Output the corresponding membership hierarchy information
 * @return  Object[][]
 *The return value contains membership hierarchy information
 */

    public Object[][] get_memDis(){
        String[] info;

        info=ReadFlexibleInfo.readFile(1).split(";");
        int num=info.length/3;
        Object[][] tabledata=new Object[num][3];
        for(int i=0;i<num;i++){
            tabledata[i][0] = info[3*i];
            tabledata[i][1] = info[3*i+1];
            tabledata[i][2]=info[3*i+2];
        }
        return tabledata;
    }
    /**
     * Output customer or trainer's information
     * @param I String to indicate customer or trainer's information to be modified
     * @return  Object[][]
     *The return value contains customer or trainer's information
     */
    public Object[][] view_info(String I){

        Login login=new Login();
        int num= Integer.parseInt(login.distri_acc(I).substring(1))-1;

        Object[][] tabledata=new Object[num][];

        for(int i=1;i<=num;i++) {
            try {
                //open the information file and read it
                FileReader fileReader = new FileReader("src/main/java/data/"+I+"Info/"+I+i + ".txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String oneLine = bufferedReader.readLine();
                String[] info = oneLine.split(";");
                tabledata[i - 1] = new Object[info.length];

                //Assigns information from the file to a two-dimensional array
                for (int j = 0; j < info.length; j++) {
                    tabledata[i - 1][j] = info[j];
                }

                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                System.out.println("ERROR");
                System.exit(1); }

        }
        return tabledata;
        }

    /**
     * Output customer or trainer's information
     * @param row int Number to indicate the relevant row of the flexi_into.txt
     * @return  Object[][]
     *The return value contains marketing information
     */
    public Object[][] get_promotionInfo(int row){

        String[] info;

        info=ReadFlexibleInfo.readFile(row).split(";");
        int num=info.length/2;
        Object[][] tabledata=new Object[num][2];
        for(int i=0;i<num;i++){
            tabledata[i][0] = info[2*i];
            tabledata[i][1] = info[2*i+1];
        }
        return tabledata;
    }
    public Object[] getColumnTitle() {
        return columnTitle;
    }

    public void setColumnTitle(Object[] columnTitle) {
        this.columnTitle = columnTitle;
    }

    public Object[] getColumnTitle1() {
        return columnTitle1;
    }

    public void setColumnTitle1(Object[] columnTitle1) {
        this.columnTitle1 = columnTitle1;
    }

    public Object[] getColumnTitle_mem() {
        return columnTitle_mem;
    }

    public void setColumnTitle_mem(Object[] columnTitle_mem) {
        this.columnTitle_mem = columnTitle_mem;
    }

    public Object[] getColumnTile_dis() {
        return columnTile_dis;
    }

    public void setColumnTile_dis(Object[] columnTile_dis) {
        this.columnTile_dis = columnTile_dis;
    }

    public Object[] getColumnTitle_rankp() {
        return columnTitle_rankp;
    }

    public void setColumnTitle_rankp(Object[] columnTitle_rankp) {
        this.columnTitle_rankp = columnTitle_rankp;
    }
}
