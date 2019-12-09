import java.awt.*;

public class GraphicsDisplay  extends Canvas {

    private int width, height, cols, rows, x, y, thickness, rowHeight, rowWidth;
    Color darkishBlue = new Color(0, 26, 51);

    Game game;

    public GraphicsDisplay(Game game){
        this.game = game;

        this.width = game.width;
        this.height = game.height;

        this.cols = game.cols;
        this.rows = game.rows;

        rowHeight = height / (rows);
        rowWidth = width / (cols);
    }


    private void drawGrid(Graphics g){
        int i;
        //int rowHeight = height / (rows);
        for (i = 0; i < rows; i++)
            g.drawLine(0, i * rowHeight, width, i * rowHeight);

        //int rowWidth = width / (cols);
        for (i = 0; i < cols; i++)
            g.drawLine(i * rowWidth, 0, i * rowWidth, height);
    }


    private void drawBorder(Graphics g){
        g.drawLine(0, 0, width, 0);
        g.drawLine(0, height, width, height);
        g.drawLine(0, 0, 0, height);
        g.drawLine(width, 0, width, height);
    }

    public void drawStart(Graphics g, int x, int y, char dir){

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

    public void drawLaser(Graphics g){
        //g.drawLine(x1*rowWidth + (rowWidth/2), y1*rowHeight + (rowHeight/2), x2*rowWidth + (rowWidth/2), y2*rowHeight + (rowHeight/2));
        int size = game.laserRoute.size();
        int x1,y1, x2, y2;
        for(int i = 0 ; i<size-1; i++){
            x1 = game.laserRoute.get(i)[0];
            y1 = game.laserRoute.get(i)[1];
            x2 = game.laserRoute.get(i+1)[0];
            y2 = game.laserRoute.get(i+1)[1];
            g.drawLine(x1*rowWidth + (rowWidth/2), y1*rowHeight + (rowHeight/2), x2*rowWidth + (rowWidth/2), y2*rowHeight + (rowHeight/2));
        }
    }

    //TODO
    // Mirror
    public void drawMirror(Graphics g){
        g.fillArc(rowWidth*(cols/2-1), 2*rowHeight, rowWidth,rowHeight,45+ 90+180,180);
        g.fillArc(rowWidth*(cols/2-1), (rows-2)*rowHeight, rowWidth,rowHeight,45+ 90,180);
    }

    public void drawTarget(Graphics g, int x, int y){
        g.drawOval(x*rowWidth, y*rowHeight, rowWidth, rowHeight);
        g.fillOval(x*rowWidth + (rowWidth/2-rowWidth/8), y*rowHeight + (rowHeight/2-rowHeight/8), rowWidth/4, rowHeight/4);
    }

    public void drawWall(Graphics g, int x, int y){
        g.fillRect(x*rowWidth, y*rowHeight, rowWidth, rowHeight);
    }

    public void drawDemo(Graphics g){
        drawGrid(g);
        g.setColor( darkishBlue);
        //drawBorder(g);

        //if(game!=null)drawWalls(g);

        g.setColor(Color.green);
       // drawLaser(g);

        g.setColor(Color.blue);
        //drawMirror(g);

        g.setColor(Color.green);
        //drawTarget(g);
        //g.fillRect(x,y, thickness, thickness);
//        g.setColor(Color.white);
//        g.fillRect(297,397, 6, 6);
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
8 - lustro
*/

    public void drawLvl(Graphics g){
        drawGrid(g);
        g.setColor( darkishBlue);
        drawBorder(g);

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
            }
        }

    }

    public void paint(Graphics g){



        //super.paint(g);
        setBackground(Color.BLACK);
        g.setColor(darkishBlue);

       //drawDemo(g);
        drawLvl(g);

    }
}
