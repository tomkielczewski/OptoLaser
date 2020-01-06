import javax.swing.*;

public class Level {

    protected int[][] map;

    public int[] startCoordinates;
    public char startDir;
    public int[] finishCoordinates;
    public int numOfMirrors;

    public int cols, rows;

    public Level(Game game, int lvl){
        this.cols = game.cols;
        this.rows = game.rows;

        map = new int[cols][rows];

        for(int y = 0 ; y<rows; y++){
            for(int x = 0; x<cols; x++){
                map[x][y] = 0;
            }
        }
        switch (lvl){
            case 1:
                startCoordinates = new int[]{0,rows-1};
                startDir = 'e';
                finishCoordinates = new int[]{cols-1, rows-1};

                numOfMirrors = 3;

                map[startCoordinates[0]][startCoordinates[1]] = 6;
                map[10][rows-1] = 7;
                map[10][rows-2] = 7;
                map[10][rows-3] = 7;
                map[10][rows-4] = 7;
                map[10][rows-5] = 7;
                map[10][rows-6] = 7;
                map[finishCoordinates[0]][finishCoordinates[1]] = 1;
                break;
            case 2:
                startCoordinates = new int[]{0,rows-1};
                startDir = 'e';
                finishCoordinates = new int[]{cols-1, rows-1};

                numOfMirrors = 9;

                map[startCoordinates[0]][startCoordinates[1]] = 6;
                for(int i=0; i<7; i++)map[i][rows-2]=7;
                for(int i=6; i<8; i++)map[i][rows-3]=7;
                for(int i=7; i<9; i++)map[i][rows-4]=7;

                for(int i=5; i<6; i++)map[8][rows-i]=7;

                for(int i=8; i<11; i++)map[i][rows-7]=7;

                for(int i=1; i<8; i++)map[10][rows-i]=7;
                for(int i=1; i<3; i++)map[9][rows-i]=7;
                map[8][rows-1] = 7;

                map[finishCoordinates[0]][finishCoordinates[1]] = 1;
                break;
//            case 3:
//                break;
            default:
                JOptionPane.showMessageDialog(game.frame, "You managed to complete all levels!");
                break;
        }
    }
}
