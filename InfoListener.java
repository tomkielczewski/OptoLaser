import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoListener implements ActionListener {

    private final Frame frame;
    private InfoPanel infoPanel;

    public void setPanel(InfoPanel infoPanel) {

        this.infoPanel = infoPanel;
    }

    public InfoListener(Frame frame) {

        this.frame = frame;
    }


    @Override
    public void actionPerformed(ActionEvent event) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // panel z canvas-em
                Game game = new Game(frame);
                JPanel gamePanel = new GamePanel(frame, game);
                // usuwanie panelu startowego
                frame.getContentPane().removeAll();
                // dodanie panelu gry i odswiezenie
                frame.add(gamePanel);
                frame.validate();
            }
        });
    }
}
