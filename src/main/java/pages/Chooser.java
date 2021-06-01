package pages;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
/**
 *Class that displays calendar
 * @author Yixin Li
 * @version 5.6.3
 */

public class Chooser extends JPanel{

    private static final long serialVersionUID = -5384012731547358720L;
    
    private Calendar calendar;
    private Calendar now = Calendar.getInstance();
    private JPanel calendarPanel;
    private java.awt.Font font = new java.awt.Font("Times",java.awt.Font.PLAIN,12);
    private java.text.SimpleDateFormat sdf;
    private final LabelManager lm = new LabelManager();
    private javax.swing.Popup pop;
    private TitlePanel titlePanel;
    private BodyPanel bodyPanel;
    private FooterPanel footerPanel;
    
    private JComponent showDate;
    private boolean isShow = false;
    private static final String DEFAULTFORMAT = "yyyy-MM-dd";
    private static final String[] showTEXT = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    private static WeekLabel[] weekLabels = new WeekLabel[7];
    private static int defaultStartDAY = 0;//0 is from Sun, 1 is from Mon, 2 is from Tue
    private static Color hoverColor = Color.BLUE; // hover color

    /**
     * This a constructor to pass parameters
     * @param date Date to be chosen
     * @param format selected format
     * @param startDAY indicate start day
     */
    private Chooser(Date date, String format, int startDAY){
        if(startDAY > -1 && startDAY < 7) defaultStartDAY = startDAY;
        int dayIndex = defaultStartDAY;
        for(int i=0; i<7; i++){
            if(dayIndex > 6) dayIndex = 0;
            weekLabels[i] = new WeekLabel(dayIndex, showTEXT[dayIndex]);
            dayIndex ++ ;
        }
        sdf = new java.text.SimpleDateFormat(format);
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        initCalendarPanel();
    }
    
    public static Chooser getInstance(Date date, String format){
        return new Chooser(date, format, defaultStartDAY);
    }

    public static Chooser getInstance(Date date){
        return getInstance(date, DEFAULTFORMAT);
    }
    public static Chooser getInstance(String format){
        return getInstance(new Date(), format);
    }
    public static Chooser getInstance(){
        return getInstance(new Date(), DEFAULTFORMAT);
    }
/**
 * Initial the calender panel.
 */
    private void initCalendarPanel(){
        calendarPanel = new JPanel(new BorderLayout());
        calendarPanel.setBorder(BorderFactory.createLineBorder(new Color(0xAA, 0xAA, 0xAA)));
        calendarPanel.add(titlePanel = new TitlePanel(), BorderLayout.NORTH);
        calendarPanel.add(bodyPanel = new BodyPanel(), BorderLayout.CENTER);
        calendarPanel.add(footerPanel = new FooterPanel(), BorderLayout.SOUTH);
        this.addAncestorListener(new AncestorListener() {
            public void ancestorAdded(AncestorEvent event) { }
            public void ancestorRemoved(AncestorEvent event) {hidePanel();}
            //hide pop when move component.  
            public void ancestorMoved(AncestorEvent event) {
                hidePanel();
            }
        });
    }
    /**
     * Response the calender click events.
     * @param showComponent
     * component to show
     */
    public void register(final JComponent showComponent) {
        this.showDate = showComponent;
        showComponent.setRequestFocusEnabled(true);
        showComponent.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                showComponent.requestFocusInWindow();
            }
        });
        this.add(showComponent, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(90, 25));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        showComponent.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                if (showComponent.isEnabled()) {
                    showComponent.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
            public void mouseExited(MouseEvent me) {
                if (showComponent.isEnabled()) {
                    showComponent.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    showComponent.setForeground(Color.BLACK);
                }
            }
            public void mousePressed(MouseEvent me) {
                if (showComponent.isEnabled()) {
                    showComponent.setForeground(hoverColor);
                    if (isShow) {
                        hidePanel();
                    } else {
                        showPanel(showComponent);
                    }
                }
            }
            public void mouseReleased(MouseEvent me) {
                if (showComponent.isEnabled()) {
                    showComponent.setForeground(Color.BLACK);
                }
            }
        });
        showComponent.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                hidePanel();
            }
            public void focusGained(FocusEvent e) { }
        });
    }
    /**
     * hide the main panel.
     */
    private void hidePanel() {
        if (pop != null) {
            isShow = false;
            pop.hide();
            pop = null;
        }
    }
    /**
     * show the main panel.
     * @param owner Component to show the main panel
     */

    private void showPanel(Component owner) {
        if (pop != null) pop.hide();
        Point show = new Point(0, showDate.getHeight());
        SwingUtilities.convertPointToScreen(show, showDate);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = show.x;
        int y = show.y;
        if (x < 0) x = 0;
        if (x > size.width - 212) x = size.width - 212;
        if (y > size.height - 167) y -= 165;
        pop = PopupFactory.getSharedInstance().getPopup(owner, calendarPanel, x, y);
        pop.show();
        isShow = true;
    }
    /**
     * change text or label's content.
     */

    private void commit() {
        if (showDate instanceof JTextField) {
            ((JTextField) showDate).setText(sdf.format(calendar.getTime()));
        }else if (showDate instanceof JLabel) {
            ((JLabel) showDate).setText(sdf.format(calendar.getTime()));
        }
        hidePanel();
    }
    /**
     *Class that controls panel
     * @author Yixin Li
     * @version 5.6.3
     */

    private class TitlePanel extends JPanel {
        
        private static final long serialVersionUID = -2865282186037420798L;
        private JLabel preYear,preMonth,center,nextMonth,nextYear,centercontainer;
        /**
         * This a constructor without parameters
         */
        public TitlePanel(){
            super(new BorderLayout());
            this.setBackground(new Color(190, 200, 200));
            initTitlePanel();
        }
        /**
         * Initial the panel's title
         */
        private void initTitlePanel(){
            preYear = new JLabel("<<", JLabel.CENTER);
            preMonth = new JLabel("<", JLabel.CENTER);
            center = new JLabel("", JLabel.CENTER);
            centercontainer = new JLabel("", JLabel.CENTER);
            nextMonth = new JLabel(">", JLabel.CENTER);
            nextYear = new JLabel(">>", JLabel.CENTER);
            
            preYear.setToolTipText("Last Year");
            preMonth.setToolTipText("Last Month");
            nextMonth.setToolTipText("Next Month");
            nextYear.setToolTipText("Next Year");
            
            preYear.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 0));
            preMonth.setBorder(BorderFactory.createEmptyBorder(2, 15, 0, 0));
            nextMonth.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 15));
            nextYear.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 10));
            
            centercontainer.setLayout(new BorderLayout());
            centercontainer.add(preMonth, BorderLayout.WEST);
            centercontainer.add(center, BorderLayout.CENTER);
            centercontainer.add(nextMonth, BorderLayout.EAST);
            
            this.add(preYear, BorderLayout.WEST);
            this.add(centercontainer, BorderLayout.CENTER);
            this.add(nextYear, BorderLayout.EAST);
            this.setPreferredSize(new Dimension(210, 25));
            
            updateDate();
            
            preYear.addMouseListener(new MyMouseAdapter(preYear, Calendar.YEAR, -1));
            preMonth.addMouseListener(new MyMouseAdapter(preMonth, Calendar.MONTH, -1));
            nextMonth.addMouseListener(new MyMouseAdapter(nextMonth, Calendar.MONTH, 1));
            nextYear.addMouseListener(new MyMouseAdapter(nextYear, Calendar.YEAR, 1));
        }
        /**
         *Update the date
         */
        private void updateDate() {
            center.setText(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH) + 1));
        }
        /**
         *listener for control label
         * @author Yixin Li
         * @version 5.6.3
         */

        class MyMouseAdapter extends MouseAdapter{
            
            JLabel label;
            private int type, value;
            /**
             * This a constructor to pass parameters
             * @param label
             * @param type
             * @param value
             */
            public MyMouseAdapter(final JLabel label, final int type, final int value){
                this.label = label;
                this.type = type;
                this.value = value;
            }
            /**
             * response to mouse event
             * @param me mouse event
             */
            public void mouseEntered(MouseEvent me) {
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
                label.setForeground(hoverColor);
            }
            /**
             * response to mouse event
             * @param me mouse event
             */
            public void mouseExited(MouseEvent me) {
                label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                label.setForeground(Color.BLACK);
            }
            /**
             * response to mouse event
             * @param me mouse event
             */
            public void mousePressed(MouseEvent me) {
                calendar.add(type, value);
                label.setForeground(Color.WHITE);
                refresh();
            }
            /**
             * response to mouse event
             * @param me mouse event
             */
            public void mouseReleased(MouseEvent me) {
                label.setForeground(Color.BLACK);
            }
        }
    }
    /**
     * Class that contains body panel, include week labels and day labels
     * @author Yixin Li
     * @version 5.6.3
     */

    private class BodyPanel extends JPanel {
        
        private static final long serialVersionUID = 5677718768457235447L;
        /**
         * This a constructor without parameters
         */
        public BodyPanel(){
            super(new GridLayout(7, 7));
            this.setPreferredSize(new Dimension(210, 140));
            initMonthPanel();
        }
        /**
         * Initial the month panel
         */
        private void initMonthPanel(){
            updateDate();
        }
        /**
         * Update the date
         */
        public void updateDate() {
            this.removeAll();
            lm.clear();
            Date temp = calendar.getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTime(temp);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            
            int index = cal.get(Calendar.DAY_OF_WEEK);
            if(index > defaultStartDAY) cal.add(Calendar.DAY_OF_MONTH, -index + defaultStartDAY);
            else cal.add(Calendar.DAY_OF_MONTH, -index + defaultStartDAY - 7);
            
            for (WeekLabel weekLabel : weekLabels) {
                this.add(weekLabel);
            }
            for (int i = 0; i < 42; i++) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
                lm.addLabel(new DayLabel(cal));
            }
            for (DayLabel my : lm.getLabels()) {
                this.add(my);
            }
        }
    }
    /**
     * Class that displays the calendar panel's footer
     * @author Yixin Li
     * @version 5.6.3
     */
    private class FooterPanel extends JPanel {
        
        private static final long serialVersionUID = 8135037333899746736L;
        private JLabel dateLabel;
        /**
         * This a constructor without parameters
         */
        public FooterPanel(){
            super(new BorderLayout());
            initFooterPanel();
        }
        /**
         * Initial the panel
         */
        private void initFooterPanel(){
            dateLabel = new JLabel("Today is : "+sdf.format(new Date()));
            dateLabel.addMouseListener(new MouseListener() {
                
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mousePressed(MouseEvent e) {
                    calendar.setTime(new Date());
                    refresh();
                    commit();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    dateLabel.setForeground(Color.BLACK);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    dateLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    dateLabel.setForeground(hoverColor);
                }
                @Override
                public void mouseClicked(MouseEvent e) {}
            });
            this.add(dateLabel);
        }
        public void updateDate(){};
    }
    /**
     * refresh all panel.
     */


    private void refresh() {
        titlePanel.updateDate();
        bodyPanel.updateDate();
        footerPanel.updateDate();
        SwingUtilities.updateComponentTreeUI(this);
    }
    /**
     * Class that displays the calendar panel's week label
     * @author Yixin Li
     * @version 5.6.3
     */
    private class WeekLabel extends JLabel {
        
        private static final long serialVersionUID = -8053965084432740110L;
        private String name;
        /**
         * This a constructor to pass parameters
         * @param index int
         * @param name String
         */
        public WeekLabel(int index, String name){
            super(name, JLabel.CENTER);
            this.name = name;
        }
        public String toString(){
            return name;
        }
    }
    /**
     * Class that displays the calendar panel's day label
     * @author Yixin Li
     * @version 5.6.3
     */
    private class DayLabel extends JLabel implements java.util.Comparator<DayLabel>, MouseListener, java.awt.event.MouseMotionListener {

        private static final long serialVersionUID = -6002103678554799020L;
        private boolean isSelected;
        private int year, month, day;
        /**
         * This a constructor to pass parameters
         * @param cal Calendar
         */
        public DayLabel(Calendar cal){
            super(""+cal.get(Calendar.DAY_OF_MONTH), JLabel.CENTER);
            this.year = cal.get(Calendar.YEAR);
            this.month = cal.get(Calendar.MONTH);
            this.day = cal.get(Calendar.DAY_OF_MONTH);
            
            this.setFont(font);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            if(month == calendar.get(Calendar.MONTH)) this.setForeground(Color.BLACK);
            else this.setForeground(Color.LIGHT_GRAY);
            
        }
        public boolean getIsSelected() {
            return isSelected;
        }
        /**
         * set the select
         * @param b Boolean
         * @param isDrag boolean
         */
        public void setSelected(boolean b, boolean isDrag) {
            isSelected = b;
            if (b && !isDrag) {
                int temp = calendar.get(Calendar.MONTH);
                calendar.set(year, month, day);
                if (temp == month) {
                    SwingUtilities.updateComponentTreeUI(bodyPanel);
                } else {
                    refresh();
                }
                this.repaint();
            }
        }
        /**
         * show the paint component.
         * @param g Graphics to show the paint component
         */

        @Override
        protected void paintComponent(Graphics g) {
            //set curr select day's background
            if(day == calendar.get(Calendar.DAY_OF_MONTH) && month == calendar.get(Calendar.MONTH)){
                g.setColor(new Color(0xBB, 0xBF, 0xDA));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            //set current day's border
            if(year == now.get(Calendar.YEAR) && month == now.get(Calendar.MONTH) && day == now.get(Calendar.DAY_OF_MONTH)){
                Graphics2D gd = (Graphics2D) g;
                gd.setColor(new Color(0x55, 0x55, 0x88));
                Polygon p = new Polygon();
                p.addPoint(0, 0);
                p.addPoint(getWidth() - 1, 0);
                p.addPoint(getWidth() - 1, getHeight() - 1);
                p.addPoint(0, getHeight() - 1);
                gd.drawPolygon(p);
            }
            if (isSelected){
                Stroke s = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1.0f, new float[] { 2.0f, 2.0f }, 1.0f);
                Graphics2D gd = (Graphics2D) g;
                gd.setStroke(s);
                gd.setColor(Color.BLACK);
                Polygon p = new Polygon();
                p.addPoint(0, 0);
                p.addPoint(getWidth() - 1, 0);
                p.addPoint(getWidth() - 1, getHeight() - 1);
                p.addPoint(0, getHeight() - 1);
                gd.drawPolygon(p);
            }
            super.paintComponent(g);
        }
        /**
         * Set bound of the panel.
         * @param p Point to add the panel
         * @return boolean
         */
        public boolean contains(Point p) {
            return this.getBounds().contains(p);
        }
        /**
         * Repaint the component.
         */
        private void update() {
            repaint();
        }
        @Override
        public void mouseDragged(MouseEvent e) { }
        @Override
        public void mouseMoved(MouseEvent e) { }
        @Override
        public void mouseClicked(MouseEvent e) { }
        /**
         * Response to the mouse clicked.
         * @param e MouseEvent to select day
         */
        @Override
        public void mousePressed(MouseEvent e) {
            isSelected = true;
            update();
        }
        /**
         * Response to the mouse released.
         * @param e MouseEvent to select day
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            Point p = SwingUtilities.convertPoint(this, e.getPoint(), bodyPanel);
            this.setForeground(Color.BLACK);
            lm.setSelect(p, false);
            commit();
        }
        /**
         * change color when mouse over.
         * @param e MouseEvent of mouse over
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            this.setForeground(hoverColor);
            this.repaint();
        }
        /**
         * change color when mouse exit.
         * @param e MouseEvent of mouse exit
         */
        @Override
        public void mouseExited(MouseEvent e) {
            if(month == calendar.get(Calendar.MONTH)) this.setForeground(Color.BLACK);
            else this.setForeground(Color.LIGHT_GRAY);
            this.repaint();
        }
        /**
         * Compare two labels.
         * @param o1 DayLabel to be compared
         * @param o2 DayLabel to be compared
         * @return int
         */
        @Override
        public int compare(DayLabel o1, DayLabel o2) {
            Calendar c1 = Calendar.getInstance();
            c1.set(o1.year, o1.month, o1.day);
            Calendar c2 = Calendar.getInstance();
            c2.set(o2.year, o2.month, o2.day);
            return c1.compareTo(c2);
        }
        
    }
    /**
     * Class that manages labels
     * @author Yixin Li
     * @version 5.6.3
     */
    private class LabelManager {
        private List<DayLabel> list;
        
        public LabelManager(){
            list = new ArrayList<DayLabel>();
        }
        
        public List<DayLabel> getLabels(){
            return list;
        }
        public void addLabel(DayLabel label){
            list.add(label);
        }
        public void clear() {
            list.clear();
        }
        /**
         *Set select.
         * @param p Point to be selected
         * @param b Boolean to indicate selected or not
         */
        public void setSelect(Point p, boolean b) {

            if (b) {

                boolean findPrevious = false, findNext = false;
                for (DayLabel lab : list) {
                    if (lab.contains(p)) {
                        findNext = true;
                        if (lab.getIsSelected()) findPrevious = true;
                        else lab.setSelected(true, b);
                    } else if (lab.getIsSelected()) {
                        findPrevious = true;
                        lab.setSelected(false, b);
                    }
                    if (findPrevious && findNext) return;
                }
            }else {
                DayLabel temp = null;
                for (DayLabel m : list) {
                    if (m.contains(p)) {
                        temp = m;
                    } else if (m.getIsSelected()) {
                        m.setSelected(false, b);
                    }
                }
                if (temp != null) temp.setSelected(true, b);
            }
        }
    }
    

}