package game.actors;

import game.basics.Entity;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
public abstract class Enemy extends Entity {
    public Enemy(int x, int y, String path, int atk, int def, int hp, double mvspd) {
        super(x, y, path, atk, def, hp, mvspd);
    }
}
