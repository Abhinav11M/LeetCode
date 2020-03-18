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

		List<String> resStringCasePermutation = subsets.permutationByChangingCase("a2b");
		resStringCasePermutation = subsets.permutationByChangingCase("ad52");
		resStringCasePermutation = subsets.permutationByChangingCase("ab7c");
		
		List<String> parenthesis = subsets.generateValidParentheses(3);
		parenthesis = subsets.generateValidParentheses(2);
		
//		List<Integer> expressionRes = subsets.diffWaysToEvaluateExpression("1+2*3");
		List<Integer> expressionRes = subsets.diffWaysToEvaluateExpression("2*3-4-5");
		
		System.out.println();
	}

}
