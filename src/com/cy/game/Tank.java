package com.cy.game;

import com.cy.util.Constnt;
import com.sun.org.apache.bcel.internal.Const;

import java.awt.*;

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

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;

    public Tank(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawTank(Graphics g) {
        judgeStatus();
        g.fillOval(x, y, width, height);
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
                break;
        }
    }

    private void tankStand() {
    }

}
