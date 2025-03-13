package com.cy.game;

import com.cy.util.BullitPool;
import com.cy.util.Constnt;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tank {
    private int x, y;
    private int width, height;
    private int speed = 30;


    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public static final int STATUS_STAND = 0;
    public static final int STATUS_MOVE = 1;
    public static final int STATUS_DEAD = 2;

    private int dir;
    private List<Bullit> arrList = new ArrayList();

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
    private int atk;

    public void setEnemy(Boolean enemy) {
        isEnemy = enemy;
    }

    private Boolean isEnemy = false;
    public static Image[] tankIamges = new Image[4];
    public static Image[] enemyTankImages = new Image[4];
    public static String[] tankIamgesNames = {
            "p1tankU.gif",
            "p1tankR.gif",
            "p1tankD.gif",
            "p1tankL.gif",
    };

    static {
        String[] enemyImage = {
                "enemy1U.gif",
                "enemy1R.gif",
                "enemy1D.gif",
                "enemy1L.gif",
        };
        for (int i = 0; i < 4; i++) {
            tankIamges[i] = Toolkit.getDefaultToolkit().createImage("images/player1/" + tankIamgesNames[i]);
            enemyTankImages[i] = Toolkit.getDefaultToolkit().createImage("images/enemy/" + enemyImage[i]);
        }
    }

    public Tank(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static Tank createEnemy() {
        int x;
        int y = GameFrame.titleBarH + 50;
        int random = (int) (Math.random() * Constnt.GAME_WIDTH);
        System.out.println(random + ";" + GameFrame.WIDTH);
        if (random < 50 + GameFrame.borderLeft) x = 50 + GameFrame.borderLeft;
        else if (random > -50 + Constnt.GAME_WIDTH - GameFrame.borderRight)
            x = -50 + Constnt.GAME_WIDTH - GameFrame.borderRight;
        else x = random;
        Tank tank = new Tank(x, y, 50, 50);
        tank.setEnemy(true);
        tank.setStatus(STATUS_MOVE);
        return tank;
    }

    public void drawTank(Graphics g) {
        judgeStatus();
        drawBullits(g);
        if (isEnemy) {
            g.drawImage(enemyTankImages[DOWN], x, y, null);
            return;
        }
        g.drawImage(tankIamges[dir], x, y, null);
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
                if (y < GameFrame.titleBarH + height) y = GameFrame.titleBarH;
                else y -= speed;
                break;
            case RIGHT:
                if (x > Constnt.GAME_WIDTH - GameFrame.borderRight - width - speed)
                    x = Constnt.GAME_WIDTH - GameFrame.borderRight - width;
                else x += speed;
                break;
            case DOWN:
                if (y < Constnt.GAME_HEIGHT - height - speed) y += speed;
                else y = Constnt.GAME_HEIGHT - height;
                break;
            case LEFT:
                if (x <= width) x = GameFrame.borderLeft;
                else x -= speed;
                System.out.println(x + "," + y);
                break;
        }
    }

    public void fire() {
        int x = 0;
        int y = 0;
        switch (dir) {
            case UP:
                x = this.x + 20;
                y = this.y - (tankIamges[0].getHeight(null) >> 1) + 50 + (status == STATUS_MOVE ? -speed : 0);
                break;
            case RIGHT:
                x = this.x + (tankIamges[0].getHeight(null) >> 1) - 20 + (status == STATUS_MOVE ? +speed : 0);
                y = this.y + 20;
                break;
            case DOWN:
                x = this.x + 20;
                y = this.y + (tankIamges[0].getHeight(null) >> 1) - 20 + (status == STATUS_MOVE ? +speed : 0);
                break;
            case LEFT:
                x = this.x - (tankIamges[0].getWidth(null) >> 1) + 50 + (status == STATUS_MOVE ? -speed : 0);
                y = this.y + 20;
        }
//        Bullit bullit = new Bullit(x, y, dir, atk);
        Bullit bullit = BullitPool.getBullit();
        bullit.setX(x);
        bullit.setY(y);
        bullit.setDir(dir);
        bullit.setAtk(atk);
        arrList.add(bullit);

    }

    public void drawBullits(Graphics g) {
        arrList.forEach(item -> {
            item.draw(g);
        });
        for (int i = 0; i < arrList.size(); i++) {
            Bullit bullit = arrList.get(i);
            if (!bullit.getVisible()) {
                Bullit remove = arrList.remove(i);
                BullitPool.putInBullit(remove);
            }
        }
    }

    private void tankStand() {
    }

}
