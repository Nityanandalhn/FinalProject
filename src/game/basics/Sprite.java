package game.basics;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
/**
 * This class holds the required details for drawing different objects with multiple
 * configurations within a single file.
 */
public abstract class Sprite
{

    private final String imgFilePath;
    private double x;
    private double y;
    private double scale;

    private int x1, x2, y1, y2;

    private int height;
    private int width;

    /**
     * This returns a new Sprite
     * @param x double window position x
     * @param y double window position y
     * @param path String file path
     */
    public Sprite(double x, double y, String path)
    {
        this.x = x;
        this.y = y;
        imgFilePath = path;
    }

    /**
     * This method returns the x position where this should be drawn
     * @return double window position x
     */
    public double getX() {
        return x;
    }

    /**
     * This method sets the x position where this should be drawn
     * @param x double window position x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * This method returns the y position where this should be drawn
     * @return double window position y
     */
    public double getY() {
        return y;
    }

    /**
     * This method sets the y position where this should be drawn
     * @param y double window position y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * This method returns the image height
     * @return int height
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method sets the image height
     * @param height int height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * This method returns the image width
     * @return int width
     */
    public int getWidth() {
        return width;
    }

    /**
     * This method sets the image width
     * @param width int width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * This method returns the image file path
     * @return String image file path
     */
    public String getImgFilePath() {
        return imgFilePath;
    }

    /**
     * This method will check if an image is within bounds of another image
     * @param s Sprite image
     * @return true if the image is within bounds / false otherwise
     */
    public boolean collidesWith(Sprite s)
    {
        return      this.x > s.getX() - (double) s.getWidth() / (s.getScale() * 2)
                &&  this.x < s.getX() + (double) s.getWidth() / (s.getScale() * 2)
                &&  this.y > s.getY() - (double) s.getHeight() / (s.getScale() * 2)
                &&  this.y < s.getY() + (double) s.getHeight() / (s.getScale() * 2);
    }

    /**
     * This method changes the window position where the image should be drawn
     * @param x double window position x
     * @param y double window position y
     */
    public void moveTo(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * This method returns the first point of the rectangle that should be drawn
     * from within a set of specific coordinates within the image file (in pixels)
     * @return int x first point
     */
    public int getX1() {
        return x1;
    }

    /**
     * This method sets the first point of the rectangle that should be drawn
     * from within a set of specific coordinates within the image file (in pixels)
     * @param x1 int x first point
     */
    public void setX1(int x1) {
        this.x1 = x1;
    }

    /**
     * This method returns the last point of the rectangle that should be drawn
     * from within a set of specific coordinates within the image file (in pixels)
     * @return int x last point
     */
    public int getX2() {
        return x2;
    }

    /**
     * This method sets the last point of the rectangle that should be drawn
     * from within a set of specific coordinates within the image file (in pixels)
     * @param x2 int x last point
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     * This method returns the first point of the rectangle that should be drawn
     * from within a set of specific coordinates within the image file (in pixels)
     * @return int y first point
     */
    public int getY1() {
        return y1;
    }

    /**
     * This method sets the first point of the rectangle that should be drawn
     * from within a set of specific coordinates within the image file (in pixels)
     * @param y1 int y first point
     */
    public void setY1(int y1) {
        this.y1 = y1;
    }

    /**
     * This method returns the last point of the rectangle that should be drawn
     * from within a set of specific coordinates within the image file (in pixels)
     * @return int y last point
     */
    public int getY2() {
        return y2;
    }

    /**
     * This method sets the last point of the rectangle that should be drawn
     * from within a set of specific coordinates within the image file (in pixels)
     * @param y2 int y last point
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }

    /**
     * This method returns the scale applied to the image (in case of too small
     * or too large image source files)
     * @return double scale
     */
    public double getScale() {
        return scale;
    }

    /**
     * This method sets the scale that should be applied to the image (in case of too
     * small or too large image source files)
     * @param scale double scale
     */
    public void setScale(double scale) {
        this.scale = scale;
    }
}
