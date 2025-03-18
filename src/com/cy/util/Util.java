package com.cy.util;

import com.cy.game.Bullit;
import com.cy.map.Brick;
import com.cy.tank.Tank;

import java.util.List;

public class Util {
    public static Boolean isCrash(Tank tank, Bullit bullit) {
//        if(tank.getX() - bullit.getX() < 50 && tank.getX() - bullit.getX() > 0  && tank.getY() - bullit.getY() < 50 && tank.getY() - bullit.getY() >0) {
        //按照圆心计算
        int tankRadioX = tank.getX() + 25;
        int tankRadioY = tank.getY() + 25;
        int bullitRadioX = bullit.getX() + 5;
        int bullitRadioY = bullit.getY() + 5;
        if (Math.abs(tankRadioX - bullitRadioX) < 30 && Math.abs(tankRadioY - bullitRadioY) < 30 && bullit.getVisible()) {
            System.out.println("jinlai");
            return true;
        }
        return false;
    }
    public static Boolean isOverlap(List<Brick> brickList,int x,int y){
        int radioX = x + 30;
        int radioY = y + 30;
        for (Brick brick : brickList) {
            int brickRadioX = brick.getX() + 30;
            int brickRadioY = brick.getY() + 30;
            if (Math.abs(brickRadioX - radioX) < 60 && Math.abs(brickRadioY - radioY) < 60) {
                return true;
            }
        }
        return false;
    }
    public static Boolean isBrickCrash(Brick brick, Bullit bullit) {
        //按照圆心计算
        int brickRadioX = brick.getX() + 30;
        int brickRadioY = brick.getY() + 30;
        int bullitRadioX = bullit.getX() + 5;
        int bullitRadioY = bullit.getY() + 5;
        if (Math.abs(brickRadioX - bullitRadioX) < 35 && Math.abs(brickRadioY - bullitRadioY) < 35 && bullit.getVisible()) {
            return true;
        }
        return false;
    }
}
