import java.util.Date;
import java.util.List;
import java.util.*;

public class Assignment implements Calendar {
	
	// The default constructor for the class should be public
	// We will use this when we test your code!
	Hashtable< Date, ArrayList< Appointment> > byDate;
	Hashtable< String, ArrayList< Appointment > > byLocation;
	//LinkedList< Appointment > byAppointment;
	
	public Assignment() {
		byDate = new Hashtable<Date,ArrayList< Appointment >>();
		byLocation = new Hashtable< String,ArrayList< Appointment >>();
		//byAppointment = new LinkedList< Appointment >();
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
		// TODO Implement this! (then remove this TODO comment)
		if ( byDate.containsKey( when ) ){
			if ( byDate.get( when ).size() > 0 ){
				return byDate.get( when ).get( 0 );
			}
		}else{
			Set< Date > keys = byDate.keySet();
			List< Date > keyList = new ArrayList( keys );
			Collections.sort( keyList );
			//Iterator< Date > itr = keys.iterator();
			//while( itr.hasNext() ){
			for ( int i = 0; i < keyList.size(); i ++ ){
				//Date d = itr.next();
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
		}else if ( byLocation.containsKey( location ) ){
			ArrayList< Appointment> temp = byLocation.get( location );
			Hashtable< Date, ArrayList<Appointment> > ht = new Hashtable< Date, ArrayList<Appointment> >();
			for ( int i = 0; i < temp.size(); i++ ){
				if ( ht.containsKey( temp.get( i ).getStartTime(  ) ) ){
					ArrayList< Appointment > list = ht.get( temp.get(i).getStartTime());
					list.add( temp.get( i ) );
				}else{
					ArrayList< Appointment > list = new ArrayList<>();
					list.add(temp.get(i));
					ht.put( temp.get( i ).getStartTime(), list );
				}
			}

			Set<Date> keys = ht.keySet();
			List<Date> keyList = new ArrayList( keys );
			Collections.sort( keyList );
			
			for ( int i = 0; i < temp.size(); i++ ){
				Date d = keyList.get( i );
				if ( d.after( when ) ){
					return ht.get( d ).get(0);
				}
			}
			
			
			/*for ( Appointment i : temp ){
				if ( i.getStartTime().equals( when ) || i.getStartTime().after( when ) ){
					return i;
				}
			}*/
		}
		return null;
	}

	@Override
	public void add(String description, Date when, String location) {
		if ( description == null || when == null || location == null ){
			throw new IllegalArgumentException( "One or argument is null" );
		}
		
		MyAppointment temp = new MyAppointment( description, when, location );
		//byAppointment.add( temp );	
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
		// TODO Implement this! (then remove this TODO comment)
		if ( appointment == null ){
			throw new IllegalArgumentException("Appointment is null");
		}
		Date date = appointment.getStartTime();
		String location = appointment.getLocation();
		if ( !byDate.containsKey( date ) || !byLocation.containsKey( location ) ){
			return;
		}else{
		//byAppointment.remove( appointment );
		byDate.get( date ).remove( appointment );
		byLocation.get( location ).remove( appointment );
		}
	}
}
