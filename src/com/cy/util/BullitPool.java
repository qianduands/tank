package com.cy.util;

import com.cy.game.Bullit;
import java.util.ArrayList;
public class BullitPool {
    public static final int POOL_SIZE = 200;
    public static final int POOL_MAX_SIZE = 300;
    public static ArrayList<Bullit> bullitsList = new ArrayList<>();
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            bullitsList.add(new Bullit());
        }
    }
    public static Bullit getBullit(){
        if(bullitsList.isEmpty()){
            Bullit bullit = new Bullit();
            bullitsList.add(bullit);
        }
        return bullitsList.remove(0);
    }

    public static void putInBullit(Bullit bullit){
        bullitsList.add(bullit);
        System.out.println(bullitsList.size());
    }
}
