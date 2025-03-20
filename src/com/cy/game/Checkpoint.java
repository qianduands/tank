package com.cy.game;

public class Checkpoint {
    private String point;
    private int destroyNumber;
    private int mapStartX;
    private int mapStartY;
    private int disCol;
    private int row;
    private int numberInRow;

    public String getPoint() {
        return point;
    }

    public int getDestroyNumber() {
        return destroyNumber;
    }

    public int getMapStartX() {
        return mapStartX;
    }

    public int getMapStartY() {
        return mapStartY;
    }

    public int getDisCol() {
        return disCol;
    }

    public int getRow() {
        return row;
    }

    public int getNumberInRow() {
        return numberInRow;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public void setDestroyNumber(int destroyNumber) {
        this.destroyNumber = destroyNumber;
    }

    public void setMapStartX(int mapStartX) {
        this.mapStartX = mapStartX;
    }

    public void setMapStartY(int mapStartY) {
        this.mapStartY = mapStartY;
    }

    public void setDisCol(int disCol) {
        this.disCol = disCol;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setNumberInRow(int numberInRow) {
        this.numberInRow = numberInRow;
    }
}
