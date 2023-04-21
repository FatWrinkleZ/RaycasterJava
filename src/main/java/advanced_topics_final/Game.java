package advanced_topics_final;

import advanced_topics_final.numerics.Vector2;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Game extends Group implements EventHandler<KeyEvent>{

    public GraphicsContext context;
    public Canvas canvas;
    public AnimationTimer ticker;
    public static float deltaTime;
    public static float FPS;
    Camera cam = new Camera();

    public Game(int sceneWidth, int sceneHeight){
        cam.position = new Vector2(3, 3);
        cam.rotation = 45;
        cam.fov=60;
        cam.InitCameraPlane();
        canvas = new Canvas(sceneWidth,sceneHeight);
        context = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
        GameManager.InitDebug();
        ticker = new AnimationTimer() {

            @Override
            public void handle(long now) {
                Update(now);
            }
            
        };
        ticker.start();
    }
    private static long then = 0;
    public void Update(long now){
        deltaTime = (float)(now-then)/(float)Math.pow(10, 9);
        Renderer.Raycast(context, cam);
        context.setStroke(Color.WHITE);
        context.setLineWidth(1);
        FPS = (1.0f/deltaTime);
        context.strokeText("FPS: "+(int)FPS, App.WIDTH-50, 10, App.WIDTH);
        then = now;
    }
    @Override
    public void handle(KeyEvent event) {
        KeyCode key = event.getCode();
        if(key == KeyCode.A){cam.rotation-=2f;}
        if(key == KeyCode.D){cam.rotation += 2f;}
        if(cam.rotation > 360){cam.rotation -= 360;}
        if(cam.rotation < 0){cam.rotation += 360;}
        Vector2 moveVec = new Vector2(cam.position.x, cam.position.y);
        if(key == KeyCode.W){moveVec.x += 0.1f*Math.cos(Math.toRadians(cam.rotation)); moveVec.y += 0.1f*Math.sin(Math.toRadians(cam.rotation));}
        if(key == KeyCode.S){moveVec.x -= 0.1f*Math.cos(Math.toRadians(cam.rotation)); moveVec.y -= 0.1f*Math.sin(Math.toRadians(cam.rotation));}
        if(GameManager.MAP[(int)(moveVec.x+0.5f)][(int)cam.position.y]==0){
            cam.position.x = moveVec.x;
        }
        if(GameManager.MAP[(int)cam.position.x][(int)(moveVec.y+0.5f)]!=1){
            cam.position.y = moveVec.y;
        }
        for(Entity e : GameManager.entities){
            e.lookAt(cam);
        }
        //cam.UpdateCameraPlane();
    }
    
}
