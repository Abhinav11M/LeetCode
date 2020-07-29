package programcreek.algolist;

import java.util.Arrays;

import datastructures.ListNode;
import helpers.Helpers;

public class ProgCreekAlgosMain {

	public static void main(String[] args) {
		ProgCreekAlgos algos = new ProgCreekAlgos();
		Helpers.printArr(algos.twoSum(new int[] {2,7,11,15}, 9));
		Helpers.printArr(algos.twoSum(new int[] {9,6,2,8}, 14));

		Helpers.printArr(algos.twoSum2(new int[] {1,2,3,5,7}, 8));
		Helpers.printArr(algos.twoSum2(new int[] {2,5,8,12,15}, 27));
		
		TwoSum twoSum = new TwoSum();
		twoSum.add(1);
		twoSum.add(4);
		twoSum.add(1);
		twoSum.add(5);
		System.out.println(twoSum.find(2));
		System.out.println(twoSum.find(9));
		System.out.println(twoSum.find(3));
		
		System.out.println(algos.threeSum(new int[] {-1,0,1,2,-1,-4}));
		System.out.println(algos.minSubArrayLen(7, new int[] {2,3,1,2,4,3}));
		System.out.println(algos.minSubArrayLen(7, new int[] {2,3,3,1,6,2,4}));

		System.out.println(algos.removeDuplicates(new int[] {1,2,2,3,4,4,5,6,6}));
		System.out.println(algos.removeDuplicates(new int[] {1,1,1,1,1,1,2}));

		System.out.println(algos.removeDuplicates2(new int[] {1,1,1,1,1,1,2}));
		System.out.println(algos.removeDuplicates2(new int[] {1,2,2,2,4,4}));

		System.out.println(algos.removeElement(new int[] {1,2,2,4,4,2,5,6,2,1}, 2));

		int[] arr = new int[] {0,1,0,0,2,0,0,3,0,0,0,5,0};
		algos.moveZeroes(arr);
		Helpers.printArr(arr);
		
		System.out.println(algos.maxArea(new int[] {1,2,1,3,2,1,1,1}));

		System.out.println(algos.trappingWater(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));

		System.out.println(algos.summaryRanges(new int[] {0,1,2,4,5,7}));
		
		System.out.println(algos.editDistance("cba", "cd"));
		System.out.println(algos.editDistanceDP("cba", "cd"));

		int[] arr1 = new int[] {2,0};
		int[] arr2 = new int[] {1};

		algos.mergeSortedArrays(arr1, 1, arr2, 1);
		Helpers.printArr(arr1);
		
		System.out.println(algos.medianOfSortedArrays(new int[] {1,12,15,26,38}, new int[] {2,13,17,30,45}));
		
		// Min in rotated sorted array
		System.out.println(algos.findMin(new int[] {3,4,5,1,2}));
		System.out.println(algos.findMin(new int[] {4,5,6,7,0,1,2}));
		System.out.println(algos.findMin(new int[] {2,1}));
		System.out.println(algos.findMin(new int[] {2,3,4,5,1}));
		System.out.println(algos.findMin(new int[] {5,1,2,3,4}));
		System.out.println(algos.findMin(new int[] {2,2,2,0,1}));
		System.out.println(algos.findMinWithDup(new int[] {3,3,1,3}));
		System.out.println(algos.findMinWithDup(new int[] {3,3,3,3}));

		Helpers.printArr(algos.searchRange(new int[] {5,7,7,8,8,10}, 8));

		System.out.println(algos.searchInRotatedSortedArray(new int[] {4,5,6,7,0,1,2}, 0));
		System.out.println(algos.searchInRotatedSortedArrayWithDup(new int[] {1,3,1,1,1}, 3));
		
		System.out.println(algos.evalRPN(new String[] {"2", "1", "+", "3", "*"}));
		System.out.println(algos.evalRPN(new String[] {"4", "13", "5", "/", "+"}));
		System.out.println(algos.evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
		
		System.out.println(algos.longestValidParentheses("()"));
		System.out.println(algos.longestValidParentheses(")()())"));
		
//		System.out.println(algos.largestRectangleArea(new int[] {2,1,5,6,2,3}));
//		System.out.println(algos.largestRectangleArea2(new int[] {4,2,0,3,2,5}));
//		System.out.println(algos.largestRectangleArea2(new int[] {3,6,5,7,4,8,1,0}));
		System.out.println(algos.largestRectangleArea2(new int[] {1,2,3,4,5,3,3,2}));

		System.out.println("Broken Calc");
//		System.out.println(algos.brokenCalc(2, 3));
//		System.out.println(algos.brokenCalc(5, 8));
//		System.out.println(algos.brokenCalc(3, 10));
//		System.out.println(algos.brokenCalc(1, 1000000000));
		
//		System.out.println(algos.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"}));
		System.out.println(algos.lengthOfLongestSubstring("abcabcbb"));
		System.out.println(algos.lengthOfLongestSubstring("bbbbb"));
		System.out.println(algos.lengthOfLongestSubstring("pwwkew"));
		System.out.println(algos.lengthOfLongestSubstring("tmmzuxt"));
		System.out.println(algos.lengthOfLongestSubstring("bbtablud"));

		System.out.println(algos.majorityElement2(new int[] {3,2,3}));
		System.out.println(algos.majorityElement2(new int[] {1,1,1,3,3,2,2,2}));
		System.out.println(algos.majorityElement2(new int[] {2,2}));
		
		System.out.println(algos.increasingTriplet(new int[] {1,1,1}));

		// Rotate array
		arr = new int[] {1,2,3,4,5,6,7}; 
		algos.rotate(arr, 3);
		Helpers.printArr(arr);
		arr = new int[] {1,2,3,4,5}; 
		algos.rotate(arr, 3);
		Helpers.printArr(arr);

		System.out.println(algos.reverseWords("the sky is blue"));
		System.out.println(algos.reverseWords(" "));
		
		String s = "the sky is blue";
		char[] cArr = s.toCharArray();
		algos.reverseWords(cArr);
		for(char ch : cArr) {
			System.out.print(ch);
		}
		System.out.println();
		
		// ========== Group Anagrams =========
		System.out.println(algos.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
		System.out.println(algos.groupAnagramsOpt(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));

		/*
		System.out.println(algos.isMatchDP("aa","a")); // false
		System.out.println(algos.isMatchDP("aa","*a")); // true
		System.out.println(algos.isMatchDP("cb","?a")); // false
		System.out.println(algos.isMatchDP("adceb","a*c?b")); // true
		System.out.println(algos.isMatchDP("aa","*")); // true
		System.out.println(algos.isMatchDP("adceb","*a*b")); // true
		System.out.println(algos.isMatchDP("zacabz","*a?b*")); // false
		System.out.println(algos.isMatchDP("","")); // true*/
		
		System.out.println(algos.isMatch("aa","a")); // false
		System.out.println(algos.isMatch("aa","*a")); // true
		System.out.println(algos.isMatch("cb","?a")); // false
		System.out.println(algos.isMatch("adceb","a*c?b")); // true
		System.out.println(algos.isMatch("aa","*")); // true
		System.out.println(algos.isMatch("adceb","*a*b")); // true
		System.out.println(algos.isMatch("zacabz","*a?b*")); // false
		System.out.println(algos.isMatch("","")); // true

		System.out.println();
		System.out.println(algos.isReachable(Arrays.asList(new Integer[] {1,2,3,4}), 21));
		
		System.out.println(algos.flipGameMtd2("++++"));
		System.out.println(algos.flipGameMtd2("+++-++"));
		System.out.println(algos.generatePossibleNextMoves("++++"));
		System.out.println(algos.generatePossibleNextMoves("+++-++"));
		System.out.println(algos.canWin("++++"));
		
		System.out.println(algos.wordPattern2("abba", "dog cat cat dog"));
		System.out.println(algos.wordPattern2("abba", "dog cat cat fish"));
		System.out.println(algos.wordPattern2("abba", "dog dog dog dog"));

		System.out.println(algos.wordPatternMatch("abab", "redblueredblue"));
		System.out.println(algos.wordPatternMatch("aaaa", "asdasdasdasd"));
//		System.out.println(algos.wordPatternMatch("aabb", "xyzabcxzyabc")); // Failing
		
		System.out.println(algos.findKthLargest(new int[] {3,2,1,5,6,4}, 2));
		System.out.println(algos.findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4));

		System.out.println(algos.topKFrequent(new int[] {1,1,1,2,2,3}, 2));
		
		System.out.println(algos.minMeetingRooms(new ProgCreekAlgos.Interval[] {
				new ProgCreekAlgos.Interval(0, 30),
				new ProgCreekAlgos.Interval(5, 10),
				new ProgCreekAlgos.Interval(15, 20)
		}));
		
		System.out.println(algos.minMeetingRooms(new ProgCreekAlgos.Interval[] {
				new ProgCreekAlgos.Interval(7, 10),
				new ProgCreekAlgos.Interval(2, 4)
		}));

		System.out.println(algos.minMeetingRooms(new ProgCreekAlgos.Interval[] {
				new ProgCreekAlgos.Interval(0, 14),
				new ProgCreekAlgos.Interval(6, 25),
				new ProgCreekAlgos.Interval(12, 19),
				new ProgCreekAlgos.Interval(13, 19),
				new ProgCreekAlgos.Interval(5, 9)
		}));
		
		System.out.println(algos.canAttendMeetings(new ProgCreekAlgos.Interval[] {
				new ProgCreekAlgos.Interval(0, 4),
				new ProgCreekAlgos.Interval(12, 19),
				new ProgCreekAlgos.Interval(5, 9)
		}));
		
		System.out.println(algos.canAttendMeetings(new ProgCreekAlgos.Interval[] {
				new ProgCreekAlgos.Interval(4, 6),
				new ProgCreekAlgos.Interval(8, 10),
				new ProgCreekAlgos.Interval(6, 8)
		}));

		System.out.println(algos.canAttendMeetings(new ProgCreekAlgos.Interval[] {
				new ProgCreekAlgos.Interval(4, 6),
				new ProgCreekAlgos.Interval(8, 10),
				new ProgCreekAlgos.Interval(5, 8)
		}));
		
		Helpers.printArr(algos.getModifiedArray(4, new int[][] {
			{0, 1, 1},
			{0, 2, 3},
		}));
		
		Helpers.printArr(algos.getModifiedArray(8, new int[][] {
			{0, 3, 1},
			{4, 5, 3},
			{1, 4, 2},
			{0, 1, 4},
			{2, 5, 2}
		}));
		
		ListNode head1 = new ListNode(1, new ListNode(4, new ListNode(5)));
		ListNode head2 = new ListNode(1, new ListNode(3, new ListNode(4)));
		ListNode head3 = new ListNode(2, new ListNode(6));
		
		ListNode res = algos.mergeKLists(new ListNode[] {head1, head2, head3});
		res = algos.mergeKLists(new ListNode[] {});
		
		System.out.println(algos.containsDuplicate(new int[] {1,3,5,2,4,7}));
		System.out.println(algos.containsDuplicate(new int[] {1,3,5,2,4,2}));
		System.out.println("Duplicate II");
		System.out.println(algos.containsNearbyDuplicate(new int[] {1,2,3,1}, 3));
		System.out.println(algos.containsNearbyDuplicate(new int[] {1,0,1,1}, 1));
		System.out.println(algos.containsNearbyDuplicate(new int[] {1,2,3,1,2,3}, 2));

		System.out.println("Duplicate III");
		System.out.println(algos.containsNearbyAlmostDuplicate(new int[] {1,2,3,1}, 3, 0));
		System.out.println(algos.containsNearbyAlmostDuplicate(new int[] {1,0,1,1}, 1, 2));
		System.out.println(algos.containsNearbyAlmostDuplicate(new int[] {1,5,9,1,5,9}, 2, 3));
		System.out.println(algos.containsNearbyAlmostDuplicate(new int[] {1,-1}, 1, -1));
		
		System.out.println("Missing number");
		System.out.println(algos.missingNumberMtd3(new int[] {3,0,1}));
		System.out.println(algos.missingNumberMtd3(new int[] {9,6,4,2,3,5,7,0,1}));

		System.out.println("Duplicate number");
		System.out.println(algos.findDuplicateLeet(new int[] {1,3,4,2,2}));
		System.out.println(algos.findDuplicateLeet(new int[] {3,1,3,4,2}));
		System.out.println(algos.findDuplicateLeet(new int[] {1,3,4,2,1}));
		System.out.println("Floyd");
		System.out.println(algos.FindDuplicateFloyd(new int[] {1,3,4,2,2}));
		System.out.println(algos.FindDuplicateFloyd(new int[] {3,1,3,4,2}));
		System.out.println(algos.FindDuplicateFloyd(new int[] {1,3,4,2,1}));
		
		System.out.println("First Missing Positive Integer");
//		System.out.println(algos.firstMissingPositive(new int[] {1,2,0}));
//		System.out.println(algos.firstMissingPositive(new int[] {3,4,-1,1}));
//		System.out.println(algos.firstMissingPositive(new int[] {7,8,9,11,12}));
//		System.out.println(algos.firstMissingPositive(new int[] {1}));
//		System.out.println(algos.firstMissingPositive(new int[] {1,1}));
//		System.out.println(algos.firstMissingPositive(new int[] {2,2}));
		System.out.println(algos.firstMissingPositive(new int[] { 
						11, 1, 6, 11, 5, 5, -6, 9, -3, 9, 5, 4, 2, -8, 16, -6, -4, 2, 3 
						}));
		
		System.out.println("H-Index");
		System.out.println(algos.hIndex(new int[] {3,0,6,1,5}));
		System.out.println(algos.hIndex(new int[] {100}));
		System.out.println(algos.hIndex(new int[] {0,0,4,4}));

		System.out.println("H-IndexOpt");
		System.out.println(algos.hIndex2Opt(new int[] {0,1,3,5,6}));
		System.out.println(algos.hIndex2Opt(new int[] {100}));
		System.out.println(algos.hIndex2Opt(new int[] {0,0,4,4}));
		System.out.println(algos.hIndex2Opt(new int[] {0}));
		
		System.out.println("Maximum Sliding Window");
		Helpers.printArr(algos.maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3));;
		Helpers.printArr(algos.maxSlidingWindow(new int[] {1}, 1));;
		Helpers.printArr(algos.maxSlidingWindow(new int[] {1,-1}, 1));;
		Helpers.printArr(algos.maxSlidingWindow(new int[] {7,2,4}, 2));;
		Helpers.printArr(algos.maxSlidingWindow(new int[] {1,3,1,2,0,5}, 3));;
		
		MovingAverage avg = new MovingAverage(3);
		System.out.println(avg.next(1));
		System.out.println(avg.next(2));
		System.out.println(avg.next(3));
		System.out.println(avg.next(4));
		System.out.println(avg.next(5));
		System.out.println(avg.next(6));
		
		System.out.println("Median Finder");
		MedianFinder med = new MedianFinder();
		med.addNum(2);
		med.addNum(3);
		med.addNum(4);
		
		System.out.println(med.findMedian());
		
		med = new MedianFinder();
		med.addNum(2);
		med.addNum(3);
		
		System.out.println(med.findMedian());
		
		System.out.println("Disjoint intervals");
		SummaryRanges summRanges = new SummaryRanges();
		summRanges.addNum(1);
		summRanges.addNum(3);
		summRanges.addNum(7);
		summRanges.addNum(2);
		summRanges.addNum(6);
		
		Helpers.printArr(summRanges.getIntervals());
		
		System.out.println("Largest Numbers");
		System.out.println(algos.largestNumber(new int[] {10, 2}));
		System.out.println(algos.largestNumber(new int[] {3,30,34,5,9}));
		
		
		ListNode slHead = new ListNode(4, new ListNode(1, new ListNode(3)));
		SortLinkedList sl = new SortLinkedList();
		ListNode sortedHead = sl.mergeSortList(slHead);
		System.out.println(sortedHead);
		slHead = new ListNode(4, new ListNode(1));
		sortedHead = sl.mergeSortList(slHead);
		System.out.println(sortedHead);
		slHead = new ListNode(4, new ListNode(1, new ListNode(3, new ListNode(2, new ListNode(5)))));
		sortedHead = sl.mergeSortList(slHead);
		System.out.println(sortedHead);
		
		int[] arrSortCol = new int[] {2,0,2,1,1,0};
		algos.sortColors(arrSortCol);
		Helpers.printArr(arrSortCol);
		algos.sortColors2(arrSortCol);
		Helpers.printArr(arrSortCol);
		
		Helpers.printArr(algos.merge(new int[][] {{1,3},{2,6},{8,10},{15,18}}));
		Helpers.printArr(algos.merge(new int[][] {{1,4},{4,5}}));
		Helpers.printArr(algos.merge(new int[][] {{1,4},{0,4}}));
		Helpers.printArr(algos.merge(new int[][] {{1,4},{2,3}}));
		
		System.out.println("Insert Interval");
//		Helpers.printArr(algos.insert2(new int[][] {{1,3},{6,9}}, new int[] {2,5}));
		Helpers.printArr(algos.insert2(new int[][] {{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[] {4,8}));
//		Helpers.printArr(algos.insert2(new int[][] {{1,2}}, new int[] {4,8}));
//		Helpers.printArr(algos.insert2(new int[][] {{4,8}}, new int[] {1,2}));
//		Helpers.printArr(algos.insert2(new int[][] {{1,5}}, new int[] {2,8}));
//		Helpers.printArr(algos.insert2(new int[][] {}, new int[] {2,8}));
		
		System.out.println("MyStack");
		MyStack<String> ss = new MyStack<>(3);
		System.out.println(ss.push("A"));
		System.out.println(ss.push("B"));
		System.out.println(ss.push("C"));
		System.out.println(ss.push("D"));
		
		System.out.println(ss.pop());
		System.out.println(ss.pop());
		System.out.println(ss.pop());
		System.out.println(ss.pop());
		
		QueueFromStack qFS = new QueueFromStack();
		qFS.push(1);
		qFS.push(2);
		System.out.println(qFS.peek());
		System.out.println(qFS.pop());
		System.out.println(qFS.empty());
		
		// ===== TRIE =====
		System.out.println("==== TRIE ====");
		Trie trie = new Trie();
		trie.insert("abcd");
		trie.insert("abgd");
		System.out.println(trie.search("abcd"));
		System.out.println(trie.search("abgd"));
		System.out.println(trie.search("ab"));
		System.out.println(trie.startsWith("ab"));
	}

}













