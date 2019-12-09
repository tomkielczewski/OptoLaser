import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    GraphicsDisplay gameArea;
    MouseMech listener;
    Frame frame;
    Game game;

    public GamePanel(Frame frame, Game game){
        super();

        this.frame = frame;
        this.game = game;
//        GridBagLayout gridBag = new GridBagLayout();
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.fill = GridBagConstraints.HORIZONTAL;
//        gridBag.setConstraints(this, constraints);
//        setLayout(gridBag);
        setPreferredSize(new Dimension(game.width, game.height));
        BorderLayout gridLay = new BorderLayout();
        setLayout(gridLay);


        createComponents();
    }

    private void createComponents(){

        //TODO
        // Zastanów się nad przyciskami w tym panelu. Czy mają być buttony, czy wszystko Mouse Listenerem?

        gameArea = new GraphicsDisplay(game);
        listener = new MouseMech();

//        c.gridx = 0;
//        c.gridy = 0;
//        c.gridwidth = 3;
//        this.add(gameArea, c);
        this.add(gameArea, BorderLayout.CENTER);
        //addMouseListener(listener);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());


        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        JButton mirrorButton = new JButton();
        //mirrorButton.setBackground(new Color(0, 26, 51));
        mirrorButton.setContentAreaFilled(false);
        //mirrorButton.setIcon(new ImageIcon("img/mirror.png"));
        try {
            Image img = ImageIO.read(getClass().getResource("img/mirror.png"));
            mirrorButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(mirrorButton, c);


        JButton finishButton = new JButton("Dalej");
        //finishButton.addActionListener(listener);
        c.gridx = 0;
        c.gridy = 3;
        //gbc.gridheight = 2 ;
        buttonPanel.add(finishButton, c);

        JButton deleteButton = new JButton("Usuń");
        //deleteButton.addActionListener(listener);
        c.gridx = 0;
        c.gridy = 2;
        buttonPanel.add(deleteButton, c);


        buttonPanel.setBackground(Color.BLACK);
        this.add(buttonPanel, BorderLayout.EAST);

//        //pomocniczy panel
//        JPanel parentPanel = new JPanel();
//        parentPanel.setLayout(new BorderLayout());
//        parentPanel.add(textPane, BorderLayout.CENTER);
//        parentPanel.add(startButton, BorderLayout.SOUTH);
//
//        this.add(parentPanel);
    }
}
