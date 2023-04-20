package advanced_topics_final;

import advanced_topics_final.numerics.Vector2;

public class Camera extends Entity{
    public float fov;
    public Vector2 cameraPlane;
    public void InitCameraPlane(){
        Vector2 direction = new Vector2((float)Math.cos(Math.toRadians(rotation)), (float)Math.sin(Math.toRadians(rotation)));
        float halfFovTan = (float) Math.tan(Math.toRadians(rotation) / 2.0);
        cameraPlane = new Vector2(-direction.y * halfFovTan, direction.x * halfFovTan);
    }
    public Vector2 GetCameraPlane(){
        Vector2 direction = new Vector2((float)Math.cos(Math.toRadians(rotation)), (float)Math.sin(Math.toRadians(rotation)));
        float halfFovTan = (float) Math.tan(Math.toRadians(fov) / 2.0);
        Vector2 camPlane = new Vector2(-direction.y * halfFovTan, direction.x * halfFovTan);
        return camPlane;
    }
    public void UpdateCameraPlane(float rotation){
        Vector2 direction = new Vector2((float)Math.cos(rotation), (float)Math.sin(rotation));
        float halfFovTan = (float) Math.tan(Math.toRadians(fov) / 2.0);
        cameraPlane = new Vector2(-direction.y * halfFovTan, direction.x * halfFovTan);
    }
}
