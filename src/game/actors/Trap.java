package game.actors;

import game.basics.Attacker;
import game.basics.Entity;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
public class Trap extends Entity implements Attacker {
    public Trap(int x, int y, String path, int atk, int def, int hp) {
        super(x, y, path, atk, def, hp, 0);
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
}
