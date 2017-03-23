import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;


public class Assignment1Test {
	@Test
    //Test if getAppointments return correct size of of list
	public void getAppointmentsTest(){
        Date d = new Date(2016, 9, 03);
        Assignment temp = new Assignment();
        temp.add( "A", d,"SIT 117");
        temp.add( "B", d, "SIT 117");
        temp.add("C", d, "SIT 117");

        assertEquals(3, temp.getAppointments("SIT 117").size() );
    }

    @Test
    //test if getAppointments return correct result
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
    // test if getAppointments return correct result when adding item with unsorted sequence
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
    // test if remove function working correctly
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
    //test if getNextAppointment return correct result
    public void getNextAppointment(){
        Date a = new Date( 2016, 9, 03);
        Date b = new Date( 2016, 9, 04);
        Date c = new Date( 2016, 9, 02 );

        String l1 = "SIT117";

        Assignment temp = new Assignment();
        temp.add( "B",b, l1 );
        temp.add("A", a, l1 );

        Appointment app = temp.getNextAppointment( a );
        assertEquals( "A",app.getDescription() );
    }

    @Test
    //test if getNextAppointment return correct result when given date that do not exist
    public void getNextAppointment_AfterLast(){
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

    @Test
    //test if getNextAppointment return correct result when all appointment hasbeen removed
    public void removeAllTest(){
        Date a = new Date( 2016, 9, 05 );
        Date b = new Date( 2016, 9, 06 );
        Date c = new Date( 2016, 9, 07 );
        Date d = new Date( 2016, 9, 12 );

        String l1 = "SIT115";
        String l2 = "SIT116";
        String l3 = "SIT117";
        String l4 = "SIT118";

        Assignment temp = new Assignment();
        temp.add( "A", a, l1 );
        temp.add( "B", b, l2 );
        temp.add( "C", c, l3 );
        temp.add( "D", a, l2 );

        Appointment app = temp.getNextAppointment( a );
        temp.remove( app );
        app = temp.getNextAppointment( a, l2 );
        temp.remove( app );
        app = temp.getNextAppointment( b, l2);
        temp.remove( app );
        app = temp.getNextAppointment( c, l3 );
        temp.remove( app );

        assertEquals( null, temp.getNextAppointment( c, l3 ) );
    }

    @Test
    //test if remove non exist appointment return correct result
    // if any problem happens, code would throw exception
    public void removeNonExist(){
        Date a = new Date( 2016, 9, 05);
        String l1 = "SIT118";
        Assignment temp = new Assignment();
        temp.add( "A", a, l1);
        Appointment app = temp.getNextAppointment( a );

        temp.remove( app );
        temp.remove( app );
    }

    @Test
    //test if remove duplicate appointment return correct result
    public void removeDuplicate(){

        Date a = new Date( 2016, 9, 05 );
        String l1 = "SIT118";

        Assignment temp = new Assignment();
        temp.add("A",a,l1);
        temp.add("B",a,l1);

        Appointment app = temp.getNextAppointment( a, l1 );
        temp.remove( app );
        
        assertEquals( "B", temp.getNextAppointment( a, l1 ).getDescription() );
    }

    @Test
    //test if return correct appointment when given a date that is before added date
    public void getNextAppointment_BeforeInputDate(){
        Date a = new Date( 2016, 9, 05 );
        Date b = new Date( 2016, 9, 06 );
        Date c = new Date( 2016, 9, 02 );

        String l1 = "SIT118";

        Assignment temp = new Assignment();
        temp.add( "B", b, l1 );
        temp.add( "A", a, l1 );
        
        assertEquals( "A", temp.getNextAppointment( c ).getDescription() );
    }

    @Test
    // test in valid argument
    // if any argument is invalid, an exception will be thrown.
    public void invalidArugumentTest(){
        Date a = null;
        String l1 = null;
        Appointment app = null;

        Assignment temp = new Assignment();
        temp.add( null, a, l1);
        temp.getNextAppointment( a );
        temp.getNextAppointment(a, l1);
        temp.getAppointments( l1 );
        temp.remove( app );
    }    

}
