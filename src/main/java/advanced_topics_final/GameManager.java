package advanced_topics_final;

import java.util.ArrayList;

import advanced_topics_final.numerics.Vector2;

public class GameManager {
    public static int BLOCK_SIZE = 60;
    public static int[][] MAP = new int[][]{
        {1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,1,1,0,0,0,1},
        {1,0,0,0,1,1,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,1,1,0,0,0,1},
        {1,0,0,0,1,1,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1}
    };
    public static ArrayList<Entity> entities = new ArrayList<Entity>();
    public static void InitDebug(){
        Entity debug = new Entity();
        debug.position = new Vector2(4, 4);
        debug.dimensions = new Vector2(0.5f, 0.5f);
        entities.add(debug);
    }
}
