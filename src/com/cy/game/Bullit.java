package com.cy.game;

import java.awt.*;

public class Bullit {
    private int width = 10;
    private int height = 10;
    private int x,y;
    private int dir;
    private int atk;
    private int speed = 10;
    public Bullit(int x, int y, int dir, int atk) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.atk = atk;
    }

    public void logic(){
        switch (dir){
            case Tank.UP :
                y-= speed;
                break;
            case Tank.RIGHT:
                x += speed;
                break;
            case Tank.DOWN:
                y += speed;
                break;
            case Tank.LEFT:
                x -= speed;
                break;
        }
    }
    public void draw(Graphics g){
        logic();
        g.fillOval(x,y,width,height);
    }
}
