package com.cy.map;

import com.cy.util.BrickPool;

import java.awt.*;
import java.sql.Array;

public class Home {
    private int x, y;
    private Brick[] brickArray = new Brick[6];

    public Brick[] getBrickArray() {
        return brickArray;
    }

    public Home(int x, int y) {
        this.x = x;
        this.y = y;
        initHome();
    }
    public void initHome(){
        for (int i = 0; i < brickArray.length; i++) {
            brickArray[i] = BrickPool.getBrick();
            brickArray[i].setType(Brick.SOFT);
            if (i == brickArray.length - 1) brickArray[i].setType(Brick.HOME);
            switch (i) {
                case 0:
                    brickArray[i].setX(x);
                    brickArray[i].setY(y);
                    break;
                case 1:
                    brickArray[i].setX(x);
                    brickArray[i].setY(y - i * 60);
                    break;
                case 2:
                    brickArray[i].setX(x + 60);
                    brickArray[i].setY(y - 60);
                    break;
                case 3:
                    brickArray[i].setX(x + 2 * 60);
                    brickArray[i].setY(y - 60);
                    break;
                case 4:
                    brickArray[i].setX(x + 2 * 60);
                    brickArray[i].setY(y);
                    break;
                case 5:
                    brickArray[i].setX(x + 60);
                    brickArray[i].setY(y);
                    break;
            }

        }
    }
    public Boolean isHomeDestroy(){
       return brickArray[brickArray.length - 1].getHp() <= 0;
    }
    public void draw(Graphics g) {
        for (Brick brick : brickArray) {
            brick.draw(g);
        }
    }
}
