package display;

import game.GameLogic;
import game.basics.Entity;
import game.stages.MapBlock;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import main.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
public class GameScene extends GeneralScene
{
    private List<Image> tileSets;
    private Map<String,Image> entities;
    private Image background;
    private final GameLogic GAME;
    private double offSetX;
    private double offSetY;

    public GameScene()
    {
        super();

        GAME = new GameLogic();
    }

    private void InitializeImages() {
        tileSets = new ArrayList<>();
        entities = new HashMap<>();

        for (int stage = 0; stage < GAME.getStages().size(); stage++)
        {
            try
            {
                background = new Image(Files.newInputStream(
                        Paths.get(GAME.getBackgroundImageFilePath())));

                tileSets.add(new Image(Files.newInputStream(
                        Paths.get(GAME.getStages().get(stage).getBlocksTilesetPath()
                        ))));

                for(Entity e : GAME.getStages().get(stage).getEntityTypes())
                {
                    entities.put(e.getClass().toString(),
                            new Image(Files.newInputStream(Paths.get(
                                    e.getImgFilePath()))));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw()
    {
        activeKeys.clear();

        sound = new Media(
                new File(GAME.getBackgroundSoundFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        InitializeImages();

        new AnimationTimer()
        {
            double lastInputTime;

            @Override
            public void handle(long currentNanoTime)
            {
                gc.setFill(Color.DARKGRAY);
                gc.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
                gc.drawImage(background,0,0,GAME_WIDTH,GAME_HEIGHT);

                GAME.handleGameLogic();

                if ((currentNanoTime - lastInputTime) / 1000000000 > 0.1)
                {
                    GAME.handleUserEvents("IDLE");
                }

                if (activeKeys.contains(KeyCode.RIGHT))
                {
                    GAME.handleUserEvents("MV_RIGHT");
                }

                if(activeKeys.contains(KeyCode.LEFT))
                {
                    GAME.handleUserEvents("MV_LEFT");
                }

                if(activeKeys.contains(KeyCode.CONTROL))
                {
                    GAME.handleUserEvents("ATTACK");
                }
                else if (releasedKeys.contains(KeyCode.CONTROL))
                {
                    GAME.handleUserEvents("ATTACK_CANCEL");
                }

                if(activeKeys.contains(KeyCode.SPACE))
                {
                    GAME.handleUserEvents("MV_JUMP");
                }

                else if (releasedKeys.contains(KeyCode.SPACE))
                {
                    GAME.handleUserEvents("MV_JUMP_CANCEL");
                    releasedKeys.remove(KeyCode.SPACE);
                }

                if (activeKeys.size() > 0)
                    lastInputTime = currentNanoTime;

                offSetX = GAME.getStages().get(GAME.getCurrentStage()).getEntities()
                        .get(GAME.MAIN_CHARACTER).getX() - GAME_WIDTH / 2;

                offSetY = GAME.getStages().get(GAME.getCurrentStage()).getEntities()
                        .get(GAME.MAIN_CHARACTER).getY() - GAME_HEIGHT / 2;

                for(Entity e :
                        GAME.getStages().get(GAME.getCurrentStage()).getEntities())
                {
                    gc.drawImage(
                            entities.get(e.getClass().toString()),
                            e.getX1(), e.getY1(),
                            e.getWidth(), e.getHeight(),
                            e.getX() - e.getWidth() / (
                                    e.getScale() * e.getDirection() * 2) - offSetX,
                            e.getY() - offSetY,
                            e.getWidth() * e.getDirection() / e.getScale(),
                            e.getHeight() / e.getScale()
                    );
                }

                for(MapBlock mb :
                        GAME.getStages().get(GAME.getCurrentStage()).getBlocks())
                {
                    gc.drawImage(
                            tileSets.get(0), mb.getX1(), mb.getY1(), mb.getWidth(),
                            mb.getHeight(), mb.getX() - offSetX,
                            mb.getY() - offSetY,
                            mb.getWidth() / mb.getScale(),
                            mb.getHeight() / mb.getScale());
                }

                if(!GAME.isInProgress() || activeKeys.contains(KeyCode.ESCAPE))
                {
                    this.stop();
                    GAME.reset();
                    mediaPlayer.stop();
                    Main.setScene(Main.MENU_SCENE);
                }

                if(GAME.getClearCondition())
                {
                    this.stop();
                    GAME.reset();
                    mediaPlayer.stop();
                    Main.setScene(Main.CREDITS_SCENE);
                }
            }
        }.start();
    }
}
