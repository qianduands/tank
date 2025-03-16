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
        if(index < 7) g.drawImage(images[index],tank.getX(),tank.getY(),null );
        index++;
    }
}
