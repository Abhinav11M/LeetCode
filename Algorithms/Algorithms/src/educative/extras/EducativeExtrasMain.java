package educative.extras;

import java.util.List;

public class EducativeExtrasMain {
	public static void main(String[] args) {
		List<List<Integer>> res = EducativeExtras.findSubarrays(new int[] {2, 5, 3, 10}, 30);
		System.out.println(res);
	}
}
