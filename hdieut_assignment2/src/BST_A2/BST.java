package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

@Override
public boolean insert(String s) {
	BST_Node newNode = new BST_Node(s);
	
	if (root == null) {
		root = newNode;
		size++;
		return true;
	} else {
		if (root.insertNode(s) == true) {
			size++;
			return true; 
		} else {
			return false;
		}
	}
}

@Override
public boolean remove(String s) {
	if (root == null || !contains(s)) {
		return false;
	} else if (root.data == s) {
		BST_Node nullNode = new BST_Node(null);
		nullNode.left = root;
		boolean result = root.removeNode(s, nullNode);
		root = nullNode.left;
		size--;
		return result;
	} else {
		size--;
		return root.removeNode(s, null);
	}
}

@Override
public String findMin() {
	if (size() == 0) {
		return null;
	} else {
		return root.findMin(root);
	}
}

@Override
public String findMax() {
	if (size() == 0) {
		return null;
	} else {
		return root.findMax(root);
	}
}

@Override
public boolean empty() {
	if (root == null) {
		return true;
	} else {
		return false;
	}
	
}

@Override
public boolean contains(String s) {
	BST_Node newNode = new BST_Node(s);
	
	if (s == null) {
		return false;
	} else if (size() == 0) {
		return false;
	} else {
		return root.containsNode(newNode, root);
	}

}

@Override
public int size() {
	if (empty()) {
		return 0;
	} else {
		return root.getSize(root);
	}
	
}

@Override
public int height() {
	if (size() == 0) {
		return -1;
	} else if (root.data == null){
		return 0;
	} else {
		return root.getHeight();
	}
}

}
