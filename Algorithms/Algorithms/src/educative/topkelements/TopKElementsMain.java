package educative.topkelements;

public class TopKElementsMain {

	public static void main(String[] args) {
		TopKElements topKElements = new TopKElements();
		System.out.println(topKElements.findKLargestNumbers(new int[] {3, 1, 5, 12, 2, 11}, 3));
		System.out.println(topKElements.findKthSmallestNumber(new int[] {3, 21, 5, 12, 2, 11}, 3));
		
		System.out.println(topKElements.findClosestPoints(new Point[] {
				new Point(1,2),
				new Point(1,3)
		}, 1));
		
		System.out.println(topKElements.findClosestPoints(new Point[] {
				new Point(1,3),
				new Point(3,4),
				new Point(2,-1)
		}, 2));
		
		System.out.println(topKElements.minimumCostToConnectRopes(new int[] {1, 3, 11, 5}));
		System.out.println(topKElements.minimumCostToConnectRopes(new int[] {3, 4, 5, 6}));

		System.out.println(topKElements.findTopKFrequentNumbers(new int[] {1, 3, 5, 12, 11, 12, 11}, 2));
		System.out.println(topKElements.findTopKFrequentNumbers(new int[] {5, 12, 11, 3, 11}, 2));

		System.out.println(topKElements.sortCharacterByFrequency("Programming"));
		
		int[] input = new int[] { 3, 1, 5, 12, 2, 11 };
		KthLargestNumberInStream kthLargestNumber = new KthLargestNumberInStream(input, 4);
	    System.out.println("4th largest number is: " + kthLargestNumber.add(6));
	    System.out.println("4th largest number is: " + kthLargestNumber.add(13));
	    System.out.println("4th largest number is: " + kthLargestNumber.add(4));
	    
	    System.out.println("**************");
	    System.out.println(topKElements.findClosestElements(new int[] {5, 6, 7, 8, 9}, 3, 7));
	    System.out.println(topKElements.findClosestElements1(new int[] {5, 6, 7, 8, 9}, 3, 7));
	    System.out.println(topKElements.findClosestElements(new int[] {2, 4, 5, 6, 9}, 3, 6));
	    System.out.println(topKElements.findClosestElements1(new int[] {2, 4, 5, 6, 9}, 3, 6));
	    System.out.println(topKElements.findClosestElements(new int[] {2, 4, 5, 6, 9}, 3, 10));
	    System.out.println(topKElements.findClosestElements1(new int[] {2, 4, 5, 6, 9}, 3, 10));
	    System.out.println(topKElements.findClosestElements(new int[] {1, 3, 4, 6, 9, 10}, 3, 4));
	    System.out.println(topKElements.findClosestElements1(new int[] {1, 3, 4, 6, 9, 10}, 3, 4));
	    
	    System.out.println(topKElements.findMaximumDistinctElements(new int[] {7, 3, 5, 8, 5, 3, 3}, 2));
	    System.out.println(topKElements.findMaximumDistinctElements(new int[] {3, 5, 12, 11, 12}, 3));
	    System.out.println(topKElements.findMaximumDistinctElements(new int[] {1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5}, 2));

	    System.out.println(topKElements.findSumOfElements(new int[] {1, 3, 12, 5, 15, 11}, 3, 6));
	    System.out.println(topKElements.findSumOfElements(new int[] {3, 5, 8, 7}, 1, 4));
	    System.out.println(topKElements.findSumOfElements1(new int[] {1, 3, 12, 5, 15, 11}, 3, 6));
	    System.out.println(topKElements.findSumOfElements1(new int[] {3, 5, 8, 7}, 1, 4));

	    System.out.println(topKElements.rearrangeString("aappp"));
	    System.out.println(topKElements.rearrangeString("Programming"));
	    System.out.println(topKElements.rearrangeString("aapa"));
	    
	    System.out.println("***************");
	    System.out.println(topKElements.reorganizeString("mmpp", 2));
	    System.out.println(topKElements.reorganizeString("Programming", 3));
	    System.out.println(topKElements.reorganizeString("aab", 2));
	    System.out.println(topKElements.reorganizeString("aappa", 3));

	    System.out.println(topKElements.scheduleTasks2(new char[] {'a', 'a', 'a', 'b', 'c', 'c'}, 2));
	    System.out.println(topKElements.scheduleTasks2(new char[] {'a', 'b', 'a'}, 3));
	    System.out.println(topKElements.scheduleTasks2(new char[] {'a', 'a', 'a'}, 3));

	    System.out.println("***************");
	    
	    CustomStack frequencyStack = new CustomStack();
	    frequencyStack.push(1);
	    frequencyStack.push(2);
	    frequencyStack.push(3);
	    frequencyStack.push(2);
	    frequencyStack.push(1);
	    frequencyStack.push(2);
	    frequencyStack.push(5);
	    System.out.println(frequencyStack.pop());
	    System.out.println(frequencyStack.pop());
	    System.out.println(frequencyStack.pop());
	}
}
