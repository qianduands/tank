package com.cy.util;

import com.cy.game.Bullit;
import com.cy.game.Explode;
import com.cy.map.Brick;
import com.cy.tank.Tank;

import java.util.ArrayList;

public class ExplodePool {
    private static final int MAX_EXPLODE = 200;
    private static final ArrayList<Explode> exceptions = new ArrayList<>();
    static {
        for (int i = 0; i < MAX_EXPLODE; i++) {
            exceptions.add(new Explode());
        }
    }
    public static Explode getExplode(Tank tank){
        if(exceptions.isEmpty()){
            exceptions.add(new Explode());
        }
        Explode remove = exceptions.remove(0);
        remove.setIndex(0);
        remove.setX(tank.getX());
        remove.setY(tank.getY());
        return remove;
    }
    public static Explode getExplode(Brick brick){
        if(exceptions.isEmpty()){
            exceptions.add(new Explode());
        }
        Explode remove = exceptions.remove(0);
        remove.setIndex(0);
        remove.setX(brick.getX());
        remove.setY(brick.getY());
        return remove;
    }
    public static void putInExplode(Explode explode){
        exceptions.add(explode);
    }
}
