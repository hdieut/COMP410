package BST_A2;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  int size = 0;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
  public boolean containsNode(BST_Node newNode, BST_Node ogNode){
	  if (ogNode == null) {
		  return false;
	  } else {
		  int compare = newNode.data.compareTo(ogNode.data);
		  if (compare == 0) {
			  return true;
		  } else if (compare < 0) {
			  return containsNode(newNode, ogNode.left);
		  } else {
			  return containsNode(newNode, ogNode.right);
		  }
	  }
	  }
  
  public boolean insertNode(String s){ 
	  BST_Node newNode = new BST_Node(s);
	  if (data == s) {
		return false;
	  } else if (data.compareTo(s) == 0) {
		  return false;
	  } else if (data.compareTo(s) > 0) {
		 if (left == null) {
			 left = newNode;
			 size++;
			 return true;
		 } else {
			 return left.insertNode(s);
		 }
		 
	  } else {
		  if (right == null) {
			  right = newNode;
			  size++;
			  return true;
		  }
		  else {
			  return right.insertNode(s);
		  }
	  }
	  }
  public boolean removeNode(String s, BST_Node node){ 
	 int compare = s.compareTo(data);
	 
	 if (compare < 0) {
		 if(left == null) {
			 return false;
		 } else {
			 return left.removeNode(s, this);
		 }
	 } else if (compare > 0) {
		 if (right == null) {
			 return false;
		 } else {
			 return right.removeNode(s, this);
		 }
	 } else {
		 if (left != null && right != null) {
			 data = right.findMin(right);
			 right.removeNode(data, this);
			 return true;
		 } else if (left != null && right == null) {
			 if (node.left != null && node.left == this) {
				 node.left = left;
				 return true;
			 } else {
				 node.right = left;
				 return true;
			 } 
		 } else if (right != null && left == null) {
			 if (node.left != null && node.left == this) {
				 node.left = right;
				 return true;
			 } else {
				 node.right = right;
				 return true;
			 }
		 }
	 } if (left == null && right == null) {
		 if (node.left != null && node.left == this) {
			 node.left = null;
			 return true;
		 } else {
			 node.right = null;
			 return true;
		 }
	 }
	 return false;
  }
  public String findMin(BST_Node minNode){ 
	  while(minNode.left != null) {
		  minNode = minNode.left;
	  }
	  return minNode.data; }
  
  public String findMax(BST_Node maxNode){ 
	  while (maxNode.right !=null) {
		  maxNode = maxNode.right;
	  }
	  return maxNode.data; }
  
  public int getMaxHeight(int left, int right) {
	  
	  if (right > left) {
		  return right;
	  } else {
		  return left;
	  }
	  
  }
  
 
  public int getHeight(){ 
		 int heightL = 0;
		 int heightR = 0;
		 if (left != null) {
			 heightL = left.getHeight() + 1;
		 }
		 if (right != null) {
			 heightR = right.getHeight() + 1;
		 }
		 
		 return Math.max(heightL, heightR);
	}
  
  public int getSize(BST_Node node) {
	  if (node == null) {
		  return 0;
	  } else {
		  return 1 + getSize(node.left) + getSize(node.right);
	  }
  }
 

  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}
