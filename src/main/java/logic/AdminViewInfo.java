package logic;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//实现管理员查看用户和教练信息的功能类
public class AdminViewInfo {

    private Object[] columnTitle = {"AccountID" , "Password" , "First Name","Last Name","Gender","Phone Number","E-mail",
            "Date of birth","Profession","Rank"};
    private Object[] columnTitle1 = {"AccountID" , "Password" , "Nickname","First Name","Last Name","Gender","Phone Number",
            "E-mail","Date of birth","Membership"};
    private  Object[] columnTitle_mem={"Membership","Discount"};
    private Object[] columnTile_dis={"Price","Price to subtract"};
    private Object[] columnTitle_rankp={"Rank","Price"};

    //将信息作为二维数组返回
    public Object[][] view_info(String I){

        Login login=new Login();
        int num= Integer.parseInt(login.distri_acc(I).substring(1))-1;//一共有多少个成员
        //System.out.println(num);
        Object[][] tabledata=new Object[num][];

        for(int i=1;i<=num;i++) {
            try {
                //打开文件，读信息
                FileReader fileReader = new FileReader("src/main/java/data/"+I+"Info/"+I+i + ".txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String oneLine = bufferedReader.readLine();
                String[] info = oneLine.split(";");
                tabledata[i - 1] = new Object[info.length];

                //把文件中的信息赋给二维数组
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

        //输出对应的促销信息
    public Object[][] get_promotionInfo(String choice,int num){
        Object[][] tabledata=new Object[num][];
        int i=1;
        String[] info;
            try {
                //打开文件，读信息
                FileReader fileReader = new FileReader("src/main/java/data/"+choice+".txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String oneLine = bufferedReader.readLine();
                while(oneLine!=null){
                    info = oneLine.split(";");
                    tabledata[i - 1] = new Object[info.length];
                    //把文件中的信息赋给二维数组
                    for (int j = 0; j < info.length; j++) {
                        tabledata[i - 1][j] = info[j];
                    }
                    i++;
                    oneLine = bufferedReader.readLine();
                }
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                System.out.println("ERROR");
                System.exit(1); }


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
