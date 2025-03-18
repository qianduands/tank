package com.cy.map;

import com.cy.game.Bullit;
import com.cy.util.Util;

import java.awt.*;

public class Brick {
    private int x,y;
    private int width,height;
    private static Image image;
    static {
        image = Toolkit.getDefaultToolkit().getImage("images/walls.gif");
    }
    public Brick(){
    }
    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g){
        g.drawImage(image,x,y,null);
    }
    public Boolean isCrash(Bullit bullit){
        return Util.isBrickCrash(this,bullit);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Brick{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
