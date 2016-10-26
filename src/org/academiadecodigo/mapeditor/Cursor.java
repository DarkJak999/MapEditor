package org.academiadecodigo.mapeditor;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 25/10/16.
 */
public class Cursor{

    private static final int CELL_SIZE = 40;
    private static final int PADDING = 10;
    private Rectangle rect;
    private Grid grid;

    public Cursor(int x, int y,Grid grid){
        this.grid = grid;
        this.rect = new Rectangle(PADDING+x,PADDING+y, CELL_SIZE, CELL_SIZE);
        rect.setColor(Color.RED);
        rect.fill();
    }

    public void moveCursor(Direction dir){

        switch (dir){
            case UP:
                moveUP();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;

        }
    }

    public void moveRight(){

        if(rect.getX() != grid.getWidth() - CELL_SIZE)
            rect.translate(CELL_SIZE,0);

    }

    public void moveLeft(){
        if(rect.getX() != 10)
            rect.translate(-CELL_SIZE,0);
    }

    public void moveUP(){
        if(rect.getY() != 10)
            rect.translate(0, -CELL_SIZE);

    }

    public void moveDown(){
        if(rect.getY() != grid.getHeigth() - CELL_SIZE)
            rect.translate(0,CELL_SIZE);
    }

    public int getX(){
        return rect.getX()-PADDING;
    }

    public int getY(){
        return rect.getY()-PADDING;
    }

    public void delete(){
        rect.delete();
    }




}
