package game.basics;

import java.util.HashMap;
import java.util.Map;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
public abstract class AnimatedSprite extends Sprite
{
    protected int direction;
    private int spriteChangeRate;

    private Map<String,int[][]> patterns = new HashMap<>();
    protected int frame;
    private int currentFrameChange;
    private int constantHeight;
    private String pattern;

    public AnimatedSprite(int x, int y, String path)
    {
        super(x, y, path);
    }

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    public void addPattern(String name, int[][] pattern)
    {
        patterns.put(name, pattern);
    }

    public int[][] getPatternSettings(String name)
    {
        return patterns.get(name);
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public void advanceFrame()
    {
        if(currentFrameChange++ >= spriteChangeRate)
        {
            frame = (frame + 1) % patterns.get(pattern).length;

            setX1(patterns.get(pattern)[frame][0]);
            setY1(patterns.get(pattern)[frame][1]);
            setX2(patterns.get(pattern)[frame][2]);
            setY2(patterns.get(pattern)[frame][3]);

            setWidth(getX2() - getX1());
            setHeight(getY2() - getY1());

            currentFrameChange = 0;
        }
    }

    public void setSpriteChangeRate(int spriteChangeRate) {
        this.spriteChangeRate = spriteChangeRate;
    }

    public int getFrame() {
        return frame;
    }

    public void setConstantHeight(int constantHeight) {
        this.constantHeight = constantHeight;
    }

    public int getConstantHeight() {
        return constantHeight;
    }
}
