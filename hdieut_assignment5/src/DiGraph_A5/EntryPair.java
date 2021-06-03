package DiGraph_A5;

public class EntryPair implements EntryPairInterface{
	public String value;
	public long priority;
	
	public EntryPair(String val, long dist) {
		value = val;
		priority = dist;
	}
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public long getPriority() {
		return priority;
	}

}
