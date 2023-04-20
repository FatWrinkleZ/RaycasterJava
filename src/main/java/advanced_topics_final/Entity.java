package advanced_topics_final;
import advanced_topics_final.numerics.*;
public class Entity {
    public Vector2 position;
    public Vector2 velocity;
    public Vector2 dimensions;
    public float rotation;
    public Vector2 forward(Camera cam){
        return Vector2.Multiplication(cam.GetCameraPlane(), -1);
    }
    public Vector2 left(Camera cam){
        return new Vector2(-forward(cam).y, forward(cam).x);
    }
}
