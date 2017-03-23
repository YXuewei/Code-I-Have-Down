public class Strange{
    public static void main(String[] args) {
    double one = 0.1;
    double two = 0.2;
    double three = 0.3;
    double six = 0.6;

    if ((one + two) + three != six) {
      System.out.println("The two expressions are DIFFERENT!");
    } else {
      System.out.println("The two expressions are equal.");
    }
  }
}