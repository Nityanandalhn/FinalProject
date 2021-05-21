package game.basics;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
public interface Movable {

    void move();
    void jump();
    boolean isJumping();
    void dodge();
}
