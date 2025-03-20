package com.cy.map;

import com.cy.game.Bullit;
import com.cy.util.BrickPool;
import com.cy.util.Util;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;

public class Brick {
    private int x, y;
    private int width, height;
    private static Image[] image = new Image[4];
    private int hp = 20;
    private Boolean isVisible = true;

    public final static int SOFT = 0;
    public final static int HARD = 1;
    public final static int COVER = 2;
    public final static int HOME = 3;

    private int type = SOFT;

    static {
        image[SOFT] = Toolkit.getDefaultToolkit().getImage("images/walls.gif");
        image[HARD] = Toolkit.getDefaultToolkit().getImage("images/fe.gif");
        image[COVER] = Toolkit.getDefaultToolkit().getImage("images/cao.gif");
        image[HOME] = Toolkit.getDefaultToolkit().getImage("images/base.gif");
    }

    public Brick() {
    }

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if(isVisible) g.drawImage(image[type], x, y, null);
    }

    public Boolean isCrash(Bullit bullit) {
        return Util.isBrickCrash(this, bullit);
    }
    public void giveBack(){
        BrickPool.putInBrick(this);
    }
    public void decreaseHp(Bullit bullit) {
        if (type != SOFT && type != HOME) return;
        if (hp == 0 || hp - bullit.getAtk() <= 0) {
            hp = 0;
            isVisible = false;
        } else hp -= bullit.getAtk();
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

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
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
