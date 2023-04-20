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

    public float Magnitude(){
        return (float)Math.sqrt(x*x + y*y);
    }

    public static float cross(Vector2 a, Vector2 b) {
        return a.x * b.y - a.y * b.x;
    }

    public static float Distance(Vector2 vec1, Vector2 vec2){
        Vector2 diff = Vector2.Subtraction(vec1, vec2);
        return diff.Magnitude();
    }
    


}
