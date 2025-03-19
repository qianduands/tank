package com.cy.map;

import com.cy.game.Bullit;
import com.cy.util.Util;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;

public class Brick {
    private int x,y;
    private int width,height;
    private static Image image;
    private int hp = 20;
    private Boolean isVisible = true;
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
    public void decreaseHp(Bullit bullit){
        if(hp == 0 || hp - bullit.getAtk() <= 0) {
            hp = 0;
            isVisible = false;
        }
        else hp -= bullit.getAtk();
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Boolean getVisible() {
        return isVisible;
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
