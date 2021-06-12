package Snake;

import java.awt.*;

public class Token {

    private int x,y,score;
    private Snake snake;

    public Token(Snake s) {
        x = (int) (Math.random() * 395);//random location on the visible board
        y = (int) (Math.random() * 395);//random location on the visible board
        snake = s;
    }

    public void changePosition(){
        x = (int) (Math.random() * 395);//random location on the visible board
        y = (int) (Math.random() * 395);//random location on the visible board
    }

    public int getScore(){
        return score;
    }

    public void draw(Graphics g){
        g.setColor(Color.green);
        g.fillRect(x,y,6,6); //size of apple
    }

    public boolean snakeCollision(){
        int snakeX = snake.getX() + 2;
        int snakeY = snake.getY() + 2; //center of rectangle
        if(snakeX >= (x-1) && snakeX <= (x+7)){
            if(snakeY >= (y-1) && snakeY <= (y+7)){//compares snake to token to a 1 pixel overlap
                changePosition();
                score++;
                snake.setElongate(true);
                return true;
            }
        }
        return false;
    }
}
