
public class BinarySearchableArray<T extends Comparable<T>> extends SearchableArray<T> {

	// constructor
	public BinarySearchableArray(T[] data) {
		super(data); //call the constructor of the super-type as SearchableArray<T>(data)
	}

	@Override
	public T search(T target) {

		int len = this.data.length;
		int result = this.BinarySearch( target, 0, len - 1);

		if (result == 0){
			return target;
		}
		
		return null; //if the search failed, return null
	}


	// 0 means find the target
	// 1 means did not find the target
	public int BinarySearch( T target, int lowerBound, int upperBound ){
		if ( lowerBound <= upperBound ){
			int mid = ( lowerBound + upperBound ) / 2;
			int result = this.data[ mid ].compareTo( target );
			if ( result == 0 ){
				return 0;
			} else if ( result < 0 ){
				return this.BinarySearch( target, mid + 1, upperBound );
			}else{
				return this.BinarySearch( target, lowerBound, upperBound - 1 );
			}
		}else{
			return 1;
		}
	}

}
