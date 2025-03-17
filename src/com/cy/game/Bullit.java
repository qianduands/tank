package com.cy.game;

import com.cy.tank.Tank;
import com.cy.util.Constnt;

import java.awt.*;

public class Bullit {
    private int width = 10;
    private int height = 10;
    private int x, y;
    private int dir;
    private int atk;
    private int speed = 60;
    private Boolean isVisible = true;
    private Color color;
    public Boolean getVisible() {
        return isVisible;
    }

    public Bullit() {
    }

    public Bullit(int x, int y, int dir, int atk,Color color) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.atk = atk;
        this.color = color;
    }


    public void logic() {
        switch (dir) {
            case Tank.UP:
                y -= speed;
                if (y < 0) isVisible = false;
                break;
            case Tank.RIGHT:
                x += speed;
                if (x > Constnt.GAME_WIDTH) isVisible = false;
                break;
            case Tank.DOWN:
                y += speed;
                if (y > Constnt.GAME_HEIGHT) isVisible = false;
                break;
            case Tank.LEFT:
                x -= speed;
                if (x < 0) isVisible = false;
                break;
        }
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public void draw(Graphics g) {
        logic();
        g.setColor(color);
        if(isVisible) g.fillOval(x, y, width, height);
        g.setColor(Color.white);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAtk() {
        return atk;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bullit{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
