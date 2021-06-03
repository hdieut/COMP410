package DiGraph_A5;

public interface HeapInterface {

	EntryPair[] getHeap();
	void insert(EntryPair entry);
	void delMin();
	EntryPair getMin();
	void build(EntryPair[] entries);
	int size();
}
