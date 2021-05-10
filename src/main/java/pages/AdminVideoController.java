package pages;

import logic.AdminVideoListener;
import logic.searchVideos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminVideoController extends JPanel {
    private JFrame belongsTo;
    private JPanel container;
    private CardLayout manager;

    public JFrame getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(JFrame belongsTo) {
        this.belongsTo = belongsTo;
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        this.container = container;
    }

    public CardLayout getManager() {
        return manager;
    }

    public void setManager(CardLayout manager) {
        this.manager = manager;
    }

    private JButton bAdd;
    private JButton bDel;
    private JButton bView;
    private JTable table;
    private DefaultTableModel tableModel;
    private AdminVideoListener avl;


    public AdminVideoController(){
        /*
        //JPanel CardContainer = new JPanel();
        //MenuPart mp = new MenuPart();
        CardLayout CardManager = new CardLayout();

        this.setLayout(CardManager);
        //this.add(CardContainer);

        RecordMarket rm = new RecordMarket();
        showAPlayer sap = new showAPlayer();


        this.add(rm,"rm");
        this.add(sap,"sap");
        rm.setBrotherPan(sap);
        sap.setBrotherPan(rm);

        CardManager.show(this,"rm");
        */  //Use the video view model in the RecordMarket and showAPlayer

        avl = new AdminVideoListener();
        //The whole panel
        JPanel pAll = new JPanel();
        this.add(pAll);
        pAll.setLayout(new BorderLayout());

        //Video Record Panel
        JPanel pVideoRecord = new JPanel();
        pAll.add(pVideoRecord,BorderLayout.CENTER);



        //get all videos info
        ArrayList<String[]> allvideos = new searchVideos().searchAllvideos();
        Object[][] videosInfo = (Object[][])allvideos.toArray(new Object[0][0]);
        int col = videosInfo[0].length;


        //set table
        String[] headNames = {"Video Name","Video Cover Format","Video Format","Category","Introduction"};
        tableModel = new DefaultTableModel(videosInfo,headNames);
        table = new JTable(tableModel);

        //set table style
        //table.setForeground(Color.BLACK);
        table.setSelectionForeground(Color.DARK_GRAY);
        table.setSelectionBackground(Color.LIGHT_GRAY);  //
        table.setGridColor(Color.GRAY);

        table.getTableHeader().setFont(new Font(null,Font.BOLD,14));
        //table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setResizingAllowed(false);  //not allow to resize manually
        table.getTableHeader().setReorderingAllowed(false);  //not allow to reorder table manually

        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(400);
        table.setPreferredScrollableViewportSize(new Dimension(1200,400));


        JScrollPane scrollPane = new JScrollPane(table);
        pVideoRecord.add(scrollPane);





        JPanel pButton = new JPanel();
        pAll.add(pButton,BorderLayout.SOUTH);

        //Add Video Button
        bAdd = new JButton("Add");
        pButton.add(bAdd);
        bAdd.addActionListener(avl);
        JLabel l1 = new JLabel("              ");
        pButton.add(l1);

        //View Video Button
        bView = new JButton("View");
        pButton.add(bView);
        bView.addActionListener(avl);
        JLabel l2 = new JLabel("              ");
        pButton.add(l2);

        //Delete Video Button
        bDel = new JButton("Delete");
        pButton.add(bDel);
        bDel.addActionListener(avl);
        JLabel l3 = new JLabel("             ");
        pButton.add(l3);


        avl.setAvc(this);


        //

        //data sheet class including video description?



    }

    public JButton getbAdd() {
        return bAdd;
    }

    public JButton getbDel() {
        return bDel;
    }

    public JButton getbView() {
        return bView;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public AdminVideoListener getAvl() {
        return avl;
    }



    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        AdminVideoController adminVideoController = new AdminVideoController();

        StandardFrame frame = new StandardFrame("ADMIN",MenuPart.MENU_ADMIN);


        //frame.setJMenuBar(mp);

        frame.add(adminVideoController);
        frame.setSize(1100,600);    //
        frame.setVisible(true);

    }
}
