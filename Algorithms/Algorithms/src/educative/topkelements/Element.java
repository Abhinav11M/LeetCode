package educative.topkelements;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Element implements Comparable<Element> {
	public int value;
	public int frequency;
	public int sequence;

	@Override
	public int compareTo(Element o) {
		int retVal = o.frequency - this.frequency;
		if(retVal == 0) {
			return o.sequence - this.sequence;
		}
		return retVal;
	}
}
