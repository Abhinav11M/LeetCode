package educative.revision;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import educative.fastandslowpointers.ListNode;
import educative.revision.EducativeRevision.Job;
import educative.revision.EducativeRevision.Meeting;
import educative.revision.EducativeRevision.Point;
import educative.revision.EducativeRevision.TreeNodeWithNext;
import educative.tree.dfs.TreeNode;
import educative.twoheaps.Interval;
import helpers.Helpers;

public class EducativeRevisionMain {

	public static void main(String[] args) {
		EducativeRevision algos = new EducativeRevision();

		// ===== Two pointers =====

		// Maximum Sum Subarray of Size K (easy)
		System.out.println(
				"Maximum sum of a subarray of size K: " + algos.findMaxSumSubArray(3, new int[] { 2, 1, 5, 1, 3, 2 }));
		System.out.println(
				"Maximum sum of a subarray of size K: " + algos.findMaxSumSubArray(2, new int[] { 2, 3, 4, 1, 5 }));

		// Smallest Subarray with a given sum (easy)
		System.out.println(algos.findMinSubArray(7, new int[] { 2, 1, 5, 2, 3, 2 }));
		System.out.println(algos.findMinSubArray(7, new int[] { 2, 1, 5, 2, 8 }));
		System.out.println(algos.findMinSubArray(8, new int[] { 3, 4, 1, 1, 6 }));

		// Longest Substring with K Distinct Characters (medium)
		System.out.println("Length of the longest substring: " + algos.findLength("araaci", 2));
		System.out.println("Length of the longest substring: " + algos.findLength("araaci", 1));
		System.out.println("Length of the longest substring: " + algos.findLength("cbbebi", 3));

		// Fruits into Baskets (medium)
		System.out.println("Maximum number of fruits: "
				+ algos.findMaxBasketSize(new char[] { 'A', 'B', 'C', 'A', 'C' }));
		System.out.println("Maximum number of fruits: "
				+ algos.findMaxBasketSize(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));

		// No-repeat Substring (hard)
		System.out.println("Length of the longest substring: " + algos.findLongestNonRepeatingSubstr("aabccbb"));
	    System.out.println("Length of the longest substring: " + algos.findLongestNonRepeatingSubstr("abbbb"));
	    System.out.println("Length of the longest substring: " + algos.findLongestNonRepeatingSubstr("abccde"));
	    System.out.println("Length of the longest substring: " + algos.findLongestNonRepeatingSubstr("dvdf"));
	    
	    System.out.println(algos.findLongestRepeatingStrWithKReplacement("aabccbb", 2));
	    System.out.println(algos.findLongestRepeatingStrWithKReplacement("abbcb", 1));
	    System.out.println(algos.findLongestRepeatingStrWithKReplacement("abccde", 1));
	    
	    // Longest Subarray with Ones after Replacement (hard)
	    System.out.println(algos.findLongestArrayWithOnes(new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 }, 2));
	    System.out.println(algos.findLongestArrayWithOnes(new int[] { 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1 }, 3));
	    
	    // Permutation in a String (hard)
	    System.out.println(algos.findPermutation("oidbcaf", "abc"));
	    System.out.println(algos.findPermutation("odicf", "dc"));
	    System.out.println(algos.findPermutation("bcdxabcdy", "bcdyabcdx"));
	    System.out.println(algos.findPermutation("aaacb", "abc"));

	    System.out.println(algos.findPermutationEducativeSol("oidbcaf", "abc"));
	    System.out.println(algos.findPermutationEducativeSol("odicf", "dc"));
	    System.out.println(algos.findPermutationEducativeSol("bcdxabcdy", "bcdyabcdx"));
	    System.out.println(algos.findPermutationEducativeSol("aaacb", "abc"));
	    
	    // String Anagrams (hard)
	    System.out.println(algos.findStringAnagrams("ppqp", "pq"));
	    System.out.println(algos.findStringAnagrams("abbcabc", "abc"));
	    
	    // Smallest Window containing Substring (hard)
	    System.out.println(algos.findSubstring("aabdec", "abc"));
	    System.out.println(algos.findSubstring("abdbca", "abc"));
	    System.out.println(algos.findSubstring("adcad", "abc"));
	    System.out.println(algos.findSubstring("cabwefgewcwaefgcf", "cae"));
	    System.out.println(algos.findSubstring("aaabdabcefaecbef", "abc"));
	    
	    // Words Concatenation (hard)
	    System.out.println(algos.findWordConcatenation("catfoxcat", new String[] {"cat", "fox"}));
	    System.out.println(algos.findWordConcatenation("catcatfoxfox", new String[] {"cat", "fox"}));
		// ===== Two pointers =====
	    
	    // =========================================================================================
	    // =========================================================================================
	    
	    // Pair with target sum
	    Helpers.printArr(algos.pairWithTargetSum(new int[] {1, 2, 3, 4, 6}, 6));
	    Helpers.printArr(algos.pairWithTargetSum(new int[] {2, 5, 9, 11}, 11));
	    
	    int[] arr = new int[] {2, 3, 3, 3, 6, 9, 9};
	    System.out.println(algos.removeDuplicates(arr));
	    Helpers.printArr(arr);

	    // Remove duplicates
	    arr = new int[] {2, 2, 2, 11};
	    System.out.println(algos.removeDuplicates(arr));
	    Helpers.printArr(arr);
	    
	    // Squaring a sorted array
	    Helpers.printArr(algos.makeSquares(new int[] {-2, -1, 0, 2, 3}));
	    Helpers.printArr(algos.makeSquares(new int[] {-3, -1, 0, 1, 2}));
	    Helpers.printArr(algos.makeSquares(new int[] {-5, -4, -3, -2, -1}));
	    Helpers.printArr(algos.makeSquares(new int[] {1, 2, 3, 4, 5}));

	    Helpers.printArr(algos.makeSquares2(new int[] {-2, -1, 0, 2, 3}));
	    Helpers.printArr(algos.makeSquares2(new int[] {-3, -1, 0, 1, 2}));
	    Helpers.printArr(algos.makeSquares2(new int[] {-5, -4, -3, -2, -1}));
	    Helpers.printArr(algos.makeSquares2(new int[] {1, 2, 3, 4, 5}));
	    
	    // Search triplets
	    System.out.println(algos.searchTriplets(new int[] {-3, 0, 1, 2, -1, 1, -2}));
	    System.out.println(algos.searchTripletsEduSol(new int[] {-3, 0, 1, 2, -1, 1, -2}));
	    
	    // Triplet Sum Close to Target (medium)
	    System.out.println(algos.tripletSumCloseToTarget(new int[] {-2, 0, 1, 2}, 2));
	    System.out.println(algos.tripletSumCloseToTarget(new int[] {-3, -1, 1, 2}, 1));
	    System.out.println(algos.tripletSumCloseToTarget(new int[] {1, 0, 1, 1}, 100));
	    
	    // Subarrays with product less than a target
	    System.out.println(algos.subarrayProductLessThanK(new int[] {2, 5, 3, 10}, 30));
	    
	    // Dutch National Flag Problem (medium)
	    arr = new int[] {1, 0, 2, 1, 0};
	    algos.dutchNationalFlag(arr);
	    Helpers.printArr(arr);
	    
	    // Comparing Strings containing Backspaces (medium)
	    System.out.println(algos.compareStringWithBackSpaces("xy#z", "xzz#"));
	    System.out.println(algos.compareStringWithBackSpaces("xy#z", "xyz#"));
	    System.out.println(algos.compareStringWithBackSpaces("xp#", "xyz##"));
	    System.out.println(algos.compareStringWithBackSpaces("xywrrmp", "xywrrmu#p"));
	    
	    // Minimum window sort
	    System.out.println(algos.minimumWindowSort(new int[] {1, 2, 5, 3, 7, 10, 9, 12}));
	    System.out.println(algos.minimumWindowSort(new int[] {1, 3, 2, 0, -1, 7, 10}));
	    System.out.println(algos.minimumWindowSort(new int[] {1, 2, 3}));
	    System.out.println(algos.minimumWindowSort(new int[] {3, 2, 1}));
	    
	    // =========================================================================================
	    // =========================================================================================
	    System.out.println(algos.isHappyNumber(23));
	    System.out.println(algos.isHappyNumber(12));
	    
	    // Palindrome list
	    ListNode head = new ListNode(2);
	    head.next = new ListNode(4);
	    head.next.next = new ListNode(6);
	    head.next.next.next = new ListNode(4);
	    head.next.next.next.next = new ListNode(2);
	    System.out.println("Is palindrome: " + algos.isPalindrome(head));

	    // Reorder linked list
	    head = new ListNode(2);
	    head.next = new ListNode(4);
	    head.next.next = new ListNode(6);
	    head.next.next.next = new ListNode(8);
	    head.next.next.next.next = new ListNode(10);
	    head.next.next.next.next.next = new ListNode(12);
	    algos.reorder(head);
	    
	    ListNode.printList(head);
	    
	    // Cycle in a circular array
	    System.out.println(algos.circularArrayLoop(new int[] {1, 2, -1, 2, 2}));
	    System.out.println(algos.circularArrayLoop(new int[] {2, 2, -1, 2}));
	    System.out.println(algos.circularArrayLoop(new int[] {2, 1, -1, -2}));
	    
	    // =========================================================================================
	    // =========================================================================================
	    
	    // Merge intervals
	    ArrayList<Interval> intervals = Lists.newArrayList(
	    		new Interval(1,4),
	    		new Interval(2,5),
	    		new Interval(7,9)
	    		);
	    System.out.println(algos.merge(intervals));
	    
	    // Insert interval
	    intervals = Lists.newArrayList(
	    		new Interval(1,3),
	    		new Interval(5,7),
	    		new Interval(8,12)
	    		);
	    
	    System.out.println(algos.insertInterval(intervals, new Interval(4,6)));
	    System.out.println(algos.insertInterval(intervals, new Interval(4,10)));

	    intervals = Lists.newArrayList(
	    		new Interval(2,3),
	    		new Interval(5,7)
	    		);
	    
	    System.out.println(algos.insertInterval(intervals, new Interval(1,4)));
	    
	    // Merge two intervals list
	    Interval[] intervals1 = new Interval[] {
	    		new Interval(1,3),
	    		new Interval(5,6),
	    		new Interval(5,9)
	    };

	     Interval[] intervals2 = new Interval[] {
	    		new Interval(2,3),
	    		new Interval(5,7)
	     };
	    System.out.println("Merge Intervals Lists");
	    System.out.println(algos.mergeIntervalLists(intervals1, intervals2));
	    
	    // Find intersection of intervals b/w two list of intervals
	    intervals1 = new Interval[] {
	    		new Interval(1,3),
	    		new Interval(5,6),
	    		new Interval(7,9)
	    };

	    intervals2 = new Interval[] {
	    		new Interval(2,3),
	    		new Interval(5,7)
	    };
	    
	    System.out.println("Intersection of intervals");
	    Helpers.printArr(algos.intervalIntersection(intervals1, intervals2));
	    
	    intervals1 = new Interval[] {
	    		new Interval(1,3),
	    		new Interval(5,7),
	    		new Interval(9,12)
	    };

	    intervals2 = new Interval[] {
	    		new Interval(5,10)
	    };

	    Helpers.printArr(algos.intervalIntersection(intervals1, intervals2));
	    
	    // Conflicting Appointments
	    System.out.println("Conflicting appointments");
	    System.out.println(algos.canAttendAllAppointments(new Interval[] {
	    		new Interval(1,4),
	    		new Interval(2,5),
	    		new Interval(7,9)
	    }));
	    
	    System.out.println(algos.canAttendAllAppointments(new Interval[] {
	    		new Interval(6,7),
	    		new Interval(2,4),
	    		new Interval(8,12)
	    }));

	    System.out.println(algos.canAttendAllAppointments(new Interval[] {
	    		new Interval(4,5),
	    		new Interval(2,3),
	    		new Interval(3,6)
	    }));
	    
	    // Minimum Meeting Rooms (hard)
	    System.out.println("Minimum meeting rooms");
	    ArrayList<Meeting> meetings = Lists.newArrayList(
	    		new Meeting(1,4),
	    		new Meeting(2,5),
	    		new Meeting(7,9)
	    		);
	    System.out.println(algos.findMinimumMeetingRooms(meetings));

	    meetings = Lists.newArrayList(
	    		new Meeting(6,7),
	    		new Meeting(2,4),
	    		new Meeting(8,12)
	    		);
	    System.out.println(algos.findMinimumMeetingRooms(meetings));

	    meetings = Lists.newArrayList(
	    		new Meeting(1,4),
	    		new Meeting(2,3),
	    		new Meeting(3,6)
	    		);
	    System.out.println(algos.findMinimumMeetingRooms(meetings));

	    meetings = Lists.newArrayList(
	    		new Meeting(4,5),
	    		new Meeting(2,3),
	    		new Meeting(2,4),
	    		new Meeting(3,5)
	    		);
	    System.out.println(algos.findMinimumMeetingRooms(meetings));

	    // Maximum CPU Load
	    System.out.println("Maximum CPU Load");
	    ArrayList<Job> jobs = Lists.newArrayList(
	    		new Job(1,4,3),
	    		new Job(2,5,4),
	    		new Job(7,9,6)
	    		);
	    System.out.println(algos.findMaxCPULoad(jobs));

	    jobs = Lists.newArrayList(
	    		new Job(6,7,10),
	    		new Job(2,4,11),
	    		new Job(8,12,15)
	    		);
	    System.out.println(algos.findMaxCPULoad(jobs));

	    jobs = Lists.newArrayList(
	    		new Job(1,4,2),
	    		new Job(2,4,1),
	    		new Job(3,6,5)
	    		);
	    System.out.println(algos.findMaxCPULoad(jobs));
	    
	    System.out.println("Employee Free Time");
	    List<List<Interval>> schedule = Lists.newArrayList(
	    		Lists.newArrayList(new Interval(1,3), new Interval(5,6)),
	    		Lists.newArrayList(new Interval(2,3), new Interval(6,8))
	    		);
	    System.out.println(algos.findEmployeeFreeTime(schedule));

	    schedule = Lists.newArrayList(
	    		Lists.newArrayList(new Interval(1,3), new Interval(9,12)),
	    		Lists.newArrayList(new Interval(2,4), new Interval(6,8))
	    		);
	    System.out.println(algos.findEmployeeFreeTime(schedule));

	    schedule = Lists.newArrayList(
	    		Lists.newArrayList(new Interval(1,3), new Interval(2,4)),
	    		Lists.newArrayList(new Interval(3,5), new Interval(7,9))
	    		);
	    System.out.println(algos.findEmployeeFreeTime(schedule));

	    // =========================================================================================
	    // =========================================================================================
	    
	    // Cyclic Sort
	    arr = new int[] {3, 1, 5, 4, 2};
	    algos.cyclicSort(arr);
	    Helpers.printArr(arr);

	    arr = new int[] {2, 6, 4, 3, 1, 5};
	    algos.cyclicSort(arr);
	    Helpers.printArr(arr);

	    arr = new int[] {1, 5, 6, 4, 3, 2};
	    algos.cyclicSort(arr);
	    Helpers.printArr(arr);
	    
	    // Find the first missing number
	    System.out.println(algos.findMissingNumber(new int[] {4, 0, 3, 1}));
	    System.out.println(algos.findMissingNumber(new int[] {8, 3, 5, 2, 4, 6, 0, 1}));
	    
	    // Find all missing numbers
	    System.out.println(algos.findAllMissingNumbers(new int[] {2, 3, 1, 8, 2, 3, 5, 1}));
	    System.out.println(algos.findAllMissingNumbers(new int[] {2, 4, 1, 2}));
	    System.out.println(algos.findAllMissingNumbers(new int[] {2, 3, 2, 1}));
	    
	    // Find the duplicate number
	    System.out.println(algos.findDuplicateNumber(new int[] {1, 4, 4, 3, 2}));
	    System.out.println(algos.findDuplicateNumber(new int[] {2, 1, 3, 3, 5, 4}));
	    System.out.println(algos.findDuplicateNumber(new int[] {2, 4, 1, 4, 4}));

	    // Find all the duplicate numbers
	    System.out.println(algos.findAllDuplicateNumbers(new int[] {3, 4, 4, 5, 5}));
	    System.out.println(algos.findAllDuplicateNumbers(new int[] {5, 4, 7, 2, 3, 5, 3}));
	    
	    // Find the Corrupt Pair (easy)
	    Helpers.printArr(algos.findCorruptNums(new int[] {3, 1, 2, 5, 2}));
	    Helpers.printArr(algos.findCorruptNums(new int[] {3, 1, 2, 3, 6, 4}));
	    
	    // First missing positive integer
	    System.out.println(algos.findFirstMissingPositive(new int[] {-3, 1, 5, 4, 2}));
	    System.out.println(algos.findFirstMissingPositive(new int[] {3, -2, 0, 1, 2}));
	    System.out.println(algos.findFirstMissingPositive(new int[] {3, 2, 5, 1}));
	    
	    // Find the first k missing positive numbers
	    System.out.println(algos.findFirstKMissingPositiveIntegers(new int[] {3, -1, 4, 5, 5}, 3));
	    System.out.println(algos.findFirstKMissingPositiveIntegers(new int[] {2, 3, 4}, 3));
	    System.out.println(algos.findFirstKMissingPositiveIntegers(new int[] {-2, -3, 4}, 2));
	    
	    /**========================
	     * === Pattern: Tree BFS ==
	     * ========================
	     */
	    // Level order traversal
	    TreeNode root = new TreeNode(12);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(9);
	    root.right.left = new TreeNode(10);
	    root.right.right = new TreeNode(5);
	    List<List<Integer>> result = algos.levelOrderTraversal(root);
	    System.out.println(result);
	    
	    // Reverse level order traversal
	    result = algos.reverseLevelOrderTraversal(root);
	    System.out.println(result);

	    // ZigZag level order traversal
	    result = algos.zigZagLevelOrderTraversal(root);
	    System.out.println(result);
	    
	    // Level order average
	    System.out.println(algos.findLevelAverages(root));
	    
	    // Minimum binary tree depth
	    root = new TreeNode(12);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(1);
	    root.right.left = new TreeNode(10);
	    root.right.right = new TreeNode(5);
	    System.out.println("Tree Minimum Depth: " + algos.minimumBinaryTreeDepthRec(root));
	    System.out.println("Tree Minimum Depth: " + algos.minimumBinaryTreeDepth(root));
	    root.left.left = new TreeNode(9);
	    root.right.left.left = new TreeNode(11);
	    System.out.println("Tree Minimum Depth: " + algos.minimumBinaryTreeDepthRec(root));
	    System.out.println("Tree Minimum Depth: " + algos.minimumBinaryTreeDepth(root));
	    
	    // Connect level order siblings
	    TreeNodeWithNext root1 = new TreeNodeWithNext(12);
	    root1.left = new TreeNodeWithNext(7);
	    root1.right = new TreeNodeWithNext(1);
	    root1.left.left = new TreeNodeWithNext(9);
	    root1.right.left = new TreeNodeWithNext(10);
	    root1.right.right = new TreeNodeWithNext(5);
	    algos.connectLevelOrderSiblings(root1);
	    System.out.println("Level order traversal using 'next' pointer: ");
	    root1.printLevelOrder();
	    
	    // =========================================================================================
	    // =========================================================================================

	    /**========================
	     * === Pattern: Tree BFS ==
	     * ========================
	     */

	    // Path with sum
	    root = new TreeNode(12);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(9);
	    root.right.left = new TreeNode(10);
	    root.right.right = new TreeNode(5);
	    System.out.println("Tree has path: " + algos.hasPathWithSum(root, 23));
	    System.out.println("Tree has path: " + algos.hasPathWithSum(root, 16));
	    
	    // All Paths for a Sum (medium)
	    root = new TreeNode(12);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(4);
	    root.right.left = new TreeNode(10);
	    root.right.right = new TreeNode(5);
	    int sum = 23;
	    List<List<Integer>> resultAllPaths = algos.findPathsWithSum(root, sum);
	    System.out.println("Tree paths with sum " + sum + ": " + resultAllPaths);
	    
	    // Sum of Path Numbers (medium)
	    root = new TreeNode(1);
	    root.left = new TreeNode(0);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(1);
	    root.right.left = new TreeNode(6);
	    root.right.right = new TreeNode(5);
	    System.out.println("Total Sum of Path Numbers: " + algos.findSumOfPathNumbers(root));
	    
	    // Path with given sequence
	    root = new TreeNode(1);
	    root.left = new TreeNode(0);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(1);
	    root.right.left = new TreeNode(6);
	    root.right.right = new TreeNode(5);

	    System.out.println("Tree has path sequence: " + algos.findPathWithSequence(root, new int[] { 1, 0, 7 }));
	    System.out.println("Tree has path sequence: " + algos.findPathWithSequence(root, new int[] { 1, 1, 6 }));
	    
	    // Count paths for a sum
	    root = new TreeNode(12);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(4);
	    root.right.left = new TreeNode(10);
	    root.right.right = new TreeNode(5);
	    System.out.println("Tree has path: " + algos.countPathsWithSum(root, 11));
	    
	    // Find diameter of a tree
	    root = new TreeNode(1);
	    root.left = new TreeNode(2);
	    root.right = new TreeNode(3);
	    root.left.left = new TreeNode(4);
	    root.right.left = new TreeNode(5);
	    root.right.right = new TreeNode(6);
	    System.out.println("Tree Diameter: " + algos.diameterOfTree(root));
	    root.left.left = null;
	    root.right.left.left = new TreeNode(7);
	    root.right.left.right = new TreeNode(8);
	    root.right.right.left = new TreeNode(9);
	    root.right.left.right.left = new TreeNode(10);
	    root.right.right.left.left = new TreeNode(11);
	    System.out.println("Tree Diameter: " + algos.diameterOfTree(root));
	    
	    // Path with Maximum Sum (hard)
	    root = new TreeNode(1);
	    root.left = new TreeNode(2);
	    root.right = new TreeNode(3);
	    System.out.println("Maximum Path Sum: " + algos.findMaximumPathSum(root));
	    
	    root.left.left = new TreeNode(1);
	    root.left.right = new TreeNode(3);
	    root.right.left = new TreeNode(5);
	    root.right.right = new TreeNode(6);
	    root.right.left.left = new TreeNode(7);
	    root.right.left.right = new TreeNode(8);
	    root.right.right.left = new TreeNode(9);
	    System.out.println("Maximum Path Sum: " + algos.findMaximumPathSum(root));
	    
	    root = new TreeNode(-1);
	    root.left = new TreeNode(-3);
	    System.out.println("Maximum Path Sum: " + algos.findMaximumPathSum(root));

	    // =========================================================================================
	    // =========================================================================================

	    /**========================
	     * === Pattern: Subsets ===
	     * ========================
	     */
	    // Subsets
	    System.out.println(algos.findSubsets(new int[] {1, 5, 3}));
	    
	    // Subsets with duplicates
	    System.out.println(algos.findSubsetsWithDuplicates(new int[] {1, 3, 3}));
	    System.out.println(algos.findSubsetsWithDuplicates(new int[] {1, 5, 3, 3}));
	    
	    // Permutations
	    System.out.println(algos.findPermutations(new int[] {1,3,5}));
	    System.out.println(algos.findPermutationsRec(new int[] {1,3,5}));
	    
	    // String Permutations by changing case (medium)
	    System.out.println(algos.findLetterCaseStringPermutations("ad52"));
	    System.out.println(algos.findLetterCaseStringPermutations("ab7c"));
	    
	    // Balanced Parentheses (hard)
	    System.out.println(algos.generateValidParentheses(2));
	    System.out.println(algos.generateValidParentheses(3));
	    
	    // Evaluate Expression (hard)
	    System.out.println(algos.diffWaysToEvaluateExpression("1+2*3"));
	    System.out.println(algos.diffWaysToEvaluateExpression("2*3-4-5"));    
	    
	    // Structurally Unique Binary Search Trees (hard)
	    List<TreeNode> uniqueBsts = algos.findUniqueTrees(2);
	    System.out.println(uniqueBsts);
	    uniqueBsts = algos.findUniqueTrees(3);
	    System.out.println(uniqueBsts);

	    // =========================================================================================
	    // =========================================================================================
	    // Order agnostic binary search
	    System.out.println(algos.agnosticBinSearch(new int[] { 4, 6, 10 }, 10));
	    System.out.println(algos.agnosticBinSearch(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 5));
	    System.out.println(algos.agnosticBinSearch(new int[] { 10, 6, 4 }, 10));
	    System.out.println(algos.agnosticBinSearch(new int[] { 10, 6, 4 }, 4));
	    
	    // Ceiling of a Number (medium)
	    System.out.println("Ceiling of a number");
	    System.out.println(algos.searchCeilingOfANumber(new int[] { 4, 6, 10 }, 6));
	    System.out.println(algos.searchCeilingOfANumber(new int[] { 1, 3, 8, 10, 15 }, 12));
	    System.out.println(algos.searchCeilingOfANumber(new int[] { 4, 6, 10 }, 17));
	    System.out.println(algos.searchCeilingOfANumber(new int[] { 4, 6, 10 }, -1));
	    
	    // Next Letter (medium)
	    System.out.println(algos.searchNextLetter(new char[] {'a', 'c', 'f', 'h'}, 'f'));
	    System.out.println(algos.searchNextLetter(new char[] {'a', 'c', 'f', 'h'}, 'b'));
	    System.out.println(algos.searchNextLetter(new char[] {'a', 'c', 'f', 'h'}, 'm'));
	    System.out.println(algos.searchNextLetter(new char[] {'a', 'c', 'f', 'h'}, 'h'));
	    
	    // Number Range (medium)
	    Helpers.printArr(algos.findRange(new int[] {4, 6, 6, 6, 9}, 6));
	    Helpers.printArr(algos.findRange(new int[] {1, 3, 8, 10, 15}, 10));
	    Helpers.printArr(algos.findRange(new int[] {1, 3, 8, 10, 15}, 12));
	    
	    // Minimum Difference Element (medium)
	    System.out.println(algos.searchMinDiffElement(new int[] {4, 6, 10}, 7));
	    System.out.println(algos.searchMinDiffElement(new int[] {4, 6, 10}, 4));
	    System.out.println(algos.searchMinDiffElement(new int[] {1, 3, 8, 10, 15}, 12));
	    System.out.println(algos.searchMinDiffElement(new int[] {4, 6, 10}, 17));
	    
	    // Bitonic Array Maximum (easy)
	    System.out.println("Max of a bitonic array");
	    System.out.println(algos.findMaxInBitonicArray(new int[] {1, 3, 8, 12, 4, 2}));
	    System.out.println(algos.findMaxInBitonicArray(new int[] {3, 8, 3, 1}));
	    System.out.println(algos.findMaxInBitonicArray(new int[] {1, 3, 8, 12}));
	    System.out.println(algos.findMaxInBitonicArray(new int[] {10, 9, 8}));
	    
	    // Search Bitonic Array (medium) 
	    System.out.println("Search in bitonic array");
	    System.out.println(algos.searchInBitonicArray(new int[] {1, 3, 8, 4, 3}, 4));
	    System.out.println(algos.searchInBitonicArray(new int[] {3, 8, 3, 1}, 8));
	    System.out.println(algos.searchInBitonicArray(new int[] {1, 3, 8, 12}, 12));
	    System.out.println(algos.searchInBitonicArray(new int[] {10, 9, 8}, 10));
	    
	    // Search in Rotated Array (medium)
	    System.out.println(algos.searchRotatedArray(new int[] {10, 15, 1, 3, 8}, 15));
	    System.out.println(algos.searchRotatedArray(new int[] {4, 5, 7, 9, 10, -1, 2}, 10));
	    
	    // Rotation Count (medium)
	    System.out.println("Rotation count");
	    System.out.println(algos.rotationCountOfRotatedArray(new int[] {10, 15, 1, 3, 8}));
	    System.out.println(algos.rotationCountOfRotatedArray(new int[] {4, 5, 7, 9, 10, -1, 2}));
	    System.out.println(algos.rotationCountOfRotatedArray(new int[] {1, 3, 8, 10}));

	    // =========================================================================================
	    // =========================================================================================
	    // Top K elements
	    System.out.println(algos.findKLargestNumbers(new int[] {3, 1, 5, 12, 2, 11}, 3));
	    System.out.println(algos.findKLargestNumbers(new int[] {5, 12, 11, -1, 12}, 3));
	    
	    // Kth smallest number
	    System.out.println(algos.findKthSmallestNumber(new int[] {1, 5, 12, 2, 11, 5}, 3));
	    System.out.println(algos.findKthSmallestNumber(new int[] {1, 5, 12, 2, 11, 5}, 4));
	    System.out.println(algos.findKthSmallestNumber(new int[] {5, 12, 11, -1, 12}, 3));
	    
	    // Closest points
	    Point[] points = new Point[] { new Point(1,2), new Point(1,3) };
	    System.out.println(algos.findClosestPoints(points, 1));

	    points = new Point[] { new Point(1,3), new Point(3,4), new Point(2,-1) };
	    System.out.println(algos.findClosestPoints(points, 2));
	    
	    // Connect Ropes (easy)
	    System.out.println(algos.minimumCostToConnectRopes(new int[] {1, 3, 11, 5}));
	    System.out.println(algos.minimumCostToConnectRopes(new int[] {3, 4, 5, 6}));
	    System.out.println(algos.minimumCostToConnectRopes(new int[] {1, 3, 11, 5, 2}));
	    
	    // Top 'K' Frequent Numbers (medium)
	    System.out.println(algos.findTopKFrequentNumbers(new int[] {1, 3, 5, 12, 11, 12, 11}, 2));
	    System.out.println(algos.findTopKFrequentNumbers(new int[] {5, 12, 11, 3, 11}, 2));
	    
	    // Frequency Sort (medium)
	    System.out.println(algos.sortCharacterByFrequency("Programming"));
	    System.out.println(algos.sortCharacterByFrequency("abcbab"));
	    
	    // K Closest numbers (medium)
	    System.out.println(algos.findClosestElements(new int[] {5, 6, 7, 8, 9}, 3, 7));
	    System.out.println(algos.findClosestElements(new int[] {2, 4, 5, 6, 9}, 3, 6));
	    System.out.println(algos.findClosestElements(new int[] {2, 4, 5, 6, 9}, 3, 10));

	    System.out.println(algos.findClosestElements2(new int[] {5, 6, 7, 8, 9}, 3, 7));
	    System.out.println(algos.findClosestElements2(new int[] {2, 4, 5, 6, 9}, 3, 6));
	    System.out.println(algos.findClosestElements2(new int[] {2, 4, 5, 6, 9}, 3, 10));
	    
	    // Maximum Distinct Elements (medium)
	    System.out.println(algos.findMaximumDistinctElements(new int[] {7, 3, 5, 8, 5, 3, 3}, 2));
	    System.out.println(algos.findMaximumDistinctElements(new int[] {3, 5, 12, 11, 12}, 3));
	    System.out.println(algos.findMaximumDistinctElements(new int[] {1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5}, 2));
	    
	    // Sum of Elements (medium)
	    System.out.println(algos.findSumOfElements(new int[] {1, 3, 12, 5, 15, 11}, 3, 6));
	    System.out.println(algos.findSumOfElements(new int[] {3, 5, 8, 7}, 1, 4));
	    
	    // Rearrange String (hard)
	    System.out.println(algos.rearrangeString("aappp"));
	    System.out.println(algos.rearrangeString("Programming"));
	    System.out.println(algos.rearrangeString("aapa"));
	    
	    // Rearrange String K Distance Apart (hard)
	    System.out.println("Rearrange String K Distance Apart");
	    System.out.println(algos.reorganizeString("mmpp", 2));
	    System.out.println(algos.reorganizeString("Programming", 3));
	    System.out.println(algos.reorganizeString("aab", 2));
	    System.out.println(algos.reorganizeString("aappa", 3));
	    
	    // Scheduling Tasks (hard)
	    System.out.println(algos.scheduleTasks(new char[] {'a', 'a', 'a', 'b', 'c', 'c'}, 2));
	    System.out.println(algos.scheduleTasks(new char[] {'a', 'b', 'a'}, 3));

	    // =========================================================================================
	    // =========================================================================================
	    // Two Heaps
	    MedianOfAStream medianOfStream = new MedianOfAStream();
	    medianOfStream.insertNum(3);
	    medianOfStream.insertNum(1);
	    System.out.println(medianOfStream.findMedian());
	    medianOfStream.insertNum(5);
	    System.out.println(medianOfStream.findMedian());
	    medianOfStream.insertNum(4);
	    System.out.println(medianOfStream.findMedian());
	    
	    // Sliding Window Median (hard)
	    SlidingWindowMedian slm = new SlidingWindowMedian();
	    Helpers.printArr(slm.findSlidingWindowMedian(new int[] {1, 2, -1, 3, 5}, 2));
	    
	    // Maximize Capital (hard)
	    System.out.println(algos.findMaximumCapital(new int[] { 0, 1, 2 }, new int[] { 1, 2, 3 }, 2, 1));
	    System.out.println(algos.findMaximumCapital(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0));
	    
	    // Next Interval (hard)
	    Interval[] intervals3 = new Interval[] { new Interval(2, 3), new Interval(3, 4), new Interval(5, 6) };
	    Helpers.printArr(algos.findNextInterval(intervals3));

	    intervals3 = new Interval[] { new Interval(3, 4), new Interval(1, 5), new Interval(4, 6) };
	    Helpers.printArr(algos.findNextInterval(intervals3));
	    
	    // =========================================================================================
	    // =========================================================================================
	    // Dynamic Programming
	    // 0/1 Knapsack (Recursive)
	    System.out.println(algos.solveKnapsack(new int[] {1, 6, 10, 16}, new int[] {1, 2, 3, 5}, 7));
	    System.out.println(algos.solveKnapsack(new int[] {1, 6, 10, 16}, new int[] {1, 2, 3, 5}, 6));
	    // 0/1 Knapsack (Memoization)
	    System.out.println(algos.solveKnapsackMemoization(new int[] {1, 6, 10, 16}, new int[] {1, 2, 3, 5}, 7));
	    System.out.println(algos.solveKnapsackMemoization(new int[] {1, 6, 10, 16}, new int[] {1, 2, 3, 5}, 6));
	    // 0/1 Knapsack (Tabulation)
	    System.out.println(algos.solveKnapsackTabulation(new int[] {1, 6, 10, 16}, new int[] {1, 2, 3, 5}, 7));
	    System.out.println(algos.solveKnapsackTabulation(new int[] {1, 6, 10, 16}, new int[] {1, 2, 3, 5}, 6));
	    // 0/1 Knapsack (Tabulation Optimized)
	    System.out.println(algos.solveKnapsackTabulationOpt(new int[] {1, 6, 10, 16}, new int[] {1, 2, 3, 5}, 7));
	    System.out.println(algos.solveKnapsackTabulationOpt(new int[] {1, 6, 10, 16}, new int[] {1, 2, 3, 5}, 6));
	    
	    // Equal Subset Sum Partition (medium)
		System.out.println(algos.canPartition(new int[] { 1, 2, 3, 4 }));
		System.out.println(algos.canPartition(new int[] { 1, 1, 3, 4, 7 }));
		System.out.println(algos.canPartition(new int[] { 2, 3, 4, 6 }));
		System.out.println(algos.canPartition(new int[] { 1, 2, 4, 9 }));
		System.out.println(algos.canPartition(new int[] { 1, 2, 3, 8 }));
		
		// Subset Sum (medium)
		System.out.println(algos.canFindSubsetWithSum(new int[] {1, 2, 3, 7}, 6));
		System.out.println(algos.canFindSubsetWithSum(new int[] {1, 2, 7, 1, 5}, 10));
		System.out.println(algos.canFindSubsetWithSum(new int[] {1, 3, 4, 8}, 6));
		
		// Minimum Subset Sum Difference (hard)
		System.out.println(algos.partitionWithMinDiffBewSubsets(new int[] {1, 2, 3, 9}));
		System.out.println(algos.partitionWithMinDiffBewSubsets(new int[] {1, 2, 7, 1, 5}));
		System.out.println(algos.partitionWithMinDiffBewSubsets(new int[] {1, 3, 100, 4}));
		System.out.println(algos.partitionWithMinDiffBewSubsetsMemoization(new int[] {1, 2, 3, 9}));
		System.out.println(algos.partitionWithMinDiffBewSubsetsMemoization(new int[] {1, 2, 7, 1, 5}));
		System.out.println(algos.partitionWithMinDiffBewSubsetsMemoization(new int[] {1, 3, 100, 4}));
		System.out.println(algos.partitionWithMinDiffBewSubsetsTabulation(new int[] {1, 2, 3, 9}));
		System.out.println(algos.partitionWithMinDiffBewSubsetsTabulation(new int[] {1, 2, 7, 1, 5}));
		System.out.println(algos.partitionWithMinDiffBewSubsetsTabulation(new int[] {1, 3, 100, 4}));
		
		// Count of Subset Sum (hard)
		System.out.println("Count number of subsets with sum");
		System.out.println(algos.countSubsets(new int[] {1, 1, 2, 3}, 4));
		System.out.println(algos.countSubsets(new int[] {1, 2, 7, 1, 5}, 9));
		
		// Target Sum (hard)
		System.out.println("Target Sum");
		System.out.println(algos.findTargetSubsets(new int[] {1, 1, 2, 3}, 1));
		System.out.println(algos.findTargetSubsets(new int[] {1, 2, 7, 1}, 9));
	}
}
