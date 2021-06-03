package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
 private  Node sentinel; //this will be the entry point to your linked list (the head)
 private int numElts; 
 
  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    sentinel=new Node(0); //Note that the root's data is not a true part of your data set!
    numElts = 0;
  }
  
  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return sentinel;
  }

@Override
public boolean insert(double elt, int index) {
	
	int size = size();
	if (index > size) {
		return false;
	}
	Node newNode = new Node(elt);

	if (index == 1) {
		sentinel.next = newNode;
		numElts++;
		return true;
	} else {
		Node previous = sentinel;
		Node current = sentinel;
		while (numElts != index) {
			previous.next = current.getNext();
			numElts++;
		}
			
		newNode.prev = previous;
		newNode.next = previous.next;
		
		return true;
	}
}

@Override
public boolean remove(int index) {
	
	int size = size();
	Node nullNode = null;
	
	if (index > size || index < 1) {
		return false;
	}
	
	if (index == 0) {
		sentinel = nullNode;
		numElts--;
	}
	
	if (index == 1) {
		Node tempNode = sentinel;
		tempNode.next = nullNode;
		numElts--;
		return true;
	} else {
		Node previous = sentinel;
		
		while (numElts < index - 1) {
			previous.next = previous.getNext();
			numElts++;
		}
		
		numElts--;
		Node current = previous.getNext();
		current.next = nullNode;
		return true;
	}
	
}

@Override
public double get(int index) {
	Node previous = sentinel;
	int size = size();
	int position = 0;
	
	if (index > size || index < 1) {
		return Double.NaN;
	} else {
		while (position != index) {
			previous = previous.getNext();
			position++;
		}
		
		return previous.getData(); 
	}
}

@Override
public int size() {
	return numElts;
}

@Override
public boolean isEmpty() {
	return size() == 0;
}

@Override
public void clear() {
	int count = 0;
	while (sentinel.getNext() != null) {
		remove(count);
		count++;
	}
	
}
}
