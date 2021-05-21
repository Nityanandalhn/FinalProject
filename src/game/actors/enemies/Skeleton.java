package game.actors.enemies;

import game.basics.Attacker;
import game.actors.Enemy;
import game.basics.Movable;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
public class Skeleton extends Enemy implements Attacker, Movable {
    private static final int ATK = 15;
    private static final int DEF = 10;
    private static final int HP = 10;
    private static final double MVSPD = 1.111;
    private static final String PATH = "assets/Skeleton/SpriteSheets/Skeleton_Large.png";
    private static final double SCALE = 3;

    private static final int[][] moveAnimationParameters = {
            {1185,225,1275,390},
            {1296,225,1385,390},
            {1406,225,1495,390},
            {1511,225,1610,390},
            {1621,225,1725,390},
            {1731,225,1839,390},
            {1841,225,1949,390},
            {1951,225,2059,390},
            {2061,225,2169,390},
            {2171,225,2270,390},
            {2286,225,2385,390},
            {2401,225,2490,390},
            {2511,225,2595,390}
    };

    public Skeleton(int x, int y) {
        this(x, y, PATH, ATK, DEF, HP, MVSPD);
    }

    public Skeleton(int x, int y,
                    String path, int atk, int def, int hp, double mvspd)
    {
        super(x, y, path, atk, def, hp, mvspd);

        direction = 1;
        setScale(SCALE);

        addPattern("move",moveAnimationParameters);
        setPattern("move");
        setSpriteChangeRate(10);

        setX1(moveAnimationParameters[0][0]);
        setY1(moveAnimationParameters[0][1]);
        setX2(moveAnimationParameters[0][2]);
        setY2(moveAnimationParameters[0][3]);

        this.setHeight(getY2()-getY1());
        this.setWidth(getX2()-getX1());
        this.setConstantHeight(this.getHeight());
    }

    @Override
    public void attack() {

    }

    @Override
    public boolean isAttacking() {
        return false;
    }

    @Override
    public void block() {

    }

    @Override
    public void move() {
        moveTo(getX() + MVSPD * direction, getY());
    }

    @Override
    public void jump() {

    }

    @Override
    public boolean isJumping() {
        return false;
    }

    @Override
    public void dodge() {

    }
}
