import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Frame extends JFrame{

    //Window size
    private static final int width = 1024;
    private static final int height = 768;
    

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Frame();
            }
        });
    }

    public Frame(){
        super("OptoLaser");
        InfoListener infoListener = new InfoListener(this);
        JPanel infoPanel = new InfoPanel(infoListener);
        add(infoPanel);

        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }


}



/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LaserGame extends JPanel{

    //Window size
    private static int width = 1024;
    private static int height = 768;

    private int[][] map;
    private int screen;

    GraphicsDisplay gameArea = new GraphicsDisplay();
    MouseMech listener = new MouseMech();



    public static void main(String[] args) {

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

        JButton button;
        JTextPane text;


        JPanel paneMenu = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        paneMenu.setLayout(new GridBagLayout());

        text = new JTextPane();

        button = new JButton("Start");
        c.gridx = 0;
        c.gridy = 1;
        paneMenu.add(button, c);
//        button = new JButton("Button 2");
//        //c.weightx = 0.5;
//        c.gridx = 0;
//        c.gridy = 2;
//        paneMenu.add(button, c);





        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




//        JComponent newContentPane = new LaserGame();
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);

        paneMenu.setOpaque(true); //content panes must be opaque
        frame.setContentPane(paneMenu);
        paneMenu.setPreferredSize(new Dimension(width, height));
        paneMenu.setBackground(new Color(0, 26, 51));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public LaserGame() {
        //super(new GridLayout(2,0));
        //gameArea = new GraphicsDisplay();
        //setLayout(new FlowLayout());
        //setLayout(new BorderLayout());
        //setLayout(new GridBagLayout());
        super(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        //gbc.gridheight = 2 ;
        add(gameArea, gbc);

//        JButton b = new JButton("Start Game");
//        //b.setBounds(width - 10, height  - 5, width/20, height/20);
//        //b.setBounds(50,100,95,30);
//        b.addActionListener(e -> {
//            screen++;
//            gameArea.repaint();
//        });
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        add(b, gbc);
        //add(b, BorderLayout.SOUTH);


        //Register for mouse events on gameArea and the panel.
        gameArea.addMouseListener(listener);
        addMouseListener(listener);
        setPreferredSize(new Dimension(width, height));
        //setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }


    public void game(){

    }


}

*/