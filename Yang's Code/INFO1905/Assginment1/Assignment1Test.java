import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;


public class Assignment1Test {
	@Test
	public void putTest(){
        Date d = new Date(2016, 9, 03);
        Assignment temp = new Assignment();
        temp.add( "A", d,"SIT 117");
        temp.add( "B", d, "SIT 117");
        temp.add("C", d, "SIT 117");

        assertEquals(3, temp.getAppointments("SIT 117").size() );
    }

    @Test
    public void getAppointmentsContentTest(){
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
    
        assertEquals("A", temp.getAppointments( l1 ).get( 0 ).getDescription() );
        assertEquals("B", temp.getAppointments( l1 ).get( 1 ).getDescription() );
        assertEquals("C", temp.getAppointments( l1 ).get( 2 ).getDescription() );
    
    }

    @Test
    public void getApointments_AddUnsorted(){
        Date a = new Date( 2016, 9, 03 );
        Date b = new Date( 2016, 9, 03 );
        Date c = new Date( 2016, 9, 03 );

        String l1 = "SIT117";
        String l2 = "SIT118";
        String l3 = "SIT119";

        Assignment temp = new Assignment();
        temp.add("A", a, l1 );
        temp.add("B", b, l2);
        temp.add("C", c, l3 );
        temp.add("D",b,l1);
        temp.add("E",c,l2);
        temp.add("F",a, l2);

        assertEquals(3, temp.getAppointments( l2 ).size() );
        assertEquals(2, temp.getAppointments( l1 ).size() );
    }

    @Test
    public void getAppointmentLocation_RemoveOne(){
        Date a = new Date( 2016, 9, 03 );
        Date b = new Date( 20-16,9, 04 );

        String l1 = "SIT117";
        String l2 = "SIT118";

        Assignment temp = new Assignment();
        temp.add( "A", a, l1 );
        temp.add( "B",b, l2);

       Appointment my = temp.getNextAppointment( a );
        temp.remove( my );

        assertEquals("SIT118", temp.getNextAppointment( b,l2 ).getLocation() );
        assertEquals("B", temp.getNextAppointment( b,l2 ).getDescription() );
    }

    @Test
    public void getAppointment(){
        Date a = new Date( 2016, 9, 03);
        Date b = new Date( 2016, 9, 04);
        Date c = new Date( 2016, 9, 02 );

        String l1 = "SIT117";

        Assignment temp = new Assignment();
        temp.add( "B",b, l1 );
        temp.add("A", a, l1 );

        Appointment app = temp.getNextAppointment( c );
        assertEquals( "A",app.getDescription() );
    }

    @Test
    public void getAppointment_AfterLast(){
        Date a = new Date( 2016, 9, 03 );
        Date b = new Date( 2016, 9, 04 );
        Date c = new Date( 2016,9, 05 );

        String l1 = "SIT117";
        String l2 = "SIT118";

        Assignment temp = new Assignment();
        temp.add( "A",a,l1);
        temp.add( "B", b, l2);

        assertEquals( null, temp.getNextAppointment(c ) );
    }
}
