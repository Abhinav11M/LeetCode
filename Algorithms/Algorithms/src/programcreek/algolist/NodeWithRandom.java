package programcreek.algolist;

public class NodeWithRandom {
	public NodeWithRandom next;
	public NodeWithRandom random;
	public int val;
	
	public NodeWithRandom(int v) {
		val = v;
	}
	
	@Override
	public String toString() {
		String strNext = next != null ? Integer.toString(next.val) : "null";
		String strRand = random != null ? Integer.toString(random.val) : "null";
		
		return "[Val : " + val + ", Next : " + strNext + ", Random : " + strRand + "]";
	}
}
