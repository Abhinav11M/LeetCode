package programcreek.algolist;

public class Element {
	public int val; // Value of the current node
	public int min; // Minimum value till the chain
	public Element next; // Link to the next node
	
	public Element(int val, int min) {
		this.val = val;
		this.min = min;
	}
}
