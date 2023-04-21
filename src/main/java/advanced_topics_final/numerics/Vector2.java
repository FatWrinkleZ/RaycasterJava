package advanced_topics_final.numerics;

public class Vector2 {
    public float x, y;
    public Vector2(){x = 0; y = 0;}
    public Vector2(float x, float y){this.x = x; this.y = y;}

    public void Add(Vector2 vec){x+=vec.x; y+=vec.y;}
    public static Vector2 Addition(Vector2 vec1, Vector2 vec2){return new Vector2(vec1.x + vec2.x, vec1.y + vec2.y);}

    public void Subtract(Vector2 vec){x-=vec.x; y-=vec.y;}
    public static Vector2 Subtraction(Vector2 vec1, Vector2 vec2){return new Vector2(vec1.x-vec2.x,vec1.y-vec2.y);}

    public void Multiply(Vector2 vec){x*=vec.x;y*=vec.y;}
    public void Multiply(float mult){x*=mult; y*= mult;}
    public static Vector2 Multiplication(Vector2 vec1, Vector2 vec2){return new Vector2(vec1.x*vec2.x, vec1.y * vec2.y);}
    public static Vector2 Multiplication(Vector2 vec, float mult){return new Vector2(vec.x*mult, vec.y*mult);}

    public static float cross(Vector2 a, Vector2 b) {
        return a.x * b.y - a.y * b.x;
    }
    public float Magnitude(){
        return (float)Math.sqrt(x*x + y*y);
    }
    public static float Distance(Vector2 vec1, Vector2 vec2){
        Vector2 diff = Vector2.Subtraction(vec1, vec2);
        return diff.Magnitude();
    }
    public static float dot(Vector2 vec1, Vector2 vec2) {
        vec1 = vec1.Normalized();
        vec2 = vec2.Normalized();
        return vec1.x * vec2.x + vec1.y * vec2.y;
    }
    

    public Vector2 Normalized() {
        float magnitude = Magnitude();
        if (magnitude != 0) {
            return new Vector2(x / magnitude, y / magnitude);
        }
        return new Vector2(0, 0);
    }
    public static float angleBetweenVectors(Vector2 v1, Vector2 v2) {
        float dot = Vector2.dot(v1, v2);
        float v1Magnitude = v1.Magnitude();
        float v2Magnitude = v2.Magnitude();
        return (float) Math.toDegrees(Math.acos(dot / (v1Magnitude * v2Magnitude)));
    }
    
    

}
