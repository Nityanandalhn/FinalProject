package display;

import game.menus.Icon;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;

import java.nio.file.Files;
import java.nio.file.Paths;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
public class MenuScene extends GeneralScene
{
    private final int MENU_POSITION_Y = GAME_HEIGHT / 2 - 100;
    private final int MENU_POSITION_X = GAME_WIDTH / 2 - 200;

    private final int START_GAME = 0;
    private final int SHOW_CREDITS = 1;
    private final int SHOW_SCORES = 2;
    private final int EXIT = 3;

    private Image swordCursor;
    private final Icon CURSOR;

    public MenuScene()
    {
        super();

        CURSOR = new Icon(MENU_POSITION_X, MENU_POSITION_Y,
                "assets/Icons/sword_ico_large.png", 50, 50);

        try {
            swordCursor = new Image(
                    Files.newInputStream(Paths.get(CURSOR.getImgFilePath())));
        }
        catch (Exception ignored){
            System.out.println("Cursor file not found.");
        }
    }

    private void showMenu()
    {
        Font menuFont = Font.font("Arial", FontWeight.BOLD, CURSOR.getHeight());

        gc.setFill( Color.WHITE );
        gc.setFont(menuFont);

        gc.fillText("Start", MENU_POSITION_X + CURSOR.getWidth() + 5,
                MENU_POSITION_Y + CURSOR.getHeight() * (START_GAME + 1));

        gc.fillText("Credits", MENU_POSITION_X + CURSOR.getWidth() + 5,
                MENU_POSITION_Y + CURSOR.getHeight() * (SHOW_CREDITS + 1));

        gc.fillText("Scores", MENU_POSITION_X + CURSOR.getWidth() + 5,
                MENU_POSITION_Y + CURSOR.getHeight() * (SHOW_SCORES + 1));

        gc.fillText("Exit", MENU_POSITION_X + CURSOR.getWidth() + 5,
                MENU_POSITION_Y + CURSOR.getHeight() * (EXIT + 1));

        gc.drawImage(swordCursor, CURSOR.getX(), CURSOR.getY());
    }

    @Override
    public void draw() {
        activeKeys.clear();

        CURSOR.moveTo(MENU_POSITION_X, MENU_POSITION_Y);

        new AnimationTimer()
        {
            private int option = 0;

            @Override
            public void handle(long currentNanoTime)
            {
                gc.setFill(Color.DARKGRAY);
                gc.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);

                showMenu();

                if ( activeKeys.contains(KeyCode.SPACE)
                    || activeKeys.contains(KeyCode.ENTER) )
                {
                    this.stop();

                    switch(option)
                    {
                        case START_GAME:
                            Main.setScene(Main.GAME_SCENE);
                            break;

                        case SHOW_CREDITS:
                            //not fully implemented
                        case SHOW_SCORES:
                            //not fully implemented
                            Main.setScene(Main.CREDITS_SCENE);
                            break;

                        case EXIT:
                            Main.exit();
                            break;
                    }
                }

                else if (activeKeys.contains(KeyCode.ESCAPE))
                {
                    this.stop();
                    Main.exit();
                }

                else if (activeKeys.contains(KeyCode.DOWN))
                {
                    activeKeys.remove(KeyCode.DOWN);

                    option = (option + 1) % (EXIT + 1);

                    CURSOR.setY(MENU_POSITION_Y + option * CURSOR.getHeight());
                }

                else if (activeKeys.contains(KeyCode.UP))
                {
                    activeKeys.remove(KeyCode.UP);

                    option = (option - 1) < 0 ? EXIT : (option - 1);

                    CURSOR.setY(MENU_POSITION_Y + option * CURSOR.getHeight());
                }
            }
        }.start();
    }
}
