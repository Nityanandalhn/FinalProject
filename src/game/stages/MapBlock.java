package game.stages;

import game.basics.Entity;
import game.basics.Sprite;
/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
/**
 * This class handles all the information that a background object of any stage should
 * have.
 */
public class MapBlock extends Sprite
{
    private boolean solidGround;
    private String type;
    private int padding;
    private static int SCALE = 3;

    //String type exists in case I want to use it to simplify some things later on
    //it might never be used, in which case I'll remove it then.

    /**
     * Constructor that will return a map block object.
     * @param x int Position x within the game stage
     * @param y int Position y within the game stage
     * @param path String image file path
     * @param x1 int first x point within the image for the block rectangle
     * @param y1 int first y point within the image for the block rectangle
     * @param x2 int last x point within the image for the block rectangle
     * @param y2 int last y point within the image for the block rectangle
     * @param solidGround boolean that returns true if this block should prevent
     *                    entities from falling off the screen
     * @param type String used to keep tract of the block model, useful for managing
     *             which enemies should procedurally appear on which blocks
     */
    public MapBlock(int x, int y, String path, int x1, int y1, int x2, int y2,
                    boolean solidGround, String type)
    {
        this(x,y,path,x1,y1,x2,y2,solidGround,type,0);
    }

    /**
     * Constructor that will return a map block object.
     * @param x int Position x within the game stage
     * @param y int Position y within the game stage
     * @param path String image file path
     * @param x1 int first x point within the image for the block rectangle
     * @param y1 int first y point within the image for the block rectangle
     * @param x2 int last x point within the image for the block rectangle
     * @param y2 int last y point within the image for the block rectangle
     * @param solidGround boolean that returns true if this block should prevent
     *                    entities from falling off the screen
     * @param type String used to keep tract of the block model, useful for managing
     *                   which enemies should procedurally appear on which blocks
     * @param padding int that will modify the rectangle collision logic. Only
     *                useful if the block is set to solidGround = true;
     */
    public MapBlock(int x, int y, String path, int x1, int y1, int x2, int y2,
                    boolean solidGround, String type, int padding)
    {
        super(x, y, path);

        this.setX1(x1);
        this.setX2(x2);
        this.setY1(y1);
        this.setY2(y2);

        this.setHeight(y2-y1);
        this.setWidth(x2-x1);

        this.solidGround = solidGround;
        this.type = type;
        this.padding = padding;
        setScale(3);
    }

    /**
     * Method that modifies the block state to prevent or allow entities from
     * falling off through the block.
     * @param solidGround boolean true: prevent falling / false: allow falling
     */
    public void setSolidGround(boolean solidGround)
    {
        this.solidGround = solidGround;
    }

    /**
     * Method that will check if an entity is on top of this block.
     * @param e entity
     * @return true if the entity is on top, false if the entity is not
     */
    public boolean hasEntityOnTop(Entity e)
    {
        return      (e.getY() + (double) e.getConstantHeight() / e.getScale()
                        + e.getFallingSpeed() > this.getY() - padding)

                &&  (e.getY() + (double) e.getConstantHeight() / e.getScale()
                        - e.getFallingSpeed() - 0.01 < this.getY() - padding)

                &&  (e.getX() > this.getX() - (double) e.getWidth() / (e.getScale() * 2)
                        - padding * e.getScale() - e.getMvspd()

                &&   e.getX() < this.getX() + (double) this.getWidth() / this.getScale()
                        + (double) e.getWidth() / (e.getScale() * 2)
                        + padding * e.getScale() + e.getMvspd());
    }

    /**
     * Method that will check if an entity has reached the outer boundaries of this
     * block.
     * @param e entity
     * @return true if the entity is on a corner of this block, false otherwise
     */
    public boolean edgeReached(Entity e)
    {
        return      (e.getX() < - (double) e.getWidth() / (e.getScale() * 2)
                        - padding * e.getScale() + e.getMvspd() * 2 + this.getX()

                ||  e.getX() > this.getX() + (double) this.getWidth() / this.getScale()
                        + (double) e.getWidth() / (e.getScale() * 2)
                        + padding * e.getScale() - e.getMvspd() * 2);
    }

    /**
     * Method that will return true if a block should prevent entities from falling
     * through.
     * @return true: prevents falling through / false: allows falling through
     */
    public boolean isSolidGround()
    {
        return solidGround;
    }
}
