package educative.subsets;

import java.util.List;

public class SubsetsMain {

	public static void main(String[] args) {
		Subsets subsets = new Subsets();
		List<List<Integer>> res = subsets.findSubsets(new int[] {1,2});
		res = subsets.findSubsets(new int[] {1,2,3});

		res = subsets.findSubsetsWithDuplicates(new int[] {1,3,3});
		
		res = subsets.findSubsetsIter(new int[] {1,2,3});

		res = subsets.findSubsetsWithDuplicatesIter(new int[] {1,3,3});
		
		res = subsets.permute(new int[] {1,2,3});

		res = subsets.permuteIter(new int[] {1,2,3});

		res = subsets.permuteRecursive(new int[] {1,2,3});

		res = subsets.permuteRecursive(new int[] {1,2,3,4});

		System.out.println();
	}

}
