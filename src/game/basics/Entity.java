package game.basics;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
/**
 * This class handles all the parameters an active entity should have.
 */
public abstract class Entity extends AnimatedSprite
{
    protected int atk, def, hp;
    protected double mvspd;

    protected double fallingSpeed;

    /**
     * This will return a new entity with the following parameters
     * @param x int window position x
     * @param y int window position y
     * @param path String entity image file path
     * @param atk int attack points value
     * @param def int defense points value
     * @param hp int health points value
     * @param mvspd double move speed (affects jump height)
     */
    public Entity(int x, int y, String path, int atk, int def, int hp, double mvspd)
    {
        super(x,y,path);

        this.atk = atk;
        this.def = def;
        this.hp = hp;
        this.mvspd = mvspd;
        this.fallingSpeed = 0;
    }

    /**
     * Increases the health points of this entity
     * @param amount int amount
     */
    public void recoverHp(int amount)
    {
        hp += amount;
    }

    /**
     * Lowes the health points of this entity based on an attack value.
     * Formula: enemy attack - this entity's def (Minimum value: 1)
     * @param enemyAttack int attack value
     */
    public void reduceHp(int enemyAttack)
    {
        hp -= Math.max(1, (enemyAttack - def));
    }

    /**
     * Returns this entity's attack value
     * @return int attack points
     */
    public int getAtk() {
        return atk;
    }

    /**
     * Modifies this entity's attack value
     * @param atk int attack points
     */
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * Returns this entity's defense value
     * @return int defense points
     */
    public int getDef() {
        return def;
    }

    /**
     * Modifies this entity's defense value
     * @param def int defense points
     */
    public void setDef(int def) {
        this.def = def;
    }

    /**
     * Returns this entity's current health value. An entity should be remove should
     * this value reach 0.
     * @return int health points
     */
    public int getHp() {
        return hp;
    }

    /**
     * Modifies this entity's health value. An entity should be remove should
     * this value reach 0.
     * @param hp int health points
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Returns this entity's current move speed. This affects how far will this entity
     * reach with a single step on a certain direction.
     * @return double move speed
     */
    public double getMvspd() {
        return mvspd;
    }

    /**
     * Modifies this entity's move speed. This affects how far will this entity
     * reach with a single step on a certain direction.
     * @param mvspd double move speed
     */
    public void setMvspd(int mvspd) {
        this.mvspd = mvspd;
    }

    /**
     * Returns this entity's falling speed. The falling speed will make an entity
     * constantly move towards the lowest point on the stage on a straight line.
     * @return double falling speed
     */
    public double getFallingSpeed() {
        return fallingSpeed;
    }

    /**
     * Modifies this entity's falling speed. The falling speed will make an entity
     * constantly move towards the lowest point on the stage on a straight line.
     * @param fallingSpeed double falling speed
     */
    public void setFallingSpeed(double fallingSpeed) {
        this.fallingSpeed = fallingSpeed;
    }
}
