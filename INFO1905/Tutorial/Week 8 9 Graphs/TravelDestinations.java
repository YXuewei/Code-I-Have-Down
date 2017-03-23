import java.util.*;

public class TravelDestinations {

	private Graph<String, Integer> graph;

	public TravelDestinations(Graph<String, Integer> graph) {
		// TODO: implement this method
		this.graph = graph;
	}

	/* Exercise 1 methods */

	/**
	 * Return all the countries that are a single direct flight away from the
	 * given country, in any order. If no flights depart this country, return an
	 * empty List.
	 */
	public List<String> getDirectDestinations(String fromCountry) {
		List< String > list = new ArrayList<>();
		Iterable<Vertex<String>> verticesSet = graph.vertices();
		for( Vertex< String > vertex : verticesSet ){
			if ( vertex.getElement().equals( fromCountry ) ){
				for ( Edge<Integer> edge : graph.outgoingEdges( vertex ) ){
					list.add( graph.endVertices( edge )[ 1 ].getElement() );
				}
			}
		}
		return list;
	}

	/**
	 * Return true if there is a direct flight from 'fromCountry' to
	 * 'toCountry'. Otherwise, return false.
	 */
	public boolean isDirectFlight(String fromCountry, String toCountry) {
		boolean result = false;	
		List< String > list = this.getDirectDestinations( fromCountry );
		for ( String str : list ){
			if ( str.equals( toCountry ) ){
				result = true;
			}
		}
		return result;
	}

	/* Exercise 2 methods */
	/**
	 * Return all the countries that are reachable from the given country, using
	 * any number of flights (for example, if we can fly from A to B, then from
	 * B to C, then we can say that both B and C are reachable from A.
	 */
	public List<String> getReachableDestinations(String country) {
		Set< String > set = new HashSet<>();
		Stack< List< String > > stack = new Stack<>();
		List< String > list = getDirectDestinations( country );
		List< String > visited = new ArrayList<>();	
		
		stack.push ( list );

		while ( !stack.empty() ){
			List< String > temp = stack.pop();
			if ( temp != null ){
				set.addAll( temp );
			}
			for ( int i = 0; i < temp.size(); i++ ){
				if ( !visited.contains( temp.get( i ) ) ){
					stack.push( getDirectDestinations( temp.get( i ) ) );
				}
				visited.add( temp.get( i ) );
			}
		}
		
		set.remove( country );
		return new ArrayList( set );
	}

	/* Exercise 3 methods */

	/**
	 * Return the country ('destinationA' or 'destinationB') which requires
	 * fewer flights to travel to from country 'current'
	 */
	public String closerDestination(String current, String destinationA, String destinationB) {
		int lengthA = 0;
		boolean foundA = false;
		int lengthB = 0;
		boolean foundB = false;

		Stack< List<String> > stack = new Stack<>();
		List< String > visited = new ArrayList<>();
		List< String > list = getDirectDestinations( current );
		visited.add( current );
		stack.push( list );	
		//get a list of direct destination,then check if A or B in such list
		// if found, mark as found, if not, push list of destination into stack as pending for check
		// since same return either, so value with less length would always be correct value
		while ( !stack.empty() ){
			List< String > temp = stack.pop();

			if( foundA == false ){
				lengthA++;
			}

			if ( foundB == false ){
				lengthB++;
			}
			if ( temp.contains( destinationA ) ){
				foundA = true;
			}

			if ( temp.contains( destinationB ) ){
				foundB = true;
			}

			for ( int i = 0; i < temp.size(); i++ ){
				if ( !visited.contains( temp.get( i ) ) ){
					stack.push( getDirectDestinations( temp.get( i ) ) );
				}
				visited.add( temp.get( i ) );
			}
		}
		
		if ( foundA == false && foundB == false ){
			return null;
		}
		String result = ( lengthA >= lengthB ) ? destinationB : destinationA;

		return result;
	}

	public String cheapestDirectFlight(String fromCountry) {
		Iterable< Vertex<String>> vertexSet = graph.vertices();
		Vertex<String> from = null;
		for ( Vertex<String> v : vertexSet ){
			if ( v.getElement().equals( fromCountry ) ){
				from = v;
				break;
			}
		}
		if( from == null ){
			return null;
		}

		Edge< Integer > minimalEdge = null;
		int minimalValue = Integer.MAX_VALUE;
		Iterable< Edge< Integer > > edegeSet = graph.outgoingEdges( from );
		int count = 0;
		for ( Edge< Integer > e : edegeSet ){
			count++;
			if ( e.getElement() < minimalValue ){
				minimalValue = e.getElement();
				minimalEdge = e;
			}
		}
		if ( count == 0 ){
			return null;
		}
		return graph.endVertices( minimalEdge )[1].getElement();
	}

	/**
	 * Return the minimal cost to get to toCountry from fromCountry. If
	 * fromCountry cannot be reached, then return Integer.MAX_VALUE
	 */
	public int shortestPathCost(String fromCountry, String toCountry) {
		// TODO: implement this
		return Integer.MAX_VALUE;
	}

	/**
	 * Return a path which has the minimal cost to get to toCountry from
	 * fromCountry. If fromCountry cannot be reached, then return null
	 */
	public List<String> shortestPath(String fromCountry, String toCountry) {
		// TODO: implement this
		return null;
	}
	
}
