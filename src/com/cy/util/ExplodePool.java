package com.cy.util;

import com.cy.game.Explode;

import java.util.ArrayList;

public class ExplodePool {
    private static final int MAX_EXPLODE = 200;
    private static final ArrayList<Explode> exceptions = new ArrayList<>();
    static {
        for (int i = 0; i < MAX_EXPLODE; i++) {
            exceptions.add(new Explode());
        }
    }
    public static Explode getExplode(){
        if(exceptions.isEmpty()){
            exceptions.add(new Explode());
        }
        return exceptions.remove(0);
    }
    public static void putInExplode(Explode explode){
        explode.setIndex(0);
        exceptions.add(explode);
    }
}
