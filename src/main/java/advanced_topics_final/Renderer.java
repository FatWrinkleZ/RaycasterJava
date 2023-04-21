package advanced_topics_final;

import advanced_topics_final.numerics.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class Renderer {
    public static final float RESOLUTION = 2;
    private static final float PI = (float)Math.PI;
    private static final float WIDTH = App.WIDTH/RESOLUTION;
    private static final float HEIGHT = App.HEIGHT/RESOLUTION;
    public static void Raycast(GraphicsContext context, Camera camera){
        context.setFill(Color.BLACK);
        context.fillRect(0,0,App.WIDTH, App.HEIGHT);
        context.setLineWidth(RESOLUTION+1);
        CastRay(context, camera);
        context.setStroke(Color.WHITE);
        context.setLineWidth(1);
        context.strokeText("angle = "+camera.rotation+String.format("\tPlayerPosition (%.3f, %.3f)",camera.position.x, camera.position.y), 10, 10, App.WIDTH);
    }

    private static void CastRay(GraphicsContext context, Camera camera){
        float fovInRads = camera.fov * PI/180f;
        for(int i = 0; i < GameManager.MAP.length; i ++){
            for(int j = 0; j < GameManager.MAP[0].length; j++){
                GameManager.MAP[i][j] = GameManager.MAP[i][j]!=0 ? 1 : 0;
            }
        }
        float px = camera.position.x, py = camera.position.y;
        float rotationInRads = (camera.rotation) * PI/180f;
        rotationInRads = FixAng(rotationInRads);
        float pangle = rotationInRads - (fovInRads/2f);
        float incrementAng = fovInRads/WIDTH;
        camera.UpdateCameraPlane(rotationInRads);
        for(int x = 0; x < WIDTH; x++){
            //camera.UpdateCameraPlane(pangle);
            int MAPx = (int)px;
            int MAPy = (int)py;
            float sideDistX, sideDistY;
            float cameraX = 2 * x / (float) WIDTH - 1;
            float rayDirX = (float) (Math.cos(pangle) + camera.cameraPlane.x * cameraX);
            float rayDirY = (float) (Math.sin(pangle) + camera.cameraPlane.y * cameraX);
            float deltaDistX = (float)((rayDirX == 0) ? 0 : Math.abs(1.0f / rayDirX));
            float deltaDistY = (float)((rayDirY == 0) ? 0 : Math.abs(1.0f / rayDirY));
            Vector2 rayDir = new Vector2(rayDirX,rayDirY);
            int stepX, stepY;
            int hit = 0, side=0;
            if(rayDirX < 0){
                stepX = -1;
                sideDistX = (px - MAPx)*deltaDistX;
            }else{
                stepX = 1;
                sideDistX = (MAPx + 1.0f - px)*deltaDistX;
            }
            if(rayDirY < 0){
                stepY = -1;
                sideDistY = (py-MAPy)*deltaDistY;
            }else{
                stepY = 1;
                sideDistY = (MAPy + 1.0f - py)*deltaDistY;
            }
            while(hit == 0){
                if(sideDistX < sideDistY){
                    sideDistX += deltaDistX;
                    MAPx += stepX;
                    side = 0;
                    context.setStroke(Color.RED);
                }else{
                    sideDistY += deltaDistY;
                    MAPy+=stepY;
                    side = 1;
                    context.setStroke(Color.DARKRED);
                }
                if(MAPx >= GameManager.MAP.length || MAPy >= GameManager.MAP[0].length || GameManager.MAP[MAPx][MAPy]!=0){
                    hit = 1;
                    //GameManager.MAP[MAPx][MAPy]=2;
                }
            }
            float perpWallDist = (side == 0) ? (sideDistX - deltaDistX) : sideDistY-deltaDistY;
            float actWallDist = 0;
            if (side == 0) {
                actWallDist = Math.abs((MAPx - px + (1 - stepX) / 2) / rayDirX);
            } else {
                actWallDist = Math.abs((MAPy - py + (1 - stepY) / 2) / rayDirY);
            }
            float lineHeight = HEIGHT/perpWallDist;
            float drawStart = -lineHeight/2+HEIGHT/2;
            if(drawStart < 0)drawStart = 0;
            float drawEnd = lineHeight /2 + HEIGHT /2;
            if(drawEnd >= HEIGHT)drawEnd = HEIGHT-1;
            context.strokeLine(x*RESOLUTION, drawStart*RESOLUTION, x*RESOLUTION, drawEnd*RESOLUTION);
            context.setStroke(Color.GREEN);
            context.setFill(Color.GREEN);
            for (Entity e : GameManager.entities) {
                Vector2 diff = Vector2.Subtraction(e.position, camera.position);
                float dist = diff.Magnitude();
                float entityAngle = (float) Math.atan2(diff.y, diff.x) - rotationInRads;
            
                // Keep the angle between -PI and PI
                while (entityAngle > PI) {
                    entityAngle -= 2 * PI;
                }
                while (entityAngle < -PI) {
                    entityAngle += 2 * PI;
                }
            
                Vector2 l = Vector2.Addition(e.position, Vector2.Multiplication(e.right(), e.dimensions.x));
                Vector2 r = Vector2.Subtraction(e.position, Vector2.Multiplication(e.right(), e.dimensions.x));
            
                float entityScreenSpaceXLeft = (float) (WIDTH / 2) * (1 + ((float) Math.atan2(Vector2.Subtraction(l, camera.position).y, Vector2.Subtraction(l, camera.position).x) - rotationInRads) / fovInRads);
                float entityScreenSpaceXRight = (float) (WIDTH / 2) * (1 + ((float) Math.atan2(Vector2.Subtraction(r, camera.position).y, Vector2.Subtraction(r, camera.position).x) - rotationInRads) / fovInRads);
            
                if (dist < actWallDist) {
                    lineHeight = (HEIGHT * e.dimensions.y) / dist;
            
                    drawStart = (HEIGHT / 2) - (lineHeight / 2);
                    drawEnd = (HEIGHT / 2) + (lineHeight / 2);
            
                    if (drawStart < 0) drawStart = 0;
                    if (drawEnd >= HEIGHT) drawEnd = HEIGHT - 1;
            
                    for (int _x = (int) entityScreenSpaceXLeft; _x <= entityScreenSpaceXRight; _x++) {
                        context.strokeLine(_x * RESOLUTION, drawStart * RESOLUTION, _x * RESOLUTION, drawEnd * RESOLUTION);
                    }
                }
            }
            
            
            pangle=FixAng(pangle + incrementAng);
        }             
    }
    public static Vector2 rayIntersectsLineSegment(Vector2 rayOrigin, Vector2 rayDirection, Vector2 lineSegmentStart, Vector2 lineSegmentEnd) {
        Vector2 segmentDirection = Vector2.Subtraction(lineSegmentEnd, lineSegmentStart);
        Vector2 segmentOriginToRayOrigin = Vector2.Subtraction(rayOrigin, lineSegmentStart);
    
        float crossProduct = Vector2.cross(rayDirection, segmentDirection);
        if (Math.abs(crossProduct) < 1e-8) { // if parallel
            return null;
        }
    
        float t1 = Vector2.cross(segmentOriginToRayOrigin, segmentDirection) / crossProduct;
        float t2 = Vector2.cross(segmentOriginToRayOrigin, rayDirection) / crossProduct;
    
        if (t1 >= 0.001 && t2 >= 0 && t2 <= 1) {
            return Vector2.Addition(rayOrigin, Vector2.Multiplication(rayDirection, t1));
        }
        return null;
    }
    
    private static float FixAng(float ang){
        if(ang>2*PI){ ang-=2*PI;} if(ang<0){ ang+=(2*PI);} return ang;
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }
    

}
