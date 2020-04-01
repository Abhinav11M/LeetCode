package educative.twoheaps;

import java.util.List;

import helpers.Helpers;

public class TwoHeapsMain {
	public static void main(String[] args) {
		MedianHeap medianHeap = new MedianHeap();
		medianHeap.add(3);
		medianHeap.add(1);
		System.out.println(medianHeap.getMedian());
		medianHeap.add(5);
		System.out.println(medianHeap.getMedian());
		medianHeap.add(4);
		System.out.println(medianHeap.getMedian());
		
		TwoHeaps twoHeaps = new TwoHeaps();
		List<Double> res = twoHeaps.slidingWindowMedian(new int[] {1, 2, -1, 3, 5}, 2);
		System.out.println(res);
		res = twoHeaps.slidingWindowMedian(new int[] {1, 2, -1, 3, 5}, 3);
		System.out.println(res);
		
		System.out.println("**********");
		
		System.out.println(twoHeaps.findMaximumCapital(new int[] { 0, 1, 2 }, new int[] { 1, 2, 3 }, 2, 1));
		System.out.println(twoHeaps.findMaximumCapital(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0));
		System.out.println(twoHeaps.findMaximumCapital1(new int[] { 0, 1, 2 }, new int[] { 1, 2, 3 }, 2, 1));
		System.out.println(twoHeaps.findMaximumCapital1(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0));
		
		Interval[] intervals = new Interval[] { new Interval(2, 3), new Interval(3, 4), new Interval(5, 6) };
		int[] resArr = twoHeaps.findNextInterval(intervals);
		Helpers.printArr(resArr);

		intervals = new Interval[] { new Interval(3, 4), new Interval(1, 5), new Interval(4, 6) };
		resArr = twoHeaps.findNextInterval(intervals);
		Helpers.printArr(resArr);
	}
}
