import java.util.*;

public class Test{
    public static void main( String[] args ){

        Date a = new Date( 2016, 9, 03 );
        Date b = new Date( 2016, 9, 03 );
        Date c = new Date( 2016, 9, 03 );

        String l1 = "SIT117";
        String l2 = "SIT118";
        String l3 = "SIT119";

        Assignment temp = new Assignment();
        temp.add( "A", a, l1);
        temp.add("B",b,l1);
        temp.add("C",c,l1);

        System.out.println( temp.getAppointments( l1 ).get( 0 ) );
        List < Appointment > list = temp.getAppointments( l1 );
        System.out.println( list.get( 1 ));

        System.out.println( a == b );
    }
}