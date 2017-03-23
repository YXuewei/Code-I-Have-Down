import java.awt.Point;

public class Test{
    public static void main(String[] args){
    int x1 = Integer.parseInt(args[0]);
    int y1 = Integer.parseInt(args[1]);
    int x2 = Integer.parseInt(args[2]);
    int y2 = Integer.parseInt(args[3]);
    Point p1 = new Point(x1,y1);
    Point p2 = new Point(x2,y2);
    double distance = p1.distance(p2);
    System.out.println("The distance is " + distance);
    }   
}