import java.util.List;
import java.util.Map;

public class Lab11 implements PrefixMap {
	
	private Map<String, String> keys;
	private Map<String, List<String>> prefixes;

	/*
	 * The default constructor will be called by the tests on Ed
	 */
	public Lab11() {
		// TODO Initialise your maps
	}

	@Override
	public int size() {
		// TODO Implement this, then remove this comment
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Implement this, then remove this comment
		return false;
	}

	@Override
	public String get(String key) {
		// TODO Implement this, then remove this comment
		return null;
	}

	@Override
	public String put(String key, String value) {
		// TODO Implement this, then remove this comment
		return null;
	}

	@Override
	public String remove(String key) {
		// TODO Implement this, then remove this comment
		return null;
	}

	@Override
	public int countKeysMatchingPrefix(String prefix) {
		// TODO Implement this, then remove this comment
		return 0;
	}

	@Override
	public List<String> getKeysMatchingPrefix(String prefix) {
		// TODO Implement this, then remove this comment
		return null;
	}

	@Override
	public int countPrefixes() {
		// TODO Implement this, then remove this comment
		return 0;
	}

	@Override
	public int sumKeyLengths() {
		// TODO Implement this, then remove this comment
		return 0;
	}
	
}