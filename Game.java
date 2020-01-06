import javax.swing.*;
import java.util.*;

public class Game {
    protected int[][] map;
    public int lvl;
    private int[] components;
    Frame frame;
    Level level;
    public int width;
    public int height;
    public int cols;
    public int rows;
    public int[] startCoordinates;
    public char startDir;
    public int[] finishCoordinates;
    public int numOfMirrors;
    public boolean finished;

    public List<int[]> laserRoute = new ArrayList<>();

    public Game(Frame frame){
        this.frame = frame;
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        width = frameWidth - (frameWidth/4) - 5;
        height = frameHeight - (frameHeight/15);

        cols = width/40;
        rows = height/40;

        map = new int[cols][rows];

        for(int y = 0 ; y<rows; y++){
            for(int x = 0; x<cols; x++){
                map[x][y] = 0;
            }
        }
        finished = false;

        lvl = 1;

        loadMap();

        generateLaserRoute(startCoordinates[0], startCoordinates[1], startDir);

    }

    protected void addObject(int x, int y, int object){
        if(x < cols && y < rows)
            map[x][y] = object;
        refresh();
    }

    protected void deleteObject(int x, int y){
        if(x >= 0 && x < cols && y>=0 && y < rows)
            map[x][y] = 0;
        refresh();
    }

    protected void refresh(){
        laserRoute = new ArrayList<>();
        this.map = level.map;
        generateLaserRoute(startCoordinates[0], startCoordinates[1], startDir);
    }

    protected void finish(){
        JOptionPane.showMessageDialog(frame, "Congratulations! \n You did it!");
        finished = false;
        lvl += 1;
        loadMap();
        refresh();
        frame.getContentPane().repaint();
    }

    private void loadMap(){
        level = new Level(this, lvl);
        this.map = level.map;
        this.startCoordinates = level.startCoordinates;
        this.startDir = level.startDir;
        this.numOfMirrors = level.numOfMirrors;
    }

    protected void reset(){
        loadMap();
        refresh();
        frame.getContentPane().repaint();
    }

    protected void generateLaserRoute(int x, int y, char dir){
        int[] a = {x, y};
        laserRoute.add(a);
        switch (dir){
            case 'n': {
                if(y>0)y--;
                while (y > 0 && map[x][y] == 0){
                    y--;
                }
                if(map[x][y] == 10){//SE
                    generateLaserRoute(x,y,'e');
                }
                else if(map[x][y] == 11){//SW
                    generateLaserRoute(x,y,'w');
                }
            }
            break;
            case 's': {
                if(y<rows-1)y++;
                while (y < rows-1 && map[x][y] == 0){
                    y++;
                }
                if(map[x][y] == 8){//NW
                    generateLaserRoute(x,y,'w');
                }
                else if(map[x][y] == 9){//NE
                    generateLaserRoute(x,y,'e');
                }
            }
            break;
            case 'w': {
                if(x>0)x--;
                while (x > 0 && map[x][y] == 0){
                    x--;
                }
                if(map[x][y] == 9){//NE
                    generateLaserRoute(x,y,'n');
                }
                else if(map[x][y] == 10){//SE
                    generateLaserRoute(x,y,'s');
                }
            }
            break;
            case 'e': {
                if(x<cols-1)x++;
                while (x < cols-1 && map[x][y] == 0){
                    x++;
                }
                if(map[x][y] == 8){//NW
                    generateLaserRoute(x,y,'n');
                }
                else if(map[x][y] == 11){//SW
                    generateLaserRoute(x,y,'s');
                }
            }
            break;
            default:
                break;
        }
        if(map[x][y] == 1) {
            map[x][y] = 2;
            int[] b = {x, y};
            laserRoute.add(b);
            finished = true;
        }
        else{
            int[] b = {x, y};
            laserRoute.add(b);
        }
    }





}


/*
0 - puste
1 - meta Szara
2 - meta Aktywowana
3 - start N
4 - start S
5 - start W
6 - start E
7 - ściana
8 - lustro NW
9 - lustro NE
10 - lustro SE
11 - lustro SW
*/