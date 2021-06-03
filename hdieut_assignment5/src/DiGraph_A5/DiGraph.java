package DiGraph_A5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
	HashMap<String, Node> nodeMap = new HashMap<String, Node>();  //key:label, value: Node
	HashSet<Long> nodeId = new HashSet<Long>();//store all nodes' id
	HashSet<Long> edgeId = new HashSet<Long>();//store all edges' id


  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
  }

@Override
public boolean addNode(long idNum, String name) {
	 if (idNum < 0 || name == null) {
		  return false;
	 } else if(nodeMap.containsKey(name) == false && nodeId.contains(idNum) == false) {
		  Node newNode = new Node(idNum, name);
		  nodeMap.put(name, newNode);
		  nodeId.add(idNum);
		  return true; 
	} else {
		  return false;
	}
		 		
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	  if(idNum < 0 || edgeId.contains(idNum) == true) {
		  return false;
	  } else if (nodeMap.containsKey(sLabel) == false || nodeMap.containsKey(dLabel) == false) {
		  return false;
	  } else  {
		  Node sourceNode = nodeMap.get(sLabel);
		  Node destiNode  = nodeMap.get(dLabel);
		  if (sourceNode.outEdge.containsKey(dLabel) && destiNode.inEdge.containsKey(sLabel)) {
			  return false;
		  } else {
			  Edge newEdge = new Edge(idNum,sLabel,dLabel,weight,eLabel);
			  edgeId.add(idNum);
			  sourceNode.outEdge.put(dLabel, newEdge);
			  destiNode.inEdge.put(sLabel, newEdge);
			  destiNode.inDegree+=1;		  
			  return true;
		  }
		 
	  }
}

@Override
public boolean delNode(String label) {
	  if(nodeMap.containsKey(label) == false) {
		  return false;
	  } else {
		  Node remNode = nodeMap.get(label);
		
		  Iterator<String> InEdge_sL = remNode.inEdge.keySet().iterator();
		  Iterator<String> OutEdge_dL = remNode.outEdge.keySet().iterator();
		  
		  while (InEdge_sL.hasNext()) {
			  delEdge(InEdge_sL.next(),remNode.label);
		  }
		  while (OutEdge_dL.hasNext()) {
			  delEdge(remNode.label,OutEdge_dL.next());
		  }
			  
		  nodeMap.remove(label,remNode);
		  nodeId.remove(remNode.idNum);
		  return true;
	  } 
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	  if (nodeMap.containsKey(sLabel)==false || nodeMap.containsKey(dLabel)==false) {
		  return false;
	  } else {
		  Node sourceNode = nodeMap.get(sLabel);
		  Node destiNode = nodeMap.get(dLabel);
		  if (sourceNode.outEdge.containsKey(dLabel)) {
			  Edge remEdge = nodeMap.get(sLabel).outEdge.get(dLabel);
			  sourceNode.outEdge.remove(dLabel,remEdge);
		      destiNode.inEdge.remove(sLabel,remEdge);
		      destiNode.inDegree-= 1;  
			  edgeId.remove(remEdge.idNum);
			  return true;
		  } else {
			  return false;
		  }
	  }
}

@Override
public long numNodes() {
	 return nodeId.size();
}

@Override
public long numEdges() {
	return edgeId.size();
}
  
  // rest of your code to implement the various operations
public String[] topoSort() {
	   Queue<Node> zeroNode = new LinkedList<Node>();
	   if (nodeMap.isEmpty()) {
		   return null;
	   }

	   ArrayList<String> topoString = new ArrayList<String>();
	   for (Node node:nodeMap.values()) {
		   if (node.inDegree==0 ) {
			   zeroNode.add(node);
		   } 
	   }
	  
	   while (zeroNode.isEmpty() == false) {
		   Node deqNode = zeroNode.poll();		   
		   topoString.add(deqNode.label);
		   Iterator<Edge> OutEdge = deqNode.outEdge.values().iterator();
		   while (OutEdge.hasNext()) {			
				Node destiNode = nodeMap.get(OutEdge.next().dLabel);
				destiNode.inDegree-= 1;
				if (destiNode.inDegree == 0 ) {
					zeroNode.add(destiNode);
				}
			}	   
	   }
	
	   if (nodeMap.size() == topoString.size()) {
		   return topoString.toArray( new String[topoString.size()]);
	   } else {
		   return null; 
	   }
}

public ShortestPathInfo[] shortestPath(String label) {
	int size = nodeId.size();
	ShortestPathInfo[] shortestPath = new ShortestPathInfo[size];
	MinBinHeap priority = new MinBinHeap();
	EntryPair node = new EntryPair(label, 0);
	priority.insert(node);
	shortestPath[0] = new ShortestPathInfo(label, 0);
	int x = 0;
	
	while (priority.size() != 0) {
		Node currentNode = nodeMap.get(priority.getMin().value);
		long currentDist = priority.getMin().priority;
		priority.delMin();
		if (currentNode.flag == 1) {
			shortestPath[x] = new ShortestPathInfo(currentNode.label, currentDist);
			currentNode.flag = 1;
		}
		Iterator<Edge> adjNode = currentNode.outEdge.values().iterator();
		while (adjNode.hasNext()) {
			Node node1 = nodeMap.get(adjNode.next().dLabel);
			if (node1.flag == 0) {
				long newDist = currentDist + currentNode.outEdge.get(node1.label).weight;
				if (node1.dist == 0 || node1.dist > newDist) {
					node1.dist = newDist;
					EntryPair y = new EntryPair(node1.label, node1.dist);
					priority.insert(y);
				}
			}
		}
		x++;
	}

	return shortestPath;
}



}
