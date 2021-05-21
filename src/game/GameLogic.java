package game;

import game.actors.Enemy;
import game.actors.MainCharacter;
import game.actors.Portal;
import game.actors.enemies.Skeleton;
import game.basics.Attacker;
import game.basics.Entity;
import game.basics.Movable;
import game.scores.Score;
import game.scores.Shop;
import game.stages.Map;
import game.stages.MapBlock;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
/**
 * This class handles the game state and is in charge of initializing the different
 * stages, entities and maps. There are two main methods to alter the game state,
 * one that should always run in the background which handles the system behaviour
 * and another that should be called conditionally when a user performs an action
 * to parse said action and alter the state accordingly.
 */
public class GameLogic
{
    private final double GRAVITY = 0.04;
    //MC should always be the first entity added to each stage.
    public final int MAIN_CHARACTER = 0;

    private final int DIRECTION_LEFT = -1;
    private final int DIRECTION_RIGHT = 1;

    private int score;
    private int currentStage;

    private final List<Map> stages;
    private final Shop shop;

    private boolean gameInProgress;
    private boolean clearCondition;
    private String backgroundImageFilePath;
    private String backgroundSoundFilePath;

    private Dictionary<String, Boolean> triggers;

    /**
     * This will return a new game initialized at stage 0
     */
    public GameLogic()
    {
        score = 0;
        currentStage = 0;
        shop = new Shop();
        stages = new ArrayList<>();
        gameInProgress = true;
        clearCondition = false;

        initializeStageOne();
    }

    /**
     * Method that sets the stage 0 values to its initial parameters.
     */
    public void initializeStageOne()
    {
        Map stage = new Map();
        triggers = new Hashtable<>();
        backgroundImageFilePath = "assets/Backgrounds/fantasy-forest.jpg";
        backgroundSoundFilePath = "assets/Sounds/zero-project - 02 - Still breathing.mp3";

        stages.add(stage);

        stage.setBlocksTilesetPath("assets/MossyTileset/MossyFloatingPlatforms.png");

        //start at 120, 180
        stage.addEntity(new MainCharacter(120,180));
        stage.addEntity(new Skeleton(251, 180));
        stage.addEntity(new Skeleton(251, 180));

        stage.addEntity(new Portal(-10000,200));

        //Adding all types of entity before the bulk
        for(Entity e : stage.getEntities())
        {
            stage.addEntityType(e);
        }

        addSolidBlock( 100,300,"MHA");
        addSolidBlock( 50, 800, "MHB");
        stage.addEntity(new Skeleton(200,700));
        addSolidBlock( 50, 250, "MVA");
        addSolidBlock( 300, 250, "MHA");
        addSolidBlock( 400, 380, "MVA");
        addSolidBlock( 950, 250, "MVA");
        stage.addEntity(new Skeleton(995, 170));
        addSolidBlock( 1250, 105, "MVA");
        stage.addEntity(new Skeleton(1300, 50));
        addSolidBlock( 1650, 600, "MHB");
        addSolidBlock( 1950, 450, "MVA");
        stage.addEntity(new Skeleton(1750,-30000));
        stage.addEntity(new Skeleton(2050,-32000));
        stage.addEntity(new Skeleton(1950,-30000));
        stage.addEntity(new Skeleton(1750,-60000));
        stage.addEntity(new Skeleton(2050,-64000));
        stage.addEntity(new Skeleton(1950,-60000));
        addSolidBlock(2400, 400, "MHA");
        stage.addEntity(new Skeleton(2450,-50000));
        stage.addEntity(new Skeleton(2450,-100000));
        stage.addEntity(new Skeleton(2450,-150000));
        addSolidBlock(3300,800,"MHA");
        addSolidBlock(3350,700,"MHB");
        stage.addEntity(new Skeleton(3350,-60000));
        addSolidBlock(2650,250,"MVA");
        stage.addEntity(new Skeleton(2700,-45000));
        addSolidBlock(3650,400,"MVA");
        stage.addEntity(new Skeleton(3700,355));
        addSolidBlock( 3500,150,"MHB");
        stage.addEntity(new Skeleton(3550,145));
        stage.addEntity(new Skeleton(3650,145));
        stage.addEntity(new Skeleton(3750,145));
        addSolidBlock( 3600,350,"MHA");
        stage.addEntity(new Skeleton(3650,340));
        addSolidBlock(3500,300,"MHA");
        addSolidBlock(3450,600,"MVA");
        stage.addEntity(new Skeleton(3500,585));
        addSolidBlock(3550,500,"MVA");
        stage.addEntity(new Skeleton(3600,485));
        stage.addEntity(new Skeleton(3700, -200000));
        stage.addEntity(new Skeleton(3700, -250000));
        stage.addEntity(new Skeleton(3700, -300000));
        stage.addEntity(new Skeleton(3700, -350000));
        stage.addEntity(new Skeleton(3700, -450000));
        addSolidBlock(3200,150, "MHA");

        for(int i = 0; i < 1; i++)
        {
            triggers.put("t"+i,true);
        }
    }

    /**
     * Current types of block: MHA - MHB - MVA<br>
     * MHA: Horizontal type A<br>
     * MHB: Horizontal type B<br>
     * MVA: Vertical type A<br>
     * @param x int window position x
     * @param y int window position y
     * @param type String type of block
     */
    private void addSolidBlock(int x, int y, String type) { //x100y300

        /* Unused at the moment
        //Horizontal Solid Block Type A
        int[] typeMHA = {475,25,1530,490};
        //Vertical Solid Block Type A
        int[] typeMVA = {1590,25,2045,995};
        //Horizontal Solid Block Type B
        int[] typeMHB = {20,1570,2045,1995};

        int[][] types = {typeMHA, typeMHB, typeMVA};*/

        MapBlock mb = null;
        Map stage = stages.get(currentStage);

        switch (type)
        {
            case "MHA": //Horizontal Solid Block Type A
                mb =new MapBlock(x,y,stage.getBlocksTilesetPath(),
                        475,25,1530,490, true, type, -15);
                break;

            case "MHB": //Horizontal Solid Block Type B
                stage.addBlock(new MapBlock(x, y, stage.getBlocksTilesetPath(),
                        20, 1570, 2045, 1995, true, type, -15));
                break;

            case "MVA": //Vertical Solid Block Type A
                stage.addBlock(new MapBlock(x,y, stage.getBlocksTilesetPath(),
                        1590,25,2045,995, true,type, -15));
                break;
        }

        if(mb != null)
            stage.addBlock(mb);
    }

    /**
     * This method will set the game state with a procedurally generated initial
     * test stage.
     */
    public void initializeTestStage()
    {
        Map stage = new Map();
        backgroundImageFilePath = "assets/Backgrounds/fantasy-forest.jpg";
        backgroundSoundFilePath = "assets/Sounds/zero-project - 02 - Still breathing.mp3";

        stages.add(stage);
        //Horizontal Solid Block Type A
        int[] typeMHA = {475,25,1530,490};
        //Vertical Solid Block Type A
        int[] typeMVA = {1590,25,2045,995};
        //Horizontal Solid Block Type B
        int[] typeMHB = {20,1570,2045,1995};

        int[][] types = {typeMHA, typeMHB, typeMVA};

        stage.setBlocksTilesetPath("assets/MossyTileset/MossyFloatingPlatforms.png");

        //Horizontal Solid Block Type A
        stage.addBlock(new MapBlock(100,300,stage.getBlocksTilesetPath(),
                475,25,1530,490, true, "MHA", -15));

        //Vertical Solid Block Type A
        stage.addBlock(new MapBlock(50,250, stage.getBlocksTilesetPath(),
                1590,25,2045,995, true,"MVA", -15));

        //Horizontal Solid Block Type B
        stage.addBlock(new MapBlock(50, 800, stage.getBlocksTilesetPath(),
                20, 1570, 2045, 1995, true, "MHB", -15));

        stage.addBlock(new MapBlock(300,250,stage.getBlocksTilesetPath(),
                475,25,1530,490, true, "MHA", -15));

        stage.addBlock(new MapBlock(400,380,stage.getBlocksTilesetPath(),
                1590,25,2045,1000, false,"MVA"));

        stage.addEntity(new MainCharacter(120,180));
        stage.addEntity(new Skeleton(251, 180));

        //Adding all types before the bulk
        for(Entity e : stage.getEntities())
        {
            stage.addEntityType(e);
        }

        stage.addEntity(new Skeleton(351, 180));

        //Generating 2000 entities is really tough on CPU for a single thread...
        byte multiplier = 22;
        Thread[] threads = new Thread[multiplier];
        //But, I think the threads can cause null pointers on javafx loading though...
        for(byte i = 0; i < multiplier; i++)
        {
            threads[i] = new Thread()
            {
                @Override
                public void run()
                {
                    super.run();

                    for (byte i = 0; i < 50; i++)
                    {
                        int[] bounds = types[(int) (Math.random() * types.length)];

                        int x = (int) (Math.random() * 75000);
                        int y = (int) (Math.random() * 5000);

                        stage.addBlock(
                            new MapBlock(x, y, stage.getBlocksTilesetPath(),
                                bounds[0], bounds[1], bounds[2], bounds[3],
                                    true, "", -15));

                        stage.addEntity(new Skeleton(x + 50, y - 50));
                    }
                }
            };
            threads[i].start();
        }
        for (int i = 0; i < multiplier; i++)
        {
            try {
                threads[i].join();
            }
            catch (Exception e){ e.printStackTrace(); }
        }
    }

    /**
     * Method used for parsing user input and modifying the game state accordingly.
     *
     * @param event String that must contain one of the following inputs.
     *
     *              <br><br><h3>Possible user inputs:</h3><br>
     *
     *              <h3>MV_RIGHT:</h3>
     *                          <p>The playable character will change direction
     *                          and move east side.</p><br>
     *
     *              <h3>MV_LEFT:</h3>
     *                          <p>The playable character will change direction
     *                          and move west side.</p><br>
     *
     *              <h3>MV_JUMP:</h3>
     *                          <p>The playable character will jump.</p><br>
     *
     *              <h3>MV_JUMP_CANCEL:</h3>
     *                          <p>This should be passed when the jump trigger
     *                          stops, so jumping can be terminated midway.</p><br>
     *
     *              <h3>ATTACK:</h3>
     *                          <p>The playable character will go into attack mode,
     *                          the main character will be unable to move,
     *                          and the method will check if any enemy is hit
     *                          during the animation, then lower its HP.</p><br>
     *
     *              <h3>ATTACK_CANCEL:</h3>
     *                          <p>This should be passed when the attack trigger
     *                          stops, so the attack animation is reset and
     *                          the main character is freed from the attack lock.</p>
     *                          <br>
     *
     *              <h3>IDLE:</h3>
     *                          <p>This should be passed after a certain amount of time,
     *                          the main character will go into idle animation.</p>
     */
    public void handleUserEvents(String event)
    {
        MainCharacter mc = (MainCharacter)
                stages.get(currentStage).getEntities().get(MAIN_CHARACTER);

        switch (event)
        {
            case "MV_RIGHT":
                mc.setDirection(DIRECTION_RIGHT);

                if(!mc.isJumping()) {
                    mc.setSpriteChangeRate(10);
                    mc.setPattern("run");
                }

                mc.move();
                break;

            case "MV_LEFT":
                mc.setDirection(DIRECTION_LEFT);

                if(!mc.isJumping()) {
                    mc.setSpriteChangeRate(10);
                    mc.setPattern("run");
                }

                mc.move();
                break;

            case "MV_JUMP":
                if (mc.isJumping())
                {
                    mc.setPattern("jump");
                    mc.setSpriteChangeRate(20);

                    mc.jump();
                }

                else
                {
                    for (MapBlock mb : stages.get(currentStage).getBlocks())
                    {
                        if (mb.hasEntityOnTop(mc))
                            mc.jump();
                    }
                }
                break;

            case "MV_JUMP_CANCEL":
                if(mc.isJumping())
                {
                    mc.setJumping(false);
                    mc.setFallingSpeed((mc.getFallingSpeed() - mc.getMvspd())
                            > 0 ? (mc.getFallingSpeed() - mc.getMvspd()) : 0 );
                }
                break;

            case "ATTACK":
                mc.attack();
                mc.setSpriteChangeRate(10);
                mc.setPattern("attack");

                if (mc.isAttacking())
                {
                    List<Entity> entities = stages.get(currentStage).getEntities();

                    for (Entity e : entities)
                    {
                        if (!(e instanceof MainCharacter) && mc.collidesWith(e))
                        {
                            e.reduceHp(mc.getAtk());

                            byte direction = (byte) ((mc.getX() - e.getX())
                                    / (Math.abs(mc.getX() - e.getX())));

                            e.moveTo(e.getX() - direction
                                    * (e.getWidth() + mc.getFallingSpeed()), e.getY());
                        }
                    }
                }
                break;

            case "ATTACK_CANCEL":
                if(mc.isAttacking())
                {
                    mc.setAttacking(false);
                }
                break;

            case "BLOCK":
                break;

            case "DODGE":
                break;

            case "IDLE":
                mc.setPattern("idle");
                mc.setSpriteChangeRate(15);
                break;
        }
    }

    /**
     * This method should be constantly called in the game loop. It handles all the
     * game logic, and will modify the game state accordingly.
     */
    public void handleGameLogic()
    {
        MainCharacter mc = (MainCharacter)
                stages.get(currentStage).getEntities().get(MAIN_CHARACTER);

        List<Entity> entities = stages.get(currentStage).getEntities();

        for(int i = 0; i < entities.size(); i++)
        {
            //In case the loop is still ongoing when the user returns to the main menu
            if(gameInProgress) {
                Entity e = entities.get(i);

                boolean onSolidGround = false;
                boolean movable = e instanceof Movable;
                double lowestMapBlock = 0;

                if (e.getPatternSettings(e.getPattern()) != null)
                    e.advanceFrame();

                for (MapBlock mb : stages.get(currentStage).getBlocks()) {
                    if (mb.isSolidGround() && mb.hasEntityOnTop(entities.get(i))) {
                        onSolidGround = true;

                        if (movable && e instanceof Enemy && mb.edgeReached(e)) {
                            e.setDirection(-e.getDirection());
                            ((Movable) e).move();
                            ((Movable) e).move();
                        }
                    }

                    if (mb.getY() > lowestMapBlock) {
                        lowestMapBlock = mb.getY();
                    }
                }

                if (e instanceof Enemy && e.collidesWith(mc) && !mc.isAttacking()) {
                    byte direction = (byte) ((mc.getX() - e.getX())
                            / (Math.abs(mc.getX() - e.getX())));

                    mc.moveTo(mc.getX() + direction * e.getMvspd() * mc.getMvspd()
                            * (20 + mc.getFallingSpeed()), mc.getY() - 20);

                    e.moveTo(e.getX() - direction
                            * (20 + mc.getFallingSpeed()), e.getY());

                    mc.reduceHp(e.getAtk() / 10);
                }

                if (e instanceof Attacker && e instanceof Enemy)
                {
                    //PENDING - pseudocode - missing methods
                    /* if e is in range of mc -> ((Attacker) e).attack(); */
                }

                if (!onSolidGround) {
                    e.setFallingSpeed(e.getFallingSpeed() + GRAVITY);

                    e.moveTo(entities.get(i).getX(), e.getY() + e.getFallingSpeed());

                    if (e.getY() > lowestMapBlock + 150) {
                        if (e instanceof MainCharacter)
                            gameInProgress = false;
                        else
                            entities.remove(e);
                    }
                } else {
                    e.setFallingSpeed(GRAVITY);

                    if (movable && e instanceof Enemy)
                        ((Movable) e).move();
                }

                if(e.getHp() <= 0) {
                    if(e instanceof MainCharacter) {
                        gameInProgress = false;
                        score = score * mc.getHp();
                        Score.saveScores(score,Score.loadScores());
                    }

                    else {
                        entities.remove(e);
                        score += 500;
                    }
                }
            }
        }

        triggerStageEvents();
    }

    //Method extracted to alleviate the convolution of handling the game Logic
    private void triggerStageEvents()
    {
        MainCharacter mc = (MainCharacter)
                stages.get(currentStage).getEntities().get(MAIN_CHARACTER);

        Map stage = stages.get(currentStage);

        switch (currentStage)
        {
            case 0:
                if(triggers.get("t0") && mc.getX() > 3200 && mc.getX() < 3300 && mc.getY() < 149) {
                    addSolidBlock(3200, 100, "MVA");
                    addSolidBlock(2000, 200, "MHA");
                    addSolidBlock(1500, 250, "MVA");
                    stage.addEntity(new Skeleton(1750,-10000));
                    stage.addEntity(new Skeleton(2050,-12000));
                    stage.addEntity(new Skeleton(1950,-10000));
                    stage.addEntity(new Skeleton(1750,-20000));
                    stage.addEntity(new Skeleton(2050,-24000));
                    stage.addEntity(new Skeleton(1950,-20000));
                    stage.addEntity(new Portal(75,500));
                    triggers.put("t0",false);
                }

                if(stage.getEntities().size() == 1) {
                    clearCondition = true;
                    score = score * mc.getHp();
                    Score.saveScores(score,Score.loadScores());
                }

                if(!triggers.get("t0") &&
                        mc.collidesWith(stage.getEntities().get(
                                stage.getEntities().size() - 1)))
                {
                    clearCondition = true;
                    score = score * mc.getHp();
                    Score.saveScores(score,Score.loadScores());
                }
                break;
        }
    }

    /**
     * Method used to reset the game to the initial state.
     */
    public void reset()
    {
        stages.clear();
        clearCondition = false;
        currentStage = 0;
        gameInProgress = true;
        initializeStageOne();
    }

    /**
     * Method that returns the user score.
     * @return int score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Method used to access the background objects within each stage.
     * @return list<Map> stages
     */
    public List<Map> getStages() {
        return stages;
    }

    /**
     * Method used to check the current stage number.
     * @return int current stage
     */
    public int getCurrentStage() {
        return currentStage;
    }

    /**
     * Method that returns true if the game is still in progress, or false
     * if the game logic has reached an end point.
     * @return boolean game state
     */
    public boolean isInProgress() {
        return gameInProgress;
    }

    /**
     * Method that contains the background image file info.
     * @return String image path
     */
    public String getBackgroundImageFilePath() {
        return backgroundImageFilePath;
    }

    /**
     * Method that contains the background music file info.
     * @return String sound file path
     */
    public String getBackgroundSoundFilePath() {
        return backgroundSoundFilePath;
    }

    /**
     * Method that returns if the stage has been cleared.
     * @return true / false
     */
    public boolean getClearCondition()
    {
        return clearCondition;
    }
}
