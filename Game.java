import java.util.*;

public class Game {
    protected int[][] map;
    public int lvl;
    private int[] components;
    Frame frame;
    public int width;
    public int height;
    public int cols;
    public int rows;

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


        map[0][rows-1] = 6;
        map[7][rows-1] = 7;
        map[cols-1][rows-1] = 1;

        generateLaserRoute(0,rows-1, 'e');

    }

    protected void generateLaserRoute(int x, int y, char dir){
        int[] a = {x, y};
        laserRoute.add(a);
        switch (dir){
            case 'n': {
                do {
                    y--;
                } while (y > 0 && map[x][y] == 0);
                if(map[x][y] == 10){//SE
                    generateLaserRoute(x,y,'e');
                }
                else if(map[x][y] == 11){//SW
                    generateLaserRoute(x,y,'w');
                }
            }
            break;
            case 's': {
                do {
                    y++;
                } while (y < rows-1 && map[x][y] == 0);
                if(map[x][y] == 8){//NW
                    generateLaserRoute(x,y,'w');
                }
                else if(map[x][y] == 9){//NE
                    generateLaserRoute(x,y,'e');
                }
            }
            break;
            case 'w': {
                do {
                    x--;
                } while (x > 0 && map[x][y] == 0);
                if(map[x][y] == 9){//NE
                    generateLaserRoute(x,y,'n');
                }
                else if(map[x][y] == 10){//SE
                    generateLaserRoute(x,y,'s');
                }
            }
            break;
            case 'e': {
                do {
                    x++;
                } while (x < cols-1 && map[x][y] == 0);
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
//TODO
8 - lustro NW
9 - lustro NE
10 - lustro SE
11 - lustro SW
*/