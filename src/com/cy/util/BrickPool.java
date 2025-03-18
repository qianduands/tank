package com.cy.util;

import com.cy.map.Brick;

import java.util.ArrayList;

public class BrickPool {
    public static final int POOL_SIZE = 100;
    public static final int POOL_MAX_SIZE = 300;
    public static ArrayList<Brick> BrickList = new ArrayList<>();
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            BrickList.add(new Brick());
        }
    }
    public static Brick getBrick(){
        if(BrickList.isEmpty()){
            Brick brick = new Brick();
            BrickList.add(brick);
        }
        return BrickList.remove(0);
    }

    public static void putInBrick(Brick brick){
        BrickList.add(brick);
    }
}
