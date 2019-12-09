import java.awt.*;
import java.awt.event.*;
public class MouseMech implements MouseListener{
    int x;
    int y;

    MouseMech(){
        x=0;
        y=0;
//        addMouseListener(this);
////
////        setSize(300,300);
////        setLayout(null);
////        setVisible(true);
    }


    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
//        Graphics g=getGraphics();
//        g.setColor(Color.BLUE);
//        g.fillOval(e.getX(),e.getY(),30,30);
    }


    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

}  