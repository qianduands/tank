package com.cy.util;

import com.cy.game.Bullit;
import com.cy.tank.Tank;

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
}
