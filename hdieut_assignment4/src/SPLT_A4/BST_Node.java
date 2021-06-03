package SPLT_A4;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node parent; // parent...not necessarily required, but can be useful in
	boolean justMade; // could be helpful if you change some of the return types

	BST_Node(String data) {
		this.data = data;
		this.justMade = true;
	}

	BST_Node(String data, BST_Node left, BST_Node right, BST_Node parent) { 
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
		this.justMade = true;
	}

	// --- used for testing ----------------------------------------------
	//
	// leave these 3 methods in, as is (meaning also make sure they do in fact
	// return data,left,right respectively)

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	// --- end used for testing -------------------------------------------

	public BST_Node splay(BST_Node toSplay) {
		while (toSplay.parent != null) {
			BST_Node parent = toSplay.parent;
			BST_Node grandparent = parent.parent;
			
			if (grandparent == null) {
				if (toSplay == parent.left) {
					//zig
					rotateRight(toSplay);
				} else {
					//zag
					rotateLeft(toSplay);
				}
			} else {
				if (parent.left == toSplay && parent == grandparent.left ) {
					//zig zig
					rotateRight(toSplay);
					rotateRight(toSplay);
				} else if (parent.right == toSplay && parent == grandparent.right) {
					//zag zag
					rotateLeft(toSplay);
					rotateLeft(toSplay);
				} else if (parent.right == toSplay && parent == grandparent.left) {
					//zag zig
					rotateLeft(toSplay);
					rotateRight(toSplay);
				} else if (parent.left == toSplay && parent == grandparent.right) {
					// zig zag
					rotateRight(toSplay);
					rotateLeft(toSplay);
				}
			}
		}
		return toSplay;
	}

	private BST_Node rotateLeft(BST_Node rotating) {
		BST_Node parent = rotating.parent;
		BST_Node grandparent = parent.parent;
		
		if (grandparent != null) {
			if (grandparent.left == parent) {
				grandparent.left = rotating;
			} else if(grandparent.right == parent){
				grandparent.right = rotating;
			}
		}
		parent.right = rotating.left;
		
		if (rotating.left != null) {
			parent.right.parent = parent;
		}
		
		rotating.left = parent;
		parent.parent = rotating;
		rotating.parent = grandparent;
		return rotating;

	}

	private BST_Node rotateRight(BST_Node rotating) {
		BST_Node parent = rotating.parent;
		BST_Node grandparent = parent.parent;
		
		if (grandparent != null) {
			if (grandparent.right == parent) {
				grandparent.right = rotating;
			} else if(grandparent.left == parent){
				grandparent.left = rotating;
			}
		}
		
		parent.left = rotating.right;
		
		if (rotating.right != null) {
			parent.left.parent = parent;
		}
		
		rotating.right = parent;
		parent.parent = rotating;
		rotating.parent = grandparent;
		return rotating;

	}

	public BST_Node containsNode(String s) {
		if (data.equals(s)) {
			return splay(this);
		}

		else if (data.compareTo(s) > 0) {// s lexiconically less than data
			if (left != null)
				return left.containsNode(s);
		} else if (data.compareTo(s) < 0) {
			if (right != null)
				return right.containsNode(s);
		}
		return splay(this); // shouldn't hit
	} 

	public BST_Node insertNode(String s) {
		justMade = !justMade;
		if (data.compareTo(s) > 0) {
			if (left == null) {
				left = new BST_Node(s, null, null, this);
				return splay(left);
			}
			return left.insertNode(s);
		}
		if (data.compareTo(s) < 0) {
			if (right == null) {
				right = new BST_Node(s, null, null, this);
				return splay(right);
			}
			return right.insertNode(s);
		}
		return splay(this);
	} 

	public boolean removeNode(String s){ //DIO
		if(data==null)return false;
		if(data.equals(s)){
			if(left!=null){
				data=left.findMax().data;
				left.removeNode(data);
				if(left.data==null)left=null;
			}
			else if(right!=null){
				data=right.findMin().data;
				right.removeNode(data);
				if(right.data==null)right=null;
			}
			else data=null;
			return true;
		}
		else if(data.compareTo(s)>0){
			if(left==null)return false;
			if(!left.removeNode(s))return false;
			if(left.data==null)left=null;
			return true;
		}
		else if(data.compareTo(s)<0){
			if(right==null)return false;
			if(!right.removeNode(s))return false;
			if(right.data==null)right=null;
			return true;
		}
		return false;
	}

	public BST_Node findMin() {
		if (left != null)
			return left.findMin();
		else
			return splay(this);
	}

	public BST_Node findMax() {
		if (right != null)
			return right.findMax();
		else
			return splay(this);
	}

	public int getHeight() {
		int l = 0;
		int r = 0;
		if (left != null)
			l += left.getHeight() + 1;
		if (right != null)
			r += right.getHeight() + 1;
		return Integer.max(l, r);
	}
	
	public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")+",Right: "+((this.right!=null)?right.data:"null");
	}

}