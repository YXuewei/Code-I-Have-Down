import java.util.Date;
import java.util.List;
import java.util.*;

public class Assignment implements Calendar {
	
	// The default constructor for the class should be public
	// We will use this when we test your code!
	Hashtable< Date, ArrayList< Appointment> > byDate;
	Hashtable< String, ArrayList< Appointment > > byLocation;
	LinkedList< Appointment > byAppointment;
	
	public Assignment() {
		byDate = new Hashtable<Date,ArrayList< Appointment >>();
		byLocation = new Hashtable< String,ArrayList< Appointment >>();
		byAppointment = new LinkedList< Appointment >();
	}

	public static class MyAppointment implements Appointment{
		
		private String description;
		private String location;
		private Date date;

		public MyAppointment( String d, Date t, String l ){
			this.description = d;
			this.date = t;
			this.location = l;
		}

		public String getDescription(){
			return this.description;
		}

		public String getLocation(){
			return this.location;
		}

		public Date getStartTime(){
			return this.date;
		}
	}

	@Override
	public List<Appointment> getAppointments(String location) {
		// TODO Implement this! (then remove this TODO comment)
		if ( location == null ){
			throw new IllegalArgumentException("location was null");
		}
		List< Appointment > list = byLocation.get( location );
		return list;
	}

	@Override
	public Appointment getNextAppointment(Date when) {
		if(when == null) {
			throw new IllegalArgumentException("time was null");
		}
		// TODO Implement this! (then remove this TODO comment)
		if ( byDate.containsKey( when ) ){
			return byDate.get( when ).get( 0 );
		}else{
			return null;
		}
	}

	@Override
	public Appointment getNextAppointment(Date when, String location) {
		// TODO Implement this! (then remove this TODO comment)
		if ( when == null || location == null ){
			throw new IllegalArgumentException("One or more argument is null");
		}
		
		if ( byDate.containsKey( when ) ){
			ArrayList< Appointment > temp = byDate.get( when );
			for ( Appointment i : temp ){
				if ( i.getLocation().equals( location ) ){
					return i;
				}
			}
		}
		return null;
	}

	@Override
	public void add(String description, Date when, String location) {
		if ( description == null || when == null || location == null ){
			throw new IllegalArgumentException( "One or argument is null" );
		}	
		
		MyAppointment temp = new MyAppointment( description, when, location );
		byAppointment.add( temp );	
		if ( !byDate.containsKey( when ) ){
			ArrayList< Appointment > list = new ArrayList<>();
			list.add( temp );
			byDate.put( when, list );
			byLocation.put ( location, list );
		}else{
			ArrayList< Appointment > list = byDate.get( when );
			list.add( temp );
		}
	}

	@Override
	public void remove(Appointment appointment) {
		// TODO Implement this! (then remove this TODO comment)
		if ( appointment == null ){
			throw new IllegalArgumentException("Appointment is null");
		}
		Date date = appointment.getStartTime();
		String location = appointment.getLocation();
		byAppointment.remove( appointment );
		byDate.remove( date );
		byLocation.remove( location );
	}
}
