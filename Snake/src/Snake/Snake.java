package Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    List<Point> snakePoints;
    int xDirection,yDirection;
    boolean isMoving,elongate;
    final int STARTSIZE =20,STARTX =150,STARTY =150;

    public Snake() {
        snakePoints = new ArrayList<Point>();
        xDirection=0;
        yDirection=0;
        isMoving=false;
        elongate=false;
        snakePoints.add(new Point(STARTX,STARTY));
        for(int i = 1; i< STARTSIZE; i++){
            snakePoints.add(new Point(STARTX - i * 4,STARTY));
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        for(Point p : snakePoints){
            g.fillRect(p.getX(),p.getY(),4,4);//snake size 4,4
        }
    }

    public void move(){
        if(isMoving){
            Point temp = snakePoints.get(0);
            Point last = snakePoints.get(snakePoints.size()-1);//gets the last square of snake
            Point newStart = new Point(temp.getX() + xDirection * 4,temp.getY() + yDirection * 4);//makes movements multiples of 4

            for(int i = snakePoints.size()-1; i >= 1;i--){//all body becomes the one before it
                snakePoints.set(i,snakePoints.get(i-1));
            }
            snakePoints.set(0,newStart);
            if(elongate){
                snakePoints.add(last);//adds another square at the previous last square
                elongate = false;
            }
        }
    }

    public boolean snakeCollision(){
        int x = this.getX();//gets head location
        int y = this.getY();
        for(int i = 1; i < snakePoints.size();i++){
            if(snakePoints.get(i).getX() == x && snakePoints.get(i).getY() == y ){
                return true;//checks if head crosses any part of body
            }
        }
        return false;
    }

    public boolean getIsMoving(){
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getxDirection() {
        return xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    //X position of head of snake
    public int getX(){
        return snakePoints.get(0).getX();
    }
    //Y position of head of snake
    public int getY(){
        return snakePoints.get(0).getY();
    }


    public void setElongate(boolean e){
        elongate = e;
    }
}
