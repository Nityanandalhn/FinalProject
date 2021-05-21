package display;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashSet;
import java.util.Set;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
public abstract class GeneralScene extends Scene
{
    public static final int GAME_WIDTH = 1800;
    public static final int GAME_HEIGHT = 1020;

    private StackPane root;
    protected GraphicsContext gc;
    protected Set<KeyCode> activeKeys;
    protected Set<KeyCode> releasedKeys;
    protected MediaPlayer mediaPlayer;
    protected Media sound;

    public GeneralScene()
    {
        super(new StackPane(), GAME_WIDTH, GAME_HEIGHT);

        root = new StackPane();
        this.setRoot(root);

        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        activeKeys = new HashSet<>();
        releasedKeys = new HashSet<>();

        this.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                activeKeys.add(keyEvent.getCode());
            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                activeKeys.remove(keyEvent.getCode());
                releasedKeys.add(keyEvent.getCode());
            }
        });
    }

    public abstract void draw();
}
