package org.academiadecodigo.mapeditor;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

/**
 * Created by codecadet on 26/10/16.
 */
public class KeyEvents implements KeyboardHandler, MouseHandler {

    MapEditor editor;
    Grid grid;
    boolean paintFlag;

    public KeyEvents(MapEditor editor) {
        this.editor = editor;
        this.grid = editor.getGrid();

        init();
    }

    public void init() {

        Keyboard k = new Keyboard(this);
        KeyboardEvent load = new KeyboardEvent();
        KeyboardEvent up = new KeyboardEvent();
        KeyboardEvent down = new KeyboardEvent();
        KeyboardEvent left = new KeyboardEvent();
        KeyboardEvent right = new KeyboardEvent();
        KeyboardEvent exit = new KeyboardEvent();
        KeyboardEvent save = new KeyboardEvent();
        KeyboardEvent reset = new KeyboardEvent();
        KeyboardEvent paint = new KeyboardEvent();

        load.setKey(KeyboardEvent.KEY_L);
        load.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        exit.setKey(KeyboardEvent.KEY_Q);
        exit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        save.setKey(KeyboardEvent.KEY_S);
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        reset.setKey(KeyboardEvent.KEY_R);
        reset.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        paint.setKey(KeyboardEvent.KEY_SPACE);
        paint.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        k.addEventListener(load);
        k.addEventListener(up);
        k.addEventListener(down);
        k.addEventListener(right);
        k.addEventListener(left);
        k.addEventListener(exit);
        k.addEventListener(save);
        k.addEventListener(reset);
        k.addEventListener(paint);

        Mouse m = new Mouse(this);
        m.addEventListener(MouseEventType.MOUSE_CLICKED);
        m.addEventListener(MouseEventType.MOUSE_MOVED);

        editor.init();
    }

    @Override
    public void keyPressed(KeyboardEvent k) {
        //does nothing
        /*
        SPACE -> Paints the map
        DIRECTIONAL KEYS -> move the cursor
        Q -> exits
        R -> resets
        L -> loads the file
        S -> saves to file
         */

        switch (k.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                //System.out.println("space");
                if (paintFlag)
                    paintFlag = false;
                else
                    paintFlag = true;
                break;

            case KeyboardEvent.KEY_UP:
                System.out.println("Up");
                paint(paintFlag);
                editor.getCursor().moveCursor(Direction.UP);
                break;

            case KeyboardEvent.KEY_DOWN:
                System.out.println("Down");
                paint(paintFlag);
                editor.getCursor().moveCursor(Direction.DOWN);
                break;

            case KeyboardEvent.KEY_LEFT:
                System.out.println("Left");
                paint(paintFlag);
                editor.getCursor().moveCursor(Direction.LEFT);
                break;

            case KeyboardEvent.KEY_RIGHT:
                System.out.println("Right");
                paint(paintFlag);
                editor.getCursor().moveCursor(Direction.RIGHT);
                break;

            case KeyboardEvent.KEY_Q:
                System.exit(0);
                break;

            case KeyboardEvent.KEY_S:
                editor.saveToFile();
                break;

            case KeyboardEvent.KEY_L:
                System.out.println("L");
                editor.saveCursor();
                grid.deleteGrid();
                editor.drawFromFile();
                editor.drawCursor();
                break;

            case KeyboardEvent.KEY_R:
                System.out.println("Pressed R");
                editor.saveCursor();
                grid.deleteGrid();
                grid.drawClearGrid();
                editor.setMatrix(grid.getClearMatrix());
                editor.drawCursor();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent k) {

    }

    public void paint(boolean paintFlag) {

        if (paintFlag) {
            editor.saveCursor();
            grid.paintCell(editor.getCursor().getX(), editor.getCursor().getY());
            editor.drawCursor();
        }
    }

    @Override
    public void mouseClicked(MouseEvent m) {

        if(bluePos(m)){
            System.out.println("Clicked blue");
            grid.setCurrentColor(Color.BLUE);
        }
        else if(blackPos(m)){
            System.out.println("Clicked black");
            grid.setCurrentColor(Color.BLACK);
        }

    }

    @Override
    public void mouseMoved(MouseEvent m) {
        //System.out.println(m.getX() + " " + m.getY());
    }

    public boolean bluePos(MouseEvent m){

        System.out.println(grid.getBlueY());
        if(m.getX() > grid.getBlueX() && m.getX() < grid.getBlueX() + 20
                && m.getY() > grid.getBlueY() && m.getY() < grid.getBlueY() + 20){
            return true;
        }

        return false;
    }

    public boolean blackPos(MouseEvent m){
        System.out.println(grid.getBlackX() + " " + grid.getBlackY());
        if(m.getX() > grid.getBlackX() && m.getX() < grid.getBlackX() + 20
                && m.getY() > grid.getBlackY() && m.getY() < grid.getBlackY() + 20){
            return true;
        }

        return false;
    }
}
