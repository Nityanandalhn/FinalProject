package game.actors;

import game.basics.Entity;

public class Portal extends Entity {
    private static final int ATK = 0;
    private static final int DEF = 999;
    private static final int HP = 999;
    private static final double MVSPD = 0;
    private static final String PATH = "assets/Portal/GreenPortal.png";
    private static final double SCALE = 0.7;

    private static final int[][] idleAnimationParameters = {
            {12,13,40,57},
            {86,13,105,57},
            {150,13,175,57},
            {210,13,240,57},
            {274,13,300,57},
            {333,13,361,57},
            {405,13,421,57},
            {466,13,485,57}
    };

    public Portal(int x, int y) {
        this(x, y, PATH, ATK, DEF, HP, MVSPD);
    }

    public Portal(int x, int y,
                    String path, int atk, int def, int hp, double mvspd)
    {
        super(x, y, path, atk, def, hp, mvspd);

        direction = 1;
        setScale(SCALE);

        addPattern("idle", idleAnimationParameters);
        setPattern("idle");
        setSpriteChangeRate(10);

        setX1(idleAnimationParameters[0][0]);
        setY1(idleAnimationParameters[0][1]);
        setX2(idleAnimationParameters[0][2]);
        setY2(idleAnimationParameters[0][3]);

        this.setHeight(getY2()-getY1());
        this.setWidth(getX2()-getX1());
        this.setConstantHeight(this.getHeight());
    }
}
