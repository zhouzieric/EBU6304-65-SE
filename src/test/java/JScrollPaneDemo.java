import mdlaf.components.button.MaterialButtonUI;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JScrollPaneDemo extends JFrame{
    private JPanel contentPane;


    public JScrollPaneDemo(){
        contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(0,0));
        this.setContentPane(contentPane);
        JButton jButton = new JButton("?");
        contentPane.add(jButton,BorderLayout.CENTER);


        MaterialButtonUI materialButtonUI = new MaterialButtonUI();
        materialButtonUI.installUI(jButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 250, 200);
        this.setVisible(true);
    }
    public static void main(String []args){
        JScrollPaneDemo example=new JScrollPaneDemo();
    }
}
