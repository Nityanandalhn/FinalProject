package game.stages;

import game.basics.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* Code written by Nityananda Lorenzo Hernández during the year of 2021 for
 * the 1DAM course at IES San Vicente.
 */
/**
 * This class should be considered a partial stage, but could be considered complete
 * for simple or shorter stages on which only one map is required to finish.
 *
 * It holds three different lists.
 * MapBlocks: With all the background objects
 * entities: With all the different entities (Main character should be always be 0)
 * entity types: With one of each type of entity for reference and/or performance
 * improvements on loops required for accessing image paths for drawing.
 */
public class Map
{
    private List<MapBlock> blocks;
    private List<Entity> entities;
    private String blocksTilesetPath;
    private Set<Entity> entityTypes;

    /**
     * Will return a new empty map.
     */
    public Map()
    {
        blocks = new ArrayList<>();
        entities = new ArrayList<>();
        entityTypes = new HashSet<>();
    }

    /**
     * Method that adds entities to the entity list
     * @param e entity
     */
    public void addEntity(Entity e)
    {
        entities.add(e);
    }

    /**
     * Method that returns all the active entities within the map
     * @return list entity
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Method that adds a background block to the map
     * @param mb map block
     */
    public void addBlock(MapBlock mb)
    {
        blocks.add(mb);
    }

    /**
     * Method that returns the tile set image path used for all the blocks in this
     * map
     * @return String path
     */
    public String getBlocksTilesetPath()
    {
        return blocksTilesetPath;
    }

    /**
     * Method that allows changing the background image for this map
     * @param blocksTilesetPath String path
     */
    public void setBlocksTilesetPath(String blocksTilesetPath) {
        this.blocksTilesetPath = blocksTilesetPath;
    }

    /**
     * Method that returns all the background objects within this map
     * @return list map block
     */
    public List<MapBlock> getBlocks() {
        return blocks;
    }

    /**
     * Method that returns all the entity tipes used on this map
     * @return list entity types
     */
    public Set<Entity> getEntityTypes() {
        return entityTypes;
    }

    /**
     * Method that should be called once each time a new type of entity is
     * added to the map
     * @param e entity
     */
    public void addEntityType(Entity e)
    {
        entityTypes.add(e);
    }
}
