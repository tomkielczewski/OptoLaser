import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private final JEditorPane textPane = new JEditorPane();
    private JButton startButton;
    private InfoListener listener;

    String text = "Prawo odbicia\nKąt odbicia jest równy kątowi padania, a promień padający, promień odbity i normalna (prostopadła do powierzchni zwierciadła) leżą w jednej płaszczyźnie.";

    public InfoPanel(InfoListener infoListener) {
        super();

        setLayout(new BorderLayout());

        this.listener = infoListener;
        this.listener.setPanel(this);

        createComponents();
    }

    private void createComponents(){

        textPane.setEditable(false);
        textPane.setText(text);
        this.add(textPane,  BorderLayout.CENTER);

        startButton = new JButton("Start");
        startButton.addActionListener(listener);
        this.add(startButton, BorderLayout.SOUTH);

    }

}
