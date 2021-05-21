package display;

import game.scores.Score;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
public class CreditsScene extends GeneralScene
{
    public CreditsScene()
    {
        super();
    }

    private void showCredits()
    {
        Font creditsFont = Font.font("Arial", FontWeight.BOLD,50);

        gc.setFill( Color.WHITE );
        gc.setFont(creditsFont);

        int[] scores = Score.loadScores();

        gc.fillText("Highest score: " + scores[0],
                GAME_WIDTH / 2 - 300,GAME_HEIGHT / 2 - 200);
        gc.fillText("Current score: " + scores[1],
                GAME_WIDTH / 2 - 300,GAME_HEIGHT / 2 - 100);
    }

    @Override
    public void draw() {
        activeKeys.clear();

        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);

        showCredits();

        new AnimationTimer()
        {
            private int option = 0;

            @Override
            public void handle(long currentNanoTime)
            {

                if (activeKeys.contains(KeyCode.ESCAPE))
                {
                    this.stop();
                    Main.setScene(Main.MENU_SCENE);
                }
            }
        }.start();
    }
}
