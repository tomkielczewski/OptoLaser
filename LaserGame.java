import javax.swing.*;
import java.awt.*;

public class LaserGame extends JPanel{

    //Window size
    private static int width = 800;
    private static int height = 600;

//    JFrame frame = new JFrame("Laser");
    GraphicsDisplay gameArea = new GraphicsDisplay();
    MouseMech listener = new MouseMech();
//    JPanel panel = new JPanel();


    public static void main(String[] args) {
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("OptoLaser");
       // GraphicsDisplay gameArea = new GraphicsDisplay();
        JPanel panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentPane = new LaserGame();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

//        panel.setBackground(Color.BLACK);
//        frame.getContentPane().add(panel);
//        frame.getContentPane().add(gameArea, BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void game(){

    }


    public LaserGame() {
        super(new GridLayout(0,1));
        gameArea = new GraphicsDisplay();
        add(gameArea);


        //Register for mouse events on blankArea and the panel.
        gameArea.addMouseListener(listener);
        addMouseListener(listener);
        setPreferredSize(new Dimension(450, 450));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }


}
