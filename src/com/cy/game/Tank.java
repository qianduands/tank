package com.cy.game;

import com.cy.util.Constnt;

import java.awt.*;

public class Tank {
    private int x, y;
    private int width, height;
    private int speed = 10;

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
                y -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;

        }
    }

    private void tankStand() {
    }

}
