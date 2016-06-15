import java.util.*;
import java.math.*;

public class Vector {

	private Long sum;
	private Long mode;
	private Long median;
	private Long minimum;
	private Long maximum;
	
	public static long[] PQ_LIST = {4,6,9,10,14,15,21,22,25,26,33,34,
						  			35,38,39,46,49,51,55,57,58,62,65,
						  			69,74,77,82,85,86,87,91,93,94,95};									  
	
	private final int length;
	private final long[] elements;

	// ===========================================================================
	// INITIALIZATION
	// ===========================================================================

	/**
	 * Constructs new vector with the given
	 * length and all elements set to zero.
	 */
	public Vector(int length) {

		this.sum = null;
		this.mode = null;
		this.median = null;
		this.minimum = null;
		this.maximum = null;
	
		this.length = length;
		this.elements = new long[length];
	}

	/**
	 * Returns new vector with elements generated at random up to 100.
	 */
	public static Vector random(int length, int seed) {

		Vector vector = new Vector(length);
		Random random = new Random(seed);

		for (int i = 0; i < length; i++) {
			vector.elements[i] = (long) random.nextInt(101);
		}

		return vector;
	}

	/**
	 * Returns new vector with all elements set to given value.
	 */
	public static Vector uniform(int length, long value) {

		Vector temp = new Vector(length);
		for(int i =0; i < length; i++){
			temp.elements[i] = value;
		}
		temp.minimum = value;
		temp.maximum = value;
		temp.median = value;
		temp.mode = value;
		temp.sum = value * length;
		return temp;
	}

	/**
	 * Returns new vector with elements in sequence from given start and step.
	 */
	public static Vector sequence(int length, long start, long step) {
		Vector temp = new Vector(length);
		temp.elements[0] = start;
        for(int i = 1; i < length; i++){
            temp.elements[i] = start + ((long)i * step);
        }
		temp.minimum = temp.elements[0];
		temp.maximum = temp.elements[length - 1];
		temp.mode = (long)-1;
		temp.sum = ( (temp.elements[0] + temp.elements[length -1]) * length ) / 2;
		return temp;
	}

	/**
	 * Returns whether the number is semiprime.
	 */
	 public static boolean isPQ(long number) {
    	
		if(isPrime(number)){
			return false;
        }else if(number < 100){
			int len = PQ_LIST.length;
			for(int i = 0; i < len; i++ ){
				if(PQ_LIST[i] == number){
					return true;
				}
			}
			return false;
		}else{ 
			int root = (int)(Math.sqrt(number) + 1);
			for(int i = 2; i < root;i++){
				if(number % i == 0){
					if(isPrime(i) && isPrime(number / i)){
						return true;
					}
				}
			}
		}
			return false;	
	}
    
	/**
	 * Returns new vector with elements generated from the
	 * pq number sequence starting from the specified value.
	 */
	public static Vector pq(int length, long start) {
		Vector temp = new Vector(length);
		if(start < 4){
            start = 4;
        }
        boolean set = false;
        for(int i = 0; i < length; i++){
            set = false;
            while(set == false){
                if(isPQ(start) == true){
                    temp.elements[i] = start;
                    start++;
                    set = true;
                }else{
                    start++;
                }
            }
        }
		temp.minimum = temp.elements[0];
		temp.maximum = temp.elements[length - 1];
		temp.mode = (long)-1;
		return temp;
	}

	/**
	 * Returns whether the number is prime.
	 */
	public static boolean isPrime(long number) {
		if(number == 2){
			return true;
		}else if(number < 2){
			return false;
		}else if(number % 2 == 0){
			return false;
		}else{
			int root = (int)Math.sqrt(number) + 1;
			for(int i = 3; i < root;i += 2){
				if(number % i == 0 ){
					return false;
				}
			}
			return true;
		}
	}
        

	/**
	 * Returns new vector with elements generated from the
	 * prime number sequence starting from the specified value.
	 */
	public static Vector prime(int length, long start) {
		Vector temp = new Vector(length);
		int	count = 0;
		while(count < length){
			if(isPrime(start)){
				temp.elements[count] = start;
				count += 1;
			}
			start += 1;
		}
		temp.minimum = temp.elements[0];
		temp.maximum = temp.elements[length - 1];
		temp.mode = (long)-1;
		return temp;
	}

	/**
	 * Returns whether the number is abundant.
	 */
	public static boolean isAbundant(long number) {

		if(number < 12){
			return false;
		}else if(number == 12){
			return true;
		}else if(number % 6 == 0){
			return true;
		}else{
			long half = number /2;
			long i = 1;
			long sum = 0;
			while(half >= i){
				if(number % i == 0){
					sum += i;
				}
				i++;
			}
			if(sum > number){
				return true;
			}else{
				return false;
			}
		}
	}

	/**
	 * Returns new vector with elements generated from the
	 * abundant number sequence starting from the specified value.
	 */
	public static Vector abundant(int length, long start) {
      
	 
	   
	   	Vector temp = new Vector(length);
		if(start < 12){
            start = 12;
        }

		for(int i = 0; i < length; i++){
			while(isAbundant(start) == false){
				start++;
			}
			temp.elements[i] = start;
			start++;
		}
		temp.minimum = temp.elements[0];
		temp.maximum = temp.elements[length - 1];
		temp.mode = (long)-1;
        return temp;
	}

	/**
	 * Returns whether the number is composite.
	 */
	public static boolean isComposite(long number) {
		if(number < 4){
			return false;
		}else if (number % 2 == 0){
			return true;
		}else{
			if(isPrime(number)){
				return false;
			}else{
				return true;
			}
		}
	}

	/**
	 * Returns new vector with elements generated from the
	 * composite number sequence starting from the specified value.
	 */
	public static Vector composite(int length, long start) {
		
		 if(start < 4){
            start = 4;
        }
        Vector temp = new Vector(length);
		int count = 0;
		while(count < length){
			if(isComposite(start)){
				temp.elements[count] = start;
				count += 1;
			}
			start += 1;
		}
		//temp.minimum = temp.elements[0];
		//temp.maximum = temp.elements[length - 1];
		temp.mode = (long)-1;
		return temp;
	}
   

	// ===========================================================================
	// VECTOR OPERATIONS
	// ===========================================================================

	/**
	 * Returns new vector that is a copy of the current vector.
	 */
	protected Vector cloned(){
		Vector temp = new Vector(this.length);
		temp.maximum = this.maximum;
		temp.minimum = this.minimum;
		temp.mode = this.mode;
		temp.median = this.median;
		temp.sum = this.sum;
		for(int i = 0; i < temp.length; i++){
			temp.elements[i] = this.elements[i];
		}
		return temp;
	}

	/**
	 * Returns new vector with elements ordered from smallest to largest.
	 */
	public Vector sorted() {
		int len = this.length;
		Vector temp = this.cloned();
		Arrays.sort(temp.elements);
		return temp;
	}

	/**
	 * Returns new vector with elements ordered in reverse.
	 */
	public Vector reversed() {
		int len = this.length;
		long[] rev = new long[len];
		long temp_save;
		int last_index = len - 1;
		for(int i = 0; i < len; i++){
			rev[i] = this.elements[last_index - i];
		}
		Vector temp = this.cloned();
		for(int i = 0; i < len; i++){
			temp.elements[i] = rev[i];
		}
		return temp;
	}

	/**
	 * Returns new vector with elements shifted right by a given number of positions.
	 */
	public Vector shifted(long amount) {
		int movement = (int)amount % length;
		Vector temp = this.cloned();
		int lastIndex = this.length - 1;
		for(int i = 0; i < this.length; i++){
			if((i + movement) > lastIndex){
				temp.elements[(i + movement) - lastIndex - 1] = this.elements[i];
			}else{
				temp.elements[i + movement] = this.elements[i];
			}
		}
		return temp;
	}

	public Vector scalarAdd(long scalar) {
		
		Vector temp = new Vector(this.length);
		for(int i = 0; i < length;i++){
			temp.elements[i] = this.elements[i] + scalar;
		}
		return temp;
	}


	public Vector scalarMultiply(long scalar) {

		Vector temp = new Vector(this.length);
		for(int i = 0; i < length; i++){
			temp.elements[i] = this.elements[i] * scalar;
		}

		return temp;
	}

	/**
	 * Returns new vector, adding elements with the same index.
	 */
	public Vector vectorAdd(Vector other) {
		
		Vector temp = new Vector(this.length);
		for(int i = 0; i< this.length;i++){
			temp.elements[i] = this.elements[i] + other.elements[i];
		}
		return temp;
	}

	/**
	 * Returns new vector, multiplying elements with the same index.
	 */
	public Vector vectorMultiply(Vector other) {

		Vector temp = new Vector(this.length);
		for(int i = 0; i < temp.length; i++){
			temp.elements[i] = this.elements[i] * other.elements[i];
		}
		return temp;
		
	}

	// ===========================================================================
	// VECTOR COMPUTATIONS
	// ===========================================================================

	/**
	 * Returns the sum of all elements.
	 */
	public long getSum() {

		if (this.sum == null) {
			long s = 0;
			for(long e : this.elements){
				s += e;
			}
			return s;
		}
		return this.sum;
	}

	/**
	 * Returns the most frequently occuring element
	 * or -1 if there is no such unique element.
	 */
	public Long getMode() {
		
		if(this.mode == null){
			HashMap<Long,Integer> modeCount = new HashMap<Long,Integer>();
			for ( int i = 0; i < this.length;i++ ){
				if( modeCount.containsKey( this.elements[i] ) == false ){
					modeCount.put( this.elements[i], 1 );
				}else{
					int value = modeCount.get( this.elements[i] );
					modeCount.put( this.elements[i], value + 1 );
				}
			}
			Long modeNumber = null;
			int maxCount = 0;
			int anotherMaxCount = 0;
			int currentCount;
			int length = modeCount.size();
			for( Map.Entry<Long,Integer> i : modeCount.entrySet() ){
				currentCount = i.getValue();
				if( currentCount == maxCount ){
					anotherMaxCount = currentCount;	
				
				}else if( currentCount > maxCount ){
					maxCount = currentCount;
					modeNumber = i.getKey();
				}
			}
			if( maxCount > anotherMaxCount ){
				return modeNumber;
			}else{
				return -1l;
			}
		}
		return this.mode;
		
	}
	
	/**
	 * Returns the upper median.
	 */
	public Long getMedian() {

		if(this.median == null){
			Vector temp = this.sorted();
			if(this.length % 2 != 0){
				int index = (temp.length + 1)/ 2;
				this.median = temp.elements[index];
			}else{
				int index = (temp.length / 2);
				this.median = temp.elements[index];
			}
		}
		return this.median;
	}

	/**
	 * Returns the smallest value in the vector.
	 */
	public Long getMinimum() {
		if(this.minimum == null){
			this.minimum = this.elements[0];
			for(int i = 1; i < this.length; i++){
				if(this.minimum > this.elements[i]){
					this.minimum = this.elements[i];
				}
			}
			return this.minimum;
		}else{
			return this.minimum;
		}
	}

	/**
	 * Returns the largest value in the vector.
	 */
	public Long getMaximum() {
		if(this.maximum == null){
			this.maximum = this.elements[0];
			for(int i = 1; i < this.length; i++){
				if(this.maximum < this.elements[i]){
					this.maximum = this.elements[i];
				}
			}	
			return this.maximum;
		}else{
			return this.maximum;
		}
	}

	/**
	 * Returns the frequency of the value in the vector.
	 */
	public long getFrequency(long value) {
		int len = this.length;
		long frequency = 0;
		for(int i = 0; i < len;i++){
			if(this.elements[i] == value){
				frequency += 1;
			}
		}
		return frequency;
	}

	// ===========================================================================
	// DISPLAY OPERATIONS
	// ===========================================================================

	/**
	 * Displays the vector.
	 */
	public void display() {
		int length = this.length;
		for(int i = 0; i < length - 1; i++){
			System.out.print(this.elements[i] + " ");
		}
		System.out.println(this.elements[length - 1]);
		return;
	}

	/**
	 * Displays the element at the specified index.
	 */
	public void displayElement(int index) {
		System.out.println(this.elements[index]);
		return;
	}

	// ===========================================================================
	// ACCESSOR METHODS
	// ===========================================================================

	/**
	 * Returns the vector length.
	 */
	public int getLength() {

		return this.length;
	}

	/**
	 * Returns the vector elements.
	 */
	public long[] getElements() {

		return this.elements;
	}
	
	public static ArrayList<Integer> primeSeive(long number){
        ArrayList<Integer> primeSet = new ArrayList<Integer>();
        int N = (int)Math.sqrt(number);
        boolean[] isPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= N using Sieve of Eratosthenes
        for (int i = 2; i*i <= N; i++) {
            // if i is prime, then mark multiples of i as nonprime
            // suffices to consider mutiples i, i+1, ..., N/i
            if (isPrime[i]) {
                for (int j = i; i*j <= N; j++) {
                    isPrime[i*j] = false;
                }
            }
        }
        for(int i = 2; i < isPrime.length;i++){
            if(isPrime[i] == true){
                Integer Int = new Integer(i);
                primeSet.add(Int);
            }
        }
        return primeSet;
    }
}