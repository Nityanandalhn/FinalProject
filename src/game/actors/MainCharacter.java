package game.actors;

import game.basics.Attacker;
import game.basics.Entity;
import game.basics.Movable;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
/**
 * This class handles all the parameters and methods that a user controlled entity
 * should have.
 */
public class MainCharacter extends Entity implements Attacker, Movable
{
    private static final int ATK = 12;
    private static final int DEF = 1;
    private static final int HP = 10;
    private static final double MVSPD = 2.5;
    private static final String PATH =
            "assets/Warrior/SpriteSheet/Warrior_Large.png";
    private static final double SCALE = 3;

    /* The arrays follow the pattern {x1,y1,x2,y2},
    forming a 2 point rectangle for the sprite. */

    private static final int[][] idleAnimationParameters = {
            {90, 50, 180, 215},
            {435, 50, 525, 215},
            {775, 50, 875, 215},
            {1120, 50, 1220, 215},
            {1470, 50, 1560, 215},
            {1815, 50, 1905, 215}
    };
    private static final int[][] runAnimationParameters = {
            {60, 280, 185, 435},
            {410, 280, 530, 435},
            {750, 280, 895, 435},
            {1090, 280, 1220, 435},
            {1440, 280, 1585, 435},
            {1790, 280, 1930, 435}
    };
    private static final int[][] jumpAnimationParameters = {
            {95, 1580, 195, 1755},
            {440, 1580, 540, 1755},
            {785, 1580, 885, 1755},
            {1125, 1580, 1230, 1755},
            {1470, 1580, 1575, 1755},
            {1815, 1580, 1920, 1755}
    };
    private static final int[][] attackAnimationParameters = {
            {420, 520, 530, 655},
            {750, 445, 885, 655},
            {1080, 470, 1230, 655},
            {1435, 495, 1575, 655},
            {1790, 495, 1935, 655},
            {75, 715, 210, 875},
            {410, 675, 650, 875},
            {760, 680, 1010, 875},
            {1120, 680, 1320, 875},
            {1480, 730, 1595, 875},
            {1725, 665, 2000, 875},
            {0, 885, 255, 1095},
            {355, 895, 595, 1095}
    };

    private boolean attacking;
    private boolean blocking;
    private boolean dodging;

    //private double maxJumpHeight;
    private boolean jumping;
    private boolean falling;

    /**
     * Constructor that will return a new user controlled entity at the specified
     * window position completely initialized with default stats.
     * @param x int window position x
     * @param y int window position y
     */
    public MainCharacter(int x, int y)
    {
        super(x, y, PATH, ATK, DEF, HP, MVSPD);

        attacking = false;
        jumping = false;
        blocking = false;
        dodging = false;
        direction = 1;

        setScale(SCALE);

        addPattern("idle", idleAnimationParameters);
        addPattern("run", runAnimationParameters);
        addPattern("jump", jumpAnimationParameters);
        addPattern("attack",attackAnimationParameters);

        setPattern("idle");

        setX1(idleAnimationParameters[0][0]);
        setY1(idleAnimationParameters[0][1]);
        setX2(idleAnimationParameters[0][2]);
        setY2(idleAnimationParameters[0][3]);

        this.setHeight(getY2()-getY1());
        this.setWidth(getX2()-getX1());
        this.setConstantHeight(this.getHeight());
    }

    /**
     * Method that handles how this entity should perform an attack animation.
     */
    @Override
    public void attack()
    {
        if(!attacking && !jumping) {
            attacking = true;
            frame = 0;
        }

        else if (frame + 1 >= attackAnimationParameters.length)
            attacking = false;
    }

    /**
     * Method that returns if this entity is attacking.
     * @return true if attacking / false otherwise
     */
    @Override
    public boolean isAttacking() {
        return attacking;
    }

    /**
     * Method that alters if this entity should continue attacking.
     * This entity will be barred from moving while attacking.
     * @param attacking true / false
     */
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    /**
     * Method that handles how this entity should perform a block animation.
     */
    @Override
    public void block()
    {
        //prevent movement + increase defense ++ set block animation
    }

    /**
     * Method that alters this entity's position within the game along the X axis.
     */
    @Override
    public void move()
    {
        if(!attacking)
            moveTo(getX() + MVSPD * direction, getY());
    }

    /**
     * Method that handles how this entity moves upwards along the y axis.
     * This entity's move speed greatly affects how high it can jump.
     */
    @Override
    public void jump()
    {
        if(!jumping)
        {
            jumping = true;
        }
        else
        {
            moveTo(getX(), getY() - (MVSPD + 1));
        }

        if(fallingSpeed > (MVSPD + 1) * 2)
            jumping = false;
    }

    /**
     * Method that handles how this entity should perform a dodge animation.
     */
    @Override
    public void dodge()
    {
        //move faster + avoid collisions ++ set dodge animation?
    }

    /**
     * Method that checks if this entity is jumping.
     * @return true / false
     */
    public boolean isJumping() {
        return jumping;
    }

    /**
     * Method that alters if this entity should continue jumping.
     * @param jumping true / false
     */
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
