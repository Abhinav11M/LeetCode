package educative.twoheaps;

public class Interval {
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "Interval [start=" + start + ", end=" + end + "]";
	}

	public int start;
	public int end;
}
