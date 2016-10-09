public class IntegerHashMap<V> implements Map<Integer, V> {
	
	private class HashMapEntry implements Entry<Integer, V> {
		
		private Integer key;
		private V value;
		
		public HashMapEntry(Integer key, V value) {
			// TODO
		}

		@Override
		public Integer getKey() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	private HashMapEntry[] items;
	private int numberOfItems;
	
	@SuppressWarnings("unchecked")	
	public IntegerHashMap(int capacity) {
		items = (IntegerHashMap<V>.HashMapEntry[]) new IntegerHashMap.HashMapEntry[capacity];
		this.numberOfItems = 0;
	}
	
	private int hash(Integer key) {
		return key;
	}
	
	private int compress(int hash) {
		return Math.abs(hash) % items.length;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(Integer key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Integer> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<Integer, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}