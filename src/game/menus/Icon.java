package game.menus;

import game.basics.Sprite;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
/**
 * This class is used to instantiate basic images as a single image Sprite for drawing
 */
public class Icon extends Sprite {

    /**
     * This will return a new image object
     * @param x int window position x
     * @param y int window position y
     * @param path String image file path
     * @param height int image height
     * @param width int image width
     */
    public Icon(int x, int y, String path, int height, int width) {
        super(x, y, path);

        this.setHeight(height);
        this.setWidth(width);
    }
}
