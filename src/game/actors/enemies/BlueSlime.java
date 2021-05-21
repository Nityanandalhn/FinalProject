package game.actors.enemies;

import game.basics.Attacker;
import game.actors.Enemy;
import game.basics.Movable;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
public class BlueSlime extends Enemy implements Attacker, Movable {
    private static final int ATK = 5;
    private static final int DEF = 5;
    private static final int HP = 3;
    private static final double MVSPD = 2;
    private static final String PATH = "assets/Slime/Blue";

    public BlueSlime(int x, int y) {
        this(x, y, PATH, ATK, DEF, HP, MVSPD);
    }

    public BlueSlime(int x, int y,
                     String path, int atk, int def, int hp, double mvspd)
    {
        super(x, y, path, atk, def, hp, mvspd);
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
