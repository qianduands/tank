package com.cy.tank;

import com.cy.game.Bullit;
import com.cy.game.Explode;
import com.cy.game.GameFrame;
import com.cy.map.Brick;
import com.cy.util.BullitPool;
import com.cy.util.Constnt;
import com.cy.util.ExplodePool;
import com.cy.util.Util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tank {
    private int x, y;
    private int hp = 100;
    private int width, height;
    private int speed = 20;
    private Color color = Color.red;
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public static final int STATUS_STAND = 0;
    public static final int STATUS_MOVE = 1;
    public static final int STATUS_DEAD = 2;
    private Blood blood = new Blood();
    private int dir;
    private int status;
    private int atk = 10;
    private List<Bullit> arrList = new ArrayList();
    private List<Explode> explodesList = new ArrayList();

    public List<Bullit> getBullitArrList() {
        return arrList;
    }

    private Explode explodes;


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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static Enemy createEnemy() {
        int x;
        int y = GameFrame.titleBarH + 50;
        int random = (int) (Math.random() * Constnt.GAME_WIDTH);
        if (random < 50 + GameFrame.borderLeft) x = 50 + GameFrame.borderLeft;
        else if (random > -50 + Constnt.GAME_WIDTH - GameFrame.borderRight)
            x = -50 + Constnt.GAME_WIDTH - GameFrame.borderRight;
        else x = random;
        Enemy enemy = new Enemy(x, y, 50, 50);
        enemy.setEnemy(true);
        enemy.setStatus(STATUS_MOVE);
        enemy.setDir(DOWN);
        enemy.setSpeed(15);
        System.out.println("本机：" + tankIamges[0].getWidth(null) + ",敌机" + enemyTankImages[0].getWidth(null));
        return enemy;
    }

    ;

    public void drawTank(Graphics g,ArrayList<Brick> brickArrayList) {
        judgeStatus(brickArrayList);
        drawBullits(g);
        if (isEnemy) drawEnemy(g);
        else drawHero(g);
        blood.drawBlood(g);
    }

    public void drawHero(Graphics g) {
        g.drawImage(tankIamges[dir], x, y, null);
    }

    public void drawEnemy(Graphics g) {
        g.drawImage(enemyTankImages[DOWN], x, y, null);
    }

    private void judgeStatus(ArrayList<Brick> brickArrayList) {
        switch (status) {
            case STATUS_STAND:
                tankStand();
                break;
            case STATUS_MOVE:
                tankMove(brickArrayList);
                break;
            case STATUS_DEAD:
                tankDead();
        }
    }

    private void tankDead() {

    }

    private void tankMove(ArrayList<Brick> brickArrayList) {
        if(isCollision(brickArrayList)) return;
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
        blood.setX(x);
        blood.setY(y - 10);
    }
    private Boolean isCollision(ArrayList<Brick> brickArrayList){
        int x1 = x;
        int y1 = y;
        switch (dir) {
            case UP:
                y1 -= speed;
                break;
            case RIGHT:
                 x1 += speed;
                break;
            case DOWN:
                 y1 += speed;
                break;
            case LEFT:
                x1 -= speed;
                break;
        }
        return Util.isTankAndBrickCrash(brickArrayList, x1, y1);
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
        bullit.setVisible(true);
        bullit.setColor(color);
        arrList.add(bullit);

    }

    public void returnBullits() {
        arrList.forEach(item -> {
            BullitPool.putInBullit(item);
        });
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
                i--;
            }
        }
    }

    public void addExplode(Tank tank, Bullit bullit) {

        if (Util.isCrash(this, bullit)) {
//            tank.explodes = (ExplodePool.getExplode(this));
            tank.explodesList.add(ExplodePool.getExplode(this));
            decrease(bullit);
            bullit.setVisible(false);
        }
    }

    public void addExplode(ArrayList<Brick> brickArrayList, Bullit bullit) {
        brickArrayList.forEach(item->{
            if (Util.isBrickCrash(item, bullit)) {
                item.decreaseHp(bullit);
                explodesList.add(ExplodePool.getExplode(item));
                bullit.setVisible(false);
            }
        });
    }

    public void decrease(Bullit bullit) {
        if (hp == 0 || hp - bullit.getAtk() <= 0) hp = 0;
        else hp -= bullit.getAtk();
    }

    public Boolean isDie() {
        return hp <= 0;
    }

    class Blood {
        private int x, y;
        private final int MAX_BlOOD = 100;
        private final int width = 50;
        private final int height = 5;

        public Blood() {

        }

        public Blood(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void drawBlood(Graphics g) {
            g.setColor(Color.white);
            g.drawRect(x, y, width, height);
            g.setColor(Color.green);
            g.fillRect(x, y, width, height);
            g.setColor(Color.red);
            g.fillRect(x, y, (int) ((double) hp / MAX_BlOOD * width), height);
            g.setColor(Color.white);
            System.out.println("血量剩余：" + hp / MAX_BlOOD);
        }
    }

    public void setExplodes(Explode explodes) {
        this.explodes = explodes;
    }

    public List<Explode> getExplodesList() {
        return explodesList;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getDir() {
        return dir;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setEnemy(Boolean enemy) {
        isEnemy = enemy;
    }

    private void tankStand() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Tank{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
