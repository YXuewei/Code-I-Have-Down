import java.util.*;

public class IntegerHashMap<V> implements Map<Integer, V> {
	
	private class HashMapEntry implements Entry<Integer, V> {
		
		private Integer key;
		private V value;
		
		public HashMapEntry(Integer key, V value) {
			this.key = key;
            this.value = value;
		}

		@Override
		public Integer getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
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
		return this.numberOfItems;
	}

	@Override
	public boolean isEmpty() {
        if ( numberOfItems == 0 ){
            return true;
        }

        return false;
	}

	@Override
	public V get(Integer key) {
		int index = compress( hash( key ) );
        if ( items.length - 1 >= index ){
            if ( items[ index ] != null ){
                if ( items[ index ].getKey() == key ){
                	return items[ index ].getValue();
				}
            }
        }
		return null;
	}

	@Override
	public V put(Integer key, V value) {
	   int index = compress( hash( key ) );
       if( items[ index ] != null ){
		   if( items[ index ].getKey() != key ){
        		throw new IllegalArgumentException("Key conflict");
		   }
       }
       if( items[ index ] == null ){
            numberOfItems++;
            items[index] = new HashMapEntry(key, value);
            return null;
       }
        V oldValue = items[ index ].getValue(); 
      	items[index] = new HashMapEntry(key, value);
        return oldValue;
    }

	@Override
	public V remove(Integer key) {
		int index = compress( hash( key ) );
		if ( items.length - 1 >= index ){
			if ( items[index] != null ){
				if ( items[ index ].getKey() == key ){
					V oldValue = items[ index ].getValue();	
					items[ index ] = null;
					numberOfItems--;
					return oldValue;
				}
			}
		}
		return null;
	}

	@Override
	public Iterable<Integer> keySet() {
		ArrayList< Integer> list = new ArrayList< Integer >();
		for ( int i = 0; i < items.length; i++ ){
			if( items[ i ] != null ){
				list.add( items[ i ].getKey() );
			}
		}
		return list;
	}

	@Override
	public Iterable<V> values() {
		ArrayList< V > list = new ArrayList< V >();
		for ( int i = 0; i < items.length; i++ ){
			if ( items[ i ] != null ){
				list.add( items[ i ].getValue() );
			}
		}	
		return list;
	}

	@Override
	public Iterable<Entry<Integer, V>> entrySet() {
		ArrayList< Entry<Integer,V> > list = new ArrayList< Entry<Integer, V>>();
		for ( int i = 0; i < items.length; i++ ){
			if ( items[ i ] != null ){
				list.add( items[ i ] );
			}
		}	
		return list;
	}
}