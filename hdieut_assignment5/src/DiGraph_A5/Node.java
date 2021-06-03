package DiGraph_A5;

import java.util.HashMap;

public class Node {

long idNum;
String label;
HashMap<String, Edge> inEdge;
HashMap<String, Edge> outEdge;
int inDegree;
int flag;
long dist;
	
  
  
public Node(long idNum, String label) {
    this.idNum = idNum;
    this.label = label;
    this.inEdge = new HashMap();
    this.outEdge = new HashMap();
    this.inDegree = 0;
    this.flag = 0;
}
  
public String getLabel() {
    return this.label;
 }
  
public long getID() {
    return this.idNum;
}
  
public String toString() {
	return "Node: id: "+this.idNum+"  lable:"+this.label;
}
}