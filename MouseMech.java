import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MouseMech implements MouseListener, MouseMotionListener{
    int x;
    int y;
    GraphicsDisplay gameArea;
    Game game;
    boolean wasMap;
    boolean mirrorPressed;
    boolean mirrorDraged;

    int[] coor;

    MouseMech(GraphicsDisplay gameArea){
        this.gameArea = gameArea;
        x = 0;
        y = 0;
        wasMap = false;
        mirrorPressed = false;
        mirrorDraged = false;

        this.game = gameArea.game;

    }


    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        if( gameArea.isMirrorButton(x,y)) {
            gameArea.mirrorButtonDir += 1;
            gameArea.mirrorButtonDir %= 4;
            game.refresh();
            gameArea.repaint();
        }
        else if(gameArea.isMap(x,y)){
            coor = gameArea.locateClick(x,y);
            if(game.map[coor[0]][coor[1]]>=8 && game.map[coor[0]][coor[1]]<12) { //Change if more objects added
                int dir = game.map[coor[0]][coor[1]] - 8;
                dir += 1;
                dir %= 4;
                game.map[coor[0]][coor[1]] = dir + 8;
                game.refresh();
                gameArea.repaint();
            }
        }
        gameArea.isDragged = false;
    }


    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        //In case of adding other objects variable isDragged is invalid
        if( gameArea.isMirrorButton(x,y)) {
            if(game.numOfMirrors > 0) {
                gameArea.dragDir = gameArea.mirrorButtonDir;
                gameArea.isDragged = true;
                mirrorPressed = true;
            }
        }
        else if(gameArea.isMap(x,y)) {
            coor = gameArea.locateClick(x,y);
            if(game.map[coor[0]][coor[1]]>=8 && game.map[coor[0]][coor[1]]<12){ //Change if more objects added
                gameArea.dragDir = game.map[coor[0]][coor[1]] - 8;
                wasMap = true;
                gameArea.isDragged = true;
            }
        }
    }
    public void mouseReleased(MouseEvent e) {
        x=e.getX();
        y=e.getY();

        if(gameArea.isDragged){
            coor = gameArea.locateClick(x,y);
            game.addObject(coor[0], coor[1], gameArea.dragDir+8);
            gameArea.repaint();
            gameArea.isDragged = false;
            wasMap = false;
        }

        if(gameArea.isMap(x,y))
            if(mirrorDraged)
                game.numOfMirrors -= 1;

        mirrorPressed = false;
        mirrorDraged = false;
    }

    public void mouseDragged(MouseEvent e){
        x=e.getX();
        y=e.getY();


        gameArea.coursorX = x;
        gameArea.coursorY = y;
        if(wasMap) {
            game.deleteObject(coor[0], coor[1]);
        }
        gameArea.repaint();
        if(mirrorPressed)mirrorDraged = true;

    }

    public void mouseMoved(MouseEvent e){ }

}