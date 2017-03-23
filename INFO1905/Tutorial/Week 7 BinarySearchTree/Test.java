public class Test{
    public static void main( String[] args ){
        BstSimpleSortedMap map = new BstSimpleSortedMap();
        map.put( 1, "A" );
        //System.out.println( map.put(1, "A"));
        map.put( 2, "B" );
        //System.out.println( map.get(1));
        map.put( 3, "E" );
        System.out.println( map.get(1));
        System.out.println( map.get(2));
        System.out.println( map.get(3));
        System.out.println(map.size());
    }
}