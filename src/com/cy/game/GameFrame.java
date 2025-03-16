package com.cy.game;

import com.cy.tank.Enemy;
import com.cy.tank.Hero;
import com.cy.tank.Tank;
import com.cy.util.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends Frame implements Runnable {
    public int status;
    public int menusIndex;
    public Tank tank;
    public static int titleBarH;
    public static int borderRight;
    public static int borderLeft;
    private BufferedImage bufferedImage = new BufferedImage(Constnt.GAME_WIDTH,Constnt.GAME_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);
    private ArrayList<Tank> tankArrayList = new ArrayList<>();
    public GameFrame() {
        initFrame(Constnt.GAME_TITLE);
        initEvenMonitor();
        this.tank = new Hero(Constnt.GAME_WIDTH >> 1, Constnt.GAME_HEIGHT - 50, 50, 50);
        new Thread(this).start();
        new Thread(){
            @Override
            public void run() {
               while (true){
                   System.out.println("坦克数量："+tankArrayList.size());
                    if(tankArrayList.size() < Constnt.ENEMY_MAX_NUMBER) {
                        Enemy tank1 = Tank.createEnemy();
                        tankArrayList.add(tank1);
                    }

                   try {
                       Thread.sleep(5000);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
            }
        }.start();
    }

    public void initFrame(String title) {
        setTitle(title);
        setVisible(true);
        setSize(Constnt.GAME_WIDTH, Constnt.GAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
         titleBarH = getInsets().top;
         borderRight = getInsets().right;
         borderLeft = getInsets().left;
    }

    public void initEvenMonitor() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        tank.setStatus(Tank.STATUS_MOVE);
                        tank.setDir(Tank.UP);
                        upPress();
                        break;
                    case KeyEvent.VK_DOWN:
                        tank.setStatus(Tank.STATUS_MOVE);
                        tank.setDir(Tank.DOWN);
                        downPress();
                        System.out.println("down");
                        break;
                    case KeyEvent.VK_LEFT:
                        tank.setStatus(Tank.STATUS_MOVE);
                        tank.setDir(Tank.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        tank.setStatus(Tank.STATUS_MOVE);
                        tank.setDir(Tank.RIGHT);
                        break;
                    case KeyEvent.VK_ENTER:
                        gameRun();
                        break;
                    case KeyEvent.VK_SPACE:
                        tank.fire();
                        break;
                }
            }


            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() != KeyEvent.VK_SPACE)  tank.setStatus(Tank.STATUS_STAND);

            }
        });
    }

    private void gameRun() {
        if (menusIndex == 0) status = Constnt.GAME_RUN;
    }

    private void downPress() {
        //Constnt.MENUS
        menusIndex = (menusIndex + 1) % Constnt.MENUS.length;
    }
    private void addExplodeByCrash(){
        //检测英雄机碰到敌人子弹
        tankArrayList.forEach(item->{
            List<Bullit> arrList = item.getBullitArrList();
            for (int i = 0; i < arrList.size(); i++) {
                tank.addExplode(tank,arrList.get(i));
            }
        });
        //检测敌机碰到英雄机子弹
        List<Bullit> bullitArrList = tank.getBullitArrList();
        bullitArrList.forEach(item->{
            for (int i = 0; i < tankArrayList.size(); i++) {
                Tank tank1 = tankArrayList.get(i);
                tank1.addExplode(tank1,item);
            }
        });
    }
    private void drawExplode(Graphics g){
        tankArrayList.forEach(item->{
            Explode explodes = item.getExplodes();
            if(explodes != null) {
                if(explodes.getIndex() <= 7) explodes.drawBom(g,item);
                else{
                    ExplodePool.putInExplode(explodes);
                    item.setExplodes(null);
                }
            }
        });
        Explode explodes = tank.getExplodes();
        if(explodes != null) {
            if(explodes.getIndex() <= 7) explodes.drawBom(g,tank);
            else {
                ExplodePool.putInExplode(explodes);
                tank.setExplodes(null);
            }
        }
    }
    private void upPress() {
        menusIndex = (Constnt.MENUS.length + menusIndex - 1) % Constnt.MENUS.length;
    }

    @Override
    public void update(Graphics g1) {
        Graphics g = bufferedImage.getGraphics();
        super.update(g);
        switch (this.status) {
            case 0:
                drawMenus(g);
                break;
            case 1:
                drawHelp(g);
                break;
            case 2:
                drawAbout(g);
                break;
            case 3:
                drawRun(g);
                break;
            case 4:
                drawOver(g);
                break;
        }
        g1.drawImage(bufferedImage,0,0,null);
    }

    private void drawOver(Graphics g) {
    }

    private void drawRun(Graphics g) {
//        g.setColor(Color.red);
//        g.fillRect(0, 0, Constnt.GAME_WIDTH, Constnt.GAME_HEIGHT);
        tank.drawTank(g);
        drawEnemyTanks(g);
        addExplodeByCrash();
        drawExplode(g);
    }
    private void drawEnemyTanks(Graphics g){
        tankArrayList.forEach(item->{
            item.drawTank(g);
        });
    }
    private void drawAbout(Graphics g) {
    }

    private void drawHelp(Graphics g) {

    }

    public void drawMenus(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Constnt.GAME_WIDTH, Constnt.GAME_HEIGHT);
        g.setColor(Color.white);
        final int MARGIN_TOP = Constnt.GAME_HEIGHT / 2 - 100;
        final int MARGIN_LEFT = Constnt.GAME_WIDTH / 2 - 100;
        final int DISTANCE = 30;
        for (int i = 0; i < Constnt.MENUS.length; i++) {
            if (menusIndex == i) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            g.drawString(Constnt.MENUS[i], MARGIN_LEFT, MARGIN_TOP + i * DISTANCE);
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

