package advanced_topics_final;
import advanced_topics_final.numerics.*;
public class Entity {
    public Vector2 position;
    public Vector2 velocity;
    public Vector2 dimensions;
    public float rotation;
    public Vector2 forward(){
        return new Vector2((float)Math.cos(Math.toRadians(rotation)), (float)Math.sin(Math.toRadians(rotation)));
    }
    public Vector2 right(){
        return new Vector2(forward().y, forward().x);
    }
}
