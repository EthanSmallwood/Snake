package Snake;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends Applet implements Runnable, KeyListener {//makes it so the program can run and gets java classes required
    //initialises everything
    Graphics gfx;
    Image img;
    Thread thread;
    Snake snake;
    boolean gameOver;
    Token token;

    public void init() {
        this.resize(400,400);//size of game
        gameOver = false;
        img = createImage(400,400);
        gfx = img.getGraphics();
        this.addKeyListener(this);
        snake = new Snake();
        token = new Token(snake);
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g) {
        gfx.setColor(Color.BLACK);//sets background black
        gfx.fillRect(0,0,400,400);//size of background
        if(!gameOver){
            snake.draw(gfx);//shows snake
            token.draw(gfx);//shows token
        }else{//end screen
            gfx.setColor(Color.red);//makes text red
            gfx.drawString("Game Over",180,150);//game over
            gfx.drawString("Score: "+ token.getScore(),180,170);//score: i
        }
        g.drawImage(img,0,0,null);//draws everything
    }

    public void update(Graphics g){
        paint(g);

    }

    public void repaint(Graphics g){
        paint(g);
    }

    @Override
    public void run() {
        for(;;){//infinite loop
            if(!gameOver){
                snake.move();//moves snake
                this.checkGameOver();//checks if user made an error
                token.snakeCollision();//sees if snake hit the token
            }
            this.repaint();//updates the applications

            try {
                Thread.sleep(40);//speed of snake
            } catch (InterruptedException e) {
                e.printStackTrace();//returns error
            }
        }
    }

    public void checkGameOver() {
        if(snake.getX() < 0 || snake.getX() > 396){//accounts for size of square
            gameOver=true;
        }
        if(snake.getY() < 0 || snake.getY() > 396){//accounts for size of square
            gameOver=true;
        }
        if(snake.snakeCollision()){//checks if snake overlaps
            gameOver=true;
        }


    }

    @Override
    public void keyPressed(KeyEvent e) {//key presses
        if(!snake.getIsMoving()){//starts game
            if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN  ||e.getKeyCode()==KeyEvent.VK_RIGHT ){
                snake.setMoving(true);
            }
        }

       if(e.getKeyCode()==KeyEvent.VK_UP){
           if(snake.getyDirection()!=1){//if not going down then can go up
                snake.setyDirection(-1);//going up
                snake.setxDirection(0);//cant go diagonal
           }
       }
       if(e.getKeyCode()==KeyEvent.VK_DOWN){
           if(snake.getyDirection()!= -1){//if not going up then can go up
               snake.setyDirection(1);//going down
               snake.setxDirection(0);//cant go diagonal
           }
       }
       if(e.getKeyCode()==KeyEvent.VK_LEFT){
           if(snake.getxDirection()!= 1){//if not going right then can go left
               snake.setxDirection(-1);//going left
               snake.setyDirection(0);//cant go diagonal
           }
       }
       if(e.getKeyCode()==KeyEvent.VK_RIGHT){
           if(snake.getxDirection()!= -1){//if not going left then can go right
               snake.setxDirection(1);//going right
               snake.setyDirection(0);//cant go diagonal
           }
       }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
