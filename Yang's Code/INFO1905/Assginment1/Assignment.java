import java.util.Date;
import java.util.List;
import java.util.*;

public class Assignment implements Calendar {
	
	// The default constructor for the class should be public
	// We will use this when we test your code!
	HashMap< Date, ArrayList< Appointment> > byDate;
	HashMap< String, ArrayList< Appointment > > byLocation;

	public Assignment() {
		byDate = new HashMap<Date,ArrayList< Appointment >>();
		byLocation = new HashMap< String,ArrayList< Appointment >>();
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
		if ( location == null ){
			throw new IllegalArgumentException("location was null");
		}
		ArrayList< Appointment > list = byLocation.get( location );
		if ( list == null ){
			list = new ArrayList< Appointment >();
		}
		return list;
	}

	@Override
	public Appointment getNextAppointment(Date when) {
		if(when == null) {
			throw new IllegalArgumentException("time was null");
		}
		// check if "when" is found if not, sort date then iterate
		if ( byDate.containsKey( when ) ){
			if ( byDate.get( when ).size() > 0 ){
				return byDate.get( when ).get( 0 );
			}
		// sort first then iterate
		}else{
			Set< Date > keys = byDate.keySet();
			List< Date > keyList = new ArrayList( keys );
			Collections.sort( keyList );
	
			for ( int i = 0; i < keyList.size(); i ++ ){
				Date d = keyList.get( i );
				if ( d.after( when ) ){
					if( byDate.get( d ).size() > 0 ){
						return byDate.get( d ).get( 0 );
					}
				}
			}
		}
		return null;
	}

	@Override
	public Appointment getNextAppointment(Date when, String location) {
		if ( when == null || location == null ){
			throw new IllegalArgumentException("One or more argument is null");
		}
		//attempt to search by date first
		if ( byDate.containsKey( when ) ){
			ArrayList< Appointment > temp = byDate.get( when );
			for ( Appointment i : temp ){
				if ( i.getLocation().equals( location ) ){
					return i;
				}
			}
		}
		// didn't find any appointment in geiven date so start to look at location
		// if location was found, get all dates then sort so can get very 
		//first appointment after "when"
		if ( byLocation.containsKey( location ) ){
			ArrayList< Appointment> temp = byLocation.get( location );
			HashMap< Date, ArrayList<Appointment> > ht = new HashMap< Date, ArrayList<Appointment> >();
		
			for ( int i = 0; i < temp.size(); i++ ){
				Appointment app = temp.get( i );
				if ( ht.containsKey( app.getStartTime() ) ){
					ArrayList< Appointment > list = ht.get( app.getStartTime() );
					list.add( app );
				}else{
					ArrayList< Appointment > list = new ArrayList<>();
					list.add( app );
					ht.put( app.getStartTime(), list );
				}
			}

			// sort date and iterate
			Set<Date> keys = ht.keySet();
			List<Date> keyList = new ArrayList( keys );
			Collections.sort( keyList );
			
			for ( int i = 0; i < temp.size(); i++ ){
				Date d = keyList.get( i );
				if ( d.after( when ) ){
					return ht.get( d ).get(0);
				}
			}
		}
		return null;
	}

	@Override
	public void add(String description, Date when, String location) {
		if ( description == null || when == null || location == null ){
			throw new IllegalArgumentException( "One or more argument is null" );
		}
		
		MyAppointment temp = new MyAppointment( description, when, location );
		//add to map associated by date
		if ( !byDate.containsKey( when ) ){
			ArrayList< Appointment > list = new ArrayList<>();
			list.add( temp );
			byDate.put( when, list );
		}else{
			ArrayList< Appointment > list = byDate.get( when );
			list.add( temp );
		}
		// add to map associated by location
		if( !byLocation.containsKey( location ) ){
			ArrayList< Appointment > list2 = new ArrayList<>();
			list2.add( temp );
			byLocation.put( location, list2 );
		}else{
			ArrayList< Appointment> list2 = byLocation.get( location );
			list2.add( temp );
		}
	}

	@Override
	public void remove(Appointment appointment) {
		if ( appointment == null ){
			throw new IllegalArgumentException("Appointment is null");
		}
		Date date = appointment.getStartTime();
		String location = appointment.getLocation();
		if ( !byDate.containsKey( date ) || !byLocation.containsKey( location ) ){
			return;
		}else{
		byDate.get( date ).remove( appointment );
		byLocation.get( location ).remove( appointment );

		//once the list is empty, remove the key from map
			if ( byDate.get( date ).size() == 0 ){
				byDate.remove( date );
			}

			if ( byLocation.get( location).size() == 0 ){
				byLocation.remove( location );
			}
		}
	}

}
