package com.cy.tank;

import com.cy.game.GameFrame;
import com.cy.util.Constnt;

import java.awt.*;

public class Enemy extends Tank{
    private int time;
    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.time = (int)(System.currentTimeMillis());
    }
    public void drawEnemy(Graphics g) {
        stupidMove();
        g.drawImage(enemyTankImages[getDir()], getX(), getY(), null);
    }
    public void stupidMove() {
        if((int)(System.currentTimeMillis()) - this.time > 3000){
            this.time = (int)(System.currentTimeMillis());
            //取值范围[0,1)作闭右开，
            setDir((int)(Math.random() * (LEFT + 1)));
            setStatus((int)(Math.random() * STATUS_DEAD));
        }
        if(Math.random() < Constnt.FIRE_PROBABILITY){
            fire();
        }
    }
}
