package com.cy.game;

import com.cy.util.Constnt;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tank {
    private int x, y;
    private int width, height;
    private int speed = 30;

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public static final int STATUS_STAND = 0;
    public static final int STATUS_MOVE = 1;
    public static final int STATUS_DEAD = 2;

    private int dir;
    private List<Bullit> arrList = new ArrayList();
    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
    private int atk;
    public static Image[] tankIamges = new Image[4];
    public static String[] tankIamgesNames = {
            "p1tankU.gif",
            "p1tankR.gif",
            "p1tankD.gif",
            "p1tankL.gif",
    };
    static {
        for (int i = 0; i < 4;i++){
            tankIamges[i] = Toolkit.getDefaultToolkit().createImage("images/player1/"+tankIamgesNames[i]);
        }
    }
    public Tank(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawTank(Graphics g) {
        judgeStatus();
        drawBullits(g);
//        g.fillOval(x, y, width, height);
        g.drawImage(tankIamges[dir],x,y,null);
    }


    private void judgeStatus() {
        switch (status) {
            case STATUS_STAND:
                tankStand();
                break;
            case STATUS_MOVE:
                tankMove();
                break;
            case STATUS_DEAD:
                tankDead();
        }
    }

    private void tankDead() {

    }

    private void tankMove() {
        switch (dir) {
            case UP:
                if(y > GameFrame.titleBarH)  y -= speed;
               else y = GameFrame.titleBarH;
                break;
            case RIGHT:
                if(x > Constnt.GAME_WIDTH - GameFrame.borderRight - width + speed) x = Constnt.GAME_WIDTH - GameFrame.borderRight - width;
                else x += speed;
                break;
            case DOWN:
                if(y < Constnt.GAME_HEIGHT - height) y += speed;
                else y = Constnt.GAME_HEIGHT - height;
                break;
            case LEFT:
                if(x <= width+speed) x = width;
                else x -= speed;
                System.out.println(x+","+y);
                break;
        }
    }
    public void fire(){
         int x = 0;
         int y = 0;
        switch (dir){
            case UP:
                x = this.x  + 20;
                y = this.y - (tankIamges[0].getHeight(null) >> 1) + 50 + (status == STATUS_MOVE ? -speed : 0) ;
                break;
            case RIGHT:
                x = this.x + (tankIamges[0].getHeight(null) >> 1)  - 20 + (status == STATUS_MOVE ? +speed : 0 );
                y = this.y + 20;
                break;
            case DOWN:
                x = this.x + 20;
                y = this.y + (tankIamges[0].getHeight(null) >> 1) - 20 + (status == STATUS_MOVE ? +speed : 0);
                break;
            case LEFT:
                x = this.x - (tankIamges[0].getWidth(null) >> 1)  + 50 + (status == STATUS_MOVE ? -speed : 0);
                y = this.y + 20;
        }
        Bullit bullit = new Bullit(x, y, dir, atk);
        arrList.add(bullit);

    }
    public void drawBullits(Graphics g){
        arrList.forEach(item->{
            item.draw(g);
        });
    }
    private void tankStand() {
    }

}
