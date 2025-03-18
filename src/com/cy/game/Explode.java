package com.cy.game;

import com.cy.tank.Tank;

import java.awt.*;

public class Explode {
    private int x,y;
    private static Image[] images = new Image[7];
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    static {
        for (int i = 0; i < 7; i++) {
            images[i] = Toolkit.getDefaultToolkit().createImage("images/explode/"+(i+1)+".gif" );
        }
    }
    public void drawBom(Graphics g, Tank tank){
        System.out.println("index"+index);
        if(index < 7) g.drawImage(images[index],x,y,null );
        index++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
