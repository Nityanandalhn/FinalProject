package main;

import display.CreditsScene;
import display.GameScene;
import display.GeneralScene;
import display.MenuScene;
import javafx.application.Application;
import javafx.stage.Stage;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
public class Main extends Application {

    public static final int MAX_SCENES = 3;
    public static final int MENU_SCENE = 0;
    public static final int GAME_SCENE = 1;
    public static final int CREDITS_SCENE = 2;

    public static final GeneralScene[] scenes = new GeneralScene[MAX_SCENES];

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception
    {
        Main.stage = stage;

        scenes[MENU_SCENE] = new MenuScene();
        scenes[GAME_SCENE] = new GameScene();
        scenes[CREDITS_SCENE] = new CreditsScene();

        stage.setTitle("Test");
        setScene(MENU_SCENE);

        stage.show();
    }

    public static void setScene(int sceneNumber)
    {
        stage.setScene(scenes[sceneNumber]);
        scenes[sceneNumber].draw();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void exit()
    {
        stage.hide();
    }
}
