import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    GraphicsDisplay gameArea;
    MouseMech listener;
    GameButtonsListener buttonListener;
    Frame frame;
    Game game;

    JPanel buttonPanel;
    JButton resetButton;
    JButton backToStartButton;

    public GamePanel(Frame frame, Game game){
        super();

        this.frame = frame;
        this.game = game;

        setPreferredSize(new Dimension(game.width, game.height));
        BorderLayout gridLay = new BorderLayout();
        setLayout(gridLay);


        createComponents();
    }

    private void createComponents(){

        gameArea = new GraphicsDisplay(game);
        listener = new MouseMech(gameArea);
        gameArea.addMouseListener(listener);
        gameArea.addMouseMotionListener(listener);
        this.add(gameArea, BorderLayout.CENTER);


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        buttonListener = new GameButtonsListener(this);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(buttonListener);
        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(resetButton, c);

        backToStartButton = new JButton("Wróć na start");
        backToStartButton.addActionListener(buttonListener);
        c.gridx = 0;
        c.gridy = 2;
        buttonPanel.add(backToStartButton, c);


        buttonPanel.setBackground(Color.BLACK);
        this.add(buttonPanel, BorderLayout.EAST);

    }
}
