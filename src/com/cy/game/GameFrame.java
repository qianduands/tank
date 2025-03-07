package com.cy.game;

import com.cy.util.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame implements Runnable {
    public int status;
    public int menusIndex;
    public Tank tank;

    public GameFrame() {
        initFrame(Constnt.GAME_TITLE);
        initEvenMonitor();
        this.tank = new Tank(Constnt.GAME_WIDTH >> 1, Constnt.GAME_HEIGHT - 20, 20, 20);
        new Thread(this).start();
    }

    public void initFrame(String title) {
        setTitle(title);
        setVisible(true);
        setSize(Constnt.GAME_WIDTH, Constnt.GAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
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
                }
            }


            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                tank.setStatus(Tank.STATUS_STAND);
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

    private void upPress() {
        menusIndex = (Constnt.MENUS.length + menusIndex - 1) % Constnt.MENUS.length;
    }

    @Override
    public void update(Graphics g) {
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
    }

    private void drawOver(Graphics g) {
    }

    private void drawRun(Graphics g) {
//        g.setColor(Color.red);
//        g.fillRect(0, 0, Constnt.GAME_WIDTH, Constnt.GAME_HEIGHT);
        tank.drawTank(g);
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

