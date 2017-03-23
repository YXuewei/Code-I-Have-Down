


public class BstSimpleSortedMap implements SimpleSortedMap {
	
	private class MySimpleEntry implements SimpleEntry {
		
		private final Integer key;
		private  final String value;
		private MySimpleEntry left;
		private MySimpleEntry right;
		private MySimpleEntry parent;
		
		MySimpleEntry(Integer key, String value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
			this.parent = null;
		}

		@Override
		public Integer getKey() { return key; }

		@Override
		public String getValue() { return value; }
		
		protected MySimpleEntry getLeft() { return left; }
		protected MySimpleEntry getRight() { return right; }
		protected MySimpleEntry getParent() { return parent; }

		protected void setLeft(MySimpleEntry entry) { left = entry; }
		protected void setRight(MySimpleEntry entry) { right = entry; }
		protected void setParent(MySimpleEntry entry) { parent = entry; }
	}

	private int size;
	private MySimpleEntry root;
	
	public BstSimpleSortedMap() {
		size = 0;
		root = null;
	}
	
	// attaches the subtree rooted at 'child', to the parent
	private void attachLeft(MySimpleEntry parent, MySimpleEntry child) {
		if(child != null) { child.setParent(parent); }
		if(parent != null) { parent.setLeft(child); }
	}

	// attaches the subtree rooted at 'child', to the parent
	private void attachRight(MySimpleEntry parent, MySimpleEntry child) {
		if(child != null) { child.setParent(parent); }
		if(parent != null) { parent.setRight(child); }
	}

	//////////////////////////
	// Map ADT methods:
	//////////////////////////
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public String get(Integer k) {
		return get(k, root);
	}
	
	private String get(Integer k, MySimpleEntry subtreeRoot) {
		// base case: empty subtree
		if(subtreeRoot == null) {
			// k isn't in this subtree
			return null;
		}
		
		// base case: k matches the key in the current entry
		if(k.compareTo(subtreeRoot.getKey()) == 0) {
			return subtreeRoot.getValue();
		}
		// recursive case: k < the current entry
		else if(k.compareTo(subtreeRoot.getKey()) < 0) {
			return get( k, subtreeRoot.getLeft() );
		}
		// recursive case: k > the current entry
		else {
			return get( k, subtreeRoot.getRight() );
		}
	}

	@Override
	public String put(Integer k, String v) {
		// We will implement this recursively.
		
		// If the key already exists, we will need to return the old value
		String oldValue = get(k);
		
		// Replace the subtree rooted at 'root' with
		// the resulting subtree after doing the put
		root = put(k, v, root);
		
		return oldValue;
	}
	
	// Recursive helper method for put
	// Returns the subtree rooted at entry after performing the put
	private MySimpleEntry put(Integer k, String v, MySimpleEntry subtreeRoot) {
		// base case: the key wasn't in the subtree
		if(subtreeRoot == null) {
			// we have reached a null subtree, where k should be
			// create a new entry
			//increment the size variable
			//return the new entry
			MySimpleEntry newEntry = new MySimpleEntry( k, v );
			size++;
			return newEntry;
		}

		// base case: k matches the one in the current entry
		if(k.compareTo(subtreeRoot.getKey()) == 0) {
			// create a new entry
			// attach the left child of the current entry to it			
			// attach the right child of the current entry to it			
			// return the new subtree
			MySimpleEntry newEntry = new MySimpleEntry( k, v );
			//newEntry.setParent( subtreeRoot.getParent() );
			newEntry.setLeft( subtreeRoot.getLeft() );
			newEntry.setRight( subtreeRoot.getRight() );
			return newEntry;
		}
		// recursive case: k < the current entry
		else if(k.compareTo(subtreeRoot.getKey()) < 0) {
			// get the subtree resulting from recursing left
			// attach that subtree to the current entry
			// return the modified entry
			MySimpleEntry updated =  put( k, v, subtreeRoot.getLeft() );
			subtreeRoot.setLeft( updated );
			return subtreeRoot;
		}
		// recursive case: k > the current entry
		else {
			// get the subtree resulting from recursing right
			// attach that subtree to the current entry
			// return the modified entry
			MySimpleEntry updated =  put( k, v, subtreeRoot.getRight() );
			subtreeRoot.setRight( updated );
			return subtreeRoot;
		}
	}	

	@Override
	public String remove(Integer k) {
		size--;
		String oldValue = get( k );
		root = remove( k, root );	
		return oldValue;	
	}

	public MySimpleEntry remove( Integer k, MySimpleEntry root ){
		if ( root == null ){
			return null;
		}

		if ( k.compareTo( root.getKey() ) == 0 ){
			if ( root.getLeft() == null ){
				return root.getRight();
			}else if ( root.getRight() == null ){
				return root.getLeft();
			}else{
				MySimpleEntry p = root;
				root = getSucc( p.getRight() );
				root.right = removeMin( p.right );
				root.left = p.left;	
			}
		}else if ( k.compareTo( root.getKey() ) < 0 ){
			root.left = remove( k, root.getLeft() );
		}else if ( k.compareTo( root.getKey() ) > 0 ){
			root.right = remove( k, root.getRight() );
		}

		return root;
	}

	public MySimpleEntry getSucc( MySimpleEntry root ){
		if ( root.getLeft() == null ){
			return root;
		}
		return getSucc( root.getLeft() );
	}

	public MySimpleEntry removeMin( MySimpleEntry root ){
		if ( root.getLeft() == null ){
			return root.getRight();
		}else{
			root.left = removeMin( root.left );
			return root;
		}
	}

	public Integer minValue( MySimpleEntry root ){
		Integer minv = root.getKey();
		root = root.getLeft();
		while( root.getRight() != null ){
			minv = root.getRight().getKey();
			root = root.getRight();
		}

		return minv;
	}
	public MySimpleEntry search( Integer k, MySimpleEntry n ){
		if ( k.compareTo( n.getKey()) < 0 ){
			return search( k, n.getLeft() );
		}else if ( k.compareTo( n.getKey()) == 0 ){
			return n;
		}else if ( k.compareTo( n.getKey()) > 0 ){
			return search( k, n.getRight() );
		}

		return null;
	}

	@Override
	public Iterable<Integer> keySet() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Iterable<String> values() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Iterable<SimpleEntry> entrySet() {
		throw new java.lang.UnsupportedOperationException();
	}

	//////////////////////////
	// SortedMap ADT methods:
	//////////////////////////
	
	@Override
	public SimpleEntry firstEntry() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SimpleEntry lastEntry() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SimpleEntry ceilingEntry(Integer key) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SimpleEntry floorEntry(Integer key) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SimpleEntry lowerEntry(Integer key) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SimpleEntry higherEntry(Integer key) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Iterable<Integer> subMap(Integer fromKey, Integer toKey) {
		throw new java.lang.UnsupportedOperationException();
	}
	
	
	
	

}
