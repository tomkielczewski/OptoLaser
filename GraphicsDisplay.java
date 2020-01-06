import java.awt.*;

public class GraphicsDisplay  extends Canvas {

    public int coursorX, coursorY;
    private int width, height, cols, rows, rowHeight, rowWidth;
    Color darkishBlue = new Color(0, 26, 51);

    Game game;
    Graphics g;

    boolean isDragged;
    //This value should be between 0 and 3.
    public int mirrorButtonDir;
    public int dragDir;

    public GraphicsDisplay(Game game){
        this.game = game;

        this.width = game.width;
        this.height = game.height;

        this.cols = game.cols;
        this.rows = game.rows;

        rowHeight = height / (rows);
        rowWidth = width / (cols);
        mirrorButtonDir = 0;
        isDragged = false;
        dragDir = 0;
    }

    public void paint(Graphics g){

        this.g = g;

        //super.paint(g);
        setBackground(Color.BLACK);
        g.setColor(darkishBlue);

        //drawDemo(g);
        drawGrid(g);
        drawButtons(g);
        g.setColor( darkishBlue);
        drawBorder(g);
        if(isDragged){
            g.setColor(Color.BLUE);
            drawMirrorByCoordinates(g, coursorX-rowWidth/2, coursorY-rowHeight/2, dragDir+8);
        }

        drawLvl(g);

        if(game.finished)game.finish();
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
    public void drawLvl(Graphics g){
        int[][] map = game.map;

        g.setColor(Color.GREEN);
        drawLaser(g);

        for(int y = 0 ; y<rows; y++){
            for(int x = 0; x<cols; x++){
                switch (map[x][y]){
                    case 1:
                        g.setColor(Color.GRAY);
                        drawTarget(g, x, y);
                        break;
                    case 2:
                        g.setColor(Color.GREEN);
                        drawTarget(g, x, y);
                        break;
                    case 3:
                        g.setColor(Color.GREEN);
                        drawStart(g, x, y, 'n');
                        break;
                    case 4:
                        g.setColor(Color.GREEN);
                        drawStart(g, x, y, 's');
                        break;
                    case 5:
                        g.setColor(Color.GREEN);
                        drawStart(g, x, y, 'w');
                        break;
                    case 6:
                        g.setColor(Color.GREEN);
                        drawStart(g, x, y, 'e');
                        break;
                    case 7:
                        g.setColor(darkishBlue);
                        drawWall(g, x, y);
                        break;
                    default:
                        break;
                }
                if(map[x][y] >= 8 && map[x][y] <= 11){
                    g.setColor(Color.BLUE);
                    drawMirror(g, x, y, map[x][y]);
                }
            }
        }
    }



    public boolean isMirrorButton(int x, int y){
        if(x >= (cols+1) * rowWidth && x < (cols+2) * rowWidth && y >= (cols/2 - 1) * rowHeight && y < (cols/2) * rowHeight)
            return true;
        else
            return false;
    }

    public boolean isMap(int x, int y){
        if(x >= 0 && x < width  && y >= 0 && y < height)
            return true;
        else
            return false;
    }

    //Finds place on map where to put new object after dragging
    protected int[] locateClick(int x, int y){
        int[] coor = new int[2];
        for(int j = 0 ; j<rows; j++) {
            for (int i = 0; i < cols; i++) {
                if(x >= i * rowWidth && x < (i + 1) * rowWidth && y >= j * rowHeight &&  y < (j + 1) * rowHeight){
                    coor[0] = i;
                    coor[1] = j;
                    return coor;
                }
            }
        }
        coor[0] = 999;
        coor[1] = 999;
        return coor;
    }

    private void drawGrid(Graphics g){
        int i;
        for (i = 0; i < rows; i++)
            g.drawLine(0, i * rowHeight, width, i * rowHeight);

        for (i = 0; i < cols; i++)
            g.drawLine(i * rowWidth, 0, i * rowWidth, height);
    }

    private void drawButtons(Graphics g){
        int i;
        for (i = 1; i <= 3; i++)
            g.drawLine((cols+i) * rowWidth, (cols/2 - 1) * rowHeight, (cols+i) * rowWidth, (cols/2) * rowHeight);

        for (i = 0; i < 2; i++)
            g.drawLine((cols+1) * rowWidth, (cols/2 - i) * rowHeight, (cols+3) * rowWidth, (cols/2 - i) * rowHeight);

        g.setColor(Color.BLUE);
        drawMirror(g, cols+1, cols/2 - 1, 8 + mirrorButtonDir);
        g.setColor(SystemColor.LIGHT_GRAY);
        g.setFont(new Font("Courier New", Font.PLAIN, 20));
        drawNumOfMirrors(g, game.numOfMirrors);
        drawLvl(g,game.lvl);

    }

    private void drawNumOfMirrors(Graphics g, int num){
        char[] sign = new char[1];
        sign[0] = (char)(num + '0');
        g.drawChars(sign, 0,1, (cols+2) * rowWidth + 3*rowHeight/8, (cols/2 - 1) * rowHeight + 5*rowHeight/8);
    }

    private void drawLvl(Graphics g, int num){
        char[] sign = new char[]{'l','e','v','e','l',':',' ','.'};
        sign[7] = (char)(num + '0');
        g.drawChars(sign, 0,8, (cols+1) * rowWidth , 3 * rowHeight);
    }


    private void drawBorder(Graphics g){
        g.drawLine(0, 0, width, 0);
        g.drawLine(0, height, width, height);
        g.drawLine(0, 0, 0, height);
        g.drawLine(width, 0, width, height);
    }

    private void drawStart(Graphics g, int x, int y, char dir){

        switch (dir){
            case 'n': {
                int[] xs  = {x * rowWidth + (rowWidth/4), (x+1) * rowWidth - (rowWidth/4), x * rowWidth + (rowWidth / 2)};
                int[] ys = {(y+1) * rowHeight, (y+1) * rowHeight, y * rowHeight + (rowHeight / 2)};
                g.drawPolygon(xs ,ys ,3);
            }
            break;
            case 's': {
                int[] xs  = {x * rowWidth + (rowWidth/4), (x+1) * rowWidth - (rowWidth/4), x * rowWidth + (rowWidth / 2)};
                int[] ys = {y * rowHeight, y * rowHeight, y * rowHeight + (rowHeight / 2)};
                g.drawPolygon(xs ,ys ,3);
            }
            break;
            case 'w': {
                int[] xs  = {(x+1) * rowWidth, (x+1) * rowWidth, x * rowWidth + (rowWidth / 2)};
                int[] ys = {y * rowHeight + (rowHeight/4), (y+1) * rowHeight - (rowHeight/4), y * rowHeight + (rowHeight / 2)};
                g.drawPolygon(xs ,ys ,3);
            }
            break;
            case 'e': {
                int[] xs  = {x * rowWidth, x * rowWidth, x * rowWidth + (rowWidth / 2)};
                int[] ys = {y * rowHeight + (rowHeight/4), (y+1) * rowHeight - (rowHeight/4), y * rowHeight + (rowHeight / 2)};
                g.drawPolygon(xs ,ys ,3);
            }
            break;
            default:
                break;
        }
        g.fillOval(x*rowWidth + (rowWidth/2-rowWidth/8), y*rowHeight + (rowHeight/2-rowHeight/8), rowWidth/4, rowHeight/4);
    }

    private void drawLaser(Graphics g){
        //g.drawLine(x1*rowWidth + (rowWidth/2), y1*rowHeight + (rowHeight/2), x2*rowWidth + (rowWidth/2), y2*rowHeight + (rowHeight/2));
        int size = game.laserRoute.size();
        int x1,y1, x2, y2;
        for(int i = 0 ; i<size-1; i++){
            x1 = game.laserRoute.get(i)[0];
            y1 = game.laserRoute.get(i)[1];
            x2 = game.laserRoute.get(i+1)[0];
            y2 = game.laserRoute.get(i+1)[1];
            if(game.map[x2][y2] == 0) {
                if (y2 == 0 && y1 > y2)
                    g.drawLine(x1 * rowWidth + (rowWidth / 2), y1 * rowHeight + (rowHeight / 2), x2 * rowWidth + (rowWidth / 2), 0);
                else if (y2 == rows - 1 && y1 < y2)
                    g.drawLine(x1 * rowWidth + (rowWidth / 2), y1 * rowHeight + (rowHeight / 2), x2 * rowWidth + (rowWidth / 2), height);
                else if (x2 == 0 && x1 > x2)
                    g.drawLine(x1 * rowWidth + (rowWidth / 2), y1 * rowHeight + (rowHeight / 2), 0, y2 * rowHeight + (rowHeight / 2));
                else if (x2 == cols - 1 && x1 < x2)
                    g.drawLine(x1 * rowWidth + (rowWidth / 2), y1 * rowHeight + (rowHeight / 2), width, y2 * rowHeight + (rowHeight / 2));
                else
                    g.drawLine(x1*rowWidth + (rowWidth/2), y1*rowHeight + (rowHeight/2), x2*rowWidth + (rowWidth/2), y2*rowHeight + (rowHeight/2));
            }
            else
                g.drawLine(x1*rowWidth + (rowWidth/2), y1*rowHeight + (rowHeight/2), x2*rowWidth + (rowWidth/2), y2*rowHeight + (rowHeight/2));
        }
    }

    private void drawMirror(Graphics g, int x, int y, int dir){
        switch (dir){
            case 8:     //NW
                g.fillArc(x*rowWidth, y*rowHeight, rowWidth,rowHeight,45 + 180,180);
                break;
            case 9:     //NE
                g.fillArc(x*rowWidth, y*rowHeight, rowWidth,rowHeight,45 + 90,180);
                break;
            case 10:    //SE
                g.fillArc(x*rowWidth, y*rowHeight, rowWidth,rowHeight,45,180);
                break;
            case 11:    //SW
                g.fillArc(x*rowWidth, y*rowHeight, rowWidth,rowHeight,45 + 90 + 180,180);
                break;
            default:
                break;
        }
    }

    public void drawMirrorByCoordinates(Graphics g, int x, int y, int dir){
        switch (dir){
            case 8:     //NW
                g.fillArc(x, y, rowWidth,rowHeight,45 + 180,180);
                break;
            case 9:     //NE
                g.fillArc(x, y, rowWidth,rowHeight,45 + 90,180);
                break;
            case 10:    //SE
                g.fillArc(x, y, rowWidth,rowHeight,45,180);
                break;
            case 11:    //SW
                g.fillArc(x, y, rowWidth,rowHeight,45 + 90 + 180,180);
                break;
            default:
                break;
        }
    }

    private void drawTarget(Graphics g, int x, int y){
        g.drawOval(x*rowWidth, y*rowHeight, rowWidth, rowHeight);
        g.fillOval(x*rowWidth + (rowWidth/2-rowWidth/8), y*rowHeight + (rowHeight/2-rowHeight/8), rowWidth/4, rowHeight/4);
    }

    private void drawWall(Graphics g, int x, int y){
        g.fillRect(x*rowWidth, y*rowHeight, rowWidth, rowHeight);
    }



//    public void drawDemo(Graphics g){
//        drawGrid(g);
//        g.setColor( darkishBlue);
//        //drawBorder(g);
//
//        //if(game!=null)drawWalls(g);
//
//        g.setColor(Color.green);
//       // drawLaser(g);
//
//        g.setColor(Color.blue);
//        //drawMirror(g);
//
//        g.setColor(Color.green);
//        //drawTarget(g);
//        //g.fillRect(x,y, thickness, thickness);
////        g.setColor(Color.white);
////        g.fillRect(297,397, 6, 6);
//    }



}
