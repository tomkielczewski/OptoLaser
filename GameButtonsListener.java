import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButtonsListener  implements ActionListener {

    private GamePanel gamePanel;

    public GameButtonsListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    @Override
    public void actionPerformed(ActionEvent event) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Object source = event.getSource();

                if(source == gamePanel.resetButton)
                    gamePanel.game.reset();
                else if(source == gamePanel.backToStartButton){
                    gamePanel.game.lvl = 1;
                    gamePanel.game.reset();
                }
            }
        });
    }
}
