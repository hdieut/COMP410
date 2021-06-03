package DiGraph_A5;

public class Edge {

String sourceL;
String dLabel;
String eLabel;
long idNum;
long weight;
	  
public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	this.idNum=idNum;
	this.sourceL = sLabel;
	this.dLabel = dLabel;
	this.weight = weight;
	this.eLabel = eLabel;
}

public String getSourceL() {
	return this.sourceL;
}
	
public void setSourceL(String sourceL) {
	this.sourceL = sourceL;
}
	  
public String getDestL() {
	return this.dLabel;
}
	  
public void setDestL(String destiL) {
	this.dLabel = destiL;
}
	  
public String getELabel() {
	return this.eLabel;
}
	  
public void setELabel(String elabel) {
	this.eLabel = elabel;
}
	  
public long getEId() {
	return this.idNum;
}
	  
public void setEId(long idNum) {
	this.idNum = idNum;
}
	  
public long getWeight() {
	return this.weight;
}
	  
public void setWeight(long weight) {
	this.weight = weight;
}
	
}
