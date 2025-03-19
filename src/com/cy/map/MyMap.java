package com.cy.map;

import com.cy.util.BrickPool;
import com.cy.util.Util;

import java.awt.*;
import java.util.ArrayList;

public class MyMap {
    private int x,y;
    private int width,height;
    private static final int MAX_NUMBER = 20;
    private  ArrayList<Brick> brickArrayList =  new ArrayList<>();
   public MyMap(int x, int y, int width, int height) {
       this.x = x;
       this.y = y;
       this.width = width;
       this.height = height;
       initMap();
   }
   public void initMap(){
       for (int i = 0; i < MAX_NUMBER; i++) {
           int i1 = (int) (Math.random() * (width - x) + x);
           int i2 = (int) (Math.random() * (height - y) + y);
            if(Util.isOverlap(brickArrayList, i1, i2)){
                i--;
                continue;
            }
           Brick brick = BrickPool.getBrick();
           brick.setX(i1);
           brick.setY(i2);
           brickArrayList.add(brick);
       }
   }
   public void draw(Graphics g){
       brickArrayList.forEach(brick -> {
           if(brick.getVisible()) brick.draw(g);
       });
   }

    public ArrayList<Brick> getBrickArrayList() {
        return brickArrayList;
    }
}
